package com.app.delivxstore.main.inventory;


import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InventoryModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract InventoryFragment inventoryFragment();

    @ActivityScoped
    @Binds
    abstract InventoryContract.View getView(InventoryFragment  inventoryFragment);

    @ActivityScoped
    @Binds
    abstract InventoryContract.Presenter getPresenter(InventoryPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector//(modules = InventoryFilterModule.class)
    abstract InventoryFilterFragment inventoryFilterFragment();

}
