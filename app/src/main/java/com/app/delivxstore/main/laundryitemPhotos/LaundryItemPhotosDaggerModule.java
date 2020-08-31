package com.app.delivxstore.main.laundryitemPhotos;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

@Module
public  abstract class LaundryItemPhotosDaggerModule {


    @Binds
    @ActivityScoped
    abstract Activity getActivity(LaundryItemPhotosActivity activity);

    @Binds
    @ActivityScoped
    abstract LaundryItemPhotosContract.View getView(LaundryItemPhotosActivity activity);

    @Binds
    @ActivityScoped
    abstract LaundryItemPhotosContract.Presenter getPresenter(LaundryItemPhotosPresenterImpl presenter);

}
