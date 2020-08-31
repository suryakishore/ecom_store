package com.app.delivxstore.main.add_address;

import android.app.Activity;


import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by dell on 01-Feb-18.
 */
@Module
public abstract class AddAddressDaggerModule {

    @ActivityScoped
    @Binds
    abstract Activity provideLobbyView(AddAddressAct act);

    @ActivityScoped
    @Binds
    abstract AddressActPresenter taskPresenter(AddressActPresenterImpl presenter);

    @ActivityScoped
    @Binds
    abstract AddressActView provideView(AddAddressAct view);

}
