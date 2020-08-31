package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingDetails implements Parcelable {
  public static final Creator<ShippingDetails> CREATOR = new Creator<ShippingDetails>() {
    @Override
    public ShippingDetails createFromParcel(Parcel in) {
      return new ShippingDetails(in);
    }

    @Override
    public ShippingDetails[] newArray(int size) {
      return new ShippingDetails[size];
    }
  };
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("trackingId")
  @Expose
  private String trackingId;

  protected ShippingDetails(Parcel in) {
    name = in.readString();
    id = in.readString();
    trackingId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(id);
    dest.writeString(trackingId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getTrackingId() {
    return trackingId;
  }
}