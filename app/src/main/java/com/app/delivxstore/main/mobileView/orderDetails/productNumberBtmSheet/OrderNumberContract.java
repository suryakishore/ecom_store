package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;

import com.app.delivxstore.BaseView;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface OrderNumberContract {
  interface OrderNumberView extends BaseView {
    /**
     * <h>Finish Activity</h>
     * <p>This method is using to close the activity after Successful state transition  </p>
     */
    void finishActivity();

    void onError(String msg);

    void setPackResult();
  }

  interface OrderNumberPresenter {
    void attachView(OrderNumberContract.OrderNumberView view);

    void applyPack(String orderId, int quantity);

    int getStoreType();

    void closeAct();
  }
}
