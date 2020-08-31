package com.app.delivxstore.main.mobileView.pastOrders;

import android.app.Activity;
import android.content.Context;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
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

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class PastOrdersPresenterImpl implements PastOrdersContract.PastOrdersPresenter{




    @Inject
    PastOrdersContract.PastOrdersView view;

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

    @Inject
    Activity activity;



    private String orderID;
    private String dueTime;
    private String timeStamp;
    private PastOrdersData pastOrdersData;


    @Inject
    PastOrdersPresenterImpl() {
    }


    @Override
    public void getUserHistory(String customerId) {

        if (!Utility.isNetworkConnected(activity)){
            view.onError(context.getResources().getString(R.string.networkError));

            return;

        }

        if (view != null)
            view.showProgress();


        final Observable<Response<ResponseBody>> userHistory = networkService.getPastOrder(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(),
                customerId,
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
                                view.setPastOrders(pastOrdersData);

//                                orderData = gson.fromJson(jsonObject.getJSONObject("data").toString(), HistoryModel.class);
//                                view.showOrders(orderData.getPastOrders());
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
    public void closeAct() {
        view.finishActivity();
    }
}
