package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrderInput implements Parcelable {
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("latitude")
  @Expose
  private double latitude;
  @SerializedName("longitude")
  @Expose
  private double longitude;

  public ConfirmOrderInput() {
  }

  protected ConfirmOrderInput(Parcel in) {
    type = in.readString();
    orderId = in.readString();
    latitude = in.readDouble();
    longitude = in.readDouble();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(type);
    dest.writeString(orderId);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ConfirmOrderInput> CREATOR = new Creator<ConfirmOrderInput>() {
    @Override
    public ConfirmOrderInput createFromParcel(Parcel in) {
      return new ConfirmOrderInput(in);
    }

    @Override
    public ConfirmOrderInput[] newArray(int size) {
      return new ConfirmOrderInput[size];
    }
  };

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
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
}
