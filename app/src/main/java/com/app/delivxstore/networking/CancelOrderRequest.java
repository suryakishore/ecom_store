package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelOrderRequest implements Parcelable {
  public static final Creator<CancelOrderRequest> CREATOR = new Creator<CancelOrderRequest>() {
    @Override
    public CancelOrderRequest createFromParcel(Parcel in) {
      return new CancelOrderRequest(in);
    }

    @Override
    public CancelOrderRequest[] newArray(int size) {
      return new CancelOrderRequest[size];
    }
  };
  @Expose
  @SerializedName("type")
  private String type;
  @Expose
  @SerializedName("orderId")
  private String orderId;
  @Expose
  @SerializedName("reason")
  private String reason;
  @Expose
  @SerializedName("comment")
  private String comment;

  public CancelOrderRequest() {

  }

  protected CancelOrderRequest(Parcel in) {
    type = in.readString();
    orderId = in.readString();
    reason = in.readString();
    comment = in.readString();
  }

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

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(type);
    dest.writeString(orderId);
    dest.writeString(reason);
    dest.writeString(comment);
  }
}
