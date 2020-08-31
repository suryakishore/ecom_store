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

package com.app.delivxstore.main.home.tabView.orders.preOrder.assign;

import com.app.delivxstore.dagger.ActivityScoped;
import com.app.delivxstore.dagger.FragmentScoped;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrderContract;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrderPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DriverAssignOrdersModule {
  @FragmentScoped
  @ContributesAndroidInjector
  abstract DriverAssignedFragment orderFragment();

  @ActivityScoped
  @Binds
  abstract DriverAssignContract.PresenterOperations getOrderPresenter(
      DriverAssignOrderPresenter presenter);
}
