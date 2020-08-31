/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.mobileView.orderDetails;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberBtmSheet;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberContract;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumbersPresenterImpl;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Injects view and presenter for order list page
 */
@Module
public abstract class OrderDetailDaggerModule {

    @Binds
    @ActivityScoped
    abstract Activity  getActivity(OrderDetailsForMobile activity);

    @Binds
    @ActivityScoped
    abstract OrderDetailsContract.OrderDetailsView getView(OrderDetailsForMobile activity);

    @Binds
    @ActivityScoped
    abstract OrderDetailsContract.OrderDetailsPresenter getPresenter(
        OrderDetailsPresenterImpl presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract OrderNumberBtmSheet bindBottomSheet();

   /* @ActivityScoped
    @Binds
    abstract OrderNumberContract.OrderNumberView view(OrderNumberBtmSheet view);*/

    @ActivityScoped
    @Binds
    abstract OrderNumberContract.OrderNumberPresenter bindPresenter(
        OrderNumbersPresenterImpl presenter);

}
