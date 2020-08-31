package com.app.delivxstore.main.home.tabView.orders.customCalender;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
@Module
public abstract class CustomCalenderDaggerModule {


    @Binds
    @ActivityScoped
    abstract Activity getActivity(CustomCalenderActivity activity);

    @Binds
    @ActivityScoped
    abstract CustomCalenderContract.CustomCalenderView getView(CustomCalenderActivity activity);

    @Binds
    @ActivityScoped
    abstract CustomCalenderContract.CustomCalenderPresenter getPresenter(CustomCalenderPresenterImpl presenter);


}
