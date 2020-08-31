package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class OrderImages implements Parcelable {
  protected OrderImages(Parcel in) {
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<OrderImages> CREATOR = new Creator<OrderImages>() {
    @Override
    public OrderImages createFromParcel(Parcel in) {
      return new OrderImages(in);
    }

    @Override
    public OrderImages[] newArray(int size) {
      return new OrderImages[size];
    }
  };
}
