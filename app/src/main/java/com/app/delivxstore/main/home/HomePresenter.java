/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.home;

import static com.app.ecomstore.util.EcomConstants.ACCEPTED;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CHECKED;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.DISPATCH;
import static com.app.ecomstore.util.EcomConstants.DRIVER_ID;
import static com.app.ecomstore.util.EcomConstants.FOUR;
import static com.app.ecomstore.util.EcomConstants.INTERNEL_SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.INVALID_TOKEN;
import static com.app.ecomstore.util.EcomConstants.NEW;
import static com.app.ecomstore.util.EcomConstants.NINE;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.PENDING;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.READY_FOR_PICK_UP;
import static com.app.ecomstore.util.EcomConstants.SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.STATUS;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.models.OrderCountData;
import com.app.delivxstore.main.home.models.OrderCountResponse;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RXMqttMessageObserver;
import com.app.delivxstore.observers.RXOrderResponseObserver;
import com.app.delivxstore.observers.RxLogOutObsever;
import com.app.delivxstore.observers.RxStoreObserver;
import com.app.delivxstore.observers.RxTextChangeObserver;
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
import org.json.JSONObject;
import retrofit2.Response;

/**
 * Override the method for the home presenter
 * We do the functionality task here
 */
public class HomePresenter implements HomeFragmentContract.PresenterOperations {
  @Inject
  Utility utility;
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  RxLogOutObsever rxLogOutObsever;
  @Inject
  NetworkService networkService;
  @Inject
  NetworkStateHolder networkStateHolder;
  @Inject
  RxStoreObserver rxStoreObserver;
  @Inject
  Connectable connectable;
  private Handler mHandler;
  private ArrayList<DriverData> mDriverList = new ArrayList<>();
  private HomeFragmentContract.ViewOperations mView;
  private Gson mGson = new Gson();
  private int mNewOrder, mAcceptedOrder, mPackedOrder, mReadyOrder, mInDispatchOrder, mCheckedOrder,
      mCancelledOrder,
      mCompletedOrder;
  /**
   * <h>observer object</h>
   * <p>this object is used to get the mqtt messages and manage the tab management accordingly.in
   * this every time mqtt messgae will come the onNext method will execute with the json object.
   * and this method will play a key role for data sycnching i.e,whenever the user is logged
   * in,all
   * the subscribed messages will come at a time with some 2 to 3 seconds delay,so for handling
   * all
   * the messages at a time we are showing one synching screen in home screen until the mqtt
   * messages are over.
   *
   * </p>
   */
  private Observer<JSONObject> mObserver = new Observer<JSONObject>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    /**
     * <h2>mqtt json object</h2>
     * <p>This method will execute whenever the new mqtt message will come,in this json object
     * you will take action tag value,if it is the new order we will call get orders
     *    api for refreshing the corresponding tabs data. and status messages for the
     *    corresponding orders.</p>
     * @param value this param represents the json object of the mqtt message.
     */
    @Override
    public void onNext(JSONObject value) {
      if (value.has(STATUS)) {
        getOrders();
       /*
         Data data = mGson.fromJson(value.toString(), Data.class);
        Status status = data.getStatus();
        int statusCode=Integer.parseInt(status.getStatus());
        RXOrderResponseObserver.getInstance().emit(data);
       switch (statusCode){
            case ACCEPTED:
              acceptedOrder+=ONE;
              RXOrderResponseObserver.getInstance().emit(data);
              break;
            case CANCELLED:
              acceptedOrder-=ONE;
              RXOrderResponseObserver.getInstance().emit(data);
              break;
          }
        mView.setCountValues(
            newOrder + "",
            acceptedOrder + "",
            cancelledOrder + "",
            packedOrder + "",
            readyOrder + "",
            inDispatchOrder + "",
            completedOrder + "");*/
      } else if (value.has(DRIVER_ID)) {
        DriverData driverData = mGson.fromJson(value.toString(), DriverData.class);
        if (mDriverList.contains(driverData)) {
          if (driverData.getStatus().equals(String.format("%d", FOUR))
              || driverData.getStatus().equals(String.format("%d", NINE))) {
            mDriverList.remove(driverData);
            rxStoreObserver.publishData(mDriverList);
          }
        } else {
          mDriverList.add(driverData);
          rxStoreObserver.publishData(mDriverList);
        }
      }
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };

