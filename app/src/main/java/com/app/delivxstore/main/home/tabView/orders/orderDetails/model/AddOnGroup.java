package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class AddOnGroup implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("name")
    @Expose
    private String name;

    protected AddOnGroup(Parcel in) {
        id = in.readString();
        price = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(price);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddOnGroup> CREATOR = new Creator<AddOnGroup>() {
        @Override
        public AddOnGroup createFromParcel(Parcel in) {
            return new AddOnGroup(in);
        }

        @Override
        public AddOnGroup[] newArray(int size) {
            return new AddOnGroup[size];
        }
    };

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

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


}
