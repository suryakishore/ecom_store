package com.app.delivxstore.main.wallet.withdraw;

import android.os.Bundle;
import com.app.delivxstore.main.wallet.stripe.model.response.AccountData;

/*defines two interface for RechargeFragment and RechargePresenter*/
public interface WithDrawContract {

  /*declares some methods for updating views*/
  interface WithDrawView {

    /*set values for withdraw view*/
    void setWithDrawUi(AccountData accountData, String currency, String cause);

    /*show progress bar*/
    void showProgress();

    /*hide progress bar*/
    void hideProgress();

    /*show error*/
    void showError(String message);

    /*show success view*/
    void showSuccess(String amount, String message);
  }

  /*declares some methods for executing user requests*/
  interface Presenter {

    /* attach view to Presenter*/
    void attachView(WithDrawFragment rechargeFragment);

    /*call withdraw api*/
    void withDrawApi(String bankId, String amount, String pgName);

    /*get bundle data from activity*/
    void getBundleData(Bundle arguments);

  }
}
