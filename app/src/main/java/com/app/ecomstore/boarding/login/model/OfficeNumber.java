package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfficeNumber implements Parcelable {
    @SerializedName("Phone")
    @Expose
    private String Phone;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    protected OfficeNumber(Parcel in) {
        Phone = in.readString();
        countryCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Phone);
        dest.writeString(countryCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OfficeNumber> CREATOR = new Creator<OfficeNumber>() {
        @Override
        public OfficeNumber createFromParcel(Parcel in) {
            return new OfficeNumber(in);
        }

        @Override
        public OfficeNumber[] newArray(int size) {
            return new OfficeNumber[size];
        }
    };

    public String getPhone ()
    {
        return Phone;
    }

    public void setPhone (String Phone)
    {
        this.Phone = Phone;
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
        return "ClassPojo [Phone = "+Phone+", countryCode = "+countryCode+"]";
    }
}
