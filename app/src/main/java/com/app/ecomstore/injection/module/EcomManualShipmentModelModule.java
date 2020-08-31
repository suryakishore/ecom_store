package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.boarding.login.EcomLoginViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.shipment.ManualShippmentViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class EcomManualShipmentModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(ManualShippmentViewModel.class)
  protected abstract ViewModel homeViewModel(ManualShippmentViewModel manualShippmentViewModel);
}
