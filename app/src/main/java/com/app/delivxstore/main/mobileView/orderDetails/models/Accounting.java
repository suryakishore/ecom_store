package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.history.model.DeliveryDetails;
import com.app.delivxstore.main.home.models.PayBy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Accounting implements Parcelable {
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
  @SerializedName("payBy")
  @Expose
  private PayBy payBy;
  @SerializedName("currencySymbol")
  @Expose
  private String currencySymbol;
  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;
  @SerializedName("unitPrice")
  @Expose
  private Double unitPrice;
  @SerializedName("offerDiscount")
  @Expose
  private double offerDiscount;
  @SerializedName("finalUnitPrice")
  @Expose
  private double finalUnitPrice;
  @SerializedName("addOnsAmount")
  @Expose
  private double addOnsAmount;
  @SerializedName("taxableAmount")
  @Expose
  private double taxableAmount;
  @SerializedName("tax")
  @Expose
  private ArrayList<ProductTaxArray> tax = null;
  @SerializedName("taxAmount")
  @Expose
  private Double taxAmount;
  @SerializedName("subTotal")
  @Expose
  private Double subTotal;
  @SerializedName("promoDiscount")
  @Expose
  private Double promoDiscount;
  @SerializedName("deliveryFee")
  @Expose
  private Double deliveryFee;
  @SerializedName("finalTotal")
  @Expose
  private Double finalTotal;
  @SerializedName("deliveryDetails")
  @Expose
  private DeliveryDetails deliveryDetails;
  @SerializedName("appEarning")
  @Expose
  private Double appEarning;
  @SerializedName("appEarningWithTax")
  @Expose
  private Double appEarningWithTax;
  @SerializedName("storeEarning")
  @Expose
  private Double storeEarning;
  @SerializedName("driverEarning")
  @Expose
  private Double driverEarning;
  @SerializedName("pgEarning")
  @Expose
  private Double pgEarning;

  protected Accounting(Parcel in) {
    payBy = in.readParcelable(PayBy.class.getClassLoader());
    currencySymbol = in.readString();
    currencyCode = in.readString();
    if (in.readByte() == 0) {
      unitPrice = null;
    } else {
      unitPrice = in.readDouble();
    }
    offerDiscount = in.readDouble();
    finalUnitPrice = in.readDouble();
    addOnsAmount = in.readDouble();
    taxableAmount = in.readDouble();
    tax = in.createTypedArrayList(ProductTaxArray.CREATOR);
    if (in.readByte() == 0) {
      taxAmount = null;
    } else {
      taxAmount = in.readDouble();
    }
    if (in.readByte() == 0) {
      subTotal = null;
    } else {
      subTotal = in.readDouble();
    }
    if (in.readByte() == 0) {
      promoDiscount = null;
    } else {
      promoDiscount = in.readDouble();
    }
    if (in.readByte() == 0) {
      deliveryFee = null;
    } else {
      deliveryFee = in.readDouble();
    }
    if (in.readByte() == 0) {
      finalTotal = null;
    } else {
      finalTotal = in.readDouble();
    }
    if (in.readByte() == 0) {
      appEarning = null;
    } else {
      appEarning = in.readDouble();
    }
    if (in.readByte() == 0) {
      appEarningWithTax = null;
    } else {
      appEarningWithTax = in.readDouble();
    }
    if (in.readByte() == 0) {
      storeEarning = null;
    } else {
      storeEarning = in.readDouble();
    }
    if (in.readByte() == 0) {
      driverEarning = null;
    } else {
      driverEarning = in.readDouble();
    }
    if (in.readByte() == 0) {
      pgEarning = null;
    } else {
      pgEarning = in.readDouble();
    }
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(payBy, flags);
    dest.writeString(currencySymbol);
    dest.writeString(currencyCode);
    if (unitPrice == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(unitPrice);
    }
    dest.writeDouble(offerDiscount);
    dest.writeDouble(finalUnitPrice);
    dest.writeDouble(addOnsAmount);
    dest.writeDouble(taxableAmount);
    dest.writeTypedList(tax);
    if (taxAmount == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(taxAmount);
    }
    if (subTotal == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(subTotal);
    }
    if (promoDiscount == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(promoDiscount);
    }
    if (deliveryFee == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(deliveryFee);
    }
    if (finalTotal == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(finalTotal);
    }
    if (appEarning == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(appEarning);
    }
    if (appEarningWithTax == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(appEarningWithTax);
    }
    if (storeEarning == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(storeEarning);
    }
    if (driverEarning == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(driverEarning);
    }
    if (pgEarning == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeDouble(pgEarning);
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public PayBy getPayBy() {
    return payBy;
  }

  public void setPayBy(PayBy payBy) {
    this.payBy = payBy;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getOfferDiscount() {
    return offerDiscount;
  }

  public void setOfferDiscount(double offerDiscount) {
    this.offerDiscount = offerDiscount;
  }

  public double getFinalUnitPrice() {
    return finalUnitPrice;
  }

  public void setFinalUnitPrice(double finalUnitPrice) {
    this.finalUnitPrice = finalUnitPrice;
  }

  public double getAddOnsAmount() {
    return addOnsAmount;
  }

  public void setAddOnsAmount(double addOnsAmount) {
    this.addOnsAmount = addOnsAmount;
  }

  public double getTaxableAmount() {
    return taxableAmount;
  }

  public void setTaxableAmount(double taxableAmount) {
    this.taxableAmount = taxableAmount;
  }

  public ArrayList<ProductTaxArray> getTax() {
    return tax;
  }

  public void setTax(
      ArrayList<ProductTaxArray> tax) {
    this.tax = tax;
  }

  public Double getTaxAmount() {
    return taxAmount;
  }

  public void setTaxAmount(Double taxAmount) {
    this.taxAmount = taxAmount;
  }

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getPromoDiscount() {
    return promoDiscount;
  }

  public void setPromoDiscount(Double promoDiscount) {
    this.promoDiscount = promoDiscount;
  }

  public Double getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(Double deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public Double getFinalTotal() {
    return finalTotal;
  }

  public void setFinalTotal(Double finalTotal) {
    this.finalTotal = finalTotal;
  }

  public DeliveryDetails getDeliveryDetails() {
    return deliveryDetails;
  }

  public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
    this.deliveryDetails = deliveryDetails;
  }

  public Double getAppEarning() {
    return appEarning;
  }

  public void setAppEarning(Double appEarning) {
    this.appEarning = appEarning;
  }

  public Double getAppEarningWithTax() {
    return appEarningWithTax;
  }

  public void setAppEarningWithTax(Double appEarningWithTax) {
    this.appEarningWithTax = appEarningWithTax;
  }

  public Double getStoreEarning() {
    return storeEarning;
  }

  public void setStoreEarning(Double storeEarning) {
    this.storeEarning = storeEarning;
  }

  public Double getDriverEarning() {
    return driverEarning;
  }

  public void setDriverEarning(Double driverEarning) {
    this.driverEarning = driverEarning;
  }

  public Double getPgEarning() {
    return pgEarning;
  }

  public void setPgEarning(Double pgEarning) {
    this.pgEarning = pgEarning;
  }
}
