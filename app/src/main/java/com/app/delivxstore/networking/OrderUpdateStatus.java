package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderUpdateStatus implements Parcelable {
  public static final Creator<OrderUpdateStatus> CREATOR = new Creator<OrderUpdateStatus>() {
    @Override
    public OrderUpdateStatus createFromParcel(Parcel in) {
      return new OrderUpdateStatus(in);
    }

    @Override
    public OrderUpdateStatus[] newArray(int size) {
      return new OrderUpdateStatus[size];
    }
  };
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("preparationTime")
  @Expose
  private String preparationTime;
  @SerializedName("orderId")
  @Expose
  private String orderId;

  public OrderUpdateStatus() {
  }

  protected OrderUpdateStatus(Parcel in) {
    type = in.readString();
    preparationTime = in.readString();
    orderId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(type);
    dest.writeString(preparationTime);
    dest.writeString(orderId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPreparationTime() {
    return preparationTime;
  }

  public void setPreparationTime(String preparationTime) {
    this.preparationTime = preparationTime;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
