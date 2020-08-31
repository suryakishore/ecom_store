package com.app.delivxstore.main.home.tabView.orders.sendpackage;

import android.os.Bundle;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderFragment;

import java.util.ArrayList;

public interface SendPackageConrtact {

    interface SendPackageView extends BaseView
    {

        void setStoreName(String storeName);
        void setTitle(boolean tablet, String type);

        void setListData(ArrayList<OrderDetails> orders);

//        void setView();
    }

    interface SendPackagePresenter
    {

        void subscribeFilterData();
        void attachView(SendPackageFragment viewOperations);

//        void getOrders();

        void getView(Bundle arguments);

    }
}
