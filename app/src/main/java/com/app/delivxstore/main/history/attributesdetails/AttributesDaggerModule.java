package com.app.delivxstore.main.history.attributesdetails;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class AttributesDaggerModule {

  @ActivityScoped
  @Binds
  abstract Activity getActivity(AttributesDetailsActivity attributesDetailsActivity);

  @ActivityScoped
  @Binds
  abstract AttributeDetailsContract.AttributesView getView(AttributesDetailsActivity attributesDetailsActivity);

  @ActivityScoped
  @Binds
  abstract AttributeDetailsContract.Presenter getPresenter(AttributeDetailsPresenter attributeDetailsPresenter);
}
