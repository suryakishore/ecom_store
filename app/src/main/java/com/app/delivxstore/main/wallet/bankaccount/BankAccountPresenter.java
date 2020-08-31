package com.app.delivxstore.main.wallet.bankaccount;


import static com.app.delivxstore.utility.VariableConstants.IS_ACCOUNT_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.content.Context;
import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.bankaccount.model.request.BankAccountBody;
import com.app.delivxstore.main.wallet.bankaccount.model.response.BankResponse;
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
public class BankAccountPresenter implements BankAccountContract.Presenter {

  public static final String TAG = String.format("%s ", BankAccountPresenter.class.getSimpleName());
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  @Inject
  Gson gson;
  @Inject
  Context context;
  private BankAccountContract.BankAccountView mBankAccountView;
  private BankAccountBody mBankAccountBody;

  @Inject
  BankAccountPresenter() {
    mBankAccountBody = new BankAccountBody();
  }

  @Override
  public void attachView(BankAccountFragment bankAccountFragment) {
    mBankAccountView = bankAccountFragment;
  }

  @Override
  public void validateAllFields(String bankCode, String accountNumber, String confirmAccountNo) {
    mBankAccountBody.setEmail(preferenceHelperDataSource.getMyEmail());
    mBankAccountBody.setAccountNumber(accountNumber);
    mBankAccountBody.setCountry(preferenceHelperDataSource.getStripeCountry());
    mBankAccountBody.setAccountHolderType(preferenceHelperDataSource.getBusinessType());
    mBankAccountBody.setAccountHolderName(preferenceHelperDataSource.getMyName());
    if (bankCode.isEmpty()) {
      mBankAccountView.showError(context.getString(R.string.enterBankCode));
    } else {
      if (accountNumber.isEmpty()) {
        mBankAccountView.showError(context.getString(R.string.enterAccountNumber));
      } else {
        if (confirmAccountNo.isEmpty()) {
          mBankAccountView.showError(context.getString(R.string.confirmAccount));
        } else {
          if (!accountNumber.equals(confirmAccountNo)) {
            mBankAccountView.showError(context.getString(R.string.accountError));
          } else {
            callAddAccountApi();
          }
        }
      }
    }
  }

  /*calls add account api*/
  private void callAddAccountApi() {
    if (Utility.isNetworkAvailable(context)) {
      mBankAccountView.showProgress();
      Utility.printLog(TAG + "add bank request parameters :" + mBankAccountBody.toString());
      Observable<Response<ResponseBody>> request =
          networkService.createBankAccount(
              ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
              preferenceHelperDataSource.getLanguage(), mBankAccountBody);
      request.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseBody>>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Response<ResponseBody> response) {
          Utility.printLog(TAG + "add bank " + response.code());
          JSONObject jsonObject;
          try {
            if (response.code() == RESPONSE_CODE_SUCCESS) {

              if (response.body() != null) {
                jsonObject = new JSONObject(response.body().string());
                BankResponse bankResponse = gson.fromJson(jsonObject.toString(),
                    BankResponse.class);
                IS_ACCOUNT_UPDATED = true;
                mBankAccountView.goBack();
                Utility.printLog(TAG + "add bank response" + jsonObject.toString());
              }
            } else {
              if (response.errorBody() != null) {
                jsonObject = new JSONObject(response.errorBody().string());
                mBankAccountView.showError(jsonObject.getString(MESSAGE));
                Utility.printLog(TAG + "add bank error :" + jsonObject.getString(MESSAGE));
              }
            }
          } catch (JSONException | IOException e) {
            e.printStackTrace();
            mBankAccountView.showError(e.getMessage());
            Log.d(TAG, "catch " + e.getMessage());
          }
        }

        @Override
        public void onError(Throwable errorMsg) {
          mBankAccountView.showError(errorMsg.getMessage());
        }

        @Override
        public void onComplete() {
          mBankAccountView.hideProgress();
        }
      });
    } else {
      mBankAccountView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }
}
