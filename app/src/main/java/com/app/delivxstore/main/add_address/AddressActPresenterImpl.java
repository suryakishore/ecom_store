package com.app.delivxstore.main.add_address;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import androidx.core.app.ActivityCompat;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.location.AddressProviderFromLocation;
import com.app.delivxstore.location.LocationCallBack;
import com.app.delivxstore.location.LocationProvider;
import com.app.delivxstore.location.PlaceProvider;
import com.app.delivxstore.location.RxAddressObserver;
import com.app.delivxstore.location.RxLocationObserver;
import com.app.delivxstore.location.RxPlaceSearchObserver;
import com.app.delivxstore.main.manage_address.DataInGetAddress;
import com.app.delivxstore.main.manage_address.SendAddress;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.AutocompleteInfo;
import com.app.delivxstore.utility.Utility;
import com.google.android.gms.common.api.Status;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import retrofit2.Response;

import static com.app.delivxstore.utility.VariableConstants.ADD_ADDRESS;


public class AddressActPresenterImpl implements AddressActPresenter, LocationCallBack {

    @Inject
    LocationProvider provider;
    @Inject
    RxLocationObserver observer;
    @Inject
    AddressProviderFromLocation providerFromLocation;
    @Inject
    ReactiveLocationProvider locationProvider;
    @Inject
    RxAddressObserver addressObserver;
    @Inject
    AddressActView mView;

    @Inject
    @Named(ADD_ADDRESS)
    CompositeDisposable compositeDisposable;

    @Inject
    NetworkStateHolder networkStateHolder;
    @Inject
    NetworkService service;

    @Inject
    PreferenceHelperDataSource manager;


    @Inject
    Activity mActivity;
    @Inject
    ReactiveLocationProvider reactiveLocationProvider;
    @Inject
    PlaceProvider placeProvider;
    @Inject
    RxPlaceSearchObserver placeSearchObserver;
    private String taggedAs, city, country, state, address1, address2, zipcode;
    private String AddressId;
    private double latitude, longitude;
    private String action = "add";
    private boolean isSelected=true;

    @Inject
    AddressActPresenterImpl() {

    }

    @Override
    public void start() {

        if (action.equals("add")) {
            getLatLong();
            mView.setHomeType();
            taggedAs = mActivity.getString(R.string.home);
        }
        getFullAddress();
        getplaces();

    }

    @Override
    public void setIntentData(String action, DataInGetAddress address) {
        this.action = action;
        address1 = address.getAddLine1();
        address2 = address.getAddLine2();
        taggedAs = address.getTaggedAs();
        city = address.getCity();
        country = address.getCountry();
        state = address.getState();
        zipcode = address.getPincode();
        AddressId = address.getId();
        latitude = address.getLatitude();
        longitude = address.getLongitude();


    }

    @Override
    public void setSavedAs(int type, String savedAs) {
        taggedAs = savedAs;
        switch (type) {
            case 0:
                mView.setHomeType();
                break;
            case 1:
                mView.setWorkType();
                break;
            case 2:
                mView.setOther(mActivity.getString(R.string.other));
                break;

            default:
                break;
        }
    }

    @Override
    public void addAddress(String add1, String add2, String houseNo, String landMark, double lat, double longi, String savedAs, int savedType, String otherAddress,String customerID) {

            if (address1==null || !address1.equals(""))
            {
                address1=add1;
            }
        if (address1 != null && !address1.equals("")) {
            if (taggedAs != null && !taggedAs.equals("")) {
                Utility.printLog("valid ");
                if (action.equals("add"))
                    mAddAddressApi(houseNo, landMark, latitude, longitude, otherAddress,customerID);
                else
                    mUpdateAddressApi(houseNo, landMark, latitude, longitude, otherAddress,customerID);
            } else {
                mView.onError(mActivity.getString(R.string.selectSaveAs));

            }

        } else {
            mView.onError(mActivity.getString(R.string.selectAddress));
        }


    }

    public void setLatlong(double lat, double longi)
    {
        latitude = lat;
        longitude = longi;
    }

