package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderedItemDetails implements Serializable {

    private String cartTotal;

    private ArrayList<ManagerLogs> managerLogs;

    private StoreCoordinates storeCoordinates;

    public boolean isIncreaseTimer() {
        return isIncreaseTimer;
    }

    public void setIncreaseTimer(boolean increaseTimer) {
        isIncreaseTimer = increaseTimer;
    }

    private boolean isIncreaseTimer;
    private String orderTypeMsg;
     private boolean isLoadingItem;

    public boolean isLoadingItem() {
        return isLoadingItem;
    }

    public void setLoadingItem(boolean loadingItem) {
        isLoadingItem = loadingItem;
    }

    private String paymentTypeMsg;

    private String cityId;
    private String weight;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private Drop drop;

    private StoreCoordinates customerCoordinates;

    private String bookingType;

    private String subTotalAmountWithExcTax;

    private String storeLogo;

    private String dueDatetime;

    private String city;

    private String storeFreeDelivery;

    private String dueDatetimeTimeStamp;

    private String storeTypeMsg;

    private CustomerDetails customerDetails;

    private String dispatchCount;

    private ArrayList<Activities> activities;

    private String extraNote;

    private String masterPushTopic;

    private String driverType;

    private Drop pickup;

    private String isPopupShowing;

    private Accouting accouting;

    private String orderType;

    private String status;

    private String forcedAccept;

    private String cartDiscount;

    private String serviceType;

    private String deliveryCharge;

    private String discount;

    private DispatchSetting dispatchSetting;

    private ArrayList<Dispatched> dispatched;

    private String bookingShowingPopup;

    private String lg;

    private String excTax;

    private String storeType;

    private DriverDetails driverDetails;

    private String storeId;

    private String zoneType;
    private TimeStamp timeStamp;

    private String subTotalAmount;

    private String cartId;

    private String lt;

    private String pricingModel;

    private String currency;

    private String estimateId;

    private String _id;

    private String driverId;

    private String totalAmount;

    private String abbrevationText;

    private PaidBy paidBy;

    private CoinpayTransaction coinpayTransaction;

    private String orderId;

    private String storeCommissionTypeMsg;

    private ArrayList<Items> Items;

    private String mileageMetric;

    private String bookingDate;

    private String storeName;

    private String abbrevation;

    private String storeDeliveryFee;

    private String paymentType;
    private String cardDeliveryPaymentType;

    public String getCardDeliveryPaymentType() {
        return cardDeliveryPaymentType;
    }

    public void setCardDeliveryPaymentType(String cardDeliveryPaymentType) {
        this.cardDeliveryPaymentType = cardDeliveryPaymentType;
    }

    public String getPayByWallet() {
        return payByWallet;
    }

    public void setPayByWallet(String payByWallet) {
        this.payByWallet = payByWallet;
    }

    private String payByWallet;

    private ArrayList<ExclusiveTaxes> exclusiveTaxes;

    private String storeAddress;

    private String couponCode;

    private String statusMsg;

    private boolean inDispatch;

    private String storeCommissionType;

    private String bookingDateTimeStamp;

    private String currencySymbol;

    private String storeCommission;

    private ArrayList<ActivityLogs> activityLogs;


    public String getCartTotal ()
    {
        return cartTotal;
    }

    public void setCartTotal (String cartTotal)
    {
        this.cartTotal = cartTotal;
    }

    public ArrayList<ManagerLogs> getManagerLogs ()
    {
        return managerLogs;
    }

    public void setManagerLogs (ArrayList<ManagerLogs> managerLogs)
    {
        this.managerLogs = managerLogs;
    }

    public StoreCoordinates getStoreCoordinates ()
    {
        return storeCoordinates;
    }

    public void setStoreCoordinates (StoreCoordinates storeCoordinates)
    {
        this.storeCoordinates = storeCoordinates;
    }

    public String getOrderTypeMsg ()
    {
        return orderTypeMsg;
    }

    public void setOrderTypeMsg (String orderTypeMsg)
    {
        this.orderTypeMsg = orderTypeMsg;
    }

    public String getPaymentTypeMsg ()
    {
        return paymentTypeMsg;
    }

    public void setPaymentTypeMsg (String paymentTypeMsg)
    {
        this.paymentTypeMsg = paymentTypeMsg;
    }

    public String getCityId ()
    {
        return cityId;
    }

    public void setCityId (String cityId)
    {
        this.cityId = cityId;
    }

    public Drop getDrop ()
    {
        return drop;
    }

    public void setDrop (Drop drop)
    {
        this.drop = drop;
    }

    public StoreCoordinates getCustomerCoordinates ()
    {
        return customerCoordinates;
    }

    public void setCustomerCoordinates (StoreCoordinates customerCoordinates)
    {
        this.customerCoordinates = customerCoordinates;
    }

    public String getBookingType ()
    {
        return bookingType;
    }

    public void setBookingType (String bookingType)
    {
        this.bookingType = bookingType;
    }

    public String getSubTotalAmountWithExcTax ()
    {
        return subTotalAmountWithExcTax;
    }

    public void setSubTotalAmountWithExcTax (String subTotalAmountWithExcTax)
    {
        this.subTotalAmountWithExcTax = subTotalAmountWithExcTax;
    }

    public String getStoreLogo ()
    {
        return storeLogo;
    }

    public void setStoreLogo (String storeLogo)
    {
        this.storeLogo = storeLogo;
    }

    public String getDueDatetime ()
    {
        return dueDatetime;
    }

    public void setDueDatetime (String dueDatetime)
    {
        this.dueDatetime = dueDatetime;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getStoreFreeDelivery ()
    {
        return storeFreeDelivery;
    }

    public void setStoreFreeDelivery (String storeFreeDelivery)
    {
        this.storeFreeDelivery = storeFreeDelivery;
    }

    public String getDueDatetimeTimeStamp ()
    {
        return dueDatetimeTimeStamp;
    }

    public void setDueDatetimeTimeStamp (String dueDatetimeTimeStamp)
    {
        this.dueDatetimeTimeStamp = dueDatetimeTimeStamp;
    }

    public String getStoreTypeMsg ()
    {
        return storeTypeMsg;
    }

    public void setStoreTypeMsg (String storeTypeMsg)
    {
        this.storeTypeMsg = storeTypeMsg;
    }

    public CustomerDetails getCustomerDetails ()
    {
        return customerDetails;
    }

    public void setCustomerDetails (CustomerDetails customerDetails)
    {
        this.customerDetails = customerDetails;
    }

    public String getDispatchCount ()
    {
        return dispatchCount;
    }

    public void setDispatchCount (String dispatchCount)
    {
        this.dispatchCount = dispatchCount;
    }

    public ArrayList<Activities> getActivities ()
    {
        return activities;
    }

    public void setActivities (ArrayList<Activities> activities)
    {
        this.activities = activities;
    }

    public String getExtraNote ()
    {
        return extraNote;
    }

    public void setExtraNote (String extraNote)
    {
        this.extraNote = extraNote;
    }

    public String getMasterPushTopic ()
    {
        return masterPushTopic;
    }

    public void setMasterPushTopic (String masterPushTopic)
    {
        this.masterPushTopic = masterPushTopic;
    }

    public String getDriverType ()
    {
        return driverType;
    }

    public void setDriverType (String driverType)
    {
        this.driverType = driverType;
    }

    public Drop getPickup ()
    {
        return pickup;
    }

    public void setPickup (Drop pickup)
    {
        this.pickup = pickup;
    }

    public String getIsPopupShowing ()
    {
        return isPopupShowing;
    }

    public void setIsPopupShowing (String isPopupShowing)
    {
        this.isPopupShowing = isPopupShowing;
    }

    public Accouting getAccouting ()
    {
        return accouting;
    }

    public void setAccouting (Accouting accouting)
    {
        this.accouting = accouting;
    }

    public String getOrderType ()
    {
        return orderType;
    }

    public void setOrderType (String orderType)
    {
        this.orderType = orderType;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getForcedAccept ()
    {
        return forcedAccept;
    }

    public void setForcedAccept (String forcedAccept)
    {
        this.forcedAccept = forcedAccept;
    }

    public String getCartDiscount ()
    {
        return cartDiscount;
    }

    public void setCartDiscount (String cartDiscount)
    {
        this.cartDiscount = cartDiscount;
    }

    public String getServiceType ()
    {
        return serviceType;
    }

    public void setServiceType (String serviceType)
    {
        this.serviceType = serviceType;
    }

    public String getDeliveryCharge ()
    {
        return deliveryCharge;
    }

    public void setDeliveryCharge (String deliveryCharge)
    {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDiscount ()
    {
        return discount;
    }

    public void setDiscount (String discount)
    {
        this.discount = discount;
    }

    public DispatchSetting getDispatchSetting ()
    {
        return dispatchSetting;
    }

    public void setDispatchSetting (DispatchSetting dispatchSetting)
    {
        this.dispatchSetting = dispatchSetting;
    }

    public ArrayList<Dispatched> getDispatched ()
    {
        return dispatched;
    }

    public void setDispatched (ArrayList<Dispatched> dispatched)
    {
        this.dispatched = dispatched;
    }

    public String getBookingShowingPopup ()
    {
        return bookingShowingPopup;
    }

    public void setBookingShowingPopup (String bookingShowingPopup)
    {
        this.bookingShowingPopup = bookingShowingPopup;
    }

    public String getLg ()
    {
        return lg;
    }

    public void setLg (String lg)
    {
        this.lg = lg;
    }

    public String getExcTax ()
    {
        return excTax;
    }

    public void setExcTax (String excTax)
    {
        this.excTax = excTax;
    }

    public String getStoreType ()
    {
        return storeType;
    }

    public void setStoreType (String storeType)
    {
        this.storeType = storeType;
    }

    public DriverDetails getDriverDetails ()
    {
        return driverDetails;
    }

    public void setDriverDetails (DriverDetails driverDetails)
    {
        this.driverDetails = driverDetails;
    }

    public String getStoreId ()
    {
        return storeId;
    }

    public void setStoreId (String storeId)
    {
        this.storeId = storeId;
    }

    public String getZoneType ()
    {
        return zoneType;
    }

    public void setZoneType (String zoneType)
    {
        this.zoneType = zoneType;
    }

    public TimeStamp getTimeStamp ()
    {
        return timeStamp;
    }

    public void setTimeStamp (TimeStamp timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getSubTotalAmount ()
    {
        return subTotalAmount;
    }

    public void setSubTotalAmount (String subTotalAmount)
    {
        this.subTotalAmount = subTotalAmount;
    }

    public String getCartId ()
    {
        return cartId;
    }

    public void setCartId (String cartId)
    {
        this.cartId = cartId;
    }

    public String getLt ()
    {
        return lt;
    }

    public void setLt (String lt)
    {
        this.lt = lt;
    }

    public String getPricingModel ()
    {
        return pricingModel;
    }

    public void setPricingModel (String pricingModel)
    {
        this.pricingModel = pricingModel;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getEstimateId ()
    {
        return estimateId;
    }

    public void setEstimateId (String estimateId)
    {
        this.estimateId = estimateId;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getDriverId ()
    {
        return driverId;
    }

    public void setDriverId (String driverId)
    {
        this.driverId = driverId;
    }

    public String getTotalAmount ()
    {
        return totalAmount;
    }

    public void setTotalAmount (String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getAbbrevationText ()
    {
        return abbrevationText;
    }

    public void setAbbrevationText (String abbrevationText)
    {
        this.abbrevationText = abbrevationText;
    }

    public PaidBy getPaidBy ()
    {
        return paidBy;
    }

    public void setPaidBy (PaidBy paidBy)
    {
        this.paidBy = paidBy;
    }

    public CoinpayTransaction getCoinpayTransaction ()
    {
        return coinpayTransaction;
    }

    public void setCoinpayTransaction (CoinpayTransaction coinpayTransaction)
    {
        this.coinpayTransaction = coinpayTransaction;
    }

    public String getOrderId ()
    {
        return orderId;
    }

    public void setOrderId (String orderId)
    {
        this.orderId = orderId;
    }

    public String getStoreCommissionTypeMsg ()
    {
        return storeCommissionTypeMsg;
    }

    public void setStoreCommissionTypeMsg (String storeCommissionTypeMsg)
    {
        this.storeCommissionTypeMsg = storeCommissionTypeMsg;
    }

    public ArrayList<Items> getItems ()
    {
        return Items;
    }

    public void setItems (ArrayList<Items> Items)
    {
        this.Items = Items;
    }

    public String getMileageMetric ()
    {
        return mileageMetric;
    }

    public void setMileageMetric (String mileageMetric)
    {
        this.mileageMetric = mileageMetric;
    }

    public String getBookingDate ()
    {
        return bookingDate;
    }

    public void setBookingDate (String bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    public String getStoreName ()
    {
        return storeName;
    }

    public void setStoreName (String storeName)
    {
        this.storeName = storeName;
    }

    public String getAbbrevation ()
    {
        return abbrevation;
    }

    public void setAbbrevation (String abbrevation)
    {
        this.abbrevation = abbrevation;
    }

    public String getStoreDeliveryFee ()
    {
        return storeDeliveryFee;
    }

    public void setStoreDeliveryFee (String storeDeliveryFee)
    {
        this.storeDeliveryFee = storeDeliveryFee;
    }

    public String getPaymentType ()
    {
        return paymentType;
    }

    public void setPaymentType (String paymentType)
    {
        this.paymentType = paymentType;
    }

    public ArrayList<ExclusiveTaxes> getExclusiveTaxes ()
    {
        return exclusiveTaxes;
    }

    public void setExclusiveTaxes (ArrayList<ExclusiveTaxes> exclusiveTaxes)
    {
        this.exclusiveTaxes = exclusiveTaxes;
    }

    public String getStoreAddress ()
    {
        return storeAddress;
    }

    public void setStoreAddress (String storeAddress)
    {
        this.storeAddress = storeAddress;
    }

    public String getCouponCode ()
    {
        return couponCode;
    }

    public void setCouponCode (String couponCode)
    {
        this.couponCode = couponCode;
    }

    public String getStatusMsg ()
    {
        return statusMsg;
    }

    public void setStatusMsg (String statusMsg)
    {
        this.statusMsg = statusMsg;
    }

    public boolean isInDispatch ()
    {
        return inDispatch;
    }

    public void setInDispatch (boolean inDispatch)
    {
        this.inDispatch = inDispatch;
    }

    public String getStoreCommissionType ()
    {
        return storeCommissionType;
    }

    public void setStoreCommissionType (String storeCommissionType)
    {
        this.storeCommissionType = storeCommissionType;
    }

    public String getBookingDateTimeStamp ()
    {
        return bookingDateTimeStamp;
    }

    public void setBookingDateTimeStamp (String bookingDateTimeStamp)
    {
        this.bookingDateTimeStamp = bookingDateTimeStamp;
    }

    public String getCurrencySymbol ()
    {
        return currencySymbol;
    }

    public void setCurrencySymbol (String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
    }

    public String getStoreCommission ()
    {
        return storeCommission;
    }

    public void setStoreCommission (String storeCommission)
    {
        this.storeCommission = storeCommission;
    }

    public ArrayList<ActivityLogs> getActivityLogs ()
    {
        return activityLogs;
    }

    public void setActivityLogs (ArrayList<ActivityLogs> activityLogs)
    {
        this.activityLogs = activityLogs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cartTotal = "+cartTotal+", managerLogs = "+managerLogs+", storeCoordinates = "+storeCoordinates+", orderTypeMsg = "+orderTypeMsg+", paymentTypeMsg = "+paymentTypeMsg+", cityId = "+cityId+", drop = "+drop+", customerCoordinates = "+customerCoordinates+", bookingType = "+bookingType+", subTotalAmountWithExcTax = "+subTotalAmountWithExcTax+", storeLogo = "+storeLogo+", dueDatetime = "+dueDatetime+", city = "+city+", storeFreeDelivery = "+storeFreeDelivery+", dueDatetimeTimeStamp = "+dueDatetimeTimeStamp+", storeTypeMsg = "+storeTypeMsg+", customerDetails = "+customerDetails+", dispatchCount = "+dispatchCount+", activities = "+activities+", extraNote = "+extraNote+", masterPushTopic = "+masterPushTopic+", driverType = "+driverType+", pickup = "+pickup+", isPopupShowing = "+isPopupShowing+", accouting = "+accouting+", orderType = "+orderType+", status = "+status+", forcedAccept = "+forcedAccept+", cartDiscount = "+cartDiscount+", serviceType = "+serviceType+", deliveryCharge = "+deliveryCharge+", discount = "+discount+", dispatchSetting = "+dispatchSetting+", dispatched = "+dispatched+", bookingShowingPopup = "+bookingShowingPopup+", lg = "+lg+", excTax = "+excTax+", storeType = "+storeType+", driverDetails = "+driverDetails+", storeId = "+storeId+", zoneType = "+zoneType+", timeStamp = "+timeStamp+", subTotalAmount = "+subTotalAmount+", cartId = "+cartId+", lt = "+lt+", pricingModel = "+pricingModel+", currency = "+currency+", estimateId = "+estimateId+", _id = "+_id+", driverId = "+driverId+", totalAmount = "+totalAmount+", abbrevationText = "+abbrevationText+", paidBy = "+paidBy+", coinpayTransaction = "+coinpayTransaction+", orderId = "+orderId+", storeCommissionTypeMsg = "+storeCommissionTypeMsg+", Items = "+Items+", mileageMetric = "+mileageMetric+", bookingDate = "+bookingDate+", storeName = "+storeName+", abbrevation = "+abbrevation+", storeDeliveryFee = "+storeDeliveryFee+", paymentType = "+paymentType+", exclusiveTaxes = "+exclusiveTaxes+", storeAddress = "+storeAddress+", couponCode = "+couponCode+", statusMsg = "+statusMsg+", inDispatch = "+inDispatch+", storeCommissionType = "+storeCommissionType+", bookingDateTimeStamp = "+bookingDateTimeStamp+", currencySymbol = "+currencySymbol+", storeCommission = "+storeCommission+", activityLogs = "+activityLogs+"]";
    }
}
