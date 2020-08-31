package com.app.delivxstore.main.manage_address;

import android.app.Activity;


import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
@Module
public abstract class ManageAddressDaggerModule {

    /**
     * <P>This method provides activity reference</P>
     * @return activity.
     */

    @ActivityScoped
    @Binds
    abstract Activity getActivity(ManageAddressAct activity);

    @ActivityScoped
    @Binds
    abstract ManageAddressView getView(ManageAddressAct view );

    @ActivityScoped
    @Binds
    abstract ManageAddressPresenter getManageAddressPresenter(ManageAddressPresenterImpl presenter);



}
