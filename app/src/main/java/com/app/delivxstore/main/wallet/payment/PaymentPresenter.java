package com.app.delivxstore.main.wallet.payment;

import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.content.Context;
import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeResponse;
import com.app.delivxstore.networking.NetworkService;
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

/*implements Presenter to execute user request*/
public class PaymentPresenter implements PaymentContract.Presenter {

  public static final String TAG = String.format("%s ", PaymentPresenter.class.getSimpleName());
  @Inject
  NetworkService networkService;
  @Inject
  Gson gson;
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject PaymentContract.PaymentView paymentView;

  @Inject
  PaymentPresenter(){

  }
  @Override
  public void getAccounts() {
    if (Utility.isNetworkAvailable(context)) {
      paymentView.showProgress();
      Observable<Response<ResponseBody>> request =
          networkService.getStripDetails(
              ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
              preferenceHelperDataSource.getLanguage());
      request.subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> response) {
              paymentView.hideProgress();
              Utility.printLog(TAG + "get account api " + response.code());
              JSONObject jsonObject;
              try {
                if (response.code() == RESPONSE_CODE_SUCCESS) {
                  if (response.body() != null) {
                    jsonObject = new JSONObject(response.body().string());
                    StripeResponse stripeResponse = gson.fromJson(jsonObject.toString(),
                        StripeResponse.class);
                    Utility.printLog(TAG + "get account api response" + jsonObject.toString());
                    paymentView.setUi(stripeResponse.getData());
                    preferenceHelperDataSource.setBusinessType(stripeResponse.getData().getBusinessType());
                    preferenceHelperDataSource.setStripeCountry(stripeResponse.getData().getCountry());
                    preferenceHelperDataSource.setStripeCurrency(stripeResponse.getData().getDefaultCurrency());
                  }
                } else {
                  if (response.errorBody() != null) {
                    jsonObject = new JSONObject(response.errorBody().string());
                    Utility.printLog(TAG + "get account api error :" + jsonObject.getString(MESSAGE));
                  }
                }
              } catch (JSONException | IOException e) {
                e.printStackTrace();
                Log.d(TAG, "catch " + e.getMessage());
              }
            }

            @Override
            public void onError(Throwable errorMsg) {
              paymentView.hideProgress();
            }

            @Override
            public void onComplete() {
              paymentView.hideProgress();
            }
          });
    } else {
      paymentView.onError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void attachView(PaymentActivity paymentFragment) {
    paymentView = paymentFragment;
  }
}
