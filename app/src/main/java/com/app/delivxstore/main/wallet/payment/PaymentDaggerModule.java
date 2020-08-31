package com.app.delivxstore.main.wallet.payment;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import com.app.delivxstore.main.wallet.accountdetails.AccountListContract;
import com.app.delivxstore.main.wallet.accountdetails.AccountListFragment;
import com.app.delivxstore.main.wallet.accountdetails.AccountListPresenter;
import com.app.delivxstore.main.wallet.bankaccount.BankAccountContract;
import com.app.delivxstore.main.wallet.bankaccount.BankAccountFragment;
import com.app.delivxstore.main.wallet.bankaccount.BankAccountPresenter;
import com.app.delivxstore.main.wallet.stripe.StripeAccountContract;
import com.app.delivxstore.main.wallet.stripe.StripeAccountFragment;
import com.app.delivxstore.main.wallet.stripe.StripeAccountPresenter;
import com.app.delivxstore.main.wallet.withdraw.WithDrawContract;
import com.app.delivxstore.main.wallet.withdraw.WithDrawFragment;
import com.app.delivxstore.main.wallet.withdraw.WithDrawPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/*injects all payment package classed*/
@Module
public abstract class PaymentDaggerModule {

  @ActivityScoped
  @Binds
  abstract Activity getActivity(PaymentActivity activity);

  @ActivityScoped
  @Binds
  abstract PaymentContract.PaymentView getPaymentView(PaymentActivity view);

  @ActivityScoped
  @Binds
  abstract PaymentContract.Presenter getpaymentPresenter(PaymentPresenter presenter);


  @FragmentScoped
  @ContributesAndroidInjector
  abstract AccountListFragment bankDetailsFragment();

  @ActivityScoped
  @Binds
  abstract AccountListContract.BankDetailsView bankDetailsView(AccountListFragment accountListFragment);

  @ActivityScoped
  @Binds
  abstract AccountListContract.Presenter bankDetailsPresenter(AccountListPresenter presenter);

  @FragmentScoped
  @ContributesAndroidInjector
  abstract StripeAccountFragment stripAccountFragment();

  @ActivityScoped
  @Binds
  abstract StripeAccountContract.StripView stripView(StripeAccountFragment stripeAccountFragment);

  @ActivityScoped
  @Binds
  abstract StripeAccountContract.StripPresenter stripPresenter(StripeAccountPresenter stripeAccountPresenter);


  @FragmentScoped
  @ContributesAndroidInjector
  abstract BankAccountFragment bankAccountFragment();

  @ActivityScoped
  @Binds
  abstract BankAccountContract.BankAccountView bankAccountView(BankAccountFragment bankAccountFragment);

  @ActivityScoped
  @Binds
  abstract BankAccountContract.Presenter bankAccountPresenter(BankAccountPresenter presenter);

  @FragmentScoped
  @ContributesAndroidInjector
  abstract WithDrawFragment withDrawFragment();

  @ActivityScoped
  @Binds
  abstract WithDrawContract.WithDrawView withDrawView(WithDrawFragment rechargeFragment);

  @ActivityScoped
  @Binds
  abstract WithDrawContract.Presenter withDrawPresenter(WithDrawPresenter presenter);

}
