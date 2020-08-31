package com.app.delivxstore.main.searchCustomer;

import android.app.Activity;
import android.util.Log;

import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.model.GetOrderResponse;
import com.app.delivxstore.main.inventory.model.InventoryPojo;
import com.app.delivxstore.main.orderFrom.OrderFromContract;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.RXOrderResponseObserver;
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

public class SearchCustomerPresenter implements SearchCustomerContract.PresenterOperations {

    @Inject
    NetworkService networkService;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    @Inject
    Utility utility;

    @Inject
    Activity context;

    @Inject
    SearchCustomerContract.ViewOperations view;

    @Inject
    SearchCustomerPresenter() {

    }

    @Override
    public void searchCustomer() {
        view.searchCustomer();
    }

    @Override
    public void getCustomers(String hint) {


        if (view != null) {
            view.showProgress();
        }

        final Observable<Response<ResponseBody>> getCustomers = networkService.getCustomers(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                preferenceHelperDataSource.getStoreId(),
                hint);

             Log.d("exe","token"+ preferenceHelperDataSource.getToken());
        Log.d("exe","storeiD"+  preferenceHelperDataSource.getStoreId());

        getCustomers.observeOn(AndroidSchedulers.mainThread())
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
                        Log.d("exe","value.code()"+value.code());

                        switch (value.code()) {
                            case 200:
                                try {
                                    String response = value.body().string();
                                    Gson gson = new Gson();
                                    SearchCustomerPojo searchCustomerPojo = gson.fromJson(response, SearchCustomerPojo.class);
                                    view.setCustomers(searchCustomerPojo.getData());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 404:
                                 view.showCustomerDet(hint);
                                break;

                            case 500:
                            case 502:
                                view.showError("Internal server error");
                                break;
                            case 498:
                                break;
                            default:
                                try {
                                    view.showError(value.errorBody().string());
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
                        }
                        view.showError("Sorry" + e.getMessage());
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
