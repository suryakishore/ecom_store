package com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductOrdersRequest implements Parcelable {

  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("quantity")
  @Expose
  private int quantity;

  public ProductOrdersRequest() {

  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  protected ProductOrdersRequest(Parcel in) {
    orderId = in.readString();
    quantity = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderId);
    dest.writeInt(quantity);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ProductOrdersRequest> CREATOR = new Creator<ProductOrdersRequest>() {
    @Override
    public ProductOrdersRequest createFromParcel(Parcel in) {
      return new ProductOrdersRequest(in);
    }

    @Override
    public ProductOrdersRequest[] newArray(int size) {
      return new ProductOrdersRequest[size];
    }
  };
}
