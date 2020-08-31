package com.app.delivxstore.main.mobileView.pastOrders;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

@Module
public abstract class PastOrdersDaggerModule {


    @Binds
    @ActivityScoped
    abstract Activity getActivity(PastOrdersAct activity);

    @Binds
    @ActivityScoped
    abstract PastOrdersContract.PastOrdersView getView(PastOrdersAct activity);

    @Binds
    @ActivityScoped
    abstract PastOrdersContract.PastOrdersPresenter getPresenter(PastOrdersPresenterImpl presenter);
}
