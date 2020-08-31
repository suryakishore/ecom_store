package com.app.delivxstore.main.history.adapter;

import static com.app.delivxstore.utility.OrderStatusConstant.ACCEPTED;
import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED;
import static com.app.delivxstore.utility.OrderStatusConstant.CANCELLED_AND_COMPLETED;
import static com.app.delivxstore.utility.OrderStatusConstant.COMPLETED;
import static com.app.delivxstore.utility.OrderStatusConstant.IN_DISPATCH;
import static com.app.delivxstore.utility.OrderStatusConstant.NEW;
import static com.app.delivxstore.utility.OrderStatusConstant.PACKED_AND_READY;
import static com.app.delivxstore.utility.OrderStatusConstant.READY_FOR_PICKUP;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemHistoryBinding;
import com.app.delivxstore.main.history.HistoryFragment;
import com.app.delivxstore.main.history.model.HistoryData;
import com.app.delivxstore.utility.Utility;
import java.util.ArrayList;

/*binds recyclerview data of history to adapter*/
public class HistoryAdapter extends RecyclerView.Adapter implements
    Filterable {
  private final ArrayList<HistoryData> mHistoryDataList;
  private ArrayList<HistoryData> mFilteredList;
  private HistoryFragment mHistoryFragment;
  private Context mContext;

  public HistoryAdapter(HistoryFragment historyFragment, ArrayList<HistoryData> historyData) {
    mHistoryFragment = historyFragment;
    mHistoryDataList = historyData;
    mFilteredList = mHistoryDataList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    ItemHistoryBinding itemHistoryBinding =
        ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()));
    return new HistoryViewHolder(itemHistoryBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder instanceof HistoryViewHolder) {
      HistoryViewHolder holder = (HistoryViewHolder) viewHolder;
      HistoryData historyData = mFilteredList.get(position);
      holder.binding.tvOrderId.setText(String.format("%s: %s", mContext.getString(R.string.sOrder),
          historyData.getStoreOrderId()));
      holder.binding.tvOrderAmount.setText(String.format("%s %s",
          historyData.getAccounting().getCurrencySymbol(),
          Utility.roundOfDoubleValue(String.valueOf(historyData.getAccounting().getFinalTotal()))));
      holder.binding.tvCustomerName.setText(historyData.getCustomerDetails().getFirstName());
      Utility.printLog("store Order Id : " + historyData.getStoreOrderId());
      Utility.printLog("status: " + historyData.getStatus().getStatus());
      holder.binding.tvOrderStatus.setText(historyData.getStatus().getStatusText());
      holder.binding.tvOrderStatus.setTextColor(historyData.getStatus().getStatus() == CANCELLED
          ? mContext.getResources().getColor(R.color.cancelColor, null)
          : mContext.getResources().getColor(R.color.historyOrderStatus, null));
      holder.binding.tvNumberOfProduct.setText(String.format("%s %s",
          historyData.getProducts().size(), mContext.getString(R.string.products)));
      setTimeStamp(historyData.getStatus().getStatus(), historyData, holder.binding.tvDeliveryTime);
      Utility.printLog("time stamp created : " + historyData.getTimestamps().getCreated() + "\n"
          + historyData.getTimestamps().getCompleted());
      if (!historyData.getTimestamps().getCreated().equals("")
          || historyData.getTimestamps().getCreated() != null) {
        holder.binding.tvOrderTime.setText(String.format("%s %s",
            mContext.getString(R.string.placeOn),
            Utility.changeTimeFormat(Integer.parseInt(historyData.getTimestamps().getCreated()))));
      }
      holder.binding.clItem.setOnClickListener(view -> {
        mHistoryFragment.goToHistoryDetails(historyData);
      });
    }
  }

  /*set time stamp according to status*/
  private void setTimeStamp(int status, HistoryData historyData, AppCompatTextView tvDeliveryTime) {
    String timeStamp = null;
    switch (status) {
      case PACKED_AND_READY:
        timeStamp = historyData.getTimestamps().getPacked();
        break;
      case ACCEPTED:
        timeStamp = historyData.getTimestamps().getAccepted();
        break;
      case IN_DISPATCH:
        timeStamp = historyData.getTimestamps().getInDispatch();
        break;
      case READY_FOR_PICKUP:
        timeStamp = historyData.getTimestamps().getReadyForPickup();
        break;
      case COMPLETED:
        timeStamp = historyData.getTimestamps().getCompleted();
        break;
      case CANCELLED_AND_COMPLETED:
      case CANCELLED:
        timeStamp = historyData.getTimestamps().getCancelled();
        break;
      case NEW:
        timeStamp = historyData.getTimestamps().getCreated();
        break;
    }
    assert timeStamp != null;
    if (!timeStamp.equals("")) {
      tvDeliveryTime.setText(Utility.changeTimeFormat(Integer.parseInt
          (timeStamp)));
    }
  }

  @Override
  public int getItemCount() {
    return mFilteredList == null ? ZERO : mFilteredList.size();
  }

  @Override
  public Filter getFilter() {
    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
          mFilteredList = mHistoryDataList;
        } else {
          ArrayList<HistoryData> localFilterList = new ArrayList<>();
          for (HistoryData orderDetails : mHistoryDataList) {
            if (orderDetails.getCustomerDetails().getFirstName() != null
                && orderDetails.getCustomerDetails().getFirstName()
                .toLowerCase().contains(charString.toLowerCase())) {
              localFilterList.add(orderDetails);
            } else if (orderDetails.getStoreOrderId() != null
                && orderDetails.getStoreOrderId().contains(charString.toLowerCase())) {
              localFilterList.add(orderDetails);
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
        mFilteredList = (ArrayList<HistoryData>) filterResults.values;
        notifyDataSetChanged();
      }
    };
  }

  static class HistoryViewHolder extends RecyclerView.ViewHolder {
    ItemHistoryBinding binding;

    HistoryViewHolder(ItemHistoryBinding itemHistoryBinding) {
      super(itemHistoryBinding.getRoot());
      binding = itemHistoryBinding;
    }
  }
}
