package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ChooseQtyModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(ChooseQtyViewModel.class)
  protected abstract ViewModel homeViewModel(ChooseQtyViewModel packingViewModel);
}
