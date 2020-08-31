/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.adapters;

import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemCountBinding;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;

/**
 * Adapter for showing the quantity of item
 */
public class CountRecyclerAdapter extends
    RecyclerView.Adapter<CountRecyclerAdapter.ViewHolderCount> {
  private Context mContext;
  private int mCount;
  private int mPosition = ZERO;
  private SelectItem mSelectItem;

  public CountRecyclerAdapter(int count, SelectItem selectItem) {
    this.mCount = count;
    this.mSelectItem = selectItem;
    this.mPosition = count-1;
  }

  @NonNull
  @Override
  public CountRecyclerAdapter.ViewHolderCount onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View view;
    mContext = parent.getContext();
    ItemCountBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_count, parent, false);
    return new CountRecyclerAdapter.ViewHolderCount(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull CountRecyclerAdapter.ViewHolderCount viewHolder, int i) {
    viewHolder.mItemCountBinding.tvItemCountValue.setText(
        String.format("%d", viewHolder.getAdapterPosition() + ONE));
    viewHolder.mItemCountBinding.viewItemCountLeft.setVisibility(
        (viewHolder.getAdapterPosition() != ZERO && viewHolder.getAdapterPosition() < mCount
            ? View.GONE : View.VISIBLE));
    viewHolder.mItemCountBinding.tvItemCountValue.setTextAppearance(
        mContext,
        mPosition == viewHolder.getAdapterPosition() ? R.style.Text_20sp_White_MontserratBold
            : R.style.Text_20sp_BlueZodiac_MontserratRegular);
    viewHolder.mItemCountBinding.clItemCountContainer.setBackgroundColor(
        mPosition == viewHolder.getAdapterPosition() ?
            mContext.getResources().getColor(R.color.colorPrimary)
            : mContext.getResources().getColor(R.color.grayShade));
    viewHolder.itemView.setOnClickListener(v -> {
      if (mPosition != viewHolder.getAdapterPosition()) {
        mPosition = viewHolder.getAdapterPosition();
        notifyDataSetChanged();
        mSelectItem.onSelectItem(mPosition);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mCount;
  }

  public class ViewHolderCount extends RecyclerView.ViewHolder {
    ItemCountBinding mItemCountBinding;

    public ViewHolderCount(@NonNull ItemCountBinding itemView) {
      super(itemView.getRoot());
      mItemCountBinding = itemView;
    }
  }
}
