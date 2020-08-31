package com.app.delivxstore.main.home.tabView.orders.inAssign;

import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentOrderBinding;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.tabView.orders.OrderAdapter;
import com.app.delivxstore.utility.MyScrollListener;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class InAssignFragment extends DaggerFragment implements InAssignContract.ViewOperations,
    TextWatcher {
  @Inject
  public InAssignContract.PresenterOperations presenter;
  private ArrayList<Data> mOrderDetails = new ArrayList<>();
  private OrderAdapter orderAdapter;
  private FragmentOrderBinding mBinding;
  private int mPenCount;

  @Inject
  public InAssignFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
    presenter.subscribeFilterData();
    initView();
    return mBinding.getRoot();
  }

  /**
   * initialization of view
   */
  private void initView() {
    presenter.attachView(this);
    mBinding.llMain.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
    presenter.getView(getArguments());
    mBinding.search.addTextChangedListener(this);
    mOrderDetails.clear();
    orderAdapter = new OrderAdapter(mOrderDetails);
    GridLayoutManager layoutManager;
    layoutManager = new GridLayoutManager(getActivity(),
        Utility.isTablet(getActivity()) ? FIVE : TWO, GridLayoutManager.VERTICAL,
        false);
    mBinding.rvOrder.setLayoutManager(layoutManager);
    mBinding.rvOrder.setItemAnimator(new DefaultItemAnimator());
    mBinding.rvOrder.setAdapter(orderAdapter);
    mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
      assert getParentFragment() != null;
      presenter.getPackedOrderApi(ZERO, LIMIT);
      mBinding.swipeRefreshLayout.setRefreshing(FALSE);
    });
  }

  @Override
  public void setStoreName(String storeName) {
  }

  @Override
  public void setTitle(boolean tablet, String type) {
  }

  @Override
  public void setListData(ArrayList<Data> orders, int status, int penCount, boolean isCityLogin) {
    mPenCount = penCount;
    if (orders != null && orders.size() > ZERO) {
      mOrderDetails.clear();
      mOrderDetails.addAll(orders);
      orderAdapter.setStatus(status, isCityLogin);
    } else {
      mOrderDetails.clear();
    }
    GridLayoutManager layoutManager;
    layoutManager = new GridLayoutManager(getActivity(),
        Utility.isTablet(getActivity()) ? FIVE : TWO, GridLayoutManager.VERTICAL,
        false);
    mBinding.rvOrder.setLayoutManager(layoutManager);
    orderAdapter.notifyDataSetChanged();
    mBinding.rvOrder.addOnScrollListener(
        new MyScrollListener(layoutManager) {
          @Override
          protected void loadMoreItems() {
            EcomUtil.printLog(
                "exe" + "mNotifica   " + mOrderDetails.size() + "mPenCount" + mPenCount);
            if (mOrderDetails.size() < mPenCount) {
              EcomUtil.printLog("exe" + "sizeWhileCalling" + mOrderDetails.size() + "LIMIT " + (
                  mOrderDetails.size() + LIMIT) + "mPenCount" + mPenCount);
              presenter.getPackedOrderApi(
                  mOrderDetails.size(),
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
    Log.d("exe", "mOrderDetails" + this.mOrderDetails.size());
    mBinding.clNoOrders.setVisibility(
        this.mOrderDetails != null && this.mOrderDetails.size() > ZERO ? View.GONE : View.VISIBLE);
  }

  @Override
  public void setListData(ArrayList<Data> orders, int status, boolean isCityLogin) {
    if (orders != null && orders.size() > ZERO) {
      mOrderDetails.addAll(orders);
      orderAdapter.setStatus(status, isCityLogin);
    } else {
      mOrderDetails.clear();
    }
    orderAdapter.notifyDataSetChanged();
  }

  @Override
  public void setView() {
  }

  @Override
  public void showMessage(String msg) {
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
  public void onPause() {
    super.onPause();
    orderAdapter.clearAll();
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
