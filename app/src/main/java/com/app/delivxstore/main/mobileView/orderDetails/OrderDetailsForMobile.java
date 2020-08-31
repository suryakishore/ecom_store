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

package com.app.delivxstore.main.mobileView.orderDetails;

import static com.app.delivxstore.utility.Utility.openWebBrowser;
import static com.app.ecomstore.util.EcomConstants.ACCEPTED;
import static com.app.ecomstore.util.EcomConstants.ADD_PRODUCT_REQUEST;
import static com.app.ecomstore.util.EcomConstants.AISLE;
import static com.app.ecomstore.util.EcomConstants.ASSIGN_MANUALLY;
import static com.app.ecomstore.util.EcomConstants.ATTRIBUTE_DATA;
import static com.app.ecomstore.util.EcomConstants.BARCODE_REQUEST;
import static com.app.ecomstore.util.EcomConstants.BLACK;
import static com.app.ecomstore.util.EcomConstants.CANCELLED;
import static com.app.ecomstore.util.EcomConstants.CHECKED;
import static com.app.ecomstore.util.EcomConstants.COMPLETED;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.CUSTOMER_ID;
import static com.app.ecomstore.util.EcomConstants.CUSTOMER_NAME;
import static com.app.ecomstore.util.EcomConstants.CUSTOMER_NUMBER;
import static com.app.ecomstore.util.EcomConstants.DATA;
import static com.app.ecomstore.util.EcomConstants.DISPATCH;
import static com.app.ecomstore.util.EcomConstants.DRIVER_ID;
import static com.app.ecomstore.util.EcomConstants.DRIVER_REQUEST;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.FINISH;
import static com.app.ecomstore.util.EcomConstants.FIVE;
import static com.app.ecomstore.util.EcomConstants.FORCE_PICK_REQUEST;
import static com.app.ecomstore.util.EcomConstants.FOUR;
import static com.app.ecomstore.util.EcomConstants.GROCERY;
import static com.app.ecomstore.util.EcomConstants.HUNDRED;
import static com.app.ecomstore.util.EcomConstants.IMAGE_URL;
import static com.app.ecomstore.util.EcomConstants.IN_REVIEW;
import static com.app.ecomstore.util.EcomConstants.ITEM_AVAILABLE;
import static com.app.ecomstore.util.EcomConstants.ITEM_DATA;
import static com.app.ecomstore.util.EcomConstants.ITEM_PRICE;
import static com.app.ecomstore.util.EcomConstants.LIGHT_BLACK;
import static com.app.ecomstore.util.EcomConstants.MAKE_PHONE_CALL;
import static com.app.ecomstore.util.EcomConstants.MASTER_ORDER;
import static com.app.ecomstore.util.EcomConstants.M_CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.NEW;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.OPEN_CAMERA;
import static com.app.ecomstore.util.EcomConstants.ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.PACKAGE_ID;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.PARENT_PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.PENDING;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.PHARMACY_REJECT;
import static com.app.ecomstore.util.EcomConstants.PRESCRIPTION_IMG;
import static com.app.ecomstore.util.EcomConstants.PRODUCT;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ID_TXT;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ORDER_TYPE;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_SUBSTITUDE;
import static com.app.ecomstore.util.EcomConstants.QUANTITY;
import static com.app.ecomstore.util.EcomConstants.READY_FOR_PICK_UP;
import static com.app.ecomstore.util.EcomConstants.REASON;
import static com.app.ecomstore.util.EcomConstants.REASON_ID;
import static com.app.ecomstore.util.EcomConstants.REASON_PRODUCT_CANCELLED;
import static com.app.ecomstore.util.EcomConstants.REASON_PRODUCT_UNAVAILABLE;
import static com.app.ecomstore.util.EcomConstants.RECEIPT_IMG;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.SECTOR;
import static com.app.ecomstore.util.EcomConstants.SELECTED_COUNT;
import static com.app.ecomstore.util.EcomConstants.SELECT_REASON;
import static com.app.ecomstore.util.EcomConstants.SEND_MANUALLY;
import static com.app.ecomstore.util.EcomConstants.SEVEN;
import static com.app.ecomstore.util.EcomConstants.SEVENTY_FIVE;
import static com.app.ecomstore.util.EcomConstants.SHELF;
import static com.app.ecomstore.util.EcomConstants.SIXTY_FIVE;
import static com.app.ecomstore.util.EcomConstants.SLOT_ID;
import static com.app.ecomstore.util.EcomConstants.STATUS;
import static com.app.ecomstore.util.EcomConstants.STORE_CATEGORY_ID;
import static com.app.ecomstore.util.EcomConstants.STORE_ID;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.THOUSAND;
import static com.app.ecomstore.util.EcomConstants.THREE;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.TWENTY;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.UPDATE_ASILE;
import static com.app.ecomstore.util.EcomConstants.UPLOAD_FORCE_PICK_REQUEST;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityOrderDetailsBinding;
import com.app.delivxstore.main.dispatchDetails.DispatchDetailsActivity;
import com.app.delivxstore.main.history.model.Packaging;
import com.app.delivxstore.main.home.models.CustomerDetails;
import com.app.delivxstore.main.home.models.DriverDetails;
import com.app.delivxstore.main.home.models.Status;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.CancellationDialog;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.adapter.PastOrderInOrderDetAdapter;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOnGroup;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOns;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.main.mobileView.orderDetails.adapter.AddOnsAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.adapter.BoxIdsAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.adapter.PrescriptionsAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes;
import com.app.delivxstore.main.mobileView.orderDetails.models.Count;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;
import com.app.delivxstore.main.mobileView.orderDetails.models.ProductTaxArray;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.models.subsituteWith;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberBtmSheet;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.ProductUnavailableSubstituteActivity;
import com.app.delivxstore.main.mobileView.pastOrders.PastOrdersAct;
import com.app.delivxstore.mqtt_chat.ChattingActivity;
import com.app.delivxstore.utility.TimeFormatter;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.addproduct.AddProductActivity;
import com.app.ecomstore.customizations.CustomizationsActivity;
import com.app.ecomstore.drivers.SelectDriversActivity;
import com.app.ecomstore.forcepick.ForcePickActivity;
import com.app.ecomstore.prescription.PrescriptionDetail;
import com.app.ecomstore.printlabel.LabelBags;
import com.app.ecomstore.printlabel.PrintLabelsActivity;
import com.app.ecomstore.substitute.ProductSubStituteActivity;
import com.app.ecomstore.trackOrder.TrackOrderActivity;
import com.app.ecomstore.uiutil.barcodescanning.BarCodePreviewActivity;
import com.app.ecomstore.updateasile.UpdateAsileActivity;
import com.app.ecomstore.util.EcomUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.function.Predicate;
import javax.inject.Inject;

/**
 * Show the details for order if the device is mobile
 */
