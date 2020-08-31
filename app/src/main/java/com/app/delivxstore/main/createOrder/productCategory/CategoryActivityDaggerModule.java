package com.app.delivxstore.main.createOrder.productCategory;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class CategoryActivityDaggerModule
{
  @Binds
  @ActivityScoped
  abstract Activity getActivity(CategoryActivity mainActivity);

  @Binds
  @ActivityScoped
  abstract CategoryActivityContract.CategoryView getView(CategoryActivity categoryActivity);

  @Binds
  @ActivityScoped
  abstract CategoryActivityContract.CategoryPresenter getPresenter(CategoryPresenterImpl presenter);
}
