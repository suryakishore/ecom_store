package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class GenerateLableInput implements Parcelable {

  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("bags")
  @Expose
  private int bags;
  @SerializedName("latitude")
  @Expose
  private double latitude;
  @SerializedName("longitude")
  @Expose
  private double longitude;

  public GenerateLableInput() {
  }

  protected GenerateLableInput(Parcel in) {
    orderId = in.readString();
    bags = in.readInt();
    latitude = in.readDouble();
    longitude = in.readDouble();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderId);
    dest.writeInt(bags);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<GenerateLableInput> CREATOR = new Creator<GenerateLableInput>() {
    @Override
    public GenerateLableInput createFromParcel(Parcel in) {
      return new GenerateLableInput(in);
    }

    @Override
    public GenerateLableInput[] newArray(int size) {
      return new GenerateLableInput[size];
    }
  };

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public int getBags() {
    return bags;
  }

  public void setBags(int bags) {
    this.bags = bags;
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
