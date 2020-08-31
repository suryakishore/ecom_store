package com.app.delivxstore.observers;

import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observables.ConnectableObservable;


public class RxStoreObserver extends Observable<ArrayList<DriverData>>
{
    private static RxStoreObserver observebleClass;
    private Observer<? super ArrayList<DriverData>> observer;
    private ConnectableObservable<ArrayList<DriverData>> connectableObservable;

    public static RxStoreObserver getStoreTypeObsrever()
    {
        if (observebleClass == null)
        {
            observebleClass = new RxStoreObserver();
            return observebleClass;
        } else
        {
            return observebleClass;
        }
    }

    @Override
    protected void subscribeActual(Observer<? super ArrayList<DriverData>> observer) {
        this.observer = observer;
    }

    public void publishData(ArrayList<DriverData> myEventStatus)
    {
        if(observer!=null)
        {
            observer.onNext(myEventStatus);
            observer.onComplete();
        }

    }
}
