/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentHomeBinding;
import com.app.delivxstore.main.home.models.FoodTabs;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrderFragment;
import com.app.delivxstore.main.home.tabView.orders.inDispatchOrders.IndispatchFragment;
import com.app.delivxstore.main.home.tabView.orders.newOrders.NewOrdersFragment;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.boarding.login.EcomLoginActivity;
import com.app.ecomstore.util.EcomUtil;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Home fragment of the store where the orders in different
 * tabs are shown.
 */
public class HomeFragment extends DaggerFragment implements HomeFragmentContract.ViewOperations, SelectItem {
  @Inject
  public HomeFragmentContract.PresenterOperations presenter;
  @Inject
  Utility utility;
  @Inject
  NewOrdersFragment newOrders;
  @Inject
  AcceptedOrderFragment acceptedOrders;
  @Inject
  IndispatchFragment dispatchOrders;
  private ViewPagerAdapter mPagerAdapter;
  private FragmentHomeBinding mBinding;
  private ArrayList<FoodTabs> mFoodTabs = new ArrayList<>();
  private int mPosition;
  private TabsOrderAdapter mTabsOrderAdapter;

  @Inject
  public HomeFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
    presenter.attachView(this);
    presenter.checkScreenSize();
    mBinding.vpHomeTabViewPager.setCurrentItem(0, false);
    mBinding.vpHomeTabViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override
      public void onPageSelected(int position) {
        mPosition = position;
        mTabsOrderAdapter.setSwipe(mPosition);
      }

      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });
    return mBinding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.getOrders();
  }

  @Override
  public void setCountValues(
      int storeType,
      String newOrder,
      String acceptedOrder,
      String cancelledOrder,
      String packedOrder,
      String readyOrder,
      String checkedOrder,
      String inDispatchOrder,
      String completedOrder) {
    if (mPagerAdapter != null) {
      mPagerAdapter.setCountvalues(
          newOrder,
          acceptedOrder,
          cancelledOrder,
          packedOrder,
          storeType,
          readyOrder,
          inDispatchOrder,
          completedOrder);
      addHeaderItems(storeType, newOrder, acceptedOrder, cancelledOrder, packedOrder, readyOrder,
          checkedOrder,
          inDispatchOrder, completedOrder);
    }
  }

  @Override
  public void startLogin() {
    if (getActivity() != null) {
      startActivity(new Intent(getActivity(), EcomLoginActivity.class));
      getActivity().finish();
    }
  }

  @Override
  public void tokenExpire() {
    if (getActivity() != null) {
      SharedPreferences preferences = getActivity().getSharedPreferences("uflyStore",
          Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = preferences.edit();
      editor.clear();
      editor.commit();
      Intent i = new Intent(getActivity(), EcomLoginActivity.class);
      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      startActivity(i);
      getActivity().finish();
    }
  }

  /**
   * <h2>set view</h2>
   * <p>this method is used to decide the data </p>
   *
   * @param tablet is tablet display or not
   */
  @SuppressLint("RestrictedApi")
  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override
  public void setView(boolean tablet, int storeType, int forceAccept, int autoDispatch,
      int driverType, int packageType) {
    mBinding.vpHomeTabViewPager.setVisibility(View.VISIBLE);
    if (storeType == FIVE) {
      mBinding.vpHomeTabViewPager.setOffscreenPageLimit(FOUR);
    } else if (forceAccept == ONE && autoDispatch == ONE) {
      mBinding.vpHomeTabViewPager.setOffscreenPageLimit(FOUR);
    } else if (forceAccept == ZERO && autoDispatch == ONE) {
      mBinding.vpHomeTabViewPager.setOffscreenPageLimit(FIVE);
    } else {
      mBinding.vpHomeTabViewPager.setOffscreenPageLimit(FIVE);
    }
    mPagerAdapter = new ViewPagerAdapter(
        getChildFragmentManager(),
        storeType,
        forceAccept,
        autoDispatch,
        driverType, packageType);
    mBinding.vpHomeTabViewPager.setAdapter(mPagerAdapter);
  }

  @Override
  public void showMessage(final String statusMsg) {
    if (getActivity() != null) {
      getActivity().runOnUiThread(() -> {
        Toast.makeText(getActivity(), statusMsg, Toast.LENGTH_LONG).show();
      });
    }
  }

  /**
   * This method used to add the food items
   */
  private void addHeaderItems(int storeType,
      String newOrder,
      String acceptedOrder,
      String cancelledOrder,
      String packedOrder,
      String readyOrder,
      String checkedOrder,
      String inDispatchOrder,
      String completedOrder) {
    mFoodTabs.clear();
    FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
    flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
    flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
    flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
    switch (storeType) {
      case RESTAURENT:
        FoodTabs tabNewRestaurant = new FoodTabs();
        FoodTabs tabAcceptedRestaurant = new FoodTabs();
        FoodTabs tabReadyForPickUpRestaurant = new FoodTabs();
        FoodTabs tabInDelRestaurant = new FoodTabs();
        tabNewRestaurant.setStatusText(getResources().getString(R.string.New));
        tabNewRestaurant.setStatusCount(newOrder);
        tabAcceptedRestaurant.setStatusText(getResources().getString(R.string.inPreparing));
        tabAcceptedRestaurant.setStatusCount(acceptedOrder);
        tabReadyForPickUpRestaurant.setStatusText(
            getResources().getString(R.string.readyForPickUp));
        tabReadyForPickUpRestaurant.setStatusCount(readyOrder);
        tabInDelRestaurant.setStatusText(getResources().getString(R.string.inDelivery));
        tabInDelRestaurant.setStatusCount(inDispatchOrder);
        mFoodTabs.add(tabNewRestaurant);
        mFoodTabs.add(tabAcceptedRestaurant);
        mFoodTabs.add(tabReadyForPickUpRestaurant);
        mFoodTabs.add(tabInDelRestaurant);
        EcomUtil.printLog("exe" + "mPosition" + mPosition);
        mTabsOrderAdapter = new TabsOrderAdapter(mFoodTabs, mPosition,
            this);
        mBinding.rvFoodOrders.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFoodOrders.setAdapter(mTabsOrderAdapter);
        break;
      case LAUNDRY:
        FoodTabs tabNewLaundry = new FoodTabs();
        FoodTabs tabAcceptedLaundry = new FoodTabs();
        FoodTabs tabPackedLaundry = new FoodTabs();
        FoodTabs tabReadyForPickUpLaundry = new FoodTabs();
        FoodTabs tabInDelLaundry = new FoodTabs();
        tabNewLaundry.setStatusText(getResources().getString(R.string.New));
        tabNewLaundry.setStatusCount(newOrder);
        tabAcceptedLaundry.setStatusText(getResources().getString(R.string.accepted));
        tabAcceptedLaundry.setStatusCount(acceptedOrder);
        tabPackedLaundry.setStatusText(
            getResources().getString(R.string.processing));
        tabPackedLaundry.setStatusCount(packedOrder);
        tabReadyForPickUpLaundry.setStatusText(
            getResources().getString(R.string.readyForPickUp));
        tabReadyForPickUpLaundry.setStatusCount(readyOrder);
        tabInDelLaundry.setStatusText(getResources().getString(R.string.inDelivery));
        tabInDelLaundry.setStatusCount(inDispatchOrder);
        mFoodTabs.add(tabNewLaundry);
        mFoodTabs.add(tabAcceptedLaundry);
        mFoodTabs.add(tabPackedLaundry);
        mFoodTabs.add(tabReadyForPickUpLaundry);
        mFoodTabs.add(tabInDelLaundry);
        EcomUtil.printLog("exe" + "mPosition" + mPosition);
        mTabsOrderAdapter = new TabsOrderAdapter(mFoodTabs, mPosition,
            this);
        mBinding.rvFoodOrders.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFoodOrders.setAdapter(mTabsOrderAdapter);
        break;
      case GROCERY:
        FoodTabs tabNewGrocery = new FoodTabs();
        FoodTabs tabAcceptedGrocery = new FoodTabs();
        FoodTabs tabCheckOutGrocery = new FoodTabs();
        FoodTabs tabReadyForPickUpGrocery = new FoodTabs();
        FoodTabs tabInDelGrocery = new FoodTabs();
        tabNewGrocery.setStatusText(getResources().getString(R.string.New));
        tabNewGrocery.setStatusCount(newOrder);
        tabAcceptedGrocery.setStatusText(getResources().getString(R.string.picking));
        tabAcceptedGrocery.setStatusCount(acceptedOrder);
        tabCheckOutGrocery.setStatusText(getResources().getString(R.string.checkOut));
        tabCheckOutGrocery.setStatusCount(checkedOrder);
        tabReadyForPickUpGrocery.setStatusText(getResources().getString(R.string.readyForPickUp));
        tabReadyForPickUpGrocery.setStatusCount(readyOrder);
        tabInDelGrocery.setStatusText(getResources().getString(R.string.inDelivery));
        tabInDelGrocery.setStatusCount(inDispatchOrder);
        mFoodTabs.add(tabNewGrocery);
        mFoodTabs.add(tabAcceptedGrocery);
        mFoodTabs.add(tabCheckOutGrocery);
        mFoodTabs.add(tabReadyForPickUpGrocery);
        mFoodTabs.add(tabInDelGrocery);
        EcomUtil.printLog("exe" + "mPosition" + mPosition + "newOrder  " + newOrder);
        mTabsOrderAdapter = new TabsOrderAdapter(mFoodTabs, mPosition, this);
        mBinding.rvFoodOrders.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFoodOrders.setAdapter(mTabsOrderAdapter);
        break;
      case PHARMACY:
        FoodTabs tabNewPharmacy = new FoodTabs();
        FoodTabs tabAcceptedPharmacy = new FoodTabs();
        FoodTabs tabCheckOutPharmacy = new FoodTabs();
        FoodTabs tabReadyForPickUpPharmacy = new FoodTabs();
        FoodTabs tabInDelPharmacy = new FoodTabs();
        tabNewPharmacy.setStatusText(getResources().getString(R.string.New));
        tabNewPharmacy.setStatusCount(newOrder);
        tabAcceptedPharmacy.setStatusText(getResources().getString(R.string.picking));
        tabAcceptedPharmacy.setStatusCount(acceptedOrder);
        tabCheckOutPharmacy.setStatusText(getResources().getString(R.string.checkOut));
        tabCheckOutPharmacy.setStatusCount(checkedOrder);
        tabReadyForPickUpPharmacy.setStatusText(
            getResources().getString(R.string.readyForPickUp));
        tabReadyForPickUpPharmacy.setStatusCount(readyOrder);
        tabInDelPharmacy.setStatusText(getResources().getString(R.string.inDelivery));
        tabInDelPharmacy.setStatusCount(inDispatchOrder);
        mFoodTabs.add(tabNewPharmacy);
        mFoodTabs.add(tabAcceptedPharmacy);
        mFoodTabs.add(tabCheckOutPharmacy);
        mFoodTabs.add(tabReadyForPickUpPharmacy);
        mFoodTabs.add(tabInDelPharmacy);
        EcomUtil.printLog("exe" + "mPosition" + mPosition + "newOrder  " + newOrder);
        mTabsOrderAdapter = new TabsOrderAdapter(mFoodTabs, mPosition, this);
        mBinding.rvFoodOrders.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFoodOrders.setAdapter(mTabsOrderAdapter);
        break;
      default:
        FoodTabs tabNew = new FoodTabs();
        FoodTabs tabAccepted = new FoodTabs();
        FoodTabs tabPacked = new FoodTabs();
        FoodTabs tabReadyForPickUp = new FoodTabs();
        FoodTabs tabInDel = new FoodTabs();
        tabNew.setStatusText(getResources().getString(R.string.New));
        tabNew.setStatusCount(newOrder);
        tabAccepted.setStatusText(getResources().getString(R.string.accepted));
        tabAccepted.setStatusCount(acceptedOrder);
        tabPacked.setStatusText(getResources().getString(R.string.packed));
        tabPacked.setStatusCount(packedOrder);
        tabReadyForPickUp.setStatusText(getResources().getString(R.string.readyForPickUp));
        tabReadyForPickUp.setStatusCount(readyOrder);
        tabInDel.setStatusText(getResources().getString(R.string.inDelivery));
        tabInDel.setStatusCount(inDispatchOrder);
        mFoodTabs.add(tabNew);
        mFoodTabs.add(tabAccepted);
        mFoodTabs.add(tabPacked);
        mFoodTabs.add(tabReadyForPickUp);
        mFoodTabs.add(tabInDel);
        EcomUtil.printLog("exe" + "mPosition" + mPosition);
        mTabsOrderAdapter = new TabsOrderAdapter(mFoodTabs, mPosition, this);
        mBinding.rvFoodOrders.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFoodOrders.setAdapter(mTabsOrderAdapter);
        break;
    }
  }

  @Override
  public void showProgress() {
    if (mBinding.progressBar != null) {
      mBinding.progressBar.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideProgress() {
    if (mBinding.progressBar != null) {
      mBinding.progressBar.setVisibility(View.GONE);
    }
  }

  @Override
  public void logout() {
    startActivity(new Intent(getActivity(), EcomLoginActivity.class));
    getActivity().finish();
  }

  @Override
  public void onSelectItem(int position) {
    EcomUtil.printLog("exe" + "position" + position);
    mBinding.vpHomeTabViewPager.setCurrentItem(position, false);
  }
}
