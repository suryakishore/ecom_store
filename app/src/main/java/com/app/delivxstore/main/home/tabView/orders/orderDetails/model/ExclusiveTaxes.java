package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class ExclusiveTaxes implements Serializable {

    private String price;

    private String taxId;

    private String taxValue;

    private String taxFlagMsg;

    private String taxtName;

    private String taxCode;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getTaxId ()
    {
        return taxId;
    }

    public void setTaxId (String taxId)
    {
        this.taxId = taxId;
    }

    public String getTaxValue ()
    {
        return taxValue;
    }

    public void setTaxValue (String taxValue)
    {
        this.taxValue = taxValue;
    }

    public String getTaxFlagMsg ()
    {
        return taxFlagMsg;
    }

    public void setTaxFlagMsg (String taxFlagMsg)
    {
        this.taxFlagMsg = taxFlagMsg;
    }

    public String getTaxtName ()
    {
        return taxtName;
    }

    public void setTaxtName (String taxtName)
    {
        this.taxtName = taxtName;
    }

    public String getTaxCode ()
    {
        return taxCode;
    }

    public void setTaxCode (String taxCode)
    {
        this.taxCode = taxCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", taxId = "+taxId+", taxValue = "+taxValue+", taxFlagMsg = "+taxFlagMsg+", taxtName = "+taxtName+", taxCode = "+taxCode+"]";
    }
}
