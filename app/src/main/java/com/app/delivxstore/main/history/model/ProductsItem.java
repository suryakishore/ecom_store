package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductsItem implements Parcelable {

  @SerializedName("upcNumber")
  @Expose
  private String upcNumber;

  @SerializedName("color")
  @Expose
  private String color;

  @SerializedName("timestamps")
  @Expose
  private Timestamps timestamps;

  @SerializedName("singleUnitPrice")
  @Expose
  private SingleUnitPrice singleUnitPrice;

  @SerializedName("offerDetails")
  @Expose
  private OfferDetails offerDetails;

  @SerializedName("noofunits")
  @Expose
  private String noofunits;

  @SerializedName("productOrderId")
  @Expose
  private String productOrderId;

  @SerializedName("accounting")
  @Expose
  private Accounting accounting;

  @SerializedName("aisle")
  @Expose
  private String aisle;

  @SerializedName("packageType")
  @Expose
  private String packageType;

  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;

  @SerializedName("allowOrderOutOfStock")
  @Expose
  private boolean allowOrderOutOfStock;

  @SerializedName("shippingDetails")
  @Expose
  private ShippingDetails shippingDetails;

  @SerializedName("unitId")
  @Expose
  private String unitId;

  @SerializedName("invoiceLink")
  @Expose
  private String invoiceLink;

  @SerializedName("productDeliveryFee")
  @Expose
  private int productDeliveryFee;

  @SerializedName("mouData")
  @Expose
  private MouData mouData;

  @SerializedName("images")
  @Expose
  private Images images;

  @SerializedName("brandName")
  @Expose
  private String brandName;

  @SerializedName("quantity")
  @Expose
  private Quantity quantity;

  @SerializedName("productId")
  @Expose
  private String productId;

  @SerializedName("coupon")
  @Expose
  private String coupon;

/*  @SerializedName("addOns")
  @Expose
  private List<Object> addOns;*/

  @SerializedName("packageId")
  @Expose
  private String packageId;

  @SerializedName("shippingLabel")
  @Expose
  private String shippingLabel;

  @SerializedName("isSplitProduct")
  @Expose
  private boolean isSplitProduct;

  @SerializedName("currencySymbol")
  @Expose
  private String currencySymbol;

  @SerializedName("packaging")
  @Expose
  private Packaging packaging;

  @SerializedName("shelf")
  @Expose
  private String shelf;

  @SerializedName("isParentProduct")
  @Expose
  private boolean isParentProduct;

  @SerializedName("isCentral")
  @Expose
  private boolean isCentral;

  @SerializedName("directions")
  @Expose
  private String directions;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("rattingData")
  @Expose
  private RattingData rattingData;

  @SerializedName("attributes")
  @Expose
  private ArrayList<AttributesItem> attributes;

  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;

  @SerializedName("status")
  @Expose
  private Status status;

  @SerializedName("updateType")
  @Expose
  private String updateType;

  @SerializedName("reason")
  @Expose
  private String reason;

  protected ProductsItem(Parcel in) {
    upcNumber = in.readString();
    color = in.readString();
    noofunits = in.readString();
    productOrderId = in.readString();
    aisle = in.readString();
    packageType = in.readString();
    centralProductId = in.readString();
    allowOrderOutOfStock = in.readByte() != 0;
    shippingDetails = in.readParcelable(ShippingDetails.class.getClassLoader());
    unitId = in.readString();
    invoiceLink = in.readString();
    productDeliveryFee = in.readInt();
    brandName = in.readString();
    productId = in.readString();
    coupon = in.readString();
    packageId = in.readString();
    shippingLabel = in.readString();
    isSplitProduct = in.readByte() != 0;
    currencySymbol = in.readString();
    shelf = in.readString();
    isParentProduct = in.readByte() != 0;
    isCentral = in.readByte() != 0;
    directions = in.readString();
    name = in.readString();
    currencyCode = in.readString();
    updateType = in.readString();
    reason = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(upcNumber);
    dest.writeString(color);
    dest.writeString(noofunits);
    dest.writeString(productOrderId);
    dest.writeString(aisle);
    dest.writeString(packageType);
    dest.writeString(centralProductId);
    dest.writeByte((byte) (allowOrderOutOfStock ? 1 : 0));
    dest.writeParcelable(shippingDetails, flags);
    dest.writeString(unitId);
    dest.writeString(invoiceLink);
    dest.writeInt(productDeliveryFee);
    dest.writeString(brandName);
    dest.writeString(productId);
    dest.writeString(coupon);
    dest.writeString(packageId);
    dest.writeString(shippingLabel);
    dest.writeByte((byte) (isSplitProduct ? 1 : 0));
    dest.writeString(currencySymbol);
    dest.writeString(shelf);
    dest.writeByte((byte) (isParentProduct ? 1 : 0));
    dest.writeByte((byte) (isCentral ? 1 : 0));
    dest.writeString(directions);
    dest.writeString(name);
    dest.writeString(currencyCode);
    dest.writeString(updateType);
    dest.writeString(reason);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ProductsItem> CREATOR = new Creator<ProductsItem>() {
    @Override
    public ProductsItem createFromParcel(Parcel in) {
      return new ProductsItem(in);
    }

    @Override
    public ProductsItem[] newArray(int size) {
      return new ProductsItem[size];
    }
  };

  public boolean isSplitProduct() {
    return isSplitProduct;
  }

  public boolean isParentProduct() {
    return isParentProduct;
  }

  public boolean isCentral() {
    return isCentral;
  }

  public String getUpdateType() {
    return updateType;
  }

  public String getReason() {
    return reason;
  }

  public String getUpcNumber() {
    return upcNumber;
  }

  public String getColor() {
    return color;
  }

  public Timestamps getTimestamps() {
    return timestamps;
  }

  public SingleUnitPrice getSingleUnitPrice() {
    return singleUnitPrice;
  }

  public OfferDetails getOfferDetails() {
    return offerDetails;
  }

  public String getNoofunits() {
    return noofunits;
  }

  public String getProductOrderId() {
    return productOrderId;
  }

  public Accounting getAccounting() {
    return accounting;
  }

  public String getAisle() {
    return aisle;
  }

  public String getPackageType() {
    return packageType;
  }

  public String getCentralProductId() {
    return centralProductId;
  }

  public boolean isAllowOrderOutOfStock() {
    return allowOrderOutOfStock;
  }

  public ShippingDetails getShippingDetails() {
    return shippingDetails;
  }

  public String getUnitId() {
    return unitId;
  }

  public String getInvoiceLink() {
    return invoiceLink;
  }

  public int getProductDeliveryFee() {
    return productDeliveryFee;
  }

  public MouData getMouData() {
    return mouData;
  }

  public Images getImages() {
    return images;
  }

  public String getBrandName() {
    return brandName;
  }

  public Quantity getQuantity() {
    return quantity;
  }

  public String getProductId() {
    return productId;
  }

  public String getCoupon() {
    return coupon;
  }

/*
  public List<Object> getAddOns() {
    return addOns;
  }
*/

  public String getPackageId() {
    return packageId;
  }

  public String getShippingLabel() {
    return shippingLabel;
  }

  public boolean isIsSplitProduct() {
    return isSplitProduct;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public Packaging getPackaging() {
    return packaging;
  }

  public String getShelf() {
    return shelf;
  }

  public boolean isIsParentProduct() {
    return isParentProduct;
  }

  public boolean isIsCentral() {
    return isCentral;
  }

  public String getDirections() {
    return directions;
  }

  public String getName() {
    return name;
  }

  public RattingData getRattingData() {
    return rattingData;
  }

  public ArrayList<AttributesItem> getAttributes() {
    return attributes;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Status getStatus() {
    return status;
  }
}