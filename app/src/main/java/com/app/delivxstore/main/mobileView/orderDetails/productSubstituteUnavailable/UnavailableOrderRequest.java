package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnavailableOrderRequest implements Parcelable {
  public static final Creator<UnavailableOrderRequest> CREATOR =
      new Creator<UnavailableOrderRequest>() {
        @Override
        public UnavailableOrderRequest createFromParcel(Parcel in) {
          return new UnavailableOrderRequest(in);
        }

        @Override
        public UnavailableOrderRequest[] newArray(int size) {
          return new UnavailableOrderRequest[size];
        }
      };
  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("updateType")
  @Expose
  private int updateType;
  @SerializedName("extraNote")
  @Expose
  private String extraNote;
  @SerializedName("ipAddress")
  @Expose
  private String ipAddress;
  @SerializedName("latitude")
  @Expose
  private String latitude;
  @SerializedName("longitude")
  @Expose
  private String longitude;
  @SerializedName("items")
  @Expose
  private UnavailableItemRequest items;
  @SerializedName("newItems")
  @Expose
  private UnavailableItemRequest newItems;

  public UnavailableOrderRequest() {
  }

  protected UnavailableOrderRequest(Parcel in) {
    orderId = in.readString();
    updateType = in.readInt();
    extraNote = in.readString();
    ipAddress = in.readString();
    latitude = in.readString();
    longitude = in.readString();
    items = in.readParcelable(UnavailableItemRequest.class.getClassLoader());
    newItems = in.readParcelable(UnavailableItemRequest.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderId);
    dest.writeInt(updateType);
    dest.writeString(extraNote);
    dest.writeString(ipAddress);
    dest.writeString(latitude);
    dest.writeString(longitude);
    dest.writeParcelable(items, flags);
    dest.writeParcelable(newItems, flags);
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

  public int getUpdateType() {
    return updateType;
  }

  public void setUpdateType(int updateType) {
    this.updateType = updateType;
  }

  public String getExtraNote() {
    return extraNote;
  }

  public void setExtraNote(String extraNote) {
    this.extraNote = extraNote;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public UnavailableItemRequest getItems() {
    return items;
  }

  public void setItems(
      UnavailableItemRequest items) {
    this.items = items;
  }

  public UnavailableItemRequest getNewItems() {
    return newItems;
  }

  public void setNewItems(
      UnavailableItemRequest newItems) {
    this.newItems = newItems;
  }
}
