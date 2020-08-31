package com.app.delivxstore.main.wallet.stripe;

import static com.app.delivxstore.utility.VariableConstants.CAMERA_PIC;
import static com.app.delivxstore.utility.VariableConstants.CAUSE;
import static com.app.delivxstore.utility.VariableConstants.CROP_IMAGE;
import static com.app.delivxstore.utility.VariableConstants.FOUR;
import static com.app.delivxstore.utility.VariableConstants.GALLERY_PIC;
import static com.app.delivxstore.utility.VariableConstants.IS_ACCOUNT_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.ONE;
import static com.app.delivxstore.utility.VariableConstants.PATCH;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.delivxstore.utility.VariableConstants.STRIPE_PIC;
import static com.app.delivxstore.utility.VariableConstants.STRIP_DATA;
import static com.app.delivxstore.utility.VariableConstants.newFile;
import static com.app.delivxstore.utility.VariableConstants.newProfileImageUri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.stripe.model.request.PatchStripeBody;
import com.app.delivxstore.main.wallet.stripe.model.request.StripeAccountBody;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.UploadAwsImage;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import eu.janmuller.android.simplecropimage.CropImage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/*executes user request and presents to view*/
public class StripeAccountPresenter implements StripeAccountContract.StripPresenter {
  public static final String TAG = String.format("%s ",
      StripeAccountPresenter.class.getSimpleName());
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  @Inject
  Gson gson;
  @Inject
  Context context;
  @Inject
  UploadAwsImage amazonS3;
  private StripeAccountContract.StripView mStripView;
  private StripeAccountBody mStripeAccountBody;
  private int mYear, mMonth, mDay;
  private String mNewImage;
  private String mCause;

  @Inject
  StripeAccountPresenter() {
    mStripeAccountBody = new StripeAccountBody();
  }

  @Override
  public void attachView(StripeAccountFragment stripeAccountFragment) {
    mStripView = stripeAccountFragment;
  }

  @SuppressLint("DefaultLocale")
  @Override
  public void setDate(int year, int month, int dayOfMonth) {
    mYear = year;
    mMonth = month + ONE;
    mDay = dayOfMonth;
    mStripView.setDob(String.format("%d/%d/%d", dayOfMonth, month + ONE, year));
  }

