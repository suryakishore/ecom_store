package com.app.delivxstore.main.createOrder;

import com.app.delivxstore.BaseView;

public interface CreateOrderContract {

    interface ViewOperations extends BaseView {

        void sendPackage();
        void sendInventory();
        void pickUp();

        void delivery();
        void showError(String msg);
        void orderFrom();

    }

    interface PresenterOperations {

        void sendPackage();
        void sendInventory();
        void pickUp();

        void delivery();
       void orderFrom();

    }

}
