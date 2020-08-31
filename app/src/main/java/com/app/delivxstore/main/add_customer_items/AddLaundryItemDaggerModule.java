package com.app.delivxstore.main.add_customer_items;

import android.app.Activity;


import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
@Module
public abstract class AddLaundryItemDaggerModule {


    /**
     * <P>This method provides activity reference</P>
     *
     * @return activity.
     */

    @ActivityScoped
    @Binds
    abstract Activity getActivity(AddLaundryItemActivity activity);

    @ActivityScoped
    @Binds
    abstract AddLaundryItemView getView(AddLaundryItemActivity view);

    @ActivityScoped
    @Binds
    abstract AddLaundryPresenter getChangeLanguage(AddLaundryPresenterImpl presenter);

}
