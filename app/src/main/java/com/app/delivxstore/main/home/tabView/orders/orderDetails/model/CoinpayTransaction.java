package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CoinpayTransaction implements Serializable {

    private String transactionQrUrl;

    private String transactionId;

    private String transactionStatusUrl;

    private String transactionStatusMsg;

    private ArrayList<Actions> actions;

    private String transactionAddress;

    public String getTransactionQrUrl ()
    {
        return transactionQrUrl;
    }

    public void setTransactionQrUrl (String transactionQrUrl)
    {
        this.transactionQrUrl = transactionQrUrl;
    }

    public String getTransactionId ()
    {
        return transactionId;
    }

    public void setTransactionId (String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getTransactionStatusUrl ()
    {
        return transactionStatusUrl;
    }

    public void setTransactionStatusUrl (String transactionStatusUrl)
    {
        this.transactionStatusUrl = transactionStatusUrl;
    }

    public String getTransactionStatusMsg ()
    {
        return transactionStatusMsg;
    }

    public void setTransactionStatusMsg (String transactionStatusMsg)
    {
        this.transactionStatusMsg = transactionStatusMsg;
    }

    public ArrayList<Actions> getActions ()
    {
        return actions;
    }

    public void setActions (ArrayList<Actions> actions)
    {
        this.actions = actions;
    }

    public String getTransactionAddress ()
    {
        return transactionAddress;
    }

    public void setTransactionAddress (String transactionAddress)
    {
        this.transactionAddress = transactionAddress;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [transactionQrUrl = "+transactionQrUrl+", transactionId = "+transactionId+", transactionStatusUrl = "+transactionStatusUrl+", transactionStatusMsg = "+transactionStatusMsg+", actions = "+actions+", transactionAddress = "+transactionAddress+"]";
    }
}
