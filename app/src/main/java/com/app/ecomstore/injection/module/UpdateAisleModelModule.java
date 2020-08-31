package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.updateasile.UpdateAisleViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class UpdateAisleModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(UpdateAisleViewModel.class)
  protected abstract ViewModel homeViewModel(UpdateAisleViewModel ecomLoginViewModel);
}
