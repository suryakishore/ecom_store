package com.app.delivxstore.location;


import com.app.delivxstore.utility.AutocompleteInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by dell on 06-Feb-18.
 */

public class RxPlaceSearchObserver extends Observable<List<AutocompleteInfo>>
{
    private static final String TAG = "RxLocationObserver";
    private Observer<?super List<AutocompleteInfo>> observer;

    @Override
    protected void subscribeActual(Observer<? super List<AutocompleteInfo>> observer)
    {
        this.observer = observer;
    }

    /**
     * <h2>publishData</h2>
     * This method is used to publish the searched places
     * @param location data to be pushed
     */
    void publishData(List<AutocompleteInfo> location)
    {
        if(observer!=null)
        {
            observer.onNext(location);
            observer.onComplete();
        }
    }
}
