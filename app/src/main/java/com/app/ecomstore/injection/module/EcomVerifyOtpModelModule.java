package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.boarding.verifyotp.EcomVerifyOtpViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class EcomVerifyOtpModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(EcomVerifyOtpViewModel.class)
  protected abstract ViewModel homeViewModel(EcomVerifyOtpViewModel ecomVerifyOtpViewModel);
}
