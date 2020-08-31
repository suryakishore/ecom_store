package com.app.ecomstore.injection.module;

import androidx.lifecycle.ViewModel;
import com.app.ecomstore.forcepick.ForcePickViewModel;
import com.app.ecomstore.injection.ViewModelKey;
import com.app.ecomstore.printlabel.PrintLabelViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PrintLabelModelModule extends ViewModelFactory {
  @Binds
  @IntoMap
  @ViewModelKey(PrintLabelViewModel.class)
  protected abstract ViewModel homeViewModel(PrintLabelViewModel packingViewModel);
}
