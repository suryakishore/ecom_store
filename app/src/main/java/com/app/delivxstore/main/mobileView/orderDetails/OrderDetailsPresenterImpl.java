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

package com.app.delivxstore.main.mobileView.orderDetails;

import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LAT;
import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LONGI;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CHECKED;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.DATA;
import static com.app.ecomstore.util.EcomConstants.DEF_LAN;
import static com.app.ecomstore.util.EcomConstants.DEF_LAT;
import static com.app.ecomstore.util.EcomConstants.DELAY_STATUS;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.IP_ADDRESS;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.MESSAGE;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.PACKAGE_ID;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ORDER_TYPE;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.STATUS;
import static com.app.ecomstore.util.EcomConstants.STATUS_MSG;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.SUCCESS_RESPONSE;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.os.Bundle;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.CancelReasons;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberRequest;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.ProductOrdersRequest;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.UnavailableItemRequest;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.UnavailableOrderRequest;
import com.app.delivxstore.networking.CancelOrderRequest;
import com.app.delivxstore.networking.ConfirmOrderInput;
import com.app.delivxstore.networking.DispatcherService;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.networking.OrderPickInput;
import com.app.delivxstore.networking.OrderPickedInput;
import com.app.delivxstore.networking.OrderUpdateStatus;
import com.app.delivxstore.networking.ProductOrderInput;
import com.app.delivxstore.networking.ReadyForPickUpStatus;
import com.app.delivxstore.observers.RXMqttMessageObserver;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.drivers.GenerateLabelRequest;
import com.app.ecomstore.util.EcomUtil;
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

/**
 * Override the method for the order details presenter
 * We do the functionality task here
 */
