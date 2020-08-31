package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnavailableItemRequest implements Parcelable {
  public static final Creator<UnavailableItemRequest> CREATOR =
      new Creator<UnavailableItemRequest>() {
        @Override
        public UnavailableItemRequest createFromParcel(Parcel in) {
          return new UnavailableItemRequest(in);
        }

        @Override
        public UnavailableItemRequest[] newArray(int size) {
          return new UnavailableItemRequest[size];
        }
      };
  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;
  @SerializedName("productId")
  @Expose
  private String productId;
  @SerializedName("unitId")
  @Expose
  private String unitId;
  @SerializedName("quantity")
  @Expose
  private int quantity;

  public UnavailableItemRequest() {
  }

  protected UnavailableItemRequest(Parcel in) {
    centralProductId = in.readString();
    productId = in.readString();
    unitId = in.readString();
    quantity = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(centralProductId);
    dest.writeString(productId);
    dest.writeString(unitId);
    dest.writeInt(quantity);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getCentralProductId() {
    return centralProductId;
  }

  public void setCentralProductId(String centralProductId) {
    this.centralProductId = centralProductId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getUnitId() {
    return unitId;
  }

  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
