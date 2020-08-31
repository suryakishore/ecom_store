package com.app.delivxstore.splash;

import android.app.Activity;

import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/* <h>SplashDaggerModule</h>
    * <p>Load the splash dagger Module</p>
    * @author 3Embed
    *@since 28-2-18
    */
@Module
public abstract class SplashDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(SplashActivity splashActivity);

    @Binds
    @ActivityScoped
    abstract SplashContract.PresenterOperations provideSplashPresenter(SplashPresenter presenter);

    @Binds
    @ActivityScoped
    abstract SplashContract.ViewOperations provideSplashView(SplashActivity activity);

}
