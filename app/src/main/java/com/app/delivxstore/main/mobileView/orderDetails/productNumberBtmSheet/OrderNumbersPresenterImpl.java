package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;

import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LAT;
import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LONGI;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;

import android.content.Context;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.networking.OrderPickInput;
import com.app.delivxstore.networking.ProductOrderInput;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
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

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class OrderNumbersPresenterImpl implements OrderNumberContract.OrderNumberPresenter {
  @Inject
  NetworkService networkService;
  @Inject
  Utility utility;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkStateHolder networkStateHolder;
  @Inject
  Context context;
  private OrderNumberContract.OrderNumberView mView;

  @Inject
  OrderNumbersPresenterImpl() {
  }

  @Override
  public void attachView(OrderNumberContract.OrderNumberView view) {
    this.mView = view;
  }

  @Override
  public void applyPack(String orderId, int quantity) {
    if (mView != null) {
      mView.showProgress();
    }
    ProductOrdersRequest productOrdersRequest = new ProductOrdersRequest();
    productOrdersRequest.setOrderId(orderId);
    productOrdersRequest.setQuantity(quantity);
    ArrayList<ProductOrdersRequest> productOrdersRequests = new ArrayList<>();
    productOrdersRequests.add(productOrdersRequest);
    OrderNumberRequest orderNumberRequest = new OrderNumberRequest();
    orderNumberRequest.setProductOrders(productOrdersRequests);
    orderNumberRequest.setIpAddress(Utility.getIpAddress(context));
    orderNumberRequest.setLatitude(String.valueOf(CURRENT_ZONE_LAT));
    orderNumberRequest.setLongitude(String.valueOf(CURRENT_ZONE_LONGI));
    final Observable<Response<ResponseBody>> userHistory = networkService.setPackOrder(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        orderNumberRequest);
    userHistory.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (mView != null) {
              mView.hideProgress();
            }
            try {
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                mView.setPackResult();
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
//                                view.showError(jsonObject.getString("message"),value.code());
                Utility.printLog("userHistory : " + jsonObject.toString());
              }
            } catch (JSONException e) {
              Utility.printLog("userHistory : Catch :" + e.getMessage());
            } catch (IOException e) {
              Utility.printLog("userHistory : Catch :" + e.getMessage());
            }
          }

          @Override
          public void onError(Throwable e) {
            if (mView != null) {
              mView.hideProgress();
            }
            Utility.printLog("userHistory : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (mView != null) {
              mView.hideProgress();
            }
          }
        });
  }



  @Override
  public int getStoreType() {
    return preferenceHelperDataSource.getStoreType();
  }

  @Override
  public void closeAct() {
    mView.finishActivity();
  }
}
