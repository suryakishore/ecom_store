package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class LoginData implements Parcelable {
  public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
    @Override
    public LoginData createFromParcel(Parcel in) {
      return new LoginData(in);
    }

    @Override
    public LoginData[] newArray(int size) {
      return new LoginData[size];
    }
  };
  @SerializedName("role")
  @Expose
  private String role;
  @SerializedName("authToken")
  @Expose
  private AuthToken authToken;
  @SerializedName("storeFrontType")
  @Expose
  private String storeFrontType;
  @SerializedName("otpId")
  @Expose
  private String otpId;
  @SerializedName("otpExpiryTime")
  @Expose
  private long otpExpiryTime;
  @SerializedName("linkedWithMsg")
  @Expose
  private String linkedWithMsg;
  @SerializedName("apiPrecenceTime")
  @Expose
  private String apiPrecenceTime;
  @SerializedName("apiIntervalTime")
  @Expose
  private String apiIntervalTime;
  @SerializedName("originalPassword")
  @Expose
  private String originalPassword;
  @SerializedName("createdAt")
  @Expose
  private String createdAt;
  @SerializedName("sellerId")
  @Expose
  private String sellerId;
  @SerializedName("countryCode")
  @Expose
  private String countryCode;
  @SerializedName("storeName")
  @Expose
  private StoreName storeName;
  @SerializedName("lastStatusLog")
  @Expose
  private LastStatusLog lastStatusLog;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("seqId")
  @Expose
  private String seqId;
  @SerializedName("updatedAt")
  @Expose
  private String updatedAt;
  @SerializedName("storeFrontTypeId")
  @Expose
  private String storeFrontTypeId;
  @SerializedName("cities")
  @Expose
  private ArrayList<Cities> cities;
  @SerializedName("roleId")
  @Expose
  private String roleId;
  @SerializedName("profilePic")
  @Expose
  private String profilePic;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("fcmTopic")
  @Expose
  private String fcmTopic;
  @SerializedName("linkedWith")
  @Expose
  private String linkedWith;
  @SerializedName("statusMsg")
  @Expose
  private String statusMsg;
  @SerializedName("mqttManagerTopic")
  @Expose
  private String mqttManagerTopic;
  @SerializedName("loggedIn")
  @Expose
  private String loggedIn;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("online")
  @Expose
  private String online;
  @SerializedName("storeData")
  @Expose
  private StoreData storeData;
  @SerializedName("location")
  @Expose
  private Location location;
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("status")
  @Expose
  private String status;

  protected LoginData(Parcel in) {
    role = in.readString();
    authToken = in.readParcelable(AuthToken.class.getClassLoader());
    storeFrontType = in.readString();
    otpId = in.readString();
    otpExpiryTime = in.readLong();
    linkedWithMsg = in.readString();
    apiPrecenceTime = in.readString();
    apiIntervalTime = in.readString();
    originalPassword = in.readString();
    createdAt = in.readString();
    sellerId = in.readString();
    countryCode = in.readString();
    email = in.readString();
    seqId = in.readString();
    updatedAt = in.readString();
    storeFrontTypeId = in.readString();
    cities = in.createTypedArrayList(Cities.CREATOR);
    roleId = in.readString();
    profilePic = in.readString();
    mobile = in.readString();
    fcmTopic = in.readString();
    linkedWith = in.readString();
    statusMsg = in.readString();
    mqttManagerTopic = in.readString();
    loggedIn = in.readString();
    name = in.readString();
    online = in.readString();
    _id = in.readString();
    status = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(role);
    dest.writeParcelable(authToken, flags);
    dest.writeString(storeFrontType);
    dest.writeString(otpId);
    dest.writeLong(otpExpiryTime);
    dest.writeString(linkedWithMsg);
    dest.writeString(apiPrecenceTime);
    dest.writeString(apiIntervalTime);
    dest.writeString(originalPassword);
    dest.writeString(createdAt);
    dest.writeString(sellerId);
    dest.writeString(countryCode);
    dest.writeString(email);
    dest.writeString(seqId);
    dest.writeString(updatedAt);
    dest.writeString(storeFrontTypeId);
    dest.writeTypedList(cities);
    dest.writeString(roleId);
    dest.writeString(profilePic);
    dest.writeString(mobile);
    dest.writeString(fcmTopic);
    dest.writeString(linkedWith);
    dest.writeString(statusMsg);
    dest.writeString(mqttManagerTopic);
    dest.writeString(loggedIn);
    dest.writeString(name);
    dest.writeString(online);
    dest.writeString(_id);
    dest.writeString(status);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public AuthToken getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthToken authToken) {
    this.authToken = authToken;
  }

  public String getStoreFrontType() {
    return storeFrontType;
  }

  public void setStoreFrontType(String storeFrontType) {
    this.storeFrontType = storeFrontType;
  }

  public String getLinkedWithMsg() {
    return linkedWithMsg;
  }

  public void setLinkedWithMsg(String linkedWithMsg) {
    this.linkedWithMsg = linkedWithMsg;
  }

  public String getApiPrecenceTime() {
    return apiPrecenceTime;
  }

  public void setApiPrecenceTime(String apiPrecenceTime) {
    this.apiPrecenceTime = apiPrecenceTime;
  }

  public String getApiIntervalTime() {
    return apiIntervalTime;
  }

  public void setApiIntervalTime(String apiIntervalTime) {
    this.apiIntervalTime = apiIntervalTime;
  }

  public String getOriginalPassword() {
    return originalPassword;
  }

  public void setOriginalPassword(String originalPassword) {
    this.originalPassword = originalPassword;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public StoreName getStoreName() {
    return storeName;
  }

  public void setStoreName(StoreName storeName) {
    this.storeName = storeName;
  }

  public LastStatusLog getLastStatusLog() {
    return lastStatusLog;
  }

  public void setLastStatusLog(LastStatusLog lastStatusLog) {
    this.lastStatusLog = lastStatusLog;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSeqId() {
    return seqId;
  }

  public void setSeqId(String seqId) {
    this.seqId = seqId;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getStoreFrontTypeId() {
    return storeFrontTypeId;
  }

  public void setStoreFrontTypeId(String storeFrontTypeId) {
    this.storeFrontTypeId = storeFrontTypeId;
  }

  public ArrayList<Cities> getCities() {
    return cities;
  }

  public void setCities(ArrayList<Cities> cities) {
    this.cities = cities;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFcmTopic() {
    return fcmTopic;
  }

  public void setFcmTopic(String fcmTopic) {
    this.fcmTopic = fcmTopic;
  }

  public String getLinkedWith() {
    return linkedWith;
  }

  public void setLinkedWith(String linkedWith) {
    this.linkedWith = linkedWith;
  }

  public String getStatusMsg() {
    return statusMsg;
  }

  public void setStatusMsg(String statusMsg) {
    this.statusMsg = statusMsg;
  }

  public String getMqttManagerTopic() {
    return mqttManagerTopic;
  }

  public void setMqttManagerTopic(String mqttManagerTopic) {
    this.mqttManagerTopic = mqttManagerTopic;
  }

  public String getLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(String loggedIn) {
    this.loggedIn = loggedIn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOnline() {
    return online;
  }

  public void setOnline(String online) {
    this.online = online;
  }

  public StoreData getStoreData() {
    return storeData;
  }

  public void setStoreData(StoreData storeData) {
    this.storeData = storeData;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOtpId() {
    return otpId;
  }

  public void setOtpId(String otpId) {
    this.otpId = otpId;
  }

  public long getOtpExpiryTime() {
    return otpExpiryTime;
  }

  public void setOtpExpiryTime(long otpExpiryTime) {
    this.otpExpiryTime = otpExpiryTime;
  }
}
