package com.app.delivxstore.main.mobileView.orderDetails.adapter;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemAddonsRowBinding;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOnGroup;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class AddOnsAdapter extends RecyclerView.Adapter<AddOnsAdapter.ViewHolder> {
  private ArrayList<AddOnGroup> mAddOnGroupArrayList;
  private String mCurrencySymbol;

  public AddOnsAdapter(ArrayList<AddOnGroup> addOnGroupArrayList, String currencySymbol) {
    this.mAddOnGroupArrayList = addOnGroupArrayList;
    this.mCurrencySymbol = currencySymbol;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemAddonsRowBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(viewGroup.getContext()),
        R.layout.item_addons_row, viewGroup, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    AddOnGroup addOnGroup = mAddOnGroupArrayList.get(i);
    viewHolder.mBinding.tvItemName.setText(addOnGroup.getName());
    viewHolder.mBinding.etQuantity.setText("1");
    viewHolder.mBinding.etUnitPrice.setText(addOnGroup.getPrice());
    viewHolder.mBinding.tvCurrencySymbol.setText(String.format("%s ", mCurrencySymbol));
    viewHolder.mBinding.tvOrderListTotalPrice.setText(String.format("%s %s", mCurrencySymbol,
        Utility.roundOfDoubleValue(addOnGroup.getPrice())));
  }

  @Override
  public int getItemCount() {
    return mAddOnGroupArrayList != null ? mAddOnGroupArrayList.size() : ZERO;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ItemAddonsRowBinding mBinding;

    public ViewHolder(@NonNull ItemAddonsRowBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
