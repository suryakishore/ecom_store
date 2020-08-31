package com.app.delivxstore.main.wallet.adapter;


import static com.app.delivxstore.utility.VariableConstants.CREDIT_TRANSACTION;
import static com.app.delivxstore.utility.VariableConstants.ONE;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemWalletTransactionsBinding;
import com.app.delivxstore.main.wallet.response.TransactionItem;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;

/*binds transactions data to adapter using recycler view*/
public class TransactionsAdapter extends RecyclerView.Adapter {
  private ItemWalletTransactionsBinding mBinding;
  private Context mContext;
  private ArrayList<TransactionItem> mTransactionItemArrayList;

  public TransactionsAdapter() {
  }

  /*initialize list for transaction*/
  public void setListValue(ArrayList<TransactionItem> transactionItemArrayList) {
    mTransactionItemArrayList = transactionItemArrayList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    mBinding = ItemWalletTransactionsBinding.inflate(LayoutInflater.from(parent.getContext()));
    return new RecyclerView.ViewHolder(mBinding.getRoot()) {
      @Override
      public String toString() {
        return super.toString();
      }
    };
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    TransactionItem transactionItem = mTransactionItemArrayList.get(position);
    mBinding.view1.setVisibility(mTransactionItemArrayList.size() == ONE || position == mTransactionItemArrayList.size() - ONE
        ? View.GONE : View.VISIBLE);
    mBinding.tvTransId.setText(transactionItem.getTxnid());
    mBinding.tvTransType.setText(transactionItem.getDescription());
    mBinding.ivTransType.setImageDrawable(transactionItem.getTxntype() == CREDIT_TRANSACTION
        ? mContext.getResources().getDrawable(R.drawable.ic_credit, null)
        : mContext.getResources().getDrawable(R.drawable.ic_debit, null));
    mBinding.tvTransTime.setText(Utility.convertIntoLongTime(transactionItem.getTxntimestamp()));
    mBinding.tvTransAmount.setText(String.format("%s %s", transactionItem.getCurrency(),
        transactionItem.getAmount()));
  }

  @Override
  public int getItemCount() {
    return mTransactionItemArrayList == null ? ZERO : mTransactionItemArrayList.size();
  }
}
