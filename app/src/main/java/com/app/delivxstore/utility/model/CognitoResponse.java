package com.app.delivxstore.utility.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CognitoResponse implements Parcelable {
  public static final Creator<CognitoResponse> CREATOR = new Creator<CognitoResponse>() {
    @Override
    public CognitoResponse createFromParcel(Parcel in) {
      return new CognitoResponse(in);
    }

    @Override
    public CognitoResponse[] newArray(int size) {
      return new CognitoResponse[size];
    }
  };
  @SerializedName("data")
  @Expose
  private Data data;
  @SerializedName("message")
  @Expose
  private String message;

  protected CognitoResponse(Parcel in) {
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public Data getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }
}
