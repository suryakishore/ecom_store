package com.app.delivxstore.main.home.tabView.orders.newpending;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NewPendingOrderDaggerModule {
  @FragmentScoped
  @ContributesAndroidInjector
  abstract NewPendingOrderFragment orderFragment();

  @ActivityScoped
  @Binds
  abstract newpendingConrtact.PreOrderPresenter getOrderpresenter(
      NewPendingOrderPresenterImpl presenter);
}
