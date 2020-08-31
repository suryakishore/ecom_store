package com.app.delivxstore.main.home.model;

import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.ActivityLogs;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.CoinpayTransaction;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DispatchSetting;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Drop;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.ExclusiveTaxes;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PaidBy;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.StoreCoordinates;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.TimeStamp;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDetails implements Serializable{

    private String zoneType;

    private String estimateId;
    private String cartTotal;
    private String cartDiscount;
    private String storeLogo;
    private String storeName;
    private String franchiseId;
    private String franchiseName;
    private String storeCommissionType;
    private String storeType;
    private String storeTypeMsg;
    private String storePhone;
    private String storeCommissionTypeMsg;
    private String storeFreeDelivery;
    private String subTotalAmountWithExcTax;
    private String storeDeliveryFee;
    private String excTax;
    private String orderType;
    private String orderTypeMsg;
    private String paymentTypeMsg;
    private String bookingDateIsoDate;
    private String zoneId;
    private StoreCoordinates storeCoordinates;
    private ArrayList<ExclusiveTaxes> exclusiveTaxes;
    private ArrayList<Items> Items;
    private CoinpayTransaction coinpayTransaction;
    private boolean isSelected;
    private int itemType;
    private boolean inDispatch;
    private String inDispatchMsg;

    public boolean isIncreaseTimer() {
        return isIncreaseTimer;
    }

    public void setIncreaseTimer(boolean increaseTimer) {
        isIncreaseTimer = increaseTimer;
    }

    private boolean isIncreaseTimer;

    public String getInDispatchMsg() {
        return inDispatchMsg;
    }

    public void setInDispatchMsg(String inDispatchMsg) {
        this.inDispatchMsg = inDispatchMsg;
    }

    public boolean isInDispatch() {
        return inDispatch;
    }

    public void setInDispatch(boolean inDispatch) {
        this.inDispatch = inDispatch;
    }
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }

    public String getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(String cartTotal) {
        this.cartTotal = cartTotal;
    }

    public String getCartDiscount() {
        return cartDiscount;
    }

    public void setCartDiscount(String cartDiscount) {
        this.cartDiscount = cartDiscount;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(String franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

//    public String getStoreCommission() {
//        return storeCommission;
//    }
//
//    public void setStoreCommission(String storeCommission) {
//        this.storeCommission = storeCommission;
//    }

    public String getStoreCommissionType() {
        return storeCommissionType;
    }

    public void setStoreCommissionType(String storeCommissionType) {
        this.storeCommissionType = storeCommissionType;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStoreTypeMsg() {
        return storeTypeMsg;
    }

    public void setStoreTypeMsg(String storeTypeMsg) {
        this.storeTypeMsg = storeTypeMsg;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreCommissionTypeMsg() {
        return storeCommissionTypeMsg;
    }

    public void setStoreCommissionTypeMsg(String storeCommissionTypeMsg) {
        this.storeCommissionTypeMsg = storeCommissionTypeMsg;
    }

    public String getStoreFreeDelivery() {
        return storeFreeDelivery;
    }

    public void setStoreFreeDelivery(String storeFreeDelivery) {
        this.storeFreeDelivery = storeFreeDelivery;
    }

    public String getSubTotalAmountWithExcTax() {
        return subTotalAmountWithExcTax;
    }

    public void setSubTotalAmountWithExcTax(String subTotalAmountWithExcTax) {
        this.subTotalAmountWithExcTax = subTotalAmountWithExcTax;
    }

    public String getStoreDeliveryFee() {
        return storeDeliveryFee;
    }

    public void setStoreDeliveryFee(String storeDeliveryFee) {
        this.storeDeliveryFee = storeDeliveryFee;
    }

    public String getExcTax() {
        return excTax;
    }

    public void setExcTax(String excTax) {
        this.excTax = excTax;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeMsg() {
        return orderTypeMsg;
    }

    public void setOrderTypeMsg(String orderTypeMsg) {
        this.orderTypeMsg = orderTypeMsg;
    }

    /*public boolean isPayByWallet() {
        return payByWallet;
    }

    public void setPayByWallet(boolean payByWallet) {
        this.payByWallet = payByWallet;
    }*/

    public String getPaymentTypeMsg() {
        return paymentTypeMsg;
    }

    public void setPaymentTypeMsg(String paymentTypeMsg) {
        this.paymentTypeMsg = paymentTypeMsg;
    }

    public String getBookingDateIsoDate() {
        return bookingDateIsoDate;
    }

    public void setBookingDateIsoDate(String bookingDateIsoDate) {
        this.bookingDateIsoDate = bookingDateIsoDate;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public StoreCoordinates getStoreCoordinates() {
        return storeCoordinates;
    }

    public void setStoreCoordinates(StoreCoordinates storeCoordinates) {
        this.storeCoordinates = storeCoordinates;
    }

    public ArrayList<ExclusiveTaxes> getExclusiveTaxes() {
        return exclusiveTaxes;
    }

    public void setExclusiveTaxes(ArrayList<ExclusiveTaxes> exclusiveTaxes) {
        this.exclusiveTaxes = exclusiveTaxes;
    }

    public ArrayList<com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items> getItems() {
        return Items;
    }

    public void setItems(ArrayList<com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items> items) {
        Items = items;
    }

    public CoinpayTransaction getCoinpayTransaction() {
        return coinpayTransaction;
    }

    public void setCoinpayTransaction(CoinpayTransaction coinpayTransaction) {
        this.coinpayTransaction = coinpayTransaction;
    }

    public StoreCoordinates getCustomerCoordinates() {
        return customerCoordinates;
    }

    public void setCustomerCoordinates(StoreCoordinates customerCoordinates) {
        this.customerCoordinates = customerCoordinates;
    }

    public DispatchSetting getDispatchSetting() {
        return dispatchSetting;
    }

    public void setDispatchSetting(DispatchSetting dispatchSetting) {
        this.dispatchSetting = dispatchSetting;
    }

    public Drop getPickup() {
        return pickup;
    }

    public void setPickup(Drop pickup) {
        this.pickup = pickup;
    }

    public Drop getDrop() {
        return drop;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ArrayList<ActivityLogs> getActivityLogs() {
        return activityLogs;
    }

    public void setActivityLogs(ArrayList<ActivityLogs> activityLogs) {
        this.activityLogs = activityLogs;
    }

    public PaidBy getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(PaidBy paidBy) {
        this.paidBy = paidBy;
    }

//    public Accouting getAccouting() {
//        return accouting;
//    }
//
//    public void setAccouting(Accouting accouting) {
//        this.accouting = accouting;
//    }

    private StoreCoordinates customerCoordinates;
    private DispatchSetting dispatchSetting;

    private Drop pickup;
    private Drop drop;
    private TimeStamp timeStamp;
    private ArrayList<ActivityLogs> activityLogs;
    private PaidBy paidBy;
//    private Accouting accouting;



    private String cityId;

    private String bookingType;

    private String cartId;

    private String subTotalAmount;

    private String pricingModel;

    private String dueDatetime;

    private String city;

    private String currency;

    private String _id;

    private String totalAmount;

    private CustomerDetails customerDetails;

    private String extraNote;

    private String orderId;

    private String mileageMetric;

    private String driverType;

    private String bookingDate;

    private String status;

    private String paymentType;

    private String storeAddress;

    private String couponCode;

    private String forcedAccept;

    private String statusMsg;

    private String serviceType;

    private String deliveryCharge;

    private String discount;

    private String currencySymbol;

    private String storeId;

    private long bookingDateTimeStamp;

    private String dueDatetimeTimeStamp;

    public long getBookingDateTimeStamp() {
        return bookingDateTimeStamp;
    }

    public void setBookingDateTimeStamp(long bookingDateTimeStamp) {
        this.bookingDateTimeStamp = bookingDateTimeStamp;
    }

    public String getDueDatetimeTimeStamp() {
        return dueDatetimeTimeStamp;
    }

    public void setDueDatetimeTimeStamp(String dueDatetimeTimeStamp) {
        this.dueDatetimeTimeStamp = dueDatetimeTimeStamp;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(String subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public String getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(String pricingModel) {
        this.pricingModel = pricingModel;
    }

    public String getDueDatetime() {
        return dueDatetime;
    }

    public void setDueDatetime(String dueDatetime) {
        this.dueDatetime = dueDatetime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMileageMetric() {
        return mileageMetric;
    }

    public void setMileageMetric(String mileageMetric) {
        this.mileageMetric = mileageMetric;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getForcedAccept() {
        return forcedAccept;
    }

    public void setForcedAccept(String forcedAccept) {
        this.forcedAccept = forcedAccept;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

}
