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

import static com.app.ecomstore.util.EcomConstants.GROCERY;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PHARMACY;
import static com.app.ecomstore.util.EcomConstants.RESTAURENT;
import static com.app.ecomstore.util.EcomConstants.THREE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.app.delivxstore.R;
import com.app.delivxstore.main.mobileView.orderDetails.models.Count;
import com.app.ecomstore.util.EcomUtil;
import com.google.android.material.tabs.TabLayout;

/**
 * Viewpager for the order item status
 */
public class StatusViewPager extends PagerAdapter {
  private Context mContext;
  private String tabTitles[];
  private int mStoreType;
  private Count count;

  StatusViewPager(Context context, int storeType) {
    this.mContext = context;
    this.mStoreType = storeType;
    if (mStoreType == RESTAURENT) {
      tabTitles = new String[]{mContext.getResources().getString(R.string.pending),
          mContext.getResources().getString(R.string.removed)};
    } else if (mStoreType == PHARMACY || mStoreType == GROCERY) {
      tabTitles = new String[]{mContext.getResources().getString(R.string.pending),
          mContext.getResources().getString(R.string.pickedTxt),
          mContext.getResources().getString(R.string.inReview),
          mContext.getResources().getString(R.string.removed)};
    } else {
      tabTitles = new String[]{mContext.getResources().getString(R.string.pending),
          mContext.getResources().getString(R.string.packed),
          mContext.getResources().getString(R.string.removed)};
    }
    EcomUtil.printLog("exe" + "tabTitles" + tabTitles);
  }

  /**
   * <h>get the custom views of the tab layout</h>
   *
   * @param position position for which the custom view is required
   * @return a custom view
   */
  View getTabView(int position) {
    @SuppressLint("InflateParams")
    View v = LayoutInflater.from(mContext).inflate(R.layout.item_order_status, null);
    TextView tvStatusTxt = v.findViewById(R.id.tvOrderStatusName);
    TextView tvStatusCount = v.findViewById(R.id.tvOrderStatusCount);
    CardView mainCv = v.findViewById(R.id.cvOrderStatus);
    tvStatusTxt.setText(tabTitles[position]);
    EcomUtil.printLog("exe" + "position" + position);
    switch (position) {
      case ZERO:
        EcomUtil.printLog("exe" + "countTab0" + count.getPending());
        tvStatusCount.setText(count.getPending());
        break;
      case ONE:
        EcomUtil.printLog("exe" + "countTab1" + count.getPacked());
        EcomUtil.printLog("exe" + "countPicked" + count.getPicked());
        tvStatusCount.setText(mStoreType == RESTAURENT ? count.getUnavailable() :
            (mStoreType == PHARMACY || mStoreType == GROCERY) ? count.getPicked()
                : count.getPacked());
        break;
      case TWO:
        tvStatusCount.setText(count.getReview());
        break;
      case THREE:
        tvStatusCount.setText(count.getUnavailable());
        break;
    }
    tvStatusTxt.setTextColor(mContext.getResources().getColor(R.color.colorBlueZodiac));
    tvStatusCount.setTextColor(mContext.getResources().getColor(R.color.colorBlueZodiac));
    mainCv.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
    return v;
  }

  /**
   * <h>set the view for selected tab item</h>
   *
   * @param tabLayout tab layout
   * @param position  selected position
   */
  void setOnSelectView(TabLayout tabLayout, int position) {
    TabLayout.Tab tab = tabLayout.getTabAt(position);
    assert tab != null;
    View selected = tab.getCustomView();
    assert selected != null;
    TextView tvOrderStatusName = selected.findViewById(R.id.tvOrderStatusName);
    TextView tvOrderStatusCount = selected.findViewById(R.id.tvOrderStatusCount);
    CardView cvOrderStatus = selected.findViewById(R.id.cvOrderStatus);
    tvOrderStatusName.setTextColor(mContext.getResources().getColor(R.color.white));
    tvOrderStatusCount.setTextColor(mContext.getResources().getColor(R.color.white));
    cvOrderStatus.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
  }

  /**
   * <h>set the view for selected tab item</h>
   *
   * @param tabLayout tab layout
   * @param position  unselected selected position
   */
  void setUnSelectView(TabLayout tabLayout, int position) {
    TabLayout.Tab tab = tabLayout.getTabAt(position);
    assert tab != null;
    View selected = tab.getCustomView();
    assert selected != null;
    TextView tvStatus = selected.findViewById(R.id.tvOrderStatusName);
    TextView tvValue = selected.findViewById(R.id.tvOrderStatusCount);
    CardView mainCv = selected.findViewById(R.id.cvOrderStatus);
    tvStatus.setTextColor(mContext.getResources().getColor(R.color.blackShade));
    tvValue.setTextColor(mContext.getResources().getColor(R.color.blackShade));
    mainCv.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
  }

  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_order_status, collection, false);
    collection.addView(layout);
    return layout;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    ViewPager vp = (ViewPager) container;
    View view = (View) object;
    vp.removeView(view);
  }

  @Override
  public int getCount() {
    return tabTitles != null ? tabTitles.length : ZERO;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
    return view == o;
  }

  public void setTabText(Count count) {
    this.count = count;
  }
}
