package com.app.delivxstore.main;

import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;
import com.app.delivxstore.main.store_filter.StoreDetails;

import java.util.ArrayList;

public interface MainActivityContract {

    interface ViewOperations
    {
        void setDriverList(ArrayList<DriverData> driverList);
        void setArryList(ArrayList<StoreDetails> storeDetails);
        void setView(boolean tablet, boolean isCityLogin);
        void setProfileDetails(String storeName,String name,String role);
        void setVersionText(String version);
        void startLogin();
    }

    interface PresenterOperations
    {

        void publishStoreName(StoreDetails storeDetails);
        String getStoreType();
        String getFranchiseName();
        String getLanguage();
        int getAutoDispatch();
        int getForceAccept();
        int getDriverType();
        void getStoreList();
        void getDriverList();
        void logout();
        void logoutOnClick();
        void checkScreenSize();
        void onDrawerOpen();
        void getVersion();
        String getCityName();

        /*extracts language code from shared preferences*/
      String getLanguageCode();
    }


}