    @Override
    public void getAddress(double lat, double longi) {
        latitude = lat;
        longitude = longi;
       // providerFromLocation.getAddressFromLocation(lat, longi, BuildConfig.SERVER_KEY);


        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mActivity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

             if(addresses!=null && addresses.size()>0)
                getAddressDetail(addresses.get(0));

           /* String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL*/
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void mapReady() {
        if (action.equals("edit")) {
            String full_address;
            full_address = address1;
            if (address2 != null) {
                full_address = address1 + " " + address2;
            }

            if(isSelected)
                mView.setAddress(full_address + ", " + city + ", " + state + " " + zipcode + " , " + country);

            if (taggedAs.equalsIgnoreCase(mActivity.getString(R.string.home)))
                mView.setHomeType();
            else if (taggedAs.equalsIgnoreCase(mActivity.getString(R.string.work)))
                mView.setWorkType();
            else
                mView.setOther(taggedAs);

            mView.setMarker(latitude, longitude);
        }
    }

    @Override
    public void getCurrentAddress() {
        provider.startLocation(this);

    }

    @Override
    public void setObserver(Observable<String> queryObservable) {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }

        placeProvider.startSearching(queryObservable);
    }

    @Override
    public void removeObserver(Observable<String> queryObservable) {
        // placeProvider.stopSearching();
    }

    @SuppressLint("CheckResult")
    private void getplaces() {
        Observer<List<AutocompleteInfo>> observer = new Observer<List<AutocompleteInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<AutocompleteInfo> places) {

                mView.addressList(places);

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Utility.printLog(" onError location oberved  " + e);
            }

