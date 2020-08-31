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

package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.app.ecomstore.util.EcomConstants.BLACK;
import static com.app.ecomstore.util.EcomConstants.E_COMMERCE;
import static com.app.ecomstore.util.EcomConstants.GROCERY;
import static com.app.ecomstore.util.EcomConstants.HUNDRED;
import static com.app.ecomstore.util.EcomConstants.LIGHT_BLACK;
import static com.app.ecomstore.util.EcomConstants.MINUS_ONE;
import static com.app.ecomstore.util.EcomConstants.NEW;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.SIZE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentBtmSheetOrderNumberBinding;
import com.app.delivxstore.main.history.model.Packaging;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOnGroup;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOns;
import com.app.delivxstore.main.mobileView.orderDetails.OrderDetailsForMobile;
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.adapters.AddOnsAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.adapters.CountRecyclerAdapter;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.forcepick.ForcePickActivity;
import com.app.ecomstore.util.EcomUtil;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.Predicate;
import javax.inject.Inject;

/**
 * Show user the quantity available for the mItem selected
 */
public class OrderNumberBtmSheet extends BottomSheetDialogFragment implements
    OrderNumberContract.OrderNumberView, SelectItem {
  @Inject
  OrderNumberContract.OrderNumberPresenter presenter;
  private Products mItem;
  private boolean mPick;
  private boolean mIsToPack;
  private ArrayList<AddOnGroup> mAddOnGroupArrayList = new ArrayList<>();
  private AddOnsAdapter mAddOnsAdapter;
  private CountRecyclerAdapter mCountRecyclerAdapter;
  private FragmentBtmSheetOrderNumberBinding mBinding;
  private int mPosition = ZERO;

  @Inject
  public OrderNumberBtmSheet() {
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.attachView(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_btm_sheet_order_number,
        container, false);
    initViews();
    return mBinding.getRoot();
  }

  /**
   * <h>initViews</h>
   * <p>Initialize views</p>
   */
  private void initViews() {
    if (mItem != null && presenter.getStoreType() == E_COMMERCE) {
      setAdapterForAddOns();
      setAdapterForCount();
      setDropDownForQuantity();
      mBinding.layoutButton.buttonCommon.setText(getContext().getString(R.string.add));
      float subTotal = ZERO;
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
          mItem.getAddOns().size() > ZERO) {
        for (int i = ZERO; i < mItem.getAddOns().size(); i++) {
          AddOns addOn = mItem.getAddOns().get(i);
          mAddOnGroupArrayList.addAll(addOn.getAddOnGroup());
        }
      }
      if (mAddOnGroupArrayList.size() > ZERO) {
        mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setVisibility(View.VISIBLE);
        mAddOnsAdapter.notifyDataSetChanged();
      }
      int quantity;
      double unitPrice = ZERO;
      quantity = Integer.parseInt(mItem.getQuantity().getValue());
      if (mItem.getAccounting().getFinalTotal() != null) {
        unitPrice = mItem.getAccounting().getFinalTotal();
      }
      subTotal += quantity * unitPrice;
      String totalPrice = String.format("%s %s", mItem.getCurrencySymbol(),
          Utility.roundOfDoubleValue(String.format("%s", subTotal)));
      mBinding.itemUnAvailabilitySingleRow.tvItemUnAvailableProductPrice.setText(totalPrice);
      mCountRecyclerAdapter.notifyDataSetChanged();
      if (mItem.getAttributes() != null && mItem.getAttributes().size() > ZERO) {
        StringBuilder attributeName = new StringBuilder();
        for (int i = ZERO; i < mItem.getAttributes().size(); i++) {
          if (mItem.getAttributes().get(i).getValue() != null && !mItem.getAttributes().get(
              i).getValue().isEmpty()) {
            attributeName.append(mItem.getAttributes().get(i).getAttrname()).append(":").append(
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
          String.format("%s %s", getResources().getString(R.string.units),
              mItem.getQuantity().getValue()));
    } else if (mItem != null && Integer.parseInt(mItem.getStatus().getStatus()) == NEW &&
        presenter.getStoreType() == RESTAURENT) {
      mBinding.itemPreTime.layoutButton.buttonCommon.setText(
          getContext().getString(R.string.Update));
      setDropDownPreparationTime();
      mBinding.itemPreTime.clProductItem.setVisibility(View.VISIBLE);
      mBinding.clProductItem.setVisibility(View.GONE);
    } else if (mItem != null && presenter.getStoreType() == RESTAURENT) {
      setAdapterForAddOns();
      setAdapterForCount();
      setDropDownForQuantity();
      mBinding.layoutButton.buttonCommon.setText(getContext().getString(R.string.add));
      LayoutInflater inflaterAttribute = (LayoutInflater) getActivity().getSystemService(
          LAYOUT_INFLATER_SERVICE);
      mBinding.itemFoodSingleRow.clFoodItem.setVisibility(View.VISIBLE);
      mBinding.itemUnAvailabilitySingleRow.clProductItem.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.hsItemUnAvailableStatus.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.tvItemSizeAndColor.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.tvItemAttributes.setVisibility(View.GONE);
      mBinding.itemFoodSingleRow.tvItemUnAvailableProductName.setText(mItem.getName());
      mBinding.itemFoodSingleRow.tvItemUnAvailableProductPrice.setText(
          String.format("%s %s", mItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(mItem.getAccounting().getUnitPrice() + "")));
      mBinding.itemFoodSingleRow.tvItemQty.setText(
          String.format("%s %s * %s %s", getResources().getString(R.string.qty),
              mItem.getQuantity().getValue(), mItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(mItem.getSingleUnitPrice().getUnitPrice() + "")));
      mBinding.itemFoodSingleRow.llFoodAttributeGroup.removeAllViews();
      ArrayList<Attributes> mAttributesList = new ArrayList<>();
      mAttributesList.clear();
      mAttributesList.addAll(mItem.getAttributes());
      Predicate<Attributes> conditionPending =
          orderCountData -> !orderCountData.getAttrname().equals(SIZE);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mAttributesList.removeIf(conditionPending);
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
          Utility.printLog("exe" + "attributesArrayList" + attributesArrayList.size());
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
      setAdapterForAddOns();
      setAdapterForCount();
      setDropDownForQuantity();
      mBinding.itemPharmacyRow.clPharmacyItem.setVisibility(View.VISIBLE);
      mBinding.itemFoodSingleRow.clFoodItem.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.groupAsile.setVisibility(View.GONE);
      mBinding.itemUnAvailabilitySingleRow.clProductItem.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.hsItemUnAvailableStatus.setVisibility(View.GONE);
      mBinding.layoutButton.buttonCommon.setText(getContext().getString(R.string.confirm));
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
          String.format("%s %s %s", getResources().getString(R.string.units),
              mItem.getQuantity().getValue(), mItem.getQuantity().getUnit()));
      mBinding.itemPharmacyRow.tvItemDosage.setVisibility(View.GONE);
      mBinding.itemPharmacyRow.tvItemStripCount.setText(
          String.format("%s %s*%s %s", mItem.getQuantity().getValue(),
              mItem.getQuantity().getUnit(), mItem.getCurrencySymbol(),
              Utility.roundOfDoubleValue(
                  String.format("%s", mItem.getSingleUnitPrice().getSubTotal()))));
      mBinding.itemPharmacyRow.tvItemUnAvailableProductPrice.setText(
          String.format("%s %s", mItem.getAccounting().getCurrencySymbol(),
              Utility.roundOfDoubleValue(
                  String.format("%s", mItem.getAccounting().getFinalTotal()))));
      Predicate<Attributes> conditionIsAddOn =
          orderCountData -> orderCountData.isAddOn();
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mItem.getAttributes().removeIf(conditionIsAddOn);
      }
      if (mItem.getAttributes() != null && mItem.getAttributes().size() > ZERO) {
        StringBuilder value = new StringBuilder();
        for (int j = ZERO; j < mItem.getAttributes().size(); j++) {
          Attributes attributes = mItem.getAttributes().get(j);
          String addOnName = EcomUtil.getColoredSpanned(attributes.getAttrname(),
              LIGHT_BLACK);
          String attrValue = EcomUtil.getColoredSpanned(attributes.getValue(), BLACK);
          if (attributes.getValue() != null && !attributes.getValue().isEmpty()) {
            value.append(addOnName).append(":").append(attrValue).append(" | ");
          }
        }
        if (value.length() > ZERO) {
          mBinding.itemPharmacyRow.tvItemDosage.setText(
              Html.fromHtml(value.substring(ZERO, value.length() - TWO)));
        } else {
          mBinding.itemPharmacyRow.tvItemDosage.setVisibility(View.GONE);
        }
      }
    }
    mBinding.layoutButton.buttonCommon.setOnClickListener(v -> {
      if (mIsToPack && !(getActivity() instanceof ForcePickActivity)) {
        if (!Utility.isNetworkConnected(getActivity())) {
          onError(getResources().getString(R.string.networkError));
          return;
        }
        presenter.applyPack(mItem.getProductOrderId(),
            mPosition + ONE);
      } else if (mPick) {
        if (getActivity() instanceof OrderDetailsForMobile) {
          if (getDialog() != null) {
            if (getDialog().isShowing()) {
              getDialog().dismiss();
            }
          }
          ((OrderDetailsForMobile) getActivity()).callPickApi(
              mPosition + ONE);
        }
      } else {
        if (mPosition != MINUS_ONE) {
          if (getDialog() != null) {
            if (getDialog().isShowing()) {
              getDialog().dismiss();
            }
          }
          if (getActivity() != null) {
            if (getActivity() instanceof OrderDetailsForMobile) {
              ((OrderDetailsForMobile) getActivity()).startProductUnAvailableAct(mItem,
                  mPosition + ONE);
            } else if (getActivity() instanceof ForcePickActivity) {
              ((ForcePickActivity) getActivity()).setQuantity(
                  mPosition + ONE);
            }
          }
        }
      }
    });
    mBinding.itemPreTime.layoutButton.buttonCommon.setOnClickListener(v -> {
      if (getDialog() != null) {
        if (getDialog().isShowing()) {
          getDialog().dismiss();
          if (getActivity() != null) {
            int selectItem = mBinding.itemPreTime.spinnerPreperationTime.getSelectedItemPosition();
            ((OrderDetailsForMobile) getActivity()).acceptFoodOrder(
                EcomUtil.getPreperationTime(selectItem));
          }
        }
      }
    });
  }

  /**
   * <h>setDropDownForQuantity</h>
   * <p>Set the drop down for the quantity if the
   * list is out of display boundaries</p>
   */
  private void setDropDownForQuantity() {
    ArrayList<String> countArray = new ArrayList<>();
    for (int i = ONE; i <= Integer.parseInt(mItem.getQuantity().getValue()); i++) {
      countArray.add(String.format("%d", i));
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
        getContext(),
        R.layout.item_spinner,
        countArray);
    mBinding.spinnerOrderitemQuantity.setAdapter(adapter);
  }

  /**
   * <h>setDropDownForQuantity</h>
   * <p>Set the drop down for the quantity if the
   * list is out of display boundaries</p>
   */
  private void setDropDownPreparationTime() {
    ArrayList<String> countArray = new ArrayList<>();
    String[] preparation_time = getActivity().getResources().getStringArray(
        R.array.preparation_time);
    for (int i = ZERO; i < preparation_time.length; i++) {
      countArray.add(preparation_time[i]);
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
        getContext(),
        R.layout.item_food_spinner,
        countArray);
    mBinding.itemPreTime.spinnerPreperationTime.setAdapter(adapter);
  }

  /**
   * <h>setAdapterForCount</h>
   * <p>set adapter for showing the mItem quantity</p>
   */
  private void setAdapterForCount() {
    mBinding.rvOrderItemQuantity.setLayoutManager(new LinearLayoutManager(
        getContext(),
        LinearLayoutManager.HORIZONTAL,
        false));
    mBinding.rvOrderItemQuantity.setHasFixedSize(true);
    mCountRecyclerAdapter = new CountRecyclerAdapter(
        Integer.parseInt(mItem.getQuantity().getValue()), this::onSelectItem);
    mPosition = Integer.parseInt(mItem.getQuantity().getValue()) - ONE;
    mBinding.rvOrderItemQuantity.setAdapter(mCountRecyclerAdapter);
    new Handler().postDelayed(() -> {
      mBinding.spinnerOrderitemQuantity.setVisibility(
          isRecyclerViewScrollable() ? View.VISIBLE : View.GONE);
      mBinding.rvOrderItemQuantity.setVisibility(
          isRecyclerViewScrollable() ? View.GONE : View.VISIBLE);
    }, HUNDRED);
  }

  /**
   * <h>isRecyclerViewScrollable</h>
   * <p>Check if the quantity recycler view is scrollable or not</p>
   *
   * @return true if scrollable and false if not
   */
  public boolean isRecyclerViewScrollable() {
    LinearLayoutManager layoutManager =
        (LinearLayoutManager) mBinding.rvOrderItemQuantity.getLayoutManager();
    RecyclerView.Adapter adapter = mBinding.rvOrderItemQuantity.getAdapter();
    if (layoutManager == null || adapter == null) return false;
    return layoutManager.findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - ONE;
  }

  /**
   * <h>setAdapterForAddOns</h>
   * <p>set the adapter for items addons</p>
   */
  private void setAdapterForAddOns() {
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setLayoutManager(
        new LinearLayoutManager(getContext()));
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setHasFixedSize(true);
    mAddOnsAdapter = new AddOnsAdapter(mAddOnGroupArrayList, mItem.getCurrencySymbol());
    mBinding.itemUnAvailabilitySingleRow.rvItemUnAvailableAddOns.setAdapter(mAddOnsAdapter);
    mAddOnGroupArrayList.clear();
  }

  /**
   * <h>setItemDetails</h>
   * <p>set the mItem details for which quantity has to be selected</p>
   *
   * @param item mItem data which is selected
   * @param pick currency symbol
   */
  public void setItemDetails(Products item, boolean pick, boolean pack) {
    this.mIsToPack = pack;
    this.mItem = item;
    this.mPick = pick;
  }

  @Override
  public void finishActivity() {
    if (getActivity() != null) {
      getActivity().finish();
    }
  }

  @Override
  public void onError(String msg) {
  }

  @Override
  public void setPackResult() {
    EcomUtil.printLog("exe" + "value" + mItem.getQuantity().getValue());
    if (mPosition + ONE == Integer.parseInt(mItem.getQuantity().getValue())) {
      if (getActivity() != null) {
        getActivity().finish();
      }
    } else {
      if (getDialog() != null) {
        if (getDialog().isShowing()) {
          getDialog().dismiss();
          if (getActivity() != null) {
            ((OrderDetailsForMobile) getActivity()).refreshApi();
          }
        }
      }
    }
  }

  @Override
  public void showProgress() {
    mBinding.progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    mBinding.progressBar.setVisibility(View.GONE);
  }

  @Override
  public void onSelectItem(int position) {
    mPosition = position;
  }
}
