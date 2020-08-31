package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models;

import java.util.ArrayList;

public class ProductSubstituteMain {

    private ArrayList<ProductSubstituteData> data;

    private String message;

    public ArrayList<ProductSubstituteData> getData ()
    {
        return data;
    }

    public void setData (ArrayList<ProductSubstituteData> data)
    {
        this.data = data;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", message = "+message+"]";
    }

}
