package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class HistoryData implements Parcelable {
  @SerializedName("requestedFor")
  @Expose
  private String requestedFor;
  @SerializedName("deliverySlotId")
  @Expose
  private String deliverySlotId;
  @SerializedName("orderType")
  @Expose
  private int orderType;
  @SerializedName("orderTypeMsg")
  @Expose
  private String orderTypeMsg;
  @SerializedName("accounting")
  @Expose
  private Accounting accounting;
  @SerializedName("requestedForPickupTimeStamp")
  @Expose
  private String requestedForPickupTimeStamp;
  @SerializedName("requestedForPickup")
  @Expose
  private String requestedForPickup;
  @SerializedName("storeTaxId")
  @Expose
  private String storeTaxId;
  @SerializedName("deliveryAddress")
  @Expose
  private DeliveryAddress deliveryAddress;
  @SerializedName("poInvoiceLink")
  @Expose
  private String poInvoiceLink;
  @SerializedName("bookingType")
  @Expose
  private int bookingType;
  @SerializedName("storePhone")
  @Expose
  private String storePhone;
  @SerializedName("reminderPreference")
  @Expose
  private ArrayList<ReminderPreference> reminderPreference;
  @SerializedName("cartId")
  @Expose
  private String cartId;
  @SerializedName("pickupSlotId")
  @Expose
  private String pickupSlotId;
  @SerializedName("bookingTypeText")
  @Expose
  private String bookingTypeText;
  @SerializedName("storeTypeMsg")
  @Expose
  private String storeTypeMsg;
  @SerializedName("storeOrderId")
  @Expose
  private String storeOrderId;
  @SerializedName("autoAcceptOrders")
  @Expose
  private boolean autoAcceptOrders;
  @SerializedName("pickupAddress")
  @Expose
  private PickupAddressHistory pickupAddress;
  @SerializedName("storeEmail")
  @Expose
  private String storeEmail;
  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("status")
  @Expose
  private Status status;
  @SerializedName("walletDetails")
  @Expose
  private WalletDetails walletDetails;
  @SerializedName("paymentTypeText")
  @Expose
  private String paymentTypeText;
  @SerializedName("driverTypeId")
  @Expose
  private int driverTypeId;
  @SerializedName("timestamps")
  @Expose
  private Timestamps timestamps;
  @SerializedName("extraNote")
  @Expose
  private String extraNote;
  @SerializedName("createdTimeStamp")
  @Expose
  private String createdTimeStamp;
  @SerializedName("paymentType")
  @Expose
  private int paymentType;
  @SerializedName("products")
  @Expose
  private ArrayList<ProductsItem> products;
  @SerializedName("requestedForTimeStamp")
  @Expose
  private String requestedForTimeStamp;
  @SerializedName("orderImages")
  @Expose
  private ArrayList<OrderImages> orderImages;
  @SerializedName("autoDispatch")
  @Expose
  private boolean autoDispatch;
  @SerializedName("cardDetails")
  @Expose
  private CardDetails cardDetails;
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
  private int freeDeliveryLimitPerStore;
  @SerializedName("payByWallet")
  @Expose
  private boolean payByWallet;
  @SerializedName("storeType")
  @Expose
  private int storeType;
  @SerializedName("coupon")
  @Expose
  private String coupon;
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("driverType")
  @Expose
  private String driverType;
  @SerializedName("masterOrderId")
  @Expose
  private String masterOrderId;
  @SerializedName("createdDate")
  @Expose
  private String createdDate;
  @SerializedName("storeLogo")
  @Expose
  private StoreLogo storeLogo;
  @SerializedName("billingAddress")
  @Expose
  private BillingAddress billingAddress;
  @SerializedName("storePlan")
  @Expose
  private StorePlan storePlan;

  protected HistoryData(Parcel in) {
    requestedFor = in.readString();
    deliverySlotId = in.readString();
    orderType = in.readInt();
    orderTypeMsg = in.readString();
    requestedForPickupTimeStamp = in.readString();
    requestedForPickup = in.readString();
    storeTaxId = in.readString();
    poInvoiceLink = in.readString();
    bookingType = in.readInt();
    storePhone = in.readString();
    cartId = in.readString();
    pickupSlotId = in.readString();
    bookingTypeText = in.readString();
    storeTypeMsg = in.readString();
    storeOrderId = in.readString();
    autoAcceptOrders = in.readByte() != 0;
    storeEmail = in.readString();
    id = in.readString();
    paymentTypeText = in.readString();
    driverTypeId = in.readInt();
    extraNote = in.readString();
    createdTimeStamp = in.readString();
    paymentType = in.readInt();
    products = in.createTypedArrayList(ProductsItem.CREATOR);
    requestedForTimeStamp = in.readString();
    autoDispatch = in.readByte() != 0;
    customerId = in.readString();
    storeName = in.readString();
    freeDeliveryLimitPerStore = in.readInt();
    payByWallet = in.readByte() != 0;
    storeType = in.readInt();
    coupon = in.readString();
    storeId = in.readString();
    driverType = in.readString();
    masterOrderId = in.readString();
    createdDate = in.readString();
    storeLogo = in.readParcelable(StoreLogo.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(requestedFor);
    dest.writeString(deliverySlotId);
    dest.writeInt(orderType);
    dest.writeString(orderTypeMsg);
    dest.writeString(requestedForPickupTimeStamp);
    dest.writeString(requestedForPickup);
    dest.writeString(storeTaxId);
    dest.writeString(poInvoiceLink);
    dest.writeInt(bookingType);
    dest.writeString(storePhone);
    dest.writeString(cartId);
    dest.writeString(pickupSlotId);
    dest.writeString(bookingTypeText);
    dest.writeString(storeTypeMsg);
    dest.writeString(storeOrderId);
    dest.writeByte((byte) (autoAcceptOrders ? 1 : 0));
    dest.writeString(storeEmail);
    dest.writeString(id);
    dest.writeString(paymentTypeText);
    dest.writeInt(driverTypeId);
    dest.writeString(extraNote);
    dest.writeString(createdTimeStamp);
    dest.writeInt(paymentType);
    dest.writeTypedList(products);
    dest.writeString(requestedForTimeStamp);
    dest.writeByte((byte) (autoDispatch ? 1 : 0));
    dest.writeString(customerId);
    dest.writeString(storeName);
    dest.writeInt(freeDeliveryLimitPerStore);
    dest.writeByte((byte) (payByWallet ? 1 : 0));
    dest.writeInt(storeType);
    dest.writeString(coupon);
    dest.writeString(storeId);
    dest.writeString(driverType);
    dest.writeString(masterOrderId);
    dest.writeString(createdDate);
    dest.writeParcelable(storeLogo, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<HistoryData> CREATOR = new Creator<HistoryData>() {
    @Override
    public HistoryData createFromParcel(Parcel in) {
      return new HistoryData(in);
    }

    @Override
    public HistoryData[] newArray(int size) {
      return new HistoryData[size];
    }
  };

  public String getRequestedFor() {
    return requestedFor;
  }

  public String getDeliverySlotId() {
    return deliverySlotId;
  }

  public int getOrderType() {
    return orderType;
  }

  public String getOrderTypeMsg() {
    return orderTypeMsg;
  }

  public Accounting getAccounting() {
    return accounting;
  }

  public String getRequestedForPickupTimeStamp() {
    return requestedForPickupTimeStamp;
  }

  public String getRequestedForPickup() {
    return requestedForPickup;
  }

  public String getStoreTaxId() {
    return storeTaxId;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public String getPoInvoiceLink() {
    return poInvoiceLink;
  }

  public int getBookingType() {
    return bookingType;
  }

  public String getStorePhone() {
    return storePhone;
  }

  public ArrayList<ReminderPreference> getReminderPreference() {
    return reminderPreference;
  }

  public String getCartId() {
    return cartId;
  }

  public String getPickupSlotId() {
    return pickupSlotId;
  }

  public String getBookingTypeText() {
    return bookingTypeText;
  }

  public String getStoreTypeMsg() {
    return storeTypeMsg;
  }

  public String getStoreOrderId() {
    return storeOrderId;
  }

  public boolean isAutoAcceptOrders() {
    return autoAcceptOrders;
  }

  public PickupAddressHistory getPickupAddress() {
    return pickupAddress;
  }

  public String getStoreEmail() {
    return storeEmail;
  }

  public String getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public WalletDetails getWalletDetails() {
    return walletDetails;
  }

  public String getPaymentTypeText() {
    return paymentTypeText;
  }

  public int getDriverTypeId() {
    return driverTypeId;
  }

  public Timestamps getTimestamps() {
    return timestamps;
  }

  public String getExtraNote() {
    return extraNote;
  }

  public String getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public int getPaymentType() {
    return paymentType;
  }

  public ArrayList<ProductsItem> getProducts() {
    return products;
  }

  public String getRequestedForTimeStamp() {
    return requestedForTimeStamp;
  }

  public ArrayList<OrderImages> getOrderImages() {
    return orderImages;
  }

  public boolean isAutoDispatch() {
    return autoDispatch;
  }

  public CardDetails getCardDetails() {
    return cardDetails;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getStoreName() {
    return storeName;
  }

  public CustomerDetails getCustomerDetails() {
    return customerDetails;
  }

  public int getFreeDeliveryLimitPerStore() {
    return freeDeliveryLimitPerStore;
  }

  public boolean isPayByWallet() {
    return payByWallet;
  }

  public int getStoreType() {
    return storeType;
  }

  public String getCoupon() {
    return coupon;
  }

  public String getStoreId() {
    return storeId;
  }

  public String getDriverType() {
    return driverType;
  }

  public String getMasterOrderId() {
    return masterOrderId;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public StoreLogo getStoreLogo() {
    return storeLogo;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public StorePlan getStorePlan() {
    return storePlan;
  }

}