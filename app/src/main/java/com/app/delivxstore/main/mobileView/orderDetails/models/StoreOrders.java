package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.home.models.CustomerDetails;
import com.app.delivxstore.main.home.models.DeliveryAddress;
import com.app.delivxstore.main.home.models.PickupAddress;
import com.app.delivxstore.main.home.models.Status;
import com.app.ecomstore.boarding.login.model.BillingAddress;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class StoreOrders implements Parcelable {
  public static final Creator<StoreOrders> CREATOR = new Creator<StoreOrders>() {
    @Override
    public StoreOrders createFromParcel(Parcel in) {
      return new StoreOrders(in);
    }

    @Override
    public StoreOrders[] newArray(int size) {
      return new StoreOrders[size];
    }
  };
  @SerializedName("orderType")
  @Expose
  private String orderType;
  @SerializedName("paymentTypeText")
  @Expose
  private String paymentTypeText;
  @SerializedName("driverTypeId")
  @Expose
  private String driverTypeId;
  @SerializedName("orderTypeMsg")
  @Expose
  private String createdTimeStamp;
  @SerializedName("paymentType")
  @Expose
  private String paymentType;
  @SerializedName("products")
  @Expose
  private ArrayList<Products> products;
  @SerializedName("storeOrderValue")
  @Expose
  private String storeOrderValue;
  @SerializedName("autoDispatch")
  @Expose
  private String autoDispatch;
  @SerializedName("deliveryAddress")
  @Expose
  private DeliveryAddress deliveryAddress;
  /*private StorePhone storePhone;*/
  @SerializedName("customerId")
  @Expose
  private String customerId;
  @SerializedName("storeName")
  @Expose
  private String storeName;
  @SerializedName("customerDetails")
  @Expose
  private CustomerDetails customerDetails;
  @SerializedName("freeDeliveryLimitPerStore")
  @Expose
  private String freeDeliveryLimitPerStore;
  @SerializedName("payByWallet")
  @Expose
  private String payByWallet;
  @SerializedName("storeType")
  @Expose
  private String storeType;
  @SerializedName("storeDeliveryFee")
  @Expose
  private String storeDeliveryFee;
  @SerializedName("cartId")
  @Expose
  private String cartId;
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("driverType")
  @Expose
  private String driverType;
  @SerializedName("masterOrderId")
  @Expose
  private String masterOrderId;
  @SerializedName("packingDetails")
  @Expose
  private String[] packingDetails;
  @SerializedName("storeTypeMsg")
  @Expose
  private String storeTypeMsg;
  @SerializedName("createdDate")
  @Expose
  private String createdDate;
  @SerializedName("storeOrderId")
  @Expose
  private String storeOrderId;
  @SerializedName("autoAcceptOrders")
  @Expose
  private String autoAcceptOrders;
  @SerializedName("pickupAddress")
  @Expose
  private PickupAddress pickupAddress;
  /*private StoreLogo storeLogo;*/
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("billingAddress")
  @Expose
  private BillingAddress billingAddress;
  @SerializedName("storeLevelAccounting")
  @Expose
  private StoreLevelAccounting storeLevelAccounting;
  @SerializedName("status")
  @Expose
  private Status status;

  protected StoreOrders(Parcel in) {
    orderType = in.readString();
    paymentTypeText = in.readString();
    driverTypeId = in.readString();
    createdTimeStamp = in.readString();
    paymentType = in.readString();
    products = in.createTypedArrayList(Products.CREATOR);
    storeOrderValue = in.readString();
    autoDispatch = in.readString();
    deliveryAddress = in.readParcelable(DeliveryAddress.class.getClassLoader());
    customerId = in.readString();
    storeName = in.readString();
    customerDetails = in.readParcelable(CustomerDetails.class.getClassLoader());
    freeDeliveryLimitPerStore = in.readString();
    payByWallet = in.readString();
    storeType = in.readString();
    storeDeliveryFee = in.readString();
    cartId = in.readString();
    storeId = in.readString();
    driverType = in.readString();
    masterOrderId = in.readString();
    packingDetails = in.createStringArray();
    storeTypeMsg = in.readString();
    createdDate = in.readString();
    storeOrderId = in.readString();
    autoAcceptOrders = in.readString();
    pickupAddress = in.readParcelable(PickupAddress.class.getClassLoader());
    _id = in.readString();
    billingAddress = in.readParcelable(BillingAddress.class.getClassLoader());
    status = in.readParcelable(Status.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(orderType);
    dest.writeString(paymentTypeText);
    dest.writeString(driverTypeId);
    dest.writeString(createdTimeStamp);
    dest.writeString(paymentType);
    dest.writeTypedList(products);
    dest.writeString(storeOrderValue);
    dest.writeString(autoDispatch);
    dest.writeParcelable(deliveryAddress, flags);
    dest.writeString(customerId);
    dest.writeString(storeName);
    dest.writeParcelable(customerDetails, flags);
    dest.writeString(freeDeliveryLimitPerStore);
    dest.writeString(payByWallet);
    dest.writeString(storeType);
    dest.writeString(storeDeliveryFee);
    dest.writeString(cartId);
    dest.writeString(storeId);
    dest.writeString(driverType);
    dest.writeString(masterOrderId);
    dest.writeStringArray(packingDetails);
    dest.writeString(storeTypeMsg);
    dest.writeString(createdDate);
    dest.writeString(storeOrderId);
    dest.writeString(autoAcceptOrders);
    dest.writeParcelable(pickupAddress, flags);
    dest.writeString(_id);
    dest.writeParcelable(billingAddress, flags);
    dest.writeParcelable(status, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getPaymentTypeText() {
    return paymentTypeText;
  }

  public void setPaymentTypeText(String paymentTypeText) {
    this.paymentTypeText = paymentTypeText;
  }

  public String getDriverTypeId() {
    return driverTypeId;
  }

  public void setDriverTypeId(String driverTypeId) {
    this.driverTypeId = driverTypeId;
  }

  public String getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(String createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public ArrayList<Products> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Products> products) {
    this.products = products;
  }

  public String getStoreOrderValue() {
    return storeOrderValue;
  }

  public void setStoreOrderValue(String storeOrderValue) {
    this.storeOrderValue = storeOrderValue;
  }

  public String getAutoDispatch() {
    return autoDispatch;
  }

  public void setAutoDispatch(String autoDispatch) {
    this.autoDispatch = autoDispatch;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

    /*public StorePhone getStorePhone ()
    {
        return storePhone;
    }

    public void setStorePhone (StorePhone storePhone)
    {
        this.storePhone = storePhone;
    }*/

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public CustomerDetails getCustomerDetails() {
    return customerDetails;
  }

  public void setCustomerDetails(CustomerDetails customerDetails) {
    this.customerDetails = customerDetails;
  }

  public String getFreeDeliveryLimitPerStore() {
    return freeDeliveryLimitPerStore;
  }

  public void setFreeDeliveryLimitPerStore(String freeDeliveryLimitPerStore) {
    this.freeDeliveryLimitPerStore = freeDeliveryLimitPerStore;
  }

  public String getPayByWallet() {
    return payByWallet;
  }

  public void setPayByWallet(String payByWallet) {
    this.payByWallet = payByWallet;
  }

  public String getStoreType() {
    return storeType;
  }

  public void setStoreType(String storeType) {
    this.storeType = storeType;
  }

  public String getStoreDeliveryFee() {
    return storeDeliveryFee;
  }

  public void setStoreDeliveryFee(String storeDeliveryFee) {
    this.storeDeliveryFee = storeDeliveryFee;
  }

  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getDriverType() {
    return driverType;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public String getMasterOrderId() {
    return masterOrderId;
  }

  public void setMasterOrderId(String masterOrderId) {
    this.masterOrderId = masterOrderId;
  }

  public String[] getPackingDetails() {
    return packingDetails;
  }

  public void setPackingDetails(String[] packingDetails) {
    this.packingDetails = packingDetails;
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

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public void setStoreOrderId(String storeOrderId) {
    this.storeOrderId = storeOrderId;
  }

  public String getAutoAcceptOrders() {
    return autoAcceptOrders;
  }

  public void setAutoAcceptOrders(String autoAcceptOrders) {
    this.autoAcceptOrders = autoAcceptOrders;
  }

  public PickupAddress getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(PickupAddress pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

    /*public StoreLogo getStoreLogo ()
    {
        return storeLogo;
    }

    public void setStoreLogo (StoreLogo storeLogo)
    {
        this.storeLogo = storeLogo;
    }*/

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public StoreLevelAccounting getStoreLevelAccounting() {
    return storeLevelAccounting;
  }

  public void setStoreLevelAccounting(StoreLevelAccounting storeLevelAccounting) {
    this.storeLevelAccounting = storeLevelAccounting;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
