package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.partner.SelectParnerViewModel;
import com.app.ecomstore.shipment.ManualShippmentViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SelectPartnerModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(SelectParnerViewModel.class)
  protected abstract ViewModel homeViewModel(SelectParnerViewModel selectParnerViewModel);
}
