package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerAverageRating implements Parcelable {
    @SerializedName("attributeId")
    @Expose
    private String attributeId;
    @SerializedName("avgValue")
    @Expose
    private String avgValue;

    protected SellerAverageRating(Parcel in) {
        attributeId = in.readString();
        avgValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(attributeId);
        dest.writeString(avgValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SellerAverageRating> CREATOR = new Creator<SellerAverageRating>() {
        @Override
        public SellerAverageRating createFromParcel(Parcel in) {
            return new SellerAverageRating(in);
        }

        @Override
        public SellerAverageRating[] newArray(int size) {
            return new SellerAverageRating[size];
        }
    };

    public String getAttributeId ()
    {
        return attributeId;
    }

    public void setAttributeId (String attributeId)
    {
        this.attributeId = attributeId;
    }

    public String getAvgValue ()
    {
        return avgValue;
    }

    public void setAvgValue (String avgValue)
    {
        this.avgValue = avgValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [attributeId = "+attributeId+", avgValue = "+avgValue+"]";
    }
}
