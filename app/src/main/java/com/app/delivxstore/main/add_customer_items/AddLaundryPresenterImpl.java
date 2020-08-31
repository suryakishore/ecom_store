package com.app.delivxstore.main.add_customer_items;

import android.annotation.SuppressLint;
import android.util.Log;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.manage_address.GetAddressResponse;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class AddLaundryPresenterImpl implements AddLaundryPresenter {

    @Inject
    AddLaundryPresenterImpl() {
    }

    @Inject
    NetworkService service;

    @Inject
    PreferenceHelperDataSource manager;

    @Inject
    AddLaundryItemView view;

    @Inject
    AddLaundryItemActivity mContext;

    private CompositeDisposable compositeDisposable;

    private Disposable disposable;


    @Override
    public void start() {

        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void stop() {
        view.stopAct();
    }

    @Override
    public boolean isNetworkAvailable() {
        return Utility.isNetworkAvailable(mContext);
    }


    @Override
    public void submitLaundryItem(String productId, String productName, double quantity, double storeType) {

        if (isNetworkAvailable()) {
            submitAnswer(productId, productName, quantity, storeType);
        } else
            view.onError(mContext.getResources().getString(R.string.internetIssue));

    }


    @Override
    public void removeLaundryItem(String customerId, String cartId, String productId, String unitID, String addToCartOn, double orderType, int position) {

        if (isNetworkAvailable()) {
            removeLaundryItemApi(customerId, cartId, productId, unitID, addToCartOn, orderType, position);
        } else {
            view.onError(mContext.getResources().getString(R.string.internetIssue));
        }
    }


    @Override
    public void upDateCart(int pos, String customerID, String cartId, String childProductId, String unitID, int quantity, int unitPrice) {

        if (isNetworkAvailable())
            updateCartApi(pos, customerID, cartId, childProductId, unitID, quantity, unitPrice);
        else
            view.onError(mContext.getResources().getString(R.string.internetIssue));

    }


    @Override
    public void startLogin() {
        view.startLoginAct();
    }

    @Override
    public void getCart(String customerID) {
        if (isNetworkAvailable())
            getLaundryCartItems(customerID);
        else
            view.onError(mContext.getResources().getString(R.string.internetIssue));
    }

    @Override
    public void checkOut(String customerId, int status, double type, double latitude, double longitude, double pickUpLat, double pickUpLong) {
        if (isNetworkAvailable())
            checkOutApi(customerId, status, type, latitude, longitude, pickUpLat, pickUpLong);
        else
            view.onError(mContext.getResources().getString(R.string.internetIssue));
    }

    private void checkOutApi(String customerId, int status, double type, double latitude, double longitude, double pickUpLat, double pickUpLong) {
           Log.d("exe","status"+status+"type"+type);

        view.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable =
            service.getFare(token, manager.getLanguage(), customerId, status, type, latitude,
                longitude, pickUpLat, pickUpLong);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //   compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        String message = "";
                        try {
                            Log.d("exe", "response" + "code" + responseBodyResponse.code());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 200:
                                Gson gson = new Gson();
                                try {

                                    result = responseBodyResponse.body().string();
                                    Log.d("exe", "getFare" + result);

                                    JSONObject jsonObject=new JSONObject(result);
                                    JSONArray jsonObjectArray=jsonObject.getJSONArray("data");
                                    JSONObject jsonObjectData=jsonObjectArray.getJSONObject(0);
                                    int tax=0,discount=0;
                                    if (jsonObjectData.has("tax")) {
                                         tax = jsonObjectData.getInt("tax");
                                        discount = jsonObjectData.getInt("discount");

                                    }
                                    view.getFare(discount,tax,jsonObjectData.getInt("storeDeliveryFee"),jsonObjectData.getString("currencySymbol"));

                                } catch (Exception e) {
                                    Log.d("exe", "IOException" + e.getMessage());

                                    e.printStackTrace();
                                }

                                break;
                            case 400:
                            case 404:

                                try {
                                    JSONObject jsonObject = new JSONObject(responseBodyResponse.errorBody().string());
                                    view.onError(jsonObject.getString("message"));
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                         /*   case 498:
                                SessionTokenExpObservable.getInstance().emit(true, message);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false, message);
                                break;
*/
                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });


    }


   /* private void updateQuantityItemApi(int position, String cartId, String productId, String quantity) {

        Log.d("exe", "cartId" + cartId + "productId" + productId);
        view.showProgress();
        String token =manager.getToken();

        Observable<Response<ResponseBody>> observable = service.updateQuantityItemApi(token, manager.getLanguage(), cartId, productId, quantity);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        String message = "";
                        try {
                            *//*assert responseBodyResponse.errorBody() != null;
                            JSONObject object = new JSONObject(responseBodyResponse.errorBody().string());
                            if (object.has("message")) {
                                message = object.getString("message");
                            }*//*

                            Log.d("exe", "response" + "code" + responseBodyResponse.code());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 203:
                                try {
                                    result = responseBodyResponse.body().string();
                                    JSONObject jsonObject = new JSONObject(result);
                                    view.onError(jsonObject.getString("message"));
                                    view.onSuccessUpdateLaundryItem(position, quantity);
                                    Log.d("exe", "updateQuantityItemApi" + result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;


                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }
*/

    private void updateCartApi(int pos, String customerID, String cartId, String childProductId, String unitID, int quantity, int unitPrice) {
        Log.d("exe", "cartId" + cartId + "childProductId" + childProductId);
        // compositeDisposable = new CompositeDisposable();
        view.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.updateCart(token, manager.getLanguage(), customerID, cartId, childProductId, unitID, quantity, unitPrice);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //  compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        String message = "";
                        try {

                            Log.d("exe", "response" + "code" + responseBodyResponse.code());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 203:
                                try {
                                    result = responseBodyResponse.body().string();
                                    JSONObject jsonObject = new JSONObject(result);
                                    view.updateOrder(pos, jsonObject.getString("message"), quantity);
                                    Log.d("exe", "updateCartApi" + result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;

                            case 400:
                                try {
                                    result = responseBodyResponse.errorBody().string();
                                    JSONObject jsonObject = new JSONObject(result);
                                    view.onError(jsonObject.getString("message"));
                                    Log.d("exe", "updateCartApi" + result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;


                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });


    }


    private void removeLaundryItemApi(String customerID, String cartId, String productId, String unitId, String adToCartOn, double orderType, int position) {

        //   compositeDisposable = new CompositeDisposable();
        view.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.deleteItemApi(token, manager.getLanguage(), customerID, cartId, productId, unitId, adToCartOn, orderType);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                       // compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        Log.d("exe", "code" + responseBodyResponse.code());
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 202:
                                try {
                                    result = responseBodyResponse.body().string();
                                    JSONObject jsonObject = new JSONObject(result);
                                    view.onSuccessRemoveLaundryItem(position, jsonObject.getString("message"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;


                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }


    private void submitAnswer(String customerId, String productName, double quantity, double orderType) {

        view.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.addToCart(token, manager.getLanguage(), customerId, quantity, productName, orderType, "", 0.0, 7, "");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //      compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        String message = "";
                        try {
                            /*assert responseBodyResponse.errorBody() != null;
                            JSONObject object = new JSONObject(responseBodyResponse.errorBody().string());
                            if (object.has("message")) {
                                message = object.getString("message");
                            }*/

                            Log.d("exe", "response" + "code" + responseBodyResponse.code());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 201:

                                try {

                                    result = responseBodyResponse.body().string();
                                    Log.d("exe", "submitAnswer " + result);

                                    JSONObject jsonObject = new JSONObject(result);
                                    JSONObject dataJsonObject = jsonObject.getJSONObject("data");
                                    String cartId = dataJsonObject.getString("cartId");
                                    //  String productId = dataJsonObject.getString("productId");
                                    //   view.onError(jsonObject.getString("message"));
                                    view.onSuccessItemSubmit(cartId, jsonObject.getString("message"));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;


                            case 400:

                                try {

                                    result = responseBodyResponse.errorBody().string();
                                    JSONObject jsonObject = new JSONObject(result);
                                    view.onError(jsonObject.getString("message"));
                                    Log.d("exe", "submitAnswer " + result);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;

                           /* case 498:
                                SessionTokenExpObservable.getInstance().emit(true, message);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false, message);
                                break;*/
                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });


    }

    private void getLaundryCartItems(String customerId) {

        //  compositeDisposable = new CompositeDisposable();
        view.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.getCartApi(token, manager.getLanguage(), customerId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //   compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        view.hideProgress();
                        String message = "";
                        try {

                            Log.d("exe", "response" + "code" + responseBodyResponse.code());
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        String result;
                        switch (responseBodyResponse.code()) {
                            case 200:
                                try {
                                    result = responseBodyResponse.body().string();
                                    Log.d("exe", "getCart" + result);
                                    Gson gson = new Gson();
                                    AddLaundryResponse addLaundryResponse = gson.fromJson(result, AddLaundryResponse.class);
                                    view.setLaundryItems(addLaundryResponse.getData().getCart(), addLaundryResponse.getData().getCartId());
                                } catch (Exception e) {
                                    Log.d("exe", "IOException" + e.getMessage());
                                    e.printStackTrace();
                                }

                                break;

                         /*   case 498:
                                SessionTokenExpObservable.getInstance().emit(true, message);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false, message);
                                break;
*/
                            case 500:
                            case 502:
                                view.onError(mContext.getResources().getString(R.string.serverError));
                                break;

                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                });


    }


}
