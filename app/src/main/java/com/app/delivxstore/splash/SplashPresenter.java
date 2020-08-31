package com.app.delivxstore.splash;

import android.app.Activity;

import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

/**
 * <h1>SplashPresenter</h1>
 * This class is used to display the Splash Screen
 * @author 3Embed
 * @since on 28/2/18.
 */
public class SplashPresenter implements SplashContract.PresenterOperations {

    @Inject
    SplashContract.ViewOperations view;

    @Inject
    Utility utility;

    @Inject
    Activity context;

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void checkScreenSize() {
        view.setView(utility.isTablet(context));
    }
}
