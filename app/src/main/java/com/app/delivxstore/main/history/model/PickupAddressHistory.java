package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PickupAddressHistory implements Parcelable {

  @SerializedName("country")
  @Expose
  private String country;

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

  @SerializedName("googlePlaceName")
  @Expose
  private String googlePlaceName;

  @SerializedName("cityId")
  @Expose
  private String cityId;

  @SerializedName("long")
  @Expose
  private String jsonMemberLong;

  @SerializedName("route")
  @Expose
  private String route;

  @SerializedName("cityName")
  @Expose
  private String cityName;

  @SerializedName("addressLine1")
  @Expose
  private String addressLine1;

  @SerializedName("postCode")
  @Expose
  private String postCode;

  @SerializedName("addressLine2")
  @Expose
  private String addressLine2;

  @SerializedName("state")
  @Expose
  private String state;

  @SerializedName("addressArea")
  @Expose
  private String addressArea;

  @SerializedName("lat")
  @Expose
  private String lat;

  protected PickupAddressHistory(Parcel in) {
    country = in.readString();
    address = in.readString();
    googlePlaceId = in.readString();
    city = in.readString();
    locality = in.readString();
    areaOrDistrict = in.readString();
    googlePlaceName = in.readString();
    cityId = in.readString();
    jsonMemberLong = in.readString();
    route = in.readString();
    cityName = in.readString();
    addressLine1 = in.readString();
    postCode = in.readString();
    addressLine2 = in.readString();
    state = in.readString();
    addressArea = in.readString();
    lat = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(country);
    dest.writeString(address);
    dest.writeString(googlePlaceId);
    dest.writeString(city);
    dest.writeString(locality);
    dest.writeString(areaOrDistrict);
    dest.writeString(googlePlaceName);
    dest.writeString(cityId);
    dest.writeString(jsonMemberLong);
    dest.writeString(route);
    dest.writeString(cityName);
    dest.writeString(addressLine1);
    dest.writeString(postCode);
    dest.writeString(addressLine2);
    dest.writeString(state);
    dest.writeString(addressArea);
    dest.writeString(lat);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<PickupAddressHistory> CREATOR = new Creator<PickupAddressHistory>() {
    @Override
    public PickupAddressHistory createFromParcel(Parcel in) {
      return new PickupAddressHistory(in);
    }

    @Override
    public PickupAddressHistory[] newArray(int size) {
      return new PickupAddressHistory[size];
    }
  };

  public String getCountry() {
    return country;
  }

  public String getAddress() {
    return address;
  }

  public String getGooglePlaceId() {
    return googlePlaceId;
  }

  public String getCity() {
    return city;
  }

  public String getLocality() {
    return locality;
  }

  public String getAreaOrDistrict() {
    return areaOrDistrict;
  }

  public String getGooglePlaceName() {
    return googlePlaceName;
  }

  public String getCityId() {
    return cityId;
  }

	public String getJsonMemberLong(){
		return jsonMemberLong;
	}

  public String getRoute() {
    return route;
  }

  public String getCityName() {
    return cityName;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public String getPostCode() {
    return postCode;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public String getState() {
    return state;
  }

  public String getAddressArea() {
    return addressArea;
  }

  public String getLat() {
    return lat;
  }
}