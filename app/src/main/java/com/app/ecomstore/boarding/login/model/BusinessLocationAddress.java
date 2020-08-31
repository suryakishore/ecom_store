package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessLocationAddress implements Parcelable {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("googlePlaceId")
    @Expose
    private String googlePlaceId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("areaOrDistrict")
    @Expose
    private String areaOrDistrict;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("googlePlaceName")
    @Expose
    private String googlePlaceName;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("long")
    @Expose
    private String longi;

    protected BusinessLocationAddress(Parcel in) {
        country = in.readString();
        route = in.readString();
        address = in.readString();
        googlePlaceId = in.readString();
        city = in.readString();
        locality = in.readString();
        areaOrDistrict = in.readString();
        postCode = in.readString();
        googlePlaceName = in.readString();
        state = in.readString();
        lat = in.readString();
        longi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(route);
        dest.writeString(address);
        dest.writeString(googlePlaceId);
        dest.writeString(city);
        dest.writeString(locality);
        dest.writeString(areaOrDistrict);
        dest.writeString(postCode);
        dest.writeString(googlePlaceName);
        dest.writeString(state);
        dest.writeString(lat);
        dest.writeString(longi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BusinessLocationAddress> CREATOR =
        new Creator<BusinessLocationAddress>() {
            @Override
            public BusinessLocationAddress createFromParcel(Parcel in) {
                return new BusinessLocationAddress(in);
            }

            @Override
            public BusinessLocationAddress[] newArray(int size) {
                return new BusinessLocationAddress[size];
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

    public String getRoute ()
    {
        return route;
    }

    public void setRoute (String route)
    {
        this.route = route;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getGooglePlaceId ()
    {
        return googlePlaceId;
    }

    public void setGooglePlaceId (String googlePlaceId)
    {
        this.googlePlaceId = googlePlaceId;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getLocality ()
    {
        return locality;
    }

    public void setLocality (String locality)
    {
        this.locality = locality;
    }

    public String getAreaOrDistrict ()
    {
        return areaOrDistrict;
    }

    public void setAreaOrDistrict (String areaOrDistrict)
    {
        this.areaOrDistrict = areaOrDistrict;
    }

    public String getPostCode ()
    {
        return postCode;
    }

    public void setPostCode (String postCode)
    {
        this.postCode = postCode;
    }

    public String getGooglePlaceName ()
    {
        return googlePlaceName;
    }

    public void setGooglePlaceName (String googlePlaceName)
    {
        this.googlePlaceName = googlePlaceName;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getLong ()
    {
        return longi;
    }

    public void setLong (String longi)
    {
        this.longi = longi;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", route = "+route+", address = "+address+", googlePlaceId = "+googlePlaceId+", city = "+city+", locality = "+locality+", areaOrDistrict = "+areaOrDistrict+", postCode = "+postCode+", googlePlaceName = "+googlePlaceName+", state = "+state+", lat = "+lat+", long = "+longi+"]";
    }
}
