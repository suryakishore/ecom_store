package com.app.delivxstore.main.dispatchDetails;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DipatchDetailsDagerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(DispatchDetailsActivity activity);

    @Binds
    @ActivityScoped
    abstract DispatchDetailsContract.DispatchDetailsView getView(DispatchDetailsActivity activity);

    @Binds
    @ActivityScoped
    abstract DispatchDetailsContract.DispatchDetailsPresenter getPresenter(DispatchDetailsDaggerPresenterImpl presenter);

}
