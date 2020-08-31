package com.app.delivxstore.main.history.historydetails;

import static com.app.delivxstore.utility.VariableConstants.HISTORY_DATA;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.MESSAGE;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.history.model.HistoryData;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;
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

/*deals with local and remote data to update view*/
public class HistoryDetailsPresenter implements HistoryDetailsContract.Presenter {
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Context context;
  @Inject
  HistoryDetailsContract.HistoryDetailsView historyDetailsView;
  private OrderDetailsModel mOrderDetails;
  private String mOrderID;

  @Inject
  HistoryDetailsPresenter() {
  }

  @Override
  public void getBundleData(Intent intent) {

  }

  @Override
  public void callHistoryDetail(Bundle extras) {
    mOrderID = extras.getString(
        preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER_ID : STORE_ORDER_ID);
    if (mOrderID != null) {
      getOrder(mOrderID);
    }
  }

  @Override
  public int getStoreType() {
    return preferenceHelperDataSource.getStoreType();
  }

  /**
   * <h>getOrderDetails</h>
   * <p>get the order details</p>
   *
   * @param orderID order id for which details are needed
   */
  private void getOrder(String orderID) {
    if (historyDetailsView != null) {
      historyDetailsView.showProgress();
    }
    Observable<Response<ResponseBody>> getOrder;
    getOrder = networkService.getOrderDetails(
        ((ApplicationManager) context.getApplicationContext()).getAuthToken(
            preferenceHelperDataSource.getMyEmail()),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER : STORE_ORDER,
        orderID);
    getOrder.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("getOrderDetails : Code :" + value.code());
            switch (value.code()) {
              case SUCCESS:
                JSONObject jsonObject;
                try {
                  String response = value.body().string();
                  Utility.printLog("getOrderDetails : Code :" + response);
                  Gson gson = new Gson();
                  mOrderDetails = gson.fromJson(
                      response,
                      OrderDetailsModel.class);
                  if (mOrderDetails != null) {
                    historyDetailsView.setViews(mOrderDetails,
                        preferenceHelperDataSource.isCityLogin());
                  }
                } catch (Exception e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                }
                break;
              default:
                try {
                  jsonObject = new JSONObject(value.errorBody().string());
                  historyDetailsView.showError(jsonObject.getString(MESSAGE), value.code());
                } catch (JSONException | IOException e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (historyDetailsView != null) {
              historyDetailsView.hideProgress();
            }
            Utility.printLog("getOrderDetails : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (historyDetailsView != null) {
              historyDetailsView.hideProgress();
            }
          }
        });
  }
}
