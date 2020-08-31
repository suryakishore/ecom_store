package com.app.delivxstore.main.payment_choose_card;

import android.app.Activity;
import android.util.Log;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.app.delivxstore.utility.VariableConstants.CHOOSEPAYMENT_ACT;


/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

public class ChoosePaymentPresenterImpl implements ChoosePaymentPresenter {

    @Inject
    ChoosePaymentView mView;
    @Inject
    Activity mActivity;

    @Inject
    NetworkService service;
    @Inject
    NetworkStateHolder networkStateHolder;
    @Inject
    @Named(CHOOSEPAYMENT_ACT)
    CompositeDisposable compositeDisposable;
    @Inject
    PreferenceHelperDataSource manager;

    @Inject
    public ChoosePaymentPresenterImpl() {
    }

    @Override
    public void getCards(String customerId) {
        if (isNetworkAvailable()) {
            // mView.setWalletAmount(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);
            // mGetCard(customerId);
            //   mGetWalletApi();

        } else {
            mView.onError(mActivity.getString(R.string.networkError));
        }
    }

    @Override
    public void showCashBtn() {
        mView.showCashBtn();
    }

    @Override
    public void hideCashBtn() {
        mView.hideCashBtn();

    }

    @Override
    public void addNewCard() {

        mView.startAddCardAct();

    }

    @Override
    public String getLanguage() {
        return manager.getLanguage();
    }

    @Override
    public void showWalletPayment() {
        mView.showWalletPayment();
    }

    @Override
    public void payWallet() {

       /* if(AppConstants.WALLET_AMOUNT!=null && !AppConstants.WALLET_AMOUNT.equals("0.00") )
      {
          mView.payWallet(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);

      }else
      {
          mView.onError(mActivity.getString(R.string.insufficientBal));
      }*/
    }

    @Override
    public void addMoneyWallet() {
        mView.addWallet();
    }

    @Override
    public void placeOrder(String customerID, double paymentType, String couponCode, double discount, String cartId, double latitude, double longitude,
                           double pickUpLat, double pickUpLong, String bookingDate, int serviceType, int bookingType, String extraNote, String dueDatetime, double estimatedPackageValue,String addressId,String address1,String address2) {
        if (isNetworkAvailable()) {
            placeOrderApi(customerID, paymentType, couponCode, discount, cartId, latitude, longitude, pickUpLat, pickUpLong, bookingDate, serviceType, bookingType, extraNote, dueDatetime, estimatedPackageValue,addressId,address1,address2);
        } else {
            mView.onError(mActivity.getString(R.string.networkError));
        }
    }

