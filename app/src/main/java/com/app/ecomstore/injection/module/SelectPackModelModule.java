package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.drivers.SelectDriversViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.pack.PackingViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SelectPackModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(PackingViewModel.class)
  protected abstract ViewModel homeViewModel(PackingViewModel packingViewModel);
}
