package com.app.delivxstore.main.add_customer_items;

public class AddLaundryResponse {
    private AddLaundryData data;

    private String message;

    public AddLaundryData getData ()
    {
        return data;
    }

    public void setData (AddLaundryData data)
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
