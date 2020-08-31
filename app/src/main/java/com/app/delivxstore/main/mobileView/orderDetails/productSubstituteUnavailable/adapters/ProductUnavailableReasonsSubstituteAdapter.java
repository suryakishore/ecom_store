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

package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.adapters;

import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.delivxstore.R;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductReason;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductSubstituteData;
import java.util.ArrayList;

/**
 * Using this class to show the reasons for product unavailability
 * or the products which can be substituted instead of the selected item
 */
public class ProductUnavailableReasonsSubstituteAdapter extends
    RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context mContext;
  private ArrayList<ProductReason> mReasons;
  private ArrayList<ProductSubstituteData> mProductSubstituteData;
  private int mPosition = ZERO;
  private boolean mIsItemUnavailable = false;
  private String mCurrencySymbol;
  private SelectItem mSelectItem;

  public ProductUnavailableReasonsSubstituteAdapter(
      SelectItem selectItem,
      ArrayList<ProductReason> reasons,
      ArrayList<ProductSubstituteData> substituteProducts) {
    this.mSelectItem = selectItem;
    this.mReasons = reasons;
    this.mProductSubstituteData = substituteProducts;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    mContext = parent.getContext();
    View view;
    if (mIsItemUnavailable) {
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio_button,
          parent, false);
      return new ViewHolderCount(view);
    } else {
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_product,
          parent, false);
      return new ViewHolderItem(view);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    if (mIsItemUnavailable) {
      ViewHolderCount viewHolderCount = (ViewHolderCount) viewHolder;
      viewHolderCount.radioButton_option_reason.setText(
          mReasons.get(viewHolder.getAdapterPosition()).getReason());
      viewHolderCount.radioButton_option_reason.setChecked(
          mPosition != viewHolderCount.getAdapterPosition() ? false : true);
      viewHolderCount.radioButton_option_reason.setOnClickListener(v -> {
        if (mPosition != viewHolder.getAdapterPosition()) {
          mPosition = viewHolder.getAdapterPosition();
          notifyDataSetChanged();
          mSelectItem.onSelectItem(mPosition);
        }
      });
    } else {
      ViewHolderItem viewHolderItem = (ViewHolderItem) viewHolder;
      viewHolderItem.recyclerView_itemDetails_productAttributesAddRmv.setLayoutManager(new
          LinearLayoutManager(
          mContext,
          LinearLayoutManager.HORIZONTAL,
          false));
      ProductAttributesAddRemoveAdapter productAttributesAddRemoveAdapter =
          new ProductAttributesAddRemoveAdapter(
              mContext,
              mProductSubstituteData.get(viewHolder.getAdapterPosition()).getAttributes()
          );
      viewHolderItem.recyclerView_itemDetails_productAttributesAddRmv.setHasFixedSize(true);
      viewHolderItem.recyclerView_itemDetails_productAttributesAddRmv.setAdapter(
          productAttributesAddRemoveAdapter);
      viewHolderItem.textView_itemDetails_productName.setText(
          mProductSubstituteData.get(viewHolder.getAdapterPosition()).getName()
      );
      viewHolderItem.textView_itemDetails_productPrice.setText(
          String.format("%s %s", mCurrencySymbol,
              mProductSubstituteData.get(viewHolder.getAdapterPosition()).getPrice())
      );
    }
  }

  @Override
  public int getItemCount() {
    if (mIsItemUnavailable) {
      return mReasons.size();
    } else {
      return mProductSubstituteData.size();
    }
  }

  /**
   * <h>mIsItemUnavailable</h>
   * <p>get the type tp show reasons or items</p>
   *
   * @param mIsItemUnavailable true if reasons
   */
  public void isItemUnavailable(boolean mIsItemUnavailable) {
    this.mIsItemUnavailable = mIsItemUnavailable;
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

  public class ViewHolderCount extends RecyclerView.ViewHolder {
    @BindView(R.id.radioButton_option_reason)
    RadioButton radioButton_option_reason;

    ViewHolderCount(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public class ViewHolderItem extends RecyclerView.ViewHolder {
    @BindView(R.id.ivItemUnAvailableProductImage)
    ImageView imageView_itemDetails_productImage;
    @BindView(R.id.tvItemUnAvailableProductName)
    TextView textView_itemDetails_productName;
    @BindView(R.id.tvItemUnAvailableProductPrice)
    TextView textView_itemDetails_productPrice;
    @BindView(R.id.recyclerViewItemDetailsProductAttributesAddRmv)
    RecyclerView recyclerView_itemDetails_productAttributesAddRmv;

    ViewHolderItem(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}