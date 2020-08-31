package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProductUnavailableSubstituteDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity getActivity(ProductUnavailableSubstituteActivity activity);

    @Binds
    @ActivityScoped
    abstract ProductUnavailableSubstituteContract.OrderUnavailableView getView(
        ProductUnavailableSubstituteActivity activity);

    @Binds
    @ActivityScoped
    abstract ProductUnavailableSubstituteContract.OrderUnavailablePresenter getPresenter(ProductUnavailableSubstitutePresenterImpl presenter);

}
