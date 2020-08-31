package com.app.delivxstore.main.payment_choose_card;


import com.app.delivxstore.BasePresenter;

public interface ChoosePaymentPresenter extends BasePresenter {
    void getCards(String customerId);
    void showCashBtn();
    void hideCashBtn();
    void addNewCard();
    String getLanguage();
    void showWalletPayment();
    void payWallet();
    void addMoneyWallet();
    void placeOrder(String customerID,double paymentType,String couponCode,double discount,String cartId,double latitude,double longitude,
                    double pickUpLat,double pickUpLong,String bookingDate,int serviceType,int bookingType,String extraNote,String dueDatetime,double estimatedPackageValue,String addressId,String address1,String address2);
}



