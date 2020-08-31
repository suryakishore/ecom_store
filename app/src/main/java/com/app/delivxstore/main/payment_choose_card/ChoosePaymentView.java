package com.app.delivxstore.main.payment_choose_card;



import com.app.delivxstore.BaseView;

import java.util.ArrayList;


public interface ChoosePaymentView extends BaseView
{

    void onError(String msg);
    void setPaymentCardsList(ArrayList<CardData> cardsList);
    void showCashBtn();
    void hideCashBtn();
    void startAddCardAct();
    void showWalletPayment();
    void  payWallet(String payWallet);
    void hideWalletPay();
    void setWalletAmount(String amount);
    void addWallet();
    void stopAct();
    void placeOrder();
    void onSucess();

}
