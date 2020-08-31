package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SingleUnitPrice implements Parcelable {
  public static final Creator<SingleUnitPrice> CREATOR = new Creator<SingleUnitPrice>() {
    @Override
    public SingleUnitPrice createFromParcel(Parcel in) {
      return new SingleUnitPrice(in);
    }

    @Override
    public SingleUnitPrice[] newArray(int size) {
      return new SingleUnitPrice[size];
    }
  };
  @SerializedName("unitPrice")
  @Expose
  private float unitPrice;
  @SerializedName("offerDiscount")
  @Expose
  private float offerDiscount;
  @SerializedName("taxableAmount")
  @Expose
  private float taxableAmount;
  @SerializedName("finalUnitPrice")
  @Expose
  private float finalUnitPrice;
  @SerializedName("addOnsAmount")
  @Expose
  private float addOnsAmount;
  @SerializedName("tax")
  @Expose
  private List<TaxItem> tax;
  @SerializedName("subTotal")
  @Expose
  private double subTotal;
  @SerializedName("taxAmount")
  @Expose
  private double taxAmount;

  protected SingleUnitPrice(Parcel in) {
    unitPrice = in.readFloat();
    offerDiscount = in.readFloat();
    taxableAmount = in.readFloat();
    finalUnitPrice = in.readFloat();
    addOnsAmount = in.readFloat();
    subTotal = in.readDouble();
    taxAmount = in.readDouble();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeFloat(unitPrice);
    dest.writeFloat(offerDiscount);
    dest.writeFloat(taxableAmount);
    dest.writeFloat(finalUnitPrice);
    dest.writeFloat(addOnsAmount);
    dest.writeDouble(subTotal);
    dest.writeDouble(taxAmount);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public float getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(float unitPrice) {
    this.unitPrice = unitPrice;
  }

  public float getOfferDiscount() {
    return offerDiscount;
  }

  public void setOfferDiscount(float offerDiscount) {
    this.offerDiscount = offerDiscount;
  }

  public float getTaxableAmount() {
    return taxableAmount;
  }

  public void setTaxableAmount(float taxableAmount) {
    this.taxableAmount = taxableAmount;
  }

  public float getFinalUnitPrice() {
    return finalUnitPrice;
  }

  public void setFinalUnitPrice(float finalUnitPrice) {
    this.finalUnitPrice = finalUnitPrice;
  }

  public float getAddOnsAmount() {
    return addOnsAmount;
  }

  public void setAddOnsAmount(float addOnsAmount) {
    this.addOnsAmount = addOnsAmount;
  }

  public List<TaxItem> getTax() {
    return tax;
  }

  public void setTax(List<TaxItem> tax) {
    this.tax = tax;
  }

  public double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(double subTotal) {
    this.subTotal = subTotal;
  }

  public double getTaxAmount() {
    return taxAmount;
  }

  public void setTaxAmount(double taxAmount) {
    this.taxAmount = taxAmount;
  }
}