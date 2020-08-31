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

package com.app.delivxstore.main.home;

import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemFoodBinding;
import com.app.delivxstore.main.home.models.FoodTabs;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.app.ecomstore.util.EcomUtil;
import java.util.ArrayList;

/**
 * Order list adapter to
 * show all the orders
 */
public class TabsOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context mContext;
  private ArrayList<FoodTabs> mFoodTabs;
  private int mSelPosition;
  private int mDisPosition;
  private SelectItem mSelectItem;
  private RecyclerView mRecyclerView;

  public TabsOrderAdapter(ArrayList<FoodTabs> foodTabs, int position, SelectItem selectItem) {
    mFoodTabs = foodTabs;
    mSelPosition = position;
    mDisPosition = mSelPosition;
    this.mSelectItem = selectItem;
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    mRecyclerView = recyclerView;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    EcomUtil.printLog("exe" + "onCreateViewHolder");
    ItemFoodBinding itemFoodBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_food, parent, false);
    return new ViewHolderOrder(itemFoodBinding);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    EcomUtil.printLog("exe" + "onBindViewHolder" + position);
    FoodTabs foodTabs = mFoodTabs.get(position);
    if (foodTabs != null) {
      ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.setText(
          String.format("%s(%s)", foodTabs.getStatusText(), foodTabs.getStatusCount()));
      if (mSelPosition == position) {
        EcomUtil.setCustomDrawable(position,
            true,
            ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId, mContext);
        ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.setSelected(true);
      } else {
        EcomUtil.setCustomDrawable(position,
            false,
            ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId, mContext);
        ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.setSelected(false);
      }
      ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.setOnClickListener(
          v -> {
            if (!((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.isSelected()) {
              mSelPosition = position;
              notifyItemChanged(mDisPosition);
              mDisPosition = mSelPosition;
              ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId.setSelected(TRUE);
              EcomUtil.setCustomDrawable(position, TRUE,
                  ((ViewHolderOrder) viewHolder).mItemOrderBinding.tvOrderListOrderId, mContext);
              mSelectItem.onSelectItem(position);
            }
          });
    }
  }

  /**
   * this method is used to swipe position.
   *
   * @param position integer.
   */
  public void setSwipe(int position) {
    mSelPosition = position;
    notifyItemChanged(mDisPosition);
    mDisPosition = mSelPosition;
    mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(
        R.id.tvOrderListOrderId).setSelected(TRUE);
    EcomUtil.setCustomDrawable(position, TRUE,
        mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(
            R.id.tvOrderListOrderId), mContext);
  }

  @Override
  public int getItemCount() {
    return mFoodTabs != null ? mFoodTabs.size() : ZERO;
  }

  class ViewHolderOrder extends RecyclerView.ViewHolder {
    ItemFoodBinding mItemOrderBinding;

    ViewHolderOrder(ItemFoodBinding itemOrderBinding) {
      super(itemOrderBinding.getRoot());
      EcomUtil.printLog("exe" + "ViewHolderOrder");
      mItemOrderBinding = itemOrderBinding;
    }
  }
}
