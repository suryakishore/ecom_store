package com.app.delivxstore.main.home.tabView.orders.preOrder;

import android.os.Bundle;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.models.Data;
import java.util.ArrayList;

public interface PreOrderConrtact {

    interface PreOrderView extends BaseView
    {

        void setStoreName(String storeName);
        void setTitle(boolean tablet, String type);

        void showMessage(String msg);

//        void setView();
    }

    interface PreOrderPresenter
    {

        void subscribeFilterData();
        void attachView(PreOrderFragment viewOperations);

        void getView(Bundle arguments);
        boolean isLoading();

    }
}
