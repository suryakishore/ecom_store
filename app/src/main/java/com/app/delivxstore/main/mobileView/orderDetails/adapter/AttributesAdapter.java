package com.app.delivxstore.main.mobileView.orderDetails.adapter;

import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemAttributesBinding;
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes;
import java.util.ArrayList;

/**
 * adapter class for attributes
 */
public class AttributesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context mContext;
  private ArrayList<Attributes> mAttributesArrayList;
  private String mCurrencySymbol;

  public AttributesAdapter(
      ArrayList<Attributes> attributesArrayList) {
    this.mAttributesArrayList = attributesArrayList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    mContext = parent.getContext();
    ItemAttributesBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_attributes, parent, false);
    return new AttributesAdapter.ViewHolderItemAttributes(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    AttributesAdapter.ViewHolderItemAttributes viewHolderItemAttributes =
        (AttributesAdapter.ViewHolderItemAttributes) viewHolder;
    ((ViewHolderItemAttributes) viewHolder).mBinding.textViewAttributesTitle.setText(
        String.format("%s:",
            mAttributesArrayList.get(viewHolderItemAttributes.getAdapterPosition()).getAttrname()));
    ((ViewHolderItemAttributes) viewHolder).mBinding.textViewAttributesValue.setText(
        mAttributesArrayList.get(viewHolderItemAttributes.getAdapterPosition()).getValue());
  }

  @Override
  public int getItemCount() {
    return mAttributesArrayList!=null?mAttributesArrayList.size():ZERO;
  }

  /**
   * <h>setCurrency</h>
   * <p>get the currency symbol</p>
   *
   * @param mCurrencySymbol get the currency symbol
   */
  public void setCurrency(String mCurrencySymbol) {
    this.mCurrencySymbol = mCurrencySymbol;
  }

  public class ViewHolderItemAttributes extends RecyclerView.ViewHolder {
    ItemAttributesBinding mBinding;

    ViewHolderItemAttributes(@NonNull ItemAttributesBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}

