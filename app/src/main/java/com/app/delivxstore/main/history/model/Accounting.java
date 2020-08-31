package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Accounting implements Parcelable {

  @SerializedName("unitPrice")
  @Expose
  private double unitPrice;
  @SerializedName("taxableAmount")
  @Expose
  private double taxableAmount;
  @SerializedName("finalUnitPrice")
  @Expose
  private double finalUnitPrice;
  @SerializedName("appEarningWithTax")
  @Expose
  private double appEarningWithTax;
  @SerializedName("pgEarning")
  @Expose
  private double pgEarning;
  @SerializedName("payBy")
  @Expose
  private PayBy payBy;
  @SerializedName("driverEarning")
  @Expose
  private double driverEarning;
  @SerializedName("currencySymbol")
  @Expose
  private String currencySymbol;
  @SerializedName("addOnsAmount")
  @Expose
  private double addOnsAmount;
  @SerializedName("tax")
  @Expose
  private List<TaxItem> tax;
  @SerializedName("subTotal")
  @Expose
  private double subTotal;
  @SerializedName("storeEarning")
  @Expose
  private double storeEarning;
  @SerializedName("offerDiscount")
  @Expose
  private double offerDiscount;
  @SerializedName("deliveryFee")
  @Expose
  private double deliveryFee;
  @SerializedName("deliveryDetails")
  @Expose
  private DeliveryDetails deliveryDetails;
  @SerializedName("taxAmount")
  @Expose
  private double taxAmount;
  @SerializedName("promoDiscount")
  @Expose
  private double promoDiscount;
  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;
  @SerializedName("finalTotal")
  @Expose
  private double finalTotal;
  @SerializedName("appEarning")
  @Expose
  private double appEarning;

  protected Accounting(Parcel in) {
    unitPrice = in.readDouble();
    taxableAmount = in.readDouble();
    finalUnitPrice = in.readDouble();
    appEarningWithTax = in.readDouble();
    pgEarning = in.readDouble();
    driverEarning = in.readDouble();
    currencySymbol = in.readString();
    addOnsAmount = in.readDouble();
    subTotal = in.readDouble();
    storeEarning = in.readDouble();
    offerDiscount = in.readDouble();
    deliveryFee = in.readDouble();
    deliveryDetails = in.readParcelable(DeliveryDetails.class.getClassLoader());
    taxAmount = in.readDouble();
    promoDiscount = in.readDouble();
    currencyCode = in.readString();
    finalTotal = in.readDouble();
    appEarning = in.readDouble();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(unitPrice);
    dest.writeDouble(taxableAmount);
    dest.writeDouble(finalUnitPrice);
    dest.writeDouble(appEarningWithTax);
    dest.writeDouble(pgEarning);
    dest.writeDouble(driverEarning);
    dest.writeString(currencySymbol);
    dest.writeDouble(addOnsAmount);
    dest.writeDouble(subTotal);
    dest.writeDouble(storeEarning);
    dest.writeDouble(offerDiscount);
    dest.writeDouble(deliveryFee);
    dest.writeParcelable(deliveryDetails, flags);
    dest.writeDouble(taxAmount);
    dest.writeDouble(promoDiscount);
    dest.writeString(currencyCode);
    dest.writeDouble(finalTotal);
    dest.writeDouble(appEarning);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Accounting> CREATOR = new Creator<Accounting>() {
    @Override
    public Accounting createFromParcel(Parcel in) {
      return new Accounting(in);
    }

    @Override
    public Accounting[] newArray(int size) {
      return new Accounting[size];
    }
  };

  public double getUnitPrice() {
    return unitPrice;
  }

  public double getTaxableAmount() {
    return taxableAmount;
  }

  public double getFinalUnitPrice() {
    return finalUnitPrice;
  }

  public double getAppEarningWithTax() {
    return appEarningWithTax;
  }

  public double getPgEarning() {
    return pgEarning;
  }

  public PayBy getPayBy() {
    return payBy;
  }

  public double getDriverEarning() {
    return driverEarning;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public double getAddOnsAmount() {
    return addOnsAmount;
  }

  public List<TaxItem> getTax() {
    return tax;
  }

  public double getSubTotal() {
    return subTotal;
  }

  public double getStoreEarning() {
    return storeEarning;
  }

  public double getOfferDiscount() {
    return offerDiscount;
  }

  public double getDeliveryFee() {
    return deliveryFee;
  }

  public DeliveryDetails getDeliveryDetails() {
    return deliveryDetails;
  }

  public double getTaxAmount() {
    return taxAmount;
  }

  public double getPromoDiscount() {
    return promoDiscount;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public double getFinalTotal() {
    return finalTotal;
  }

  public double getAppEarning() {
    return appEarning;
  }
}