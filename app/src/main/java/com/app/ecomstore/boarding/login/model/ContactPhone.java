package com.app.ecomstore.boarding.login.model;

import java.io.Serializable;

public class ContactPhone implements Serializable {
    private String number;

    private String countryCode;

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [number = "+number+", countryCode = "+countryCode+"]";
    }
}
