package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.adapters;

import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.util.Log;
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
  private ArrayList<AddOnGroup> addOnGroupArrayList;
  private String currencySymbol;

  public AddOnsAdapter(ArrayList<AddOnGroup> addOnGroupArrayList, String currencySymbol) {
    this.addOnGroupArrayList = addOnGroupArrayList;
    this.currencySymbol = currencySymbol;
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
    Log.d("exe", "position" + i);
    AddOnGroup addOnGroup = addOnGroupArrayList.get(i);
    viewHolder.mBinding.tvItemName.setText(addOnGroup.getName());
    viewHolder.mBinding.etQuantity.setText("1");
    viewHolder.mBinding.etUnitPrice.setText(addOnGroup.getPrice());
    viewHolder.mBinding.tvCurrencySymbol.setText(String.format("%s ", currencySymbol));
    viewHolder.mBinding.tvOrderListTotalPrice.setText(
        String.format("%s %s", currencySymbol, Utility.roundOfDoubleValue(addOnGroup.getPrice())));
  }

  @Override
  public int getItemCount() {
    return addOnGroupArrayList!=null?addOnGroupArrayList.size():ZERO;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ItemAddonsRowBinding mBinding;

    public ViewHolder(@NonNull ItemAddonsRowBinding itemView) {
      super(itemView.getRoot());
      mBinding = itemView;
    }
  }
}
