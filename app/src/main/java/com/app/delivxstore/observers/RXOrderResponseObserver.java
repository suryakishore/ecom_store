package com.app.delivxstore.observers;

import com.app.delivxstore.main.home.model.GetOrderResponse;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.models.OrderData;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class RXOrderResponseObserver extends Observable<Data> {

    static RXOrderResponseObserver responseObserver =null;
    Observer<? super Data> observer;
    ArrayList<Observer<? super Data>> observers=new ArrayList<>();
    private RXOrderResponseObserver() {
    }

    public static RXOrderResponseObserver getInstance(){
        if(responseObserver ==null){
            responseObserver =new RXOrderResponseObserver();
        }
        return responseObserver;
    }

    @Override
    protected void subscribeActual(Observer<? super Data> observer) {
        /*this.observer=observer;
        if(!observers.contains(observer))*/
            observers.add(observer);
    }

    public void emit(Data data){
//        if(observer!=null){
            if(observers.size()>0){
                for(int i=0;i<observers.size();i++){
                    observers.get(i).onNext(data);
                }
            }
//        }
    }

    public void clear(){
        if(observers!=null && observers.size()>0){
            observers.clear();
        }
    }
}
