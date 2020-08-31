package com.app.delivxstore.main.manage_address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dell on 31-Jan-18.
 */

public class GetAddressResponse implements Serializable{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<DataInGetAddress> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DataInGetAddress> getData() {
        return data;
    }

    public void setData(ArrayList<DataInGetAddress> data) {
        this.data = data;
    }
}
