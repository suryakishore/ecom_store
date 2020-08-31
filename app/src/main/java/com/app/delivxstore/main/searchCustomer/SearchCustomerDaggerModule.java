package com.app.delivxstore.main.searchCustomer;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.main.orderFrom.OrderFromActivity;
import com.app.delivxstore.main.orderFrom.OrderFromContract;
import com.app.delivxstore.main.orderFrom.OrderFromPresenter;

import dagger.Binds;
import dagger.Module;

@Module

public abstract class SearchCustomerDaggerModule {
    @Binds
    @ActivityScoped
    abstract Activity getActivity(SearchCustomerActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract SearchCustomerContract.ViewOperations getView(SearchCustomerActivity createOrderActivity);

    @Binds
    @ActivityScoped
    abstract SearchCustomerContract.PresenterOperations getPresenter(SearchCustomerPresenter createOrderPresenter);

}
