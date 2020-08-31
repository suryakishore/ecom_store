package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.history.model.Packaging;
import com.app.delivxstore.main.history.model.SingleUnitPrice;
import com.app.delivxstore.main.history.model.Timestamps;
import com.app.delivxstore.main.home.models.Status;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.AddOns;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Products implements Parcelable  {

  @SerializedName("upcNumber")
  @Expose
  private String upcNumber;
  @SerializedName("color")
  @Expose
  private String color;
  /* @SerializedName("noofunits")
   @Expose
   private String noofunits;*/
  @SerializedName("productOrderId")
  @Expose
  private String productOrderId;
  @SerializedName("aisle")
  @Expose
  private String aisle;
  @SerializedName("packageType")
  @Expose
  private String packageType;
  @SerializedName("centralProductId")
  @Expose
  private String centralProductId;
  @SerializedName("accounting")
  @Expose
  private Accounting accounting;
  @SerializedName("unitId")
  @Expose
  private String unitId;
  @SerializedName("productDeliveryFee")
  @Expose
  private String productDeliveryFee;
  @SerializedName("images")
  @Expose
  private Images images;
  @SerializedName("brandName")
  @Expose
  private String brandName;
  @SerializedName("packageId")
  @Expose
  private String packageId;
  @SerializedName("quantity")
  @Expose
  private Quantity quantity;
  @SerializedName("productId")
  @Expose
  private String productId;
  @SerializedName("addOns")
  @Expose
  private ArrayList<AddOns> addOns;
  @SerializedName("currencySymbol")
  @Expose
  private String currencySymbol;
  @SerializedName("shelf")
  @Expose
  private String shelf;
  @SerializedName("isCentral")
  @Expose
  private String isCentral;
  @SerializedName("directions")
  @Expose
  private String directions;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("attributes")
  @Expose
  private ArrayList<Attributes> attributes;
  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;
  @SerializedName("status")
  @Expose
  private Status status;
  @SerializedName("shippingDetails")
  @Expose
  private ShippingDetails shippingDetails;
  @SerializedName("timestamps")
  @Expose
  private Timestamps timestamps;
  @SerializedName("invoiceLink")
  @Expose
  private String invoiceLink;
  @SerializedName("shippingLabel")
  @Expose
  private String shippingLabel;
  @SerializedName("comment")
  @Expose
  private String comment;
  @SerializedName("reason")
  @Expose
  private String reason;
  @SerializedName("packaging")
  @Expose
  private Packaging packaging;
  @SerializedName("singleUnitPrice")
  @Expose
  private SingleUnitPrice singleUnitPrice;
  @SerializedName("sellerSingleUnitPrice")
  @Expose
  private SingleUnitPrice sellerSingleUnitPrice;
  @SerializedName("sellerAccounting")
  @Expose
  private SingleUnitPrice sellerAccounting;
  @SerializedName("prescriptionRequired")
  @Expose
  private boolean prescriptionRequired;
  @SerializedName("barcode")
  @Expose
  private String barcode;
  @SerializedName("subsitute")
  @Expose
  private boolean subsitute;
  @SerializedName("subsituteWith")
  @Expose
  private subsituteWith subsituteWith;
  private boolean isSelected;
  private int itemType;
  private int prescriptionCount;

  public Products() {
  }

  protected Products(Parcel in) {
    upcNumber = in.readString();
    color = in.readString();
    productOrderId = in.readString();
    aisle = in.readString();
    packageType = in.readString();
    centralProductId = in.readString();
    accounting = in.readParcelable(Accounting.class.getClassLoader());
    unitId = in.readString();
    productDeliveryFee = in.readString();
    images = in.readParcelable(Images.class.getClassLoader());
    brandName = in.readString();
    packageId = in.readString();
    quantity = in.readParcelable(Quantity.class.getClassLoader());
    productId = in.readString();
    addOns = in.createTypedArrayList(AddOns.CREATOR);
    currencySymbol = in.readString();
    shelf = in.readString();
    isCentral = in.readString();
    directions = in.readString();
    name = in.readString();
    attributes = in.createTypedArrayList(Attributes.CREATOR);
    currencyCode = in.readString();
    status = in.readParcelable(Status.class.getClassLoader());
    shippingDetails = in.readParcelable(ShippingDetails.class.getClassLoader());
    timestamps = in.readParcelable(Timestamps.class.getClassLoader());
    invoiceLink = in.readString();
    shippingLabel = in.readString();
    comment = in.readString();
    reason = in.readString();
    packaging = in.readParcelable(Packaging.class.getClassLoader());
    singleUnitPrice = in.readParcelable(SingleUnitPrice.class.getClassLoader());
    sellerSingleUnitPrice = in.readParcelable(SingleUnitPrice.class.getClassLoader());
    sellerAccounting = in.readParcelable(SingleUnitPrice.class.getClassLoader());
    prescriptionRequired = in.readByte() != 0;
    barcode = in.readString();
    subsitute = in.readByte() != 0;
    subsituteWith = in.readParcelable(
        com.app.delivxstore.main.mobileView.orderDetails.models.subsituteWith.class.getClassLoader());
    isSelected = in.readByte() != 0;
    itemType = in.readInt();
    prescriptionCount = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(upcNumber);
    dest.writeString(color);
    dest.writeString(productOrderId);
    dest.writeString(aisle);
    dest.writeString(packageType);
    dest.writeString(centralProductId);
    dest.writeParcelable(accounting, flags);
    dest.writeString(unitId);
    dest.writeString(productDeliveryFee);
    dest.writeParcelable(images, flags);
    dest.writeString(brandName);
    dest.writeString(packageId);
    dest.writeParcelable(quantity, flags);
    dest.writeString(productId);
    dest.writeTypedList(addOns);
    dest.writeString(currencySymbol);
    dest.writeString(shelf);
    dest.writeString(isCentral);
    dest.writeString(directions);
    dest.writeString(name);
    dest.writeTypedList(attributes);
    dest.writeString(currencyCode);
    dest.writeParcelable(status, flags);
    dest.writeParcelable(shippingDetails, flags);
    dest.writeParcelable(timestamps, flags);
    dest.writeString(invoiceLink);
    dest.writeString(shippingLabel);
    dest.writeString(comment);
    dest.writeString(reason);
    dest.writeParcelable(packaging, flags);
    dest.writeParcelable(singleUnitPrice, flags);
    dest.writeParcelable(sellerSingleUnitPrice, flags);
    dest.writeParcelable(sellerAccounting, flags);
    dest.writeByte((byte) (prescriptionRequired ? 1 : 0));
    dest.writeString(barcode);
    dest.writeByte((byte) (subsitute ? 1 : 0));
    dest.writeParcelable(subsituteWith, flags);
    dest.writeByte((byte) (isSelected ? 1 : 0));
    dest.writeInt(itemType);
    dest.writeInt(prescriptionCount);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Products> CREATOR = new Creator<Products>() {
    @Override
    public Products createFromParcel(Parcel in) {
      return new Products(in);
    }

    @Override
    public Products[] newArray(int size) {
      return new Products[size];
    }
  };

  public String getUpcNumber() {
    return upcNumber;
  }

  public void setUpcNumber(String upcNumber) {
    this.upcNumber = upcNumber;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  /*  public String getNoofunits ()
    {
        return noofunits;
    }

    public void setNoofunits (String noofunits)
    {
        this.noofunits = noofunits;
    }
*/
  public String getProductOrderId() {
    return productOrderId;
  }

  public void setProductOrderId(String productOrderId) {
    this.productOrderId = productOrderId;
  }

  public String getAisle() {
    return aisle;
  }

  public void setAisle(String aisle) {
    this.aisle = aisle;
  }

  public String getPackageType() {
    return packageType;
  }

  public void setPackageType(String packageType) {
    this.packageType = packageType;
  }

  public String getCentralProductId() {
    return centralProductId;
  }

  public void setCentralProductId(String centralProductId) {
    this.centralProductId = centralProductId;
  }

  public Accounting getAccounting() {
    return accounting;
  }

  public void setAccounting(Accounting accounting) {
    this.accounting = accounting;
  }

  public String getUnitId() {
    return unitId;
  }

  public void setUnitId(String unitId) {
    this.unitId = unitId;
  }

  public String getProductDeliveryFee() {
    return productDeliveryFee;
  }

  public void setProductDeliveryFee(String productDeliveryFee) {
    this.productDeliveryFee = productDeliveryFee;
  }

  public Images getImages() {
    return images;
  }

  public void setImages(Images images) {
    this.images = images;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public Quantity getQuantity() {
    return quantity;
  }

  public void setQuantity(Quantity quantity) {
    this.quantity = quantity;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public ArrayList<AddOns> getAddOns() {
    return addOns;
  }

  public void setAddOns(ArrayList<AddOns> addOns) {
    this.addOns = addOns;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public String getShelf() {
    return shelf;
  }

  public void setShelf(String shelf) {
    this.shelf = shelf;
  }

  public String getIsCentral() {
    return isCentral;
  }

  public void setIsCentral(String isCentral) {
    this.isCentral = isCentral;
  }

  public String getDirections() {
    return directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Attributes> getAttributes() {
    return attributes;
  }

  public void setAttributes(ArrayList<Attributes> attributes) {
    this.attributes = attributes;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public ShippingDetails getShippingDetails() {
    return shippingDetails;
  }

  public void setShippingDetails(
      ShippingDetails shippingDetails) {
    this.shippingDetails = shippingDetails;
  }

  public Timestamps getTimestamps() {
    return timestamps;
  }

  public void setTimestamps(Timestamps timestamps) {
    this.timestamps = timestamps;
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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Packaging getPackaging() {
    return packaging;
  }

  public void setPackaging(Packaging packaging) {
    this.packaging = packaging;
  }

  public SingleUnitPrice getSingleUnitPrice() {
    return singleUnitPrice;
  }

  public void setSingleUnitPrice(SingleUnitPrice singleUnitPrice) {
    this.singleUnitPrice = singleUnitPrice;
  }

  public boolean isPrescriptionRequired() {
    return prescriptionRequired;
  }

  public void setPrescriptionRequired(boolean prescriptionRequired) {
    this.prescriptionRequired = prescriptionRequired;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  public int getItemType() {
    return itemType;
  }

  public void setItemType(int itemType) {
    this.itemType = itemType;
  }

  public int getPrescriptionCount() {
    return prescriptionCount;
  }

  public void setPrescriptionCount(int prescriptionCount) {
    this.prescriptionCount = prescriptionCount;
  }

  public subsituteWith getSubsituteWith() {
    return subsituteWith;
  }

  public void setSubsituteWith(
      subsituteWith subsituteWith) {
    this.subsituteWith = subsituteWith;
  }

  public boolean isSubsitute() {
    return subsitute;
  }

  public void setSubsitute(boolean subsitute) {
    this.subsitute = subsitute;
  }

  public SingleUnitPrice getSellerSingleUnitPrice() {
    return sellerSingleUnitPrice;
  }

  public void setSellerSingleUnitPrice(
      SingleUnitPrice sellerSingleUnitPrice) {
    this.sellerSingleUnitPrice = sellerSingleUnitPrice;
  }

  public SingleUnitPrice getSellerAccounting() {
    return sellerAccounting;
  }

  public void setSellerAccounting(SingleUnitPrice sellerAccounting) {
    this.sellerAccounting = sellerAccounting;
  }

  @Override
  public String toString() {
    return "Products{" +
        "upcNumber='" + upcNumber + '\'' +
        ", color='" + color + '\'' +
        ", productOrderId='" + productOrderId + '\'' +
        ", aisle='" + aisle + '\'' +
        ", packageType='" + packageType + '\'' +
        ", centralProductId='" + centralProductId + '\'' +
        ", accounting=" + accounting +
        ", unitId='" + unitId + '\'' +
        ", productDeliveryFee='" + productDeliveryFee + '\'' +
        ", images=" + images +
        ", brandName='" + brandName + '\'' +
        ", quantity=" + quantity +
        ", productId='" + productId + '\'' +
        ", addOns=" + addOns +
        ", currencySymbol='" + currencySymbol + '\'' +
        ", shelf='" + shelf + '\'' +
        ", isCentral='" + isCentral + '\'' +
        ", directions='" + directions + '\'' +
        ", name='" + name + '\'' +
        ", attributes=" + attributes +
        ", currencyCode='" + currencyCode + '\'' +
        ", status=" + status + "reason" + reason + "," +
        '}';
  }
}
