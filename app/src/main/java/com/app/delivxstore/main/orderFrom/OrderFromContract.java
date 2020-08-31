package com.app.delivxstore.main.orderFrom;

import com.app.delivxstore.BaseView;

public interface OrderFromContract {
    interface ViewOperations extends BaseView {


        void showError(String msg);
        void searchCustomer();
        void manageAddress();
        void DeliveryAddress();
        void schedule();
        void now();
        void setTimeIn12Hour(String time);
        void setDate(String date);
        void selectTime();
        void selectDate();
        void createOrder();


    }

    interface PresenterOperations {

        void searchCustomer();
        void manageAddress();
        void DeliveryAddress();
        void setTimeIn12Hour(int hour,int minute);
        void setDate(int currentYear, int month, int day);
        void schedule();
        void now();
        void selectSlot();
        void selectDate();
        void createOrder();
        void showError(String msg);
    }

}
