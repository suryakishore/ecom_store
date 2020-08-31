package com.app.delivxstore.splash;


/**
 * <h1>SplashContract</h1>
 * <p>This interface has the contract of presenter and view</p>
 * @author  3Embed
 * @since 26-2-2018
 */

public interface SplashContract {

    interface PresenterOperations
    {
        void checkScreenSize();
    }

    interface ViewOperations
    {
        void setView(boolean tablet);
    }
}
