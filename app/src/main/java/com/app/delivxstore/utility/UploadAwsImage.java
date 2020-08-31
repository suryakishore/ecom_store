package com.app.delivxstore.utility;

import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.content.Context;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.model.CognitoResponse;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class UploadAwsImage {

  private static UploadAwsImage mUploadAws;
  @Inject
  PreferenceHelperDataSource preferencesHelper;
  @Inject
  NetworkService networkService;
  private CognitoCachingCredentialsProvider mCredentialsProvider = null;
  private AmazonS3Client mS3Client = null;
  private TransferUtility mTransferUtility = null;
  private IdentityProvider mDeveloperProvider;

  /*create singleton object by defining private.*/
  public UploadAwsImage(Context context,
                        PreferenceHelperDataSource preferenceHelperDataSource,
                        NetworkService networkService) {
    this.preferencesHelper = preferenceHelperDataSource;
    this.networkService = networkService;
  }

  /*return instance of UploadAwsImage class*/
  public static UploadAwsImage getInstance(Context context,
                                           PreferenceHelperDataSource preferenceHelperDataSource,
                                           NetworkService networkService) {
    if (mUploadAws == null) {
      mUploadAws = new UploadAwsImage(context, preferenceHelperDataSource,
          networkService);
      return mUploadAws;
    } else {
      return mUploadAws;
    }
  }

  /*calls api for cognito id and then on success upload
   * image to aws*/
  public void uploadData(String type, Context context, final File file,
                         final UploadCallBack callBack) {
    getCognitoToken(context, file, callBack, type);
  }


  /*calls api for getting aws credentials*/
  private void getCognitoToken(Context context, File file,
                               UploadCallBack callBack, String type) {
    Observable<Response<ResponseBody>> obsResponseBody =
        networkService.getCognitoToken(
            ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferencesHelper.getMyEmail()),
            preferencesHelper.getLanguage(),
            VariableConstants.DEVICE_TYPE,
            VariableConstants.DEFAULT_CURRENCY_SYMBOL,
            preferencesHelper.getCurrency());
    obsResponseBody.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Response<ResponseBody>>() {

          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("cognito api code: " + value.code());
            JSONObject jsonObject;
            try {
              switch (value.code()) {
                case RESPONSE_CODE_SUCCESS:
                  if (value.body() != null) {
                    jsonObject = new JSONObject(value.body().string());
                    CognitoResponse cognitoResponse = new Gson().fromJson(jsonObject.toString(),
                        CognitoResponse.class);
                    if (cognitoResponse.getData() != null) {
                      preferencesHelper.setBucketName(cognitoResponse.getData().getBucket());
                      preferencesHelper.setCognitoId(cognitoResponse.getData().getIdentityId());
                      preferencesHelper.setAwsRegion(cognitoResponse.getData().getRegion());
                      preferencesHelper.setCognitoToken(cognitoResponse.getData().getToken());
                      mDeveloperProvider = new IdentityProvider(null,
                          preferencesHelper.getCognitoId(), Regions.AP_SOUTH_1);
                      onSuccess(context, preferencesHelper.getBucketName(), file, callBack, type);
                    }
                    Utility.printLog("cognito api response: " + jsonObject.toString());
                  }
                  break;
                default:
                  if (value.errorBody() != null) {
                    jsonObject = new JSONObject(value.errorBody().string());
                    Utility.printLog("cognito api error: " + jsonObject.toString());
                    callBack.error(jsonObject.toString());
                  }
                  break;
              }
            } catch (JSONException | IOException ex) {
              Utility.printLog("cognito catch error: " + ex.toString());
            }
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  /*upload image to aws on success of api call*/
  private void onSuccess(Context context, String bucketName, File file,
                         UploadCallBack callBack, String type) {
    getCredentialProvider(context);
    if (mTransferUtility != null && file != null) {
      TransferObserver observer = mTransferUtility.upload(bucketName,
          String.format("%s/%s", bucketName, file.getName()), file,
          CannedAccessControlList.PublicRead);
      observer.setTransferListener(new TransferListener() {
        @Override
        public void onStateChanged(int id, TransferState state) {
          if (state.equals(TransferState.COMPLETED)) {
            callBack.success(String.format("%s%s/%s", BuildConfig.AMAZON_BASE_URL, bucketName,
                file.getName()), type);
          }
        }

        @Override
        public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

        }

        @Override
        public void onError(int id, Exception ex) {
          callBack.error(id + ":" + ex.toString());
        }
      });
    } else {
      callBack.error("Amamzones3 is not intialize or File is empty !");
    }
  }

  /*delete the item from aws*/
  public void deleteItem(String bucketName, String keyName) {
    try {
      if (mS3Client != null) {
        mS3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
      }
    } catch (AmazonServiceException ase) {
      System.out.println("Caught an AmazonServiceException.");
      System.out.println("Error Message:    " + ase.getMessage());
      System.out.println("HTTP Status Code: " + ase.getStatusCode());
      System.out.println("AWS Error Code:   " + ase.getErrorCode());
      System.out.println("Error Type:       " + ase.getErrorType());
      System.out.println("Request ID:       " + ase.getRequestId());
    } catch (AmazonClientException ace) {
      System.out.println("Caught an AmazonClientException.");
      System.out.println("Error Message: " + ace.getMessage());
    }
  }

  /**
   * This method is used to get the CredentialProvider and we provide only context as a
   * parameter.
   *
   * @param context Here, we are getting the context from calling Activity.
   */
  private void getCredentialProvider(Context context) {
    mDeveloperProvider.updateCredentials(preferencesHelper.getCognitoId(),
        preferencesHelper.getCognitoToken());
    mCredentialsProvider = new CognitoCachingCredentialsProvider(context,
        mDeveloperProvider,
        Regions.AP_SOUTH_1);
    mS3Client = getS3Client();
    mTransferUtility = getTransferUtility(context, mS3Client);
  }

  /*return AmazonS3 Client*/
  private AmazonS3Client getS3Client() {
    mS3Client = new AmazonS3Client(mCredentialsProvider, Region.getRegion(Regions.AP_SOUTH_1));
    return mS3Client;
  }

  /*returns transfer utility object*/
  private TransferUtility getTransferUtility(Context context, AmazonS3Client amazonS3Client) {
    if (mTransferUtility == null) {
      mTransferUtility = new TransferUtility(amazonS3Client, context.getApplicationContext());
    }
    return mTransferUtility;
  }

  /**
   * Interface for the success callback fro the Amazon uploading .
   */
  public interface UploadCallBack {
    /**
     * Method for sucess .
     **/
    void success(String success);

    void success(String url, String type);

    /**
     * Method for falure.
     *
     * @param errormsg contains the error message.
     */
    void error(String errormsg);

  }

  /**
   * Interface for the success callback for downloading file. .
   */
  public interface DownloadCallBack {
    /**
     * Method for sucess .
     *
     * @param url it is true on sucess and false for falure.
     */
    void success(String url);


    /**
     * Method for falure.
     *
     * @param errormsg contains the error message.
     */
    void error(String errormsg);

  }
}
