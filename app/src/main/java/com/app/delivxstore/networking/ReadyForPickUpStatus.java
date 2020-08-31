package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadyForPickUpStatus implements Parcelable {
  @SerializedName("packageId")
  @Expose
  private String packageId;
  @SerializedName("driverId")
  @Expose
  private String driverId;
  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("autoMode")
  @Expose
  private boolean autoMode;

  public ReadyForPickUpStatus() {
  }

  protected ReadyForPickUpStatus(Parcel in) {
    packageId = in.readString();
    driverId = in.readString();
    autoMode = in.readByte() != 0;
  }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(packageId);
        dest.writeString(driverId);
        dest.writeString(orderId);
        dest.writeByte((byte) (autoMode ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReadyForPickUpStatus> CREATOR =
        new Creator<ReadyForPickUpStatus>() {
            @Override
            public ReadyForPickUpStatus createFromParcel(Parcel in) {
                return new ReadyForPickUpStatus(in);
            }

            @Override
            public ReadyForPickUpStatus[] newArray(int size) {
                return new ReadyForPickUpStatus[size];
            }
        };

    public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public boolean isAutoMode() {
    return autoMode;
  }

  public void setAutoMode(boolean autoMode) {
    this.autoMode = autoMode;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
