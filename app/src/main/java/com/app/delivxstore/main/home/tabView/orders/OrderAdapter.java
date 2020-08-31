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

package com.app.delivxstore.main.home.tabView.orders;

import static com.app.ecomstore.util.EcomConstants.CANCELLED;
import static com.app.ecomstore.util.EcomConstants.CHECKED;
import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PACKAGE_ID;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.STATUS;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemOrderBinding;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.models.HomeProducts;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.DialogOrderDetailsActivity;
import com.app.delivxstore.main.mobileView.orderDetails.OrderDetailsForMobile;
import com.app.delivxstore.utility.TimeFormatter;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.Predicate;

/**
 * Order list adapter to
 * show all the orders
 */
public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
    Filterable {
  private static final int ORDER = 0;
  public boolean mIsFromPreOrder;
  private Context mContext;
  private ArrayList<Data> mOrderData;
  private ArrayList<Data> mFilteredList;
  private Handler mHandler = new Handler();
  private GridLayoutManager.SpanSizeLookup mOnSpanSizeLookup = null;
  private GridLayoutManager mLayoutManager;
  private int mCurrentMonth;
  private int mStatus;
  private boolean mIsCityLogin;

  public OrderAdapter(ArrayList<Data> mOrderData) {
    initSpanLookUp();
    this.mOrderData = mOrderData;
    this.mFilteredList = mOrderData;
    Calendar c = Calendar.getInstance();
    mCurrentMonth = c.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * this method will identify from which fragment this adapter class loaded.
   *
   * @param status it is an integer to identify different tab titles.
   */
  public void setStatus(int status, boolean isCityLogin) {
    mStatus = status;
    mIsCityLogin = isCityLogin;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    ItemOrderBinding itemOrderBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_order, parent, false);
    return new ViewHolderOrder(itemOrderBinding);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder.getItemViewType() == ORDER) {
      ViewHolderOrder holder = (ViewHolderOrder) viewHolder;
      holder.mItemOrderBinding.tvOrderListOrderId.setText(mStatus >= PACKED && mStatus != CHECKED ?
          String.format("%s :%s", mContext.getResources().getString(R.string.packId),
              mFilteredList.get(position).getPackageId())
          : String.format("%s :%s", mContext.getResources().getString(R.string.id),
              mIsCityLogin ? mFilteredList.get(
                  position).getMasterOrderId() : mFilteredList.get(position).getStoreOrderId()));
      holder.mItemOrderBinding.tvOrderListMemberName.setText(mFilteredList.get(
          position).getCustomerDetails().getFirstName());
      holder.mItemOrderBinding.tvOrderListCategoryType.setText(
          mFilteredList.get(position).getOrderTypeMsg());
      if (mFilteredList.get(position).getProducts() != null) {
        if (mStatus == CHECKED) {
          Predicate<HomeProducts> conditionCancel =
              orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                  == CANCELLED;
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mFilteredList.get(position).getProducts().removeIf(conditionCancel);
          }
          holder.mItemOrderBinding.tvOrderListTotalPrice.setText(
              String.format("%d %s", mFilteredList.get(
                  position).getProducts().size(), mFilteredList.get(position).getProducts().size()
                  > ONE
                  ? mContext.getResources().getString(R.string.products)
                  : mContext.getResources().getString(R.string.product)));
        } else {
          holder.mItemOrderBinding.tvOrderListTotalPrice.setText(
              String.format("%d %s", mFilteredList.get(
                  position).getProducts().size(), mFilteredList.get(position).getProducts().size()
                  > ONE
                  ? mContext.getResources().getString(R.string.products)
                  : mContext.getResources().getString(R.string.product)));
        }
      } else if (mFilteredList.get(position).getProductOrderIds() != null && mStatus >= PACKED) {
        holder.mItemOrderBinding.tvOrderListTotalPrice.setText(
            String.format("%d %s", mFilteredList.get(
                position).getProductOrderIds().size(), mFilteredList.get(
                position).getProductOrderIds().size() > ONE ? mContext.getResources().getString(
                R.string.products) : mContext.getResources().getString(R.string.product)));
      }
      if (mFilteredList.get(position).getStatus() != null) {
        holder.mItemOrderBinding.tvOrderListOrderTime.setText(
            TimeFormatter.getDateTimeStamp(mFilteredList.get(
                position).getStatus().getUpdatedOnTimeStamp()));
        holder.mItemOrderBinding.tvOrderListOrderDate.setText(Utility.timeStampToTime(
            Integer.parseInt(mFilteredList.get(position).getStatus().getUpdatedOnTimeStamp())
        ));
      }
      holder.mItemOrderBinding.cvOrderListMainContainer.setOnClickListener(view -> {
        Bundle bundle = new Bundle();
        bundle.putString(STORE_ORDER_ID, mFilteredList.get(position).getStoreOrderId());
        bundle.putString(MASTER_ORDER_ID, mFilteredList.get(position).getMasterOrderId());
        bundle.putString(PACKAGE_ID, mFilteredList.get(position).getPackageId());
        bundle.putInt(STATUS, mStatus);
        Intent intent = new Intent(mContext,
            Utility.isTablet(mContext) ? DialogOrderDetailsActivity.class
                : OrderDetailsForMobile.class);
        intent.putExtras(bundle);
        EcomUtil.printLog("exe" + "orderID" + mFilteredList.get(position).getPackageId());
        mContext.startActivity(intent);
      });
    }
  }

  /**
   * this is the common method we are using for five fragments for every fragment we are removing
   * the handler.
   */
  public void clearAll() {
    mHandler.removeCallbacksAndMessages(null);
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    mLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
    mLayoutManager.setSpanSizeLookup(mOnSpanSizeLookup);
    super.onAttachedToRecyclerView(recyclerView);
  }

  @Override
  public int getItemCount() {
    return mFilteredList != null ? mFilteredList.size() : ZERO;
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
          mFilteredList = mOrderData;
        } else {
          ArrayList<Data> localFilterList = new ArrayList<>();
          for (Data orderDetails : mOrderData) {
            if (orderDetails.getCustomerDetails().getFirstName() != null
                && orderDetails.getCustomerDetails().getFirstName()
                .toLowerCase().contains(charString.toLowerCase())) {
              localFilterList.add(orderDetails);
            } else if (mStatus >= PACKED) {
              if (orderDetails.getPackageId().contains(charString)) {
                localFilterList.add(orderDetails);
              }
            } else {
              if (mIsCityLogin) {
                if (orderDetails.getMasterOrderId().contains(charString)) {
                  localFilterList.add(orderDetails);
                }
              } else {
                if (orderDetails.getStoreOrderId().contains(charString)) {
                  localFilterList.add(orderDetails);
                }
              }
            }
          }
          mFilteredList = localFilterList;
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = mFilteredList;
        return filterResults;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        mFilteredList = (ArrayList<Data>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  /**
   * <h2>init_Span_look_Up</h2>
   * Helper class to set span size for grid items based on orientation and device type
   */
  private void initSpanLookUp() {
    if (mOnSpanSizeLookup == null) {
      mOnSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
          if (Utility.isTablet(ApplicationManager.getInstance())) {
            return FIVE;
          } else {
            return TWO;
          }
        }
      };
    }
  }

  class ViewHolderOrder extends RecyclerView.ViewHolder {
    ItemOrderBinding mItemOrderBinding;

    ViewHolderOrder(ItemOrderBinding itemOrderBinding) {
      super(itemOrderBinding.getRoot());
      mItemOrderBinding = itemOrderBinding;
    }
  }
}
