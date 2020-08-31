package com.app.delivxstore.main.wallet.adapter;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemAccountBinding;
import com.app.delivxstore.main.wallet.accountdetails.AccountListFragment;
import com.app.delivxstore.main.wallet.stripe.model.response.AccountData;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;


/*binds account data to adapter to show multiple accounts*/
public class AccountListAdapter extends RecyclerView.Adapter implements View.OnClickListener {
  private AccountListFragment mAccountListFragment;
  private ArrayList<AccountData> mAccountDataList;
  private int mPosition;
  private Context mContext;

  public AccountListAdapter(AccountListFragment accountListFragment,
                            ArrayList<AccountData> accountDataList) {
    mAccountListFragment = accountListFragment;
    mAccountDataList = accountDataList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_account, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MyViewHolder) {
      final MyViewHolder viewHolder = (MyViewHolder)holder;
      AccountData accountData = mAccountDataList.get(holder.getAdapterPosition());
      viewHolder.binding.rbSingleOperator.setChecked(accountData.isSelected());
      viewHolder.binding.tvAccountNumber.setText(accountData.getAccount());
      viewHolder.binding.tvBankCode.setText(accountData.getBankName());
      viewHolder.binding.rbSingleOperator.setOnClickListener(view -> {
        setView(holder.getAdapterPosition());
        mPosition = position;
        mAccountListFragment.getSelectedAccountDetails(mAccountDataList.get(position));
        notifyDataSetChanged();
      });
      viewHolder.binding.ivRightArrow.setOnClickListener(this);
    }
  }

  /*set selected view*/
  private void setView(int position) {
    mAccountDataList.get(position).setSelected(!mAccountDataList.get(position).isSelected());
    if (mAccountDataList.get(position).isSelected()) {
      removeOtherSelectedViews(mAccountDataList, position);
    }
  }

  /*remove other selected views*/
  private void removeOtherSelectedViews(ArrayList<AccountData> accountDataArrayList, int position) {
    for (int i = 0; i < accountDataArrayList.size(); i++) {
      if (i != position) {
        accountDataArrayList.get(i).setSelected(false);
      }
    }
  }

  @Override
  public int getItemCount() {
    return mAccountDataList == null ? ZERO : mAccountDataList.size();
  }

  @Override
  public void onClick(View view) {
    if (mAccountDataList.get(mPosition).isSelected()) {
      mAccountListFragment.showDetails(mAccountDataList);
    } else {
      Utility.toastMessage(mContext, mContext.getString(R.string.selectAnAccount));
    }
  }

  /*holds view references*/
  public static class MyViewHolder extends RecyclerView.ViewHolder {
    ItemAccountBinding binding;

    MyViewHolder(View itemView) {
      super(itemView);
      binding = ItemAccountBinding.bind(itemView);
    }
  }
}
