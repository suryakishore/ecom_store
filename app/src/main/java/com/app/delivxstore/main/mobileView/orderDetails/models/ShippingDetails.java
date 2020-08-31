package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingDetails implements Parcelable {
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("trackingId")
  @Expose
  private String trackingId;
  @SerializedName("name")
  @Expose
  private String name;

  public ShippingDetails() {
  }

  protected ShippingDetails(Parcel in) {
    id = in.readString();
    trackingId = in.readString();
    name = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(trackingId);
    dest.writeString(name);
  }

  @Override
  public int describeContents() {
    return 0;
  }

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(String trackingId) {
    this.trackingId = trackingId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
