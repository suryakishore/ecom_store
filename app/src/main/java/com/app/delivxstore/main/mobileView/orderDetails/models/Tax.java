package com.app.delivxstore.main.mobileView.orderDetails.models;

import java.io.Serializable;

public class Tax implements Serializable {
    private String taxValue;

    private String taxId;

    private String taxName;
    private String totalValue;

    public String getTaxValue ()
    {
        return taxValue;
    }

    public void setTaxValue (String taxValue)
    {
        this.taxValue = taxValue;
    }

    public String getTaxId ()
    {
        return taxId;
    }

    public void setTaxId (String taxId)
    {
        this.taxId = taxId;
    }

    public String getTaxName ()
    {
        return taxName;
    }

    public void setTaxName (String taxName)
    {
        this.taxName = taxName;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [taxValue = "+taxValue+", taxId = "+taxId+", taxName = "+taxName+"]";
    }
}
