package com.app.delivxstore.main.add_address;


import com.app.delivxstore.dagger.ActivityScoped;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import static com.app.delivxstore.utility.VariableConstants.ADD_ADDRESS;

/**
 * Created by dell on 31-Mar-18.
 */

@Module
public class AddAddressUtilModule {

    @Provides
    @Named(ADD_ADDRESS)
    @ActivityScoped
    CompositeDisposable provideCompositeDisposable()
    {
        return new CompositeDisposable();
    }

}
