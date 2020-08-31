package com.app.delivxstore.main.editprofile;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class EditProfileModule {
  @ActivityScoped
  @Binds
  abstract Activity getActivity(EditProfileActivity activity);

  @Binds
  @ActivityScoped
  abstract EditProfileContract.EditProfileView getView(EditProfileActivity view);

  @Binds
  @ActivityScoped
  abstract EditProfileContract.Presenter getPresenter(EditProfilePresenter presenter);
}
