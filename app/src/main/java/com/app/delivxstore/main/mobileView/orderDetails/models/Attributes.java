package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes implements Parcelable  {

  @SerializedName("attrname")
  @Expose
  private String attrname;
  @SerializedName("value")
  @Expose
  private String value;
  @SerializedName("isAddOn")
  @Expose
  private boolean isAddOn;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("price")
  @Expose
  private String price;
  @SerializedName("addOnId")
  @Expose
  private String addOnId;
  @SerializedName("addOnName")
  @Expose
  private String addOnName;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("measurementUnit")
  @Expose
  private String measurementUnit;

  protected Attributes(Parcel in) {
    attrname = in.readString();
    value = in.readString();
    isAddOn = in.readByte() != 0;
    name = in.readString();
    price = in.readString();
    addOnId = in.readString();
    addOnName = in.readString();
    id = in.readString();
    measurementUnit = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(attrname);
    dest.writeString(value);
    dest.writeByte((byte) (isAddOn ? 1 : 0));
    dest.writeString(name);
    dest.writeString(price);
    dest.writeString(addOnId);
    dest.writeString(addOnName);
    dest.writeString(id);
    dest.writeString(measurementUnit);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Attributes> CREATOR = new Creator<Attributes>() {
    @Override
    public Attributes createFromParcel(Parcel in) {
      return new Attributes(in);
    }

    @Override
    public Attributes[] newArray(int size) {
      return new Attributes[size];
    }
  };

  public String getAttrname() {
    return attrname;
  }

  public void setAttrname(String attrname) {
    this.attrname = attrname;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isAddOn() {
    return isAddOn;
  }

  public void setAddOn(boolean addOn) {
    isAddOn = addOn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getAddOnId() {
    return addOnId;
  }

  public void setAddOnId(String addOnId) {
    this.addOnId = addOnId;
  }

  public String getAddOnName() {
    return addOnName;
  }

  public void setAddOnName(String addOnName) {
    this.addOnName = addOnName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMeasurementUnit() {
    return measurementUnit;
  }

  public void setMeasurementUnitName(String measurementUnitName) {
    this.measurementUnit = measurementUnitName;
  }

  @Override
  public String toString() {
    return "ClassPojo [attrname = " + attrname + ", value = " + value + "]";
  }
}
