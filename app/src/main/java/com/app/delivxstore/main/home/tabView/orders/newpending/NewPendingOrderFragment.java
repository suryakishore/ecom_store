package com.app.delivxstore.main.home.tabView.orders.newpending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentOrderBinding;
import dagger.android.support.DaggerFragment;
import javax.inject.Inject;

/**
 * this fragment will show the in dispatch orders.
 */
public class NewPendingOrderFragment extends DaggerFragment implements
    newpendingConrtact.PreOrderView {
  @Inject
  newpendingConrtact.PreOrderPresenter presenter;
  private FragmentOrderBinding mBinding;
  private NewPendingPagerAdapter mPagerAdapter;

  @Inject
  public NewPendingOrderFragment() {
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
   * initializing the views.
   */
  private void initView() {
    presenter.attachView(this);
    mBinding.llMain.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
    presenter.getView(getArguments());
    mBinding.tbAssignDriver.setVisibility(View.VISIBLE);
    mBinding.vpAssignTabViewPager.setVisibility(View.VISIBLE);
    mBinding.rlSearchOrder.setVisibility(View.GONE);
    mPagerAdapter = new NewPendingPagerAdapter(getChildFragmentManager());
    mBinding.vpAssignTabViewPager.setAdapter(mPagerAdapter);
    mBinding.tbAssignDriver.setupWithViewPager(mBinding.vpAssignTabViewPager);
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
  public void showMessage(String statusMsg) {
    if (getActivity() != null) {
      getActivity().runOnUiThread(() -> {
        Toast.makeText(getActivity(), statusMsg, Toast.LENGTH_LONG).show();
      });
    }
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  /**
   * set the assign driver tab count.
   *
   * @param tabCount assigned tab count.
   */
  public void setNewTabCount(int tabCount) {
    mPagerAdapter.setNewCount(String.valueOf(tabCount));
  }

  /**
   * set the not assigned tab count.
   *
   * @param tabCount not assigned tab count.
   */
  public void setPendingTabCount(int tabCount) {
    mPagerAdapter.setPendingCount(String.valueOf(tabCount));
  }
}




