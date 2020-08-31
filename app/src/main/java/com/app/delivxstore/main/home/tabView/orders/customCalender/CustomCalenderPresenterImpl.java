package com.app.delivxstore.main.home.tabView.orders.customCalender;

import android.app.Activity;

import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.DispatcherService;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class CustomCalenderPresenterImpl implements  CustomCalenderContract.CustomCalenderPresenter{

    @Inject
    CustomCalenderContract.CustomCalenderView view;

    @Inject
    NetworkService networkService;

    @Inject
    Utility utility;

    @Inject
    DispatcherService dispatcherService;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    NetworkStateHolder networkStateHolder;

    @Inject
    Activity context;

    @Inject
    CustomCalenderPresenterImpl() {

    }

    @Override
    public void getDates() {
        view.setDates();
    }

    @Override
    public void finish() {
        view.finishActivity();
    }
}
