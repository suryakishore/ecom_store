package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Quantity implements Parcelable {
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("value")
    @Expose
    private String value;

    protected Quantity(Parcel in) {
        unit = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(unit);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quantity> CREATOR = new Creator<Quantity>() {
        @Override
        public Quantity createFromParcel(Parcel in) {
            return new Quantity(in);
        }

        @Override
        public Quantity[] newArray(int size) {
            return new Quantity[size];
        }
    };

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Quantity [unit = "+unit+", value = "+value+"]";
    }
}
