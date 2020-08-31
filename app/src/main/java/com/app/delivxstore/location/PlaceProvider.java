package com.app.delivxstore.location;

import android.annotation.SuppressLint;
import android.location.Location;


import com.app.delivxstore.utility.AutocompleteInfo;
import com.app.delivxstore.utility.Utility;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;


public class PlaceProvider
{

    private ReactiveLocationProvider reactiveLocationProvider;
    private CompositeDisposable compositeDisposable;
    private RxPlaceSearchObserver placeSearchObserver;

    public PlaceProvider(ReactiveLocationProvider reactiveLocationProvider, RxPlaceSearchObserver placeSearchObserver) {
        this.reactiveLocationProvider = reactiveLocationProvider;
        this.compositeDisposable=new CompositeDisposable();
        this.placeSearchObserver = placeSearchObserver;
    }

    @SuppressLint("MissingPermission")
    public void startSearching(Observable<String> queryObservable)
    {


        Observable<Location> lastKnownLocationObservable = reactiveLocationProvider.getLastKnownLocation();
        Utility.printLog("start searching ");

        Observable<AutocompletePredictionBuffer> suggestionsObservable = Observable.combineLatest(queryObservable, lastKnownLocationObservable, QueryWithCurrentLocation::new).
                        flatMap(q -> {
                            if (q.location == null)
                                return Observable.empty();

                            double latitude = q.location.getLatitude();
                            double longitude = q.location.getLongitude();
                            LatLngBounds bounds = new LatLngBounds(
                                    new LatLng(latitude - 0.05, longitude - 0.05),
                                    new LatLng(latitude + 0.05, longitude + 0.05)
                            );
                            return reactiveLocationProvider.getPlaceAutocompletePredictions(q.query, bounds, null);
                        });


        compositeDisposable.add(suggestionsObservable.subscribe(buffer -> {
            List<AutocompleteInfo> infos = new ArrayList<>();

            for (AutocompletePrediction prediction : buffer)
            {
                infos.add(new AutocompleteInfo(prediction.getFullText(null).toString(), prediction.getPlaceId()));
            }
            Utility.printLog("start searching list "+infos.size());

            buffer.release();
            placeSearchObserver.publishData(infos);
        }));
    }

    private static class QueryWithCurrentLocation {
        final String query;
        public final Location location;

        private QueryWithCurrentLocation(String query, Location location) {
            this.query = query;
            this.location = location;
        }

    }


    public void stopSearching()
    {
        Utility.printLog("stop searching ");

        compositeDisposable.dispose();
    }

}
