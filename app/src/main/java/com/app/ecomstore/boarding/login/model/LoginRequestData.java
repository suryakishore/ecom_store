package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestData implements Parcelable {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("countryCode")
  @Expose
  private String countryCode;
  @SerializedName("verifyType")
  @Expose
  private int verifyType;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("password")
  @Expose
  private String password;
  @SerializedName("ipAddress")
  @Expose
  private String ipAddress;
  @SerializedName("type")
  @Expose
  private double type;
  @SerializedName("latitude")
  @Expose
  private double latitude;
  @SerializedName("longitude")
  @Expose
  private double longitude;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("deviceMake")
  @Expose
  private String deviceMake;
  @SerializedName("deviceOsVersion")
  @Expose
  private String deviceOsVersion;
  @SerializedName("deviceId")
  @Expose
  private String deviceId;
  @SerializedName("appVersion")
  @Expose
  private String appVersion;
  @SerializedName("deviceModel")
  @Expose
  private String deviceModel;

  public LoginRequestData() {
  }

  protected LoginRequestData(Parcel in) {
    id = in.readString();
    email = in.readString();
    countryCode = in.readString();
    verifyType = in.readInt();
    mobile = in.readString();
    password = in.readString();
    ipAddress = in.readString();
    type = in.readDouble();
    latitude = in.readDouble();
    longitude = in.readDouble();
    city = in.readString();
    country = in.readString();
    deviceMake = in.readString();
    deviceOsVersion = in.readString();
    deviceId = in.readString();
    appVersion = in.readString();
    deviceModel = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(email);
    dest.writeString(countryCode);
    dest.writeInt(verifyType);
    dest.writeString(mobile);
    dest.writeString(password);
    dest.writeString(ipAddress);
    dest.writeDouble(type);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
    dest.writeString(city);
    dest.writeString(country);
    dest.writeString(deviceMake);
    dest.writeString(deviceOsVersion);
    dest.writeString(deviceId);
    dest.writeString(appVersion);
    dest.writeString(deviceModel);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<LoginRequestData> CREATOR = new Creator<LoginRequestData>() {
    @Override
    public LoginRequestData createFromParcel(Parcel in) {
      return new LoginRequestData(in);
    }

    @Override
    public LoginRequestData[] newArray(int size) {
      return new LoginRequestData[size];
    }
  };

  public int getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(int verifyType) {
    this.verifyType = verifyType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public double getType() {
    return type;
  }

  public void setType(double type) {
    this.type = type;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDeviceMake() {
    return deviceMake;
  }

  public void setDeviceMake(String deviceMake) {
    this.deviceMake = deviceMake;
  }

  public String getDeviceOsVersion() {
    return deviceOsVersion;
  }

  public void setDeviceOsVersion(String deviceOsVersion) {
    this.deviceOsVersion = deviceOsVersion;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getVersion() {
    return appVersion;
  }

  public void setVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
