package com.app.delivxstore.main.wallet;

import static com.app.delivxstore.utility.VariableConstants.FETCH_SIZE;
import static com.app.delivxstore.utility.VariableConstants.IS_WALLET_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.ONE;
import static com.app.delivxstore.utility.VariableConstants.PAGE_STATE;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentWalletBinding;
import com.app.delivxstore.main.wallet.adapter.TransactionsAdapter;
import com.app.delivxstore.main.wallet.payment.PaymentActivity;
import com.app.delivxstore.main.wallet.response.TransactionItem;
import com.app.delivxstore.main.wallet.response.WalletDataItem;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Inject;

/*shows screen for wallet*/
public class WalletFragment extends DaggerFragment
    implements WalletContract.WalletView, View.OnClickListener {
  @Inject
  FontUtils fontUtils;
  @Inject
  WalletContract.Presenter presenter;
  private FragmentWalletBinding mBinding;
  private int mTxnType;
  private TransactionBottomSheet mTransactionBottomSheet;

  @Inject
  WalletFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = FragmentWalletBinding.inflate(inflater);
    presenter.attachView(this);
    presenter.getWalletDetails();
    initializeViews();
    pagination();
    return mBinding.getRoot();
  }

  /*initialize the views*/
  private void initializeViews() {
    mBinding.tvWithDrawWallet.setOnClickListener(this);
    mBinding.tvTransDropDown.setOnClickListener(this);
    mTransactionBottomSheet = new TransactionBottomSheet(presenter);
  }

  /*handles pagination*/
  private void pagination() {
    mBinding.rvTransactions.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int itemCount = 0;
        if (manager != null) {
          itemCount = manager.getItemCount();
        }
        if (!recyclerView.canScrollVertically(ONE) && newState == RecyclerView.SCROLL_STATE_IDLE) {
          if (itemCount % FETCH_SIZE == ZERO && PAGE_STATE != null) {
            presenter.getWalletTransactions(mTxnType);
          }
        }
      }

      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    if (IS_WALLET_UPDATED) {
      IS_WALLET_UPDATED = false;
      mBinding.tvTransDropDown.setVisibility(View.VISIBLE);
      presenter.getWalletDetails();
    }
  }

  @Override
  public void showProgress() {
    mBinding.pbWallet.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbWallet.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow().clearFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tvTransDropDown:
        if (!mTransactionBottomSheet.isAdded()) {
          mTransactionBottomSheet.show(getFragmentManager(), WalletFragment.class.getSimpleName());
        }
        break;
      case R.id.tvWithDrawWallet:
        startActivity(new Intent(getActivity(), PaymentActivity.class));
        break;
    }
  }

  @Override
  public void showNoTransactions() {
    mBinding.tvTransDropDown.setVisibility(View.GONE);
    mBinding.ivNoTrans.setVisibility(View.VISIBLE);
    mBinding.tvNoTrans.setVisibility(View.VISIBLE);
  }

  @Override
  public void error(String message) {
    Utility.toastMessage(getActivity(), message);
  }

  @Override
  public void setValues(WalletDataItem data) {
    if (data != null && data.getBalance() != null) {
      mBinding.tvWalletAmount.setText(String.format("%s %s", data.getCurrency(),
          Utility.getFormattedPrice(data.getBalance())));
    } else {
      mBinding.tvWalletAmount.setText(getString(R.string.defaultAmount));
    }
  }

  @Override
  public void updateAdapter(ArrayList<TransactionItem> transactionItemArrayList,
      String transactionHeading, int txnType) {
    mTxnType = txnType;
    mBinding.rvTransactions.setLayoutManager(new LinearLayoutManager(getActivity()));
    TransactionsAdapter adapter = new TransactionsAdapter();
    mBinding.rvTransactions.setAdapter(adapter);
    mBinding.ivNoTrans.setVisibility(transactionItemArrayList.size() == ZERO ?
        View.VISIBLE : View.GONE);
    mBinding.tvNoTrans.setVisibility(transactionItemArrayList.size() == ZERO ?
        View.VISIBLE : View.GONE);
    mBinding.rvTransactions.setVisibility(transactionItemArrayList.size() == ZERO ? View.GONE :
        View.VISIBLE);
    adapter.setListValue(transactionItemArrayList);
    adapter.notifyDataSetChanged();
    mBinding.tvTransDropDown.setText(transactionHeading);
  }
}
