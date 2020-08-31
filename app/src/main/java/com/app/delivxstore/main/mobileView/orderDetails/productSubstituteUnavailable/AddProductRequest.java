package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddProductRequest implements Parcelable {
  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;
  @SerializedName("productId")
  @Expose
  private String productId;
  @SerializedName("unitId")
  @Expose
  private String unitId;
  @SerializedName("image")
  @Expose
  private String image;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("packageType")
  @Expose
  private String packageType;
  @SerializedName("needsWeighed")
  @Expose
  private boolean needsWeighed;
  @SerializedName("price")
  @Expose
  private int price;
  @SerializedName("quantity")
  @Expose
  private int quantity;

  public AddProductRequest() {
  }

  protected AddProductRequest(Parcel in) {
    centralProductId = in.readString();
    productId = in.readString();
    unitId = in.readString();
    image = in.readString();
    name = in.readString();
    packageType = in.readString();
    needsWeighed = in.readByte() != 0;
    price = in.readInt();
    quantity = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(centralProductId);
    dest.writeString(productId);
    dest.writeString(unitId);
    dest.writeString(image);
    dest.writeString(name);
    dest.writeString(packageType);
    dest.writeByte((byte) (needsWeighed ? 1 : 0));
    dest.writeInt(price);
    dest.writeInt(quantity);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<AddProductRequest> CREATOR =
      new Creator<AddProductRequest>() {
        @Override
        public AddProductRequest createFromParcel(Parcel in) {
          return new AddProductRequest(in);
        }

        @Override
        public AddProductRequest[] newArray(int size) {
          return new AddProductRequest[size];
        }
      };

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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getPackageType() {
    return packageType;
  }

  public void setPackageType(String packageType) {
    this.packageType = packageType;
  }

  public boolean isNeedsWeighed() {
    return needsWeighed;
  }

  public void setNeedsWeighed(boolean needsWeighed) {
    this.needsWeighed = needsWeighed;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
