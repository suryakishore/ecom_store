package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Items implements Serializable {

    private float unitPrice;
    private int quantity;
    private ArrayList<AddOns> addOns;
    private ArrayList<String> images;
    private String addedToCartOn;
    private String addOnsPrice;
    private String productId;
    private String parentProductId;
    private String offerName;
    private String unitName;
    private String childProductId;
    private String subCatName;
    private String finalPrice;
    private String appliedDiscount;
    private String productName;
    private String subSubCatName;
    private String packId;
    private String itemName;
    private String catName;
    private String addOnAvailable;
    private String unitId;
    private String offerId;
    private String itemImageURL;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAddOnsPrice() {
        return addOnsPrice;
    }

    public void setAddOnsPrice(String addOnsPrice) {
        this.addOnsPrice = addOnsPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getParentProductId() {
        return parentProductId;
    }

    public void setParentProductId(String parentProductId) {
        this.parentProductId = parentProductId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getChildProductId() {
        return childProductId;
    }

    public void setChildProductId(String childProductId) {
        this.childProductId = childProductId;
    }

    public ArrayList<AddOns> getAddOns() {
        return addOns;
    }

    public void setAddOns(ArrayList<AddOns> addOns) {
        this.addOns = addOns;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getAppliedDiscount() {
        return appliedDiscount;
    }

    public void setAppliedDiscount(String appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubSubCatName() {
        return subSubCatName;
    }

    public void setSubSubCatName(String subSubCatName) {
        this.subSubCatName = subSubCatName;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAddedToCartOn() {
        return addedToCartOn;
    }

    public void setAddedToCartOn(String addedToCartOn) {
        this.addedToCartOn = addedToCartOn;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getAddOnAvailable() {
        return addOnAvailable;
    }

    public void setAddOnAvailable(String addOnAvailable) {
        this.addOnAvailable = addOnAvailable;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getItemImageURL() {
        return itemImageURL;
    }

    public void setItemImageURL(String itemImageURL) {
        this.itemImageURL = itemImageURL;
    }

    @Override
    public String toString() {
        return "Items{" +
                "unitPrice=" + unitPrice +
                ", addOnsPrice='" + addOnsPrice + '\'' +
                ", quantity=" + quantity +
                ", productId='" + productId + '\'' +
                ", images=" + images +
                ", parentProductId='" + parentProductId + '\'' +
                ", offerName='" + offerName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", childProductId='" + childProductId + '\'' +
                ", addOns=" + addOns +
                ", subCatName='" + subCatName + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                ", appliedDiscount='" + appliedDiscount + '\'' +
                ", productName='" + productName + '\'' +
                ", subSubCatName='" + subSubCatName + '\'' +
                ", packId='" + packId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", addedToCartOn=" + addedToCartOn +
                ", catName='" + catName + '\'' +
                ", addOnAvailable='" + addOnAvailable + '\'' +
                ", unitId='" + unitId + '\'' +
                ", offerId='" + offerId + '\'' +
                ", itemImageURL='" + itemImageURL + '\'' +
                '}';
    }
}
