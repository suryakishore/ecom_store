package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("data")
  @Expose
  private LoginData data;

  protected LoginResponse(Parcel in) {
    message = in.readString();
    data = in.readParcelable(LoginData.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(message);
    dest.writeParcelable(data, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
    @Override
    public LoginResponse createFromParcel(Parcel in) {
      return new LoginResponse(in);
    }

    @Override
    public LoginResponse[] newArray(int size) {
      return new LoginResponse[size];
    }
  };

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LoginData getData() {
    return data;
  }

  public void setData(LoginData data) {
    this.data = data;
  }
}
