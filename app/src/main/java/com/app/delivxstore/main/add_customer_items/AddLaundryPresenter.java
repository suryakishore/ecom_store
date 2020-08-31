package com.app.delivxstore.main.add_customer_items;


import com.app.delivxstore.BasePresenter;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface AddLaundryPresenter extends BasePresenter {
/*
    void getCategory(String id);
*/
    void submitLaundryItem(String productId, String productName, double quantity, double storeType);
/*
    void getLaundryCart();
*/
    void removeLaundryItem(String customerId,String cartId, String productId,String unitID,String addToCartOn,double orderType, int position);
/*
    void updateLaundryItem(int position, String cartId, String productId, String quantity);
*/
    void upDateCart(int pos,String customerID,String cartId, String childProductId, String unitID,int quantity,int unitPrice);
     void startLogin();
     void getCart(String  customerID);

     void checkOut(String customerId,int status,double type,double latitude,double longitude,double pickUpLat,double pickUpLong);

}
