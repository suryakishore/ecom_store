package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;


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
public abstract class OrderNumberBottomSheetDaggerModule {


  @FragmentScoped
  @ContributesAndroidInjector
  abstract OrderNumberBtmSheet bindBottomSheet();

  @ActivityScoped
  @Binds
  abstract OrderNumberContract.OrderNumberView view(OrderNumberBtmSheet view);

  @ActivityScoped
  @Binds
  abstract OrderNumberContract.OrderNumberPresenter bindPresenter(OrderNumbersPresenterImpl presenter);


}
