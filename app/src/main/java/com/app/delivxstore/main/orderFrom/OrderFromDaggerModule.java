package com.app.delivxstore.main.orderFrom;


import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.main.createOrder.CreateOrderActivity;
import com.app.delivxstore.main.createOrder.CreateOrderContract;
import com.app.delivxstore.main.createOrder.CreateOrderPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class OrderFromDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(OrderFromActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract OrderFromContract.ViewOperations getView(OrderFromActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract OrderFromContract.PresenterOperations getPresenter(OrderFromPresenter createOrderPresenter);

}
