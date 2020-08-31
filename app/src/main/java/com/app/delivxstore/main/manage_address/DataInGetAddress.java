package com.app.delivxstore.main.manage_address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dell on 31-Jan-18.
 */

public class DataInGetAddress implements Serializable{

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("addLine1")
    @Expose
    private String addLine1;
    @SerializedName("addLine2")
    @Expose
    private String addLine2;
    @SerializedName("flatNumber")
    @Expose
    private String flatNumber;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("placeId")
    @Expose
    private String placeId;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("taggedAs")
    @Expose
    private String taggedAs;
    @SerializedName("userType")
    @Expose
    private Integer userType;
    @SerializedName("userId")
    @Expose
    private String userId;

    private String fullAddress;



    public String getFullAddress() {

        fullAddress=this.getAddLine1();
        if(this.getAddLine2()!=null)
        {
            fullAddress=fullAddress+" "+this.getAddLine2();
        }
        fullAddress=fullAddress+" "+this.getCity()+" "+this.getState()+" " +this.getCountry();

        if(this.getPincode()!=null)
        {
            fullAddress=fullAddress+" "+this.getPincode();
        }
        return fullAddress;
    }



    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTaggedAs() {
        return taggedAs;
    }

    public void setTaggedAs(String taggedAs) {
        this.taggedAs = taggedAs;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
