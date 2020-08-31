package com.app.delivxstore.main.home.model;

import java.io.Serializable;

public class CustomerDetails implements Serializable {

    private String customerId;

    private String email;

    private String mqttTopic;

    private String deviceType;

    private String mmjCard;

    private String name;

    private String fcmTopic;

    private String countryCode;

    private String profilePic;

    private String identityCard;

    private String deviceId;

    private String mobile;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMmjCard() {
        return mmjCard;
    }

    public void setMmjCard(String mmjCard) {
        this.mmjCard = mmjCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFcmTopic() {
        return fcmTopic;
    }

    public void setFcmTopic(String fcmTopic) {
        this.fcmTopic = fcmTopic;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
