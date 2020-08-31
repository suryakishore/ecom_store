package com.app.delivxstore.main.inventory;


import com.app.delivxstore.dagger.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InventoryFilterModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract InventoryFilterFragment inventoryFilterFragment();

}
