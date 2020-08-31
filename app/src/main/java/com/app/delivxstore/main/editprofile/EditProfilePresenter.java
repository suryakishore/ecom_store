package com.app.delivxstore.main.editprofile;

import static com.app.delivxstore.utility.VariableConstants.DEFAULT_CURRENCY_SYMBOL;
import static com.app.delivxstore.utility.VariableConstants.DEVICE_TYPE;
import static com.app.delivxstore.utility.VariableConstants.IS_PASSWORD_CHANGED;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.delivxstore.utility.VariableConstants.WITH_STORE;

import android.content.Context;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.editprofile.model.ResetPasswordBody;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.MyTextUtils;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/*implements Presenter interface to execute user request and respond to view*/
public class EditProfilePresenter implements EditProfileContract.Presenter {
  public static final String TAG = EditProfilePresenter.class.getSimpleName();
  @Inject
  EditProfileContract.EditProfileView view;
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Context context;
  @Inject
  Gson gson;
  private ResetPasswordBody mPasswordBody;

  @Inject
  EditProfilePresenter() {
    mPasswordBody = new ResetPasswordBody();
  }

  /*validates the password fields */
  private boolean validPassword(String newPass, String confirmPass) {
    if (MyTextUtils.isEmpty(newPass)) {
      view.onError(context.getString(R.string.entNewPass));
      return false;
    } else {
      if (MyTextUtils.isEmpty(confirmPass)) {
        view.onError(context.getResources().getString(R.string.reEnterPasswordError));
        return false;
      } else {
        if (!newPass.equals(confirmPass)) {
          view.onError(context.getResources().getString(R.string.mismatchPassword));
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String getLanguageCode() {
    return preferenceHelperDataSource.getLanguageSettings().getLanguageCode();
  }

  @Override
  public void updatePassword(String newPass, String confirmPass) {
    if (validPassword(newPass, confirmPass)) {
      callUpdatePasswordApi(newPass);
    } else {
      view.disableButton();
    }
  }

  /*call update password api*/
  private void callUpdatePasswordApi(String newPass) {
    if (Utility.isNetworkAvailable(context)) {
      mPasswordBody.setEmail(preferenceHelperDataSource.getMyEmail());
      mPasswordBody.setStoreId(preferenceHelperDataSource.getStoreId());
      mPasswordBody.setType(WITH_STORE);
      view.showProgress();
      Observable<Response<ResponseBody>> resetPassword = networkService.resetPassword(
          ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
          preferenceHelperDataSource.getLanguage(),
          DEVICE_TYPE,
          DEFAULT_CURRENCY_SYMBOL,
          preferenceHelperDataSource.getCurrency(),
          mPasswordBody);
      resetPassword.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              view.hideProgress();
              Utility.printLog(TAG + "password reset api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    String responseString = value.body().string();
                    preferenceHelperDataSource.setPassword(newPass);
                    IS_PASSWORD_CHANGED = true;
                    view.onSuccess();
                    Utility.printLog(TAG + "password reset api response: " + responseString);
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    Utility.printLog(TAG + "password reset api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "password reset api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              view.hideProgress();
              Utility.printLog(TAG + "password reset api error: " + e.toString());

            }

            @Override
            public void onComplete() {

            }
          });
    } else {
      view.onError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }
}
