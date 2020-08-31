package com.app.delivxstore.main.addCustomer;

import com.app.delivxstore.BaseView;

public interface AddCustomerContract {
    interface ViewOperations extends BaseView {


        void showError(String msg);

        void getCustomerValues(String name,String phoneNumber,String emailAddress,String customerID);

    }

    interface PresenterOperations {

       void checkCustomer(String name,String phoneNumber,String emailAddress);

    }

}
