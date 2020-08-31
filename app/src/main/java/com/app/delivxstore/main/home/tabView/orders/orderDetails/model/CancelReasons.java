package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CancelReasons implements Serializable {

    private ArrayList<Reasons> reasons;

    public ArrayList<Reasons> getReasons() {
        return reasons;
    }

    public void setReasons(ArrayList<Reasons> reasons) {
        this.reasons = reasons;
    }
}