public class OrderDetailsForMobile extends DaggerAppCompatActivity
    implements OrderDetailsContract.OrderDetailsView, View.OnClickListener, SelectItem {
  @Inject
  OrderDetailsContract.OrderDetailsPresenter presenter;
  @Inject
  OrderNumberBtmSheet orderNumberBtmSheet;
  private ActivityOrderDetailsBinding mOrderDetailsBinding;
  private String mCurrencySymbol;
  private String mCustomerId;
  private int mOrderStatus;
  private float mAddOnCount = 0;
  private double mTotalAmt = 0.0;
  private Dialog mDialog;
  private CancellationDialog mCancelDialog;
  private ArrayList<Products> mItemsArrayList = new ArrayList<>();
  private ArrayList<Products> mTabItemsList = new ArrayList<>();
  private ArrayList<Products> mItems = new ArrayList<>();
  private ArrayList<Attributes> mAttributesList = new ArrayList<>();
  private String mOrderId, mPackageId;
  private OrderDetailsModel mOrderDetails;
  private String mStatus, mPhoneNumber;
  private StatusViewPager mStatusViewPager;
  private boolean mIsUpdateOrAdd = TRUE;
  private int mIndex;
  private String mProductImg = "";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Utility.RtlConversion(this, presenter.getLanguage());
    mOrderDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
    initViews();
    registerOnClick();
    mStatusViewPager = new StatusViewPager(this, presenter.getStoreType());
    mOrderDetailsBinding.viewpagerOrderDetailsViewPager.setAdapter(mStatusViewPager);
    mOrderDetailsBinding.tlOrderDetailsOrderStatus.setupWithViewPager(
        mOrderDetailsBinding.viewpagerOrderDetailsViewPager);
    mOrderStatus = getIntent().getIntExtra(STATUS, ZERO);
    presenter.getBundleData(getIntent().getExtras());
  }

  /**
   * set on click listener to views
   */
  private void registerOnClick() {
    mOrderDetailsBinding.layoutButton.buttonCommon.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutToolBar.imageViewAllBack.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutToolBar.ivAddProduct.setOnClickListener(this::onClick);
    mOrderDetailsBinding.textViewOrderDetailsManualDispatch.setOnClickListener(this::onClick);
    mOrderDetailsBinding.textViewOrderDetailsAutoDispatch.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutOptions.textViewOptionsFirst.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutOptions.textViewOptionsSecond.setOnClickListener(this::onClick);
    mOrderDetailsBinding.shipmentOptions.tvAutoShiment.setOnClickListener(this::onClick);
    mOrderDetailsBinding.shipmentOptions.tvManualShipment.setOnClickListener(this::onClick);
    mOrderDetailsBinding.accountDetails.ivOrderDetailsEdit.setOnClickListener(this::onClick);
    mOrderDetailsBinding.constraintLayoutOrderDetailsMainContainer.setOnClickListener(
        this::onClick);
    mOrderDetailsBinding.textViewOrderDetailsCustomerName.setOnClickListener(this::onClick);
    mOrderDetailsBinding.tvCancelDispatch.setOnClickListener(this::onClick);
    mOrderDetailsBinding.ivCall.setOnClickListener(this::onClick);
    mOrderDetailsBinding.tvReceipt.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutToolBar.cvMessage.setOnClickListener(this::onClick);
    mOrderDetailsBinding.cvDriverCall.setOnClickListener(this::onClick);
    mOrderDetailsBinding.tvPo.setOnClickListener(this::onClick);
    mOrderDetailsBinding.layoutBag.tvItemLable.setOnClickListener(this::onClick);
    mOrderDetailsBinding.orderDetailsTrackDetails.tvTrackOrderStatus.setOnClickListener(
        this::onClick);
    mOrderDetailsBinding.myseek.setSliderProgressCallback(progress -> {
      if (progress > SIXTY_FIVE) {
        mOrderDetailsBinding.myseek.setProgress(HUNDRED);
        //write the logic here.
        if (mStatus.equals(String.valueOf(ACCEPTED)) && (presenter.getStoreType() == PHARMACY
            || presenter.getStoreType() == GROCERY)) {
          presenter.orderPickedRequest(mOrderId);
        } else if (mStatus.equals(String.valueOf(CHECKED))
            && (presenter.getStoreType() == PHARMACY || presenter.getStoreType() == GROCERY)) {
          presenter.confirmOrder(mOrderId, ZERO);
        }
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    Utility.printLog("OrderMain" + "onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Utility.printLog("OrderMain" + "onResume");
  }

  /**
   * <h>initViews</h>
   * <p>Initialize view for the order details
   * page</p>
   */
  public void initViews() {
    mOrderDetailsBinding.layoutButton.buttonCommon.setText(getString(R.string.accept));
    mOrderDetailsBinding.nsOrderDetailsScrollContainer.setClipToPadding(false);
    mOrderDetailsBinding.layoutLoader.textViewLoaderSubTitle.setText(
        getString(R.string.driverListPleaseWait));
    mOrderDetailsBinding.layoutLoader.textViewLoaderTitle.setText(
        getString(R.string.driverListGenerateShippingLabel));
    mOrderDetailsBinding.layoutToolBar.cvMessage.setVisibility(View.VISIBLE);
  }

  /**
   * <h>setTabLayout</h>
   * <p>Setting tab layout for the order's item status</p>
   *
   * @param count count for each tab
   */
  private void setTabLayout(Count count) {
    EcomUtil.printLog("exe" + "countTab" + count.toString());
    mStatusViewPager.setTabText(count);
    Utility.printLog(
        "exe" + "storeType" + presenter.getStoreType() + "count" + mStatusViewPager.getCount()
            + "tabCount" + mOrderDetailsBinding.tlOrderDetailsOrderStatus.getTabCount());
    Utility.printLog("exe" + "countPicked " + count.getPicked());
    tabsData(count);
    mOrderDetailsBinding.tlOrderDetailsOrderStatus.setTabMode(TabLayout.MODE_SCROLLABLE);
    Objects.requireNonNull(
        mOrderDetailsBinding.tlOrderDetailsOrderStatus.getTabAt(ZERO)).select();
    mStatusViewPager.setOnSelectView(mOrderDetailsBinding.tlOrderDetailsOrderStatus, ZERO);
    mOrderDetailsBinding.tlOrderDetailsOrderStatus.addOnTabSelectedListener(
        new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
            mStatusViewPager.setOnSelectView(mOrderDetailsBinding.tlOrderDetailsOrderStatus,
                tab.getPosition());
            switch (tab.getPosition()) {
              case ZERO:
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                Predicate<Products> conditionPending =
                    orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                        != ACCEPTED;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(conditionPending);
                }
                if (presenter.getStoreType() == RESTAURENT) {
                  addFoodItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == PHARMACY) {
                  addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == GROCERY) {
                  addGroceryItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else {
                  addItems(mTabItemsList,
                      mOrderDetails.getData().getAccounting().getCurrencySymbol(),
                      mOrderDetails.getData().getStoreType());
                }
                mOrderDetailsBinding.accountDetails.llItemDetails.setVisibility(
                    mTabItemsList.size() > ZERO ? View.VISIBLE : View.GONE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(
                    mTabItemsList.size() > ZERO ? View.GONE : View.VISIBLE);
                EcomUtil.printLog("exe" + "mTabItemsListZERO" + mTabItemsList.size());
                break;
              case ONE:
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                Predicate<Products> conditionPack = null;
                if (presenter.getStoreType() == RESTAURENT) {
                  conditionPack =
                      orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                          != CANCELLED;
                } else if (presenter.getStoreType() == PHARMACY
                    || presenter.getStoreType() == GROCERY) {
                  conditionPack =
                      orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                          != CHECKED;
                } else {
                  conditionPack =
                      orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                          != PACKED;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(conditionPack);
                }
                if (presenter.getStoreType() == RESTAURENT) {
                  addFoodItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == GROCERY) {
                  addGroceryItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == PHARMACY) {
                  addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else {
                  addItems(mTabItemsList,
                      mOrderDetails.getData().getAccounting().getCurrencySymbol(),
                      mOrderDetails.getData().getStoreType());
                }
                mOrderDetailsBinding.accountDetails.llItemDetails.setVisibility(
                    mTabItemsList.size() > ZERO ? View.VISIBLE : View.GONE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(
                    mTabItemsList.size() > ZERO ? View.GONE : View.VISIBLE);
                EcomUtil.printLog("exe" + "mTabItemsListONE" + mTabItemsList.size());
                break;
              case TWO:
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                Predicate<Products> conditionRemoved =
                    orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                        != IN_REVIEW;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(conditionRemoved);
                }
                if (presenter.getStoreType() == RESTAURENT) {
                  addFoodItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == PHARMACY) {
                  for (int i = 0; i < mTabItemsList.size(); i++) {
                    Products products = mTabItemsList.get(i);
                    products.setItemType(FOUR);
                  }
                  addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == GROCERY) {
                  for (int i = 0; i < mTabItemsList.size(); i++) {
                    Products products = mTabItemsList.get(i);
                    products.setItemType(FOUR);
                  }
                  addGroceryItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else {
                  addItems(mTabItemsList,
                      mOrderDetails.getData().getAccounting().getCurrencySymbol(),
                      mOrderDetails.getData().getStoreType());
                }
                mOrderDetailsBinding.accountDetails.llItemDetails.setVisibility(View.GONE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(
                    mTabItemsList.size() > ZERO ? View.GONE : View.VISIBLE);
                EcomUtil.printLog("exe" + "mTabItemsListTWO" + mTabItemsList.size());
                break;
              case THREE:
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                Predicate<Products> conditionUnAvailable =
                    orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                        != CANCELLED;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(conditionUnAvailable);
                }
                if (presenter.getStoreType() == RESTAURENT) {
                  addFoodItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == PHARMACY) {
                  addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else if (presenter.getStoreType() == GROCERY) {
                  addGroceryItems(mTabItemsList, mOrderDetails.getData().getStoreType());
                } else {
                  addItems(mTabItemsList,
                      mOrderDetails.getData().getAccounting().getCurrencySymbol(),
                      mOrderDetails.getData().getStoreType());
                }
                mOrderDetailsBinding.accountDetails.llItemDetails.setVisibility(
                    mTabItemsList.size() > ZERO ? View.GONE : View.VISIBLE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(
                    mTabItemsList.size() > ZERO ? View.GONE : View.VISIBLE);
                EcomUtil.printLog("exe" + "mTabItemsListTWO" + mTabItemsList.size());
                break;
            }
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {
            mStatusViewPager.setUnSelectView(mOrderDetailsBinding.tlOrderDetailsOrderStatus,
                tab.getPosition());
          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {
          }
        });
  }

  /**
   * <h>addItems</h>
   * <p>Add item ui related to the order</p>
   *
   * @param products  Array mItems related tot hat order
   * @param currency  Currency symbol of the store
   * @param storeType Store type of the order
   */
  @SuppressLint("SetTextI18n")
  public void addItems(final ArrayList<Products> products, String currency,
      String storeType) {
    if (products != null && products.size() > ZERO) {
      this.mItems.clear();
      this.mItems.addAll(products);
      mItemsArrayList.clear();
      mItemsArrayList.addAll(products);
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
      float subTotal = ZERO;
      mAddOnCount = ZERO;
      LayoutInflater layoutInflater =
          (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      boolean b = storeType != null && (storeType.equals(String.valueOf(COMPLETED))
          || storeType.equals(String.valueOf(READY_FOR_PICK_UP)));
      if (b) {
        mOrderDetailsBinding.accountDetails.tvOrderDetailsPrice.setText(
            getResources().getString(R.string.quantity));
        mOrderDetailsBinding.accountDetails.llOrderDetailsDeliveryCharges.setVisibility(
            View.VISIBLE);
      }
      for (int index = ZERO; index < products.size(); index++) {
        assert layoutInflater != null;
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.item_unavailable_single_row, null);
        TextView tvItemDetailsProductName = view.findViewById(
            R.id.tvItemUnAvailableProductName);
        TextView tvItemSizeAndColor = view.findViewById(
            R.id.tvItemSizeAndColor);
        TextView tvItemQty = view.findViewById(
            R.id.tvItemQty);
        TextView tvItemUnAvailableViewMore = view.findViewById(
            R.id.tvItemUnAvailableViewMore);
        TextView tvItemDetailsProductPrice = view.findViewById(
            R.id.tvItemUnAvailableProductPrice);
        TextView tvItemDetailsItemPack = view.findViewById(
            R.id.tvItemUnAvailableItemPack);
        TextView tvItemDetailsItemUnavailable = view.findViewById(
            R.id.tvItemUnAvailableUnavailable);
        TextView tvItemDetailsItemSubstitute = view.findViewById(
            R.id.tvItemUnAvailableItemSubstitute);
        ImageView ivItemImage = view.findViewById(R.id.ivItemUnAvailableProductImage);
        HorizontalScrollView hsItemDetailStatus = view.findViewById(
            R.id.hsItemUnAvailableStatus);
        View viewItemDetailsViewWhiteLight = view.findViewById(
            R.id.viewItemUnAvailableWhiteLight);
        View viewItemDetailsViewDashed = view.findViewById(R.id.viewItemUnAvailableDashed);
        String itemName = products.get(index).getName();
        viewItemDetailsViewWhiteLight.setVisibility(View.GONE);
        tvItemDetailsProductName.setText(itemName);
        if (products.get(index).getAttributes() != null && products.get(
            index).getAttributes().size() > ZERO) {
          StringBuilder attributeName = new StringBuilder();
          for (int i = ZERO; i < products.get(index).getAttributes().size(); i++) {
            if (products.get(index).getAttributes().get(i).getValue() != null && !products.get(
                index).getAttributes().get(
                i).getValue().isEmpty()) {
              attributeName.append(products.get(index).getAttributes().get(i).getAttrname()).append(
                  ": ").append(
                  products.get(index).getAttributes().get(i).getValue()).append(" ");
            }
            if (i == ONE) {
              break;
            }
          }
          tvItemSizeAndColor.setText(attributeName.toString());
          tvItemUnAvailableViewMore.setVisibility(
              products.get(index).getAttributes().size() > TWO ? View.VISIBLE : View.GONE);
          int finalIndex2 = index;
          tvItemUnAvailableViewMore.setOnClickListener(
              v -> showAttributeActivity(products.get(finalIndex2).getAttributes()));
        }
        tvItemQty.setText(
            String.format("%s %s", getResources().getString(R.string.qty), products.get(
                index).getQuantity().getValue()));
        ArrayList<AddOnGroup> addOnGroupArrayList = new ArrayList<>();
        RecyclerView rvItemDetailsAddOns = view.findViewById(R.id.rvItemUnAvailableAddOns);
        rvItemDetailsAddOns.setLayoutManager(new LinearLayoutManager(this));
        rvItemDetailsAddOns.setHasFixedSize(TRUE);
        AddOnsAdapter addOnsAdapter = new AddOnsAdapter(addOnGroupArrayList, mCurrencySymbol);
        rvItemDetailsAddOns.setAdapter(addOnsAdapter);
        addOnGroupArrayList.clear();
        hsItemDetailStatus.setVisibility(
            Integer.parseInt(mStatus) == ACCEPTED ? View.VISIBLE : View.GONE);
        if (products.get(index).getStatus().getStatus() != null && Integer.parseInt(mStatus)
            == ACCEPTED) {
          if (Integer.parseInt(products.get(index).getStatus().getStatus()) == NEW
              || Integer.parseInt(products.get(index).getStatus().getStatus()) == PACKED
              || Integer.parseInt(products.get(index).getStatus().getStatus()) == CANCELLED) {
            hsItemDetailStatus.setVisibility(View.GONE);
          }
        }
        int finalIndex1 = index;
        tvItemDetailsItemPack.setOnClickListener(v -> {
          orderNumberBtmSheet.setItemDetails(products.get(finalIndex1), FALSE, TRUE);
          orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
        });
        tvItemDetailsItemUnavailable.setOnClickListener(v -> {
          orderNumberBtmSheet.setItemDetails(products.get(finalIndex1), FALSE, FALSE);
          orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
        });
        tvItemDetailsItemSubstitute.setOnClickListener(v -> {
          Intent intent = new Intent(this, ProductUnavailableSubstituteActivity.class);
          intent.putExtra(STORE_CATEGORY_ID, mOrderDetails.getData().getStoreCategoryId());
          intent.putExtra(ITEM_DATA, products.get(finalIndex1));
          intent.putExtra(M_CURRENCY_SYMBOL, mCurrencySymbol);
          intent.putExtra(ITEM_AVAILABLE, FALSE);
          startActivity(intent);
        });
        if (products.get(index).getImages() != null) {
          Glide.with(this)
              .load(products.get(index).getImages().getMedium())
              .into(ivItemImage);
        }
        if (products.get(index).getAddOns() != null &&
            products.get(index).getAddOns().size() > ZERO) {
          for (int i = ZERO; i < products.get(index).getAddOns().size(); i++) {
            AddOns addOn = products.get(index).getAddOns().get(i);
            for (int j = ZERO; j < addOn.getAddOnGroup().size(); j++) {
              mAddOnCount += (!addOn.getAddOnGroup().get(j).getPrice().equals("")) ?
                  Double.parseDouble(addOn.getAddOnGroup().get(j).getPrice()) :
                  ZERO;
              addOnGroupArrayList.add(addOn.getAddOnGroup().get(j));
            }
          }
        }
        if (addOnGroupArrayList.size() > ZERO) {
          rvItemDetailsAddOns.setVisibility(View.VISIBLE);
          addOnsAdapter.notifyDataSetChanged();
        }
        int quantity;
        double unitPrice = ZERO;
        quantity = Integer.parseInt(products.get(index).getQuantity().getValue());
        if (products.get(index).getAccounting().getFinalTotal() != null) {
          unitPrice = products.get(index).getAccounting().getFinalTotal();
        }
        subTotal += quantity * unitPrice;
        final int finalIndex = index;
        tvItemDetailsProductPrice.setText(
            String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(products.get(
                index).getAccounting().getFinalTotal() + "")));
        mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(view);
        if (index == products.size() - ONE) {
          viewItemDetailsViewDashed.setVisibility(View.GONE);
        }
      }
      presenter.getFares((subTotal + mAddOnCount));
    } else {
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
    }
  }

  /**
   * this method used to show the food item
   *
   * @param products this indicates products array
   */
  public void addFoodItems(final ArrayList<Products> products, String storeType) {
    if (products != null && products.size() > ZERO) {
      this.mItems.clear();
      this.mItems.addAll(products);
      mItemsArrayList.clear();
      mItemsArrayList.addAll(products);
      mAttributesList.clear();
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
      float subTotal = ZERO;
      mAddOnCount = ZERO;
      LayoutInflater layoutInflater =
          (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      LayoutInflater inflaterAttribute = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      boolean b = storeType != null && (storeType.equals(String.valueOf(COMPLETED))
          || storeType.equals(String.valueOf(READY_FOR_PICK_UP)));
      if (b) {
        mOrderDetailsBinding.accountDetails.tvOrderDetailsPrice.setText(
            getResources().getString(R.string.quantity));
        mOrderDetailsBinding.accountDetails.llOrderDetailsDeliveryCharges.setVisibility(
            View.VISIBLE);
      }
      for (int index = ZERO; index < products.size(); index++) {
        assert layoutInflater != null;
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.item_food_single_row, null);
        TextView tvItemDetailsProductName = view.findViewById(
            R.id.tvItemUnAvailableProductName);
        TextView tvItemSizeAndColor = view.findViewById(R.id.tvItemSizeAndColor);
        TextView tvItemAttributes = view.findViewById(R.id.tvItemAttributes);
        TextView tvItemQty = view.findViewById(R.id.tvItemQty);
        TextView tvItemUnAvailable = view.findViewById(R.id.tvItemUnAvailable);
        TextView tvItemDetailsProductPrice = view.findViewById(R.id.tvItemUnAvailableProductPrice);
        LinearLayout attributeGroup = view.findViewById(R.id.llFoodAttributeGroup);
        Group groupReason = view.findViewById(R.id.groupReason);
        TextView tvReason = view.findViewById(R.id.tvReason);
        HorizontalScrollView hsItemDetailStatus = view.findViewById(R.id.hsItemUnAvailableStatus);
        View viewItemDetailsViewDashed = view.findViewById(R.id.viewItemUnAvailableDashed);
        String itemName = products.get(index).getName();
        tvItemDetailsProductName.setText(itemName);
        tvItemQty.setText(
            String.format("%s %s * %s %s", getResources().getString(R.string.qty), products.get(
                index).getQuantity().getValue(), mItemsArrayList.get(
                index).getCurrencySymbol(), Utility.roundOfDoubleValue(mItemsArrayList.get(
                index).getSingleUnitPrice().getUnitPrice() + "")));
        mAttributesList.clear();
        mAttributesList.addAll(products.get(index).getAttributes());
        Predicate<Attributes> conditionIsAddOn =
            orderCountData -> orderCountData.isAddOn();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mAttributesList.removeIf(conditionIsAddOn);
        }
        if (mAttributesList != null && mAttributesList.size() > ZERO) {
          StringBuilder value = new StringBuilder();
          for (int j = ZERO; j < mAttributesList.size(); j++) {
            Attributes attributes = mAttributesList.get(j);
            String addOnName = EcomUtil.getColoredSpanned(attributes.getAttrname(), BLACK);
            String attrValue = EcomUtil.getColoredSpanned(attributes.getValue(), LIGHT_BLACK);
            if (attributes.getValue() != null && !attributes.getValue().isEmpty()) {
              value.append(String.format("%s:%s | ", addOnName, attrValue));
            }
          }
          if (value.length() > ZERO) {
            tvItemAttributes.setText(Html.fromHtml(value.substring(ZERO, value.length() - TWO)));
          } else {
            tvItemAttributes.setVisibility(View.GONE);
          }
        }
        hsItemDetailStatus.setVisibility(
            Integer.parseInt(mStatus) == ACCEPTED ? View.VISIBLE : View.GONE);
        if (products.get(index).getStatus().getStatus() != null && Integer.parseInt(mStatus)
            == ACCEPTED) {
          if (Integer.parseInt(products.get(index).getStatus().getStatus()) == NEW
              || Integer.parseInt(products.get(index).getStatus().getStatus()) == PACKED
              || Integer.parseInt(products.get(index).getStatus().getStatus()) == CANCELLED) {
            hsItemDetailStatus.setVisibility(View.GONE);
          }
        }
        attributeGroup.removeAllViews();
        mAttributesList.clear();
        mAttributesList.addAll(products.get(index).getAttributes());
        Predicate<Attributes> conditionAddOn =
            orderCountData -> !orderCountData.isAddOn();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mAttributesList.removeIf(conditionAddOn);
        }
        LinkedHashMap<String, ArrayList<Attributes>> mHashMap = new LinkedHashMap<>();
        if (mAttributesList != null && mAttributesList.size() > ZERO) {
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
              attributeGroup.addView(attributeView);
            }
          }
        }
        if (Integer.parseInt(mItemsArrayList.get(index).getStatus().getStatus()) == CANCELLED) {
          tvReason.setText(mItemsArrayList.get(index).getReason());
          groupReason.setVisibility(mItemsArrayList.get(index).getReason() != null
              && !mItemsArrayList.get(index).getReason().isEmpty() ? View.VISIBLE : View.GONE);
        }
        int finalIndex1 = index;
        tvItemUnAvailable.setOnClickListener(v -> {
          Utility.printLog("exe" + "products " + products.get(finalIndex1).toString());
          orderNumberBtmSheet.setItemDetails(products.get(finalIndex1), FALSE, FALSE);
          orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
        });
        int quantity;
        double unitPrice = ZERO;
        quantity = Integer.parseInt(products.get(index).getQuantity().getValue());
        if (products.get(index).getAccounting().getFinalTotal() != null) {
          unitPrice = products.get(index).getAccounting().getFinalTotal();
        }
        subTotal += quantity * unitPrice;
        final int finalIndex = index;
        tvItemDetailsProductPrice.setText(
            String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(products.get(
                index).getAccounting().getUnitPrice() + "")));
        mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(view);
        if (index == products.size() - ONE) {
          viewItemDetailsViewDashed.setVisibility(View.GONE);
        }
      }
      presenter.getFares((subTotal + mAddOnCount));
    } else {
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
    }
  }

  /**
   * this method used to show the pharmacy items
   *
   * @param products this indicates products array
   */
  public void addPharmacyItems(ArrayList<Products> products, String storeType) {
    if (products != null && products.size() > ZERO) {
      EcomUtil.printLog("exe" + "products" + products.size());
      mItemsArrayList.clear();
      mItemsArrayList.addAll(products);
      mAttributesList.clear();
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
      float subTotal = ZERO;
      mAddOnCount = ZERO;
      LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      boolean b = storeType != null && (storeType.equals(String.valueOf(COMPLETED))
          || storeType.equals(String.valueOf(READY_FOR_PICK_UP)));
      if (b) {
        mOrderDetailsBinding.accountDetails.tvOrderDetailsPrice.setText(
            getResources().getString(R.string.quantity));
        mOrderDetailsBinding.accountDetails.llOrderDetailsDeliveryCharges.setVisibility(
            View.VISIBLE);
      }
      for (int index = ZERO; index < mItemsArrayList.size(); index++) {
        final int finalIndex = index;
        EcomUtil.printLog("exe" + "finalIndex      " + finalIndex + " type " + mItemsArrayList.get(
            index).getItemType());
        assert layoutInflater != null;
        switch (mItemsArrayList.get(index).getItemType()) {
          case ZERO:
            View view = layoutInflater.inflate(R.layout.item_pharmacy_single_row, null);
            TextView tvItemDetailsProductName = view.findViewById(
                R.id.tvItemUnAvailableProductName);
            ImageView ivItemImage = view.findViewById(R.id.ivItemUnAvailableProductImage);
            TextView tvItemDosage = view.findViewById(R.id.tvItemDosage);
            TextView tvItemReject = view.findViewById(R.id.tvItemReject);
            TextView tvItemSubStitute = view.findViewById(R.id.tvItemSubStitute);
            TextView tvItemUnAvailableItemPack = view.findViewById(R.id.tvItemUnAvailableItemPack);
            TextView tvItemUnAvailable = view.findViewById(R.id.tvItemUnAvailableUnavailable);
            TextView tvItemTabletCount = view.findViewById(R.id.tvItemTabletCount);
            TextView tvItemStripCount = view.findViewById(R.id.tvItemStripCount);
            TextView tvItemScratchPrice = view.findViewById(R.id.tvItemScratchPrice);
            TextView tvItemQty = view.findViewById(R.id.tvItemQty);
            TextView tvAisleEdit = view.findViewById(R.id.tvAisleEdit);
            TextView tvAisle = view.findViewById(R.id.tvAisle);
            TextView tvItemDetailsProductPrice = view.findViewById(
                R.id.tvItemUnAvailableProductPrice);
            HorizontalScrollView hsItemDetailStatus = view.findViewById(
                R.id.hsItemUnAvailableStatus);
            View viewItemDetailsViewDashed = view.findViewById(R.id.viewItemUnAvailableDashed);
            Group groupReason = view.findViewById(R.id.groupReason);
            Group groupAsile = view.findViewById(R.id.groupAsile);
            TextView tvReason = view.findViewById(R.id.tvReason);
            String itemName = mItemsArrayList.get(index).getName();
            tvItemDetailsProductName.setText(itemName);
            if (mItemsArrayList.get(index).getPackaging() != null) {
              Packaging packaging = mItemsArrayList.get(index).getPackaging();
              if (packaging.getUnitValue() != ZERO) {
                tvItemTabletCount.setText(String.format("%s %s %s",
                    packaging.getUnitValue(), packaging.getUnitType(), packaging.getPackingType()));
              } else {
                tvItemTabletCount.setVisibility(View.GONE);
              }
            } else {
              tvItemTabletCount.setVisibility(View.GONE);
            }
            if (mItemsArrayList.get(index).getQuantity() != null) {
              tvItemQty.setText(
                  String.format("%s %s %s", getResources().getString(R.string.qty),
                      mItemsArrayList.get(index).getQuantity().getValue(), mItemsArrayList.get(
                          index).getQuantity().getUnit()));
              tvItemStripCount.setText(String.format("%s %s * %s %s", mItemsArrayList.get(
                  index).getQuantity().getValue(), mItemsArrayList.get(
                  index).getQuantity().getUnit(), mItemsArrayList.get(index).getCurrencySymbol(),
                  Utility.roundOfDoubleValue(mItemsArrayList.get(
                      index).getSellerSingleUnitPrice().getFinalUnitPrice() + "")));
              if (mItemsArrayList.get(index).getSellerSingleUnitPrice() != null) {
                if (mItemsArrayList.get(index).getSellerSingleUnitPrice().getOfferDiscount()
                    > ZERO) {
                  tvItemScratchPrice.setText(
                      String.format("%s %s", mItemsArrayList.get(index).getCurrencySymbol(), Utility
                          .roundOfDoubleValue(mItemsArrayList.get(
                              index).getSellerSingleUnitPrice().getUnitPrice() + "")));
                  EcomUtil.strikeThroughText(tvItemScratchPrice);
                }
              }
            }
            mAttributesList.clear();
            mAttributesList.addAll(products.get(index).getAttributes());
            Predicate<Attributes> conditionIsAddOn =
                orderCountData -> orderCountData.isAddOn();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              mAttributesList.removeIf(conditionIsAddOn);
            }
            if (mAttributesList != null && mAttributesList.size() > ZERO) {
              StringBuilder value = new StringBuilder();
              for (int j = ZERO; j < mAttributesList.size(); j++) {
                Attributes attributes = mAttributesList.get(j);
                String addOnName = EcomUtil.getColoredSpanned(attributes.getAttrname(),
                    LIGHT_BLACK);
                String attrValue = EcomUtil.getColoredSpanned(attributes.getValue(), BLACK);
                if (attributes.getValue() != null && !attributes.getValue().isEmpty()) {
                  value.append(addOnName).append(":").append(attrValue).append(
                      attributes.getMeasurementUnit()).append(" | ");
                }
              }
              if (value.length() > ZERO) {
                tvItemDosage.setText(Html.fromHtml(value.substring(ZERO, value.length() - TWO)));
              } else {
                tvItemDosage.setVisibility(View.GONE);
              }
            }
            hsItemDetailStatus.setVisibility(
                Integer.parseInt(mStatus) == ACCEPTED || Integer.parseInt(mStatus) == NEW
                    || Integer.parseInt(mStatus) == CHECKED ? View.VISIBLE
                    : View.GONE);
            EcomUtil.printLog("exe" + "status      " + Integer.parseInt(mStatus));
            EcomUtil.printLog("exe" + "listStatus      " + mItemsArrayList.get(
                index).getStatus().getStatus());
            if (mItemsArrayList.get(index).getStatus().getStatus() != null && Integer.parseInt(
                mStatus) == NEW) {
              hsItemDetailStatus.setVisibility(View.VISIBLE);
              tvItemReject.setVisibility(View.VISIBLE);
              tvItemUnAvailableItemPack.setVisibility(View.GONE);
              tvItemUnAvailable.setVisibility(View.GONE);
            } else if (mItemsArrayList.get(index).getStatus().getStatus() != null
                && Integer.parseInt(mStatus) == ACCEPTED) {
              hsItemDetailStatus.setVisibility(View.VISIBLE);
              tvItemReject.setVisibility(View.GONE);
              tvItemSubStitute.setVisibility(View.VISIBLE);
              tvItemUnAvailableItemPack.setVisibility(View.VISIBLE);
              tvItemUnAvailable.setVisibility(View.VISIBLE);
              if (Integer.parseInt(mItemsArrayList.get(index).getStatus().getStatus()) == NEW
                  || Integer.parseInt(mItemsArrayList.get(index).getStatus().getStatus()) == PACKED
                  || Integer.parseInt(mItemsArrayList.get(index).getStatus().getStatus())
                  == CANCELLED || Integer.parseInt(mItemsArrayList.get(
                  index).getStatus().getStatus())
                  == CHECKED) {
                hsItemDetailStatus.setVisibility(View.GONE);
              }
            } else if (mItemsArrayList.get(index).getStatus().getStatus() != null
                && Integer.parseInt(mStatus) == CHECKED) {
              hsItemDetailStatus.setVisibility(View.GONE);
            }
            tvItemUnAvailableItemPack.setText((mItemsArrayList.get(index).getBarcode() != null
                && !mItemsArrayList.get(index).getBarcode().isEmpty()) ? getResources().getString(
                R.string.scan) : getResources().getString(R.string.forcePick));
            if (mItemsArrayList.get(index).getStatus().getStatus() != null && Integer.parseInt(
                mItemsArrayList.get(
                    index).getStatus().getStatus()) == CANCELLED && Integer.parseInt(mStatus)
                != CHECKED) {
              EcomUtil.printLog("exe" + "reason" + mItemsArrayList.get(index).getReason());
              tvReason.setText(mItemsArrayList.get(index).getReason());
              groupReason.setVisibility(mItemsArrayList.get(index).getReason() != null
                  && !mItemsArrayList.get(
                  index).getReason().isEmpty() ? View.VISIBLE : View.GONE);
              hsItemDetailStatus.setVisibility(View.GONE);
            }
            if (mStatus != null && Integer.parseInt(mStatus) >= PACKED) {
              groupAsile.setVisibility(View.GONE);
            }
            if (mItemsArrayList.get(index).getStatus().getStatus() != null && Integer.parseInt(
                mItemsArrayList.get(
                    index).getStatus().getStatus()) == PENDING) {
              tvReason.setText(mItemsArrayList.get(index).getReason());
              groupReason.setVisibility(mItemsArrayList.get(index).getReason() != null
                  && !mItemsArrayList.get(index).getReason().isEmpty() ? View.VISIBLE : View.GONE);
              hsItemDetailStatus.setVisibility(View.GONE);
            }
            if (mItemsArrayList.get(index).getImages() != null) {
              Glide.with(this)
                  .load(mItemsArrayList.get(index).getImages().getMedium())
                  .into(ivItemImage);
            }
            if (products.get(index).getAisle() != null && !products.get(
                index).getAisle().isEmpty() && products.get(index).getShelf() != null
                && !products.get(
                index).getShelf().isEmpty()) {
              tvAisle.setText(String.format("%s. %s. %s",
                  products.get(index).getAisle(),
                  products.get(index).getShelf(),
                  products.get(index).getDirections()));
            } else {
              groupAsile.setVisibility(View.GONE);
            }
            tvItemReject.setOnClickListener(v -> {
              mIndex = finalIndex;
              EcomUtil.printLog("exe" + "  mIndex" + mIndex + "  finalIndex" + finalIndex);
              Intent intent = new Intent(OrderDetailsForMobile.this,
                  ProductUnavailableSubstituteActivity.class);
              intent.putExtra(ORDER_ID, FALSE);
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(finalIndex));
              intent.putExtra(STORE_CATEGORY_ID, mOrderDetails.getData().getStoreCategoryId());
              intent.putExtra(REASON_ID, REASON_PRODUCT_CANCELLED);
              intent.putExtra(ITEM_AVAILABLE, TRUE);
              startActivityForResult(intent, PHARMACY_REJECT);
            });
            tvItemSubStitute.setOnClickListener(v -> {
              EcomUtil.printLog("exe" + "  mIndex" + mIndex + "  finalIndex" + finalIndex);
              Intent intent = new Intent(OrderDetailsForMobile.this,
                  ProductSubStituteActivity.class);
              intent.putExtra(STORE_ORDER_ID, mOrderId);
              intent.putExtra(PRODUCT_ORDER_ID,
                  mItemsArrayList.get(finalIndex).getProductOrderId());
              intent.putExtra(PRODUCT, mItemsArrayList.get(finalIndex));
              intent.putExtra(PRODUCT_ID_TXT,
                  mItemsArrayList.get(finalIndex).getProductId());
              intent.putExtra(PARENT_PRODUCT_ID,
                  mItemsArrayList.get(finalIndex).getCentralProductId());
              intent.putExtra(QUANTITY, Integer.parseInt(mItemsArrayList.get(
                  finalIndex).getQuantity().getValue()));
              startActivityForResult(intent, PRODUCT_SUBSTITUDE);
            });
            tvItemUnAvailableItemPack.setOnClickListener(v -> {
              EcomUtil.printLog("exe" + "finalIndex1  " + finalIndex);
              mIndex = finalIndex;
              if ((mItemsArrayList.get(finalIndex).getBarcode() != null
                  && !mItemsArrayList.get(finalIndex).getBarcode().isEmpty())) {
                Intent intent = new Intent(this, BarCodePreviewActivity.class);
                intent.putExtra(CURRENCY_SYMBOL,
                    mItemsArrayList.get(finalIndex).getCurrencySymbol());
                intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(finalIndex).getCurrencyCode());
                intent.putExtra(ITEM_DATA, mItemsArrayList.get(finalIndex));
                intent.putExtra(PRODUCT_ID, mItemsArrayList.get(finalIndex).getProductId());
                intent.putExtra(PARENT_PRODUCT_ID,
                    mItemsArrayList.get(finalIndex).getCentralProductId());
                intent.putExtra(PRODUCT_ORDER_ID,
                    mItemsArrayList.get(finalIndex).getProductOrderId());
                intent.putExtra(QUANTITY, Integer.parseInt(mItemsArrayList.get(
                    finalIndex).getQuantity().getValue()));
                startActivityForResult(intent, BARCODE_REQUEST);
              } else {
                mIndex = finalIndex;
                Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
                intent.putExtra(CURRENCY_SYMBOL, mItemsArrayList.get(mIndex).getCurrencySymbol());
                intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(mIndex).getCurrencyCode());
                intent.putExtra(ORDER_ID, mOrderId);
                intent.putExtra(OPEN_CAMERA, TRUE);
                intent.putExtra(ITEM_DATA, mItemsArrayList.get(mIndex));
                startActivityForResult(intent, FORCE_PICK_REQUEST);
              }
            });
            tvItemUnAvailable.setOnClickListener(v -> {
              mIndex = finalIndex;
              orderNumberBtmSheet.setItemDetails(mItemsArrayList.get(finalIndex), FALSE,
                  FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            tvAisleEdit.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this,
                  UpdateAsileActivity.class);
              intent.putExtra(PARENT_PRODUCT_ID,
                  mItemsArrayList.get(finalIndex).getCentralProductId());
              intent.putExtra(AISLE, mItemsArrayList.get(finalIndex).getAisle());
              intent.putExtra(SHELF, mItemsArrayList.get(finalIndex).getShelf());
              intent.putExtra(SECTOR, mItemsArrayList.get(finalIndex).getDirections());
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(finalIndex));
              startActivityForResult(intent, UPDATE_ASILE);
            });
            int quantity = ONE;
            double unitPrice = ZERO;
            if (mItemsArrayList.get(index).getQuantity() != null) {
              quantity = Integer.parseInt(mItemsArrayList.get(index).getQuantity().getValue());
            }
            if (mItemsArrayList.get(index).getAccounting().getFinalTotal() != null) {
              unitPrice = mItemsArrayList.get(index).getAccounting().getFinalTotal();
            }
            subTotal += quantity * unitPrice;
            if (mItemsArrayList.get(index).getQuantity() != null) {
              tvItemDetailsProductPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      Utility.roundOfDoubleValue(mItemsArrayList.get(
                          index).getSellerAccounting().getTaxableAmount() + "")));
            }
            if (index == mItemsArrayList.size() - ONE) {
              viewItemDetailsViewDashed.setVisibility(View.GONE);
            }
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(view);
            break;
          case ONE:
            View viewWithPres = layoutInflater.inflate(R.layout.item_prescription_count, null);
            TextView tvItemPrescriptionCount = viewWithPres.findViewById(
                R.id.tvItemPrescriptionCount);
            if (mItemsArrayList.get(index).getPrescriptionCount() > ZERO) {
              tvItemPrescriptionCount.setText(
                  String.format("%s (%d)", getResources().getString(R.string.itemsWithPrescription),
                      mItemsArrayList.get(index).getPrescriptionCount()));
              mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(viewWithPres);
            }
            break;
          case TWO:
            View viewWithoutPres = layoutInflater.inflate(R.layout.item_prescription_count, null);
            TextView tvItemWithoutPrescriptionCount = viewWithoutPres.findViewById(
                R.id.tvItemPrescriptionCount);
            if (mItemsArrayList.get(index).getPrescriptionCount() > ZERO) {
              tvItemWithoutPrescriptionCount.setText(
                  String.format("%s (%d)",
                      getResources().getString(R.string.itemsWithoutPrescription),
                      mItemsArrayList.get(index).getPrescriptionCount()));
              mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(
                  viewWithoutPres);
            }
            break;
          case THREE:
            View viewRejected = layoutInflater.inflate(R.layout.item_prescription_count, null);
            TextView tvRejected = viewRejected.findViewById(R.id.tvItemPrescriptionCount);
            tvRejected.setText(getResources().getString(R.string.rejectedItems));
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(viewRejected);
            break;
          case FIVE:
            View viewAccepted = layoutInflater.inflate(R.layout.item_prescription_count, null);
            TextView tvAccepted = viewAccepted.findViewById(R.id.tvItemPrescriptionCount);
            tvAccepted.setText(getResources().getString(R.string.acceptedItems));
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(viewAccepted);
            break;
          case FOUR:
            View viewSubStitude = layoutInflater.inflate(R.layout.item_product_substitude, null);
            ImageView ivItemAddProduct = viewSubStitude.findViewById(R.id.ivItemAddProduct);
            Group groupConfirmed = viewSubStitude.findViewById(R.id.groupConfirmed);
            Group groupAddedPending = viewSubStitude.findViewById(R.id.groupAddedPending);
            Group groupSubstitute = viewSubStitude.findViewById(R.id.groupSubstitute);
            Group groupReplaceSubstituteItem = viewSubStitude.findViewById(
                R.id.groupReplaceSubstituteItem);
            TextView tvItemPending = viewSubStitude.findViewById(R.id.tvItemPending);
            TextView tvApproved = viewSubStitude.findViewById(R.id.tvApproved);
            TextView tvSubstitute = viewSubStitude.findViewById(R.id.tvSubstitute);
            View viewItemDashed = viewSubStitude.findViewById(R.id.viewItemDashed);
            TextView tvRemove = viewSubStitude.findViewById(R.id.tvRemove);
            TextView tvForcePick = viewSubStitude.findViewById(R.id.tvForcePick);
            TextView tvItemReviewTiming = viewSubStitude.findViewById(R.id.tvItemReviewTiming);
            TextView tvItemAddedReviewTiming = viewSubStitude.findViewById(
                R.id.tvItemAddedReviewTiming);
            ImageView ivItemReplacedProduct = viewSubStitude.findViewById(
                R.id.ivItemReplacedProduct);
            TextView tvItemName = viewSubStitude.findViewById(R.id.tvItemName);
            TextView tvItemReplacedItemName = viewSubStitude.findViewById(
                R.id.tvItemReplacedItemName);
            TextView tvItemBrand = viewSubStitude.findViewById(R.id.tvItemBrand);
            TextView tvItemReplacedBrand = viewSubStitude.findViewById(R.id.tvItemReplacedBrand);
            TextView tvItemPrice = viewSubStitude.findViewById(R.id.tvItemPrice);
            TextView tvItemReplacedPrice = viewSubStitude.findViewById(R.id.tvItemReplacedPrice);
            TextView tvForcePickReplace = viewSubStitude.findViewById(R.id.tvForcePickReplace);
            TextView tvRemoveReplace = viewSubStitude.findViewById(R.id.tvRemoveReplace);
            tvSubstitute.setVisibility(View.VISIBLE);
            boolean subsitute = mItemsArrayList.get(index).isSubsitute();
            subsituteWith subsituteWith = mItemsArrayList.get(index).getSubsituteWith();
            if (subsitute) {
              if (subsituteWith.getImages() != null) {
                Glide.with(this)
                    .load(subsituteWith.getImages().getMedium())
                    .into(ivItemAddProduct);
              }
              tvSubstitute.setText(getResources().getString(R.string.substitude));
              tvItemName.setText(subsituteWith.getName());
              tvItemBrand.setText(
                  String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                      subsituteWith.getQuantity().getValue(), subsituteWith.getQuantity().getUnit(),
                      mItemsArrayList.get(index).getCurrencySymbol(),
                      Utility.roundOfDoubleValue(subsituteWith.getOriginalPrice() + "")));
              tvItemPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      subsituteWith.getOriginalPrice()));
              if (mItemsArrayList.get(index).getImages() != null) {
                Glide.with(this)
                    .load(mItemsArrayList.get(index).getImages().getMedium())
                    .into(ivItemReplacedProduct);
              }
              tvItemReplacedItemName.setText(mItemsArrayList.get(index).getName());
              if (mItemsArrayList.get(index).getQuantity() != null) {
                tvItemReplacedBrand.setText(
                    String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                        mItemsArrayList.get(
                            index).getQuantity().getValue(), mItemsArrayList.get(
                            index).getQuantity().getUnit(),
                        mItemsArrayList.get(index).getCurrencySymbol(),
                        Utility.roundOfDoubleValue(mItemsArrayList.get(
                            index).getSingleUnitPrice().getSubTotal() + "")));
              }
              tvItemReplacedPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      Utility.roundOfDoubleValue(mItemsArrayList.get(
                          index).getAccounting().getFinalTotal() + "")));
            } else {
              groupSubstitute.setVisibility(View.GONE);
              groupAddedPending.setVisibility(View.VISIBLE);
              groupConfirmed.setVisibility(View.VISIBLE);
              if (mItemsArrayList.get(index).getImages() != null) {
                Glide.with(this)
                    .load(mItemsArrayList.get(index).getImages().getMedium())
                    .into(ivItemAddProduct);
              }
              tvItemName.setText(mItemsArrayList.get(index).getName());
              if (mItemsArrayList.get(index).getQuantity() != null) {
                tvItemBrand.setText(
                    String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                        mItemsArrayList.get(
                            index).getQuantity().getValue(), mItemsArrayList.get(
                            index).getQuantity().getUnit(),
                        mItemsArrayList.get(index).getCurrencySymbol(),
                        Utility.roundOfDoubleValue(mItemsArrayList.get(
                            index).getSingleUnitPrice().getSubTotal() + "")));
              }
              tvItemPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      Utility.roundOfDoubleValue(mItemsArrayList.get(
                          index).getAccounting().getFinalTotal() + "")));
            }
            tvRemove.setOnClickListener(v -> {
              orderNumberBtmSheet.setItemDetails(mItemsArrayList.get(finalIndex), FALSE,
                  FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            tvForcePick.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
              intent.putExtra(CURRENCY_SYMBOL, mItemsArrayList.get(mIndex).getCurrencySymbol());
              intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(mIndex).getCurrencyCode());
              intent.putExtra(ORDER_ID, mOrderId);
              intent.putExtra(OPEN_CAMERA, FALSE);
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(mIndex));
              startActivityForResult(intent, FORCE_PICK_REQUEST);
            });
            tvForcePickReplace.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
              intent.putExtra(CURRENCY_SYMBOL, mItemsArrayList.get(mIndex).getCurrencySymbol());
              intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(mIndex).getCurrencyCode());
              intent.putExtra(ORDER_ID, mOrderId);
              intent.putExtra(OPEN_CAMERA, FALSE);
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(mIndex));
              startActivityForResult(intent, FORCE_PICK_REQUEST);
            });
            tvRemoveReplace.setOnClickListener(v -> {
              orderNumberBtmSheet.setItemDetails(mItemsArrayList.get(finalIndex), FALSE,
                  FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            if (mItemsArrayList.get(index).getStatus() != null && mItemsArrayList.get(
                index).getStatus().getExpiryTimeForConfirmation() != null) {
              EcomUtil.printLog("exe" + "timeStamp  " + mItemsArrayList.get(
                  index).getStatus().getExpiryTimeForConfirmation());
              Long tsLong = System.currentTimeMillis() / THOUSAND;
              String expiryTimeForConfirmation = mItemsArrayList.get(
                  index).getStatus().getExpiryTimeForConfirmation();
              long sec = Long.parseLong((expiryTimeForConfirmation)) - tsLong;
              EcomUtil.printLog(
                  "exe" + "final sec  " + sec);
              int finalIndex1 = index;
              CountDownTimer countDownTimer = new CountDownTimer((sec) * THOUSAND,
                  THOUSAND) {
                public void onTick(long millisUntilFinished) {
                  //here you can have your logic to set text to edit text
                  if (subsitute) {
                    tvItemReviewTiming.setText(EcomUtil.getTimerValue(millisUntilFinished));
                    groupConfirmed.setVisibility(View.GONE);
                    groupReplaceSubstituteItem.setVisibility(View.GONE);
                  } else {
                    tvItemAddedReviewTiming.setText(EcomUtil.getTimerValue(millisUntilFinished));
                    tvApproved.setText(getResources().getString(R.string.notResponding));
                    tvApproved.setTextColor(getResources().getColor(R.color.red));
                    groupReplaceSubstituteItem.setVisibility(View.GONE);
                    groupConfirmed.setVisibility(View.GONE);
                  }
                }

                public void onFinish() {
                  groupConfirmed.setVisibility(View.VISIBLE);
                  groupAddedPending.setVisibility(View.GONE);
                  groupSubstitute.setVisibility(View.GONE);
                  groupReplaceSubstituteItem.setVisibility(View.GONE);
                  if (subsitute) {
                    if (mItemsArrayList.get(finalIndex1).getImages() != null) {
                      Glide.with(ApplicationManager.getInstance())
                          .load(mItemsArrayList.get(finalIndex1).getImages().getMedium())
                          .into(ivItemReplacedProduct);
                    }
                    groupSubstitute.setVisibility(View.VISIBLE);
                    groupConfirmed.setVisibility(View.GONE);
                    groupReplaceSubstituteItem.setVisibility(View.VISIBLE);
                    tvItemPending.setText("");
                    tvItemReviewTiming.setText("");
                    tvItemReplacedItemName.setText(mItemsArrayList.get(finalIndex1).getName());
                    tvItemReplacedBrand.setText(
                        String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                            mItemsArrayList.get(
                                finalIndex1).getQuantity().getValue(), mItemsArrayList.get(
                                finalIndex1).getQuantity().getUnit(),
                            mItemsArrayList.get(finalIndex1).getCurrencySymbol(),
                            Utility.roundOfDoubleValue(mItemsArrayList.get(
                                finalIndex1).getSingleUnitPrice().getSubTotal() + "")));
                    tvItemReplacedPrice.setText(
                        String.format("%s %s", mCurrencySymbol,
                            Utility.roundOfDoubleValue(mItemsArrayList.get(
                                finalIndex1).getAccounting().getFinalTotal() + "")));
                  }
                }
              }.start();
            }
            if (index == mItemsArrayList.size() - ONE) {
              viewItemDashed.setVisibility(View.GONE);
            }
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(viewSubStitude);
            break;
        }
        presenter.getFares((subTotal + mAddOnCount));
      }
    }
  }

  /**
   * <h>addItems</h>
   * <p>Add item ui related to the order</p>
   *
   * @param products  Array mItems related tot hat order
   * @param storeType Store type of the order
   */
  @SuppressLint("SetTextI18n")
  public void addGroceryItems(final ArrayList<Products> products, String storeType) {
    if (products != null && products.size() > ZERO) {
      this.mItems.clear();
      this.mItems.addAll(products);
      mItemsArrayList.clear();
      mItemsArrayList.addAll(products);
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
      float subTotal = ZERO;
      mAddOnCount = ZERO;
      LayoutInflater layoutInflater =
          (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      boolean b = storeType != null && (storeType.equals(String.valueOf(COMPLETED))
          || storeType.equals(String.valueOf(READY_FOR_PICK_UP)));
      if (b) {
        mOrderDetailsBinding.accountDetails.tvOrderDetailsPrice.setText(
            getResources().getString(R.string.quantity));
        mOrderDetailsBinding.accountDetails.llOrderDetailsDeliveryCharges.setVisibility(
            View.VISIBLE);
      }
      for (int index = ZERO; index < products.size(); index++) {
        assert layoutInflater != null;
        final int finalIndex = index;
        switch (products.get(index).getItemType()) {
          case ZERO:
            @SuppressLint("InflateParams")
            View view = layoutInflater.inflate(R.layout.item_grocery_single_row, null);
            TextView tvItemDetailsProductName = view.findViewById(
                R.id.tvItemUnAvailableProductName);
            TextView tvItemSizeAndColor = view.findViewById(R.id.tvItemSize);
            TextView tvItemScan = view.findViewById(R.id.tvItemScan);
            TextView tvAisle = view.findViewById(R.id.tvAisle);
            TextView tvAisleEdit = view.findViewById(R.id.tvAisleEdit);
            TextView tvItemQtyAndPrice = view.findViewById(R.id.tvItemQtyAndPrice);
            TextView tvItemDetailsProductPrice = view.findViewById(
                R.id.tvItemUnAvailableProductPrice);
            TextView tvItemSubStitute = view.findViewById(R.id.tvProductSubstitute);
            TextView tvItemDetailsItemUnavailable = view.findViewById(
                R.id.tvItemUnAvailableUnavailable);
            ImageView ivItemImage = view.findViewById(R.id.ivItemUnAvailableProductImage);
            HorizontalScrollView hsItemDetailStatus = view.findViewById(
                R.id.hsItemUnAvailableStatus);
            Group groupAsile = view.findViewById(R.id.groupAsile);
            View viewItemDetailsViewDashed = view.findViewById(R.id.viewItemUnAvailableDashed);
            String itemName = products.get(index).getName();
            tvItemDetailsProductName.setText(itemName);
            if (products.get(index).getQuantity() != null) {
              tvItemQtyAndPrice.setText(String.format("%s %s * %s %s", products.get(
                  index).getQuantity().getValue(), products.get(
                  index).getQuantity().getUnit(), products.get(index).getCurrencySymbol(),
                  Utility.roundOfDoubleValue(products.get(
                      index).getSingleUnitPrice().getUnitPrice() + "")));
            }
            mAttributesList.clear();
            mAttributesList.addAll(products.get(index).getAttributes());
            Predicate<Attributes> conditionIsAddOn =
                orderCountData -> orderCountData.isAddOn();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              mAttributesList.removeIf(conditionIsAddOn);
            }
            if (mAttributesList != null && mAttributesList.size() > ZERO) {
              StringBuilder value = new StringBuilder();
              for (int j = ZERO; j < mAttributesList.size(); j++) {
                Attributes attributes = mAttributesList.get(j);
                String addOnName = EcomUtil.getColoredSpanned(attributes.getAttrname(),
                    LIGHT_BLACK);
                String attrValue = EcomUtil.getColoredSpanned(attributes.getValue(), BLACK);
                if (attributes.getValue() != null && !attributes.getValue().isEmpty()) {
                  value.append(addOnName).append(":").append(attrValue).append(
                      attributes.getMeasurementUnit()).append(" | ");
                }
              }
              if (value.length() > ZERO) {
                EcomUtil.printLog("exe" + "size  " + value.substring(ZERO, value.length() - TWO));
                tvItemSizeAndColor.setText(
                    Html.fromHtml(value.substring(ZERO, value.length() - TWO)));
              } else {
                tvItemSizeAndColor.setVisibility(View.GONE);
              }
            }
            tvItemScan.setText((products.get(index).getBarcode() != null
                && !products.get(index).getBarcode().isEmpty()) ? getResources().getString(
                R.string.scan) : getResources().getString(R.string.forcePick));
            hsItemDetailStatus.setVisibility(
                Integer.parseInt(mStatus) == ACCEPTED ? View.VISIBLE : View.GONE);
            if (products.get(index).getStatus().getStatus() != null && Integer.parseInt(mStatus)
                == ACCEPTED) {
              if (Integer.parseInt(products.get(index).getStatus().getStatus()) == NEW
                  || Integer.parseInt(products.get(index).getStatus().getStatus()) == PACKED
                  || Integer.parseInt(products.get(index).getStatus().getStatus()) == CANCELLED) {
                hsItemDetailStatus.setVisibility(View.GONE);
              }
            }
            if (products.get(index).getStatus().getStatus() != null && Integer.parseInt(
                mStatus) == NEW) {
              hsItemDetailStatus.setVisibility(View.GONE);
            } else if (products.get(index).getStatus().getStatus() != null && Integer.parseInt(
                mStatus) == ACCEPTED) {
              hsItemDetailStatus.setVisibility(View.VISIBLE);
              tvItemSubStitute.setVisibility(View.VISIBLE);
              tvItemScan.setVisibility(View.VISIBLE);
              tvItemDetailsItemUnavailable.setVisibility(View.VISIBLE);
              if (Integer.parseInt(products.get(index).getStatus().getStatus()) == NEW
                  || Integer.parseInt(products.get(index).getStatus().getStatus()) == PACKED
                  || Integer.parseInt(products.get(index).getStatus().getStatus())
                  == CANCELLED || Integer.parseInt(products.get(index).getStatus().getStatus())
                  == CHECKED) {
                hsItemDetailStatus.setVisibility(View.GONE);
              }
            } else if (products.get(index).getStatus().getStatus() != null
                && Integer.parseInt(mStatus) == CHECKED) {
              hsItemDetailStatus.setVisibility(View.GONE);
            }
            if (mStatus != null && Integer.parseInt(mStatus) >= PACKED && Integer.parseInt(mStatus)
                != CHECKED && Integer.parseInt(mStatus) != PENDING) {
              groupAsile.setVisibility(View.GONE);
            }
            tvItemSubStitute.setOnClickListener(v -> {
              EcomUtil.printLog("exe" + "  mIndex" + mIndex + "  finalIndex" + finalIndex);
              Intent intent = new Intent(OrderDetailsForMobile.this,
                  ProductSubStituteActivity.class);
              intent.putExtra(STORE_ORDER_ID, mOrderId);
              intent.putExtra(PRODUCT_ORDER_ID,
                  products.get(finalIndex).getProductOrderId());
              intent.putExtra(PRODUCT, products.get(finalIndex));
              intent.putExtra(PRODUCT_ID_TXT,
                  products.get(finalIndex).getProductId());
              intent.putExtra(PARENT_PRODUCT_ID,
                  products.get(finalIndex).getCentralProductId());
              intent.putExtra(QUANTITY, Integer.parseInt(products.get(
                  finalIndex).getQuantity().getValue()));
              startActivityForResult(intent, PRODUCT_SUBSTITUDE);
            });
            tvItemScan.setOnClickListener(v -> {
              EcomUtil.printLog("exe" + "finalIndex1  " + finalIndex);
              mIndex = finalIndex;
              if ((products.get(finalIndex).getBarcode() != null
                  && !products.get(finalIndex).getBarcode().isEmpty())) {
                Intent intent = new Intent(this, BarCodePreviewActivity.class);
                intent.putExtra(CURRENCY_SYMBOL,
                    products.get(finalIndex).getCurrencySymbol());
                intent.putExtra(CURRENCY_CODE, products.get(finalIndex).getCurrencyCode());
                intent.putExtra(ITEM_DATA, products.get(finalIndex));
                intent.putExtra(PRODUCT_ID, products.get(finalIndex).getProductId());
                intent.putExtra(PARENT_PRODUCT_ID,
                    products.get(finalIndex).getCentralProductId());
                intent.putExtra(PRODUCT_ORDER_ID,
                    products.get(finalIndex).getProductOrderId());
                intent.putExtra(QUANTITY, Integer.parseInt(mItemsArrayList.get(
                    finalIndex).getQuantity().getValue()));
                startActivityForResult(intent, BARCODE_REQUEST);
              } else {
                mIndex = finalIndex;
                Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
                intent.putExtra(CURRENCY_SYMBOL, products.get(mIndex).getCurrencySymbol());
                intent.putExtra(CURRENCY_CODE, products.get(mIndex).getCurrencyCode());
                intent.putExtra(ORDER_ID, mOrderId);
                intent.putExtra(OPEN_CAMERA, FALSE);
                intent.putExtra(ITEM_DATA, products.get(mIndex));
                startActivityForResult(intent, FORCE_PICK_REQUEST);
              }
            });
            tvItemDetailsItemUnavailable.setOnClickListener(v -> {
              mIndex = finalIndex;
              orderNumberBtmSheet.setItemDetails(products.get(finalIndex), FALSE, FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            if (products.get(index).getImages() != null) {
              Glide.with(this)
                  .load(products.get(index).getImages().getMedium())
                  .into(ivItemImage);
            }
            if (products.get(index).getAisle() != null && !products.get(
                index).getAisle().isEmpty() && products.get(index).getShelf() != null
                && !products.get(
                index).getShelf().isEmpty()) {
              tvAisle.setText(String.format("%s. %s. %s",
                  products.get(index).getAisle(),
                  products.get(index).getShelf(),
                  products.get(index).getDirections()));
            } else {
              groupAsile.setVisibility(View.GONE);
            }
            int quantity;
            double unitPrice = ZERO;
            quantity = Integer.parseInt(products.get(index).getQuantity().getValue());
            if (products.get(index).getAccounting().getFinalTotal() != null) {
              unitPrice = products.get(index).getAccounting().getFinalTotal();
            }
            subTotal += quantity * unitPrice;
            tvItemDetailsProductPrice.setText(
                String.format("%s %s", products.get(index).getCurrencySymbol(),
                    Utility.roundOfDoubleValue(products.get(
                        index).getSellerAccounting().getTaxableAmount() + "")));
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(view);
            if (index == products.size() - ONE) {
              viewItemDetailsViewDashed.setVisibility(View.GONE);
            }
            tvAisleEdit.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this,
                  UpdateAsileActivity.class);
              intent.putExtra(PARENT_PRODUCT_ID,
                  mItemsArrayList.get(finalIndex).getCentralProductId());
              intent.putExtra(AISLE, mItemsArrayList.get(finalIndex).getAisle());
              intent.putExtra(SHELF, mItemsArrayList.get(finalIndex).getShelf());
              intent.putExtra(SECTOR, mItemsArrayList.get(finalIndex).getDirections());
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(finalIndex));
              startActivityForResult(intent, UPDATE_ASILE);
            });
            break;
          case FOUR:
            View viewSubStitude = layoutInflater.inflate(R.layout.item_product_substitude, null);
            ImageView ivItemAddProduct = viewSubStitude.findViewById(R.id.ivItemAddProduct);
            Group groupConfirmed = viewSubStitude.findViewById(R.id.groupConfirmed);
            Group groupAddedPending = viewSubStitude.findViewById(R.id.groupAddedPending);
            Group groupSubstitute = viewSubStitude.findViewById(R.id.groupSubstitute);
            Group groupReplaceSubstituteItem = viewSubStitude.findViewById(
                R.id.groupReplaceSubstituteItem);
            TextView tvItemPending = viewSubStitude.findViewById(R.id.tvItemPending);
            TextView tvApproved = viewSubStitude.findViewById(R.id.tvApproved);
            TextView tvSubstitute = viewSubStitude.findViewById(R.id.tvSubstitute);
            TextView tvRemove = viewSubStitude.findViewById(R.id.tvRemove);
            TextView tvForcePick = viewSubStitude.findViewById(R.id.tvForcePick);
            TextView tvItemReviewTiming = viewSubStitude.findViewById(R.id.tvItemReviewTiming);
            TextView tvItemAddedReviewTiming = viewSubStitude.findViewById(
                R.id.tvItemAddedReviewTiming);
            ImageView ivItemReplacedProduct = viewSubStitude.findViewById(
                R.id.ivItemReplacedProduct);
            TextView tvItemName = viewSubStitude.findViewById(R.id.tvItemName);
            TextView tvItemReplacedItemName = viewSubStitude.findViewById(
                R.id.tvItemReplacedItemName);
            TextView tvItemBrand = viewSubStitude.findViewById(R.id.tvItemBrand);
            TextView tvItemReplacedBrand = viewSubStitude.findViewById(R.id.tvItemReplacedBrand);
            TextView tvItemPrice = viewSubStitude.findViewById(R.id.tvItemPrice);
            TextView tvItemReplacedPrice = viewSubStitude.findViewById(R.id.tvItemReplacedPrice);
            TextView tvForcePickReplace = viewSubStitude.findViewById(R.id.tvForcePickReplace);
            TextView tvRemoveReplace = viewSubStitude.findViewById(R.id.tvRemoveReplace);
            tvSubstitute.setVisibility(View.VISIBLE);
            boolean subsitute = products.get(index).isSubsitute();
            subsituteWith subsituteWith = products.get(index).getSubsituteWith();
            if (subsitute) {
              if (subsituteWith.getImages() != null) {
                Glide.with(this)
                    .load(subsituteWith.getImages().getMedium())
                    .into(ivItemAddProduct);
              }
              tvSubstitute.setText(getResources().getString(R.string.substitude));
              tvItemName.setText(subsituteWith.getName());
              tvItemBrand.setText(
                  String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                      subsituteWith.getQuantity().getValue(), subsituteWith.getQuantity().getUnit(),
                      products.get(index).getCurrencySymbol(),
                      Utility.roundOfDoubleValue(subsituteWith.getOriginalPrice() + "")));
              tvItemPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      subsituteWith.getOriginalPrice()));
              if (products.get(index).getImages() != null) {
                Glide.with(this)
                    .load(products.get(index).getImages().getMedium())
                    .into(ivItemReplacedProduct);
              }
              tvItemReplacedItemName.setText(products.get(index).getName());
              if (products.get(index).getQuantity() != null) {
                tvItemReplacedBrand.setText(
                    String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                        products.get(
                            index).getQuantity().getValue(), products.get(
                            index).getQuantity().getUnit(),
                        products.get(index).getCurrencySymbol(),
                        Utility.roundOfDoubleValue(products.get(
                            index).getSingleUnitPrice().getSubTotal() + "")));
              }
              tvItemReplacedPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      Utility.roundOfDoubleValue(products.get(
                          index).getAccounting().getFinalTotal() + "")));
            } else {
              groupSubstitute.setVisibility(View.GONE);
              groupAddedPending.setVisibility(View.VISIBLE);
              groupConfirmed.setVisibility(View.VISIBLE);
              if (products.get(index).getImages() != null) {
                Glide.with(this)
                    .load(products.get(index).getImages().getMedium())
                    .into(ivItemAddProduct);
              }
              tvItemName.setText(products.get(index).getName());
              if (products.get(index).getQuantity() != null) {
                tvItemBrand.setText(
                    String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                        products.get(index).getQuantity().getValue(), products.get(
                            index).getQuantity().getUnit(),
                        products.get(index).getCurrencySymbol(),
                        Utility.roundOfDoubleValue(products.get(
                            index).getSingleUnitPrice().getUnitPrice() + "")));
              }
              tvItemPrice.setText(
                  String.format("%s %s", mCurrencySymbol,
                      Utility.roundOfDoubleValue(products.get(index).getAccounting().getUnitPrice()
                          + "")));
            }
            tvRemove.setOnClickListener(v -> {
              orderNumberBtmSheet.setItemDetails(products.get(finalIndex), FALSE, FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            tvForcePick.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
              intent.putExtra(CURRENCY_SYMBOL, products.get(mIndex).getCurrencySymbol());
              intent.putExtra(CURRENCY_CODE, products.get(mIndex).getCurrencyCode());
              intent.putExtra(ORDER_ID, mOrderId);
              intent.putExtra(OPEN_CAMERA, FALSE);
              intent.putExtra(ITEM_DATA, products.get(mIndex));
              startActivityForResult(intent, FORCE_PICK_REQUEST);
            });
            tvForcePickReplace.setOnClickListener(v -> {
              mIndex = finalIndex;
              Intent intent = new Intent(OrderDetailsForMobile.this, ForcePickActivity.class);
              intent.putExtra(CURRENCY_SYMBOL, mItemsArrayList.get(mIndex).getCurrencySymbol());
              intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(mIndex).getCurrencyCode());
              intent.putExtra(ORDER_ID, mOrderId);
              intent.putExtra(OPEN_CAMERA, FALSE);
              intent.putExtra(ITEM_DATA, mItemsArrayList.get(mIndex));
              startActivityForResult(intent, FORCE_PICK_REQUEST);
            });
            tvRemoveReplace.setOnClickListener(v -> {
              orderNumberBtmSheet.setItemDetails(mItemsArrayList.get(finalIndex), FALSE,
                  FALSE);
              orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
            });
            if (products.get(index).getStatus() != null && products.get(
                index).getStatus().getExpiryTimeForConfirmation() != null) {
              EcomUtil.printLog("exe" + "timeStamp  " + products.get(
                  index).getStatus().getExpiryTimeForConfirmation());
              Long tsLong = System.currentTimeMillis() / THOUSAND;
              String expiryTimeForConfirmation = products.get(
                  index).getStatus().getExpiryTimeForConfirmation();
              long sec = Long.parseLong((expiryTimeForConfirmation)) - tsLong;
              EcomUtil.printLog(
                  "exe" + "final sec  " + sec);
              int finalIndex1 = index;
              CountDownTimer countDownTimer = new CountDownTimer((sec) * THOUSAND,
                  THOUSAND) {
                public void onTick(long millisUntilFinished) {
                  //here you can have your logic to set text to edit text
                  if (subsitute) {
                    tvItemReviewTiming.setText(EcomUtil.getTimerValue(millisUntilFinished));
                    groupConfirmed.setVisibility(View.GONE);
                    groupReplaceSubstituteItem.setVisibility(View.GONE);
                  } else {
                    tvItemAddedReviewTiming.setText(EcomUtil.getTimerValue(millisUntilFinished));
                    tvApproved.setText(getResources().getString(R.string.notResponding));
                    tvApproved.setTextColor(getResources().getColor(R.color.red));
                    groupReplaceSubstituteItem.setVisibility(View.GONE);
                    groupConfirmed.setVisibility(View.GONE);
                  }
                }

                public void onFinish() {
                  groupConfirmed.setVisibility(View.VISIBLE);
                  groupAddedPending.setVisibility(View.GONE);
                  groupSubstitute.setVisibility(View.GONE);
                  groupReplaceSubstituteItem.setVisibility(View.GONE);
                  if (subsitute) {
                    if (products.get(finalIndex1).getImages() != null) {
                      Glide.with(ApplicationManager.getInstance())
                          .load(products.get(finalIndex1).getImages().getMedium())
                          .into(ivItemReplacedProduct
                          );
                    }
                    groupSubstitute.setVisibility(View.VISIBLE);
                    groupConfirmed.setVisibility(View.GONE);
                    groupReplaceSubstituteItem.setVisibility(View.VISIBLE);
                    tvItemPending.setText("");
                    tvItemReviewTiming.setText("");
                    tvItemReplacedItemName.setText(products.get(finalIndex1).getName());
                    tvItemReplacedBrand.setText(
                        String.format("%s %s %s . %s %s", getResources().getString(R.string.qty),
                            products.get(
                                finalIndex1).getQuantity().getValue(), products.get(
                                finalIndex1).getQuantity().getUnit(),
                            products.get(finalIndex1).getCurrencySymbol(),
                            Utility.roundOfDoubleValue(products.get(
                                finalIndex1).getSingleUnitPrice().getSubTotal() + "")));
                    tvItemReplacedPrice.setText(
                        String.format("%s %s", mCurrencySymbol,
                            Utility.roundOfDoubleValue(products.get(
                                finalIndex1).getAccounting().getFinalTotal() + "")));
                  }
                }
              }.start();
            }
            mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.addView(viewSubStitude);
            break;
        }
      }
      presenter.getFares((subTotal + mAddOnCount));
    } else {
      mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
    }
  }

  /**
   * this will give warning when the user edit the order,if the status has reached packed.
   */
  private void editWarning() {
    Toast.makeText(this, getResources().getString(R.string.editOrderWarning),
        Toast.LENGTH_LONG).show();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.textViewOrderDetailsManualDispatch:
        moveToListAct(mOrderId);
        break;
      case R.id.textViewOrderDetailsAutoDispatch:
        presenter.dispatch();
        break;
      case R.id.imageViewAllBack:
        onBackPressed();
        break;
      case R.id.textViewOptionsSecond:
        if (presenter.getStoreType() == RESTAURENT) {
          orderNumberBtmSheet.setItemDetails(mItems.get(ZERO), FALSE, FALSE);
          orderNumberBtmSheet.show(getSupportFragmentManager(), orderNumberBtmSheet.getTag());
        } else if (mStatus.equals(String.valueOf(NEW)) && presenter.getStoreType() == PHARMACY) {
          if (mOrderDetailsBinding.layoutOptions.textViewOptionsSecond.getText().toString().
              equals(getResources().getString(R.string.getConfirmation))) {
            presenter.confirmationRequest(mOrderId, "",
                presenter.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
          } else {
            showRejectConfirmation(Integer.parseInt(mStatus));
          }
        } else {
          presenter.updateOrder(ACCEPTED, null);
        }
        break;
      case R.id.textViewOptionsFirst:
        if (mStatus.equals(String.valueOf(NEW)) && presenter.getStoreType() == PHARMACY) {
          Intent intent = new Intent(OrderDetailsForMobile.this,
              ProductUnavailableSubstituteActivity.class);
          intent.putExtra(STORE_CATEGORY_ID, mOrderDetails.getData().getStoreCategoryId());
          intent.putExtra(REASON_ID, REASON_PRODUCT_CANCELLED);
          intent.putExtra(STORE_CATEGORY_ID, mOrderDetails.getData().getStoreCategoryId());
          intent.putExtra(ORDER_ID, TRUE);
          intent.putExtra(ITEM_AVAILABLE, TRUE);
          startActivityForResult(intent, PHARMACY_REJECT);
        } else {
          showRejectConfirmation(Integer.parseInt(mStatus));
        }
        break;
      case R.id.constraintLayoutOrderDetailsMainContainer:
        Utility.hideSoftKeyboard(this);
        break;
      case R.id.buttonCommon:
        if (mOrderDetailsBinding.layoutButton.buttonCommon.getText().toString().equals(
            getResources().getString(R.string.assignManually))) {
          openManualShipmentAct(TRUE);
        } else if (mOrderDetailsBinding.layoutButton.buttonCommon.getText().toString().equals(
            getResources().getString(R.string.manualDispatch))) {
          openManualShipmentAct(TRUE);
        } else if (mOrderDetailsBinding.layoutButton.buttonCommon.getText().toString().equals(
            getResources().getString(R.string.packOrder))) {
          Intent intent = new Intent(this, PrintLabelsActivity.class);
          intent.putExtra(ORDER_ID, mOrderId);
          intent.putExtra(CURRENCY_SYMBOL,
              mOrderDetails.getData().getAccounting().getCurrencySymbol());
          intent.putExtra(CURRENCY_CODE,
              mOrderDetails.getData().getAccounting().getCurrencyCode());
          intent.putExtra(ITEM_PRICE,
              String.format("%s %s", mOrderDetails.getData().getAccounting().getCurrencySymbol(),
                  mOrderDetails.getData().getAccounting().getFinalTotal()));
          intent.putExtra(QUANTITY, mOrderDetails.getData().getProducts().size());
          intent.putExtra(CUSTOMER_NAME,
              mOrderDetailsBinding.textViewOrderDetailsCustomerName.getText().toString());
          CustomerDetails customerDetails = mOrderDetails.getData().getCustomerDetails();
          String number = customerDetails.getMobile();
          intent.putExtra(CUSTOMER_NUMBER, number);
          EcomUtil.printLog("exe" + "mItemsArrayList Upload" + mItemsArrayList.size());
          startActivityForResult(intent, UPLOAD_FORCE_PICK_REQUEST);
        }
        break;
      case R.id.textViewOrderDetailsCustomerName:
        openPastOrderAct();
        break;
      case R.id.ivOrderDetailsEdit:
        presenter.editItems();
        break;
      case R.id.tvTrackOrderStatus:
        showLiveTrackPage();
        break;
      case R.id.tvManualShipment:
        openManualShipmentAct(FALSE);
        break;
      case R.id.tvAutoShiment:
        showRejectConfirmation(Integer.parseInt(mStatus));
        break;
      case R.id.tvCancelDispatch:
        presenter.cancelDispatch();
        break;
      case R.id.ivCall:
        CustomerDetails customerDetails = mOrderDetails.getData().getCustomerDetails();
        if (customerDetails != null) {
          String number = customerDetails.getMobile();
          checkCallPermission(number);
        }
        break;
      case R.id.cvMessage:
        Intent chatIntent = new Intent(this, ChattingActivity.class);
        chatIntent.putExtra(ORDER_ID, mOrderId);
        chatIntent.putExtra(CUSTOMER_ID, mOrderDetails.getData().getCustomerDetails().getId());
        chatIntent.putExtra(CUSTOMER_NAME,
            String.format("%s %s", mOrderDetails.getData().getCustomerDetails().getFirstName(),
                mOrderDetails.getData().getCustomerDetails().getLastName()));
        startActivity(chatIntent);
        break;
      case R.id.cvDriverCall:
        if (mStatus.equals(String.valueOf(READY_FOR_PICK_UP)) || mStatus.equals(
            String.valueOf(DISPATCH))) {
          DriverDetails driverDetails = mOrderDetails.getData().getDriverDetails();
          if (driverDetails != null) {
            String number = driverDetails.getMobile();
            checkCallPermission(number);
          }
        }
        break;
      case R.id.tvPo:
        openWebBrowser(mOrderDetails.getData().getPoInvoiceLink(), this);
        break;
      case R.id.tvReceipt:
        Intent intent = new Intent(this, PrescriptionDetail.class);
        intent.putExtra(RECEIPT_IMG, TRUE);
        intent.putExtra(PRESCRIPTION_IMG, mOrderDetails.getData().getRececiptURL());
        startActivity(intent);
        break;
      case R.id.tvItemLable:
        if (mOrderDetails.getData().getShippingLabel() != null
            && !mOrderDetails.getData().getShippingLabel().isEmpty()) {
          EcomUtil.printLog("exe" + "label  " + mOrderDetails.getData().getShippingLabel());
          openWebBrowser(mOrderDetails.getData().getShippingLabel(), this);
        }
        break;
      case R.id.ivAddProduct:
        EcomUtil.printLog("exe" + "ivAddProduct");
        Intent intentAddProduct = new Intent(this, AddProductActivity.class);
        intentAddProduct.putExtra(CURRENCY_SYMBOL,
            mOrderDetails.getData().getAccounting().getCurrencySymbol());
        intentAddProduct.putExtra(CURRENCY_CODE,
            mOrderDetails.getData().getAccounting().getCurrencyCode());
        intentAddProduct.putExtra(STORE_ORDER_ID, mOrderId);
        intentAddProduct.putExtra(SEND_MANUALLY, FALSE);
        startActivityForResult(intentAddProduct, ADD_PRODUCT_REQUEST);
        break;
      default:
        break;
    }
  }

  /**
   * <p>Open attribute page</p>
   */
  private void showAttributeActivity(ArrayList<Attributes> attributes) {
    Intent intent = new Intent(this, CustomizationsActivity.class);
    intent.putExtra(ATTRIBUTE_DATA, attributes);
    startActivity(intent);
  }

  /**
   * <p>Open live tracking page</p>
   */
  private void showLiveTrackPage() {
    Intent intent = new Intent(this, TrackOrderActivity.class);
    intent.putExtra(ORDER_ID, mOrderDetails.getData().getProducts().get(ZERO).getProductOrderId());
    startActivity(intent);
  }

  /**
   * <h>showRejectConfirmation</h>
   * <p>Shows confirmation for reject order</p>
   */
  private void showRejectConfirmation(int status) {
    mDialog = new Dialog(this);
    mDialog.setContentView(R.layout.layout_reject_confirmation_dialog);
    mDialog.setCancelable(true);
    TextView tvDialogTitle = mDialog.findViewById(R.id.tvDialogTitle);
    TextView tvOptionsReject = mDialog.findViewById(R.id.tvOptionsReject);
    TextView tvOptionsCancel = mDialog.findViewById(R.id.tvOptionsCancel);
    if (status == PACKED) {
      tvDialogTitle.setText(getResources().getString(R.string.dispatchProductsMsg));
      tvOptionsReject.setText(getResources().getString(R.string.yes));
      tvOptionsCancel.setText(getResources().getString(R.string.no));
    } else if (mStatus.equals(String.valueOf(NEW)) && presenter.getStoreType() == PHARMACY) {
      tvDialogTitle.setText(getResources().getString(R.string.pharmacyAcceptConfirmation));
      tvOptionsReject.setText(getResources().getString(R.string.cancel));
      tvOptionsCancel.setText(getResources().getString(R.string.accept));
    }
    tvOptionsReject.setOnClickListener(v -> {
      mDialog.dismiss();
      if (status == PACKED) {
        presenter.readyForPickUp("");
      } else if (mStatus.equals(String.valueOf(NEW)) && presenter.getStoreType() == PHARMACY) {
        mDialog.dismiss();
      } else if (status == NEW) {
        EcomUtil.printLog("exe" + "mItemsArrayList" + mItemsArrayList.size());
        presenter.rejectOrder(mOrderId, "", presenter.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
      } else {
        presenter.updateOrder(CANCELLED, "");
      }
    });
    tvOptionsCancel.setOnClickListener(v -> {
      if (mStatus.equals(String.valueOf(NEW)) && presenter.getStoreType() == PHARMACY) {
        mDialog.dismiss();
        presenter.updateOrder(ACCEPTED, null);
      } else {
        mDialog.dismiss();
      }
    });
    mDialog.show();
  }

  @Override
  public void finishActivity() {
    finish();
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public void showCancelOrDelayDailog(boolean delay) {
    mCancelDialog = new CancellationDialog(this, presenter);
    presenter.getCancellationReason();
    mCancelDialog.createDialog(delay, presenter.getDueTime(), presenter.getTimestamp());
  }

  /**
   * used to disable editing for the items.
   *
   * @param quantity string
   * @param index    integer
   */
  private void disable(String quantity, int index) {
    if (!quantity.isEmpty() && !quantity.equals("0")) {
      for (int i = ZERO; i
          < mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.getChildCount();
          i++) {
        LinearLayout ll =
            (LinearLayout) mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.getChildAt(
                i);
        LinearLayout llMain = (LinearLayout) ll.getChildAt(ZERO);
        LinearLayout llMain1 = (LinearLayout) llMain.getChildAt(TWO);
        EditText qntEt = (EditText) llMain1.getChildAt(ZERO);
        EditText priceEt = (EditText) llMain1.getChildAt(THREE);
        qntEt.setEnabled(FALSE);
        priceEt.setEnabled(FALSE);
        qntEt.setBackgroundColor(getResources().getColor(R.color.transperent));
        priceEt.setBackgroundColor(getResources().getColor(R.color.transperent));
        mItemsArrayList.get(i).getAccounting().setFinalTotal(
            (Double.parseDouble(priceEt.getText().toString())));
        mItemsArrayList.get(i).getQuantity().setValue(
            i == index ? quantity : qntEt.getText().toString());
      }
      presenter.saveItems(mItemsArrayList);
    } else {
      Toast.makeText(this, getResources().getString(R.string.quantityError),
          Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public void moveToListAct(String orderId) {
    Bundle bundle = new Bundle();
    bundle.putString(DATA, orderId);
    bundle.putString(STORE_ID, presenter.getStoreId());
    bundle.putString(SLOT_ID, mOrderDetails.getData().getDeliverySlotId());
    Intent intent = new Intent(this, SelectDriversActivity.class);
    intent.putExtras(bundle);
    startActivityForResult(intent, DRIVER_REQUEST);
  }

  @Override
  public void setReasons(ArrayList<Reasons> reasons) {
    if (mCancelDialog != null) {
      mCancelDialog.setReasons(reasons);
    }
  }

  @Override
  public void hideProgress() {
    if (mOrderDetailsBinding.progressBar != null) {
      mOrderDetailsBinding.progressBar.setVisibility(View.GONE);
    }
  }

  @Override
  public void showProgress() {
    if (mOrderDetailsBinding.progressBar != null) {
      mOrderDetailsBinding.progressBar.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void setPastOrders(PastOrdersData pastOrders) {
    PastOrderInOrderDetAdapter historyAdapter = new PastOrderInOrderDetAdapter(
        this,
        pastOrders.getPastOrders());
    historyAdapter.notifyDataSetChanged();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @SuppressLint({"SetTextI18n", "DefaultLocale"})
  @Override
  public void setViews(OrderDetailsModel orderDetails, boolean isCityLogin) {
    mStatus = orderDetails.getData().getStatus().getStatus();
    if (orderDetails.getData().getProducts() != null) {
      mPackageId = orderDetails.getData().getProducts().size() > ZERO
          ? orderDetails.getData().getProducts().get(ZERO).getPackageId() : "";
    }
    if (mOrderStatus >= PACKED) {
      if (orderDetails.getData().getProducts().size() > ZERO) {
        mStatus = orderDetails.getData().getProducts().get(ZERO).getStatus().getStatus();
      }
    }
    EcomUtil.printLog("exe" + "mStatus" + mStatus);
    EcomUtil.printLog("exe" + "storeType" + presenter.getStoreType());
    viewsVisibilityStatus();
    mTotalAmt = 0.0;
    this.mOrderDetails = orderDetails;
    this.mOrderId =
        isCityLogin ? orderDetails.getData().getMasterOrderId()
            : orderDetails.getData().getStoreOrderId();
    mCurrencySymbol = orderDetails.getData().getAccounting().getCurrencySymbol();
    mCustomerId = orderDetails.getData().getCustomerId();
    mOrderDetailsBinding.textViewOrderDetailsCustomerName.setText(
        String.format("%s %s", orderDetails.getData().getCustomerDetails().getFirstName(),
            orderDetails.getData().getCustomerDetails().getLastName()));
    mOrderDetailsBinding.ivCall.setVisibility(View.VISIBLE);
    if (Integer.parseInt(mStatus) != CHECKED) {
      mOrderDetailsBinding.layoutToolBar.textViewAllTitle.setText(
          Integer.parseInt(mStatus) >= PACKED && Integer.parseInt(mStatus) != CHECKED
              && Integer.parseInt(mStatus) != PENDING ?
              String.format("%s :%s", getResources().getString(R.string.packId),
                  mPackageId)
              : String.format("%s :%s", getResources().getString(R.string.id), mOrderId));
    } else {
      mOrderDetailsBinding.layoutToolBar.textViewAllTitle.setVisibility(View.GONE);
      mOrderDetailsBinding.layoutToolBar.groupCheckOut.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.layoutToolBar.tvCheckOutId.setText(
          String.format("%s :%s", getResources().getString(R.string.id), mOrderId));
    }
    String paymentMethod = "";
    if (orderDetails.getData().getPaymentType().equals(String.valueOf(TWO)) &&
        orderDetails.getData().getPayByWallet()) {
      paymentMethod = String.format("%s+%s", getResources().getString(R.string.cash),
          getResources().getString(R.string.wallet));
    } else if (orderDetails.getData().getPaymentType().equals(String.valueOf(ONE)) &&
        orderDetails.getData().getPayByWallet()) {
      paymentMethod = String.format("%s+%s", getResources().getString(R.string.card),
          getResources().getString(R.string.wallet));
    } else if (orderDetails.getData().getPaymentType().equals(String.valueOf(ONE)) &&
        !orderDetails.getData().getPayByWallet()) {
      paymentMethod = getResources().getString(R.string.card);
    } else if (orderDetails.getData().getPaymentType().equals(String.valueOf(TWO)) &&
        !orderDetails.getData().getPayByWallet()) {
      paymentMethod = getResources().getString(R.string.cash);
    } else if (!orderDetails.getData().getPaymentType().equals(String.valueOf(ONE)) &&
        !orderDetails.getData().getPayByWallet()) {
      paymentMethod = getResources().getString(R.string.wallet);
    }
    mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
    if (mStatus != null && Integer.parseInt(mStatus) >= PACKED && Integer.parseInt(mStatus)
        != PENDING && Integer.parseInt(mStatus) != CHECKED) {
      mOrderDetailsBinding.tvOrderDetailsOrderType.setText(
          String.format("%s | %s",
              Utility.getOrderPlacedTime(orderDetails.getData().getTimestamps().getPacked()),
              orderDetails.getData().getOrderTypeMsg()));
    } else {
      mOrderDetailsBinding.tvOrderDetailsOrderType.setText(
          String.format("%s | %s",
              Utility.getOrderPlacedTime(orderDetails.getData().getCreatedTimeStamp()),
              orderDetails.getData().getOrderTypeMsg()));
    }
    if (mOrderDetails.getData().getBags() != null
        && mOrderDetails.getData().getBags().size() > ZERO) {
      setBagDetails(mOrderDetails.getData().getBags());
    }
    mOrderDetailsBinding.tvCustomerOrderIdValue.setText(orderDetails.getData().getMasterOrderId());
    if (mStatus != null && Integer.parseInt(mStatus) >= PACKED) {
      mOrderDetailsBinding.groupStoreId.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.tvStoreOrderIdValue.setText(orderDetails.getData().getStoreOrderId());
    }
    if (mStatus != null && Integer.parseInt(mStatus) >= PACKED && Integer.parseInt(mStatus)
        != PENDING && Integer.parseInt(mStatus) != CHECKED
        && presenter.getStoreType() == PHARMACY) {
      mOrderDetailsBinding.tvReceipt.setVisibility(View.VISIBLE);
    }
    mOrderDetailsBinding.tvOrderDetailsPriceValue.setText(
        String.format("%s %s | %s", mCurrencySymbol,
            Utility.roundOfDoubleValue(
                String.valueOf(orderDetails.getData().getAccounting().getFinalTotal())),
            paymentMethod));
    if (mOrderDetails.getData().getPoInvoiceLink() != null) {
      mOrderDetailsBinding.tvPo.setVisibility(
          Integer.parseInt(mStatus) == ACCEPTED || Integer.parseInt(mStatus) == NEW
              || Integer.parseInt(mStatus) == PENDING ? View.VISIBLE
              : View.GONE);
    }
    mTabItemsList.clear();
    if (Integer.parseInt(mStatus) < PACKED) {
      setTabLayout(orderDetails.getData().getCount());
    }
    mTabItemsList.addAll(orderDetails.getData().getProducts());
    if (mStatus.equals(String.valueOf(ACCEPTED))) {
      Predicate<Products> condition =
          orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus()) != ACCEPTED;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        mTabItemsList.removeIf(condition);
      }
    }
    if (mOrderDetails.getData().getRececiptURL() != null
        && mOrderDetails.getData().getRececiptURL().size() > ZERO
        && presenter.getStoreType() == PHARMACY) {
      mOrderDetailsBinding.tvReceipt.setVisibility(View.VISIBLE);
    }
    EcomUtil.printLog("exe" + "mTabItemsList" + mTabItemsList.size());
    if (presenter.getStoreType() == RESTAURENT) {
      addFoodItems(mTabItemsList, orderDetails.getData().getStoreType());
    } else if (presenter.getStoreType() == GROCERY) {
      if (mStatus.equals(String.valueOf(ACCEPTED)) && presenter.getStoreType() == GROCERY) {
        mOrderDetailsBinding.clSeekBar.setVisibility(View.VISIBLE);
        mOrderDetailsBinding.myseek.setEnabled(FALSE);
        mOrderDetailsBinding.tvStatusText.setText(getResources().getString(R.string.donePicking));
        if (orderDetails.getData().getCount() != null) {
          if (orderDetails.getData().getCount().getPending() != null
              && orderDetails.getData().getCount().getReview() != null && Integer.parseInt(
              orderDetails.getData().getCount().getPending()) == ZERO && Integer.parseInt(
              orderDetails.getData().getCount().getReview()) == ZERO) {
            mOrderDetailsBinding.myseek.setEnabled(TRUE);
          }
        }
        addGroceryItems(mTabItemsList, orderDetails.getData().getStoreType());
      } else if (mStatus.equals(String.valueOf(CHECKED))) {
        Predicate<Products> conditionCan =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus()) == CANCELLED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mTabItemsList.removeIf(conditionCan);
        }
        if (presenter.getStoreType() == GROCERY) {
          mOrderDetailsBinding.clSeekBar.setVisibility(View.VISIBLE);
          mOrderDetailsBinding.myseek.setProgress(ZERO);
          mOrderDetailsBinding.tvStatusText.setText(
              getResources().getString(R.string.confirmAndUploadReceipt));
        }
        addGroceryItems(mTabItemsList, mOrderDetails.getData().getStoreType());
      } else {
        addGroceryItems(mTabItemsList, orderDetails.getData().getStoreType());
      }
    } else if (presenter.getStoreType() == PHARMACY) {
      ArrayList<Products> pharmacyItems = new ArrayList<>();
      int itemCount = 0, rejectedCount = 0;
      EcomUtil.printLog("exe" + "mTabItemsListInside" + mTabItemsList.size());
      if (mStatus.equals(String.valueOf(NEW))) {
        Predicate<Products> conditionCan =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                == CANCELLED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mTabItemsList.removeIf(conditionCan);
        }
        this.mItems.clear();
        this.mItems.addAll(mTabItemsList);
        Predicate<Products> conditionPre =
            orderCountData -> !orderCountData.isPrescriptionRequired();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          this.mItems.removeIf(conditionPre);
        }
        if (this.mItems.size() > ZERO) {
          Products productWithPre = new Products();
          productWithPre.setItemType(ONE);
          productWithPre.setPrescriptionCount(this.mItems.size());
          pharmacyItems.add(productWithPre);
          itemCount = this.mItems.size();
          pharmacyItems.addAll(this.mItems);
          EcomUtil.printLog("exe" + "this.mItems pre" + this.mItems.size());
        }
        this.mItems.clear();
        this.mItems.addAll(mTabItemsList);
        Predicate<Products> conditionNotPre =
            orderCountData -> orderCountData.isPrescriptionRequired();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          this.mItems.removeIf(conditionNotPre);
        }
        if (this.mItems.size() > ZERO) {
          Products productWithPre = new Products();
          productWithPre.setItemType(TWO);
          productWithPre.setPrescriptionCount(this.mItems.size());
          pharmacyItems.add(productWithPre);
          itemCount += this.mItems.size();
          pharmacyItems.addAll(this.mItems);
          EcomUtil.printLog("exe" + "this.mItems woth pre" + this.mItems.size());
        }
        mOrderDetailsBinding.accountDetails.tbPharmacyOptions.getTabAt(ZERO).setText(
            getResources().getString(R.string.items) + "("
                + itemCount + ")");
        addPharmacyItems(pharmacyItems, mOrderDetails.getData().getStoreType());
        mTabItemsList.clear();
        mTabItemsList.addAll(mOrderDetails.getData().getProducts());
        Predicate<Products> condition =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                != CANCELLED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mTabItemsList.removeIf(condition);
        }
        if (mTabItemsList.size() > ZERO) {
          mOrderDetailsBinding.layoutOptions.textViewOptionsSecond.setText(
              getResources().getString(R.string.getConfirmation));
          mOrderDetailsBinding.accountDetails.tbPharmacyOptions.getTabAt(TWO).setText(
              getResources().getString(R.string.rejected) + "("
                  + mTabItemsList.size() + ")");
        }
      } else if (mStatus.equals(String.valueOf(PENDING))) {
        Predicate<Products> conditionNotCan =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                != PENDING;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mTabItemsList.removeIf(conditionNotCan);
        }
        this.mItems.clear();
        this.mItems.addAll(mTabItemsList);
        if (this.mItems.size() > ZERO) {
          Products productPending = new Products();
          productPending.setItemType(THREE);
          productPending.setPrescriptionCount(this.mItems.size());
          pharmacyItems.add(productPending);
          pharmacyItems.addAll(this.mItems);
        }
        mTabItemsList.clear();
        this.mItems.clear();
        mTabItemsList.addAll(orderDetails.getData().getProducts());
        this.mItems.addAll(mTabItemsList);
        Predicate<Products> conditionCan =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                == PENDING;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          this.mItems.removeIf(conditionCan);
        }
        if (this.mItems.size() > ZERO) {
          Products productPending = new Products();
          productPending.setItemType(FIVE);
          productPending.setPrescriptionCount(this.mItems.size());
          pharmacyItems.add(productPending);
          pharmacyItems.addAll(this.mItems);
        }
        addPharmacyItems(pharmacyItems, mOrderDetails.getData().getStoreType());
        mOrderDetailsBinding.llRejectReason.setVisibility(View.VISIBLE);
      } else if (mStatus.equals(String.valueOf(CHECKED))) {
        Predicate<Products> conditionCan =
            orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus()) == CANCELLED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mTabItemsList.removeIf(conditionCan);
        }
        addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
      } else if (!mStatus.equals(String.valueOf(NEW))) {
        addPharmacyItems(mTabItemsList, mOrderDetails.getData().getStoreType());
      }
      if (mStatus.equals(String.valueOf(ACCEPTED)) && presenter.getStoreType() == PHARMACY) {
        mOrderDetailsBinding.clSeekBar.setVisibility(View.VISIBLE);
        mOrderDetailsBinding.myseek.setEnabled(FALSE);
        mOrderDetailsBinding.tvStatusText.setText(getResources().getString(R.string.donePicking));
        if (orderDetails.getData().getCount() != null) {
          if (orderDetails.getData().getCount().getPending() != null
              && orderDetails.getData().getCount().getReview() != null && Integer.parseInt(
              orderDetails.getData().getCount().getPending()) == ZERO && Integer.parseInt(
              orderDetails.getData().getCount().getReview()) == ZERO) {
            mOrderDetailsBinding.myseek.setEnabled(TRUE);
          }
        }
      } else if (mStatus.equals(String.valueOf(CHECKED)) && presenter.getStoreType() == PHARMACY) {
        mOrderDetailsBinding.clSeekBar.setVisibility(View.VISIBLE);
        mOrderDetailsBinding.myseek.setProgress(ZERO);
        mOrderDetailsBinding.tvStatusText.setText(
            getResources().getString(R.string.confirmAndUploadReceipt));
      }
    } else {
      addItems(mTabItemsList, orderDetails.getData().getAccounting().getCurrencySymbol(),
          orderDetails.getData().getStoreType());
    }
    setLiveTrackDetails();
    setNestedScrollViewBottomPadding();
    setDriverDetails();
    setPharmacyTabLayoutClickListener();
  }

  /**
   * This method is sued to show the bag details
   */
  private void setBagDetails(ArrayList<LabelBags> bagDetails) {
    if (presenter.getStoreType() == PHARMACY) {
      mOrderDetailsBinding.layoutBag.clBags.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.layoutBag.tvBagsTxt.setText(
          String.format("%d %s", bagDetails.size(), getResources().getString(R.string.bags)));
      String boxPrice = String.format("%s %s | %d %s",
          mOrderDetails.getData().getAccounting().getCurrencySymbol(),
          mOrderDetails.getData().getAccounting().getFinalUnitPrice(),
          mOrderDetails.getData().getProducts().size(),
          mOrderDetails.getData().getProducts().size() > ONE ? getResources().getString(
              R.string.items) : getResources().getString(R.string.item));
      mOrderDetailsBinding.layoutBag.tvItemPrice.setText(boxPrice);
      BoxIdsAdapter boxIdsAdapter = new BoxIdsAdapter(bagDetails);
      mOrderDetailsBinding.layoutBag.rvBoxId.setAdapter(boxIdsAdapter);
    }
  }

  /**
   * This method is used to show the driver details
   */
  private void setDriverDetails() {
    if (mStatus.equals(String.valueOf(READY_FOR_PICK_UP)) || mStatus.equals(
        String.valueOf(DISPATCH))) {
      DriverDetails driverDetails = mOrderDetails.getData().getDriverDetails();
      EcomUtil.printLog("exe" + "packingDetails" + driverDetails.getDriverId());
      if (driverDetails != null && driverDetails.getDriverId() != null
          && !driverDetails.getDriverId().isEmpty()) {
        mOrderDetailsBinding.clAssignDriver.setVisibility(View.VISIBLE);
        String imageUrl = driverDetails.getProfilePic();
        if (!TextUtils.isEmpty(imageUrl)) {
          RequestOptions requestOptions = new RequestOptions();
          requestOptions.placeholder(R.drawable.profile);
          requestOptions.error(R.drawable.profile);
          Glide.with(this).load(imageUrl)
              .apply(RequestOptions.circleCropTransform().apply(requestOptions)).into(
              mOrderDetailsBinding.ivDriverProfilePic);
        }
        mOrderDetailsBinding.tvDriverName.setText(
            String.format("%s %s", driverDetails.getFirstName(), driverDetails.getLastName()));
        mOrderDetailsBinding.tvDriverStatus.setText(getResources().getString(R.string.accepted));
        if (mStatus.equals(String.valueOf(READY_FOR_PICK_UP))) {
          mOrderDetailsBinding.tvCancelDispatch.setVisibility(View.VISIBLE);
        }
      } else {
        if (mStatus.equals(String.valueOf(READY_FOR_PICK_UP))) {
          mOrderDetailsBinding.linearLayoutOrderDetailsAcceptOrder.setVisibility(View.VISIBLE);
          mOrderDetailsBinding.layoutButton.buttonCommon.setText(
              getString(R.string.assignManually));
        }
      }
    }
  }

  /**
   * <h>setLiveTrackDetails</h>
   * <p>set tracking details for the order</p>
   */
  private void setLiveTrackDetails() {
    if (mStatus.equals(String.valueOf(DISPATCH))) {
      Status status = mOrderDetails.getData().getStatus();
      mOrderDetailsBinding.orderDetailsTrackDetails.clTrackViews.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.orderDetailsTrackDetails.tvTrackOrderStatus.setText(
          status.getStatusText());
      mOrderDetailsBinding.orderDetailsTrackDetails.tvTrackUpdatedOn.setText(
          TimeFormatter.getDate(status.getUpdatedOnTimeStamp()));
    }
  }

  /**
   * tab layout listener to manage the pharmacy items.
   */
  private void setPharmacyTabLayoutClickListener() {
    if (Integer.parseInt(mStatus) == NEW && presenter.getStoreType() == PHARMACY) {
      ArrayList<Products> pharmacyItems = new ArrayList<>();
      PrescriptionsAdapter prescriptionsAdapter = new PrescriptionsAdapter(
          mOrderDetails.getData().getOrderImages(), this);
      mOrderDetailsBinding.accountDetails.tbPharmacyOptions.getTabAt(ONE).setText(
          String.format("%s(%d)", getResources().getString(R.string.prescription),
              mOrderDetails.getData().getOrderImages().size()));
      mOrderDetailsBinding.accountDetails.rvPrescription.setAdapter(prescriptionsAdapter);
      mOrderDetailsBinding.accountDetails.tbPharmacyOptions.addOnTabSelectedListener(
          new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              if (tab.getPosition() == ZERO) {
                mOrderDetailsBinding.accountDetails.llPaymentBreakDown.setVisibility(View.VISIBLE);
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                mOrderDetailsBinding.accountDetails.svItemContainer.setVisibility(View.VISIBLE);
                mOrderDetailsBinding.accountDetails.rvPrescription.setVisibility(View.GONE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(View.GONE);
                pharmacyItems.clear();
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                Predicate<Products> condition =
                    orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                        == CANCELLED;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(condition);
                }
                EcomUtil.printLog("exe" + "mTabItemsList" + mTabItemsList.size());
                mItems.clear();
                mItems.addAll(mTabItemsList);
                Predicate<Products> conditionPre =
                    orderCountData -> !orderCountData.isPrescriptionRequired();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mItems.removeIf(conditionPre);
                }
                if (mItems.size() > ZERO) {
                  Products productWithPre = new Products();
                  productWithPre.setItemType(ONE);
                  productWithPre.setPrescriptionCount(mItems.size());
                  pharmacyItems.add(productWithPre);
                  pharmacyItems.addAll(mItems);
                }
                mItems.addAll(mTabItemsList);
                Predicate<Products> conditionNotPre =
                    orderCountData -> orderCountData.isPrescriptionRequired();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mItems.removeIf(conditionNotPre);
                }
                if (mItems.size() > ZERO) {
                  Products productWithPre = new Products();
                  productWithPre.setItemType(TWO);
                  productWithPre.setPrescriptionCount(mItems.size());
                  pharmacyItems.add(productWithPre);
                  pharmacyItems.addAll(mItems);
                }
                addPharmacyItems(pharmacyItems, mOrderDetails.getData().getStoreType());
              } else if (tab.getPosition() == ONE) {
                mOrderDetailsBinding.accountDetails.llPaymentBreakDown.setVisibility(View.GONE);
                mOrderDetailsBinding.accountDetails.svItemContainer.setVisibility(View.GONE);
                mOrderDetailsBinding.accountDetails.rvPrescription.setVisibility(View.VISIBLE);
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(View.GONE);
              } else {
                mOrderDetailsBinding.accountDetails.llPaymentBreakDown.setVisibility(View.GONE);
                mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer.removeAllViews();
                mOrderDetailsBinding.accountDetails.svItemContainer.setVisibility(View.VISIBLE);
                mOrderDetailsBinding.accountDetails.rvPrescription.setVisibility(View.GONE);
                pharmacyItems.clear();
                mTabItemsList.clear();
                mTabItemsList.addAll(mOrderDetails.getData().getProducts());
                Predicate<Products> condition =
                    orderCountData -> Integer.parseInt(orderCountData.getStatus().getStatus())
                        != CANCELLED;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mTabItemsList.removeIf(condition);
                }
                mItems.clear();
                mItems.addAll(mTabItemsList);
                Predicate<Products> conditionPre =
                    orderCountData -> !orderCountData.isPrescriptionRequired();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mItems.removeIf(conditionPre);
                }
                if (mItems.size() > ZERO) {
                  Products productWithPre = new Products();
                  productWithPre.setItemType(ONE);
                  productWithPre.setPrescriptionCount(mItems.size());
                  pharmacyItems.add(productWithPre);
                  pharmacyItems.addAll(mItems);
                }
                mItems.clear();
                mItems.addAll(mTabItemsList);
                Predicate<Products> conditionNotPre =
                    orderCountData -> orderCountData.isPrescriptionRequired();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                  mItems.removeIf(conditionNotPre);
                }
                if (mItems.size() > ZERO) {
                  Products productWithPre = new Products();
                  productWithPre.setItemType(TWO);
                  productWithPre.setPrescriptionCount(mItems.size());
                  pharmacyItems.add(productWithPre);
                  pharmacyItems.addAll(mItems);
                }
                addPharmacyItems(pharmacyItems, mOrderDetails.getData().getStoreType());
                mOrderDetailsBinding.accountDetails.ivNoOrders.setVisibility(
                    pharmacyItems.size() > ZERO ? View.GONE : View.VISIBLE);
                EcomUtil.printLog("exe" + "mTabItemsList Can  " + mTabItemsList.size());
              }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
          });
    }
  }

  /**
   * <h>viewsVisibilityStatus</h>
   * <p>Views visibility status based on the order status</p>
   */
  private void viewsVisibilityStatus() {
    // If the status of the order is "Accepted" then only show the
    // status tab layout
    mOrderDetailsBinding.llOrderDetailsOrderStatus.setVisibility(
        mStatus.equals(String.valueOf(ACCEPTED)) ? View.VISIBLE : View.GONE);
    if (mStatus.equals(String.valueOf(ACCEPTED)) && presenter.getStoreType() == RESTAURENT) {
      mOrderDetailsBinding.linearLayoutOrderDetailsAcceptOrder.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.layoutButton.buttonCommon.setText(
          getResources().getString(R.string.packOrder));
    }
    mOrderDetailsBinding.layoutToolBar.ivAddProduct.setVisibility(
        mStatus.equals(String.valueOf(ACCEPTED)) && (presenter.getStoreType() == PHARMACY
            || presenter.getStoreType() == GROCERY)
            ? View.VISIBLE : View.GONE);
  /*  mOrderDetailsBinding.accountDetails.llPaymentBreakDown.setVisibility(
        Integer.parseInt(mStatus) == NEW || Integer.parseInt(mStatus) == DISPATCH
            || Integer.parseInt(mStatus) == PACKED
            ? View.VISIBLE : View.GONE);*/
    mOrderDetailsBinding.accountDetails.llPaymentBreakDown.setVisibility(
        Integer.parseInt(mStatus) != ACCEPTED ? View.VISIBLE : View.GONE);
    if (Integer.parseInt(mStatus) == NEW && presenter.getStoreType() == PHARMACY) {
      mOrderDetailsBinding.accountDetails.tbPharmacyOptions.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.accountDetails.llItemDetails.setVisibility(View.GONE);
    }
    mOrderDetailsBinding.linearLayoutOrderDetailsOptions.setVisibility(
        mStatus.equals(String.valueOf(NEW)) ? View.VISIBLE : View.GONE);
    mOrderDetailsBinding.cardViewOrderDetailsManualAutoOption.setVisibility(
        mStatus.equals(String.valueOf(NEW)) ? View.GONE : View.GONE);
    if (mStatus.equals(String.valueOf(NEW))) {
      mOrderDetailsBinding.layoutOptions.textViewOptionsFirst.setText(
          presenter.getStoreType() == PHARMACY ? getString(R.string.rejectAll) :
              getString(R.string.rejectOrder));
      if (presenter.getStoreType() == PHARMACY) {
        mOrderDetailsBinding.layoutOptions.textViewOptionsFirst.setTextColor(
            getResources().getColor(R.color.red));
      }
      mOrderDetailsBinding.layoutOptions.textViewOptionsSecond.setText(getString(R.string.accept));
    } else if (mStatus.equals(String.valueOf(PACKED))) {
      if (presenter.getDriverType() == ONE) {
        mOrderDetailsBinding.linearLayoutOrderDetailsAcceptOrder.setVisibility(View.VISIBLE);
        mOrderDetailsBinding.layoutButton.buttonCommon.setText(getString(R.string.manualDispatch));
      } else {
        mOrderDetailsBinding.llOrderDetailsShipmentOptions.setVisibility(View.VISIBLE);
        mOrderDetailsBinding.shipmentOptions.tvManualShipment.setText(
            getString(R.string.manualDispatch));
        mOrderDetailsBinding.shipmentOptions.tvAutoShiment.setText(
            getString(R.string.autoDispatch));
      }
    } else if (mStatus.equals(String.valueOf(CHECKED))) {
      mOrderDetailsBinding.layoutButton.buttonCommon.setText(getString(R.string.uploadReceipt));
    }
  }

  /**
   * Set bottom padding for the nested scroll
   * view
   */
  private void setNestedScrollViewBottomPadding() {
    ViewTreeObserver viewTreeObserver =
        mOrderDetailsBinding.linearLayoutOrderDetailsBottomOptions.getViewTreeObserver();
    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        mOrderDetailsBinding.linearLayoutOrderDetailsBottomOptions.getViewTreeObserver().removeOnGlobalLayoutListener(
            this);
        int height = mOrderDetailsBinding.linearLayoutOrderDetailsBottomOptions.getMeasuredHeight();
        mOrderDetailsBinding.nsOrderDetailsScrollContainer.setPaddingRelative(
            ZERO,
            ZERO,
            ZERO,
            height + TWENTY);
      }
    });
  }

  @SuppressLint("SetTextI18n")
  public void setFares(String subTotal, String discount, String promoDiscount, String delCharge,
      String serviceCharge,
      String total, ArrayList<ProductTaxArray> exclusiveTaxes, String storeType,
      String subTotalAmount) {
    double mSubTotal = mOrderDetails.getData().getSellerAccounting().getTaxableAmount();
    float mDiscount = Float.parseFloat(discount);
    float mDelCharge = Float.parseFloat(delCharge);
    double mTotal = mOrderDetails.getData().getSellerAccounting().getSubTotal();
    double totalTaxes = 0.00;
    Log.d("exe", "mDelCharge" + mDelCharge + "exclusiveTaxes   " + exclusiveTaxes.size());
    if (storeType.equals(String.valueOf(READY_FOR_PICK_UP))) {
      mOrderDetailsBinding.accountDetails.tvOrderDetailsSubTotalValue.setText(
          String.format("%s %s", mCurrencySymbol,
              Utility.roundOfDoubleValue(String.valueOf(mSubTotal))));
      mOrderDetailsBinding.accountDetails.tvOrderDetailsDiscountValue.setText(
          String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(discount)));
    } else if (storeType.equals(String.valueOf(COMPLETED))) {
      mSubTotal = mDelCharge;
      mOrderDetailsBinding.accountDetails.tvOrderDetailsSubTotalValue.setText(
          String.format("%s %s", mCurrencySymbol,
              Utility.roundOfDoubleValue(String.valueOf(mSubTotal))));
      mOrderDetailsBinding.accountDetails.tvOrderDetailsDiscountValue.setText(
          String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(discount)));
      mOrderDetailsBinding.accountDetails.tvOrderDetailsDeliveryCharge.setText(
          String.format("%s %s", mCurrencySymbol,
              Utility.roundOfDoubleValue(String.valueOf(mDelCharge))));
    } else {
      mOrderDetailsBinding.accountDetails.tvOrderDetailsSubTotalValue.setText(
          String.format("%s %s", mCurrencySymbol,
              Utility.roundOfDoubleValue(String.valueOf(mSubTotal))));
      mOrderDetailsBinding.accountDetails.tvOrderDetailsDiscountValue.setText(
          String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(discount)));
    }
    if (promoDiscount != null && !promoDiscount.isEmpty()) {
      mOrderDetailsBinding.accountDetails.llPromoDiscount.setVisibility(View.VISIBLE);
      mOrderDetailsBinding.accountDetails.tvPromoDiscountValue.setText(
          String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(promoDiscount)));
    }
    mOrderDetailsBinding.accountDetails.llOrderDetailsTaxesDetails.setVisibility(
        exclusiveTaxes != null && exclusiveTaxes.size() < ONE
            || presenter.getStoreType() == FIVE ||
            Integer.parseInt(storeType) == FIVE || Integer.parseInt(storeType) == SEVENTY_FIVE ?
            View.GONE : View.VISIBLE);
    mOrderDetailsBinding.accountDetails.tvOrderDetailsTaxes.setVisibility(
        exclusiveTaxes != null && exclusiveTaxes.size() < ONE
            || presenter.getStoreType() == FIVE ||
            Integer.parseInt(storeType) == FIVE || Integer.parseInt(storeType) == SEVENTY_FIVE
            ? View.GONE : View.VISIBLE);
    if (exclusiveTaxes != null && exclusiveTaxes.size() < ONE
        || presenter.getStoreType() == FIVE ||
        Integer.parseInt(storeType) == FIVE || Integer.parseInt(storeType) == SEVENTY_FIVE) {
      mOrderDetailsBinding.accountDetails.tvOrderDetailsTaxesTotal.setVisibility(
          View.GONE);
    } else {
      View view;
      TextView taxType1Tv;
      TextView taxType1AmountTv;
      LayoutInflater inflater = LayoutInflater.from(this);
      mOrderDetailsBinding.accountDetails.llOrderDetailsTaxesDetailsContainer.removeAllViews();
      for (int j = ZERO; j < exclusiveTaxes.size(); j++) {
        view = inflater.inflate(R.layout.item_tax_single_row,
            mOrderDetailsBinding.accountDetails.llOrderDetailsTaxesDetailsContainer,
            false);
        taxType1Tv = view.findViewById(R.id.taxType1Tv);
        taxType1Tv.setTextColor(getResources().getColor(R.color.shark));
        taxType1AmountTv = view.findViewById(R.id.taxType1AmountTv);
        taxType1AmountTv.setTextColor(getResources().getColor(R.color.shark));
        String taxType = String.format("%s(%s%%)", exclusiveTaxes.get(j).getTaxName(),
            Utility.roundOfDoubleValue(
                String.format("%s", exclusiveTaxes.get(j).getTaxValue())));
        taxType1Tv.setText(taxType);
        taxType1AmountTv.setText(String.format("%s %s", mCurrencySymbol,
            Utility.roundOfDoubleValue(
                String.format("%s", exclusiveTaxes.get(j).getTotalValue()))));
        mOrderDetailsBinding.accountDetails.llOrderDetailsTaxesDetailsContainer.addView(view);
        if (exclusiveTaxes.get(j).getTotalValue() != null) {
          totalTaxes += Float.parseFloat(exclusiveTaxes.get(j).getTotalValue());
        }
      }
    }
    mAddOnCount += mTotal;
    if (storeType.equals(String.valueOf(FIVE))) {
      mTotal += mTotalAmt;
    }
    mOrderDetailsBinding.accountDetails.tvOrderDetailsTaxesTotal.setText(
        String.format("%s %s", mCurrencySymbol,
            Utility.roundOfDoubleValue(String.format("%s", totalTaxes))));
    if (storeType.equals(String.valueOf(SEVEN))) {
      mTotal = mSubTotal;
    }
    mOrderDetailsBinding.accountDetails.tvOrderDetailsTotalValue.setText(
        String.format("%s %s", mCurrencySymbol, Utility.roundOfDoubleValue(mTotal + "")));
  }

  /**
   * This method is used to open the manual dispatch
   */
  public void openManualShipmentAct(boolean assignManually) {
    Intent intent = new Intent(this, SelectDriversActivity.class);
    intent.putExtra(ORDER_ID, mOrderId);
    intent.putExtra(PACKAGE_ID, mPackageId);
    intent.putExtra(SLOT_ID, mOrderDetails.getData().getDeliverySlotId());
    intent.putExtra(ASSIGN_MANUALLY, assignManually);
    startActivityForResult(intent, DRIVER_REQUEST);
  }

  @Override
  public void openPastOrderAct() {
    Intent intent = new Intent(this, PastOrdersAct.class);
    intent.putExtra(CUSTOMER_ID, mCustomerId);
    startActivity(intent);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void openOrderEditDialog(Products products, int index) {
    mDialog = new Dialog(this);
    mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    mDialog.setContentView(R.layout.item_order_edit_dailog);
    mDialog.setCancelable(false);
    Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(
        new ColorDrawable(Color.TRANSPARENT));
    mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    TextView tvItemName = mDialog.findViewById(R.id.tvItemName);
    TextView tvItemPrice = mDialog.findViewById(R.id.tvItemPrice);
    TextView tvDelete = mDialog.findViewById(R.id.tvDelete);
    TextView tvUpdate = mDialog.findViewById(R.id.tvUpdate);
    final EditText etQuantity = mDialog.findViewById(R.id.etQuantity);
    ImageView ivCancel = mDialog.findViewById(R.id.ivCancel);
    tvItemName.setText(products.getName());
    etQuantity.setText(String.format("%s", products.getQuantity()));
    etQuantity.setSelection(etQuantity.getText().length());
    if (products.getAccounting().getFinalTotal() != null) {
      tvItemPrice.setText(String.format(" X %s%s", mCurrencySymbol,
          String.format("%.2f", products.getAccounting().getFinalTotal())));
    }
    ivCancel.setOnClickListener(v -> mDialog.dismiss());
    tvUpdate.setOnClickListener(v -> {
      disable(etQuantity.getText().toString(), index);
      mDialog.dismiss();
    });
    tvDelete.setOnClickListener(v -> mDialog.dismiss());
    mDialog.show();
  }

  @Override
  public void dissMiss() {
    finish();
  }

  @Override
  public void showLoader() {
    mOrderDetailsBinding.layoutLoader.clLoaderMainContainer.setVisibility(View.VISIBLE);
    mOrderDetailsBinding.cvOrderDetailsToolbar.setVisibility(View.GONE);
  }

  @Override
  public void hideLoader() {
    mOrderDetailsBinding.layoutLoader.ivLoaderTick.setVisibility(View.VISIBLE);
    new Handler().postDelayed(this::openDispatchDetails, THOUSAND);
  }

  @Override
  public void showError(String message, int code) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void refreshAPi() {
    mIsUpdateOrAdd = FALSE;
    presenter.getBundleData(getIntent().getExtras());
  }

  @Override
  public void orderConfirm() {
    Intent intent = new Intent(this, ForcePickActivity.class);
    intent.putExtra(OPEN_CAMERA, TRUE);
    intent.putExtra(CURRENCY_SYMBOL, mItemsArrayList.get(mIndex).getCurrencySymbol());
    intent.putExtra(CURRENCY_CODE, mItemsArrayList.get(mIndex).getCurrencyCode());
    intent.putExtra(ORDER_ID, mOrderId);
    intent.putExtra(ITEM_PRICE,
        String.format("%s %s", mOrderDetails.getData().getAccounting().getCurrencySymbol(),
            mOrderDetails.getData().getAccounting().getFinalTotal()));
    intent.putExtra(QUANTITY, mOrderDetails.getData().getProducts().size());
    intent.putExtra(CUSTOMER_NAME,
        mOrderDetailsBinding.textViewOrderDetailsCustomerName.getText().toString());
    CustomerDetails customerDetails = mOrderDetails.getData().getCustomerDetails();
    String number = customerDetails.getMobile();
    intent.putExtra(CUSTOMER_NUMBER, number);
    EcomUtil.printLog("exe" + "mItemsArrayList Upload" + mItemsArrayList.size());
    startActivityForResult(intent, UPLOAD_FORCE_PICK_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null) {
      if (requestCode == DRIVER_REQUEST) {
        if (resultCode == RESULT_OK) {
          boolean isToFinish = data.getBooleanExtra(FINISH, false);
          String driverId = data.getStringExtra(DRIVER_ID);
          if (isToFinish) {
            if (driverId != null) {
              if (mStatus.equals(String.valueOf(PACKED))) {
                presenter.readyForPickUp(driverId);
              }
            } else {
              finish();
            }
          }
        }
      } else if (requestCode == SELECT_REASON) {
        if (resultCode == RESULT_OK) {
          boolean isToFinish = data.getBooleanExtra(FINISH, false);
          if (isToFinish) {
            finishActivity();
          } else {
            if (presenter.getStoreType() == PHARMACY) {
              int quantity = data.getIntExtra(SELECTED_COUNT, ONE);
              presenter.confirmUnAvailability(mItemsArrayList.get(mIndex), quantity,
                  data.getStringExtra(REASON));
            } else {
              refreshApi();
            }
          }
        }
      } else if (requestCode == PHARMACY_REJECT) {
        if (resultCode == RESULT_OK) {
          String reason = data.getStringExtra(REASON);
          String orderId = mItemsArrayList.get(mIndex).getProductOrderId();
          if (data.getBooleanExtra(ORDER_ID, FALSE)) {
            EcomUtil.printLog("exe" + "mItemsArrayListFirst" + mItemsArrayList.size());
            presenter.rejectOrder(mOrderId, reason,
                presenter.isCityLogin() ? MASTER_ORDER : STORE_ORDER);
          } else {
            EcomUtil.printLog("exe" + "mItemsArrayListSecond" + mItemsArrayList.size());
            mOrderDetailsBinding.layoutOptions.textViewOptionsSecond.setText(
                getResources().getString(R.string.getConfirmation));
            presenter.rejectOrder(orderId, reason, PRODUCT_ORDER_TYPE);
          }
        }
      } else if (requestCode == PRODUCT_SUBSTITUDE) {
        if (resultCode == RESULT_OK) {
          refreshAPi();
        }
      } else if (requestCode == UPDATE_ASILE) {
        if (resultCode == RESULT_OK) {
          String aisle = data.getStringExtra(AISLE);
          String shelf = data.getStringExtra(SHELF);
          String sector = data.getStringExtra(SECTOR);
          mItemsArrayList.get(mIndex).setAisle(aisle);
          mItemsArrayList.get(mIndex).setShelf(shelf);
          mItemsArrayList.get(mIndex).setDirections(sector);
          View view = mOrderDetailsBinding.accountDetails.llOrderDetailsItemContainer
              .getChildAt(mIndex);
          TextView tvAisle = view.findViewById(R.id.tvAisle);
          tvAisle.setText(String.format("%s.%s.%s", aisle, shelf, sector));
        }
      } else if (requestCode == BARCODE_REQUEST) {
        if (resultCode == RESULT_OK) {
          String productOrderId = data.getStringExtra(PRODUCT_ORDER_ID);
          int quantity = data.getIntExtra(QUANTITY, ONE);
          String imgUrl = data.getStringExtra(IMAGE_URL);
          presenter.pickApi(productOrderId, quantity, imgUrl);
        }
      } else if (requestCode == FORCE_PICK_REQUEST) {
        if (resultCode == RESULT_OK) {
          String productOrderId = mItemsArrayList.get(mIndex).getProductOrderId();
          int quantity = data.getIntExtra(QUANTITY, ONE);
          String imgUrl = data.getStringExtra(IMAGE_URL);
          presenter.pickApi(productOrderId, quantity, imgUrl);
        }
      } else if (requestCode == UPLOAD_FORCE_PICK_REQUEST) {
        if (resultCode == RESULT_OK) {
          finish();
        }
      } else if (requestCode == ADD_PRODUCT_REQUEST) {
        if (resultCode == RESULT_OK) {
          EcomUtil.printLog("exe" + "addProduct" + ADD_PRODUCT_REQUEST);
          refreshAPi();
        }
      }
    }
  }

  /**
   * open key pad once permission accepted.
   *
   * @param number string
   */
  private void checkCallPermission(String number) {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {
      mPhoneNumber = number;
      ActivityCompat
          .requestPermissions(
              this,
              new String[]{Manifest.permission.CALL_PHONE},
              MAKE_PHONE_CALL);
    } else {
      Utility.callToNumber(number, this);
    }
  }

  /**
   * <h>openDispatchDetails</h>
   * <p>open the dispatch details screen</p>
   */
  private void openDispatchDetails() {
    Intent intent = new Intent(this, DispatchDetailsActivity.class);
    intent.putExtra(ORDER_ID, mOrderId);
    startActivity(intent);
    finish();
  }

  /**
   * this method is used to refresh the order detail api
   */
  public void refreshApi() {
    mIsUpdateOrAdd = FALSE;
    presenter.getBundleData(getIntent().getExtras());
  }

  /**
   * this method is used to accept the food order item.
   */
  public void acceptFoodOrder(String time) {
    presenter.updateOrder(ACCEPTED, time);
  }

  /**
   * this method is used to refresh the order detail api
   */
  public void startProductUnAvailableAct(Products item, int value) {
    Intent intent = new Intent(this,
        ProductUnavailableSubstituteActivity.class);
    intent.putExtra(ITEM_DATA, item);
    intent.putExtra(STORE_CATEGORY_ID, mOrderDetails.getData().getStoreCategoryId());
    intent.putExtra(REASON_ID, REASON_PRODUCT_UNAVAILABLE);
    intent.putExtra(SELECTED_COUNT, value);
    intent.putExtra(CURRENCY_SYMBOL, mCurrencySymbol);
    intent.putExtra(ITEM_AVAILABLE, TRUE);
    startActivityForResult(intent, SELECT_REASON);
  }

  /**
   * call pick api
   *
   * @param quantity quantity to be added.
   */
  public void callPickApi(int quantity) {
    presenter.pickApi(mItemsArrayList.get(mIndex).getProductOrderId(), quantity, mProductImg);
  }

  /**
   * this was used to set the
   */
  private void tabsData(Count count) {
    for (int i = ZERO; i < mStatusViewPager.getCount(); i++) {
      try {
        TabLayout.Tab tab = mOrderDetailsBinding.tlOrderDetailsOrderStatus.getTabAt(i);
        if (mIsUpdateOrAdd) {
          tab.setCustomView(mStatusViewPager.getTabView(i));
        } else {
          switch (i) {
            case ZERO:
              TextView tvPending = tab.getCustomView().findViewById(R.id.tvOrderStatusCount);
              tvPending.setText(count.getPending());
              break;
            case ONE:
              EcomUtil.printLog("exe" + "countPicked" + count.getPicked());
              TextView tvPacked = tab.getCustomView().findViewById(R.id.tvOrderStatusCount);
              tvPacked.setText(presenter.getStoreType() == RESTAURENT ? count.getUnavailable() :
                  (presenter.getStoreType() == PHARMACY || presenter.getStoreType() == GROCERY)
                      ? count.getPicked() : count.getPacked());
              break;
            case TWO:
              TextView tvInReview = tab.getCustomView().findViewById(R.id.tvOrderStatusCount);
              tvInReview.setText(count.getReview());
              break;
            case THREE:
              TextView tvUnavailable = tab.getCustomView().findViewById(R.id.tvOrderStatusCount);
              tvUnavailable.setText(count.getUnavailable());
              break;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        EcomUtil.printLog("exe" + "ExceptionTab" + e.toString());
      }
    }
    EcomUtil.printLog(
        "exe" + "tabCount" + mOrderDetailsBinding.tlOrderDetailsOrderStatus.getTabCount());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode,
        permissions,
        grantResults);
    if (requestCode == MAKE_PHONE_CALL) {
      // Checking whether user granted the permission or not.
      if (grantResults.length > ZERO
          && grantResults[ZERO] == PackageManager.PERMISSION_GRANTED) {
        // Showing the toast message
        Utility.callToNumber(mPhoneNumber, this);
      }
    }
  }

  @Override
  public void onSelectItem(int position) {
    Intent intent = new Intent(this, PrescriptionDetail.class);
    intent.putExtra(RECEIPT_IMG, FALSE);
    intent.putExtra(PRESCRIPTION_IMG, mOrderDetails.getData().getOrderImages().get(position));
    startActivity(intent);
  }
}
