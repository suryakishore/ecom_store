package com.app.delivxstore.main.wallet.accountdetails;

import android.os.Bundle;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.wallet.stripe.model.response.AccountData;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;

/*defines two interface one for
 * BankDetails Fragment and other one
 * for BankDetailsPresenter*/
public interface AccountListContract {
  /*declares some methods to be implemented
   * in BankDetailsFragment for updating view*/
  interface BankDetailsView extends BaseView {
    /*go to previous screen*/
    void goBack();

    /*initialize recycler view bank accounts*/
    void initializeRecyclerView();

    /*handles ui*/
    void setViews(StripeData stripeData);

    /*get details of selected account*/
    void getSelectedAccountDetails(AccountData accountData);

    /*show error*/
    void showError(String message);
  }

  /*declares some methods to be implemented
   * in BankDetailsPresenter for executing
   * user request*/
  interface Presenter {
    /* attach view to Presenter*/
    void attachView(AccountListFragment bankAccountFragment);

    /*calls api for get strip account*/
    void getAccounts();

    /*calls api for delete bank account*/
    void deleteAccount(String accountId);

    /*get bundle data*/
    void getBundleData(Bundle arguments);
  }
}
