package com.app.delivxstore.main.home.tabView.orders.preOrder;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PreOrderDaggerModule {
  @FragmentScoped
  @ContributesAndroidInjector
  abstract PreOrderFragment orderFragment();

  @ActivityScoped
  @Binds
  abstract PreOrderConrtact.PreOrderPresenter getOrderpresenter(PreOrderPresenterImpl presenter);
}
