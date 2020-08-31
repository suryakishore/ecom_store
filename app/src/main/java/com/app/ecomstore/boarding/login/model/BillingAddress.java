package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillingAddress implements Parcelable {
  public static final Creator<BillingAddress> CREATOR = new Creator<BillingAddress>() {
    @Override
    public BillingAddress createFromParcel(Parcel in) {
      return new BillingAddress(in);
    }

    @Override
    public BillingAddress[] newArray(int size) {
      return new BillingAddress[size];
    }
  };
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
  @SerializedName("mobileNumber")
  @Expose
  private String mobileNumber;
  @SerializedName("latitude")
  @Expose
  private String latitude;
  @SerializedName("alternatePhoneCode")
  @Expose
  private String alternatePhoneCode;
  @SerializedName("placeId")
  @Expose
  private String placeId;
  @SerializedName("createdTimeStamp")
  @Expose
  private String createdTimeStamp;
  @SerializedName("cityId")
  @Expose
  private String cityId;
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
  @SerializedName("landmark")
  @Expose
  private String landmark;
  @SerializedName("taggedAs")
  @Expose
  private String taggedAs;
  @SerializedName("longitude")
  @Expose
  private String longitude;
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
  private String _id;
  @SerializedName("userType")
  @Expose
  private String userType;

  protected BillingAddress(Parcel in) {
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
    mobileNumber = in.readString();
    latitude = in.readString();
    alternatePhoneCode = in.readString();
    placeId = in.readString();
    createdTimeStamp = in.readString();
    cityId = in.readString();
    cityName = in.readString();
    tagged = in.readString();
    addLine1 = in.readString();
    addLine2 = in.readString();
    landmark = in.readString();
    taggedAs = in.readString();
    longitude = in.readString();
    mobileNumberCode = in.readString();
    pincode = in.readString();
    flatNumber = in.readString();
    alternatePhone = in.readString();
    userId = in.readString();
    createdIsoDate = in.readString();
    name = in.readString();
    fullAddress = in.readString();
    _id = in.readString();
    userType = in.readString();
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
    dest.writeString(mobileNumber);
    dest.writeString(latitude);
    dest.writeString(alternatePhoneCode);
    dest.writeString(placeId);
    dest.writeString(createdTimeStamp);
    dest.writeString(cityId);
    dest.writeString(cityName);
    dest.writeString(tagged);
    dest.writeString(addLine1);
    dest.writeString(addLine2);
    dest.writeString(landmark);
    dest.writeString(taggedAs);
    dest.writeString(longitude);
    dest.writeString(mobileNumberCode);
    dest.writeString(pincode);
    dest.writeString(flatNumber);
    dest.writeString(alternatePhone);
    dest.writeString(userId);
    dest.writeString(createdIsoDate);
    dest.writeString(name);
    dest.writeString(fullAddress);
    dest.writeString(_id);
    dest.writeString(userType);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGooglePlaceId() {
    return googlePlaceId;
  }

  public void setGooglePlaceId(String googlePlaceId) {
    this.googlePlaceId = googlePlaceId;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getAreaOrDistrict() {
    return areaOrDistrict;
  }

  public void setAreaOrDistrict(String areaOrDistrict) {
    this.areaOrDistrict = areaOrDistrict;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getGooglePlaceName() {
    return googlePlaceName;
  }

  public void setGooglePlaceName(String googlePlaceName) {
    this.googlePlaceName = googlePlaceName;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getAlternatePhoneCode() {
    return alternatePhoneCode;
  }

  public void setAlternatePhoneCode(String alternatePhoneCode) {
    this.alternatePhoneCode = alternatePhoneCode;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(String createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getTagged() {
    return tagged;
  }

  public void setTagged(String tagged) {
    this.tagged = tagged;
  }

  public String getAddLine1() {
    return addLine1;
  }

  public void setAddLine1(String addLine1) {
    this.addLine1 = addLine1;
  }

  public String getAddLine2() {
    return addLine2;
  }

  public void setAddLine2(String addLine2) {
    this.addLine2 = addLine2;
  }

  public String getLandmark() {
    return landmark;
  }

  public void setLandmark(String landmark) {
    this.landmark = landmark;
  }

  public String getTaggedAs() {
    return taggedAs;
  }

  public void setTaggedAs(String taggedAs) {
    this.taggedAs = taggedAs;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getMobileNumberCode() {
    return mobileNumberCode;
  }

  public void setMobileNumberCode(String mobileNumberCode) {
    this.mobileNumberCode = mobileNumberCode;
  }

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

  public String getFlatNumber() {
    return flatNumber;
  }

  public void setFlatNumber(String flatNumber) {
    this.flatNumber = flatNumber;
  }

  public String getAlternatePhone() {
    return alternatePhone;
  }

  public void setAlternatePhone(String alternatePhone) {
    this.alternatePhone = alternatePhone;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCreatedIsoDate() {
    return createdIsoDate;
  }

  public void setCreatedIsoDate(String createdIsoDate) {
    this.createdIsoDate = createdIsoDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public void setFullAddress(String fullAddress) {
    this.fullAddress = fullAddress;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Override
  public String toString() {
    return "ClassPojo [country = " + country + ", route = " + route + ", address = " + address
        + ", googlePlaceId = " + googlePlaceId + ", city = " + city + ", locality = " + locality
        + ", areaOrDistrict = " + areaOrDistrict + ", postCode = " + postCode
        + ", googlePlaceName = " + googlePlaceName + ", state = " + state
        + "]";
  }
}
