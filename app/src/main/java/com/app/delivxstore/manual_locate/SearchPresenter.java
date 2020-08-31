package com.app.delivxstore.manual_locate;


import com.app.delivxstore.BasePresenter;

import io.reactivex.Observable;

/**
 * Created by dell on 16-Nov-17.
 */

 interface SearchPresenter extends BasePresenter {

    void getAddressDetail(String addressId, String fullAddress);
    void setAddress(String address, double lat, double longi);
    void setComingFrom(String comingFrom);
    void setObserver(Observable<String> queryObservable);
    void clearSearch();
    void getCurrentAddress();

    String getLanguage();
}
