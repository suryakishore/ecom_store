package pl.charmas.android.reactivelocation2.observables.location;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import pl.charmas.android.reactivelocation2.observables.BaseLocationObservableOnSubscribe;
import pl.charmas.android.reactivelocation2.observables.ObservableContext;

@SuppressWarnings("MissingPermission")
public class LastKnownLocationObservableOnSubscribe extends BaseLocationObservableOnSubscribe<Location> {
    public static Observable<Location> createObservable(ObservableContext ctx) {
        return Observable.create(new LastKnownLocationObservableOnSubscribe(ctx));
    }

    private LastKnownLocationObservableOnSubscribe(ObservableContext ctx) {
        super(ctx);
    }

    @Override
    protected void onGoogleApiClientReady(GoogleApiClient apiClient, ObservableEmitter<? super Location> emitter) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        if(location!=null)
        {
            Log.i("last location",location.getLatitude()+"");

        }else
        {
            Log.i("last location"," null ");

        }

        if (location != null) {
            emitter.onNext(location);
        }
        emitter.onComplete();
    }
}
