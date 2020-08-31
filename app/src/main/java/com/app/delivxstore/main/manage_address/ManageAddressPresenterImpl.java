package com.app.delivxstore.main.manage_address;

import android.app.Activity;
import android.util.Log;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;

import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

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

import static com.app.delivxstore.utility.VariableConstants.MANAGE_ADDRESS_ACT;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

public class ManageAddressPresenterImpl implements ManageAddressPresenter {

    @Inject
    Activity mActivity;

    @Inject
    ManageAddressView mView;


    @Inject
    NetworkService service;
    @Inject
    PreferenceHelperDataSource manager;

    @Inject
    @Named(MANAGE_ADDRESS_ACT)
    CompositeDisposable compositeDisposable;

    @Inject
    NetworkStateHolder networkStateHolder;

//    @Inject
//    CouchDbHelper couchDbHelper;

    @Inject
    ManageAddressPresenterImpl() {

    }

    @Override
    public void getAddressList(String userID) {

        if (isNetworkAvailable()) {
            mGetAddressApi(userID);
        } else {
            mView.onError(mActivity.getString(R.string.networkError));
        }


    }

    @Override
    public void deleteAddress(String id, int pos, double lat, double longi) {
        mDeleteAddressApi(id, pos, lat, longi);
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isNetworkAvailable() {
        return Utility.isNetworkAvailable(mActivity);
    }


    private void mGetAddressApi(String userID) {
        mView.showProgress();
        String token = manager.getToken();
        Observable<Response<ResponseBody>> bad = service.getAddressApi(token, manager.getLanguage(),userID);

        bad.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        String msg = "";
                        mView.hideProgress();
                        Utility.printLog("get address " + value.code());

                        try {
                            JSONObject object = new JSONObject(value.errorBody().string());
                            if (object.has("message")) {
                                msg = object.getString("message");
                            }

                        } catch (Exception e) {
                        }
                        switch (value.code()) {
                            // success
                            case 200:
                                try {
                                    //{"message":"Got The Details.","data":[]}
                                    String result = value.body().string();
                                    Log.d("murashid", "onNext: "+ result);
                                    Gson gson = new Gson();
                                    GetAddressResponse getAddressResponse = gson.fromJson(result, GetAddressResponse.class);
                                    if (getAddressResponse != null && getAddressResponse.getData() != null && getAddressResponse.getData().size() > 0) {
                                        mView.setAddressList(getAddressResponse.getData());
                                        storeAddressInDb(getAddressResponse.getData());
                                    } else {
                                        mView.noAddress();
                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                break;
/*
                            case 404:
                                SessionTokenExpObservable.getInstance().emit(true, msg);

                                break;

                            case 498:
                                SessionTokenExpObservable.getInstance().emit(true, msg);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false, msg);
                                break;*/
                            case 502:
                                mView.onError(mActivity.getString(R.string.serverError));
                                break;
                            default:
                                mView.onError(mActivity.getString(R.string.somethingWentWrong));
                                break;

                        }

                        stop();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }


    private void mDeleteAddressApi(String id, int position, double lat, double longi) {
        mView.showProgress();

        Observable<Response<ResponseBody>> bad = service.deleteAddressApi(manager.getToken(), manager.getLanguage(), id);

        bad.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        String msg = "";
                        mView.hideProgress();
                        Utility.printLog("delet address " + value.code());

                        try {
                            JSONObject object = new JSONObject(value.errorBody().string());
                            if (object.has("message")) {
                                msg = object.getString("message");
                            }

                        } catch (Exception e) {
                        }
                        switch (value.code()) {
                            // success
                            case 200:
//                                couchDbHelper.deleteAddress(lat, longi);
                                mView.removeAddressRow(position);
                                break;
/*
                            case 404:
                                SessionTokenExpObservable.getInstance().emit(true, msg);

                                break;

                            case 498:
                                SessionTokenExpObservable.getInstance().emit(true, msg);
                                break;

                            case 440:
                                SessionTokenExpObservable.getInstance().emit(false, msg);
                                break;*/

                            default:
                                mView.onError(mActivity.getString(R.string.somethingWentWrong));
                                break;

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }


    private void storeAddressInDb(ArrayList<DataInGetAddress> addressArrayList) {
        if (addressArrayList != null && addressArrayList.size() > 0) {
            for (int i = 0; i < addressArrayList.size(); i++) {
                double lat = Utility.roundValue(addressArrayList.get(i).getLatitude());
                double longi = Utility.roundValue(addressArrayList.get(i).getLongitude());

                /*if (!(couchDbHelper.isAddressAdded(addressArrayList.get(i).getLatitude(), addressArrayList.get(i).getLongitude()))) {
                    SendAddress address = new SendAddress();

                    address.setAddLine1(addressArrayList.get(i).getAddLine1());
                    address.setAddLine2(addressArrayList.get(i).getAddLine2());
                    address.setLatitude(addressArrayList.get(i).getLatitude());
                    address.setLongitude(addressArrayList.get(i).getLongitude());
                    address.setTaggedAs(addressArrayList.get(i).getTaggedAs());
                    address.setCity(addressArrayList.get(i).getCity());
                    address.setState(addressArrayList.get(i).getState());
                    address.setCountry(addressArrayList.get(i).getCountry());
                    address.setPincode(addressArrayList.get(i).getPincode());

                    couchDbHelper.addAddress(address);

                }*/
            }
        }
    }


}
