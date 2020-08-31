package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerDetails implements Parcelable {
  public static final Creator<PartnerDetails> CREATOR = new Creator<PartnerDetails>() {
    @Override
    public PartnerDetails createFromParcel(Parcel in) {
      return new PartnerDetails(in);
    }

    @Override
    public PartnerDetails[] newArray(int size) {
      return new PartnerDetails[size];
    }
  };
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("trackingId")
  @Expose
  private String trackingId;

  protected PartnerDetails(Parcel in) {
    id = in.readString();
    name = in.readString();
    trackingId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(name);
    dest.writeString(trackingId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(String trackingId) {
    this.trackingId = trackingId;
  }
}
