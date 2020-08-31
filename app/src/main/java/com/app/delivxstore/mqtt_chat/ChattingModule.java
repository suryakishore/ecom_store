package com.app.delivxstore.mqtt_chat;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

/**
 * Created by DELL on 27-03-2018.
 */

@Module
public abstract class ChattingModule {

    @ActivityScoped
    @Binds
    abstract Activity getActivity(ChattingActivity activity);

    @Binds
    @ActivityScoped
    abstract   ChattingContract.ViewOperations getView(ChattingActivity view);

    @Binds
    @ActivityScoped
    abstract  ChattingContract.PresenterOperations getPresenter(Presenter presenter);
}
