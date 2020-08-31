package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.forcepick.ForcePickViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.pack.PackingViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ForcePickModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(ForcePickViewModel.class)
  protected abstract ViewModel homeViewModel(ForcePickViewModel packingViewModel);
}
