package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images implements Parcelable {
  public static final Creator<Images> CREATOR = new Creator<Images>() {
    @Override
    public Images createFromParcel(Parcel in) {
      return new Images(in);
    }

    @Override
    public Images[] newArray(int size) {
      return new Images[size];
    }
  };
  @SerializedName("small")
  @Expose
  private String small;
  @SerializedName("large")
  @Expose
  private String large;
  @SerializedName("altText")
  @Expose
  private String altText;
  @SerializedName("extraLarge")
  @Expose
  private String extraLarge;
  @SerializedName("medium")
  @Expose
  private String medium;

  protected Images(Parcel in) {
    small = in.readString();
    large = in.readString();
    altText = in.readString();
    extraLarge = in.readString();
    medium = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(small);
    dest.writeString(large);
    dest.writeString(altText);
    dest.writeString(extraLarge);
    dest.writeString(medium);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getSmall() {
    return small;
  }

  public void setSmall(String small) {
    this.small = small;
  }

  public String getLarge() {
    return large;
  }

  public void setLarge(String large) {
    this.large = large;
  }

  public String getAltText() {
    return altText;
  }

  public void setAltText(String altText) {
    this.altText = altText;
  }

  public String getExtraLarge() {
    return extraLarge;
  }

  public void setExtraLarge(String extraLarge) {
    this.extraLarge = extraLarge;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }

  @Override
  public String toString() {
    return "Images [small = " + small + ", large = " + large + ", altText = " + altText
        + ", extraLarge = " + extraLarge + ", medium = " + medium + "]";
  }
}
