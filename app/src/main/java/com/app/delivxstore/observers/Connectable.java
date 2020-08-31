package com.app.delivxstore.observers;

import android.annotation.SuppressLint;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observables.ConnectableObservable;

public class Connectable {


    private ConnectableObservable<String> connectableObservable;

    private ObservableEmitter<String> emitor;

    @SuppressLint("CheckResult")
    public Connectable() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                emitor = e;
            }
        });
        connectableObservable = observable.publish();
        connectableObservable.share();
        connectableObservable.replay();
        connectableObservable.connect();

    }

    public ConnectableObservable<String> getObservable() {
        return connectableObservable;
    }

    public void postData(String flag) {
        if (emitor != null) {
            emitor.onNext(flag);
        }
    }


}

