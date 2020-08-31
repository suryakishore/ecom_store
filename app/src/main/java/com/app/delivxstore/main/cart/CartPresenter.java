package com.app.delivxstore.main.cart;

import android.app.Activity;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;
import javax.inject.Inject;

public class CartPresenter implements CartContract.PresenterOperations {
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Utility utility;
  @Inject
  Activity context;
  @Inject
  CartContract.ViewOperations view;

  @Inject
  CartPresenter() {
  }

  @Override
  public void startPayment() {
    view.startPayment();
  }
}
