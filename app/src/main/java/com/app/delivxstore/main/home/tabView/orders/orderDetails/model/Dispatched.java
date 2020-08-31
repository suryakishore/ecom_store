package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class Dispatched implements Serializable {

    private String status;

    private String receiveDt;

    private String image;

    private String lName;

    private String fName;

    private String expiryTime;

    private String actPing;

    private String driversLatLongs;

    private String email;

    private String mqttTopic;

    private String driverId;

    private String serverTime;

    private String fcmTopic;

    private String receiveDtisoDate;

    private String received_Act_serverTime;

    private String mobile;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getReceiveDt ()
    {
        return receiveDt;
    }

    public void setReceiveDt (String receiveDt)
    {
        this.receiveDt = receiveDt;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getLName ()
    {
        return lName;
    }

    public void setLName (String lName)
    {
        this.lName = lName;
    }

    public String getFName ()
    {
        return fName;
    }

    public void setFName (String fName)
    {
        this.fName = fName;
    }

    public String getExpiryTime ()
    {
        return expiryTime;
    }

    public void setExpiryTime (String expiryTime)
    {
        this.expiryTime = expiryTime;
    }

    public String getActPing ()
    {
        return actPing;
    }

    public void setActPing (String actPing)
    {
        this.actPing = actPing;
    }

    public String getDriversLatLongs ()
    {
        return driversLatLongs;
    }

    public void setDriversLatLongs (String driversLatLongs)
    {
        this.driversLatLongs = driversLatLongs;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getMqttTopic ()
    {
        return mqttTopic;
    }

    public void setMqttTopic (String mqttTopic)
    {
        this.mqttTopic = mqttTopic;
    }

    public String getDriverId ()
    {
        return driverId;
    }

    public void setDriverId (String driverId)
    {
        this.driverId = driverId;
    }

    public String getServerTime ()
    {
        return serverTime;
    }

    public void setServerTime (String serverTime)
    {
        this.serverTime = serverTime;
    }

    public String getFcmTopic ()
    {
        return fcmTopic;
    }

    public void setFcmTopic (String fcmTopic)
    {
        this.fcmTopic = fcmTopic;
    }

    public String getReceiveDtisoDate ()
    {
        return receiveDtisoDate;
    }

    public void setReceiveDtisoDate (String receiveDtisoDate)
    {
        this.receiveDtisoDate = receiveDtisoDate;
    }

    public String getReceived_Act_serverTime ()
    {
        return received_Act_serverTime;
    }

    public void setReceived_Act_serverTime (String received_Act_serverTime)
    {
        this.received_Act_serverTime = received_Act_serverTime;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", receiveDt = "+receiveDt+", image = "+image+", lName = "+lName+", fName = "+fName+", expiryTime = "+expiryTime+", actPing = "+actPing+", driversLatLongs = "+driversLatLongs+", email = "+email+", mqttTopic = "+mqttTopic+", driverId = "+driverId+", serverTime = "+serverTime+", fcmTopic = "+fcmTopic+", receiveDtisoDate = "+receiveDtisoDate+", received_Act_serverTime = "+received_Act_serverTime+", mobile = "+mobile+"]";
    }
}
