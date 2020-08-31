package com.app.delivxstore.main.history.historydetails;

import android.content.Intent;
import android.os.Bundle;
import com.app.delivxstore.main.history.model.HistoryData;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;

public interface HistoryDetailsContract {
  /*declares some methods to implemented in
   * HistoryDetailsActivity*/
  interface HistoryDetailsView {


    /**
     * <h>hideProgress</h>
     * <p>hide progress bar</p>
     */
    void hideProgress();

    /**
     * <h>showProgress</h>
     * <p>show progress bar</p>
     */
    void showProgress();
    void showError(String message, int code);
    void setViews(OrderDetailsModel orderedItemDetails, boolean isCityLogin);


  }

  /*declares some methods to be implemented in
   * HistoryDetailsPresenter*/
  interface Presenter {

    /*get data through intent*/
    void getBundleData(Intent intent);

     void callHistoryDetail(Bundle extra);
     int getStoreType();
  }
}
