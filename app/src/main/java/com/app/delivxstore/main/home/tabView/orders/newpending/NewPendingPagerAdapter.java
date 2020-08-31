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

package com.app.delivxstore.main.home.tabView.orders.newpending;

import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.newOrders.NewOrdersFragment;
import com.app.delivxstore.main.home.tabView.orders.newpending.pending.PendingOrdersFragment;

/**
 * Adapter for view pager
 * for the tab layout
 */
public class NewPendingPagerAdapter extends FragmentPagerAdapter {
  private String mNewCount = "0", mPendingCount = "0";

  public NewPendingPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  /**
   * used to set the count values for each tab.
   *
   * @param newCount new order count
   */
  public void setNewCount(String newCount) {
    this.mNewCount = newCount;
    notifyDataSetChanged();
  }

  /**
   * used to set the count values for each tab.
   *
   * @param pendingCount pending order count
   */
  public void setPendingCount(String pendingCount) {
    this.mPendingCount = pendingCount;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    return position == ZERO ? new NewOrdersFragment() : new PendingOrdersFragment();
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
            ApplicationManager.getInstance().getResources().getString(R.string.New),
            mNewCount);
      case ONE:
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.pendingTxt),
            mPendingCount);
      default:
        return null;
    }
  }
}
