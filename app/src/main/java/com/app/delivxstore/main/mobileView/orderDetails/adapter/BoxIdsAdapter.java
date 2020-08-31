package com.app.delivxstore.main.mobileView.orderDetails.adapter;

import static com.app.delivxstore.utility.VariableConstants.FOUR;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemBagBinding;
import com.app.ecomstore.printlabel.LabelBags;
import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class BoxIdsAdapter extends RecyclerView.Adapter<BoxIdsAdapter.ViewHolder> {
  private ArrayList<LabelBags> mBoxIds;

  public BoxIdsAdapter(ArrayList<LabelBags> boxIds) {
    mBoxIds = boxIds;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemBagBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(viewGroup.getContext()),
        R.layout.item_bag, viewGroup, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    String boxId = mBoxIds.get(position).getBadId();
    viewHolder.mBinding.tvBagId.setText(
        String.format("#...%s", boxId.substring(boxId.length() - FOUR)));
  }

  @Override
  public int getItemCount() {
    return mBoxIds != null ? mBoxIds.size() : ZERO;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ItemBagBinding mBinding;

    public ViewHolder(@NonNull ItemBagBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