    private void placeOrderApi(String customerID, double paymentType, String couponCode, double discount, String cartId, double latitude, double longitude, double pickUpLat, double pickUpLong, String bookingDate, int serviceType, int bookingType, String extraNote, String dueDatetime, double estimatedPackageValue, String addressId, String address1, String address2) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("1", extraNote);
            Log.d("exe", "jsonObject" + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mView.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.placeOrder(token, manager.getLanguage(), customerID, paymentType, couponCode, discount, cartId, latitude, longitude, pickUpLat, pickUpLong, bookingDate, serviceType, bookingType, jsonObject, dueDatetime, estimatedPackageValue,7,"Favour",addressId,address1,address2);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {

                        mView.hideProgress();
                        Utility.printLog("get placeOrder " + responseBodyResponse.code());

                        switch (responseBodyResponse.code()) {
                            case 200:
                                try {
                                    String result = responseBodyResponse.body().string();
                                    Log.d("exe", "result" + result);
                                    JSONObject object = new JSONObject(result);
                                    mView.onSucess();

                                } catch (JSONException | NullPointerException | IOException e) {
                                    e.printStackTrace();
                                }


                                break;
                            case 400:
                                try {
                                    String result = responseBodyResponse.errorBody().string();
                                    Log.d("exe", "result" + result);
                                    JSONObject object = new JSONObject(result);
                                    mView.onError(object.getString("message"));

                                } catch (JSONException | NullPointerException | IOException e) {
                                    e.printStackTrace();
                                }


                                break;
                          /*  case 498:
                                SessionTokenExpObservable.getInstance().emit(true,message);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false,message);
                                break;*/
                            case 500:
                            case 502:
                                mView.onError(mActivity.getString(R.string.serverError));
                                break;
                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isNetworkAvailable() {
        return Utility.isNetworkConnected(mActivity);
    }

    private void mGetCard(String customerId) {
        Log.d("exe", "customerId" + customerId);
        mView.showProgress();
        String token = manager.getToken();

        Observable<Response<ResponseBody>> observable = service.getCard(token, manager.getLanguage(), customerId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {

                        mView.hideProgress();
                        Utility.printLog("get card " + responseBodyResponse.code());
                        String message = "";
                        try {
                            JSONObject object = new JSONObject(responseBodyResponse.errorBody().string());
                            if (object.has("message")) {
                                message = object.getString("message");
                            }

                        } catch (JSONException | NullPointerException | IOException e) {

                        }
                        switch (responseBodyResponse.code()) {
                            case 200:
                                try {
                                    String result = responseBodyResponse.body().string();
                                    Log.d("exe", "result" + result);
                                    JSONObject object = new JSONObject(result);

                                    if (object.has("data") && object.get("data") != null) {
                                        Gson gson = new Gson();
                                        PaymentResponse paymentResponse = gson.fromJson(result, PaymentResponse.class);
                                        mView.setPaymentCardsList(paymentResponse.getData());
                                    }

                                } catch (JSONException | NullPointerException | IOException e) {
                                    e.printStackTrace();
                                }


                                break;

                          /*  case 498:
                                SessionTokenExpObservable.getInstance().emit(true,message);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false,message);
                                break;*/
                            case 500:
                            case 502:
                                mView.onError(mActivity.getString(R.string.serverError));
                                break;
                            default:
                                break;

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


  /*  private void mGetWalletApi()
    {
        if(isNetworkAvailable())
        {

            Observable<Response<ResponseBody>> bad=service.getWalletApi(((ApplicationManager) mActivity.getApplication()).getAuthToken(manager.getCustomerId()),manager.getLanguage());

            bad.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }
                        @Override
                        public void onNext(Response<ResponseBody> value)
                        {
                            String message="";
                            try
                            {
                                JSONObject object=new JSONObject(value.errorBody().string());
                                if(object.has("message"))
                                {
                                    message=object.getString("message");
                                }

                            }catch (JSONException |NullPointerException|IOException e)
                            {}
                            switch (value.code())
                            {


                                case 200:
                                    JSONObject object=null;
                                    try
                                    {
                                        String result=value.body().string();
                                         object=new JSONObject(result);
                                        if(object.has("data")&& object.getJSONObject("data")!=null)
                                        {
                                            AppConstants.WALLET_AVAILABLE=true;

                                            int walletBalance= (int) object.getJSONObject("data").opt("walletBalance");
                                            AppConstants.WALLET_AMOUNT=Utility.currencyFormat(walletBalance+"");
                                            mView.setWalletAmount(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);
                                        }

                                    } catch (ClassCastException e)
                                    {
                                        try {
                                            if(object!= null && object.has("data")&& object.getJSONObject("data")!=null)
                                            {
                                                AppConstants.WALLET_AVAILABLE=true;

                                                double walletBalance= (double) object.getJSONObject("data").opt("walletBalance");
                                                AppConstants.WALLET_AMOUNT=Utility.currencyFormat(walletBalance+"");
                                                mView.setWalletAmount(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);

                                            }
                                        } catch (JSONException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    catch (Exception  e)
                                    {
                                        AppConstants.WALLET_AMOUNT="0.00";
                                        mView.setWalletAmount(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);
                                        e.printStackTrace();
                                    }

                                    break;

                                case 404:
                                    AppConstants.WALLET_AVAILABLE=true;
                                    AppConstants.WALLET_AMOUNT="0.00";
                                    mView.setWalletAmount(manager.getCurrencySymbol()+" "+AppConstants.WALLET_AMOUNT);

                                    break;



                                case 498:
                                    SessionTokenExpObservable.getInstance().emit(true,message);
                                    break;

                                case 440:
                                    SessionTokenExpObservable.getInstance().emit(false,message);
                                    break;


                                default:
                                    break;
                            }

                        }
                        @Override
                        public void onError(Throwable e)
                        {
                            e.printStackTrace();
                        }
                        @Override
                        public void onComplete() {

                        }
                    });
        }*/

    //  }
}
