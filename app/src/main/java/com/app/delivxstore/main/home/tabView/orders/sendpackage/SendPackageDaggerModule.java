package com.app.delivxstore.main.home.tabView.orders.sendpackage;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderConrtact;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SendPackageDaggerModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SendPackageFragment orderFragment();

    @ActivityScoped
    @Binds
    abstract SendPackageConrtact.SendPackagePresenter getOrderpresenter(SendPackagePresenterImpl presenter);
}
