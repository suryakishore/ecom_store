package com.app.delivxstore.main.notification;

import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ItemNotificationBinding;
import com.app.delivxstore.main.notification.model.NotificationData;
import java.util.ArrayList;

/*binds notification data to recyclerview adapter*/
public class NotificationAdapter extends RecyclerView.Adapter {
  private ArrayList<NotificationData> mDataArrayList;

  NotificationAdapter(ArrayList<NotificationData> dataArrayList) {
    mDataArrayList = dataArrayList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemNotificationBinding itemOrderBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()),
        R.layout.item_notification, parent, false);
    return new NotificationViewHolder(itemOrderBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof NotificationViewHolder) {
      NotificationViewHolder viewHolder = (NotificationViewHolder) holder;
      viewHolder.binding.tvNotification.setText(mDataArrayList.get(position).getBody());
      viewHolder.binding.tvNotificationTime.setText(mDataArrayList.get(position).getDay());
    }
  }

  @Override
  public int getItemCount() {
    return mDataArrayList == null ? ZERO : mDataArrayList.size();
  }

  static class NotificationViewHolder extends RecyclerView.ViewHolder {
    ItemNotificationBinding binding;

    NotificationViewHolder(ItemNotificationBinding itemNotificationBinding) {
      super(itemNotificationBinding.getRoot());
      binding = itemNotificationBinding;
    }
  }
}
