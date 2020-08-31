package com.app.delivxstore.main.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData implements Parcelable {

  public static final Creator<NotificationData> CREATOR = new Creator<NotificationData>() {
    @Override
    public NotificationData createFromParcel(Parcel in) {
      return new NotificationData(in);
    }

    @Override
    public NotificationData[] newArray(int size) {
      return new NotificationData[size];
    }
  };
  @SerializedName("date")
  @Expose
  private String date;
  @SerializedName("image")
  @Expose
  private String image;
  @SerializedName("appName")
  @Expose
  private String appName;
  @SerializedName("packageId")
  @Expose
  private String packageId;
  @SerializedName("productOrderId")
  @Expose
  private String productOrderId;
  @SerializedName("notificationType")
  @Expose
  private int notificationType;
  @SerializedName("body")
  @Expose
  private String body;
  @SerializedName("userName")
  @Expose
  private String userName;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("masterOrderId")
  @Expose
  private String masterOrderId;
  @SerializedName("notificationTypeMsg")
  @Expose
  private String notificationTypeMsg;
  @SerializedName("userTypeMsg")
  @Expose
  private String userTypeMsg;
  @SerializedName("storeOrderId")
  @Expose
  private String storeOrderId;
  @SerializedName("topic")
  @Expose
  private String topic;
  @SerializedName("userType")
  @Expose
  private int userType;
  @SerializedName("day")
  @Expose
  private String day;

  protected NotificationData(Parcel in) {
    date = in.readString();
    image = in.readString();
    appName = in.readString();
    packageId = in.readString();
    productOrderId = in.readString();
    notificationType = in.readInt();
    body = in.readString();
    userName = in.readString();
    title = in.readString();
    masterOrderId = in.readString();
    notificationTypeMsg = in.readString();
    userTypeMsg = in.readString();
    storeOrderId = in.readString();
    topic = in.readString();
    userType = in.readInt();
    day = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(date);
    dest.writeString(image);
    dest.writeString(appName);
    dest.writeString(packageId);
    dest.writeString(productOrderId);
    dest.writeInt(notificationType);
    dest.writeString(body);
    dest.writeString(userName);
    dest.writeString(title);
    dest.writeString(masterOrderId);
    dest.writeString(notificationTypeMsg);
    dest.writeString(userTypeMsg);
    dest.writeString(storeOrderId);
    dest.writeString(topic);
    dest.writeInt(userType);
    dest.writeString(day);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getDate() {
    return date;
  }

  public String getImage() {
    return image;
  }

  public String getAppName() {
    return appName;
  }

  public String getPackageId() {
    return packageId;
  }

  public String getProductOrderId() {
    return productOrderId;
  }

  public int getNotificationType() {
    return notificationType;
  }

  public String getBody() {
    return body;
  }

  public String getUserName() {
    return userName;
  }

  public String getTitle() {
    return title;
  }

  public String getMasterOrderId() {
    return masterOrderId;
  }

  public String getNotificationTypeMsg() {
    return notificationTypeMsg;
  }

  public String getUserTypeMsg() {
    return userTypeMsg;
  }

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public String getTopic() {
    return topic;
  }

  public int getUserType() {
    return userType;
  }

  public String getDay() {
    return day;
  }
}