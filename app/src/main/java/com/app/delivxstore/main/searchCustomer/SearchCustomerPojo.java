package com.app.delivxstore.main.searchCustomer;

import java.util.ArrayList;

public class SearchCustomerPojo {
    private ArrayList<SearchCustomerData> data;

    private String message;

    public ArrayList<SearchCustomerData> getData ()
    {
        return data;
    }

    public void setData (ArrayList<SearchCustomerData> data)
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

}
