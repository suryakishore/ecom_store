package com.app.delivxstore.main.add_address;


import com.app.delivxstore.BasePresenter;
import com.app.delivxstore.main.manage_address.DataInGetAddress;

import io.reactivex.Observable;

/**
 * Created by dell on 01-Feb-18.
 */

public interface AddressActPresenter extends BasePresenter {

    void setLatlong(double lat, double longi);
    void setSavedAs(int type, String savedAs);
    void addAddress(String add1, String add2, String houseNo, String landMark, double lat,
                    double longi, String savedAs, int savedType, String otherValue,String customerId);


    void getAddress(double lat, double longi);

    void mapReady();
    void getCurrentAddress();
    void setObserver(Observable<String> queryObservable);
    void removeObserver(Observable<String> queryObservable);
    void setIntentData(String action, DataInGetAddress address);
    /**
     * <h>on Address Click</h>
     * <p>This method is used to set the required address.</p>
     */

    void onAddressClick();
}
