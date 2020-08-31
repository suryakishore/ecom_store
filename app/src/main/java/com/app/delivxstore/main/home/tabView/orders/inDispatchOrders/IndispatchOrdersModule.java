package com.app.delivxstore.main.home.tabView.orders.inDispatchOrders;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class IndispatchOrdersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract IndispatchFragment orderFragment();

    @ActivityScoped
    @Binds
    abstract IndispatchOrdersContract.PresenterOperations getOrderpresenter(IndispatchOrdersPresenter presenter);
}
