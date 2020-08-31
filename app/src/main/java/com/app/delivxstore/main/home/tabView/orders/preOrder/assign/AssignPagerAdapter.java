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

import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;

/**
 * Adapter for view pager
 * for the tab layout
 */
public class AssignPagerAdapter extends FragmentPagerAdapter {
  private String mDriverAssignCount = "0", mDriverNotAssignCount = "0";

  public AssignPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  /**
   * used to set the count values for each tab.
   *
   * @param driverAssignCount new order count
   */
  public void setDriverAssignCount(String driverAssignCount) {
    this.mDriverAssignCount = driverAssignCount;
    notifyDataSetChanged();
  }

  /**
   * used to set the count values for each tab.
   *
   * @param driverNotAssignCount new order count
   */
  public void setDriverNotAssignCount(String driverNotAssignCount) {
    this.mDriverNotAssignCount = driverNotAssignCount;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    if (position == ZERO) {
      return new DriverAssignedFragment();
    } else {
      return new DriverNotAssignedFragment();
    }
  }

  @Override
  public int getCount() {
    return TWO;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case ZERO:
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.driverAssigned),
            mDriverAssignCount);
      case ONE:
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.driverNotAssigned),
            mDriverNotAssignCount);
      default:
        return null;
    }
  }
}
