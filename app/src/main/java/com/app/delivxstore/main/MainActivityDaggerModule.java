package com.app.delivxstore.main;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import com.app.delivxstore.main.createOrder.CreateOrderFragment;
import com.app.delivxstore.main.history.HistoryContract;
import com.app.delivxstore.main.history.HistoryFragment;
import com.app.delivxstore.main.history.HistoryPresenter;
import com.app.delivxstore.main.language.LanguageContract;
import com.app.delivxstore.main.language.LanguageFragment;
import com.app.delivxstore.main.language.LanguagePresenter;
import com.app.delivxstore.main.notification.NotificationContract;
import com.app.delivxstore.main.notification.NotificationFragment;
import com.app.delivxstore.main.notification.NotificationPresenter;
import com.app.delivxstore.main.wallet.WalletContract;
import com.app.delivxstore.main.wallet.WalletFragment;
import com.app.delivxstore.main.wallet.WalletPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityDaggerModule {

  @Binds
  @ActivityScoped
  abstract Activity getActivity(MainActivity mainActivity);

  @Binds
  @ActivityScoped
  abstract MainActivityContract.ViewOperations getView(MainActivity mainActivity);

  @Binds
  @ActivityScoped
  abstract MainActivityContract.PresenterOperations getPresenter(MainPresenter mainPresenter);

  @FragmentScoped
  @ContributesAndroidInjector
  abstract CreateOrderFragment homeFragment();

  @FragmentScoped
  @ContributesAndroidInjector
  abstract WalletFragment walletFragment();

  @ActivityScoped
  @Binds
  abstract WalletContract.WalletView walletView(WalletFragment walletFragment);

  @ActivityScoped
  @Binds
  abstract WalletContract.Presenter walletPresenter(WalletPresenter presenter);


  @FragmentScoped
  @ContributesAndroidInjector
  abstract HistoryFragment historyFragment();

  @ActivityScoped
  @Binds
  abstract HistoryContract.HistoryView getHistoryView(HistoryFragment homeFragment);

  @ActivityScoped
  @Binds
  abstract HistoryContract.Presenter getHistoryPresenter(HistoryPresenter presenter);


  @FragmentScoped
  @ContributesAndroidInjector
  abstract LanguageFragment languageFragment();

  @ActivityScoped
  @Binds
  abstract LanguageContract.LanguageView languageView(LanguageFragment languageFragment);

  @ActivityScoped
  @Binds
  abstract LanguageContract.Presenter languagePresenter(LanguagePresenter presenter);


  @FragmentScoped
  @ContributesAndroidInjector
  abstract NotificationFragment notificationFragment();

  @ActivityScoped
  @Binds
  abstract NotificationContract.NotificationView notificationView(NotificationFragment notificationFragment);

  @ActivityScoped
  @Binds
  abstract NotificationContract.Presenter notificationPresenter(NotificationPresenter presenter);
}
