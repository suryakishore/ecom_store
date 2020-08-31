package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDetails implements Parcelable {
  public static final Creator<DriverDetails> CREATOR = new Creator<DriverDetails>() {
    @Override
    public DriverDetails createFromParcel(Parcel in) {
      return new DriverDetails(in);
    }

    @Override
    public DriverDetails[] newArray(int size) {
      return new DriverDetails[size];
    }
  };
  @SerializedName("driverId")
  @Expose
  private String driverId;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("driverType")
  @Expose
  private String driverType;
  @SerializedName("countryCode")
  @Expose
  private String countryCode;
  @SerializedName("firstName")
  @Expose
  private String firstName;
  @SerializedName("mqttTopic")
  @Expose
  private String mqttTopic;
  @SerializedName("lastName")
  @Expose
  private String lastName;
  @SerializedName("fcmTopic")
  @Expose
  private String fcmTopic;
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("profilePic")
  @Expose
  private String profilePic;
  @SerializedName("email")
  @Expose
  private String email;

  public DriverDetails() {
  }

  protected DriverDetails(Parcel in) {
    driverId = in.readString();
    mobile = in.readString();
    driverType = in.readString();
    countryCode = in.readString();
    firstName = in.readString();
    mqttTopic = in.readString();
    lastName = in.readString();
    fcmTopic = in.readString();
    storeId = in.readString();
    profilePic = in.readString();
    email = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(driverId);
    dest.writeString(mobile);
    dest.writeString(driverType);
    dest.writeString(countryCode);
    dest.writeString(firstName);
    dest.writeString(mqttTopic);
    dest.writeString(lastName);
    dest.writeString(fcmTopic);
    dest.writeString(storeId);
    dest.writeString(profilePic);
    dest.writeString(email);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getDriverType() {
    return driverType;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMqttTopic() {
    return mqttTopic;
  }

  public void setMqttTopic(String mqttTopic) {
    this.mqttTopic = mqttTopic;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFcmTopic() {
    return fcmTopic;
  }

  public void setFcmTopic(String fcmTopic) {
    this.fcmTopic = fcmTopic;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
