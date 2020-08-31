package com.app.delivxstore.main.editprofile.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordBody implements Parcelable {

  public static final Creator<ResetPasswordBody> CREATOR = new Creator<ResetPasswordBody>() {
    @Override
    public ResetPasswordBody createFromParcel(Parcel in) {
      return new ResetPasswordBody(in);
    }

    @Override
    public ResetPasswordBody[] newArray(int size) {
      return new ResetPasswordBody[size];
    }
  };
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("type")
  @Expose
  private int type;
  @SerializedName("email")
  @Expose
  private String email;

  public ResetPasswordBody() {
  }

  protected ResetPasswordBody(Parcel in) {
    storeId = in.readString();
    type = in.readInt();
    email = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(storeId);
    dest.writeInt(type);
    dest.writeString(email);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getStoreId() {
    return storeId;
  }

  public int getType() {
    return type;
  }

  public String getEmail() {
    return email;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}