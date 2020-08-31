package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.history.model.Timestamps;
import com.app.delivxstore.main.home.models.CustomerDetails;
import com.app.delivxstore.main.home.models.DeliveryAddress;
import com.app.delivxstore.main.home.models.DriverDetails;
import com.app.delivxstore.main.home.models.Orders;
import com.app.delivxstore.main.home.models.Sellers;
import com.app.delivxstore.main.home.models.Status;
import com.app.delivxstore.main.home.models.TaxList;
import com.app.ecomstore.boarding.login.model.BillingAddress;
import com.app.ecomstore.printlabel.LabelBags;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Data implements Parcelable {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("masterOrderId")
  @Expose
  private String masterOrderId;
  @SerializedName("storeOrderId")
  @Expose
  private String storeOrderId;
  @SerializedName("storeCategoryId")
  @Expose
  private String storeCategoryId;
  @SerializedName("cartId")
  @Expose
  private String cartId;
  @SerializedName("orderType")
  @Expose
  private Integer orderType;
  @SerializedName("paymentTypeText")
  @Expose
  private String paymentTypeText;
  @SerializedName("orderId")
  @Expose
  private String orderId;
  @SerializedName("createdTimeStamp")
  @Expose
  private String createdTimeStamp;
  @SerializedName("masterOrderValue")
  @Expose
  private String masterOrderValue;
  @SerializedName("orderTypeMsg")
  @Expose
  private String orderTypeMsg;
  @SerializedName("freeDeliveryLimitPerOrder")
  @Expose
  private String freeDeliveryLimitPerOrder;
  @SerializedName("paymentType")
  @Expose
  private String paymentType;
  @SerializedName("masterOrderDeliveryFee")
  @Expose
  private String masterOrderDeliveryFee;
  @SerializedName("deliveryAddress")
  @Expose
  private DeliveryAddress deliveryAddress;
  @SerializedName("customerId")
  @Expose
  private String customerId;
  @SerializedName("customerDetails")
  @Expose
  private CustomerDetails customerDetails;
  @SerializedName("payByWallet")
  @Expose
  private boolean payByWallet;
  @SerializedName("storeType")
  @Expose
  private String storeType;
  @SerializedName("coupon")
  @Expose
  private String coupon;
  @SerializedName("count")
  @Expose
  private Count count;
  @SerializedName("storeOrders")
  @Expose
  private ArrayList<StoreOrders> storeOrders;
  @SerializedName("accounting")
  @Expose
  private Accounting accounting;

  @SerializedName("sellerAccounting")
  @Expose
  private Accounting sellerAccounting;
  @SerializedName("taxList")
  @Expose
  private ArrayList<TaxList> taxList;
  @SerializedName("storeTypeMsg")
  @Expose
  private String storeTypeMsg;
  @SerializedName("createdDate")
  @Expose
  private String createdDate;
  @SerializedName("orders")
  @Expose
  private ArrayList<Orders> orders;
  @SerializedName("billingAddress")
  @Expose
  private BillingAddress billingAddress;
  @SerializedName("sellers")
  @Expose
  private ArrayList<Sellers> sellers;
  @SerializedName("status")
  @Expose
  private Status status;
  @SerializedName("products")
  @Expose
  private ArrayList<Products> products;
  @SerializedName("packingDetails")
  @Expose
  private ArrayList<PackingDetails> packingDetails;
  @SerializedName("poInvoiceLink")
  @Expose
  private String poInvoiceLink;
  @SerializedName("driverDetails")
  @Expose
  private DriverDetails driverDetails;
  @SerializedName("timestamps")
  @Expose
  private Timestamps timestamps;
  @SerializedName("orderImages")
  @Expose
  private ArrayList<String> orderImages;
  @SerializedName("pickupSlotId")
  @Expose
  private String pickupSlotId;
  @SerializedName("deliverySlotId")
  @Expose
  private String deliverySlotId;
  @SerializedName("shippingLabel")
  @Expose
  private String shippingLabel;
  private int confirmedCount;
  @SerializedName("bags")
  @Expose
  private ArrayList<LabelBags> bags;
  @SerializedName("receciptURL")
  @Expose
  private ArrayList<String> receciptURL;
  @SerializedName("reason")
  @Expose
  private String reason;

  protected Data(Parcel in) {
    id = in.readString();
    masterOrderId = in.readString();
    storeOrderId = in.readString();
    storeCategoryId = in.readString();
    cartId = in.readString();
    if (in.readByte() == 0) {
      orderType = null;
    } else {
      orderType = in.readInt();
    }
    paymentTypeText = in.readString();
    orderId = in.readString();
    createdTimeStamp = in.readString();
    masterOrderValue = in.readString();
    orderTypeMsg = in.readString();
    freeDeliveryLimitPerOrder = in.readString();
    paymentType = in.readString();
    masterOrderDeliveryFee = in.readString();
    deliveryAddress = in.readParcelable(DeliveryAddress.class.getClassLoader());
    customerId = in.readString();
    customerDetails = in.readParcelable(CustomerDetails.class.getClassLoader());
    payByWallet = in.readByte() != 0;
    storeType = in.readString();
    coupon = in.readString();
    count = in.readParcelable(Count.class.getClassLoader());
    storeOrders = in.createTypedArrayList(StoreOrders.CREATOR);
    accounting = in.readParcelable(Accounting.class.getClassLoader());
    sellerAccounting = in.readParcelable(Accounting.class.getClassLoader());
    taxList = in.createTypedArrayList(TaxList.CREATOR);
    storeTypeMsg = in.readString();
    createdDate = in.readString();
    orders = in.createTypedArrayList(Orders.CREATOR);
    billingAddress = in.readParcelable(BillingAddress.class.getClassLoader());
    sellers = in.createTypedArrayList(Sellers.CREATOR);
    status = in.readParcelable(Status.class.getClassLoader());
    products = in.createTypedArrayList(Products.CREATOR);
    packingDetails = in.createTypedArrayList(PackingDetails.CREATOR);
    poInvoiceLink = in.readString();
    driverDetails = in.readParcelable(DriverDetails.class.getClassLoader());
    timestamps = in.readParcelable(Timestamps.class.getClassLoader());
    orderImages = in.createStringArrayList();
    pickupSlotId = in.readString();
    deliverySlotId = in.readString();
    shippingLabel = in.readString();
    confirmedCount = in.readInt();
    bags = in.createTypedArrayList(LabelBags.CREATOR);
    receciptURL = in.createStringArrayList();
    reason = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(masterOrderId);
    dest.writeString(storeOrderId);
    dest.writeString(storeCategoryId);
    dest.writeString(cartId);
    if (orderType == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(orderType);
    }
    dest.writeString(paymentTypeText);
    dest.writeString(orderId);
    dest.writeString(createdTimeStamp);
    dest.writeString(masterOrderValue);
    dest.writeString(orderTypeMsg);
    dest.writeString(freeDeliveryLimitPerOrder);
    dest.writeString(paymentType);
    dest.writeString(masterOrderDeliveryFee);
    dest.writeParcelable(deliveryAddress, flags);
    dest.writeString(customerId);
    dest.writeParcelable(customerDetails, flags);
    dest.writeByte((byte) (payByWallet ? 1 : 0));
    dest.writeString(storeType);
    dest.writeString(coupon);
    dest.writeParcelable(count, flags);
    dest.writeTypedList(storeOrders);
    dest.writeParcelable(accounting, flags);
    dest.writeParcelable(sellerAccounting, flags);
    dest.writeTypedList(taxList);
    dest.writeString(storeTypeMsg);
    dest.writeString(createdDate);
    dest.writeTypedList(orders);
    dest.writeParcelable(billingAddress, flags);
    dest.writeTypedList(sellers);
    dest.writeParcelable(status, flags);
    dest.writeTypedList(products);
    dest.writeTypedList(packingDetails);
    dest.writeString(poInvoiceLink);
    dest.writeParcelable(driverDetails, flags);
    dest.writeParcelable(timestamps, flags);
    dest.writeStringList(orderImages);
    dest.writeString(pickupSlotId);
    dest.writeString(deliverySlotId);
    dest.writeString(shippingLabel);
    dest.writeInt(confirmedCount);
    dest.writeTypedList(bags);
    dest.writeStringList(receciptURL);
    dest.writeString(reason);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Data> CREATOR = new Creator<Data>() {
    @Override
    public Data createFromParcel(Parcel in) {
      return new Data(in);
    }

    @Override
    public Data[] newArray(int size) {
      return new Data[size];
    }
  };

  public Integer getOrderType() {
    return orderType;
  }

  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }

  public String getPaymentTypeText() {
    return paymentTypeText;
  }

  public void setPaymentTypeText(String paymentTypeText) {
    this.paymentTypeText = paymentTypeText;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(String createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public String getMasterOrderValue() {
    return masterOrderValue;
  }

  public void setMasterOrderValue(String masterOrderValue) {
    this.masterOrderValue = masterOrderValue;
  }

  public String getOrderTypeMsg() {
    return orderTypeMsg;
  }

  public void setOrderTypeMsg(String orderTypeMsg) {
    this.orderTypeMsg = orderTypeMsg;
  }

  public String getFreeDeliveryLimitPerOrder() {
    return freeDeliveryLimitPerOrder;
  }

  public void setFreeDeliveryLimitPerOrder(String freeDeliveryLimitPerOrder) {
    this.freeDeliveryLimitPerOrder = freeDeliveryLimitPerOrder;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getMasterOrderDeliveryFee() {
    return masterOrderDeliveryFee;
  }

  public void setMasterOrderDeliveryFee(String masterOrderDeliveryFee) {
    this.masterOrderDeliveryFee = masterOrderDeliveryFee;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public CustomerDetails getCustomerDetails() {
    return customerDetails;
  }

  public void setCustomerDetails(CustomerDetails customerDetails) {
    this.customerDetails = customerDetails;
  }

  public boolean getPayByWallet() {
    return payByWallet;
  }

  public String getStoreType() {
    return storeType;
  }

  public void setStoreType(String storeType) {
    this.storeType = storeType;
  }

  public String getCoupon() {
    return coupon;
  }

  public void setCoupon(String coupon) {
    this.coupon = coupon;
  }

  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  public Count getCount() {
    return count;
  }

  public void setCount(Count count) {
    this.count = count;
  }

  public ArrayList<StoreOrders> getStoreOrders() {
    return storeOrders;
  }

  public void setStoreOrders(ArrayList<StoreOrders> storeOrders) {
    this.storeOrders = storeOrders;
  }

  public Accounting getAccounting() {
    return accounting;
  }

  public void setAccounting(Accounting accounting) {
    this.accounting = accounting;
  }

  public ArrayList<TaxList> getTaxList() {
    return taxList;
  }

  public void setTaxList(ArrayList<TaxList> taxList) {
    this.taxList = taxList;
  }

  public String getStoreTypeMsg() {
    return storeTypeMsg;
  }

  public void setStoreTypeMsg(String storeTypeMsg) {
    this.storeTypeMsg = storeTypeMsg;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public ArrayList<Orders> getOrders() {
    return orders;
  }

  public void setOrders(ArrayList<Orders> orders) {
    this.orders = orders;
  }

  public String get_id() {
    return id;
  }

  public void set_id(String _id) {
    this.id = _id;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public ArrayList<Sellers> getSellers() {
    return sellers;
  }

  public void setSellers(ArrayList<Sellers> sellers) {
    this.sellers = sellers;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMasterOrderId() {
    return masterOrderId;
  }

  public void setMasterOrderId(String masterOrderId) {
    this.masterOrderId = masterOrderId;
  }

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public void setStoreOrderId(String storeOrderId) {
    this.storeOrderId = storeOrderId;
  }

  public ArrayList<Products> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Products> products) {
    this.products = products;
  }

  public ArrayList<PackingDetails> getPackingDetails() {
    return packingDetails;
  }

  public void setPackingDetails(
      ArrayList<PackingDetails> packingDetails) {
    this.packingDetails = packingDetails;
  }

  public String getPoInvoiceLink() {
    return poInvoiceLink;
  }

  public void setPoInvoiceLink(String poInvoiceLink) {
    this.poInvoiceLink = poInvoiceLink;
  }

  public boolean isPayByWallet() {
    return payByWallet;
  }

  public void setPayByWallet(boolean payByWallet) {
    this.payByWallet = payByWallet;
  }

  public DriverDetails getDriverDetails() {
    return driverDetails;
  }

  public void setDriverDetails(DriverDetails driverDetails) {
    this.driverDetails = driverDetails;
  }

  public Timestamps getTimestamps() {
    return timestamps;
  }

  public void setTimestamps(Timestamps timestamps) {
    this.timestamps = timestamps;
  }

  public ArrayList<String> getOrderImages() {
    return orderImages;
  }

  public void setOrderImages(ArrayList<String> orderImages) {
    this.orderImages = orderImages;
  }

  public String getStoreCategoryId() {
    return storeCategoryId;
  }

  public void setStoreCategoryId(String storeCategoryId) {
    this.storeCategoryId = storeCategoryId;
  }

  public String getPickupSlotId() {
    return pickupSlotId;
  }

  public void setPickupSlotId(String pickupSlotId) {
    this.pickupSlotId = pickupSlotId;
  }

  public String getDeliverySlotId() {
    return deliverySlotId;
  }

  public void setDeliverySlotId(String deliverySlotId) {
    this.deliverySlotId = deliverySlotId;
  }

  public int getConfirmedCount() {
    return confirmedCount;
  }

  public void setConfirmedCount(int confirmedCount) {
    this.confirmedCount = confirmedCount;
  }

  public ArrayList<LabelBags> getBags() {
    return bags;
  }

  public void setBags(ArrayList<LabelBags> bags) {
    this.bags = bags;
  }

  public ArrayList<String> getRececiptURL() {
    return receciptURL;
  }

  public void setRececiptURL(ArrayList<String> receciptURL) {
    this.receciptURL = receciptURL;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getShippingLabel() {
    return shippingLabel;
  }

  public void setShippingLabel(String shippingLabel) {
    this.shippingLabel = shippingLabel;
  }

  public Accounting getSellerAccounting() {
    return sellerAccounting;
  }

  public void setSellerAccounting(
      Accounting sellerAccounting) {
    this.sellerAccounting = sellerAccounting;
  }
}
