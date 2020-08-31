package com.app.delivxstore.utility.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {
  public static final Creator<Data> CREATOR = new Creator<Data>() {
    @Override
    public Data createFromParcel(Parcel in) {
      return new Data(in);
    }

    @Override
    public Data[] newArray(int size) {
      return new Data[size];
    }
  };
  @SerializedName("bucket")
	@Expose
	private String bucket;
  @SerializedName("IdentityId")
	@Expose
	private String IdentityId;
  @SerializedName("Token")
	@Expose
	private String Token;
  @SerializedName("region")
	@Expose
	private String region;

  protected Data(Parcel in) {
    bucket = in.readString();
    IdentityId = in.readString();
    Token = in.readString();
    region = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(bucket);
    dest.writeString(IdentityId);
    dest.writeString(Token);
    dest.writeString(region);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getBucket() {
    return bucket;
  }

  public String getIdentityId() {
    return IdentityId;
  }

  public String getToken() {
    return Token;
  }

  public String getRegion() {
    return region;
  }
}
