package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixedPricing implements Parcelable {
    @SerializedName("upto")
    @Expose
    private String upto;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("from")
    @Expose
    private String from;

    protected FixedPricing(Parcel in) {
        upto = in.readString();
        price = in.readString();
        from = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(upto);
        dest.writeString(price);
        dest.writeString(from);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FixedPricing> CREATOR = new Creator<FixedPricing>() {
        @Override
        public FixedPricing createFromParcel(Parcel in) {
            return new FixedPricing(in);
        }

        @Override
        public FixedPricing[] newArray(int size) {
            return new FixedPricing[size];
        }
    };

    public String getUpto ()
    {
        return upto;
    }

    public void setUpto (String upto)
    {
        this.upto = upto;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getFrom ()
    {
        return from;
    }

    public void setFrom (String from)
    {
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [upto = "+upto+", price = "+price+", from = "+from+"]";
    }
}
