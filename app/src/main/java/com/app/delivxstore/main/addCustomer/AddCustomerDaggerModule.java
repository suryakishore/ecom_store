package com.app.delivxstore.main.addCustomer;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.main.createOrder.CreateOrderActivity;
import com.app.delivxstore.main.createOrder.CreateOrderContract;
import com.app.delivxstore.main.createOrder.CreateOrderPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AddCustomerDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(AddCustomerActivity addCustomerActivity);

    @Binds
    @ActivityScoped
    abstract AddCustomerContract.ViewOperations getView(AddCustomerActivity addCustomerActivity);

    @Binds
    @ActivityScoped
    abstract AddCustomerContract.PresenterOperations getPresenter(AddCustomerPresenter addCustomerPresenter);

}
