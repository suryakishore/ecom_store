package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class OrderNumberRequest implements Parcelable{

  @SerializedName("productOrders")
  @Expose
  private ArrayList<ProductOrdersRequest> productOrders;
  @SerializedName("existingPackageId")
  @Expose
  private String existingPackageId;
  @SerializedName("newBoxId")
  @Expose
  private String newBoxId;
  @SerializedName("ipAddress")
  @Expose
  private String ipAddress;
  @SerializedName("latitude")
  @Expose
  private String latitude;
  @SerializedName("longitude")
  @Expose
  private String longitude;

  public OrderNumberRequest() {
  }

  public ArrayList<ProductOrdersRequest> getProductOrders() {
    return productOrders;
  }

  public void setProductOrders(
      ArrayList<ProductOrdersRequest> productOrders) {
    this.productOrders = productOrders;
  }

  public String getExistingPackageId() {
    return existingPackageId;
  }

  public void setExistingPackageId(String existingPackageId) {
    this.existingPackageId = existingPackageId;
  }

  public String getNewBoxId() {
    return newBoxId;
  }

  public void setNewBoxId(String newBoxId) {
    this.newBoxId = newBoxId;
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

  protected OrderNumberRequest(Parcel in) {
    productOrders = in.createTypedArrayList(ProductOrdersRequest.CREATOR);
    existingPackageId = in.readString();
    newBoxId = in.readString();
    ipAddress = in.readString();
    latitude = in.readString();
    longitude = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(productOrders);
    dest.writeString(existingPackageId);
    dest.writeString(newBoxId);
    dest.writeString(ipAddress);
    dest.writeString(latitude);
    dest.writeString(longitude);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<OrderNumberRequest> CREATOR = new Creator<OrderNumberRequest>() {
    @Override
    public OrderNumberRequest createFromParcel(Parcel in) {
      return new OrderNumberRequest(in);
    }

    @Override
    public OrderNumberRequest[] newArray(int size) {
      return new OrderNumberRequest[size];
    }
  };
}
