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

import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentOrderBinding;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.tabView.orders.OrderAdapter;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderFragment;
import com.app.delivxstore.utility.MyScrollListener;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * This fragment used to show the accepted status
 */
public class DriverNotAssignedFragment extends DaggerFragment implements
    DriverNotAssignContract.ViewOperations, TextWatcher {
  @Inject
  DriverNotAssignContract.PresenterOperations presenter;
  private FragmentOrderBinding mBinding;
  private ArrayList<Data> orderDetails = new ArrayList<>();
  private OrderAdapter orderAdapter;
  private int mPenCount;

  @Inject
  public DriverNotAssignedFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
    initView();
    presenter.subscribeFilterData();
    return mBinding.getRoot();
  }

  @Override
  public void setStoreName(String storeName) {
  }

  /**
   * initialize view
   */
  private void initView() {
    presenter.attachView(this);
    mBinding.llMain.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
    mBinding.search.addTextChangedListener(this);
    presenter.getView(getArguments());
    orderDetails.clear();
    orderAdapter = new OrderAdapter(orderDetails);
    GridLayoutManager layoutManager;
    layoutManager = new GridLayoutManager(getActivity(),
        Utility.isTablet(getActivity()) ? FIVE : TWO, GridLayoutManager.VERTICAL, false);
    mBinding.rvOrder.setLayoutManager(layoutManager);
    mBinding.rvOrder.setItemAnimator(new DefaultItemAnimator());
    mBinding.rvOrder.setAdapter(orderAdapter);
    mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
      presenter.getDriverNotAssignOrderApi(ZERO, LIMIT);
      mBinding.swipeRefreshLayout.setRefreshing(false);
    });
  }

  @Override
  public void showProgress() {
    if (!mBinding.swipeRefreshLayout.isRefreshing()) {
      mBinding.progressBar.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void hideProgress() {
    mBinding.progressBar.setVisibility(View.GONE);
    mBinding.swipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void setTitle(boolean tablet, String type) {
  }

  @Override
  public void setListData(ArrayList<Data> orders, int status, int penCount, int driverCount,
      boolean isCityLogin) {
    mPenCount = penCount;
    if (orders != null && orders.size() > ZERO) {
      orderDetails.clear();
      orderDetails.addAll(orders);
      orderAdapter.setStatus(status, isCityLogin);
    } else {
      orderDetails.clear();
    }
    GridLayoutManager layoutManager;
    layoutManager = new GridLayoutManager(getActivity(),
        Utility.isTablet(getActivity()) ? FIVE : TWO, GridLayoutManager.VERTICAL, false);
    mBinding.rvOrder.setLayoutManager(layoutManager);
    orderAdapter.notifyDataSetChanged();
    mBinding.rvOrder.addOnScrollListener(
        new MyScrollListener(layoutManager) {
          @Override
          protected void loadMoreItems() {
            EcomUtil.printLog(
                "exe" + "mNotifica   " + orderDetails.size() + "mPenCount" + mPenCount);
            if (orderDetails.size() < mPenCount) {
              EcomUtil.printLog("exe" + "sizeWhileCalling" + orderDetails.size() + "LIMIT " + (
                  orderDetails.size() + LIMIT) + "mPenCount" + mPenCount);
              presenter.getDriverNotAssignOrderApi(
                  orderDetails.size(),
                  LIMIT);
            }
          }

          @Override
          public boolean isLastPage() {
            return presenter.isLoading();
          }

          @Override
          public boolean isLoading() {
            return presenter.isLoading();
          }
        });
    mBinding.clNoOrders.setVisibility(
        this.orderDetails != null && this.orderDetails.size() > ZERO ? View.GONE : View.VISIBLE);
    PreOrderFragment parentFrag = ((PreOrderFragment) getParentFragment());
    if (parentFrag != null) {
      parentFrag.setDriverNotAssignTabCount(driverCount);
    }
  }

  @Override
  public void setListData(ArrayList<Data> orders, int status, boolean isCityLogin) {
    if (orders != null && orders.size() > ZERO) {
      orderDetails.addAll(orders);
      orderAdapter.setStatus(status, isCityLogin);
    } else {
      orderDetails.clear();
    }
    orderAdapter.notifyDataSetChanged();
  }

  @Override
  public void setView() {
  }

  @Override
  public void showMessage(String statusMsg) {
    if (getActivity() != null) {
      getActivity().runOnUiThread(() -> {
        Toast.makeText(getActivity(), statusMsg, Toast.LENGTH_LONG).show();
      });
    }
  }

  @Override
  public void updateOrdersList(String searchedText) {
    orderAdapter.getFilter().filter(searchedText);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }

  @Override
  public void afterTextChanged(Editable s) {
    orderAdapter.getFilter().filter(s.toString());
  }
}