            @Override
            public void onComplete() {

            }

        };
        this.placeSearchObserver.subscribeOn(Schedulers.io());
        this.placeSearchObserver.observeOn(AndroidSchedulers.mainThread());
        this.placeSearchObserver.subscribe(observer);

    }


    @Override
    public void onAddressClick() {
        mView.startLocationAct();
    }


    @Override
    public void stop() {
        placeProvider.stopSearching();
        compositeDisposable.dispose();
    }

    @Override
    public boolean isNetworkAvailable() {
        return networkStateHolder.isConnected();
    }


    private void getLatLong() {
        Observer<Location> observer = new Observer<Location>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Location location) {

                Utility.printLog(" onNext location oberved  " + location.getLatitude() + " " + location.getLongitude());

                getAddress(location.getLatitude(), location.getLongitude());

                provider.stopLocationUpdates();

                mView.setMarker(location.getLatitude(), location.getLongitude());

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Utility.printLog(" onError location oberved  " + e);
            }

            @Override
            public void onComplete() {
            }
        };
        this.observer.subscribeOn(Schedulers.io());
        this.observer.observeOn(AndroidSchedulers.mainThread());
        this.observer.subscribe(observer);
    }

    private void getAddressDetail(Address address)
    {
        String full_address = "";

        city = null;
        country = null;
        state = null;
        address1 = null;
        address2 = null;
        zipcode = null;


        if (address.getPremises() != null) {
            address1 = address.getPremises();
        }
        if (address.getThoroughfare() != null) {
            full_address = address.getThoroughfare();
        }

        if (address.getSubLocality() != null && !full_address.contains(address.getSubLocality())) {
            full_address = full_address + "," + address.getSubLocality();
        }
        address2 = full_address;

        if (address1 == null) {
            address1 = address2;
            address2 = null;
        }


        if (address2 != null) {
            full_address = address1 + " " + address2;
        }
        Utility.printLog(" onNext getAddressFromLocation full_address: " + full_address);

        city = address.getLocality();
        if(address.getAdminArea()!=null && "".equals(address.getAdminArea()))
            state = address.getAdminArea();
        else
            state = address.getLocality();
        country = address.getCountryName();
        zipcode = address.getPostalCode();

        if (full_address == null || full_address.equals("")) {
            full_address = address.getAddressLine(0);
            address1=full_address;
            if (city != null && full_address.contains(city)) {
                full_address = full_address.replace(city, "");
            }
            if (state != null && full_address.contains(state)) {
                full_address = full_address.replace(state, "");
            }
            if (country != null && full_address.contains(country)) {
                full_address = full_address.replace(country, "");
            }
            if (zipcode != null && full_address.contains(zipcode)) {
                full_address = full_address.replace(zipcode, "");
            }

        }
        String sendAddress="";
        if(address.getFeatureName()!=null && !address.getFeatureName().equals(""))
        {
            sendAddress=full_address+", "+address.getFeatureName()+ ", " + city + ", " + state + " " + zipcode + " , " + country;
        }
        if(sendAddress.contains("null"))
        {
            sendAddress = sendAddress.replace("null,","");
            sendAddress=sendAddress.replace("null","");
        }


        sendAddress = address.getAddressLine(0);

        sendAddress=sendAddress.replace(", ,","");

        mView.setAddress(sendAddress);

    }

    private void getFullAddress() {
        Observer<Address> observer = new Observer<Address>() {
            @Override
            public void onSubscribe(Disposable d) {
               compositeDisposable.add(d);
            }

            @Override
            public void onNext(Address address) {
                if (address != null) {
                    getAddressDetail(address);
                 }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Utility.printLog(" onError address observed  " + e);
            }

            @Override
            public void onComplete() {
            }
        };

        addressObserver.subscribeOn(Schedulers.io());
        addressObserver.observeOn(AndroidSchedulers.mainThread());
        addressObserver.subscribe(observer);
    }

    @Override
    public void onLocationServiceDisabled(Status status) {

    }


    private void mAddAddressApi(String houseNo, String landMark, double lat, double longi, String otherAddress,String customerID) {


        lat = Utility.roundValue(lat);
        longi = Utility.roundValue(longi);

        mView.showProgress();
        SendAddress address = new SendAddress();
        address.setAddLine1(address1);
        address.setAddLine2(address2);
        address.setFlatNumber(houseNo);
        address.setLandmark(landMark);
        address.setLatitude(lat);
        address.setLongitude(longi);
        address.setTaggedAs(taggedAs);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setPincode(zipcode);
        address.setUserId(customerID);

        if (taggedAs.equalsIgnoreCase(mActivity.getString(R.string.other))) {
            address.setTaggedAs(otherAddress);
        }

        /*if (couchDb.isAddressAdded(lat, longi)) {
            mView.hideLoading();
            mView.onError(mActivity.getString(R.string.addrExist));
        } else {*/

            Observable<Response<ResponseBody>> bad=service.addAddressApi(manager.getToken(),
                    manager.getLanguage(),address);

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
                            Utility.printLog("add address " + value.code());
                            switch (value.code()) {
                                // success
                                case 200:
//                                    couchDb.addAddress(address);
                                    mView.stopAct();
                                    break;
                                    
                                default:
                                    String Error = null;
                                    try {
                                         Error=value.errorBody().string();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    mView.onError(Error);
//                                    mView.onError(mActivity.getString(R.string.somethingWentWrong));
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
//        }

    }


    private void mUpdateAddressApi(String houseNo, String landMark, double lat, double longi, String otherAddress,String customerID) {
        mView.showProgress();
        SendAddress address = new SendAddress();
        address.setAddLine1(address1);
        address.setAddLine2(address2);
        address.setAddressId(AddressId);
      /*  address.setFlatNumber(houseNo);
        address.setLandmark(landMark);*/
        address.setLatitude(lat);
        address.setLongitude(longi);
        address.setUserId(customerID);
        address.setTaggedAs(taggedAs);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);

        if (taggedAs.equalsIgnoreCase(mActivity.getString(R.string.other))) {
            address.setTaggedAs(otherAddress);
        }
        Observable<Response<ResponseBody>> bad = service.updateAddressApi(manager.getToken(), manager.getLanguage(), address);

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
                        Utility.printLog("update address " + value.code());
                        switch (value.code()) {
                            // success
                            case 200:
                                mView.stopAct();
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
}
