package com.app.ecomstore.boarding.login.model;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CitiesOfOperation implements Parcelable {
    @SerializedName("country")
    @Expose
    private ArrayList<Country> country;
    @SerializedName("city")
    @Expose
    private ArrayList<City> city;

    protected CitiesOfOperation(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CitiesOfOperation> CREATOR = new Creator<CitiesOfOperation>() {
        @Override
        public CitiesOfOperation createFromParcel(Parcel in) {
            return new CitiesOfOperation(in);
        }

        @Override
        public CitiesOfOperation[] newArray(int size) {
            return new CitiesOfOperation[size];
        }
    };

    public ArrayList<Country> getCountry ()
    {
        return country;
    }

    public void setCountry (ArrayList<Country> country)
    {
        this.country = country;
    }

    public ArrayList<City> getCity ()
    {
        return city;
    }

    public void setCity (ArrayList<City> city)
    {
        this.city = city;
    }


}
