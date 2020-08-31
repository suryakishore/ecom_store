package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MouData implements Parcelable {
  protected MouData(Parcel in) {
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<MouData> CREATOR = new Creator<MouData>() {
    @Override
    public MouData createFromParcel(Parcel in) {
      return new MouData(in);
    }

    @Override
    public MouData[] newArray(int size) {
      return new MouData[size];
    }
  };
}