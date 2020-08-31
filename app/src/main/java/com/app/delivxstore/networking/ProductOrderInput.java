package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductOrderInput implements Parcelable  {

  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("quantity")
  @Expose
  private int quantity;

  public ProductOrderInput() {
  }

  protected ProductOrderInput(Parcel in) {
    orderId = in.readString();
    quantity = in.readInt();
  }

  public static final Creator<ProductOrderInput> CREATOR = new Creator<ProductOrderInput>() {
    @Override
    public ProductOrderInput createFromParcel(Parcel in) {
      return new ProductOrderInput(in);
    }

    @Override
    public ProductOrderInput[] newArray(int size) {
      return new ProductOrderInput[size];
    }
  };

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderId);
    dest.writeInt(quantity);
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
