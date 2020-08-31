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

package com.app.delivxstore.main.dispatchDetails;

import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.dispatchDetails.adapter.ItemsAdapter;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Show the pending dispatcher order
 * details
 */
public class DispatchDetailsActivity extends DaggerAppCompatActivity implements
        DispatchDetailsContract.DispatchDetailsView {

    @BindView(R.id.textViewAllTitle)
    TextView textView_all_title;
    @BindView(R.id.textView_orderDetails_orderId)
    TextView textView_orderDetails_orderId;
    @BindView(R.id.nestedScroll_orderDispatch_scrollView)
    NestedScrollView nestedScroll_orderDispatch_scrollView;
    @BindView(R.id.textView_orderDispatch_customerName)
    TextView textView_orderDispatch_customerName;
    @BindView(R.id.textView_orderDispatch_orderPlacedOn)
    TextView textView_orderDispatch_orderPlacedOn;
    @BindView(R.id.textView_orderDispatch_deliverAddress)
    TextView textView_orderDispatch_deliverAddress;
    @BindView(R.id.recyclerView_orderDispatch_itemList)
    RecyclerView recyclerView_orderDispatch_itemList;
    @BindView(R.id.buttonCommon)
    Button button_common;
    @BindView(R.id.linearLayoutOrderDetailsAcceptOrder)
    LinearLayout linearLayout_orderDetails_acceptOrder;
    @BindView(R.id.ivAddProduct)
    ImageView imageView_all_options;

    @Inject DispatchDetailsContract.DispatchDetailsPresenter presenter;
    @Inject PreferenceHelperDataSource preferenceHelperDataSource;

    private String mOrderId;
    private ItemsAdapter mItemsAdapter;
    private ArrayList<Items> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());
        setContentView(R.layout.activity_dispatch_details);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        if (getIntent()!=null) {
            mOrderId = getIntent().getStringExtra("orderId");
        }
        if (mOrderId!=null) {
            presenter.getOrderDetails(mOrderId);
            button_common.setText(getString(R.string.orderDispatch_dispatch));
            nestedScroll_orderDispatch_scrollView.setClipToPadding(false);
            ViewTreeObserver viewTreeObserver = linearLayout_orderDetails_acceptOrder.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    linearLayout_orderDetails_acceptOrder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int height = linearLayout_orderDetails_acceptOrder.getMeasuredHeight();
                    nestedScroll_orderDispatch_scrollView.setPaddingRelative(
                            0,
                            0,
                            0,
                            height + 20
                    );
                }
            });

            mItemsAdapter = new ItemsAdapter(this, mItems);
            recyclerView_orderDispatch_itemList.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_orderDispatch_itemList.setHasFixedSize(true);
            recyclerView_orderDispatch_itemList.setAdapter(mItemsAdapter);
            imageView_all_options.setImageResource(R.drawable.ic_printer);
        }
    }

    @OnClick({R.id.imageViewAllBack, R.id.buttonCommon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewAllBack:
            case R.id.buttonCommon:
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setViews(OrderedItemDetails mOrderDetails) {
        textView_all_title.setText(mOrderDetails.getOrderId());
        textView_orderDetails_orderId.setText(mOrderDetails.getOrderId());
        textView_orderDispatch_customerName.setText(mOrderDetails.getCustomerDetails().getName());
        textView_orderDispatch_orderPlacedOn.setText(Utility.getTimeStamp(mOrderDetails.getTimeStamp().getCreated().getTimeStamp()));
        textView_orderDispatch_deliverAddress.setText(mOrderDetails.getStoreAddress());

        mItems.clear();
        mItems.addAll(mOrderDetails.getItems());
        mItemsAdapter.notifyDataSetChanged();
    }
}
