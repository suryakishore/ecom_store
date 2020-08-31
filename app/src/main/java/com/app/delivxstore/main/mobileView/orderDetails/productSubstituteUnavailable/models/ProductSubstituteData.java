package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models;

import java.util.ArrayList;

public class ProductSubstituteData {

    private String price;

    private String name;

    private ArrayList<ProductAttributes> attributes;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public ArrayList<ProductAttributes> getAttributes ()
    {
        return attributes;
    }

    public void setAttributes (ArrayList<ProductAttributes> attributes)
    {
        this.attributes = attributes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", name = "+name+", attributes = "+attributes+"]";
    }

}
