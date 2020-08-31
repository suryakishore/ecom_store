package com.app.delivxstore.main.wallet;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.wallet.response.TransactionItem;
import com.app.delivxstore.main.wallet.response.WalletDataItem;
import java.util.ArrayList;

/*defines two interface for view and presenter*/
public interface WalletContract {

  /*declares some methods to be implemented
   * in Fragment for updating view*/
  interface WalletView extends BaseView {

    /*show no transactions view*/
    void showNoTransactions();

    /*show toast message for error*/
    void error(String string);

    /*set values for views*/
    void setValues(WalletDataItem data);

    /*send data to adapter according to transaction type*/
    void updateAdapter(ArrayList<TransactionItem> transactionItems, String transactionHeading,
                       int txnType);
  }

  /*declares some methods to be implemented
   * in presenter to implement logic*/
  interface Presenter {

    /*attach the view to presenter*/
    void attachView(WalletFragment walletFragment);

    /*calls api for wallet details*/
    void getWalletDetails();

    /*calls api for wallet transactions*/
    void getWalletTransactions(int txnType);

    /*set up adapter for all type transactions*/
    void setupAllTransAdapter();

    /*set up adapter for debit type transactions*/
    void setupDebitTransAdapter();

    /*set up adapter for credit type transactions*/
    void setupCreditTransAdapter();
  }
}
