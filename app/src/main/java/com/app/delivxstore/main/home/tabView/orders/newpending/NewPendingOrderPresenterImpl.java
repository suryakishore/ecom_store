package com.app.delivxstore.main.home.tabView.orders.newpending;

import static com.app.ecomstore.util.EcomConstants.TYPE;
import static com.app.ecomstore.util.EcomConstants.TYPE_ACCEPT;
import static com.app.ecomstore.util.EcomConstants.TYPE_DISPATCH;
import static com.app.ecomstore.util.EcomConstants.TYPE_NEW;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.models.OrderData;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RXOrderResponseObserver;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import javax.inject.Inject;

/**
 * presenter for pre order fragment.
 */
public class NewPendingOrderPresenterImpl implements newpendingConrtact.PreOrderPresenter {
  @Inject
  Utility utility;
  @Inject
  Context context;
  @Inject
  Connectable connectable;
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  Observer<Data> observer = new Observer<Data>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Data value) {
      // getReadyForPickUpApi(ZERO, LIMIT);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };
  private boolean mIsLoading;
  private newpendingConrtact.PreOrderView mView;
  private String mTitle = "";
  private OrderData mOrderData;
  private Gson mGson = new Gson();

  @Inject
  public NewPendingOrderPresenterImpl() {
  }

  /**
   * this method is used to show the selected store name.
   */
  @SuppressLint("CheckResult")
  public void subscribeFilterData() {
    connectable.getObservable().subscribe(new DisposableObserver<String>() {
      @Override
      public void onNext(String value) {
        mView.setStoreName(value);
      }

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onComplete() {
      }
    });
  }

  @Override
  public void attachView(NewPendingOrderFragment viewOperations) {
    this.mView = viewOperations;
    RXOrderResponseObserver.getInstance().subscribe(observer);
  }

  @Override
  public void getView(Bundle arguments) {
    if (arguments != null && arguments.containsKey(TYPE)) {
      switch (arguments.getString(TYPE)) {
        case TYPE_NEW:
          mTitle = context.getResources().getString(R.string.New);
          break;
        case TYPE_ACCEPT:
          mTitle = context.getResources().getString(R.string.accepted);
          break;
        case TYPE_DISPATCH:
          mTitle = context.getResources().getString(R.string.inDispatch);
          break;
      }
      mView.setTitle(utility.isTablet(context), mTitle);
    }
  }

  @Override
  public boolean isLoading() {
    return mIsLoading;
  }
}
