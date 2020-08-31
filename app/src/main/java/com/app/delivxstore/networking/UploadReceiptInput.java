package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class UploadReceiptInput implements Parcelable {
  public static final Creator<UploadReceiptInput> CREATOR = new Creator<UploadReceiptInput>() {
    @Override
    public UploadReceiptInput createFromParcel(Parcel in) {
      return new UploadReceiptInput(in);
    }

    @Override
    public UploadReceiptInput[] newArray(int size) {
      return new UploadReceiptInput[size];
    }
  };
  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("imageUrl")
  @Expose
  private ArrayList<String> imageUrl;
  @SerializedName("latitude")
  @Expose
  private double latitude;
  @SerializedName("longitude")
  @Expose
  private double longitude;

  public UploadReceiptInput() {
  }

  protected UploadReceiptInput(Parcel in) {
    orderId = in.readString();
    imageUrl = in.createStringArrayList();
    latitude = in.readDouble();
    longitude = in.readDouble();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderId);
    dest.writeStringList(imageUrl);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public ArrayList<String> getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(ArrayList<String> imageUrl) {
    this.imageUrl = imageUrl;
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
