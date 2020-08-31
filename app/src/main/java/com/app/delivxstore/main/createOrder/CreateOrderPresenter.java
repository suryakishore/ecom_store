package com.app.delivxstore.main.createOrder;

import android.app.Activity;

import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

public class CreateOrderPresenter implements CreateOrderContract.PresenterOperations {

    @Inject
    NetworkService networkService;
    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    @Inject
    Utility utility;

    @Inject
    Activity context;
    @Inject
    CreateOrderContract.ViewOperations view;
    @Inject
    CreateOrderPresenter() {

    }

    @Override
    public void sendPackage() {
        view.sendPackage();
    }

    @Override
    public void sendInventory() {
         view.sendInventory();
    }

    @Override
    public void pickUp() {
        view.pickUp();
    }

    @Override
    public void delivery() {
      view.delivery();
    }

    @Override
    public void orderFrom() {
         view.orderFrom();
    }
}
