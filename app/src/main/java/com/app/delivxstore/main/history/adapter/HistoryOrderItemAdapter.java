package com.app.delivxstore.main.history.adapter;

import static com.app.delivxstore.utility.OrderStatusConstant.ACCEPTED;
import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED;
import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED_AND_COMPLETED;
import static com.app.delivxstore.utility.OrderStatusConstant.COMPLETED;
import static com.app.delivxstore.utility.OrderStatusConstant.IN_DISPATCH;
import static com.app.delivxstore.utility.OrderStatusConstant.NEW;
import static com.app.delivxstore.utility.OrderStatusConstant.PACKED_AND_READY;
import static com.app.delivxstore.utility.OrderStatusConstant.READY_FOR_PICKUP;
import static com.app.delivxstore.utility.Utility.openWebBrowser;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemHistorydetailsBinding;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*binds recyclerview data of order item*/
public class HistoryOrderItemAdapter extends RecyclerView.Adapter {
  private Context mContext;
  private int itemType;
  private LinkedHashMap<String, ArrayList<Products>> mHashMap;

  public HistoryOrderItemAdapter(LinkedHashMap<String, ArrayList<Products>> hashMap,
      int itemType) {
    mHashMap = hashMap;
    this.itemType = itemType;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    ItemHistorydetailsBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_historydetails, parent, false);
    return new HistoryOrderItemViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof HistoryOrderItemViewHolder) {
      HistoryOrderItemViewHolder viewHolder = (HistoryOrderItemViewHolder) holder;
      if (mHashMap.keySet().toArray()[position] != null && !String.valueOf(
          mHashMap.keySet().toArray()[position]).isEmpty()) {
        viewHolder.binding.tvTransId.setText(
            String.format("%s: %s", mContext.getResources().getString(R.string.transactionId),
                mHashMap.keySet().toArray()[position]));
      } else {
        viewHolder.binding.tvTransId.setVisibility(View.GONE);
      }
      viewHolder.binding.rvItems.setAdapter(
          new HistoryDetailsItemAdapter((ArrayList<Products>) mHashMap.values().toArray()[position],
              itemType));
      if (mHashMap.values().toArray().length > ZERO) {
        ArrayList<Products> orderHistProductDataList =
            (ArrayList<Products>) mHashMap.values().toArray()[position];
        if (orderHistProductDataList != null && orderHistProductDataList.size() > ZERO) {
          Products orderHistProductData = orderHistProductDataList.get(ZERO);
          if (orderHistProductData.getInvoiceLink() == null
              || orderHistProductData.getInvoiceLink().isEmpty()) {
            viewHolder.binding.tvInvoice.setVisibility(View.GONE);
          }
          if (orderHistProductData.getShippingLabel() == null
              || orderHistProductData.getShippingLabel().isEmpty()) {
            viewHolder.binding.tvShippingLabel.setVisibility(View.GONE);
          }
          viewHolder.binding.tvStatus.setText(orderHistProductData.getStatus().getStatusText());
          setTimeStamp(Integer.parseInt(orderHistProductData.getStatus().getStatus()),
              orderHistProductData,
              viewHolder.binding.tvDeliveredTime);
          if (Integer.parseInt(orderHistProductData.getStatus().getStatus()) == CANCELLED) {
            viewHolder.binding.tvStatus.setTextColor(
                mContext.getResources().getColor(R.color.cancelColor, null));
          }
          viewHolder.binding.tvInvoice.setOnClickListener(
              v -> openWebBrowser(orderHistProductData.getInvoiceLink(), mContext));
          viewHolder.binding.tvShippingLabel.setOnClickListener(
              v -> openWebBrowser(orderHistProductData.getShippingLabel(), mContext));
        }
      }
    }
  }

  @Override
  public int getItemCount() {
    return mHashMap != null ? mHashMap.size() : ZERO;
  }

  /*set time stamp according to status*/
  private void setTimeStamp(int status, Products productsItem,
      AppCompatTextView tvDeliveryTime) {
    Utility.printLog("exe" + "status" + status);
    String timeStamp = null;
    switch (status) {
      case PACKED_AND_READY:
        timeStamp = productsItem.getTimestamps().getPacked();
        break;
      case ACCEPTED:
        timeStamp = productsItem.getTimestamps().getAccepted();
        break;
      case IN_DISPATCH:
        timeStamp = productsItem.getTimestamps().getInDispatch();
        break;
      case READY_FOR_PICKUP:
        timeStamp = productsItem.getTimestamps().getReadyForPickup();
        break;
      case COMPLETED:
        timeStamp = productsItem.getTimestamps().getCompleted();
        break;
      case CANCELLED_AND_COMPLETED:
      case CANCELLED:
        timeStamp = productsItem.getTimestamps().getCancelled();
        break;
      case NEW:
        timeStamp = productsItem.getTimestamps().getCreated();
        break;
    }
    assert timeStamp != null;
    if (!timeStamp.equals("")) {
      Utility.printLog("Cancelled time stamp: " + Utility.changeTimeFormat1(Integer.parseInt
          (timeStamp)));
      tvDeliveryTime.setText(Utility.changeTimeFormat1(Integer.parseInt
          (timeStamp)));
    }
  }

  public class HistoryOrderItemViewHolder extends RecyclerView.ViewHolder {
    ItemHistorydetailsBinding binding;

    HistoryOrderItemViewHolder(ItemHistorydetailsBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
