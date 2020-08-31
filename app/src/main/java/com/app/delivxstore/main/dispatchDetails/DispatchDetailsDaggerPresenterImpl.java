package com.app.delivxstore.main.dispatchDetails;

import android.app.Activity;
import android.content.Context;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.observers.RxLogOutObsever;
import com.app.delivxstore.observers.RxStoreObserver;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class DispatchDetailsDaggerPresenterImpl implements DispatchDetailsContract.DispatchDetailsPresenter {

    @Inject
    Utility utility;
    @Inject
    Activity activity;
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
    DispatchDetailsContract.DispatchDetailsView view;

    @Inject DispatchDetailsDaggerPresenterImpl() {}

    private OrderedItemDetails mOrderDetails;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }

    @Override
    public void getOrderDetails(String mOrderId) {
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
                mOrderId);
        getOrder.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {

                    @Override
                    public void onSubscribe(Disposable d) {}

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
                                    mOrderDetails = gson.fromJson(
                                            jsonObject.getJSONObject("data").toString(),
                                            OrderedItemDetails.class);
                                    if (mOrderDetails != null) {
                                        view.setViews(mOrderDetails);
                                    }
                                } catch (JSONException | IOException e) {
                                    Utility.printLog("getOrderDetails : Catch :" + e.getMessage());
                                }
                                break;
                            default:
                                try {
                                    String response =
                                            context.getString(R.string.order_response);
                                    Utility.printLog("getOrderDetails : Code :" + response);
                                    jsonObject = new JSONObject(response);
                                    Gson gson = new Gson();
                                    mOrderDetails = gson.fromJson(
                                            jsonObject.getJSONObject("data").toString(),
                                            OrderedItemDetails.class);
                                    if (mOrderDetails != null) {
                                        view.setViews(mOrderDetails);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.hideProgress();
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
}
