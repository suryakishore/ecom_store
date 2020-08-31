package com.app.ecomstore.boarding.verifyotp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPaswwordRequest implements Parcelable {

  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("countryCode")
  @Expose
  private String countryCode;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("verifyType")
  @Expose
  private int verifyType;

  @SerializedName("triggeredBy")
  @Expose
  private String triggeredBy;
  @SerializedName("userName")
  @Expose
  private String userName;

  @SerializedName("deviceId")
  @Expose
  private String deviceId;
  @SerializedName("deviceMake")
  @Expose
  private String deviceMake;
  @SerializedName("browserVersion")
  @Expose
  private String browserVersion;
  @SerializedName("browserName")
  @Expose
  private String browserName;
  @SerializedName("deviceModel")
  @Expose
  private String deviceModel;
 /* @SerializedName("deviceType")
  @Expose
  private int deviceType;*/

  public ForgotPaswwordRequest() {
  }

  protected ForgotPaswwordRequest(Parcel in) {
    mobile = in.readString();
    countryCode = in.readString();
    email = in.readString();
    verifyType = in.readInt();
    triggeredBy = in.readString();
    userName = in.readString();
    deviceId = in.readString();
    deviceMake = in.readString();
    browserVersion = in.readString();
    browserName = in.readString();
    deviceModel = in.readString();
   // deviceType = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mobile);
    dest.writeString(countryCode);
    dest.writeString(email);
    dest.writeInt(verifyType);
    dest.writeString(triggeredBy);
    dest.writeString(userName);
    dest.writeString(deviceId);
    dest.writeString(deviceMake);
    dest.writeString(browserVersion);
    dest.writeString(browserName);
    dest.writeString(deviceModel);
   // dest.writeInt(deviceType);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ForgotPaswwordRequest> CREATOR = new Creator<ForgotPaswwordRequest>() {
    @Override
    public ForgotPaswwordRequest createFromParcel(Parcel in) {
      return new ForgotPaswwordRequest(in);
    }

    @Override
    public ForgotPaswwordRequest[] newArray(int size) {
      return new ForgotPaswwordRequest[size];
    }
  };

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceMake() {
    return deviceMake;
  }

  public void setDeviceMake(String deviceMake) {
    this.deviceMake = deviceMake;
  }

  public String getBrowserVersion() {
    return browserVersion;
  }

  public void setBrowserVersion(String browserVersion) {
    this.browserVersion = browserVersion;
  }

  public String getBrowserName() {
    return browserName;
  }

  public void setBrowserName(String browserName) {
    this.browserName = browserName;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

 /* public int getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(int deviceType) {
    this.deviceType = deviceType;
  }*/

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(int verifyType) {
    this.verifyType = verifyType;
  }

  public String getTriggeredBy() {
    return triggeredBy;
  }

  public void setTriggeredBy(String triggeredBy) {
    this.triggeredBy = triggeredBy;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
