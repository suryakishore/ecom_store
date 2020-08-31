package com.app.delivxstore.main.manage_address;


import com.app.delivxstore.BasePresenter;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

public interface ManageAddressPresenter extends BasePresenter {

    void getAddressList(String userID);
    void deleteAddress(String id, int pos, double lat, double longi);


}



