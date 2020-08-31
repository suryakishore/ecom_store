package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyViewModel;
import com.app.ecomstore.boarding.verifyotp.EcomVerifyOtpViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.uiutil.barcodescanning.BarCodeViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class BarCodeModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(BarCodeViewModel.class)
  protected abstract ViewModel homeViewModel(BarCodeViewModel ecomVerifyOtpViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ChooseQtyViewModel.class)
  protected abstract ViewModel chooseViewModel(ChooseQtyViewModel packingViewModel);
}