  @Override
  public void validateFields(String firstName, String lastName, String ssn, String address,
                             String city, String state, String zipCode) {

    if (firstName.isEmpty()) {
      mStripView.error(context.getString(R.string.stripEnterLastName));
    } else {
      if (lastName.isEmpty()) {
        mStripView.error(context.getString(R.string.enterLastName));
      } else {
        if (ssn.isEmpty()) {
          mStripView.error(context.getString(R.string.enterSsn));
        } else {
          if (address.isEmpty()) {
            mStripView.error(context.getString(R.string.enterAddress));
          } else {
            if (city.isEmpty()) {
              mStripView.error(context.getString(R.string.enterCity));
            } else {
              if (state.isEmpty()) {
                mStripView.error(context.getString(R.string.enterState));
              } else {
                if (zipCode.isEmpty()) {
                  mStripView.error(context.getString(R.string.enterPostalCode));
                } else {
                  if (mNewImage == null || mNewImage.isEmpty()) {
                    mStripView.error(context.getString(R.string.uploadImage));
                  } else {
                    if (mCause != null && mCause.equals(PATCH)) {
                      callUpdateStripApi(firstName, lastName, ssn, address, city, state, zipCode);
                    } else {
                      callAddStripApi(firstName, lastName, ssn, address, city, state, zipCode);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  /*calls add strip account api*/
  private void callAddStripApi(String firstName, String lastName, String ssn, String address,
                               String city, String state, String zipCode) {
    if (Utility.isNetworkAvailable(context)) {
      mStripeAccountBody.setFirstName(firstName);
      mStripeAccountBody.setLastName(lastName);
      mStripeAccountBody.setDay(mDay);
      mStripeAccountBody.setMonth(mMonth);
      mStripeAccountBody.setYear(mYear);
      mStripeAccountBody.setSsnLast4(ssn.length() > FOUR ? ssn.substring(ssn.length() - FOUR) :
          ssn);
      mStripeAccountBody.setCity(city);
      mStripeAccountBody.setState(state);
      mStripeAccountBody.setPostalCode(Integer.parseInt(zipCode));
      mStripeAccountBody.setLine1(address);
      mStripeAccountBody.setEmail(preferenceHelperDataSource.getMyEmail());
      mStripeAccountBody.setIp(Utility.getIpAddress(context));
      mStripeAccountBody.setGender(context.getString(R.string.male));
/*
      mStripeAccountBody.setPhone(preferenceHelperDataSource.getMobile());
*/
      mStripeAccountBody.setPhone("");

      mStripView.showProgress();
      Utility.printLog(TAG + "add strip api request parameters :" + mStripeAccountBody.toString());
      Observable<Response<ResponseBody>> request =
          networkService.createStripAccount(
              ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
              preferenceHelperDataSource.getLanguage(), mStripeAccountBody);
      request.subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> response) {
              mStripView.hideProgress();
              Utility.printLog(TAG + "add strip api " + response.code());
              JSONObject jsonObject;
              try {
                if (response.code() == RESPONSE_CODE_SUCCESS) {
                  if (response.body() != null) {
                    jsonObject = new JSONObject(response.body().string());
                    IS_ACCOUNT_UPDATED = true;
                    mStripView.error(jsonObject.getString(MESSAGE));
                    mStripView.getBack();
                    Utility.printLog(TAG + "add strip api response" + jsonObject.toString());
                  }
                } else {
                  if (response.errorBody() != null) {
                    jsonObject = new JSONObject(response.errorBody().string());
                    mStripView.error(jsonObject.getString(MESSAGE));
                    Utility.printLog(TAG + "add strip api error :" + jsonObject.getString(MESSAGE));
                  }
                }
              } catch (JSONException | IOException e) {
                e.printStackTrace();
                mStripView.error(e.getMessage());
                Log.d(TAG, "catch " + e.getMessage());
              }
            }

            @Override
            public void onError(Throwable errorMsg) {
              mStripView.error(errorMsg.getMessage());
              mStripView.hideProgress();
            }

            @Override
            public void onComplete() {
              mStripView.hideProgress();
            }
          });
    } else {
      mStripView.error(context.getResources().getString(R.string.allNoNetworkError));
    }
  }


  /*calls update strip account api*/
  private void callUpdateStripApi(String firstName, String lastName, String ssn, String address,
                                  String city, String state, String zipCode) {
    PatchStripeBody patchStripeBody = new PatchStripeBody();
    if (Utility.isNetworkAvailable(context)) {
      patchStripeBody.setFirstName(firstName);
      patchStripeBody.setLastName(lastName);
      patchStripeBody.setIp(Utility.getIpAddress(context));
      mStripView.showProgress();
      Utility.printLog(TAG + "patch strip api request parameters :" + patchStripeBody.toString());
      Observable<Response<ResponseBody>> request =
          networkService.updateStripAccount(
              ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
              preferenceHelperDataSource.getLanguage(), patchStripeBody);
      request.subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> response) {
              mStripView.hideProgress();
              Utility.printLog(TAG + "patch strip api " + response.code());
              JSONObject jsonObject;
              try {
                if (response.code() == RESPONSE_CODE_SUCCESS) {
                  String responseString;
                  if (response.body() != null) {
                    jsonObject = new JSONObject(response.body().string());
                    IS_ACCOUNT_UPDATED = true;
                    mStripView.error(jsonObject.getString(MESSAGE));
                    mStripView.getBack();
                    Utility.printLog(TAG + "patch strip api response" + jsonObject.toString());
                  }
                } else {
                  if (response.errorBody() != null) {
                    jsonObject = new JSONObject(response.errorBody().string());
                    mStripView.error(jsonObject.getString(MESSAGE));
                    Utility.printLog(TAG + "patch strip api error :" + jsonObject.getString(MESSAGE));
                  }
                }
              } catch (JSONException | IOException e) {
                e.printStackTrace();
                mStripView.error(e.getMessage());
                Log.d(TAG, "catch " + e.getMessage());
              }
            }

            @Override
            public void onError(Throwable errorMsg) {
              mStripView.error(errorMsg.getMessage());
              mStripView.hideProgress();
            }

            @Override
            public void onComplete() {
              mStripView.hideProgress();
            }
          });
    } else {
      mStripView.error(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case CAMERA_PIC:
        mStripView.startCropImage();
        break;

      case GALLERY_PIC:
        try {
          newFile = Utility.filePath(context, data);
          newProfileImageUri = Uri.fromFile(newFile);
          mStripView.startCropImage();
        } catch (Exception e) {
          Utility.printLog("RegistrationAct in GALLERY_PIC Error while creating newfile:" + e);
        }
        break;
      case CROP_IMAGE:
        if (data != null) {
          String path = data.getStringExtra(CropImage.IMAGE_PATH);
          if (path == null) {
            Utility.printLog("RegistrationAct CROP_IMAGE file path is null: " + newFile.getPath());
            return;
          } else {
            newProfileImageUri = Uri.fromFile(newFile);
            try {
              byte[] bytes = new byte[(int)newFile.length()];
              InputStream inputStream =
                  context.getContentResolver().openInputStream(newProfileImageUri);
              if (inputStream != null) {
                inputStream.read(bytes);
              }
              amazonUpload(new File(path));
            } catch (Exception e) {
              Utility.printLog("RegistrationAct in CROP_IMAGE exception while copying file = " + e.toString());
            }
          }
        }
        break;
      default:
        break;
    }
  }

  @Override
  public void getBundleData(Bundle arguments) {
    if (arguments != null) {
      mCause = arguments.getString(CAUSE);
      mStripView.setViews(arguments.getParcelable(STRIP_DATA));
    }
  }

  /*upload image to aws server*/
  private void amazonUpload(File file) {
    mStripView.showProgress();
    Utility.printLog(String.format("the profile url is : %s", file.getName().replace(" ", "")));
    amazonS3.uploadData(STRIPE_PIC, context, file,
        new UploadAwsImage.UploadCallBack() {
          @Override
          public void success(String url) {
          }

          @Override
          public void success(String url, String type) {
            mStripView.hideProgress();
            mStripView.setStripPic(url);
            mNewImage = url;
            mStripeAccountBody.setDocument(mNewImage);
            Utility.printLog(TAG + "image url :" + url);
          }

          @Override
          public void error(String errorMessage) {
            Utility.printLog(TAG + "error message:" + errorMessage);
            mStripView.error(errorMessage);
            mStripView.hideProgress();
          }
        });
  }
}
