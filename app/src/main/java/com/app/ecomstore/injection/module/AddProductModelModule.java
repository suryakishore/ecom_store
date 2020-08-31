package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.addproduct.AddProductViewModel;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyViewModel;
import com.app.ecomstore.boarding.login.EcomLoginViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AddProductModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(AddProductViewModel.class)
  protected abstract ViewModel homeViewModel(AddProductViewModel ecomLoginViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ChooseQtyViewModel.class)
  protected abstract ViewModel chooseViewModel(ChooseQtyViewModel packingViewModel);

}
