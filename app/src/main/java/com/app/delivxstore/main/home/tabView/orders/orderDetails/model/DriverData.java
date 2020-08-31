package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class DriverData implements Serializable {

    public DriverData(String driverId) {
        this.driverId = driverId;
    }

    private String phone;

    private String[] serviceZones;

    private String appversion;

    private String destinationName;

    private String locationCheck;

    private String _id;

    private String deviceType;

    private String name;

    private String serverTime;

    private String driverId;

    private String longitude;

    private String firstName;

    private String batteryPercentage;

    private String lastName;

    private String status;

    private String lastActive;

    private String a;

    private String countryCode;

    private String image;

    private String batteryPer;

    private String tasks;

    private String lastOnline;

    private String email;

    private String accountType;

    private String latitude;

    private String storeId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
//        Utility.printLog("CompareDetails"+"equals");

        DriverData data=(DriverData)obj;
        if(data.driverId.equals(this.driverId))
            return true;
        else
            return false;
//        return super.equals(obj);
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String[] getServiceZones ()
    {
        return serviceZones;
    }

    public void setServiceZones (String[] serviceZones)
    {
        this.serviceZones = serviceZones;
    }

    public String getAppversion ()
    {
        return appversion;
    }

    public void setAppversion (String appversion)
    {
        this.appversion = appversion;
    }

    public String getDestinationName ()
    {
        return destinationName;
    }

    public void setDestinationName (String destinationName)
    {
        this.destinationName = destinationName;
    }

    public String getLocationCheck ()
    {
        return locationCheck;
    }

    public void setLocationCheck (String locationCheck)
    {
        this.locationCheck = locationCheck;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getDeviceType ()
    {
        return deviceType;
    }

    public void setDeviceType (String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getServerTime ()
    {
        return serverTime;
    }

    public void setServerTime (String serverTime)
    {
        this.serverTime = serverTime;
    }

    public String getDriverId ()
    {
        return driverId;
    }

    public void setDriverId (String driverId)
    {
        this.driverId = driverId;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getBatteryPercentage ()
    {
        return batteryPercentage;
    }

    public void setBatteryPercentage (String batteryPercentage)
    {
        this.batteryPercentage = batteryPercentage;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getLastActive ()
    {
        return lastActive;
    }

    public void setLastActive (String lastActive)
    {
        this.lastActive = lastActive;
    }

    public String getA ()
    {
        return a;
    }

    public void setA (String a)
    {
        this.a = a;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getBatteryPer ()
    {
        return batteryPer;
    }

    public void setBatteryPer (String batteryPer)
    {
        this.batteryPer = batteryPer;
    }

    public String getTasks ()
    {
        return tasks;
    }

    public void setTasks (String tasks)
    {
        this.tasks = tasks;
    }

    public String getLastOnline ()
    {
        return lastOnline;
    }

    public void setLastOnline (String lastOnline)
    {
        this.lastOnline = lastOnline;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAccountType ()
    {
        return accountType;
    }

    public void setAccountType (String accountType)
    {
        this.accountType = accountType;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getStoreId ()
    {
        return storeId;
    }

    public void setStoreId (String storeId)
    {
        this.storeId = storeId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phone = "+phone+", serviceZones = "+serviceZones+", appversion = "+appversion+", destinationName = "+destinationName+", locationCheck = "+locationCheck+", _id = "+_id+", deviceType = "+deviceType+", name = "+name+", serverTime = "+serverTime+", driverId = "+driverId+", longitude = "+longitude+", firstName = "+firstName+", batteryPercentage = "+batteryPercentage+", lastName = "+lastName+", status = "+status+", lastActive = "+lastActive+", a = "+a+", countryCode = "+countryCode+", image = "+image+", batteryPer = "+batteryPer+", tasks = "+tasks+", lastOnline = "+lastOnline+", email = "+email+", accountType = "+accountType+", latitude = "+latitude+", storeId = "+storeId+"]";
    }
}
