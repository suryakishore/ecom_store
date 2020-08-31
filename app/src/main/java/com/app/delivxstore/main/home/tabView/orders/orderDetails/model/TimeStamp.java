package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class TimeStamp implements Serializable {
    private Created created;

    public Created getCreated ()
    {
        return created;
    }

    public void setCreated (Created created)
    {
        this.created = created;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [created = "+created+"]";
    }
}
