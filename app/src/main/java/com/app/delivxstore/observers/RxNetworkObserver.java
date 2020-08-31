package com.app.delivxstore.observers;


import com.app.delivxstore.networking.NetworkStateHolder;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * <h2>RxNetworkObserver</h2>
 * <P>
 *  Its the network observer class to observe the network
 *  changes in the App.
 * </P>
 * @version 1.0.
 * @author 3Embed.*/
public class RxNetworkObserver extends Observable<NetworkStateHolder>
{
    private Observer<?super NetworkStateHolder> observer;

    @Override
    protected void subscribeActual(Observer<? super NetworkStateHolder> observer)
    {
        this.observer=observer;
    }

    public void publishData(NetworkStateHolder data)
    {
        if(observer!=null)
        {
            observer.onNext(data);
            observer.onComplete();
        }
    }

}