package com.app.delivxstore.main.wallet;

import static com.app.delivxstore.utility.VariableConstants.ALL_TRANSACTION;
import static com.app.delivxstore.utility.VariableConstants.CREDIT_TRANSACTION;
import static com.app.delivxstore.utility.VariableConstants.DEBIT_TRANSACTION;
import static com.app.delivxstore.utility.VariableConstants.FETCH_SIZE;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.PAGE_STATE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.delivxstore.utility.VariableConstants.STORE;

import android.content.Context;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.wallet.response.TransactionData;
import com.app.delivxstore.main.wallet.response.TransactionItem;
import com.app.delivxstore.main.wallet.response.TransactionResponse;
import com.app.delivxstore.main.wallet.response.WalletDataItem;
import com.app.delivxstore.main.wallet.response.WalletResponse;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/*interacts with remote and local database
 * and informs view with latest values*/
public class WalletPresenter implements WalletContract.Presenter {
  public static final String TAG = String.format("%s ", WalletPresenter.class.getSimpleName());
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Gson gson;
  @Inject
  Context context;
  @Inject
  NetworkService networkService;
  private WalletContract.WalletView mWalletView;
  private WalletDataItem mWalletDataItem;
  private ArrayList<TransactionItem> mDebitArrayList = new ArrayList<>();
  private ArrayList<TransactionItem> mCreditArrayList = new ArrayList<>();
  private ArrayList<TransactionItem> mAllArrayList = new ArrayList<>();

  @Inject
  WalletPresenter() {
  }

  @Override
  public void attachView(WalletFragment walletFragment) {
    mWalletView = walletFragment;
  }

  @Override
  public void getWalletDetails() {
    if (Utility.isNetworkAvailable(context)) {
      mWalletView.showProgress();
      Observable<Response<ResponseBody>> getWalletDetails = networkService.getWalletDetails(
          ((ApplicationManager) context.getApplicationContext()).getAuthToken(
              preferenceHelperDataSource.getMyEmail()),
          preferenceHelperDataSource.getLanguage(),
          preferenceHelperDataSource.getStoreId(),
          STORE);
      getWalletDetails.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              mWalletView.hideProgress();
              Utility.printLog(TAG + "wallet details api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    String responseString = value.body().string();
                    WalletResponse walletResponse = gson.fromJson(responseString,
                        WalletResponse.class);
                    if (walletResponse.getData().getWalletData().size() > 0) {
                      setWalletData(walletResponse.getData().getWalletData().get(0));
                    } else {
                      mWalletView.showNoTransactions();
                      mWalletView.setValues(null);
                    }
                    Utility.printLog(TAG + "wallet details api response: " + responseString);
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    Utility.printLog(TAG + "wallet details api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "wallet details api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              mWalletView.hideProgress();
              Utility.printLog(TAG + "wallet details api error: " + e.toString());
            }

            @Override
            public void onComplete() {
            }
          });
    } else {
      mWalletView.error(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  /*sets data to view*/
  private void setWalletData(WalletDataItem data) {
    mWalletDataItem = data;
    getWalletTransactions(ALL_TRANSACTION);
    preferenceHelperDataSource.setCurrency(data.getCurrency());
    mWalletView.setValues(data);
  }

  @Override
  public void getWalletTransactions(int txnType) {
    if (Utility.isNetworkAvailable(context)) {
      mWalletView.showProgress();
      Utility.printLog("Requested parameters : " + mWalletDataItem.getWalletid());
      Observable<Response<ResponseBody>> getWalletDetails = networkService.getTransactions(
          ((ApplicationManager) context.getApplicationContext()).getAuthToken(
              preferenceHelperDataSource.getMyEmail()),
          preferenceHelperDataSource.getLanguage(),
          mWalletDataItem.getWalletid(),
          txnType,
          PAGE_STATE,
          FETCH_SIZE);
      getWalletDetails.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              mWalletView.hideProgress();
              Utility.printLog(TAG + "transactions api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    String responseString = value.body().string();
                    Utility.printLog(TAG + "responseString " + responseString);
                    JSONObject jsonObject1 = new JSONObject(responseString);
                    TransactionResponse transactionResponse = gson.fromJson(jsonObject1.toString(),
                        TransactionResponse.class);
                    PAGE_STATE = transactionResponse.getData().getPageState();
                    handelResponse(transactionResponse.getData(), txnType);
                    Utility.printLog(TAG + "transaction items  "
                        + transactionResponse.getData().getData().size());
                    Utility.printLog(TAG + "transactions api response: " + jsonObject1.toString());
                    Utility.printLog(TAG + "page state value : " + PAGE_STATE);
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    mWalletView.error(error);
                    Utility.printLog(TAG + "transactions api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "transactions api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              mWalletView.hideProgress();
              Utility.printLog(TAG + "transactions api error: " + e.toString());
            }

            @Override
            public void onComplete() {
            }
          });
    } else {
      mWalletView.error(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public void setupAllTransAdapter() {
    mWalletView.updateAdapter(mAllArrayList, context.getString(R.string.allTrans), ALL_TRANSACTION);
  }

  @Override
  public void setupDebitTransAdapter() {
    mWalletView.updateAdapter(mDebitArrayList, context.getString(R.string.debitTransactions),
        DEBIT_TRANSACTION);
  }

  @Override
  public void setupCreditTransAdapter() {
    mWalletView.updateAdapter(mCreditArrayList, context.getString(R.string.creditTransaction),
        CREDIT_TRANSACTION);
  }

  /*handles transaction data*/
  private void handelResponse(TransactionData data, int txnType) {
    mAllArrayList.addAll(data.getData());
    for (int i = 0; i < data.getData().size(); i++) {
      if (data.getData().get(i).getTxntype() == CREDIT_TRANSACTION) {
        mCreditArrayList.add(data.getData().get(i));
      } else {
        if (data.getData().get(i).getTxntype() == DEBIT_TRANSACTION) {
          mDebitArrayList.add(data.getData().get(i));
        }
      }
    }
    ArrayList<TransactionItem> transactionItems;
    transactionItems = txnType == ALL_TRANSACTION ? mAllArrayList
        : txnType == DEBIT_TRANSACTION ? mDebitArrayList : mCreditArrayList;
    int id = txnType == ALL_TRANSACTION ? R.string.allTrans
        : txnType == DEBIT_TRANSACTION ? R.string.debitTransactions : R.string.creditTransaction;
    mWalletView.updateAdapter(transactionItems, context.getString(id), txnType);
  }
}
