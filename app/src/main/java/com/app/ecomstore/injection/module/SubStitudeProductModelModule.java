package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.substitute.SubStitudeViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SubStitudeProductModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(SubStitudeViewModel.class)
  protected abstract ViewModel chooseViewModel(SubStitudeViewModel packingViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ChooseQtyViewModel.class)
  protected abstract ViewModel chooseQtyViewModel(ChooseQtyViewModel packingViewModel);

}
