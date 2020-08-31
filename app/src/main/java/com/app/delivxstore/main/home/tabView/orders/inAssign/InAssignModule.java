package com.app.delivxstore.main.home.tabView.orders.inAssign;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

@Module
public abstract class InAssignModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract InAssignFragment orderFragment();

    @ActivityScoped
    @Binds
    abstract InAssignContract.PresenterOperations getOrderpresenter(InAssignPresenter presenter);

}
