package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Parcelable {
    @SerializedName("storeOrderId")
    @Expose
    private String storeOrderId;
    @SerializedName("productOrderId")
    @Expose
    private ArrayList<String> productOrderId;

    protected Orders(Parcel in) {
        storeOrderId = in.readString();
        productOrderId = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeOrderId);
        dest.writeStringList(productOrderId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getStoreOrderId ()
    {
        return storeOrderId;
    }

    public void setStoreOrderId (String storeOrderId)
    {
        this.storeOrderId = storeOrderId;
    }

    public ArrayList<String> getProductOrderId ()
    {
        return productOrderId;
    }

    public void setProductOrderId (ArrayList<String> productOrderId)
    {
        this.productOrderId = productOrderId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [storeOrderId = "+storeOrderId+", productOrderId = "+productOrderId+"]";
    }
}
