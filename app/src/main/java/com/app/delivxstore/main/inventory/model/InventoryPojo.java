package com.app.delivxstore.main.inventory.model;

public class InventoryPojo {
    private InventoryData data;

    private String message;

    public InventoryData getData ()
    {
        return data;
    }

    public void setData (InventoryData data)
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
