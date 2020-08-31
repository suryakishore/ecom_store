package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsModel implements Parcelable {
  public static final Creator<OrderDetailsModel> CREATOR = new Creator<OrderDetailsModel>() {
    @Override
    public OrderDetailsModel createFromParcel(Parcel in) {
      return new OrderDetailsModel(in);
    }

    @Override
    public OrderDetailsModel[] newArray(int size) {
      return new OrderDetailsModel[size];
    }
  };
  @SerializedName("data")
  @Expose
  private Data data;
  @SerializedName("message")
  @Expose
  private String message;

  protected OrderDetailsModel(Parcel in) {
    data = in.readParcelable(Data.class.getClassLoader());
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(data, flags);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ClassPojo [data = " + data + ", message = " + message + "]";
  }
}
