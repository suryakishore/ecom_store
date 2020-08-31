package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.home.models.DriverDetails;
import com.app.delivxstore.main.home.models.PartnerDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackingDetails implements Parcelable {
  public static final Creator<PackingDetails> CREATOR = new Creator<PackingDetails>() {
    @Override
    public PackingDetails createFromParcel(Parcel in) {
      return new PackingDetails(in);
    }

    @Override
    public PackingDetails[] newArray(int size) {
      return new PackingDetails[size];
    }
  };
  @SerializedName("driverDetails")
  @Expose
  private DriverDetails driverDetails;
  @SerializedName("partnerDetails")
  @Expose
  private PartnerDetails partnerDetails;
  @SerializedName("packageId")
  @Expose
  private String packageId;

  protected PackingDetails(Parcel in) {
    driverDetails = in.readParcelable(DriverDetails.class.getClassLoader());
    partnerDetails = in.readParcelable(PartnerDetails.class.getClassLoader());
    packageId = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(driverDetails, flags);
    dest.writeParcelable(partnerDetails, flags);
    dest.writeString(packageId);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public DriverDetails getDriverDetails() {
    return driverDetails;
  }

  public void setDriverDetails(DriverDetails driverDetails) {
    this.driverDetails = driverDetails;
  }

  public PartnerDetails getPartnerDetails() {
    return partnerDetails;
  }

  public void setPartnerDetails(PartnerDetails partnerDetails) {
    this.partnerDetails = partnerDetails;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }
}
