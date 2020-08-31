package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities  implements Parcelable {
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("cityId")
    @Expose
    private String cityId;

    protected Cities(Parcel in) {
        cityName = in.readString();
        cityId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeString(cityId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cities> CREATOR = new Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel in) {
            return new Cities(in);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };

    public String getCityName ()
    {
        return cityName;
    }

    public void setCityName (String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityId ()
    {
        return cityId;
    }

    public void setCityId (String cityId)
    {
        this.cityId = cityId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cityName = "+cityName+", cityId = "+cityId+"]";
    }
}
