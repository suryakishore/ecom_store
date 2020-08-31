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

package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import static com.app.ecomstore.util.EcomConstants.BLACK;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.E_COMMERCE;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.FINISH;
import static com.app.ecomstore.util.EcomConstants.GROCERY;
import static com.app.ecomstore.util.EcomConstants.ITEM_AVAILABLE;
import static com.app.ecomstore.util.EcomConstants.ITEM_DATA;
import static com.app.ecomstore.util.EcomConstants.LIGHT_BLACK;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.REASON;
import static com.app.ecomstore.util.EcomConstants.REASON_ID;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.SELECTED_COUNT;
import static com.app.ecomstore.util.EcomConstants.SIZE;
import static com.app.ecomstore.util.EcomConstants.STORE_CATEGORY_ID;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityProductUnavailableBinding;
import com.app.delivxstore.main.history.model.Packaging;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOnGroup;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOns;
import com.app.delivxstore.main.mobileView.orderDetails.adapter.AddOnsAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.adapters.ProductUnavailableReasonsSubstituteAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductReason;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductSubstituteData;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import com.bumptech.glide.Glide;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Predicate;
import javax.inject.Inject;

/**
 * Shows mReasons for item not available or show the
 * products which can be substituted
 */
public class ProductUnavailableSubstituteActivity extends DaggerAppCompatActivity implements
    ProductUnavailableSubstituteContract.OrderUnavailableView, SelectItem, View.OnClickListener {
  @Inject
  ProductUnavailableSubstituteContract.OrderUnavailablePresenter presenter;
  private ActivityProductUnavailableBinding mBinding;
  private Products mItem;
  private String mCurrencySymbol;
  private ArrayList<AddOnGroup> mAddOnGroupArrayList = new ArrayList<>();
  private ArrayList<ProductReason> mReasons = new ArrayList<>();
  private ArrayList<ProductSubstituteData> mProductSubstituteData = new ArrayList<>();
  private AddOnsAdapter mAddOnsAdapter;
  private ProductUnavailableReasonsSubstituteAdapter mProductUnavailableReasonsSubstituteAdapter;
  private boolean mIsItemUnavailable;
  private int mSelectedCount;
  private int mPosition;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Utility.RtlConversion(this, presenter.getLanguage());
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_unavailable);
    initViews();
  }

  /**
   * <h>initViews</h>
   * <p>Initializing views related to the order unavailable status</p>
   */
  private void initViews() {
    getIntentData();
    setAdapterForAddOns();
    setAdapterForReasons();
    setViews();
    setClickToViews();
  }

  /**
   * initialize the click listener to views.
   */
  private void setClickToViews() {
    mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(this::onClick);
    mBinding.layoutButton.buttonCommon.setOnClickListener(this::onClick);
  }

  /**
   * set reasons for unavailability
   */
  private void setReasonsForUnavailability() {
    presenter.getReasons(getIntent().getStringExtra(STORE_CATEGORY_ID),
        getIntent().getIntExtra(REASON_ID, ZERO));
  }

  /**
   * <h>setAdapterForReasons</h>
   * <p>adapter to show all the mReasons related to the product
   * unavailability</p>
   */
  private void setAdapterForReasons() {
    mBinding.rvProductUnavailableReasonsSubstitute.setLayoutManager(
        new LinearLayoutManager(this));
    mBinding.rvProductUnavailableReasonsSubstitute.setHasFixedSize(true);
    mProductUnavailableReasonsSubstituteAdapter = new ProductUnavailableReasonsSubstituteAdapter(
        this,
        mReasons,
        mProductSubstituteData);
    mBinding.rvProductUnavailableReasonsSubstitute.setAdapter(
        mProductUnavailableReasonsSubstituteAdapter);
  }

  /**
   * <h>setViews</h>
   * <p>Setting up the views related to the item</p>
   */
  private void setViews() {
    mBinding.layoutToolBar.textViewAllTitle.setText(
        mIsItemUnavailable ? getString(R.string.orderUnavailable_selectReason)
            : getString(R.string.orderUnavailable_substituteProducts));
    mBinding.tvProductSubstituteTitle.setVisibility(
        mIsItemUnavailable ? View.GONE : View.VISIBLE);
    if (mIsItemUnavailable) {
      setReasonsForUnavailability();
    } else {
      setProductsForSubstitute();
    }
    mBinding.layoutButton.buttonCommon.setText(getString(R.string.confirmButton));
    if (mItem != null && presenter.getStoreType() == E_COMMERCE) {
      float subTotal = 0.0f;
      if (mItem.getImages() != null) {
        Glide.with(this)
            .load(mItem.getImages().getMedium())
            .into(mBinding.itemUnAvailabilitySingleRow.ivItemUnAvailableProductImage);
      }
      String itemName = mItem.getName();
      mBinding.itemUnAvailabilitySingleRow.viewItemUnAvailableWhiteLight.setVisibility(
          View.VISIBLE);
      mBinding.itemUnAvailabilitySingleRow.viewItemUnAvailableDashed.setVisibility(View.GONE);
      mBinding.itemUnAvailabilitySingleRow.hsItemUnAvailableStatus.setVisibility(View.GONE);
      mBinding.itemUnAvailabilitySingleRow.tvItemUnAvailableProductName.setText(itemName);
      if (mItem.getAddOns() != null &&
          mItem.getAddOns().size() > 0) {
        for (int i = 0; i < mItem.getAddOns().size(); i++) {
          AddOns addOn = mItem.getAddOns().get(i);
          mAddOnGroupArrayList.addAll(addOn.getAddOnGroup());
        }
      }
      if (mAddOnGroupArrayList.size() > 0) {
        mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setVisibility(View.VISIBLE);
        mAddOnsAdapter.notifyDataSetChanged();
      }
      int quantity;
      double unitPrice = 0;
      quantity = Integer.parseInt(mItem.getQuantity().getValue());
      if (mItem.getAccounting().getFinalTotal() != null) {
        unitPrice = mItem.getAccounting().getFinalTotal();
      }
      subTotal += quantity * unitPrice;
      String totalPrice = mCurrencySymbol + " " + Utility.roundOfDoubleValue(subTotal + "");
      mBinding.itemUnAvailabilitySingleRow.tvItemUnAvailableProductPrice.setText(totalPrice);
      if (mItem.getAttributes() != null && mItem.getAttributes().size() > ZERO) {
        StringBuilder attributeName = new StringBuilder();
        for (int i = ZERO; i < mItem.getAttributes().size(); i++) {
          if (mItem.getAttributes().get(i).getValue() != null && !mItem.getAttributes().get(
              i).getValue().isEmpty()) {
            attributeName.append(mItem.getAttributes().get(i).getAttrname()).append(
                ":").append(
                mItem.getAttributes().get(i).getValue()).append(" ");
          }
          if (i == ONE) {
            break;
          }
        }
        mBinding.itemUnAvailabilitySingleRow.tvItemSizeAndColor.setText(attributeName.toString());
        mBinding.itemUnAvailabilitySingleRow.tvItemUnAvailableViewMore.setVisibility(
            mItem.getAttributes().size() > TWO ? View.VISIBLE : View.GONE);
      }
      mBinding.itemUnAvailabilitySingleRow.tvItemQty.setText(
          String.format("%s %s", getResources().getString(R.string.qty),
              mItem.getQuantity().getValue()));
    } else if (mItem != null && presenter.getStoreType() == RESTAURENT) {
      LayoutInflater inflaterAttribute = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      mBinding.itemFoodSingleRow.clFoodItem.setVisibility(View.VISIBLE);
      mBinding.itemUnAvailabilitySingleRow.clProductItem.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.hsItemUnAvailableStatus.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.tvItemUnAvailableProductName.setText(mItem.getName());
      float subTotal = ZERO;
      int quantity;
      double unitPrice = ZERO;
      quantity = Integer.parseInt(mItem.getQuantity().getValue());
      if (mItem.getAccounting().getFinalTotal() != null) {
        unitPrice = mItem.getAccounting().getFinalTotal();
      }
      subTotal += quantity * unitPrice;
      mBinding.itemFoodSingleRow.tvItemUnAvailableProductPrice.setText(
          String.format("%s %s", mItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(
                  String.format("%s", mItem.getSellerAccounting().getTaxableAmount()))));
      mBinding.itemFoodSingleRow.tvItemQty.setText(
          String.format("%s %s", getResources().getString(R.string.qty),
              mItem.getQuantity().getValue()));
      mBinding.itemFoodSingleRow.llFoodAttributeGroup.removeAllViews();
      ArrayList<Attributes> mAttributesList = new ArrayList<>();
      mAttributesList.clear();
      mAttributesList.addAll(mItem.getAttributes());
      Predicate<Attributes> conditionPending =
          orderCountData -> !orderCountData.getAttrname().equals(SIZE);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mAttributesList.removeIf(conditionPending);
      }
      if (mAttributesList.size() > ZERO) {
        if (mAttributesList.get(ZERO).getValue() != null) {
          String size = EcomUtil.getColoredSpanned(getResources().getString(R.string.size),
              LIGHT_BLACK);
          String attrName = EcomUtil.getColoredSpanned(mAttributesList.get(ZERO).getValue(),
              BLACK);
          mBinding.itemFoodSingleRow.tvItemSizeAndColor.setText(Html.fromHtml(
              String.format("%s %s", size, attrName)));
        } else {
          mBinding.itemFoodSingleRow.tvItemSizeAndColor.setVisibility(View.GONE);
        }
      }
      mAttributesList.clear();
      mAttributesList.addAll(mItem.getAttributes());
      Predicate<Attributes> conditionIsAddOn =
          orderCountData -> !orderCountData.isAddOn();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mAttributesList.removeIf(conditionIsAddOn);
      }
      LinkedHashMap<String, ArrayList<Attributes>> mHashMap = new LinkedHashMap<>();
      if (mAttributesList.size() > ZERO) {
        for (int j = ZERO; j < mAttributesList.size(); j++) {
          Attributes attributes = mAttributesList.get(j);
          String addOnId = attributes.getAddOnId();
          if (mHashMap.containsKey(addOnId)) {
            ArrayList<Attributes> value = mHashMap.get(addOnId);
            if (value != null) {
              value.add(attributes);
            }
          } else {
            ArrayList<Attributes> attributesArrayList = new ArrayList<>();
            attributesArrayList.add(attributes);
            mHashMap.put(addOnId, attributesArrayList);
          }
        }
      }
      if (mHashMap.size() > ZERO) {
        for (int j = ZERO; j < mHashMap.size(); j++) {
          ArrayList<Attributes> attributesArrayList =
              (ArrayList<Attributes>) mHashMap.values().toArray()[j];
          if (attributesArrayList != null) {
            String addOnName = String.format("%s: ", EcomUtil.getColoredSpanned(
                attributesArrayList.get(ZERO).getAddOnName(), LIGHT_BLACK));
            View attributeView = inflaterAttribute.inflate(R.layout.item_food_attribute,
                null);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = ZERO; i < attributesArrayList.size(); i++) {
              stringBuilder.append(attributesArrayList.get(i).getAttrname()).append(",");
            }
            String attrName = EcomUtil.getColoredSpanned(
                stringBuilder.toString().substring(ZERO,
                    stringBuilder.toString().length() - ONE), BLACK);
            TextView tvAttributeItem = attributeView.findViewById(R.id.tvAttributeItem);
            tvAttributeItem.setText(Html.fromHtml(
                String.format("%s %s", addOnName, attrName)));
            mBinding.itemFoodSingleRow.llFoodAttributeGroup.addView(attributeView);
          }
        }
      }
    } else if (mItem != null && (presenter.getStoreType() == PHARMACY
        || presenter.getStoreType() == GROCERY)) {
      mBinding.itemPharmacyRow.clPharmacyItem.setVisibility(View.VISIBLE);
      mBinding.itemFoodSingleRow.clFoodItem.setVisibility(View.GONE);
      mBinding.itemUnAvailabilitySingleRow.clProductItem.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.hsItemUnAvailableStatus.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.groupAsile.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.tvItemUnAvailableProductName.setText(mItem.getName());
      if (mItem.getImages() != null) {
        Glide.with(this)
            .load(mItem.getImages().getMedium())
            .into(mBinding.itemPharmacyRow.ivItemUnAvailableProductImage);
      }
      if (mItem.getPackaging() != null) {
        Packaging packaging = mItem.getPackaging();
        if (packaging.getUnitValue() != ZERO) {
          mBinding.itemPharmacyRow.tvItemTabletCount.setText(String.format("%s %s %s",
              packaging.getUnitValue(), packaging.getUnitType(), packaging.getPackingType()));
        } else {
          mBinding.itemPharmacyRow.tvItemTabletCount.setVisibility(View.GONE);
        }
      } else {
        mBinding.itemPharmacyRow.tvItemTabletCount.setVisibility(View.GONE);
      }
      mBinding.itemPharmacyRow.tvItemQty.setText(
          String.format("%s %s %s", getResources().getString(R.string.qty),
              mItem.getQuantity().getValue(), mItem.getQuantity().getUnit()));
      mBinding.itemPharmacyRow.tvItemDosage.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.tvItemStripCount.setText(
          String.format("%d %s*%s", ONE, mItem.getQuantity().getUnit(), Utility.roundOfDoubleValue(
              String.format("%s", mItem.getSellerSingleUnitPrice().getFinalUnitPrice()))));
      mBinding.itemPharmacyRow.tvItemUnAvailableProductPrice.setText(
          String.format("%s %s", mItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(
                  String.format("%s", mItem.getSellerAccounting().getTaxableAmount()))));
    } else {
      mBinding.llOrderUnavailableContainer.setVisibility(View.GONE);
    }
  }

  /**
   * <h>setProductsForSubstitute</h>
   * <p>get the products related to the
   * selected substitute</p>
   */
  private void setProductsForSubstitute() {
    presenter.getSubstituteProducts();
  }

  /**
   * <h>getIntentData</h>
   * <p>get intent data related to item</p>
   */
  private void getIntentData() {
    if (getIntent() != null) {
      mItem = getIntent().getParcelableExtra(ITEM_DATA);
      mCurrencySymbol = getIntent().getStringExtra(CURRENCY_SYMBOL);
      mIsItemUnavailable = getIntent().getBooleanExtra(ITEM_AVAILABLE, false);
      mSelectedCount = getIntent().getIntExtra(SELECTED_COUNT, ZERO);
      presenter.setItemData(mItem, mSelectedCount);
    }
  }

  /**
   * <h>setAdapterForAddOns</h>
   * <p>set the adapter for mItems addons</p>
   */
  private void setAdapterForAddOns() {
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setLayoutManager(
        new LinearLayoutManager(this));
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setHasFixedSize(true);
    mAddOnsAdapter = new AddOnsAdapter(mAddOnGroupArrayList, mCurrencySymbol);
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setAdapter(mAddOnsAdapter);
    mAddOnGroupArrayList.clear();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imageViewAllBack:
        finish();
        break;
      case R.id.buttonCommon:
        if (presenter.getStoreType() == PHARMACY) {
          Intent intent = new Intent();
          if (mReasons.size() > ZERO) {
            intent.putExtra(REASON, mReasons.get(mPosition).getReason());
            intent.putExtra(SELECTED_COUNT, mSelectedCount);
            intent.putExtra(ORDER_ID, getIntent().getBooleanExtra(ORDER_ID, FALSE));
          }
          setResult(RESULT_OK, intent);
          finishActivity();
        } else {
          presenter.confirmUnAvailability(mReasons.get(mPosition).getReason());
        }
        break;
    }
  }

  @Override
  public void showProgress() {
    mBinding.pbUnavailableLoader.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbUnavailableLoader.setVisibility(View.GONE);
  }

  @Override
  public void setReasons(ArrayList<ProductReason> data) {
    mReasons.clear();
    mReasons.addAll(data);
    mProductUnavailableReasonsSubstituteAdapter.isItemUnavailable(mIsItemUnavailable);
    mProductUnavailableReasonsSubstituteAdapter.setCurrency(mCurrencySymbol);
    mProductUnavailableReasonsSubstituteAdapter.notifyDataSetChanged();
  }

  @Override
  public void setProductSubstitutes(ArrayList<ProductSubstituteData> data) {
    mProductSubstituteData.clear();
    mProductSubstituteData.addAll(data);
    mProductUnavailableReasonsSubstituteAdapter.isItemUnavailable(mIsItemUnavailable);
    mProductUnavailableReasonsSubstituteAdapter.setCurrency(mCurrencySymbol);
    mProductUnavailableReasonsSubstituteAdapter.notifyDataSetChanged();
  }

  @Override
  public void onError(String msg) {
  }

  @Override
  public void finishActivity() {
    finish();
  }

  @Override
  public void reasonSuccess() {
    Intent intent = new Intent();
    intent.putExtra(FINISH,
        Integer.parseInt(mItem.getQuantity().getValue()) == mSelectedCount);
    setResult(RESULT_OK, intent);
    finish();
  }

  @Override
  public void onSelectItem(int position) {
    mPosition = position;
  }
}
