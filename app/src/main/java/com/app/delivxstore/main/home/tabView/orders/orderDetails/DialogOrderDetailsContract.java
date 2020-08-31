package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.os.Bundle;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.model.OrderDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.ExclusiveTaxes;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;

import java.util.ArrayList;

public interface DialogOrderDetailsContract {

    interface ViewOperation extends BaseView{


        void showMessage(String statusMsg) ;
        void setViews(OrderedItemDetails orderDetails);

        void finishActivity();

        void showCancelOrDelayDailog(boolean delay);

        void setReasons(ArrayList<Reasons> reasons);

        void moveToListAct(String orderId);

        void showOrders(PastOrdersData pastOrdersData);

        void addItems(ArrayList<Items> items, String currency, boolean enabled);

        void setFares(String subTotal, String discount, String delCharge, String serviceCharge, String total,ArrayList<ExclusiveTaxes> exclusiveTaxes);
        void openOrderEditDialog(Items items);


    }
    interface PresenterOperations{

         String getTimestamp();
        String getDueTime();
        String getCurrencySymbol();
        void getBundleData(Bundle extras);

        void getOrderDetails();

        void updateOrder(int status);

        void dispatch();

        void cancelOrDelayOrder(boolean delay);

        void getCancellationReason();

        void cancelOrder(Reasons reasons);

        void delayOrder(Reasons reason, String selectedDelay);

        void manualDispatch();

        void callDriver();

        void callCustomer();

        void getPastOrders();

        void getDriverOngoingOrders();

        void getCurrentOrderDetails();

        void editItems();

        void updateItem(int quantity, float unitPrice, String unitId);

        void getFares(float subTotal);

        void saveItems(ArrayList<Items> itemsArrayList);
        void editOrder(Items shipmentDetails);


    }
}
