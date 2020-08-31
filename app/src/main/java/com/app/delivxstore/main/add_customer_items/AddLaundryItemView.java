package com.app.delivxstore.main.add_customer_items;


import com.app.delivxstore.BaseView;

import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public interface AddLaundryItemView extends BaseView {



    void onError(String msg);

    void setItemNameAndQuantity(String itemName, String productId);


    void onSuccessItemSubmit(String cartId,String msg);

    void removeLaundryItem(int position);

    void updateLaundryItem(int position, String productId,String unitId, String quantity);


    void onSuccessRemoveLaundryItem(int position,String msg);
    void onSuccessUpdateLaundryItem(int position, String quantity);

    void updateOrder(int pos,String msg,int quantity);

    void selectedCustomItem(String itemName);
    void startLoginAct();
    void showAlert(String title, String message);
    void startLogin();
    void stopAct();

    void setLaundryItems(ArrayList<AddLaundryCart> addLaundryData,String cartId);

    void getFare(int discount,int tax,int storeDeliveryFee,String currencySymbol);

}
