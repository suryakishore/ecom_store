package com.app.delivxstore.main.payment_choose_card;


import com.app.delivxstore.dagger.ActivityScoped;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import static com.app.delivxstore.utility.VariableConstants.CHOOSEPAYMENT_ACT;


/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
@Module
public class ChoosePaymentActModule {

    @Provides
    @Named(CHOOSEPAYMENT_ACT)
    @ActivityScoped
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }



}
