package com.app.delivxstore.main.cart;


import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.main.createOrder.CreateOrderActivity;
import com.app.delivxstore.main.createOrder.CreateOrderContract;
import com.app.delivxstore.main.createOrder.CreateOrderPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CartDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(CartActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract CartContract.ViewOperations getView(CartActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract CartContract.PresenterOperations getPresenter(CartPresenter createOrderPresenter);


}
