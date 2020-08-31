package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProductUnavailableReasons implements Parcelable {

  @SerializedName("data")
  @Expose
  private ProductReasonData data;
  @SerializedName("message")
  @Expose
  private String message;

  protected ProductUnavailableReasons(Parcel in) {
    data = in.readParcelable(ProductReasonData.class.getClassLoader());
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(data, flags);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ProductUnavailableReasons> CREATOR =
      new Creator<ProductUnavailableReasons>() {
        @Override
        public ProductUnavailableReasons createFromParcel(Parcel in) {
          return new ProductUnavailableReasons(in);
        }

        @Override
        public ProductUnavailableReasons[] newArray(int size) {
          return new ProductUnavailableReasons[size];
        }
      };

  public ProductReasonData getData() {
    return data;
  }

  public void setData(
      ProductReasonData data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
