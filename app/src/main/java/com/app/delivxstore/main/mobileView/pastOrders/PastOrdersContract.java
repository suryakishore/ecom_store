package com.app.delivxstore.main.mobileView.pastOrders;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface PastOrdersContract {


    interface PastOrdersView extends BaseView
    {
        /**
         * <h>Finish Activity</h>
         * <p>This method is using to close the activity after Successful state transition  </p>
         */
        void finishActivity();
        void setPastOrders(PastOrdersData pastOrders);
        void onError(String msg);

    }

    interface PastOrdersPresenter
    {

        void getUserHistory(String customerId);
        void closeAct();

    }
}
