package com.app.delivxstore.main.history.historydetails;

import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED;
import static com.app.delivxstore.utility.OrderStatusConstant.ECOMM_PARTNER;
import static com.app.delivxstore.utility.Utility.openWebBrowser;
import static com.app.delivxstore.utility.VariableConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityHistoryDetailsBinding;
import com.app.delivxstore.main.history.adapter.HistoryOrderItemAdapter;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.inject.Inject;

/*shows details of order history*/
public class HistoryDetailsActivity extends DaggerAppCompatActivity
    implements HistoryDetailsContract.HistoryDetailsView, View.OnClickListener {
  @Inject
  HistoryDetailsContract.Presenter presenter;
  private HistoryOrderItemAdapter mAdapter;
  private ActivityHistoryDetailsBinding mBinding;
  private String mPoInvoiceLink;
  private OrderDetailsModel mOrderDetailsModel;
  private String mOrderId, mPackageId;
  private String mStatus;
  private LinkedHashMap<String, ArrayList<Products>> mHashMap = new LinkedHashMap<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityHistoryDetailsBinding.inflate(LayoutInflater.from(this));
    setContentView(mBinding.getRoot());
    initViews();
    presenter.callHistoryDetail(getIntent().getExtras());
  }

  /*initialize views*/
  private void initViews() {
    mBinding.includeActionBar.imageViewAllBack.setOnClickListener(this);
    mBinding.includeActionBar.tvPo.setOnClickListener(this);
  }

  /*initialize order item recyclerview*/
  private void initRecyclerView(OrderDetailsModel orderDetailsModel) {
    String packageId = "";
    for (int i = 0; i < orderDetailsModel.getData().getProducts().size(); i++) {
      packageId = orderDetailsModel.getData().getProducts().get(i).getPackageId();
      EcomUtil.printLog("exe" + "packageId" + packageId);
      Log.d("exe", "packageId" + packageId);
      if (mHashMap.containsKey(packageId)) {
        ArrayList<Products> value = mHashMap.get(packageId);
        if (value != null) {
          value.add(orderDetailsModel.getData().getProducts().get(i));
        }
      } else {
        ArrayList<Products> orderHistProductDataList = new ArrayList<>();
        orderHistProductDataList.add(orderDetailsModel.getData().getProducts().get(i));
        mHashMap.put(packageId, orderHistProductDataList);
      }
    }
    mBinding.rvHistoryItem.setHasFixedSize(true);
    mBinding.rvHistoryItem.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new HistoryOrderItemAdapter(mHashMap,
        presenter.getStoreType());
    mBinding.rvHistoryItem.setAdapter(mAdapter);
  }

  @Override
  public void hideProgress() {
    mBinding.pbHistoryDetail.setVisibility(View.GONE);
  }

  @Override
  public void showProgress() {
    mBinding.pbHistoryDetail.setVisibility(View.VISIBLE);
  }

  @Override
  public void showError(String message, int code) {
  }

  @Override
  public void setViews(OrderDetailsModel orderedItemDetails, boolean isCityLogin) {
    if (orderedItemDetails != null) {
      mOrderDetailsModel = orderedItemDetails;
      mStatus = mOrderDetailsModel.getData().getStatus().getStatus();
      this.mOrderId =
          isCityLogin ? mOrderDetailsModel.getData().getMasterOrderId()
              : mOrderDetailsModel.getData().getStoreOrderId();
      if (mOrderDetailsModel.getData().getProducts() != null) {
        mPackageId = mOrderDetailsModel.getData().getProducts().size() > ZERO
            ? mOrderDetailsModel.getData().getProducts().get(ZERO).getPackageId() : "";
      }
      initRecyclerView(orderedItemDetails);
      mBinding.groupBillingAddress.setVisibility(
          Integer.parseInt(orderedItemDetails.getData().getStoreType()) == ECOMM_PARTNER
              ? View.VISIBLE
              : View.GONE);
      mBinding.tvBillingCustomerName.setText(
          orderedItemDetails.getData().getBillingAddress().getName());
      mBinding.tvBillingAddress.setText(String.format("%s%s\n%s\n%s",
          orderedItemDetails.getData().getBillingAddress().getFlatNumber(),
          orderedItemDetails.getData().getBillingAddress().getAddLine1(),
          orderedItemDetails.getData().getBillingAddress().getAddLine2(),
          orderedItemDetails.getData().getBillingAddress().getCityName()));
      mBinding.tvOrderTime.setText(
          Utility.getOrderPlacedTime(orderedItemDetails.getData().getCreatedTimeStamp()));
      mPoInvoiceLink = orderedItemDetails.getData().getPoInvoiceLink();
      mBinding.tvMasterId.setText(orderedItemDetails.getData().getMasterOrderId());
      mBinding.includeActionBar.textViewAllTitle.setVisibility(View.VISIBLE);
      mBinding.includeActionBar.textViewAllTitle.setText(Integer.parseInt(mStatus) >= PACKED
          ? String.format("%s :%s", getResources().getString(R.string.packId), mPackageId)
          : String.format("%s :%s", getResources().getString(R.string.id), mOrderId));
      mBinding.includeActionBar.tvPo.setVisibility(View.VISIBLE);
      mBinding.tvTotalAmount.setText(String.format("%s %s | %s",
          orderedItemDetails.getData().getAccounting().getCurrencySymbol(),
          Utility.roundOfDoubleValue(
              String.valueOf(orderedItemDetails.getData().getAccounting().getFinalTotal())),
          orderedItemDetails.getData().getPaymentTypeText()));
      if (orderedItemDetails.getData().getCustomerDetails() != null) {
        mBinding.tvCustomerName.setText(String.format("%s %s",
            orderedItemDetails.getData().getCustomerDetails().getFirstName(),
            orderedItemDetails.getData().getCustomerDetails().getLastName()));
      }
      if (orderedItemDetails.getData().getProducts().size() == ONE
          && Integer.parseInt(orderedItemDetails.getData().getStatus().getStatus()) == CANCELLED) {
        mBinding.view6.setVisibility(View.GONE);
        mBinding.groupPaymentBreakDown.setVisibility(View.GONE);
      }
      mBinding.tvCustomerName2.setText(orderedItemDetails.getData().getDeliveryAddress().getName());
      mBinding.tvDeliveryAddress.setText(String.format("%s%s\n%s\n%s",
          orderedItemDetails.getData().getDeliveryAddress().getFlatNumber(),
          orderedItemDetails.getData().getDeliveryAddress().getAddLine1(),
          orderedItemDetails.getData().getDeliveryAddress().getAddLine2(),
          orderedItemDetails.getData().getDeliveryAddress().getCity()));
      String currencySymbol = orderedItemDetails.getData().getAccounting().getCurrencySymbol();
      mBinding.tvTotalBeforeTaxAmount.setText(String.format("%s %s", currencySymbol,
          Utility.roundOfDoubleValue(
              String.valueOf(orderedItemDetails.getData().getAccounting().getFinalUnitPrice()))));
      addTaxDetails();
      mBinding.tvShippingFee.setText(String.format("%s %s", currencySymbol,
          Utility.roundOfDoubleValue(
              String.valueOf(orderedItemDetails.getData().getAccounting().getDeliveryFee()))));
      mBinding.tvTotalAfterTax.setText(String.format("%s %s", currencySymbol,
          Utility.roundOfDoubleValue(
              String.valueOf(orderedItemDetails.getData().getAccounting().getFinalTotal()))));
    }
  }

  /**
   * this method used to add the tax value.
   */
  private void addTaxDetails() {
    View view;
    TextView taxType1Tv;
    TextView taxType1AmountTv;
    LayoutInflater inflater = LayoutInflater.from(this);
    mBinding.llTaxDetails.removeAllViews();
    if (mOrderDetailsModel.getData().getAccounting().getTax() != null
        && mOrderDetailsModel.getData().getAccounting().getTax().size() > ZERO) {
      for (int j = ZERO; j < mOrderDetailsModel.getData().getAccounting().getTax().size(); j++) {
        view = inflater.inflate(R.layout.item_tax_single_row,
            mBinding.llTaxDetails, false);
        taxType1Tv = view.findViewById(R.id.taxType1Tv);
        taxType1Tv.setTextColor(getResources().getColor(R.color.shark));
        taxType1AmountTv = view.findViewById(R.id.taxType1AmountTv);
        taxType1AmountTv.setTextColor(getResources().getColor(R.color.shark));
        String taxType = String.format("%s(%s%%)",
            mOrderDetailsModel.getData().getAccounting().getTax().get(j).getTaxName(),
            Utility.roundOfDoubleValue(
                String.format("%s",
                    mOrderDetailsModel.getData().getAccounting().getTax().get(j).getTaxValue())));
        taxType1Tv.setText(taxType);
        taxType1AmountTv.setText(
            String.format("%s %s", mOrderDetailsModel.getData().getAccounting().getCurrencySymbol(),
                Utility.roundOfDoubleValue(
                    String.format("%s",
                        mOrderDetailsModel.getData().getAccounting().getTax().get(
                            j).getTotalValue()))));
        mBinding.llTaxDetails.addView(view);
      }
    } else {
      mBinding.tvTaxes.setVisibility(View.GONE);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imageViewAllBack:
        onBackPressed();
        break;
      case R.id.tvPo:
        openWebBrowser(mPoInvoiceLink, this);
        break;
    }
  }
}