package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.drivers.SelectDriversViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.partner.SelectParnerViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SelectDriversModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(SelectDriversViewModel.class)
  protected abstract ViewModel homeViewModel(SelectDriversViewModel selectParnerViewModel);
}
