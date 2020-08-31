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

package com.app.delivxstore.main.home.tabView.orders.newOrders;

import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
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
import androidx.recyclerview.widget.GridLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentOrderBinding;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.tabView.orders.OrderAdapter;
import com.app.delivxstore.main.home.tabView.orders.newpending.NewPendingOrderFragment;
import com.app.delivxstore.utility.MyScrollListener;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * New order page
 * view
 */
public class NewOrdersFragment extends DaggerFragment implements NewOrderContract.ViewOperations,
    TextWatcher {
  @Inject
  public NewOrderContract.PresenterOperations presenter;
  private ArrayList<Data> mNewOrderList = new ArrayList<>();
  private OrderAdapter mOrderAdapter;
  private FragmentOrderBinding mBinding;
  private int mPenCount;

  @Inject
  public NewOrdersFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
    presenter.subscribeFilterData();
    initView();
    return mBinding.getRoot();
  }

  @Override
  public void setStoreName(String storeName) {
  }

  /**
   * initialization of the views.
   */
  private void initView() {
    presenter.attachView(this);
    mBinding.llMain.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
    presenter.getView(getArguments());
    mBinding.search.addTextChangedListener(this);
    mNewOrderList.clear();
    mOrderAdapter = new OrderAdapter(mNewOrderList);
    mBinding.rvOrder.setLayoutManager(
        new GridLayoutManager(getContext(), Utility.isTablet(getActivity()) ? FIVE : TWO,
            GridLayoutManager.VERTICAL, false));
    mBinding.rvOrder.setAdapter(mOrderAdapter);
    mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
      assert getParentFragment() != null;
      presenter.getNewOrderApi(ZERO, LIMIT);
      mBinding.swipeRefreshLayout.setRefreshing(false);
    });
  }

  @Override
  public void showMessage(final String statusMsg) {
    if (getActivity() != null) {
      getActivity().runOnUiThread(() -> {
        Toast.makeText(getActivity(), statusMsg, Toast.LENGTH_LONG).show();
      });
    }
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
  public void setListData(ArrayList<Data> newOrderList, int status, int penCount, int newCount,
      boolean isCityLogin) {
    mPenCount = penCount;
    mBinding.swipeRefreshLayout.setRefreshing(FALSE);
    if (newOrderList != null && newOrderList.size() > ZERO) {
      this.mNewOrderList.clear();
      this.mNewOrderList.addAll(newOrderList);
      mOrderAdapter.setStatus(status, isCityLogin);
    } else {
      this.mNewOrderList.clear();
    }
    GridLayoutManager layoutManager;
    layoutManager = new GridLayoutManager(getActivity(),
        Utility.isTablet(getActivity()) ? FIVE : TWO, GridLayoutManager.VERTICAL,
        false);
    mBinding.rvOrder.setLayoutManager(layoutManager);
    mOrderAdapter.notifyDataSetChanged();
    mBinding.rvOrder.addOnScrollListener(
        new MyScrollListener(layoutManager) {
          @Override
          protected void loadMoreItems() {
            EcomUtil.printLog(
                "exe" + "mNotifica   " + mNewOrderList.size() + "mPenCount" + mPenCount);
            if (mNewOrderList.size() < mPenCount) {
              EcomUtil.printLog("exe" + "sizeWhileCalling" + mNewOrderList.size() + "LIMIT " + (
                  mNewOrderList.size() + LIMIT) + "mPenCount" + mPenCount);
              presenter.getNewOrderApi(
                  mNewOrderList.size(),
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
        this.mNewOrderList != null && this.mNewOrderList.size()> ZERO ? View.GONE : View.VISIBLE);
    if (presenter.getStoreType() == PHARMACY) {
      NewPendingOrderFragment parentFrag = ((NewPendingOrderFragment) getParentFragment());
      if (parentFrag != null) {
        parentFrag.setNewTabCount(newCount);
      }
    }
  }

  @Override
  public void setListData(ArrayList<Data> newOrderList, int status, boolean isCityLogin) {
    if (newOrderList != null && newOrderList.size() > ZERO) {
      mNewOrderList.addAll(newOrderList);
      mOrderAdapter.setStatus(status, isCityLogin);
    } else {
      mNewOrderList.clear();
    }
    mOrderAdapter.notifyDataSetChanged();
  }

  @Override
  public void setView() {
  }

  @Override
  public void onPause() {
    super.onPause();
    mOrderAdapter.clearAll();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }

  @Override
  public void afterTextChanged(Editable s) {
    mOrderAdapter.getFilter().filter(s.toString());
  }
}
