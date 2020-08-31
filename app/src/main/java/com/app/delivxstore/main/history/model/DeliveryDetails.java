package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DeliveryDetails implements Parcelable {

  @SerializedName("deliveryFee")
  @Expose
  private int deliveryFee;
  @SerializedName("deliveryByDeliveryPartner")
  @Expose
  private boolean deliveryByDeliveryPartner;
  @SerializedName("deliveryByFleetDriver")
  @Expose
  private boolean deliveryByFleetDriver;
  @SerializedName("time")
  @Expose
  private String time;

  protected DeliveryDetails(Parcel in) {
    deliveryFee = in.readInt();
    deliveryByDeliveryPartner = in.readByte() != 0;
    deliveryByFleetDriver = in.readByte() != 0;
    time = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(deliveryFee);
    dest.writeByte((byte) (deliveryByDeliveryPartner ? 1 : 0));
    dest.writeByte((byte) (deliveryByFleetDriver ? 1 : 0));
    dest.writeString(time);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<DeliveryDetails> CREATOR = new Creator<DeliveryDetails>() {
    @Override
    public DeliveryDetails createFromParcel(Parcel in) {
      return new DeliveryDetails(in);
    }

    @Override
    public DeliveryDetails[] newArray(int size) {
      return new DeliveryDetails[size];
    }
  };

  public int getDeliveryFee() {
    return deliveryFee;
  }

  public boolean isDeliveryByDeliveryPartner() {
    return deliveryByDeliveryPartner;
  }

  public boolean isDeliveryByFleetDriver() {
    return deliveryByFleetDriver;
  }

  public String getTime() {
    return time;
  }
}