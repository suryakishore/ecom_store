package com.app.delivxstore.manual_locate;

import android.app.Activity;


import com.app.delivxstore.dagger.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by dell on 23-Nov-17.
 */

@Module
public abstract class SearchDaggerModule
{

    @ActivityScoped
    @Binds
    abstract Activity getActivity(SearchLocationAct act);

    @ActivityScoped
    @Binds
    abstract SearchView getView(SearchLocationAct act);

    /**
     * <P>This method provides Splash presenter reference</P>
     */
    @ActivityScoped
    @Binds
    abstract SearchPresenter getPresenter(SearchPresenterImpl presenter);



}
