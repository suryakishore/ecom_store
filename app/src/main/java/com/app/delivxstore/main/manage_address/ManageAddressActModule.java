package com.app.delivxstore.main.manage_address;


import com.app.delivxstore.dagger.ActivityScoped;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import static com.app.delivxstore.utility.VariableConstants.MANAGE_ADDRESS_ACT;


/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

@Module
public class ManageAddressActModule {

    @Provides
    @Named(MANAGE_ADDRESS_ACT)
    @ActivityScoped
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
