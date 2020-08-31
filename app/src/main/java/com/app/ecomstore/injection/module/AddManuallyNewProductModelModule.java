package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.addproduct.AddProductViewModel;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyViewModel;
import com.app.ecomstore.forceaddproduct.AddManuallyProductViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AddManuallyNewProductModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(AddManuallyProductViewModel.class)
  protected abstract ViewModel homeViewModel(AddManuallyProductViewModel ecomLoginViewModel);



}
