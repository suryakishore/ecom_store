package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProductReasonData implements Parcelable {
  public static final Creator<ProductReasonData> CREATOR = new Creator<ProductReasonData>() {
    @Override
    public ProductReasonData createFromParcel(Parcel in) {
      return new ProductReasonData(in);
    }

    @Override
    public ProductReasonData[] newArray(int size) {
      return new ProductReasonData[size];
    }
  };
  @SerializedName("reasonData")
  @Expose
  private ArrayList<ProductReason> reasonData;
  @SerializedName("totalCount")
  @Expose
  private int totalCount;

  protected ProductReasonData(Parcel in) {
    reasonData = in.createTypedArrayList(ProductReason.CREATOR);
    totalCount = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(reasonData);
    dest.writeInt(totalCount);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public ArrayList<ProductReason> getReasonData() {
    return reasonData;
  }

  public void setReasonData(
      ArrayList<ProductReason> reasonData) {
    this.reasonData = reasonData;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }
}
