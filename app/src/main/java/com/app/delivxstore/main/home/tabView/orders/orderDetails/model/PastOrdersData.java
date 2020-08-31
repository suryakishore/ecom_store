package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import com.app.delivxstore.main.home.model.OrderDetails;

import java.io.Serializable;
import java.util.ArrayList;

public class PastOrdersData implements Serializable {

    private ArrayList<OrderDetails> pastOrders;
    private ArrayList<OrderDetails> orders;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<OrderDetails> getPastOrders() {
        return orders;
    }

    public void setPastOrders(ArrayList<OrderDetails> orders) {
        this.orders = orders;
    }
}
