package com.app.ecomstore.addproduct.addproductbottomsheet;

import com.app.delivxstore.dagger.FragmentScoped;
import com.app.ecomstore.updateasile.updateasilebottomsheet.UpdateAisleBtmSheet;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChooseQtyDaggerModule {
  @FragmentScoped
  @ContributesAndroidInjector
  abstract ChooseQtyBtmSheet chooseQtyBtmSheet();
}
