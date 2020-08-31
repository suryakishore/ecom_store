package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CustomerDetails implements Parcelable {

  @SerializedName("firstName")
  @Expose
  private String firstName;
  @SerializedName("lastName")
  @Expose
  private String lastName;
  @SerializedName("countryCode")
  @Expose
  private String countryCode;
  @SerializedName("userTypeText")
  @Expose
  private String userTypeText;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("userType")
  @Expose
  private int userType;
  @SerializedName("mqttTopic")
  @Expose
  private String mqttTopic;
  @SerializedName("fcmTopic")
  @Expose
  private String fcmTopic;
  @SerializedName("email")
  @Expose
  private String email;

  protected CustomerDetails(Parcel in) {
    firstName = in.readString();
    lastName = in.readString();
    countryCode = in.readString();
    userTypeText = in.readString();
    mobile = in.readString();
    id = in.readString();
    userType = in.readInt();
    mqttTopic = in.readString();
    fcmTopic = in.readString();
    email = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(firstName);
    dest.writeString(lastName);
    dest.writeString(countryCode);
    dest.writeString(userTypeText);
    dest.writeString(mobile);
    dest.writeString(id);
    dest.writeInt(userType);
    dest.writeString(mqttTopic);
    dest.writeString(fcmTopic);
    dest.writeString(email);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<CustomerDetails> CREATOR = new Creator<CustomerDetails>() {
    @Override
    public CustomerDetails createFromParcel(Parcel in) {
      return new CustomerDetails(in);
    }

    @Override
    public CustomerDetails[] newArray(int size) {
      return new CustomerDetails[size];
    }
  };

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getUserTypeText() {
    return userTypeText;
  }

  public String getMobile() {
    return mobile;
  }

  public String getId() {
    return id;
  }

  public int getUserType() {
    return userType;
  }

  public String getMqttTopic() {
    return mqttTopic;
  }

  public String getFcmTopic() {
    return fcmTopic;
  }

  public String getEmail() {
    return email;
  }
}