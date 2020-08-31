package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class subsituteWith implements Parcelable {
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("images")
  @Expose
  private Images images;
  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;
  @SerializedName("quantity")
  @Expose
  private Quantity quantity;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("originalPrice")
  @Expose
  private String originalPrice;

  protected subsituteWith(Parcel in) {
    _id = in.readString();
    images = in.readParcelable(Images.class.getClassLoader());
    centralProductId = in.readString();
    quantity = in.readParcelable(Quantity.class.getClassLoader());
    name = in.readString();
    originalPrice = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(_id);
    dest.writeParcelable(images, flags);
    dest.writeString(centralProductId);
    dest.writeParcelable(quantity, flags);
    dest.writeString(name);
    dest.writeString(originalPrice);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<subsituteWith> CREATOR = new Creator<subsituteWith>() {
    @Override
    public subsituteWith createFromParcel(Parcel in) {
      return new subsituteWith(in);
    }

    @Override
    public subsituteWith[] newArray(int size) {
      return new subsituteWith[size];
    }
  };

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public Images getImages() {
    return images;
  }

  public void setImages(Images images) {
    this.images = images;
  }

  public String getCentralProductId() {
    return centralProductId;
  }

  public void setCentralProductId(String centralProductId) {
    this.centralProductId = centralProductId;
  }

  public Quantity getQuantity() {
    return quantity;
  }

  public void setQuantity(Quantity quantity) {
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(String originalPrice) {
    this.originalPrice = originalPrice;
  }
}
