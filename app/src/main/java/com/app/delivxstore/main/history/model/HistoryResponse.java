package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class HistoryResponse implements Parcelable {

  @SerializedName("data")
  @Expose
  private ArrayList<HistoryData> data;
  @SerializedName("count")
  @Expose
  private int count;
  @SerializedName("message")
  @Expose
  private String message;

  protected HistoryResponse(Parcel in) {
    data = in.createTypedArrayList(HistoryData.CREATOR);
    count = in.readInt();
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(data);
    dest.writeInt(count);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<HistoryResponse> CREATOR = new Creator<HistoryResponse>() {
    @Override
    public HistoryResponse createFromParcel(Parcel in) {
      return new HistoryResponse(in);
    }

    @Override
    public HistoryResponse[] newArray(int size) {
      return new HistoryResponse[size];
    }
  };

  public ArrayList<HistoryData> getData() {
    return data;
  }

  public int getCount() {
    return count;
  }

  public String getMessage() {
    return message;
  }
}