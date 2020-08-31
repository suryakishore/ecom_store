package com.app.delivxstore.main.createOrder;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class CreateOrderDaggerModule {
  @Binds
  @ActivityScoped
  abstract Activity getActivity(CreateOrderActivity createOrderActivity);

  @Binds
  @ActivityScoped
  abstract CreateOrderContract.ViewOperations getView(CreateOrderActivity createOrderActivity);

  @Binds
  @ActivityScoped
  abstract CreateOrderContract.PresenterOperations getPresenter(
      CreateOrderPresenter createOrderPresenter);
}
