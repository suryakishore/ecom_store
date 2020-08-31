package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.CancelReasons;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.networking.DispatcherService;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.networking.OrderUpdateStatus;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DialogOrderDetailsPresenterImpl implements DialogOrderDetailsContract.PresenterOperations {

    @Inject
    Activity context;

    @Inject
    DialogOrderDetailsContract.ViewOperation view;

    @Inject
    Utility utility;

    @Inject
    NetworkService networkService;

    @Inject
    DispatcherService dispatcherService;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    NetworkStateHolder networkStateHolder;

    private OrderedItemDetails orderDetails;

    private String orderID;
    private String dueTime;
    private String timeStamp;

    private PastOrdersData pastOrdersData;


    @Inject
    DialogOrderDetailsPresenterImpl() {
    }

    @Override
    public void getBundleData(Bundle extras) {
        orderID = extras.getString("orderId");

        if (orderID != null) {
            getOrder(orderID);
        }

    }

    @Override
    public void getOrderDetails() {
        if (orderID != null) {
            getOrder(orderID);
        }
    }

    public String getCurrencySymbol()
    {
        return preferenceHelperDataSource.getCurrencySymbol();
    }

    @Override
    public void updateOrder(int status) {
        updateOrderStatus(status, null, null);
    }


//    status 2 - managerCancel, 3 - managerReject, 4 - managerAccept, 5 - orderReady, 6 - orderPicked, 7 - orderCompleted
    private void updateOrderStatus(int status, String reason, String dueDateTime) {
        /*Utility.printLog("UpdateOrder"+status+"  orderDetails.getOrderId()"+  orderDetails.getOrderId());

            if (orderDetails != null) {
                if (view != null) {
                    view.showProgress();
                }

                OrderUpdateStatus orderUpdateStatus = new OrderUpdateStatus();
                orderUpdateStatus.setOrderId(orderDetails.getData().getOrderId());
                orderUpdateStatus.setStatus(status);

                if (preferenceHelperDataSource.getLinkedWith().equals("1")) {
                    orderUpdateStatus.setType("masterOrder");
                } else {
                    orderUpdateStatus.setType("storeOrder");
                }

             networkService.updateOrderNew(
                     preferenceHelperDataSource.getToken(),
                     preferenceHelperDataSource.getLanguage(),
                     2,
                     orderDetails.getData().getOrderAccounting().getCurrencySymbol(),
                     orderDetails.getData().getOrderAccounting().getCurrencyCode(),
                     orderUpdateStatus
             ).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<Response<ResponseBody>>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Response<ResponseBody> value) {

                                Utility.printLog("UpdateOrder"+value.code());
                                if (view != null) {
                                    view.hideProgress();
                                }

                                try {
                                    JSONObject jsonObject;
                                    if (value.code() == 200) {
                                        String response=value.body().string();
                                        Utility.printLog("UpdateOrder"+response);
                                        jsonObject = new JSONObject(response);
                                        view.finishActivity();
//                                        Utility.printLog("updateOrder : " + jsonObject.toString());
                                    }
                                    else if (value.code()==500||value.code()==502){
                                        Toast.makeText(context, "Internal server error", Toast.LENGTH_LONG).show();
                                    }

                                    else {

                                        jsonObject = new JSONObject(value.errorBody().string());
//                                view.showError(jsonObject.getString("message"),value.code());
                                        Utility.printLog("UpdateOrder : " + jsonObject.toString());
                                    }


                                } catch (JSONException e) {
                                    Utility.printLog("UpdateOrder : Catch :" + e.getMessage());
                                } catch (IOException e) {
                                    Utility.printLog("UpdateOrder : Catch :" + e.getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (view != null) {
                                    view.hideProgress();
                                }
                                Utility.printLog("UpdateOrder : onError :" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Utility.printLog("UpdateOrder"+"complete");
                                if (view != null) {
                                    view.hideProgress();
                                }
                            }
                        });

            }
//        }*/
    }

    public String getDueTime() {
        return dueTime;
    }

    public String getTimestamp()
    {
        return timeStamp;
    }

    @Override
    public void dispatch() {
        if (orderDetails != null) {
            dispatchOrder(orderDetails.getOrderId());
        }
    }

    private void getOrder(String orderID) {

        if (view != null) {
            view.showProgress();
        }

        final Observable<Response<ResponseBody>> getOrder = networkService.getOrderDetails(
                preferenceHelperDataSource.getToken(),
                preferenceHelperDataSource.getLanguage(),
                2,
                "$",
                "INR",
                "storeOrder",
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
                            case 200:
                                JSONObject jsonObject;
                                try {
                                    String response = value.body().string();
                                    Utility.printLog("getOrderDetails : Code :" + response);
                                    jsonObject = new JSONObject(response);
                                    Gson gson = new Gson();
                                    orderDetails = gson.fromJson(jsonObject.getJSONObject("data").toString(), OrderedItemDetails.class);
                                    dueTime=orderDetails.getDueDatetime();
                                    timeStamp=orderDetails.getBookingDateTimeStamp();
                                    if (orderDetails != null) {
                                        view.setViews(orderDetails);
                                    }
                                } catch (JSONException e) {
                                    Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                                } catch (IOException e) {
                                    Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                                }
                                break;

                            default:
                                try {
                                    view.showMessage(value.errorBody().string());
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


    private void dispatchOrder(String orderID) {

        if (view != null) {
            view.showProgress();
        }

        final Observable<Response<ResponseBody>> dispatchOrder = networkService.dispatchOrder(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                System.currentTimeMillis() + "",
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

                        if (view != null)
                            view.hideProgress();


                        try {
                            JSONObject jsonObject;
                            if (value.code() == 200) {
                                jsonObject = new JSONObject(value.body().string());
                                view.finishActivity();
                                Utility.printLog("dispatchOrder : " + jsonObject.toString());
                            } else {

                                jsonObject = new JSONObject(value.errorBody().string());
//                                view.showError(jsonObject.getString("message"),value.code());
                                Utility.printLog("dispatchOrder : " + jsonObject.toString());
                            }


                        } catch (JSONException e) {
                            Utility.printLog("dispatchOrder : Catch :" + e.getMessage());
                        } catch (IOException e) {
                            Utility.printLog("dispatchOrder : Catch :" + e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null)
                            view.hideProgress();

                        Utility.printLog("dispatchOrder : onError :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (view != null)
                            view.hideProgress();

                    }
                });
    }

    @Override
    public void cancelOrDelayOrder(boolean delay) {
        view.showCancelOrDelayDailog(delay);
    }

    @Override
    public void getCancellationReason() {
        if (view != null) {
            view.showProgress();
        }

        final Observable<Response<ResponseBody>> cancellationReasons = networkService.cancellationReasons(
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

                        try {
                            JSONObject jsonObject;
                            if (value.code() == 200) {
                                jsonObject = new JSONObject(value.body().string());
                                Gson gson = new Gson();
                                CancelReasons cancelReasons = gson.fromJson(jsonObject.getJSONObject("data").toString(), CancelReasons.class);
                                view.setReasons(cancelReasons.getReasons());
                                Utility.printLog("cancellationReasons : " + jsonObject.toString());
                            } else {

                                jsonObject = new JSONObject(value.errorBody().string());
//                                view.showError(jsonObject.getString("message"),value.code());
                                Utility.printLog("cancellationReasons : " + jsonObject.toString());
                            }


                        } catch (JSONException e) {
                            Utility.printLog("cancellationReasons : Catch :" + e.getMessage());
                        } catch (IOException e) {
                            Utility.printLog("cancellationReasons : Catch :" + e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.hideProgress();
                        }
                        Utility.printLog("cancellationReasons : onError :" + e.getMessage());
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
        updateOrderStatus(2, reason.getReasons(), null);
    }

    @Override
    public void delayOrder(Reasons reason, String selectedDelay) {
        updateOrderStatus(18, reason.getReasons(), selectedDelay);
    }

    @Override
    public void manualDispatch() {
        view.moveToListAct(orderDetails.getOrderId());
    }

    @Override
    public void callDriver() {
        if (orderDetails.getDriverDetails() != null) {
            utility.makePhoneCall(orderDetails.getDriverDetails().getCountryCode() + orderDetails.getDriverDetails().getMobile(), context);
        }
    }

    @Override
    public void callCustomer() {
        if (orderDetails.getCustomerDetails() != null) {
            utility.makePhoneCall(orderDetails.getCustomerDetails().getCountryCode() + orderDetails.getCustomerDetails().getMobile(), context);
        }
    }

    @Override
    public void getPastOrders() {
        getUserHistory();
    }

    private void getUserHistory() {

        if (view != null) {
            view.showProgress();
        }

        Utility.printLog("CustmerId"+orderDetails.getCustomerDetails().getCustomerId());
        final Observable<Response<ResponseBody>> userHistory = networkService.getPastOrder(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                orderDetails.getCustomerDetails().getCustomerId(),
                0 + "");


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
                            if (value.code() == 200) {
                                String response = value.body().string();
                                jsonObject = new JSONObject(response);
                                Gson gson = new Gson();
                                pastOrdersData = gson.fromJson(jsonObject.getJSONObject("data").toString(), PastOrdersData.class);
                                view.showOrders(pastOrdersData);
                                Utility.printLog("userHistory : " + jsonObject.toString());
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
    public void getCurrentOrderDetails() {
        if (orderDetails != null) {
            view.setViews(orderDetails);
        }
    }

    @Override
    public void editItems() {
        if (orderDetails != null) {
            view.addItems(orderDetails.getItems(), orderDetails.getCurrencySymbol(), true);
        }
    }

    @Override
    public void updateItem(int quantity, float unitPrice, String unitId) {
        Utility.printLog("Update Item : " + quantity + " " + unitPrice + " " + unitId);

        for (int i = 0; i < orderDetails.getItems().size(); i++) {
            Items items = orderDetails.getItems().get(i);
            if (items.getUnitId().equals(unitId)) {
                items.setQuantity(quantity );
                items.setUnitPrice(unitPrice );
                items.setFinalPrice(unitPrice + "");
            }
        }

        view.addItems(orderDetails.getItems(), orderDetails.getCurrencySymbol(), true);
    }

    @Override
    public void getFares(float subTotal) {
        view.setFares(subTotal + "", orderDetails.getDiscount(), orderDetails.getDeliveryCharge(), "0", orderDetails.getTotalAmount(),orderDetails.getExclusiveTaxes());
    }

    @Override
    public void saveItems(ArrayList<Items> itemsArrayList) {
        Log.d("exe","itemsArrayList"+itemsArrayList);
       /* if (itemsArrayList!=null&&itemsArrayList.size()>0)
           orderDetails.setItems(itemsArrayList);*/
        view.addItems(orderDetails.getItems(), orderDetails.getCurrencySymbol(), false);

        Gson gson = new Gson();
        String items = gson.toJson(itemsArrayList);

        Log.d("exe","items"+items);

        Observable<Response<ResponseBody>> editOrder = dispatcherService.updateOrder(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                items,
                null,
                orderDetails.getOrderId(),
                orderDetails.getDeliveryCharge(),
                orderDetails.getStoreCoordinates().getLatitude(),
                orderDetails.getStoreCoordinates().getLongitude()/*,
                preferenceHelperDataSource.getStoreId(),
                preferenceHelperDataSource.getManagerID()*/);
        editOrder.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {

                        if (view != null)
                            view.hideProgress();

                        try {
                            JSONObject jsonObject;

                            if (value.code() == 200) {
                                jsonObject = new JSONObject(value.body().string());
                                Utility.printLog("editOrder : " + "if");

                            } else {

                                jsonObject = new JSONObject(value.errorBody().string());
                                Utility.printLog("editOrder : " + "else");
                                getOrderDetails();

                            }

                            Utility.printLog("editOrder : " + jsonObject.toString());

                        } catch (Exception e) {
                            Utility.printLog("editOrder : Catch :" + e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null)
                            view.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        if (view != null)
                            view.hideProgress();
                    }
                });
    }

    @Override
    public void editOrder(Items items) {

        view.openOrderEditDialog(items);
    }


    public void getDriverOngoingOrders()
    {

        if (view != null) {
            view.showProgress();
        }

        Utility.printLog("CustmerId"+orderDetails.getCustomerDetails().getCustomerId());
        final Observable<Response<ResponseBody>> userHistory = networkService.getDriverOnGoingOrders(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                orderDetails.getDriverDetails().getDriverId(),
                0 + "");


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
                            if (value.code() == 200) {
                                String response = value.body().string();
                                jsonObject = new JSONObject(response);
                                Gson gson = new Gson();
                                pastOrdersData = gson.fromJson(jsonObject.getJSONObject("data").toString(), PastOrdersData.class);
                                view.showOrders(pastOrdersData);
                                Utility.printLog("userHistory : " + jsonObject.toString());
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

}
