package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Phone implements Parcelable {
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    protected Phone(Parcel in) {
        number = in.readString();
        countryCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(countryCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

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
