package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddItemOrderRequest {
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
  @SerializedName("newItems")
  @Expose
  private AddProductRequest newItems;

  public AddItemOrderRequest() {
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

  public AddProductRequest getNewItems() {
    return newItems;
  }

  public void setNewItems(
      AddProductRequest newItems) {
    this.newItems = newItems;
  }
}
