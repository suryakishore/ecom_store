package com.app.delivxstore.main.searchCustomer;

import com.app.delivxstore.BaseView;

import java.util.ArrayList;

public interface SearchCustomerContract {
    interface ViewOperations extends BaseView {


        void showError(String msg);

        void searchCustomer();

        void setCustomers(ArrayList<SearchCustomerData> searchCustomerData);


        void showCustomerDet(String hint);

    }

    interface PresenterOperations {

        void searchCustomer();

        void getCustomers(String hint);


    }
}
