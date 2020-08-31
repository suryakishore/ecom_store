package com.app.delivxstore.manual_locate;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.util.Log;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.location.LocationCallBack;
import com.app.delivxstore.location.LocationProvider;
import com.app.delivxstore.location.PlaceProvider;
import com.app.delivxstore.location.RxLocationObserver;
import com.app.delivxstore.location.RxPlaceSearchObserver;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.AutocompleteInfo;
import com.app.delivxstore.utility.Utility;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;


/**
 * <P>This is presenter implementation for search location </P>
 *
 * @author 3Embed
 */
public class SearchPresenterImpl implements SearchPresenter, LocationCallBack {

    @Inject
    SearchView mView;

    @Inject
    NetworkService service;

    private Activity mContext;
    private CompositeDisposable compositeDisposable;

    @Inject
    PreferenceHelperDataSource manager;
    @Inject
    NetworkStateHolder networkStateHolder;
    private boolean isComingFromHome = false;
    private String comingFrom = "";
    @Inject
    ReactiveLocationProvider reactiveLocationProvider;

    @Inject
    PlaceProvider placeProvider;
    @Inject
    RxPlaceSearchObserver placeSearchObserver;
    @Inject
    LocationProvider provider;
    @Inject
    RxLocationObserver observer;

    @Inject
    SearchPresenterImpl(Activity mContext) {
        this.mContext = mContext;
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void start() {

        mGetCuurentAdd(false);
        getplaces();
    }

    @Override
    public void stop() {

        mClearDisposable();
        mView.stopAct();
    }

    @Override
    public boolean isNetworkAvailable() {
        return Utility.isNetworkConnected(mContext);
    }

    private void mClearDisposable() {
        //  placeProvider.stopSearching();
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void getAddressDetail(String addressId, String fullAddress) {
        if (isNetworkAvailable()) {
            getAddress(addressId, fullAddress);
        } else {

            mView.hideProgress();
            mView.onError(mContext.getString(R.string.networkError));

        }
    }

    @Override
    public void setAddress(String address, double lat, double longi) {
        if (comingFrom != null && comingFrom.equalsIgnoreCase("addAddress")) {
            mView.sendSuccessData(lat, longi, address);
        } else {
            mCallingGetZoneApi(lat, longi, address);
        }
    }

    @Override
    public void setComingFrom(String comingFrom) {
        this.comingFrom = comingFrom;
        isComingFromHome = comingFrom != null && comingFrom.equals("home");
    }


    private void getAddress(String placeId, String fullAddress) {
        Utility.printLog("address placeId " + placeId);
        mView.showProgress();

        GeoDataClient mGeoDataClient = Places.getGeoDataClient(mContext);

        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();

                    Place place = places.get(0);
                    if (place != null) {
                        Utility.printLog("address " + place.getName() + " " + place.getAddress() + " lat " + place.getLatLng().latitude + " " + place.getLatLng().longitude);
                        if (comingFrom != null && comingFrom.equalsIgnoreCase("addAddress")) {
                            if (fullAddress != null && !"".equals(fullAddress))
                                mView.sendSuccessData(place.getLatLng().latitude, place.getLatLng().longitude, fullAddress);
                            else
                                mView.sendSuccessData(place.getLatLng().latitude, place.getLatLng().longitude, place.getName() + " " + place.getAddress());
                        } else {
                            mCallingGetZoneApi(place.getLatLng().latitude, place.getLatLng().longitude, place.getName() + " " + place.getAddress());

                        }

                    }
                    places.release();
                } else {
                    Utility.printLog("Place not found.");
                }
            }
        });


    }

    @Override
    public void setObserver(Observable<String> queryObservable) {


        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //F                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        placeProvider.startSearching(queryObservable);

    }

    @Override
    public void clearSearch() {
        mView.clearSearch();
    }

    @Override
    public void getCurrentAddress() {


        getCurrentLoc();
    }

    @Override
    public String getLanguage() {
        return manager.getLanguage();
    }


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


    /**
     * <P>Calling get zone api to fetch selected address is in zone or not.
     * on success finishing activity and start main .</P>
     */
    private void mCallingGetZoneApi(final double lat, final double longi, final String address) {
      /*  Utility.printLog("city is " + manager.getCity());
        Observable<Response<ResponseBody>> observable = service.getZoneApi(((ApplicationManager) mContext.getApplication()).getAuthToken(manager.getCustomerId()), manager.getLanguage(), lat, longi);

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {
                        mClearDisposable();
                        mView.hideLoading();

                        if (value.isSuccessful() && value.code() == 200) {
                            try {
                                JSONObject object = new JSONObject(value.body().string());
                                if (object.has("data") && object.getJSONObject("data") != null) {
                                    try {
                                        FirebaseMessaging.getInstance().unsubscribeFromTopic(mContext.getString(R.string.customer_) + "" + manager.getCityId());
                                        FirebaseMessaging.getInstance().subscribeToTopic(mContext.getString(R.string.customer_) + "" + manager.getZoneID());
                                    } catch (Exception e) {

                                    }

                                    manager.setCurrentLat(lat);
                                    manager.setCurrentLong(longi);
                                    manager.setAddressName(address);
                                    manager.setZoneId(object.getJSONObject("data").getString("zoneId"));
                                    manager.setZoneName(object.getJSONObject("data").getString("title"));
                                    manager.setCityId(object.getJSONObject("data").getString("cityId"));
                                    manager.setCity(object.getJSONObject("data").getString("city"));
                                    manager.setMileageMetric(object.getJSONObject("data").getString("mileageMetric"));
                                    manager.setCurrency(object.getJSONObject("data").getString("currency"));
                                    manager.setCurrencySymbol(object.getJSONObject("data").getString("currencySymbol"));

                                    Utility.printLog("city is af " + manager.getCity());
                                    FirebaseMessaging.getInstance().subscribeToTopic(mContext.getString(R.string.customer_) + "" + manager.getCityId());
                                    FirebaseMessaging.getInstance().subscribeToTopic(mContext.getString(R.string.customer_) + "" + manager.getZoneID());


                                    if (isComingFromHome) {
                                        CartDataEvent data = new CartDataEvent();
                                        data.setCartItemCount("0");
                                        CartCountObservable.getInstance().emit(data, false);
//                                        couchDb.clearCart();
                                        mView.locationDataSet();
                                    } else {
                                        mView.startHome();

                                    }
                                }

                            } catch (JSONException | IOException | NullPointerException e) {
                                mView.onError(mContext.getString(R.string.somethingWentWrong));
                            }
                        } else {
                            String msg = mContext.getString(R.string.somethingWentWrong);
                            if (value.code() == 400) {
                                mView.showEmptyResult();
                                try {
                                    JSONObject object = new JSONObject(value.errorBody().string());
                                    if (object.has("message") && object.getString("message") != null) {
                                        msg = object.getString("message");
                                    }
                                } catch (JSONException | IOException | NullPointerException e) {
                                    e.printStackTrace();
                                }
                            } else if (value.code() == 502) {
                                msg = mContext.getString(R.string.serverError);
                            } else {
                                mView.onError(msg);

                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utility.printLog("response " + e);
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });*/
    }

    private void getCurrentLoc() {
        getLatLong();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        provider.startLocation(this);

    }

    private void getLatLong() {

        Observer<Location> observer = new Observer<Location>() {

            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Location location) {

                mView.setCurrentLoc(mContext.getString(R.string.gettingLoc));
                mGetCuurentAdd(true);

                Utility.printLog(" onNext location oberved  " + location.getLatitude() + " " + location.getLongitude());
                // onLocationUpdate(location);
                provider.stopLocationUpdates();

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


    private void guessCurrentPlace() {


        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        PlaceDetectionClient placeDetectionClient = Places.
                getPlaceDetectionClient(mContext, null);

        Task<PlaceLikelihoodBufferResponse> placeResult = placeDetectionClient.
                getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                List<Place> placesList = new ArrayList<Place>();
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
              /*  for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    placesList.add(placeLikelihood.getPlace().freeze());
                }*/
                PlaceLikelihood likelihood = likelyPlaces.get(0);
                if (likelihood != null) {
                    String currentAddress = likelihood.getPlace().getName().toString() + " " + likelihood.getPlace().getAddress().toString();
                    double currentLat = likelihood.getPlace().getLatLng().latitude;
                    double currentLong = likelihood.getPlace().getLatLng().longitude;

                    Utility.printLog("got cl ct " + currentLat + " " + currentAddress);


                }
                likelyPlaces.release();

            }
        });


    }


    private void mGetCuurentAdd(boolean callApi) {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        compositeDisposable.add(
                reactiveLocationProvider.getCurrentPlace(null)
                        .subscribe(new Consumer<PlaceLikelihoodBuffer>() {
                            @Override
                            public void accept(PlaceLikelihoodBuffer buffer) {
                                PlaceLikelihood likelihood = buffer.get(0);
                                if (likelihood != null) {
                                    mView.setCurrentLoc(likelihood.getPlace().getAddress().toString());
                                    String currentAddress = likelihood.getPlace().getName().toString() + " " + likelihood.getPlace().getAddress().toString();
                                    double currentLat = likelihood.getPlace().getLatLng().latitude;
                                    double currentLong = likelihood.getPlace().getLatLng().longitude;

                                    // likelihood.getPlace().getLatLng().longitude;
                                    if (callApi) {
                                        if (comingFrom != null && comingFrom.equalsIgnoreCase("addAddress")) {
                                            mView.sendSuccessData(currentLat, currentLong, likelihood.getPlace().getName() + " " + likelihood.getPlace().getAddress());
                                        } else {
                                          //  CURRENT_ZONE_LAT = currentLat;
                                            //CURRENT_ZONE_LONGI = currentLong;

                                            mCallingGetZoneApi(currentLat, currentLong, currentAddress);
                                        }
                                    }

                                }
                                buffer.release();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("PlacesActivity", "Error in observable", throwable);
                            }
                        })
        );
    }

    @Override
    public void onLocationServiceDisabled(Status status) {
        mView.showGpsAlert(status);

    }
}
