package com.app.delivxstore.main.wallet.bankaccount;


import com.app.delivxstore.BaseView;

/*defines two interface one for view and other for
 * presenter*/
public interface BankAccountContract {
  /*declares some methods to be implemented
   * in BankAccountFragment for updating views*/
  interface BankAccountView extends BaseView {

    /*go back*/
    void goBack();

    /*show error*/
    void showError(String message);
  }

  /*declares some methods to be implemented in
   * BankAccount Presenter to execute
   * user request*/
  interface Presenter {
    /* attach view to Presenter*/
    void attachView(BankAccountFragment bankAccountFragment);

    /*validates all fields before save*/
    void validateAllFields(String bankCode, String accountNumber, String confirmAccountNo);
  }
}
