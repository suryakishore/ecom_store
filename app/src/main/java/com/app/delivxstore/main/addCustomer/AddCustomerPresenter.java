package com.app.delivxstore.main.addCustomer;

import android.app.Activity;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.MyTextUtils;
import com.app.delivxstore.utility.Utility;
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

public class AddCustomerPresenter implements AddCustomerContract.PresenterOperations {
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Utility utility;

  @Inject
  Activity context;

  @Inject
  AddCustomerContract.ViewOperations view;

  @Inject
  AddCustomerPresenter() {

  }

  @Override
  public void checkCustomer(String name, String phoneNumber, String emailAddress) {
    if (MyTextUtils.isEmpty(name)) {
      view.showError(context.getString(R.string.empty_name));
      return;
    } else if (MyTextUtils.isEmpty(phoneNumber)) {
      view.showError(context.getString(R.string.phone_empty));
      return;
    } else if (!MyTextUtils.isEmail(emailAddress)) {
      view.showError(context.getString(R.string.invalid_email));
      return;
    } else {
      addCustomer(name, phoneNumber, emailAddress);
    }
  }

  private void addCustomer(String name, String phoneNumber, String emailAddress) {

    view.showProgress();
    final Observable<Response<ResponseBody>> connectAccount =
        networkService.addCustomer(preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getToken(), name, emailAddress,
            preferenceHelperDataSource.getCountry(), phoneNumber, 2);
    connectAccount.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (view != null) {
              view.hideProgress();
            }

            try {

              Utility.printLog("connectAccount Code : " + value.code());

              JSONObject jsonObject;
              if (value.code() == 200) {
                jsonObject = new JSONObject(value.body().string());
                String customerId = jsonObject.getJSONObject("data").getString("_id");
                Utility.printLog("connectAccount value : " + jsonObject.toString());
                view.getCustomerValues(name, phoneNumber, emailAddress, customerId);
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
                view.showError(jsonObject.getString("message"));
              }


            } catch (JSONException e) {
              Utility.printLog("connectAccount : Catch :" + e.getMessage());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
            // view.onFailure();
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });

  }
}
