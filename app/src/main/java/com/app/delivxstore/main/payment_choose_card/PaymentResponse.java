package com.app.delivxstore.main.payment_choose_card;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell on 06-Mar-18.
 */

public class PaymentResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<CardData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CardData> getData() {
        return data;
    }

    public void setData(ArrayList<CardData> data) {
        this.data = data;
    }
}
