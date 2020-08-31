package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderCountData implements Parcelable {
  @SerializedName("statusText")
  @Expose
  private String statusText;
  @SerializedName("count")
  @Expose
  private int count;
  @SerializedName("status")
  @Expose
  private int status;

  protected OrderCountData(Parcel in) {
    statusText = in.readString();
    count = in.readInt();
    status = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(statusText);
    dest.writeInt(count);
    dest.writeInt(status);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<OrderCountData> CREATOR = new Creator<OrderCountData>() {
    @Override
    public OrderCountData createFromParcel(Parcel in) {
      return new OrderCountData(in);
    }

    @Override
    public OrderCountData[] newArray(int size) {
      return new OrderCountData[size];
    }
  };

  public String getStatusText ()
  {
    return statusText;
  }

  public void setStatusText (String statusText)
  {
    this.statusText = statusText;
  }

  public int getCount ()
  {
    return count;
  }

  public void setCount (int count)
  {
    this.count = count;
  }

  public int getStatus ()
  {
    return status;
  }

  public void setStatus (int status)
  {
    this.status = status;
  }

}
