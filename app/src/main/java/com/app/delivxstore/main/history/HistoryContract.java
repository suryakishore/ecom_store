package com.app.delivxstore.main.history;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.history.model.HistoryData;
import java.util.ArrayList;

/*defines two interface one for view
 * and another for Presenter*/
public interface HistoryContract {

  /*declares some methods to be implemented
   * in HistoryFragment*/
  interface HistoryView extends BaseView {
    /*shw error*/
    void showError(String message);

    /*set data to views*/
    void setData(ArrayList<HistoryData> data,int count);

    void setData(ArrayList<HistoryData> data);


  }

  /*declares some methods to be implemented
   * in HistoryPresenter*/
  interface Presenter {

    /*attach view to presenter*/
    void attachView(HistoryFragment historyFragment);

    /*calls api to get order history*/
    void getOrderHistory(int skip,int limit,String search,String orderTime);
    boolean isLoading();

  }
}


