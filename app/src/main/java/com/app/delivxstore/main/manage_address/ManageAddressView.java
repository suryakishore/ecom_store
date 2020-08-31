package com.app.delivxstore.main.manage_address;


import com.app.delivxstore.BaseView;

import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */

public interface ManageAddressView extends BaseView {

    void setAddressList(ArrayList<DataInGetAddress> manageAddressList);
    void noAddress();
    void onError(String msg);
    void removeAddressRow(int pos);
}
