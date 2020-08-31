package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse implements Parcelable {

  public static final Creator<TransactionResponse> CREATOR = new Creator<TransactionResponse>() {
    @Override
    public TransactionResponse createFromParcel(Parcel in) {
      return new TransactionResponse(in);
    }

    @Override
    public TransactionResponse[] newArray(int size) {
      return new TransactionResponse[size];
    }
  };
  @SerializedName("data")
  @Expose
  private TransactionData data;
  @SerializedName("message")
  @Expose
  private String message;

  public TransactionResponse(Parcel in) {
    data = in.readParcelable(TransactionData.class.getClassLoader());
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

  public TransactionData getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return
        "TransactionResponse{" +
            "data = '" + data + '\'' +
            ",message = '" + message + '\'' +
            "}";
  }
}