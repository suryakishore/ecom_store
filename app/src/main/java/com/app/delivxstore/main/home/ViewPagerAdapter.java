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

package com.app.delivxstore.main.home;

import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.FOUR;
import static com.app.ecomstore.util.EcomConstants.GROCERY;
import static com.app.ecomstore.util.EcomConstants.LAUNDRY;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.THREE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.inAssign.InAssignFragment;
import com.app.delivxstore.main.home.tabView.orders.inDispatchOrders.IndispatchFragment;
import com.app.delivxstore.main.home.tabView.orders.newOrders.NewOrdersFragment;
import com.app.delivxstore.main.home.tabView.orders.newpending.NewPendingOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.pending.CheckOutOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.sendpackage.SendPackageFragment;
import com.app.ecomstore.util.EcomUtil;

/**
 * Adapter for view pager
 * for the tab layout
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
  private String mNewCount = "0", mAcceptCount = "0", mCancelledOrder = "0",
      mPackedOrder = "0", mReadyOrder = "0", mInDispatchOrder = "0",
      mCompletedOrder = "0";
  private int mStoreType, mForcedAcc, mAutoDis, mDriverType, mIsPackageEnable;

  ViewPagerAdapter(FragmentManager fm, int storeType, int forcedAcc,
      int autoDis, int driverType, int isPackageEnable) {
    super(fm);
    this.mStoreType = storeType;
    this.mForcedAcc = forcedAcc;
    this.mAutoDis = autoDis;
    this.mDriverType = driverType;
    this.mIsPackageEnable = isPackageEnable;
  }

  /**
   * used to set the count values for each tab.
   *
   * @param newOrder        new order count
   * @param acceptedOrder   accepted order count
   * @param cancelledOrder  cancelledOrder order count
   * @param packedOrder     packedOrder order count
   * @param storeType       storeType to define whcih type of store.
   * @param readyOrder      readyOrder order count
   * @param inDispatchOrder inDispatchOrder order count
   * @param completedOrder  completedOrder order count
   */
  void setCountvalues(String newOrder, String acceptedOrder, String cancelledOrder,
      String packedOrder, int storeType, String readyOrder,
      String inDispatchOrder, String completedOrder) {
    this.mNewCount = newOrder;
    this.mAcceptCount = acceptedOrder;
    this.mCancelledOrder = cancelledOrder;
    this.mPackedOrder = packedOrder;
    this.mStoreType = storeType;
    this.mReadyOrder = readyOrder;
    this.mInDispatchOrder = inDispatchOrder;
    this.mCompletedOrder = completedOrder;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    if (position == ZERO) {
      if (mStoreType == FIVE || (mForcedAcc == ONE && mAutoDis == ONE) ||
          (mForcedAcc == ONE && mAutoDis == ZERO)) {
        return new AcceptedOrderFragment();
      }
      if (mStoreType == PHARMACY) {
        return new NewPendingOrderFragment();
      }
      return new NewOrdersFragment();
    } else if (position == ONE) {
      if (mStoreType == FIVE) {
        return new PreOrderFragment();
      } else if ((mForcedAcc == ONE && mAutoDis == ONE) || (mForcedAcc == ONE
          && mAutoDis == ZERO)) {
        if (mDriverType == TWO) {
          return new IndispatchFragment();
        }
        return new InAssignFragment();
      }
      return new AcceptedOrderFragment();
    } else if (position == TWO) {
      if (mStoreType == RESTAURENT) {
        return new PreOrderFragment();
      } else if (mStoreType == FIVE) {
        return new IndispatchFragment();
      } else if ((mForcedAcc == ONE && mAutoDis == ONE) || (mForcedAcc == ONE
          && mAutoDis == ZERO)) {
        return new PreOrderFragment();
      } else if (mForcedAcc == ZERO && mAutoDis == ONE) {
        if (mDriverType == TWO) {
          return new IndispatchFragment();
        }
        return new InAssignFragment();
      } else if (mStoreType == PHARMACY || mStoreType == GROCERY) {
        return new CheckOutOrderFragment();
      }
      return new InAssignFragment();
    } else if (position == THREE) {
      if (mStoreType == RESTAURENT) {
        return new IndispatchFragment();
      } else if (mStoreType == PHARMACY || mStoreType == GROCERY) {
        return new PreOrderFragment();
      } else if (mStoreType == FIVE) {
        return new InAssignFragment();
      } else if (mForcedAcc == ZERO && mAutoDis == ONE) {
        if (mDriverType == TWO) {
          return new InAssignFragment();
        }
        return new PreOrderFragment();
      } else if (mForcedAcc == ONE && mAutoDis == ZERO) {
        if (mDriverType == TWO) {
          return new PreOrderFragment();
        }
        return new InAssignFragment();
      } else if (mForcedAcc == ONE && mAutoDis == ONE) {
        if (mIsPackageEnable == ONE && mDriverType != TWO) {
          return new SendPackageFragment();
        }
        return new InAssignFragment();
      } else {
        return new PreOrderFragment();
      }
    } else if (position == FOUR) {
      if (mForcedAcc == ZERO && mAutoDis == ONE) {
        if (mDriverType == TWO) {
          return new PreOrderFragment();
        }
        return new SendPackageFragment();
      } else if (mForcedAcc == ONE && mAutoDis == ZERO) {
        if (mDriverType == TWO) {
          return new InAssignFragment();
        }
        return new PreOrderFragment();
      } else if (mStoreType == PHARMACY || mStoreType == GROCERY) {
        return new IndispatchFragment();
      } else {
        return new IndispatchFragment();
      }
    } else if (position == FIVE) {
      if (mForcedAcc == ZERO && mAutoDis == ZERO && mIsPackageEnable == ONE) {
        return new SendPackageFragment();
      } else if (mStoreType == PHARMACY || mStoreType == GROCERY) {
        return new IndispatchFragment();
      }
      return new InAssignFragment();
    } else {
      return new InAssignFragment();
    }
  }

  @Override
  public int getCount() {
    switch (mStoreType) {
      case RESTAURENT:
        return FOUR;
      default:
        return FIVE;
    }
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case ZERO:
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.Now), mNewCount);
      case ONE:
        if (mStoreType == RESTAURENT) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.inPreparing),
              mAcceptCount);
        } else if (mStoreType == PHARMACY) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.picking),
              mAcceptCount);
        }
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.accepted),
            mAcceptCount);
      case TWO:
        if (mStoreType == RESTAURENT) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.readyForPickUp),
              mReadyOrder);
        } else if (mStoreType == PHARMACY) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.picking),
              mNewCount);
        } else if (mStoreType == LAUNDRY) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.processing),
              mPackedOrder);
        }
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.packed),
            mPackedOrder);
      case THREE:
        if (mStoreType == RESTAURENT) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.inDelivery),
              mInDispatchOrder);
        } else if (mStoreType == PHARMACY) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.packed),
              mPackedOrder);
        }
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.readyForPickUp),
            mReadyOrder);
      case FOUR:
        if (mStoreType == PHARMACY) {
          return String.format("%s (%s)",
              ApplicationManager.getInstance().getResources().getString(R.string.readyForPickUp),
              mReadyOrder);
        }
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.inDelivery),
            mInDispatchOrder);
      case FIVE:
        return String.format("%s (%s)",
            ApplicationManager.getInstance().getResources().getString(R.string.inDelivery),
            mInDispatchOrder);
      default:
        return null;
    }
  }
}
