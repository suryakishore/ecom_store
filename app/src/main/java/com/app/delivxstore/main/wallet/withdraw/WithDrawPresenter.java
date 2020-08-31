package com.app.delivxstore.main.wallet.withdraw;

import static com.app.delivxstore.utility.VariableConstants.ACCOUNT_DATA;
import static com.app.delivxstore.utility.VariableConstants.FOR;
import static com.app.delivxstore.utility.VariableConstants.IS_WALLET_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.delivxstore.utility.VariableConstants.STORE;

import android.content.Context;
import android.os.Bundle;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.withdraw.model.WithDrawRequestBody;
import com.app.delivxstore.networking.NetworkService;
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

/*implements RechargePresenter and execute user requests*/
public class WithDrawPresenter implements WithDrawContract.Presenter {

  public static final String TAG = String.format("%s ", WithDrawPresenter.class.getName());
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelper;
  @Inject
  NetworkService networkService;
  private WithDrawRequestBody mWithDrawBody;
  private WithDrawContract.WithDrawView mRechargeView;

  @Inject
  WithDrawPresenter() {
    mWithDrawBody = new WithDrawRequestBody();
  }

  @Override
  public void attachView(WithDrawFragment rechargeFragment) {
    mRechargeView = rechargeFragment;
  }

  @Override
  public void withDrawApi(String bankId, String amount, String pgName) {

    if (Utility.isNetworkAvailable(context)) {
      mWithDrawBody.setAmount(amount);
      mWithDrawBody.setAutoPayout(true);
      mWithDrawBody.setPgId(pgName);
      mWithDrawBody.setBankId(bankId);
      mWithDrawBody.setUserId(preferenceHelper.getStoreId());
      mWithDrawBody.setUserType(STORE);
      mWithDrawBody.setCurrency(preferenceHelper.getCurrency());
      mWithDrawBody.setPgName(pgName);
      mWithDrawBody.setNotes(TAG);
      Utility.printLog(TAG + "Requested parameters : " + mWithDrawBody.toString());

      Observable<Response<ResponseBody>> withDrawObserver = networkService.walletWithDraw(
          ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelper.getMyEmail()),
          preferenceHelper.getLanguage(),
          mWithDrawBody);
      withDrawObserver.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              mRechargeView.hideProgress();
              Utility.printLog(TAG + "withdraw api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    String responseString = value.body().string();
                    JSONObject jsonObject1 = new JSONObject(responseString);
                    IS_WALLET_UPDATED = true;
                    mRechargeView.showSuccess(String.format("%s %s",
                        preferenceHelper.getCurrency(), amount),
                        context.getString(R.string.withDrawSuccess));
                    Utility.printLog(TAG + "withdraw api response: " + jsonObject1.toString());
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    mRechargeView.showError(error);
                    Utility.printLog(TAG + "withdraw api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "withdraw api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              mRechargeView.hideProgress();
              Utility.printLog(TAG + "withdraw api error: " + e.toString());

            }

            @Override
            public void onComplete() {

            }
          });
    } else {
      mRechargeView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void getBundleData(Bundle arguments) {
    String cause = arguments.getString(FOR);
    assert cause != null;
    mRechargeView.setWithDrawUi(arguments.getParcelable(ACCOUNT_DATA),
        preferenceHelper.getCurrency(), cause);
  }
}
