package com.app.delivxstore.main.home.tabView.orders.newpending;

import android.os.Bundle;
import com.app.delivxstore.BaseView;

public interface newpendingConrtact {
  interface PreOrderView extends BaseView {
    void setStoreName(String storeName);

    void setTitle(boolean tablet, String type);

    void showMessage(String msg);
//        void setView();
  }

  interface PreOrderPresenter {
    void subscribeFilterData();

    void attachView(NewPendingOrderFragment viewOperations);

    void getView(Bundle arguments);

    boolean isLoading();
  }
}
