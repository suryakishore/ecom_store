package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PickupAddress implements Parcelable {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("cityId")
    @Expose
    private String cityId;

    /*@SerializedName("long")
    private String longs;*/
    @SerializedName("sublocality_level_2")
    @Expose
    private String sublocality_level_2;
    @SerializedName("sublocality_level_1")
    @Expose
    private String sublocality_level_1;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("administrative_area_level_2")
    @Expose
    private String administrative_area_level_2;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("administrative_area_level_1")
    @Expose
    private String administrative_area_level_1;
    @SerializedName("postal_code")
    @Expose
    private String postal_code;

    /*private String lat;*/

    protected PickupAddress(Parcel in) {
        country = in.readString();
        address = in.readString();
        locality = in.readString();
        cityId = in.readString();
        sublocality_level_2 = in.readString();
        sublocality_level_1 = in.readString();
        route = in.readString();
        administrative_area_level_2 = in.readString();
        cityName = in.readString();
        administrative_area_level_1 = in.readString();
        postal_code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(address);
        dest.writeString(locality);
        dest.writeString(cityId);
        dest.writeString(sublocality_level_2);
        dest.writeString(sublocality_level_1);
        dest.writeString(route);
        dest.writeString(administrative_area_level_2);
        dest.writeString(cityName);
        dest.writeString(administrative_area_level_1);
        dest.writeString(postal_code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PickupAddress> CREATOR = new Creator<PickupAddress>() {
        @Override
        public PickupAddress createFromParcel(Parcel in) {
            return new PickupAddress(in);
        }

        @Override
        public PickupAddress[] newArray(int size) {
            return new PickupAddress[size];
        }
    };

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getLocality ()
    {
        return locality;
    }

    public void setLocality (String locality)
    {
        this.locality = locality;
    }

    public String getCityId ()
    {
        return cityId;
    }

    public void setCityId (String cityId)
    {
        this.cityId = cityId;
    }

    /*public String getLong ()
    {
        return longs;
    }

    public void setLong (String longs)
    {
        this.longs = longs;
    }*/

    public String getSublocality_level_2 ()
    {
        return sublocality_level_2;
    }

    public void setSublocality_level_2 (String sublocality_level_2)
    {
        this.sublocality_level_2 = sublocality_level_2;
    }

    public String getSublocality_level_1 ()
    {
        return sublocality_level_1;
    }

    public void setSublocality_level_1 (String sublocality_level_1)
    {
        this.sublocality_level_1 = sublocality_level_1;
    }

    public String getRoute ()
    {
        return route;
    }

    public void setRoute (String route)
    {
        this.route = route;
    }

    public String getAdministrative_area_level_2 ()
    {
        return administrative_area_level_2;
    }

    public void setAdministrative_area_level_2 (String administrative_area_level_2)
    {
        this.administrative_area_level_2 = administrative_area_level_2;
    }

    public String getCityName ()
    {
        return cityName;
    }

    public void setCityName (String cityName)
    {
        this.cityName = cityName;
    }

    public String getAdministrative_area_level_1 ()
    {
        return administrative_area_level_1;
    }

    public void setAdministrative_area_level_1 (String administrative_area_level_1)
    {
        this.administrative_area_level_1 = administrative_area_level_1;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    /*public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }*/

    @Override
    public String toString()
    {
        return "";
        /*return "ClassPojo [country = "+country+", address = "+address+", locality = "+locality+", cityId = "+cityId+", long = "+longs+", sublocality_level_2 = "+sublocality_level_2+", sublocality_level_1 = "+sublocality_level_1+", route = "+route+", administrative_area_level_2 = "+administrative_area_level_2+", cityName = "+cityName+", administrative_area_level_1 = "+administrative_area_level_1+", postal_code = "+postal_code+", lat = "+lat+"]";*/
    }
}
