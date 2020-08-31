package com.app.delivxstore.main.cart;

import com.app.delivxstore.BaseView;

public interface CartContract {

    interface ViewOperations extends BaseView {
        void startPayment();


    }

    interface PresenterOperations {

        void startPayment();


    }

}
