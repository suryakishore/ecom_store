package com.app.delivxstore.main.payment_choose_card;

import android.app.Activity;


import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ChoosePaymentDaggerModule {

    @ActivityScoped
    @Binds
    abstract Activity getActivity(ChoosePaymentAct activity);

    @ActivityScoped
    @Binds
    abstract ChoosePaymentView getPaymentView(ChoosePaymentAct view);

    @ActivityScoped
    @Binds
    abstract ChoosePaymentPresenter getPaymentPresenter(ChoosePaymentPresenterImpl presenter);


}
