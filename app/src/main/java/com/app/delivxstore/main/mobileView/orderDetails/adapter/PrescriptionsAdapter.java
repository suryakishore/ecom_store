package com.app.delivxstore.main.mobileView.orderDetails.adapter;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemPrescriptionBinding;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class PrescriptionsAdapter extends RecyclerView.Adapter<PrescriptionsAdapter.ViewHolder> {
  private ArrayList<String> mOrderImages;
  private SelectItem mSelectItem;
  private Context mContext;

  public PrescriptionsAdapter(ArrayList<String> orderImages, SelectItem selectItem) {
    this.mOrderImages = orderImages;
    this.mSelectItem = selectItem;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    mContext = viewGroup.getContext();
    ItemPrescriptionBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(viewGroup.getContext()),
        R.layout.item_prescription, viewGroup, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    if (mOrderImages.get(i) != null) {
      viewHolder.mBinding.progressBar.setVisibility(View.VISIBLE);
      Glide.with(mContext)
          .applyDefaultRequestOptions(new RequestOptions().override(110, 110).centerCrop())
          .load(mOrderImages.get(i).trim())
          .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                Target<Drawable> target, boolean isFirstResource) {
              viewHolder.mBinding.progressBar.setVisibility(View.GONE);
              return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                DataSource dataSource, boolean isFirstResource) {
              viewHolder.mBinding.progressBar.setVisibility(View.GONE);
              return false;
            }
          }).into(viewHolder.mBinding.ivItem);
      viewHolder.mBinding.cvPrescriptionItem.setOnClickListener(v -> mSelectItem.onSelectItem(i));
    }
  }

  @Override
  public int getItemCount() {
    return mOrderImages != null ? mOrderImages.size() : ZERO;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    ItemPrescriptionBinding mBinding;

    public ViewHolder(@NonNull ItemPrescriptionBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
