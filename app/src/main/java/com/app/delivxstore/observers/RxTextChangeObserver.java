package com.app.delivxstore.observers;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class RxTextChangeObserver extends Observable<String>{

    static RxTextChangeObserver responseObserver =null;
    ArrayList<Observer<? super String>> observers=new ArrayList<>();

    public RxTextChangeObserver() {}

    public static RxTextChangeObserver getInstance(){
        if(responseObserver ==null){
            responseObserver =new RxTextChangeObserver();
        }
        return responseObserver;
    }

    @Override
    protected void subscribeActual(Observer<? super String> observer) {
        observers.add(observer);
    }

    public void emit(String data){
        if(observers.size()>0){
            for(int i=0;i<observers.size();i++){
                observers.get(i).onNext(data);
            }
        }
    }

    public void clear(){
        if(observers!=null && observers.size()>0){
            observers.clear();
        }
    }

}
