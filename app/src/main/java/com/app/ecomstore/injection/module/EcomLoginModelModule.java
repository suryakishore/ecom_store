package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.boarding.login.EcomLoginViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class EcomLoginModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(EcomLoginViewModel.class)
  protected abstract ViewModel homeViewModel(EcomLoginViewModel ecomLoginViewModel);
}
