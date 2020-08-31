package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class PaidBy implements Serializable {

    private String cash;

    private String card;

    private String wallet;

    public String getCash ()
    {
        return cash;
    }

    public void setCash (String cash)
    {
        this.cash = cash;
    }

    public String getCard ()
    {
        return card;
    }

    public void setCard (String card)
    {
        this.card = card;
    }

    public String getWallet ()
    {
        return wallet;
    }

    public void setWallet (String wallet)
    {
        this.wallet = wallet;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cash = "+cash+", card = "+card+", wallet = "+wallet+"]";
    }
}
