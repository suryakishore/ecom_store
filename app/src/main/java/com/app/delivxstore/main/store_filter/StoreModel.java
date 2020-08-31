package com.app.delivxstore.main.store_filter;

import java.util.ArrayList;

public class StoreModel {

    private ArrayList<StoreDetails> data;

    public ArrayList<StoreDetails> getData ()
    {
        return data;
    }

    public void setData (ArrayList<StoreDetails> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
