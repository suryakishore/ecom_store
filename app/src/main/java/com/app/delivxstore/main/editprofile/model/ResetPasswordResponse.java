package com.app.delivxstore.main.editprofile.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse implements Parcelable {

  public static final Creator<ResetPasswordResponse> CREATOR =
      new Creator<ResetPasswordResponse>() {
        @Override
        public ResetPasswordResponse createFromParcel(Parcel in) {
          return new ResetPasswordResponse(in);
        }

        @Override
        public ResetPasswordResponse[] newArray(int size) {
          return new ResetPasswordResponse[size];
        }
      };
  @SerializedName("data")
  @Expose
  private Data data;
  @SerializedName("message")
  @Expose
  private String message;

  protected ResetPasswordResponse(Parcel in) {
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

  public String getMessage() {
    return message;
  }
}