package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class WalletResponse implements Parcelable {

  public static final Creator<WalletResponse> CREATOR = new Creator<WalletResponse>() {
    @Override
    public WalletResponse createFromParcel(Parcel in) {
      return new WalletResponse(in);
    }

    @Override
    public WalletResponse[] newArray(int size) {
      return new WalletResponse[size];
    }
  };
  @SerializedName("data")
  private Data data;
  @SerializedName("message")
  private String message;

  protected WalletResponse(Parcel in) {
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

  @Override
  public String toString() {
    return
        "WalletResponse{" +
            "data = '" + data + '\'' +
            ",message = '" + message + '\'' +
            "}";
  }
}