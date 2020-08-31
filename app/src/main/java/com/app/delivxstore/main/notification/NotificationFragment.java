package com.app.delivxstore.main.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.databinding.FragmentNotificationBinding;
import com.app.delivxstore.main.notification.model.NotificationData;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Inject;

/*show UI for notifications*/
public class NotificationFragment extends DaggerFragment
    implements NotificationContract.NotificationView {
  @Inject
  NotificationContract.Presenter presenter;
  private FragmentNotificationBinding mBinding;

  @Inject
  public NotificationFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = FragmentNotificationBinding.inflate(inflater);
    presenter.attachView(this);
    presenter.getNotifications();
    return mBinding.getRoot();
  }

  @Override
  public void showProgress() {
    mBinding.pbNotfications.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbNotfications.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow()
        .clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void showError(String errorMessage) {
    Utility.toastMessage(getActivity(), errorMessage);
  }

  @Override
  public void setData(ArrayList<NotificationData> data) {
    NotificationAdapter notificationAdapter = new NotificationAdapter(data);
    mBinding.rvNotifications.setHasFixedSize(true);
    mBinding.rvNotifications.setLayoutManager(new LinearLayoutManager(getActivity()));
    mBinding.rvNotifications.setAdapter(notificationAdapter);
  }
}
