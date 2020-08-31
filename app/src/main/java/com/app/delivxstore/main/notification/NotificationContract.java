package com.app.delivxstore.main.notification;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.notification.model.NotificationData;
import java.util.ArrayList;

/*defines two interface one for NotificationFragment
 * and Other for NotificationPresenter*/
public interface NotificationContract {

  /*declares some methods to be
   * implemented in NotificationFragment*/
  interface NotificationView extends BaseView {

    /*show error*/
    void showError(String errorMessage);

    /*set data to recyclerview*/
    void setData(ArrayList<NotificationData> data);
  }

  /*declares some methods to be
   * implemented in NotificationPresenter*/
  interface Presenter {

    /*attach view to presenter*/
    void attachView(NotificationFragment notificationFragment);

    /*calls api for notifications*/
    void getNotifications();
  }
}
