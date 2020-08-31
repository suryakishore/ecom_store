package com.app.delivxstore.main.history;

import static com.app.delivxstore.utility.VariableConstants.ALL_STORE;
import static com.app.delivxstore.utility.VariableConstants.DEFAULT_CURRENCY_SYMBOL;
import static com.app.delivxstore.utility.VariableConstants.DEVICE_TYPE;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.HISTORY_STATUS;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.history.model.HistoryResponse;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.RxLogOutObsever;
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

/*interacts with remote and local database to get
 * order history and informs view with latest values*/
public class HistoryPresenter implements HistoryContract.Presenter {
  public static final String TAG = String.format("%s ", HistoryPresenter.class.getSimpleName());
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  @Inject
  RxLogOutObsever rxLogOutObsever;
  @Inject
  Gson gson;
  private HistoryContract.HistoryView mHistoryView;
  private boolean mIsLoading;

  @Inject
  public HistoryPresenter() {
  }

  @Override
  public void attachView(HistoryFragment historyFragment) {
    mHistoryView = historyFragment;
  }

  @Override
  public void getOrderHistory(int skip, int limit,String search,String orderTime) {
    mHistoryView.showProgress();
    mIsLoading = TRUE;
    Observable<Response<ResponseBody>> historyObserver = networkService.getOrderHistory(
        ((ApplicationManager) context.getApplicationContext()).getAuthToken(
            preferenceHelperDataSource.getMyEmail()),
        preferenceHelperDataSource.getLanguage(),
        DEVICE_TYPE,
        DEFAULT_CURRENCY_SYMBOL,
        preferenceHelperDataSource.getCurrency(),
        limit, skip, HISTORY_STATUS,
        preferenceHelperDataSource.getCityId(),
        ZERO,
        ZERO,
        ALL_STORE,
        orderTime,
        search);
    historyObserver.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            mHistoryView.hideProgress();
            mIsLoading = FALSE;
            Utility.printLog(TAG + "history api response code: " + value.code());
            try {
              JSONObject jsonObject;
              if (value.code() == RESPONSE_CODE_SUCCESS) {
                if (value.body() != null) {
                  String responseString = value.body().string();
                  jsonObject = new JSONObject(responseString);
                  HistoryResponse historyResponse = new Gson().fromJson(jsonObject.toString(),
                      HistoryResponse.class);
                  if (skip==ZERO&&limit==LIMIT)
                  mHistoryView.setData(historyResponse.getData(), historyResponse.getCount());
                  else
                    mHistoryView.setData(historyResponse.getData());
                  Utility.printLog(TAG + "history api response : " + responseString);
                } else {
                  Utility.printLog(TAG + "value body is null");
                }
              } else {
                if (value.errorBody() != null) {
                  jsonObject = new JSONObject(value.errorBody().string());
                  mHistoryView.showError(jsonObject.getString(MESSAGE));
                  Utility.printLog(TAG + "history api  error: " + jsonObject.getString(MESSAGE));
                }
              }
            } catch (IOException | JSONException e) {
              Utility.printLog(TAG + "history api catch: " + e.toString());
              mHistoryView.showError(e.toString());
            }
          }

          @Override
          public void onError(Throwable e) {
            mHistoryView.hideProgress();
            Utility.printLog(TAG + "history api rx error: " + e.toString());
          }

          @Override
          public void onComplete() {
            mHistoryView.hideProgress();
          }
        });
  }

  @Override
  public boolean isLoading() {
    return mIsLoading;
  }
}
