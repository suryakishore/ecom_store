package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeProducts implements Parcelable {

  @SerializedName("productOrderId")
  @Expose
  private String productOrderId;
  @SerializedName("productId")
  @Expose
  private String productId;
  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;
  @SerializedName("unitId")
  @Expose
  private String unitId;
  @SerializedName("allowOrderOutOfStock")
  @Expose
  private Boolean allowOrderOutOfStock;
  /* @SerializedName("quantity")
   @Expose
   private Quantity quantity;*/
  @SerializedName("packageType")
  @Expose
  private String packageType;
  @SerializedName("noofunits")
  @Expose
  private String noofunits;
  /*@SerializedName("images")
  @Expose
  private Images images;*/
  @SerializedName("brandName")
  @Expose
  private String brandName;
  @SerializedName("color")
  @Expose
  private String color;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("isCentral")
  @Expose
  private Boolean isCentral;
  @SerializedName("productDeliveryFee")
  @Expose
  private Integer productDeliveryFee;
  @SerializedName("packageId")
  @Expose
  private String packageId;
  @SerializedName("invoiceLink")
  @Expose
  private String invoiceLink;
  @SerializedName("shippingLabel")
  @Expose
  private String shippingLabel;
  @SerializedName("isSplitProduct")
  @Expose
  private Boolean isSplitProduct;
  @SerializedName("isParentProduct")
  @Expose
  private Boolean isParentProduct;
  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;
  @SerializedName("currencySymbol")
  @Expose
  private String currencySymbol;
  @SerializedName("aisle")
  @Expose
  private String aisle;
  @SerializedName("directions")
  @Expose
  private String directions;
  @SerializedName("shelf")
  @Expose
  private String shelf;
  @SerializedName("upcNumber")
  @Expose
  private String upcNumber;
  @SerializedName("status")
  @Expose
  private Status status;

  protected HomeProducts(Parcel in) {
    productOrderId = in.readString();
    productId = in.readString();
    centralProductId = in.readString();
    unitId = in.readString();
    byte tmpAllowOrderOutOfStock = in.readByte();
    allowOrderOutOfStock = tmpAllowOrderOutOfStock == 0 ? null : tmpAllowOrderOutOfStock == 1;
    packageType = in.readString();
    noofunits = in.readString();
    brandName = in.readString();
    color = in.readString();
    name = in.readString();
    byte tmpIsCentral = in.readByte();
    isCentral = tmpIsCentral == 0 ? null : tmpIsCentral == 1;
    if (in.readByte() == 0) {
      productDeliveryFee = null;
    } else {
      productDeliveryFee = in.readInt();
    }
    packageId = in.readString();
    invoiceLink = in.readString();
    shippingLabel = in.readString();
    byte tmpIsSplitProduct = in.readByte();
    isSplitProduct = tmpIsSplitProduct == 0 ? null : tmpIsSplitProduct == 1;
    byte tmpIsParentProduct = in.readByte();
    isParentProduct = tmpIsParentProduct == 0 ? null : tmpIsParentProduct == 1;
    currencyCode = in.readString();
    currencySymbol = in.readString();
    aisle = in.readString();
    directions = in.readString();
    shelf = in.readString();
    upcNumber = in.readString();
    status = in.readParcelable(Status.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(productOrderId);
    dest.writeString(productId);
    dest.writeString(centralProductId);
    dest.writeString(unitId);
    dest.writeByte((byte)(allowOrderOutOfStock == null ? 0 : allowOrderOutOfStock ? 1 : 2));
    dest.writeString(packageType);
    dest.writeString(noofunits);
    dest.writeString(brandName);
    dest.writeString(color);
    dest.writeString(name);
    dest.writeByte((byte)(isCentral == null ? 0 : isCentral ? 1 : 2));
    if (productDeliveryFee == null) {
      dest.writeByte((byte)0);
    } else {
      dest.writeByte((byte)1);
      dest.writeInt(productDeliveryFee);
    }
    dest.writeString(packageId);
    dest.writeString(invoiceLink);
    dest.writeString(shippingLabel);
    dest.writeByte((byte)(isSplitProduct == null ? 0 : isSplitProduct ? 1 : 2));
    dest.writeByte((byte)(isParentProduct == null ? 0 : isParentProduct ? 1 : 2));
    dest.writeString(currencyCode);
    dest.writeString(currencySymbol);
    dest.writeString(aisle);
    dest.writeString(directions);
    dest.writeString(shelf);
    dest.writeString(upcNumber);
    dest.writeParcelable(status, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<HomeProducts> CREATOR = new Creator<HomeProducts>() {
    @Override
    public HomeProducts createFromParcel(Parcel in) {
      return new HomeProducts(in);
    }

    @Override
    public HomeProducts[] newArray(int size) {
      return new HomeProducts[size];
    }
  };

  public String getProductOrderId() {
    return productOrderId;
  }

  public void setProductOrderId(String productOrderId) {
    this.productOrderId = productOrderId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getCentralProductId() {
    return centralProductId;
  }

  public void setCentralProductId(String centralProductId) {
    this.centralProductId = centralProductId;
  }

  public String getUnitId() {
    return unitId;
  }

  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }

  public Boolean getAllowOrderOutOfStock() {
    return allowOrderOutOfStock;
  }

  public void setAllowOrderOutOfStock(Boolean allowOrderOutOfStock) {
    this.allowOrderOutOfStock = allowOrderOutOfStock;
  }
/*
  public Quantity getQuantity() {
    return quantity;
  }*/
/*
  public void setQuantity(Quantity quantity) {
    this.quantity = quantity;
  }*/

  public String getPackageType() {
    return packageType;
  }

  public void setPackageType(String packageType) {
    this.packageType = packageType;
  }

  public String getNoofunits() {
    return noofunits;
  }

  public void setNoofunits(String noofunits) {
    this.noofunits = noofunits;
  }

 /* public Images getImages() {
    return images;
  }*/
/*

  public void setImages(Images images) {
    this.images = images;
  }
*/

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getIsCentral() {
    return isCentral;
  }

  public void setIsCentral(Boolean isCentral) {
    this.isCentral = isCentral;
  }

  public Integer getProductDeliveryFee() {
    return productDeliveryFee;
  }

  public void setProductDeliveryFee(Integer productDeliveryFee) {
    this.productDeliveryFee = productDeliveryFee;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public String getInvoiceLink() {
    return invoiceLink;
  }

  public void setInvoiceLink(String invoiceLink) {
    this.invoiceLink = invoiceLink;
  }

  public String getShippingLabel() {
    return shippingLabel;
  }

  public void setShippingLabel(String shippingLabel) {
    this.shippingLabel = shippingLabel;
  }

  public Boolean getIsSplitProduct() {
    return isSplitProduct;
  }

  public void setIsSplitProduct(Boolean isSplitProduct) {
    this.isSplitProduct = isSplitProduct;
  }

  public Boolean getIsParentProduct() {
    return isParentProduct;
  }

  public void setIsParentProduct(Boolean isParentProduct) {
    this.isParentProduct = isParentProduct;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public String getAisle() {
    return aisle;
  }

  public void setAisle(String aisle) {
    this.aisle = aisle;
  }

  public String getDirections() {
    return directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }

  public String getShelf() {
    return shelf;
  }

  public void setShelf(String shelf) {
    this.shelf = shelf;
  }

  public String getUpcNumber() {
    return upcNumber;
  }

  public void setUpcNumber(String upcNumber) {
    this.upcNumber = upcNumber;
  }

  public Boolean getCentral() {
    return isCentral;
  }

  public void setCentral(Boolean central) {
    isCentral = central;
  }

  public Boolean getSplitProduct() {
    return isSplitProduct;
  }

  public void setSplitProduct(Boolean splitProduct) {
    isSplitProduct = splitProduct;
  }

  public Boolean getParentProduct() {
    return isParentProduct;
  }

  public void setParentProduct(Boolean parentProduct) {
    isParentProduct = parentProduct;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
