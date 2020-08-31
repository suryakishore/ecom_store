package com.app.delivxstore.main.home.tabView.orders.inAssign;

import android.os.Bundle;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.models.Data;
import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface InAssignContract {
  interface ViewOperations extends BaseView {
    void setStoreName(String storeName);

    void setTitle(boolean tablet, String type);

    void setListData(ArrayList<Data> orders, int status, int penCount,boolean isCityLogin);
    void setListData(ArrayList<Data> orders, int status,boolean isCityLogin);

    void setView();

    void showMessage(String msg);
  }

  interface PresenterOperations {
    void subscribeFilterData();

    void attachView(InAssignContract.ViewOperations viewOperations);

    void getPackedOrderApi(int skip, int limit);
//        void getOrders();

    void getView(Bundle arguments);

    boolean isLoading();
  }
}
