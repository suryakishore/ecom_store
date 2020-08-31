package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeclineOn implements Parcelable {

  public static final Creator<DeclineOn> CREATOR = new Creator<DeclineOn>() {
    @Override
    public DeclineOn createFromParcel(Parcel in) {
      return new DeclineOn(in);
    }

    @Override
    public DeclineOn[] newArray(int size) {
      return new DeclineOn[size];
    }
  };
  @SerializedName("avs_failure")
  @Expose
  private boolean avsFailure;
  @SerializedName("cvc_failure")
  @Expose
  private boolean cvcFailure;

  protected DeclineOn(Parcel in) {
    avsFailure = in.readByte() != 0;
    cvcFailure = in.readByte() != 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeByte((byte)(avsFailure ? 1 : 0));
    dest.writeByte((byte)(cvcFailure ? 1 : 0));
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public boolean isAvsFailure() {
    return avsFailure;
  }

  public boolean isCvcFailure() {
    return cvcFailure;
  }

  @Override
  public String toString() {
    return "DeclineOn{" + "avs_failure = '" + avsFailure + '\'' + ",cvc_failure = '" + cvcFailure + '\'' + "}";
  }
}