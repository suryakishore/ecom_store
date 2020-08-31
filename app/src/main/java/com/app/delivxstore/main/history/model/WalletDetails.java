package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class WalletDetails implements Parcelable {

  @SerializedName("charges")
  @Expose
  private ArrayList<ChargesItem> charges;

  protected WalletDetails(Parcel in) {
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<WalletDetails> CREATOR = new Creator<WalletDetails>() {
    @Override
    public WalletDetails createFromParcel(Parcel in) {
      return new WalletDetails(in);
    }

    @Override
    public WalletDetails[] newArray(int size) {
      return new WalletDetails[size];
    }
  };

  public ArrayList<ChargesItem> getCharges() {
    return charges;
  }
}