package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.history.model.StoreLogo;
import com.app.ecomstore.boarding.login.model.BillingAddress;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Data implements Parcelable {
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
  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("masterOrderId")
  @Expose
  private String masterOrderId;
  @SerializedName("storeOrderId")
  @Expose
  private String storeOrderId;
  @SerializedName("cartId")
  @Expose
  private String cartId;
  @SerializedName("storeType")
  @Expose
  private Integer storeType;
  @SerializedName("storeTypeMsg")
  @Expose
  private String storeTypeMsg;
  @SerializedName("orderType")
  @Expose
  private Integer orderType;
  @SerializedName("orderTypeMsg")
  @Expose
  private String orderTypeMsg;
  @SerializedName("freeDeliveryLimitPerStore")
  @Expose
  private Integer freeDeliveryLimitPerStore;
  @SerializedName("driverTypeId")
  @Expose
  private Integer driverTypeId;
  @SerializedName("driverType")
  @Expose
  private String driverType;
  @SerializedName("autoDispatch")
  @Expose
  private Boolean autoDispatch;
  @SerializedName("autoAcceptOrders")
  @Expose
  private Boolean autoAcceptOrders;
  @SerializedName("pickupAddress")
  @Expose
  private PickupAddress pickupAddress;
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("storeName")
  @Expose
  private String storeName;
  @SerializedName("storeLogo")
  @Expose
  private StoreLogo storeLogo;
  @SerializedName("storePhone")
  @Expose
  private String storePhone;
  @SerializedName("storeTaxId")
  @Expose
  private String storeTaxId;
  @SerializedName("storeEmail")
  @Expose
  private String storeEmail;
  @SerializedName("poInvoiceLink")
  @Expose
  private String poInvoiceLink;
  @SerializedName("paymentType")
  @Expose
  private Integer paymentType;
  @SerializedName("paymentTypeText")
  @Expose
  private String paymentTypeText;
  @SerializedName("payByWallet")
  @Expose
  private Boolean payByWallet;
  @SerializedName("createdTimeStamp")
  @Expose
  private Integer createdTimeStamp;
  @SerializedName("createdDate")
  @Expose
  private String createdDate;
  @SerializedName("requestedFor")
  @Expose
  private String requestedFor;
  @SerializedName("requestedForTimeStamp")
  @Expose
  private String requestedForTimeStamp;
  @SerializedName("deliveryAddress")
  @Expose
  private DeliveryAddress deliveryAddress;
  @SerializedName("billingAddress")
  @Expose
  private BillingAddress billingAddress;
  @SerializedName("status")
  @Expose
  private Status status;
  @SerializedName("productOrderIds")
  @Expose
  private ArrayList<String> productOrderIds;
  @SerializedName("packageBox")
  @Expose
  private PackageBox packageBox;
  @SerializedName("packageId")
  @Expose
  private String packageId;
  @SerializedName("customerId")
  @Expose
  private String customerId;
  @SerializedName("customerDetails")
  @Expose
  private CustomerDetails customerDetails;
  @SerializedName("extraNote")
  @Expose
  private String extraNote;
  /* @SerializedName("orderImages")
   @Expose
   private List<Object> orderImages = null;*/
   /* @SerializedName("reminderPreference")
    @Expose
    private List<Object> reminderPreference = null;*/
   /* @SerializedName("storePlan")
    @Expose
    private StorePlan storePlan;*/
  @SerializedName("products")
  @Expose
  private ArrayList<HomeProducts> products = null;
  @SerializedName("driverDetails")
  @Expose
  private DriverDetails driverDetails;

  protected Data(Parcel in) {
    id = in.readString();
    masterOrderId = in.readString();
    storeOrderId = in.readString();
    cartId = in.readString();
    if (in.readByte() == 0) {
      storeType = null;
    } else {
      storeType = in.readInt();
    }
    storeTypeMsg = in.readString();
    if (in.readByte() == 0) {
      orderType = null;
    } else {
      orderType = in.readInt();
    }
    orderTypeMsg = in.readString();
    if (in.readByte() == 0) {
      freeDeliveryLimitPerStore = null;
    } else {
      freeDeliveryLimitPerStore = in.readInt();
    }
    if (in.readByte() == 0) {
      driverTypeId = null;
    } else {
      driverTypeId = in.readInt();
    }
    driverType = in.readString();
    byte tmpAutoDispatch = in.readByte();
    autoDispatch = tmpAutoDispatch == 0 ? null : tmpAutoDispatch == 1;
    byte tmpAutoAcceptOrders = in.readByte();
    autoAcceptOrders = tmpAutoAcceptOrders == 0 ? null : tmpAutoAcceptOrders == 1;
    pickupAddress = in.readParcelable(PickupAddress.class.getClassLoader());
    storeId = in.readString();
    storeName = in.readString();
    storePhone = in.readString();
    storeTaxId = in.readString();
    storeEmail = in.readString();
    poInvoiceLink = in.readString();
    if (in.readByte() == 0) {
      paymentType = null;
    } else {
      paymentType = in.readInt();
    }
    paymentTypeText = in.readString();
    byte tmpPayByWallet = in.readByte();
    payByWallet = tmpPayByWallet == 0 ? null : tmpPayByWallet == 1;
    if (in.readByte() == 0) {
      createdTimeStamp = null;
    } else {
      createdTimeStamp = in.readInt();
    }
    createdDate = in.readString();
    requestedFor = in.readString();
    requestedForTimeStamp = in.readString();
    deliveryAddress = in.readParcelable(DeliveryAddress.class.getClassLoader());
    billingAddress = in.readParcelable(BillingAddress.class.getClassLoader());
    status = in.readParcelable(Status.class.getClassLoader());
    productOrderIds = in.createStringArrayList();
    packageBox = in.readParcelable(PackageBox.class.getClassLoader());
    packageId = in.readString();
    customerId = in.readString();
    customerDetails = in.readParcelable(CustomerDetails.class.getClassLoader());
    extraNote = in.readString();
    products = in.createTypedArrayList(HomeProducts.CREATOR);
    driverDetails = in.readParcelable(DriverDetails.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(masterOrderId);
    dest.writeString(storeOrderId);
    dest.writeString(cartId);
    if (storeType == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(storeType);
    }
    dest.writeString(storeTypeMsg);
    if (orderType == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(orderType);
    }
    dest.writeString(orderTypeMsg);
    if (freeDeliveryLimitPerStore == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(freeDeliveryLimitPerStore);
    }
    if (driverTypeId == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(driverTypeId);
    }
    dest.writeString(driverType);
    dest.writeByte((byte) (autoDispatch == null ? 0 : autoDispatch ? 1 : 2));
    dest.writeByte((byte) (autoAcceptOrders == null ? 0 : autoAcceptOrders ? 1 : 2));
    dest.writeParcelable(pickupAddress, flags);
    dest.writeString(storeId);
    dest.writeString(storeName);
    dest.writeString(storePhone);
    dest.writeString(storeTaxId);
    dest.writeString(storeEmail);
    dest.writeString(poInvoiceLink);
    if (paymentType == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(paymentType);
    }
    dest.writeString(paymentTypeText);
    dest.writeByte((byte) (payByWallet == null ? 0 : payByWallet ? 1 : 2));
    if (createdTimeStamp == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(createdTimeStamp);
    }
    dest.writeString(createdDate);
    dest.writeString(requestedFor);
    dest.writeString(requestedForTimeStamp);
    dest.writeParcelable(deliveryAddress, flags);
    dest.writeParcelable(billingAddress, flags);
    dest.writeParcelable(status, flags);
    dest.writeStringList(productOrderIds);
    dest.writeParcelable(packageBox, flags);
    dest.writeString(packageId);
    dest.writeString(customerId);
    dest.writeParcelable(customerDetails, flags);
    dest.writeString(extraNote);
    dest.writeTypedList(products);
    dest.writeParcelable(driverDetails, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  /* @SerializedName("cardDetails")
       @Expose
       private CardDetails cardDetails;
       @SerializedName("walletDetails")
       @Expose
       private WalletDetails walletDetails;
       @SerializedName("accounting")
       @Expose
       private Accounting_ accounting;
    */
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

  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  public Integer getStoreType() {
    return storeType;
  }

  public void setStoreType(Integer storeType) {
    this.storeType = storeType;
  }

  public String getStoreTypeMsg() {
    return storeTypeMsg;
  }

  public void setStoreTypeMsg(String storeTypeMsg) {
    this.storeTypeMsg = storeTypeMsg;
  }

  public Integer getOrderType() {
    return orderType;
  }

  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }

  public String getOrderTypeMsg() {
    return orderTypeMsg;
  }

  public void setOrderTypeMsg(String orderTypeMsg) {
    this.orderTypeMsg = orderTypeMsg;
  }

  public Integer getFreeDeliveryLimitPerStore() {
    return freeDeliveryLimitPerStore;
  }

  public void setFreeDeliveryLimitPerStore(Integer freeDeliveryLimitPerStore) {
    this.freeDeliveryLimitPerStore = freeDeliveryLimitPerStore;
  }

  public Integer getDriverTypeId() {
    return driverTypeId;
  }

  public void setDriverTypeId(Integer driverTypeId) {
    this.driverTypeId = driverTypeId;
  }

  public String getDriverType() {
    return driverType;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public Boolean getAutoDispatch() {
    return autoDispatch;
  }

  public void setAutoDispatch(Boolean autoDispatch) {
    this.autoDispatch = autoDispatch;
  }

  public Boolean getAutoAcceptOrders() {
    return autoAcceptOrders;
  }

  public void setAutoAcceptOrders(Boolean autoAcceptOrders) {
    this.autoAcceptOrders = autoAcceptOrders;
  }

  public PickupAddress getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(PickupAddress pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public StoreLogo getStoreLogo() {
    return storeLogo;
  }

  public void setStoreLogo(StoreLogo storeLogo) {
    this.storeLogo = storeLogo;
  }

  public String getStorePhone() {
    return storePhone;
  }

  public void setStorePhone(String storePhone) {
    this.storePhone = storePhone;
  }

  public String getStoreTaxId() {
    return storeTaxId;
  }

  public void setStoreTaxId(String storeTaxId) {
    this.storeTaxId = storeTaxId;
  }

  public String getStoreEmail() {
    return storeEmail;
  }

  public void setStoreEmail(String storeEmail) {
    this.storeEmail = storeEmail;
  }

  public String getPoInvoiceLink() {
    return poInvoiceLink;
  }

  public void setPoInvoiceLink(String poInvoiceLink) {
    this.poInvoiceLink = poInvoiceLink;
  }

  public Integer getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(Integer paymentType) {
    this.paymentType = paymentType;
  }

  public String getPaymentTypeText() {
    return paymentTypeText;
  }

  public void setPaymentTypeText(String paymentTypeText) {
    this.paymentTypeText = paymentTypeText;
  }

  public Boolean getPayByWallet() {
    return payByWallet;
  }

  public void setPayByWallet(Boolean payByWallet) {
    this.payByWallet = payByWallet;
  }

  public Integer getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(Integer createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getRequestedFor() {
    return requestedFor;
  }

  public void setRequestedFor(String requestedFor) {
    this.requestedFor = requestedFor;
  }

  public String getRequestedForTimeStamp() {
    return requestedForTimeStamp;
  }

  public void setRequestedForTimeStamp(String requestedForTimeStamp) {
    this.requestedForTimeStamp = requestedForTimeStamp;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
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

  public String getExtraNote() {
    return extraNote;
  }

  public void setExtraNote(String extraNote) {
    this.extraNote = extraNote;
  }

  public ArrayList<HomeProducts> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<HomeProducts> products) {
    this.products = products;
  }

  public PackageBox getPackageBox() {
    return packageBox;
  }

  public void setPackageBox(PackageBox packageBox) {
    this.packageBox = packageBox;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public ArrayList<String> getProductOrderIds() {
    return productOrderIds;
  }

  public void setProductOrderIds(ArrayList<String> productOrderIds) {
    this.productOrderIds = productOrderIds;
  }

  public DriverDetails getDriverDetails() {
    return driverDetails;
  }

  public void setDriverDetails(DriverDetails driverDetails) {
    this.driverDetails = driverDetails;
  }
}
