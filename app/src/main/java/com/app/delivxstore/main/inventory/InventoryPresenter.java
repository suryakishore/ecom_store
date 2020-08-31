package com.app.delivxstore.main.inventory;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.inventory.model.CategoryProduct;
import com.app.delivxstore.main.inventory.model.InventoryPojo;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class InventoryPresenter implements InventoryContract.Presenter {

    private static final String TAG = "Mura";

    InventoryContract.View view;

    @Inject
    Utility utility;

    @Inject
    Activity activity;

    @Inject
    Context context;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    NetworkService networkService;

    @Inject
    NetworkStateHolder networkStateHolder;

    @Inject
    public InventoryPresenter() {

    }

    @Override
    public void attachView(Object view) {
        this.view = (InventoryContract.View) view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getInventory() {

        if (view != null)
            view.showProgress();

        final Observable<Response<ResponseBody>> getInventory = networkService.getInventary(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                preferenceHelperDataSource.getStoreId());

        getInventory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        if (view != null) {
                            Utility.printLog("MyOrderResponse : " + value.code());

                            view.hideProgress();
                            try {
                                JSONObject jsonObject;
                                switch (value.code()) {
                                    case 200:

                                        try {

                                            String response = value.body().string();
                                            Utility.printLog("MyOrderResponse : " + response);
                                            jsonObject = new JSONObject(response);
                                            Gson gson = new Gson();
                                            InventoryPojo inventoryPojo = gson.fromJson(response, InventoryPojo.class);
                                            //  ArrayList<InventoryData> inventoryDatas = inventoryPojo.getData();
                                            ArrayList<CategoryProduct> categoryProducts = new ArrayList<>();
                                            ArrayList<String> categoryNames = new ArrayList<>();
                                          /*  for (InventoryData inventoryData : inventoryDatas)
                                            {
                                                categoryNames.add(inventoryData.get_id().getCategoryName());
                                                categoryProducts.addAll(inventoryData.getCategoryProducts());
                                            }*/
                                            view.setInvenatary(inventoryPojo.getData().getProducts());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;

                                    default:
                                        jsonObject = new JSONObject(value.errorBody().string());
                                        Utility.printLog("LoginResponse : " + jsonObject.toString());
                                        view.showMessage(jsonObject.getString("message"));
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Mura Tst", "onError: " + e);
                        if (view != null) {
                            view.hideProgress();
                            view.showMessage(activity.getString(R.string.networkError));
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null)
                            view.hideProgress();
                    }
                });

    }


    @Override
    public void updateInventory(HashMap<String, Integer> selectedProductsWithStatus) {

        if (view != null)
            view.showProgress();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectRequest = new JSONObject();
        try {
            Iterator it = selectedProductsWithStatus.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", pair.getKey());
                jsonObject.put("status", pair.getValue());
                jsonArray.put(jsonObject);
                it.remove(); // avoids a ConcurrentModificationException
            }
            jsonObjectRequest.put("products", jsonArray);
            Log.d(TAG, "updateInventory: " + jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Observable<Response<ResponseBody>> getInventory = networkService.updateInventory(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                jsonObjectRequest.toString());

        getInventory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {

                        if (view != null) {
                            view.hideProgress();
                            try {
                                JSONObject jsonObject;
                                Utility.printLog("Response : " + value.code());
                                if (value.code() == 200) {
                                    String response = value.body().string();
                                    Utility.printLog("Response : " + response);
                                    jsonObject = new JSONObject(response);
                                    view.showMessage(jsonObject.getString("message"));
                                } else {
                                    jsonObject = new JSONObject(value.errorBody().string());
                                    Utility.printLog("Response : " + jsonObject.toString());
                                    view.showMessage(jsonObject.getString("message"));
                                }
                            } catch (Exception e) {
                                view.showMessage(activity.getString(R.string.networkError));
                                Utility.printLog("Response : Catch :" + e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Mura Tst", "onError: " + e);
                        if (view != null) {
                            view.hideProgress();
                            view.showMessage(activity.getString(R.string.networkError));
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
    public void setInventory(String productId, final int status, final int position){

        if (view != null)
            view.showProgress();

        final Observable<Response<ResponseBody>> getInventory = networkService.getInventaryStatus(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                productId,status);

        getInventory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        if (view != null) {
                            Utility.printLog("MyInventaryResponse : " + value.code());
                            view.hideProgress();
                            try {
                                JSONObject jsonObject;
                                switch (value.code()) {
                                    case 200:
                                        try {
                                            String response = value.body().string();
                                            Utility.printLog("invenary : " + response);
                                            jsonObject = new JSONObject(response);
                                            view.changeInventary(position,status);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;

                                    default:
                                        jsonObject = new JSONObject(value.errorBody().string());
                                        Utility.printLog("LoginResponse : " + jsonObject.toString());
                                        view.showMessage(jsonObject.getString("message"));
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Mura Tst", "onError: " + e);
                        if (view != null) {
                            view.hideProgress();
                            view.showMessage(activity.getString(R.string.networkError));
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null)
                            view.hideProgress();
                    }
                });





    }




}
