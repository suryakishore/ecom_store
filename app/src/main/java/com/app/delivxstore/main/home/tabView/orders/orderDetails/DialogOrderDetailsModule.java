package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class DialogOrderDetailsModule {
  @Binds
  @ActivityScoped
  abstract Activity getActivity(DialogOrderDetailsActivity activity);

  @Binds
  @ActivityScoped
  abstract DialogOrderDetailsContract.ViewOperation getView(DialogOrderDetailsActivity activity);

  @Binds
  @ActivityScoped
  abstract DialogOrderDetailsContract.PresenterOperations getPresenter(
      DialogOrderDetailsPresenterImpl presenter);
}
