package com.app.delivxstore.main.wallet.payment;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;

/*defines two interface for view and presenter*/
public interface PaymentContract {
  /*declares some methods to be implemented in
   * Activity for updating views*/
  interface PaymentView extends BaseView {

    /*show error message*/
    void onError(String msg);

    /*show requested ui*/
    void setUi(StripeData data);

    /*hide button*/
    void hideRechargeButton();
  }

  /*declares some methods to be implemented in
   * Presenter for executing user request */
  interface Presenter {
    /*get added account*/
    void getAccounts();

    /*attach fragment view to presenter*/
    void attachView(PaymentActivity paymentFragment);
  }
}