class OrderDetailsPresenterImpl implements OrderDetailsContract.OrderDetailsPresenter {
  @Inject
  OrderDetailsContract.OrderDetailsView view;
  @Inject
  NetworkService networkService;
  @Inject
  Utility utility;
  @Inject
  DispatcherService dispatcherService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkStateHolder networkStateHolder;
  @Inject
  Context context;
  private String mStoreId;
  private OrderDetailsModel mOrderDetails;
  private String mOrderID, mPackageId;
  /**
   * <h>observer object</h>
   * <p>this object is used to get the mqtt messages and manage the tab management accordingly.
   * in this every time mqtt messgae will come the onNext method will execute with the
   * json object.
   * and this method will play a key role for data sycnching i.e,whenever the user is logged in,
   * all the subscribed messages will come at a time with some 2 to 3 seconds delay,so
   * for handling all
   * the messages at a time we are showing one synching screen in home screen until the
   * mqtt messages are over.
   *
   * </p>
   */
  Observer<JSONObject> observer = new Observer<JSONObject>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(JSONObject value) {
      try {
        if (value.has(STATUS_MSG)) {
          String orderid = value.getString(ORDER_ID);
          String statusMsg = value.getString(STATUS_MSG);
          if (statusMsg.contains(context.getResources().getString(R.string.managerUpdate))
              && mOrderID.equals(orderid)) {
            if (value.has(context.getResources().getString(R.string.items))) {
              String response = value.toString();
              Gson gson = new Gson();
              OrderDetailsModel orderDetails = gson.fromJson(
                  response,
                  OrderDetailsModel.class);
              view.addItems(
                  orderDetails.getData().getProducts(),
                  preferenceHelperDataSource.getCurrency(),
                  value.getString(context.getResources().getString(R.string.storeType)));
            }
          } else {
            if (value.has(ORDER_ID)) {
              if (mOrderID.equals(orderid)) {
                view.dissMiss();
              }
            }
          }
        } else if (value.has(ORDER_ID)) {
          String orderid = value.getString(ORDER_ID);
          if (mOrderID.equals(orderid)) {
            view.dissMiss();
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };
  private int mStatus;
  private String mDueTime;
  private String mTimeStamp;
  private PastOrdersData mPastOrdersData;

  @Inject
  OrderDetailsPresenterImpl() {
  }

  @Override
  public void getBundleData(Bundle extras) {
    mOrderID = extras.getString(
        preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER_ID : STORE_ORDER_ID);
    mPackageId = extras.getString(PACKAGE_ID);
    mStatus = extras.getInt(STATUS, ZERO);
    RXMqttMessageObserver.getInstance().subscribe(observer);
    if (mStatus >= PACKED && mStatus != CHECKED) {
      getPackageDetails(mPackageId);
    } else {
      getOrder(mOrderID);
    }
  }

  @Override
  public String getStoreId() {
    return mStoreId;
  }

  @Override
  public String getLanguage() {
    return preferenceHelperDataSource.getLanguage();
  }

  @Override
  public int getStoreType() {
    return preferenceHelperDataSource.getStoreType();
  }

  @Override
  public void getFares(float subTotal) {
    view.setFares(
        String.format("%s", subTotal),
        String.format("%s", mOrderDetails.getData().getSellerAccounting().getOfferDiscount()),
        String.format("%s", mOrderDetails.getData().getSellerAccounting().getPromoDiscount()),
        String.format("%s", mOrderDetails.getData().getSellerAccounting().getDeliveryFee()), "0",
        String.format("%s", mOrderDetails.getData().getSellerAccounting().getFinalTotal()),
        mOrderDetails.getData().getSellerAccounting().getTax(),
        mOrderDetails.getData().getStoreType(),
        String.format("%s", mOrderDetails.getData().getSellerAccounting().getSubTotal()));
  }

  /**
   * <h>getOrderDetails</h>
   * <p>get the order details</p>
   *
   * @param orderID order id for which details are needed
   */
  private void getOrder(String orderID) {
    if (view != null) {
      view.showProgress();
    }
    Observable<Response<ResponseBody>> getOrder;
    getOrder = networkService.getOrderDetails(
        preferenceHelperDataSource.getToken(),
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
                  if (mOrderDetails.getData().get_id() != null) {
                    mStoreId = mOrderDetails.getData().get_id();
                  }
                  if (mOrderDetails != null) {
                    view.setViews(mOrderDetails, preferenceHelperDataSource.isCityLogin());
                  }
                } catch (Exception e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                }
                break;
              default:
                try {
                  jsonObject = new JSONObject(value.errorBody().string());
                  view.showError(jsonObject.getString(MESSAGE), value.code());
                } catch (JSONException | IOException e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("getOrderDetails : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <h>get</h>
   * <p>get the package details</p>
   *
   * @param packageId package id for which details are needed
   */
  private void getPackageDetails(String packageId) {
    if (view != null) {
      view.showProgress();
    }
    Observable<Response<ResponseBody>> getOrder;
    getOrder = networkService.getPackageDetails(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        packageId);
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
                  if (mOrderDetails.getData().get_id() != null) {
                    mStoreId = mOrderDetails.getData().get_id();
                  }
                  if (mOrderDetails != null) {
                    view.setViews(mOrderDetails, preferenceHelperDataSource.isCityLogin());
                  }
                } catch (Exception e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.toString());
                }
                break;
              default:
                try {
                  jsonObject = new JSONObject(value.errorBody().string());
                  view.showError(jsonObject.getString(MESSAGE), value.code());
                } catch (JSONException | IOException e) {
                  Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("getOrderDetails : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <h>getUserHistory</h>
   * <p>get the user past order</p>
   */
  public void getUserHistory() {
    if (view != null) {
      view.showProgress();
    }
    final Observable<Response<ResponseBody>> userHistory = networkService.getPastOrder(
        preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getToken(),
        mOrderDetails.getData().getCustomerId(),
        String.format("%d", ZERO)
    );
    userHistory.observeOn(AndroidSchedulers.mainThread())
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
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                String response = value.body().string();
                jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                mPastOrdersData = gson.fromJson(
                    jsonObject.getJSONObject(DATA).toString(),
                    PastOrdersData.class);
                view.setPastOrders(mPastOrdersData);
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
                Utility.printLog("userHistory : " + jsonObject.toString());
              }
            } catch (JSONException | IOException e) {
              Utility.printLog("userHistory : Catch :" + e.getMessage());
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
            Utility.printLog("userHistory : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void delayOrder(Reasons reason, String selectedDelay) {
    updateOrderStatus(DELAY_STATUS, reason.getReasons());
  }

  /**
   * <h>getCancellationReason</h>
   * <p>get the cancellation reasons for the order</p>
   */
  public void getCancellationReason() {
    if (view != null) {
      view.showProgress();
    }
    final Observable<Response<ResponseBody>> cancellationReasons =
        networkService.cancellationReasons(
            preferenceHelperDataSource.getLanguage(),
            preferenceHelperDataSource.getToken()
        );
    cancellationReasons.observeOn(AndroidSchedulers.mainThread())
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
            JSONObject jsonObject;
            switch (value.code()) {
              case SUCCESS:
                try {
                  jsonObject = new JSONObject(value.body().string());
                  Gson gson = new Gson();
                  CancelReasons cancelReasons = gson.fromJson(
                      jsonObject.getJSONObject(DATA).toString(),
                      CancelReasons.class);
                  view.setReasons(cancelReasons.getReasons());
                } catch (JSONException | IOException e) {
                  e.printStackTrace();
                }
                break;
              default:
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void cancelOrder(Reasons reason) {
    networkService.cancelOrder(
        preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getToken(), DEF_LAT, DEF_LAN,
        IP_ADDRESS, mOrderDetails.getData().getId(), reason.getReasons()
    ).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("UpdateOrder" + value.code());
            if (view != null) {
              view.hideProgress();
            }
            switch (value.code()) {
              case SUCCESS_RESPONSE:
                assert view != null;
                view.finishActivity();
                break;
              default:
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("UpdateOrder : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            Utility.printLog("UpdateOrder" + "complete");
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <h>getDueTime</h>
   * <p>get the due time of the order</p>
   *
   * @return return due time of the order
   */
  public String getDueTime() {
    return mDueTime;
  }

  /**
   * <h>getTimeStamp</h>
   * <p>get the time stamp of the order</p>
   *
   * @return return timestamp of the order
   */
  public String getTimestamp() {
    return mTimeStamp;
  }

  @Override
  public void updateOrder(int status, String time) {
    updateOrderStatus(status, time);
  }

  @Override
  public void dispatch() {
    if (mOrderDetails != null) {
      dispatchOrder(mOrderDetails.getData().getId());
    }
  }

  @Override
  public void editItems() {
  }

  @Override
  public void manualDispatch() {
    view.moveToListAct(mOrderDetails.getData().getId());
  }

  @Override
  public void callDriver() {
        /*if (mOrderDetails.getDriverDetails() != null) {
            utility.makePhoneCall(
                    mOrderDetails.getDriverDetails().getCountryCode() +
                            mOrderDetails.getDriverDetails().getMobile(),
                    context);
        }*/
  }

  @Override
  public void callCustomer() {
        /*if (mOrderDetails.getCustomerDetails() != null) {
            utility.makePhoneCall(
                    mOrderDetails.getCustomerDetails().getCountryCode() +
                            mOrderDetails.getCustomerDetails().getMobile(),
                    context);
        }*/
  }

  @Override
  public void cancelOrDelayOrder(boolean delay) {
    view.showCancelOrDelayDailog(delay);
  }

  @Override
  public void updateItem(int quantity, float unitPrice, String unitId) {
        /*for (int i = 0; i < mOrderDetails.getItems().size(); i++) {
            Items items = mOrderDetails.getItems().get(i);
            if (items.getUnitId().equals(unitId)) {
                items.setQuantity(quantity);
                items.setUnitPrice(unitPrice);
                items.setFinalPrice(unitPrice + "");
            }
        }

        view.addItems(
                mOrderDetails.getItems(),
                mOrderDetails.getCurrencySymbol(),
                mOrderDetails.getStoreType());*/
  }

  /**
   * <h>updateOrderStatus</h>
   * <p>update the order status whenever there is a change</p>
   *
   * @param status new status
   * @param time   preparation time for food item
   */
  private void updateOrderStatus(int status, String time) {
    if (mOrderDetails != null) {
      if (view != null) {
        view.showProgress();
      }
      OrderUpdateStatus orderUpdateStatus = new OrderUpdateStatus();
      String orderId =
          preferenceHelperDataSource.isCityLogin() ? mOrderDetails.getData().getMasterOrderId()
              : mOrderDetails.getData().getStoreOrderId();
      orderUpdateStatus.setOrderId(orderId);
      orderUpdateStatus.setType(
          preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
      if (preferenceHelperDataSource.getStoreType() == RESTAURENT) {
        orderUpdateStatus.setPreparationTime(time);
      }
      networkService.updateOrderNew(
          preferenceHelperDataSource.getToken(),
          preferenceHelperDataSource.getLanguage(),
          ANDROID_PLATFORM,
          EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
          mOrderDetails.getData().getAccounting().getCurrencyCode(),
          orderUpdateStatus
      ).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              Utility.printLog("UpdateOrder" + value.code());
              if (view != null) {
                view.hideProgress();
              }
              switch (value.code()) {
                case SUCCESS:
                  assert view != null;
                  view.finishActivity();
                  break;
                default:
                  try {
                    Utility.printLog("UpdateOrder" + value.errorBody().string());
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  break;
              }
            }

            @Override
            public void onError(Throwable e) {
              if (view != null) {
                view.hideProgress();
                view.finishActivity();
              }
              Utility.printLog("UpdateOrder : onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
              Utility.printLog("UpdateOrder" + "complete");
              if (view != null) {
                view.hideProgress();
              }
            }
          });
    }
  }

  /**
   * <p>update the order status whenever there is a change</p>
   *
   * @param driverId new status
   */
  @Override
  public void readyForPickUp(String driverId) {
    if (mOrderDetails != null) {
      if (view != null) {
        view.showProgress();
      }
      ReadyForPickUpStatus readyForPickUpStatus = new ReadyForPickUpStatus();
      if (preferenceHelperDataSource.getStoreType() == RESTAURENT) {
        readyForPickUpStatus.setPackageId(String.valueOf(ZERO));
        readyForPickUpStatus.setOrderId(mOrderDetails.getData().getStoreOrderId());
      } else {
        readyForPickUpStatus.setPackageId(
            mOrderDetails.getData().getProducts().get(ZERO).getPackageId());
      }
      readyForPickUpStatus.setAutoMode(!driverId.isEmpty() ? FALSE : TRUE);
      readyForPickUpStatus.setDriverId(driverId);
      networkService.readyForPickUp(
          ((ApplicationManager) context.getApplicationContext()).getAuthToken(
              preferenceHelperDataSource.getMyEmail()),
          preferenceHelperDataSource.getLanguage(),
          ANDROID_PLATFORM,
          EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
          mOrderDetails.getData().getAccounting().getCurrencyCode(),
          readyForPickUpStatus).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              Utility.printLog("UpdateOrder" + value.code());
              if (view != null) {
                view.hideProgress();
              }
              switch (value.code()) {
                case SUCCESS:
                  assert view != null;
                  view.finishActivity();
                  break;
                default:
                  try {
                    Utility.printLog("UpdateOrder" + value.errorBody().string());
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  break;
              }
            }

            @Override
            public void onError(Throwable e) {
              if (view != null) {
                view.hideProgress();
                view.finishActivity();
              }
              Utility.printLog("UpdateOrder : onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
              Utility.printLog("UpdateOrder" + "complete");
              if (view != null) {
                view.hideProgress();
              }
            }
          });
    }
  }

  /**
   * <p>cancel driver api.</p>
   */
  @Override
  public void cancelDispatch() {
    if (mOrderDetails != null) {
      if (view != null) {
        view.showProgress();
      }
      GenerateLabelRequest generateLabelRequest = new GenerateLabelRequest();
      generateLabelRequest.setOrderId(
          mOrderDetails.getData().getProducts().get(ZERO).getPackageId());
      networkService.unAssignJob(
          preferenceHelperDataSource.getToken(),
          preferenceHelperDataSource.getLanguage(),
          ANDROID_PLATFORM,
          EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
          mOrderDetails.getData().getAccounting().getCurrencyCode(),
          generateLabelRequest).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              Utility.printLog("UpdateOrder" + value.code());
              if (view != null) {
                view.hideProgress();
              }
              switch (value.code()) {
                case SUCCESS:
                  assert view != null;
                  view.finishActivity();
                  break;
                default:
                  try {
                    Utility.printLog("UpdateOrder" + value.errorBody().string());
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  break;
              }
            }

            @Override
            public void onError(Throwable e) {
              if (view != null) {
                view.hideProgress();
                view.finishActivity();
              }
              Utility.printLog("UpdateOrder : onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
              Utility.printLog("UpdateOrder" + "complete");
              if (view != null) {
                view.hideProgress();
              }
            }
          });
    }
  }

  @Override
  public void rejectOrder(String orderId, String reason, String type) {
    if (mOrderDetails != null) {
      if (view != null) {
        view.showProgress();
      }
      CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
      cancelOrderRequest.setType(type);
      cancelOrderRequest.setReason(reason);
      cancelOrderRequest.setOrderId(orderId);
      cancelOrderRequest.setComment("");
      networkService.cancelOrder(
          preferenceHelperDataSource.getToken(),
          preferenceHelperDataSource.getLanguage(),
          ANDROID_PLATFORM,
          EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
          mOrderDetails.getData().getAccounting().getCurrencyCode(),
          cancelOrderRequest).observeOn(AndroidSchedulers.mainThread())
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
              switch (value.code()) {
                case SUCCESS:
                  Utility.printLog("UpdateOrder" + value.code());
                  if (view != null) {
                    if (type.equals(PRODUCT_ORDER_TYPE)) {
                      view.refreshAPi();
                    } else {
                      view.finishActivity();
                    }
                  }
                  break;
                default:
                  try {
                    Utility.printLog("UpdateOrder" + value.errorBody().string());
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  break;
              }
            }

            @Override
            public void onError(Throwable e) {
              if (view != null) {
                view.hideProgress();
                view.finishActivity();
              }
              Utility.printLog("UpdateOrder : onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
              Utility.printLog("UpdateOrder" + "complete");
              if (view != null) {
                view.hideProgress();
              }
            }
          });
    }
  }

  @Override
  public boolean isCityLogin() {
    return preferenceHelperDataSource.isCityLogin();
  }

  @Override
  public void applyPack(ArrayList<ProductOrdersRequest> productOrdersRequests) {
    if (view != null) {
      view.showProgress();
    }
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
            if (view != null) {
              view.hideProgress();
            }
            try {
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                view.finishActivity();
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
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
            if (view != null) {
              view.hideProgress();
            }
            Utility.printLog("userHistory : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <h>dispatchOrder</h>
   * <p>dispatch the order</p>
   *
   * @param orderID order id
   */
  private void dispatchOrder(String orderID) {
    if (view != null) {
      view.showLoader();
    }
    final Observable<Response<ResponseBody>> dispatchOrder = networkService.dispatchOrder(
        preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getToken(),
        String.format("%d", System.currentTimeMillis()),
        orderID
    );
    dispatchOrder.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (view != null) {
              view.hideLoader();
            }
            switch (value.code()) {
              case SUCCESS:
                assert view != null;
                view.finishActivity();
                break;
              default:
                assert view != null;
                view.finishActivity();
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
            Utility.printLog("dispatchOrder : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <p>cancel driver api.</p>
   */
  @Override
  public void pickApi(String productOrderId, int quantity, String productImage) {
    if (view != null) {
      view.showProgress();
    }
    ProductOrderInput productOrderInput = new ProductOrderInput();
    productOrderInput.setOrderId(productOrderId);
    productOrderInput.setQuantity(quantity);
    OrderPickInput orderPickInput = new OrderPickInput();
    orderPickInput.setProductOrderInput(productOrderInput);
    orderPickInput.setLatitude(preferenceHelperDataSource.getCurrentLat());
    orderPickInput.setLongitude(preferenceHelperDataSource.getCurrentLong());
    orderPickInput.setProductImage(productImage);
    networkService.orderPick(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
        mOrderDetails.getData().getAccounting().getCurrencyCode(),
        orderPickInput).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("UpdateOrder" + value.code());
            if (view != null) {
              view.hideProgress();
            }
            switch (value.code()) {
              case SUCCESS:
                assert view != null;
                view.refreshAPi();
                break;
              default:
                try {
                  Utility.printLog("UpdateOrder" + value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("UpdateOrder : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            Utility.printLog("UpdateOrder" + "complete");
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void confirmOrder(String productOrderId, int quantity) {
    if (view != null) {
      view.showProgress();
    }
    ConfirmOrderInput confirmOrderInput = new ConfirmOrderInput();
    confirmOrderInput.setOrderId(productOrderId);
    confirmOrderInput.setType(
        preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
    confirmOrderInput.setLatitude(preferenceHelperDataSource.getCurrentLat());
    confirmOrderInput.setLongitude(preferenceHelperDataSource.getCurrentLong());
    networkService.orderConfirm(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
        mOrderDetails.getData().getAccounting().getCurrencyCode(),
        confirmOrderInput).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("UpdateOrder" + value.code());
            if (view != null) {
              view.hideProgress();
            }
            switch (value.code()) {
              case SUCCESS:
                assert view != null;
                view.orderConfirm();
                break;
              default:
                try {
                  Utility.printLog("UpdateOrder" + value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("UpdateOrder : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            Utility.printLog("UpdateOrder" + "complete");
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void confirmUnAvailability(Products mItems, int quantity, String reason) {
    if (view != null) {
      view.showProgress();
    }
    UnavailableItemRequest itemUnavailableItemRequest = new UnavailableItemRequest();
    itemUnavailableItemRequest.setQuantity(quantity);
    itemUnavailableItemRequest.setCentralProductId(mItems.getCentralProductId());
    itemUnavailableItemRequest.setProductId(mItems.getProductId());
    itemUnavailableItemRequest.setUnitId("");
    UnavailableItemRequest newItemUnavailableItemRequest = new UnavailableItemRequest();
    newItemUnavailableItemRequest.setQuantity(
        Integer.parseInt(mItems.getQuantity().getValue()) - quantity);
    newItemUnavailableItemRequest.setCentralProductId(mItems.getCentralProductId());
    newItemUnavailableItemRequest.setProductId(mItems.getProductId());
    newItemUnavailableItemRequest.setUnitId("");
    UnavailableOrderRequest unavailableOrderRequest = new UnavailableOrderRequest();
    unavailableOrderRequest.setOrderId(mItems.getProductOrderId());
    unavailableOrderRequest.setExtraNote(reason);
    unavailableOrderRequest.setUpdateType(ONE);
    unavailableOrderRequest.setIpAddress(Utility.getIpAddress(context));
    unavailableOrderRequest.setLatitude(String.valueOf(CURRENT_ZONE_LAT));
    unavailableOrderRequest.setLongitude(String.valueOf(CURRENT_ZONE_LONGI));
    unavailableOrderRequest.setNewItems(newItemUnavailableItemRequest);
    unavailableOrderRequest.setItems(itemUnavailableItemRequest);
    final Observable<Response<ResponseBody>> userHistory = networkService.setUnavailabilityOrder(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        unavailableOrderRequest);
    userHistory.observeOn(AndroidSchedulers.mainThread())
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
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                view.refreshAPi();
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
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
            if (view != null) {
              view.hideProgress();
            }
            Utility.printLog("userHistory : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void confirmationRequest(String orderId, String reason, String type) {
    if (mOrderDetails != null) {
      if (view != null) {
        view.showProgress();
      }
      CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
      cancelOrderRequest.setType(type);
      cancelOrderRequest.setReason(reason);
      cancelOrderRequest.setOrderId(orderId);
      networkService.confirmationRequest(
          preferenceHelperDataSource.getToken(),
          preferenceHelperDataSource.getLanguage(),
          ANDROID_PLATFORM,
          EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
          mOrderDetails.getData().getAccounting().getCurrencyCode(),
          cancelOrderRequest
      ).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              Utility.printLog("UpdateOrder" + value.code());
              if (view != null) {
                view.hideProgress();
              }
              switch (value.code()) {
                case SUCCESS:
                  view.finishActivity();
                  break;
                default:
                  try {
                    Utility.printLog("UpdateOrder" + value.errorBody().string());
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                  break;
              }
            }

            @Override
            public void onError(Throwable e) {
              if (view != null) {
                view.hideProgress();
                view.finishActivity();
              }
              Utility.printLog("UpdateOrder : onError :" + e.getMessage());
            }

            @Override
            public void onComplete() {
              Utility.printLog("UpdateOrder" + "complete");
              if (view != null) {
                view.hideProgress();
              }
            }
          });
    }
  }

  @Override
  public int getDriverType() {
    return preferenceHelperDataSource.getDriverType();
  }

  @Override
  public void orderPickedRequest(String orderId) {
    if (view != null) {
      view.showProgress();
    }
    OrderPickedInput orderPickedInput = new OrderPickedInput();
    orderPickedInput.setOrderId(orderId);
    orderPickedInput.setType(preferenceHelperDataSource.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
    networkService.orderPicked(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        EcomUtil.getCurrencySymbol(mOrderDetails.getData().getAccounting().getCurrencySymbol()),
        mOrderDetails.getData().getAccounting().getCurrencyCode(),
        orderPickedInput).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            Utility.printLog("UpdateOrder" + value.code());
            if (view != null) {
              view.hideProgress();
            }
            switch (value.code()) {
              case SUCCESS:
                assert view != null;
                view.refreshAPi();
                break;
              default:
                try {
                  Utility.printLog("UpdateOrder" + value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
              view.finishActivity();
            }
            Utility.printLog("UpdateOrder : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            Utility.printLog("UpdateOrder" + "complete");
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  /**
   * <h>saveItems</h>
   * <p>save items after editing</p>
   *
   * @param productsArrayList list of items in the order
   */
  public void saveItems(ArrayList<Products> productsArrayList) {
  }
}
