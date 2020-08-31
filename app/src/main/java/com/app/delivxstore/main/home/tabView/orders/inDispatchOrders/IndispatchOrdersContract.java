package com.app.delivxstore.main.home.tabView.orders.inDispatchOrders;

import android.os.Bundle;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.models.Data;
import java.util.ArrayList;

public interface IndispatchOrdersContract {

    interface ViewOperations extends BaseView {

        void setStoreName(String storeName);
        void setTitle(boolean tablet, String type);

        void setListData(ArrayList<Data> orders,int status, int penCount,boolean isCityLogin);
        void setListData(ArrayList<Data> orders,int status,boolean isCityLogin);

        void showMessage(String msg);

        void setView();
    }

    interface PresenterOperations {

        void subscribeFilterData();
        void attachView(IndispatchOrdersContract.ViewOperations viewOperations);
        void getReadyForPickUpApi(int skip,int limit);
//        void getOrders();

        void getView(Bundle arguments);
        boolean isLoading();

    }
}
