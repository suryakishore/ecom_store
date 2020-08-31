package com.app.ecomstore.updateasile.updateasilebottomsheet;

import com.app.delivxstore.dagger.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UpdateAisleDaggerModule {
  @FragmentScoped
  @ContributesAndroidInjector
  abstract UpdateAisleBtmSheet updateAisleBtmSheet();
}
