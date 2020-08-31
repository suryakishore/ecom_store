package com.app.delivxstore.networking;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPickInput implements Parcelable {
  public static final Creator<OrderPickInput> CREATOR = new Creator<OrderPickInput>() {
    @Override
    public OrderPickInput createFromParcel(Parcel in) {
      return new OrderPickInput(in);
    }

    @Override
    public OrderPickInput[] newArray(int size) {
      return new OrderPickInput[size];
    }
  };
  @SerializedName("productOrder")
  @Expose
  private ProductOrderInput ProductOrderInput;
  @SerializedName("productImage")
  @Expose
  private String productImage;
  @SerializedName("latitude")
  @Expose
  private double latitude;
  @SerializedName("longitude")
  @Expose
  private double longitude;

  public OrderPickInput() {
  }

  protected OrderPickInput(Parcel in) {
    ProductOrderInput = in.readParcelable(
        com.app.delivxstore.networking.ProductOrderInput.class.getClassLoader());
    productImage = in.readString();
    latitude = in.readDouble();
    longitude = in.readDouble();
  }

  public ProductOrderInput getProductOrderInput() {
    return ProductOrderInput;
  }

  public void setProductOrderInput(
      ProductOrderInput productOrderInput) {
    ProductOrderInput = productOrderInput;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(ProductOrderInput, flags);
    dest.writeString(productImage);
    dest.writeDouble(latitude);
    dest.writeDouble(longitude);
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
