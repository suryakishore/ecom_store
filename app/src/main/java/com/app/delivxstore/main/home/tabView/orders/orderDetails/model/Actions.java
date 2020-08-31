package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class Actions implements Serializable {
    private String customerId;

    private String timeStamp;

    private String isoDate;

    private String transactionStatusMsg;

    public String getCustomerId ()
    {
        return customerId;
    }

    public void setCustomerId (String customerId)
    {
        this.customerId = customerId;
    }

    public String getTimeStamp ()
    {
        return timeStamp;
    }

    public void setTimeStamp (String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getIsoDate ()
    {
        return isoDate;
    }

    public void setIsoDate (String isoDate)
    {
        this.isoDate = isoDate;
    }

    public String getTransactionStatusMsg ()
    {
        return transactionStatusMsg;
    }

    public void setTransactionStatusMsg (String transactionStatusMsg)
    {
        this.transactionStatusMsg = transactionStatusMsg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [customerId = "+customerId+", timeStamp = "+timeStamp+", isoDate = "+isoDate+", transactionStatusMsg = "+transactionStatusMsg+"]";
    }
}