  @Inject
  HomePresenter() {
  }

  @Override
  public void checkScreenSize() {
    mHandler = new Handler();
    mView.setView(utility.isTablet(context), preferenceHelperDataSource.getStoreType(),
        preferenceHelperDataSource.getForceAccept(), preferenceHelperDataSource.getAutoDispatch(),
        preferenceHelperDataSource.getDriverType(), preferenceHelperDataSource.getPackage());
    RXOrderResponseObserver.getInstance().clear();
    RXMqttMessageObserver.getInstance().subscribe(mObserver);
  }

  @Override
  public void attachView(HomeFragmentContract.ViewOperations viewOperations) {
    this.mView = viewOperations;
  }

  @Override
  public void getOrders() {
    mView.showProgress();
    final Observable<Response<ResponseBody>> getOrders = networkService.getOrdersCount(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        preferenceHelperDataSource.getCityId());
    getOrders.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            mView.hideProgress();
            switch (value.code()) {
              case SUCCESS:
                try {
                  String response = value.body().string();
                  Log.d("exe", "response" + response);
                  OrderCountResponse orderCountResponse = mGson.fromJson(response,
                      OrderCountResponse.class);
                  ArrayList<OrderCountData> data = orderCountResponse.getData();
                  for (int i = ZERO; i < data.size(); i++) {
                    OrderCountData orderCountData = data.get(i);
                    switch (orderCountData.getStatus()) {
                      case NEW:
                        mNewOrder = orderCountData.getCount();
                        break;
                      case ACCEPTED:
                        mAcceptedOrder = orderCountData.getCount();
                        break;
                      case PACKED:
                        mPackedOrder = orderCountData.getCount();
                        break;
                      case READY_FOR_PICK_UP:
                        mReadyOrder = orderCountData.getCount();
                        break;
                      case DISPATCH:
                        mInDispatchOrder = orderCountData.getCount();
                        break;
                      case CHECKED:
                        mCheckedOrder = orderCountData.getCount();
                        break;
                      case PENDING:
                        if (preferenceHelperDataSource.getStoreType() == PHARMACY) {
                          mNewOrder += orderCountData.getCount();
                        }
                        break;
                    }
                  }
                  Log.d("exe", "mCheckedOrder" + mCheckedOrder);
                  mView.setCountValues(preferenceHelperDataSource.getStoreType(),
                      String.format("%d", mNewOrder),
                      String.format("%d", mAcceptedOrder),
                      String.format("%d", mCancelledOrder),
                      String.format("%d", mPackedOrder),
                      String.format("%d", mReadyOrder),
                      String.format("%d", mCheckedOrder),
                      String.format("%d", mInDispatchOrder),
                      String.format("%d", mCompletedOrder));
                  RXOrderResponseObserver.getInstance().emit(null);
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
              case SERVER_ERROR:
              case INTERNEL_SERVER_ERROR:
                mView.showMessage(context.getResources().getString(R.string.serverError));
                break;
              case INVALID_TOKEN:
                mView.tokenExpire();
                break;
              default:
                try {
                  mView.showMessage(value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            mView.hideProgress();
            mView.showMessage("Sorry" + e.getMessage());
          }

          @Override
          public void onComplete() {
          }
        });
  }

  @Override
  public void logout() {
    preferenceHelperDataSource.clearSharedPredf();
    mView.startLogin();
  }

  @Override
  public void emitSearchData(String searchText) {
    RxTextChangeObserver.getInstance().emit(searchText);
  }
}
