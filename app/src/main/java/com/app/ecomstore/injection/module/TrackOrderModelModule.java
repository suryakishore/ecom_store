package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.drivers.SelectDriversViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.trackOrder.TrackOrderViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TrackOrderModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(TrackOrderViewModel.class)
  protected abstract ViewModel homeViewModel(TrackOrderViewModel trackOrderViewModel);
}
