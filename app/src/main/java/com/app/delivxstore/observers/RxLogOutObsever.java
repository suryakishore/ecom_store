package com.app.delivxstore.observers;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class RxLogOutObsever extends Observable<Boolean> {

    private Observer<?super Boolean> observer;

    @Override
    protected void subscribeActual(Observer<? super Boolean> observer)
    {
        this.observer=observer;
    }

    public void publishData(Boolean data)
    {
        if(observer!=null)
        {
            observer.onNext(data);
            observer.onComplete();
        }
    }
}
