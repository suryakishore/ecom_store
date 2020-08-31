package com.app.delivxstore.main.profile;

import static com.app.delivxstore.utility.VariableConstants.DEFAULT_CURRENCY_SYMBOL;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.language.model.LanguageList;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.RxLogOutObsever;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.VariableConstants;
import com.app.ecomstore.boarding.login.model.LoginResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Objects;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/**
 * Override the method for the profile presenter
 * We do the functionality task here
 */
public class ProfilePresenter implements ProfileContract.PresenterOperations {

  public static final String TAG = String.format("%s ", ProfilePresenter.class.getSimpleName());
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  @Inject
  RxLogOutObsever rxLogOutObsever;
  private ProfileContract.ViewOperations mView;

  @Inject
  ProfilePresenter() {
  }

  @Override
  public void attachView(ProfileContract.ViewOperations profileFragment) {
    mView = profileFragment;
  }

  @Override
  public void logout() {
    LanguageList languagesList = preferenceHelperDataSource.getLanguageSettings();

    try {
      if (preferenceHelperDataSource.getPushTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getPushTopic()
        );
      }
      if (preferenceHelperDataSource.getStoreTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getStoreTopic()
        );
      }
      if (preferenceHelperDataSource.getPushCityTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getPushCityTopic()
        );
      }
      if (preferenceHelperDataSource.getFcmyTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getFcmyTopic()
        );
      }
      ((ApplicationManager)context.getApplicationContext()).disconnectMqtt();
      ((NotificationManager)Objects.requireNonNull(context.getSystemService(Context.NOTIFICATION_SERVICE))).cancelAll();
      preferenceHelperDataSource.clearSharedPredf();
      preferenceHelperDataSource.setLanguageSettings(languagesList);
      mView.logout();
    } catch (Throwable e) {
      Log.d("exe", "logout error " + e);
    }
  }

  @Override
  public void logoutOnClick() {
    if (mView != null) {
      mView.showProgress();
    }

    LogoutRequestData logoutRequestData = new LogoutRequestData();
    logoutRequestData.setIpAddress(Utility.getIpAddress(context));
    logoutRequestData.setLat(VariableConstants.CURRENT_ZONE_LAT);
    logoutRequestData.setLongi(VariableConstants.CURRENT_ZONE_LONGI);
    logoutRequestData.setCity("Bangalore");
    logoutRequestData.setCountry("India");
    logoutRequestData.setDeviceMake("");
    logoutRequestData.setDeviceId(Utility.getDeviceId(context));
    logoutRequestData.setOsVersion(android.os.Build.VERSION.RELEASE);

    final Observable<Response<ResponseBody>> logout = networkService.logout(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        1,
        DEFAULT_CURRENCY_SYMBOL,
        preferenceHelperDataSource.getCurrency(),
        logoutRequestData);

    logout.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {

          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog(TAG + "");
            try {
              if (mView != null) {
                mView.hideProgress();
              }
              if (value.code() == RESPONSE_CODE_SUCCESS) {
                logout();
              } else {
                JSONObject jsonObject = null;
                if (value.errorBody() != null) {
                  jsonObject = new JSONObject(value.errorBody().string());
                  mView.showError(jsonObject.getString(MESSAGE));
                }
              }
            } catch (JSONException | IOException e) {
              Utility.printLog("logout : Catch :" + e.getMessage());
              mView.showError(e.toString());
            }
          }

          @Override
          public void onError(Throwable e) {
            Utility.printLog("profile : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
          }
        });
  }

  @Override
  public void getProfileData() {
    LoginResponse loginResponse =
        new Gson().fromJson(preferenceHelperDataSource.getLoginResponse(), LoginResponse.class);
    mView.setValues(loginResponse.getData());
  }

  @Override
  public void updatePassword() {
    mView.setPassword(preferenceHelperDataSource.getPassword());
  }
}
