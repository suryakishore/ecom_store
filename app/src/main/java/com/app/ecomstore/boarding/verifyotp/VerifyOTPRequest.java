package com.app.ecomstore.boarding.verifyotp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOTPRequest implements Parcelable {
  public static final Creator<VerifyOTPRequest> CREATOR = new Creator<VerifyOTPRequest>() {
    @Override
    public VerifyOTPRequest createFromParcel(Parcel in) {
      return new VerifyOTPRequest(in);
    }

    @Override
    public VerifyOTPRequest[] newArray(int size) {
      return new VerifyOTPRequest[size];
    }
  };
  @SerializedName("otpId")
  @Expose
  private String otpId;
  @SerializedName("otpCode")
  @Expose
  private String otpCode;
  @SerializedName("verifyType")
  @Expose
  private int verifyType;

  public VerifyOTPRequest() {
  }

  protected VerifyOTPRequest(Parcel in) {
    otpId = in.readString();
    otpCode = in.readString();
    verifyType = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(otpId);
    dest.writeString(otpCode);
    dest.writeInt(verifyType);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getOtpId() {
    return otpId;
  }

  public void setOtpId(String otpId) {
    this.otpId = otpId;
  }

  public String getOtpCode() {
    return otpCode;
  }

  public void setOtpCode(String otpCode) {
    this.otpCode = otpCode;
  }

  public int getVerifyType() {
    return verifyType;
  }

  public void setVerifyType(int verifyType) {
    this.verifyType = verifyType;
  }
}
