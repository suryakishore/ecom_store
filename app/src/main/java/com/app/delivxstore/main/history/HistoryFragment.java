package com.app.delivxstore.main.history;

import static com.app.ecomstore.util.EcomConstants.BUFFERING_TIME;
import static com.app.ecomstore.util.EcomConstants.END;
import static com.app.ecomstore.util.EcomConstants.HISTORY_FILTER;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.START;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.THOUSAND;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.databinding.FragmentHistoryBinding;
import com.app.delivxstore.main.history.adapter.HistoryAdapter;
import com.app.delivxstore.main.history.historydetails.HistoryDetailsActivity;
import com.app.delivxstore.main.history.model.HistoryData;
import com.app.delivxstore.main.home.tabView.orders.customCalender.CustomCalenderActivity;
import com.app.delivxstore.utility.MyScrollListener;
import com.app.delivxstore.utility.RxTextView;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/*show screen for order history*/
public class HistoryFragment extends DaggerFragment implements HistoryContract.HistoryView {
  @Inject
  HistoryContract.Presenter presenter;
  private FragmentHistoryBinding mBinding;
  private HistoryAdapter mHistoryAdapter;
  private LinearLayoutManager mLinearLayoutManager;
  private ArrayList<HistoryData> mHistoryData = new ArrayList<>();
  private int mPenCount;
  private String mSearch = "", mOrderTime = "";

  @Inject
  public HistoryFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = FragmentHistoryBinding.inflate(inflater);
    presenter.attachView(this);
    initData();
    presenter.getOrderHistory(ZERO, LIMIT, mSearch, mOrderTime);
    return mBinding.getRoot();
  }

  /**
   * initialize adapter
   */
  private void initData() {
    mHistoryAdapter = new HistoryAdapter(this, mHistoryData);
    mBinding.rvHistory.setHasFixedSize(true);
    mLinearLayoutManager = new LinearLayoutManager(getActivity());
    mBinding.rvHistory.setLayoutManager(mLinearLayoutManager);
    mBinding.rvHistory.setAdapter(mHistoryAdapter);
    mBinding.rvHistory.addOnScrollListener(
        new MyScrollListener(mLinearLayoutManager) {
          @Override
          protected void loadMoreItems() {
            if (mHistoryData.size() < mPenCount) {
              EcomUtil.printLog("exe" + "sizeWhileCalling" + mHistoryData.size() + "LIMIT " + (
                  mHistoryData.size() + LIMIT) + "mPenCount" + mPenCount);
              presenter.getOrderHistory(
                  mHistoryData.size(),
                  LIMIT, mSearch, mOrderTime);
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
    RxTextView.textChanges(mBinding.etSearch)
        .debounce(BUFFERING_TIME, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new io.reactivex.Observer<CharSequence>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(CharSequence charSequence) {
            mSearch = charSequence.toString();
            presenter.getOrderHistory(
                ZERO, LIMIT, mSearch, mOrderTime);
          }

          @Override
          public void onError(Throwable e) {
          }

          @Override
          public void onComplete() {
          }
        });
  }

  @Override
  public void showProgress() {
    mBinding.pbHistory.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbHistory.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow()
        .clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void showError(String message) {
    Utility.toastMessage(getActivity(), message);
  }

  @Override
  public void setData(ArrayList<HistoryData> data, int count) {
    mPenCount = count;
    mHistoryData.clear();
    mHistoryData.addAll(data);
    mHistoryAdapter.notifyDataSetChanged();
  }

  @Override
  public void setData(ArrayList<HistoryData> data) {
    mHistoryData.addAll(data);
    mHistoryAdapter.notifyDataSetChanged();
  }

  /*opens history order details screen*/
  public void goToHistoryDetails(HistoryData historyData) {
    Intent intent = new Intent(getActivity(), HistoryDetailsActivity.class);
    Bundle bundle = new Bundle();
    bundle.putString(STORE_ORDER_ID, historyData.getStoreOrderId());
    bundle.putString(MASTER_ORDER_ID, historyData.getMasterOrderId());
    intent.putExtras(bundle);
    startActivity(intent);
  }

  /**
   * This method was used to open the custom callender activity.
   */
  public void onClickFilter() {
    Intent intent = new Intent(getActivity(), CustomCalenderActivity.class);
    startActivityForResult(intent, HISTORY_FILTER);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null && resultCode == Activity.RESULT_OK) {
      if (requestCode == HISTORY_FILTER) {
        String start = data.getStringExtra(START);
        String end = data.getStringExtra(END);
        EcomUtil.printLog("exe" + "startDate" + start + "end" + end);
        if (start != null && !start.isEmpty() && end != null && !end.isEmpty()) {
          DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          try {
            Date dateStart = (Date) formatter.parse(start);
            Date dateEnd = (Date) formatter.parse(end);
            assert dateStart != null;
            assert dateEnd != null;
            mOrderTime = String.format("%d-%d", dateStart.getTime() / THOUSAND,
                dateEnd.getTime() / THOUSAND);
            EcomUtil.printLog("exe" + "mOrderTime" + mOrderTime);
            presenter.getOrderHistory(ZERO, LIMIT, mSearch, mOrderTime);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        } else {
          mOrderTime = "";
          EcomUtil.printLog("exe" + "mOrderTime" + mOrderTime);
          presenter.getOrderHistory(ZERO, LIMIT, mSearch, mOrderTime);
        }
      }
    }
  }
}
