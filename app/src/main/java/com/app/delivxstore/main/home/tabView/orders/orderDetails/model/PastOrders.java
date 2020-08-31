package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PastOrders implements Serializable {

    private String cityId;

    private String bookingType;

    private String subTotalAmount;

    private String currency;

    private String _id;

    private String totalAmount;

    private String extraNote;

    private String orderId;

    private ArrayList<Items> Items;

    private String bookingDate;

    private String status;

    private String paymentType;

    private String statusMsg;

    private String serviceType;

    private String deliveryCharge;

    private String discount;

    private String currencySymbol;

    private boolean isSelected;


    ArrayList<ExclusiveTaxes> exclusiveTaxes;

    public ArrayList<ExclusiveTaxes> getExclusiveTaxes() {
        return exclusiveTaxes;
    }

    public void setExclusiveTaxes(ArrayList<ExclusiveTaxes> exclusiveTaxes) {
        this.exclusiveTaxes = exclusiveTaxes;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public String getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(String subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
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

    public ArrayList<com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items> getItems() {
        return Items;
    }

    public void setItems(ArrayList<com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Items> items) {
        Items = items;
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
}
