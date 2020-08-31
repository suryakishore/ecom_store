package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPickedInput implements Parcelable {

  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("orderId")
  @Expose
  private String orderId;

  public OrderPickedInput() {
  }

  protected OrderPickedInput(Parcel in) {
    type = in.readString();
    orderId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(type);
    dest.writeString(orderId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<OrderPickedInput> CREATOR = new Creator<OrderPickedInput>() {
    @Override
    public OrderPickedInput createFromParcel(Parcel in) {
      return new OrderPickedInput(in);
    }

    @Override
    public OrderPickedInput[] newArray(int size) {
      return new OrderPickedInput[size];
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
}
