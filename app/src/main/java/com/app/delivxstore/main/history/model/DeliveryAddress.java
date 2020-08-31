package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DeliveryAddress implements Parcelable {

  @SerializedName("country")
  @Expose
  private String country;

  @SerializedName("city")
  @Expose
  private String city;

  @SerializedName("mobileNumber")
  @Expose
  private String mobileNumber;

  @SerializedName("latitude")
  @Expose
  private double latitude;

  @SerializedName("alternatePhoneCode")
  @Expose
  private String alternatePhoneCode;

  @SerializedName("placeId")
  @Expose
  private String placeId;

  @SerializedName("createdTimeStamp")
  @Expose
  private long createdTimeStamp;

  @SerializedName("cityId")
  @Expose
  private String cityId;

  @SerializedName("default")
  @Expose
  private boolean jsonMemberDefault;

  @SerializedName("cityName")
  @Expose
  private String cityName;

  @SerializedName("tagged")
  @Expose
  private String tagged;

  @SerializedName("addLine1")
  @Expose
  private String addLine1;

  @SerializedName("addLine2")
  @Expose
  private String addLine2;

  @SerializedName("state")
  @Expose
  private String state;

  @SerializedName("landmark")
  @Expose
  private String landmark;

  @SerializedName("taggedAs")
  @Expose
  private String taggedAs;

  @SerializedName("longitude")
  @Expose
  private double longitude;

  @SerializedName("mobileNumberCode")
  @Expose
  private String mobileNumberCode;

  @SerializedName("pincode")
  @Expose
  private String pincode;

  @SerializedName("flatNumber")
  @Expose
  private String flatNumber;

  @SerializedName("alternatePhone")
  @Expose
  private String alternatePhone;

  @SerializedName("locality")
  @Expose
  private String locality;

  @SerializedName("userId")
  @Expose
  private String userId;

  @SerializedName("createdIsoDate")
  @Expose
  private String createdIsoDate;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("fullAddress")
  @Expose
  private String fullAddress;

  @SerializedName("_id")
  @Expose
  private String id;

  @SerializedName("userType")
  @Expose
  private int userType;

  protected DeliveryAddress(Parcel in) {
    country = in.readString();
    city = in.readString();
    mobileNumber = in.readString();
    latitude = in.readDouble();
    alternatePhoneCode = in.readString();
    placeId = in.readString();
    createdTimeStamp = in.readLong();
    cityId = in.readString();
    jsonMemberDefault = in.readByte() != 0;
    cityName = in.readString();
    tagged = in.readString();
    addLine1 = in.readString();
    addLine2 = in.readString();
    state = in.readString();
    landmark = in.readString();
    taggedAs = in.readString();
    longitude = in.readDouble();
    mobileNumberCode = in.readString();
    pincode = in.readString();
    flatNumber = in.readString();
    alternatePhone = in.readString();
    locality = in.readString();
    userId = in.readString();
    createdIsoDate = in.readString();
    name = in.readString();
    fullAddress = in.readString();
    id = in.readString();
    userType = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(country);
    dest.writeString(city);
    dest.writeString(mobileNumber);
    dest.writeDouble(latitude);
    dest.writeString(alternatePhoneCode);
    dest.writeString(placeId);
    dest.writeLong(createdTimeStamp);
    dest.writeString(cityId);
    dest.writeByte((byte) (jsonMemberDefault ? 1 : 0));
    dest.writeString(cityName);
    dest.writeString(tagged);
    dest.writeString(addLine1);
    dest.writeString(addLine2);
    dest.writeString(state);
    dest.writeString(landmark);
    dest.writeString(taggedAs);
    dest.writeDouble(longitude);
    dest.writeString(mobileNumberCode);
    dest.writeString(pincode);
    dest.writeString(flatNumber);
    dest.writeString(alternatePhone);
    dest.writeString(locality);
    dest.writeString(userId);
    dest.writeString(createdIsoDate);
    dest.writeString(name);
    dest.writeString(fullAddress);
    dest.writeString(id);
    dest.writeInt(userType);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<DeliveryAddress> CREATOR = new Creator<DeliveryAddress>() {
    @Override
    public DeliveryAddress createFromParcel(Parcel in) {
      return new DeliveryAddress(in);
    }

    @Override
    public DeliveryAddress[] newArray(int size) {
      return new DeliveryAddress[size];
    }
  };

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public double getLatitude() {
    return latitude;
  }

  public String getAlternatePhoneCode() {
    return alternatePhoneCode;
  }

  public String getPlaceId() {
    return placeId;
  }

  public long getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public String getCityId() {
    return cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public String getTagged() {
    return tagged;
  }

  public String getAddLine1() {
    return addLine1;
  }

  public String getAddLine2() {
    return addLine2;
  }

  public String getState() {
    return state;
  }

  public String getLandmark() {
    return landmark;
  }

  public String getTaggedAs() {
    return taggedAs;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getMobileNumberCode() {
    return mobileNumberCode;
  }

  public String getPincode() {
    return pincode;
  }

  public String getFlatNumber() {
    return flatNumber;
  }

  public String getAlternatePhone() {
    return alternatePhone;
  }

  public String getLocality() {
    return locality;
  }

  public String getUserId() {
    return userId;
  }

  public String getCreatedIsoDate() {
    return createdIsoDate;
  }

  public String getName() {
    return name;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public String getId() {
    return id;
  }

  public int getUserType() {
    return userType;
  }
}