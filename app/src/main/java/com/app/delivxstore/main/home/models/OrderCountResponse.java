package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class OrderCountResponse implements Parcelable {
  @SerializedName("data")
  @Expose
  private ArrayList<OrderCountData> data;
  @SerializedName("message")
  @Expose
  private String message;

  protected OrderCountResponse(Parcel in) {
    data = in.createTypedArrayList(OrderCountData.CREATOR);
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(data);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<OrderCountResponse> CREATOR = new Creator<OrderCountResponse>() {
    @Override
    public OrderCountResponse createFromParcel(Parcel in) {
      return new OrderCountResponse(in);
    }

    @Override
    public OrderCountResponse[] newArray(int size) {
      return new OrderCountResponse[size];
    }
  };

  public ArrayList<OrderCountData> getData ()
  {
    return data;
  }

  public void setData (ArrayList<OrderCountData> data)
  {
    this.data = data;
  }

  public String getMessage ()
  {
    return message;
  }

  public void setMessage (String message)
  {
    this.message = message;
  }
}
