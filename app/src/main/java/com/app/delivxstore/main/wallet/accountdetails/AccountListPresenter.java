package com.app.delivxstore.main.wallet.accountdetails;

import static com.app.delivxstore.utility.VariableConstants.IS_ACCOUNT_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.delivxstore.utility.VariableConstants.STRIP_DATA;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.accountdetails.model.AccountDeleteBody;
import com.app.delivxstore.main.wallet.bankaccount.BankAccountPresenter;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;
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

/*executes user request and presents to view*/
public class AccountListPresenter implements AccountListContract.Presenter {

  public static final String TAG = String.format("%s ", BankAccountPresenter.class.getSimpleName());
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  @Inject
  Gson gson;
  @Inject
  Context context;
  private AccountListContract.BankDetailsView mBankDetailsView;
  private StripeData mStripeData;

  @Inject
  AccountListPresenter() {

  }

  @Override
  public void attachView(AccountListFragment accountListFragment) {
    mBankDetailsView = accountListFragment;
  }

  @Override
  public void getAccounts() {
    if (Utility.isNetworkAvailable(context)) {
      mBankDetailsView.showProgress();
      Utility.printLog(TAG + "get account api request parameters :" + "");
      Observable<Response<ResponseBody>> request = networkService.getStripDetails(
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
              mBankDetailsView.hideProgress();
              Utility.printLog(TAG + "get account api " + response.code());
              JSONObject jsonObject;
              try {
                if (response.code() == RESPONSE_CODE_SUCCESS) {
                  if (response.body() != null) {
                    jsonObject = new JSONObject(response.body().string());
                    StripeResponse stripeResponse = gson.fromJson(jsonObject.toString(),
                        StripeResponse.class);
                    Utility.printLog(TAG + "get account api response" + jsonObject.toString());
                    preferenceHelperDataSource.setBusinessType(stripeResponse.getData().getBusinessType());
                    preferenceHelperDataSource.setStripeCountry(stripeResponse.getData().getCountry());
                    preferenceHelperDataSource.setStripeCurrency(stripeResponse.getData().getDefaultCurrency());
                    mBankDetailsView.setViews(stripeResponse.getData());
                  }
                } else {
                  if (response.errorBody() != null) {
                    jsonObject = new JSONObject(response.errorBody().string());
                    mBankDetailsView.showError(jsonObject.getString(MESSAGE));
                    Utility.printLog(TAG + "get account api error :" + jsonObject.getString(MESSAGE));
                  }
                }
              } catch (JSONException | IOException e) {
                e.printStackTrace();
                mBankDetailsView.showError(e.getMessage());
                Log.d(TAG, "catch " + e.getMessage());
              }
            }

            @Override
            public void onError(Throwable errorMsg) {
              mBankDetailsView.showError(errorMsg.getMessage());
              mBankDetailsView.hideProgress();
            }

            @Override
            public void onComplete() {
              mBankDetailsView.hideProgress();
            }
          });
    } else {
      mBankDetailsView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void deleteAccount(String accountId) {
    if (Utility.isNetworkAvailable(context)) {
      mBankDetailsView.showProgress();
      AccountDeleteBody accountDeleteBody = new AccountDeleteBody();
      accountDeleteBody.setAccountId(accountId);
      Utility.printLog(TAG + "delete account api request parameters :" + accountDeleteBody);
      Observable<Response<ResponseBody>> request = networkService.deleteBankAccount(
          ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
          preferenceHelperDataSource.getLanguage(),
          accountDeleteBody);
      request.subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> response) {
              mBankDetailsView.hideProgress();
              Utility.printLog(TAG + "delete account api " + response.code());
              JSONObject jsonObject;
              try {
                if (response.code() == RESPONSE_CODE_SUCCESS) {
                  String responseString;
                  if (response.body() != null) {
                    responseString = response.body().string();
                    Utility.printLog(TAG + "delete account api response" + responseString);
                    IS_ACCOUNT_UPDATED = true;
                    mBankDetailsView.goBack();
                  }
                } else {
                  if (response.errorBody() != null) {
                    jsonObject = new JSONObject(response.errorBody().string());
                    mBankDetailsView.showError(jsonObject.getString(MESSAGE));
                    Utility.printLog(TAG + "delete account api error :" + jsonObject.getString(MESSAGE));
                  }
                }
              } catch (JSONException | IOException e) {
                e.printStackTrace();
                mBankDetailsView.showError(e.getMessage());
                Log.d(TAG, "catch " + e.getMessage());
              }
            }

            @Override
            public void onError(Throwable errorMsg) {
              mBankDetailsView.showError(errorMsg.getMessage());
              mBankDetailsView.hideProgress();
            }

            @Override
            public void onComplete() {
              mBankDetailsView.hideProgress();
            }
          });
    } else {
      mBankDetailsView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void getBundleData(Bundle arguments) {
    if (arguments != null) {
      mStripeData = arguments.getParcelable(STRIP_DATA);
      if (mStripeData != null) {
        mBankDetailsView.setViews(mStripeData);
      }
    }
  }
}
