package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceData implements Parcelable {
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("deviceMake")
    @Expose
    private String deviceMake;
    @SerializedName("ipAddress")
    @Expose
    private String ipAddress;
    @SerializedName("deviceTypeMsg")
    @Expose
    private String deviceTypeMsg;
    @SerializedName("deviceOsVersion")
    @Expose
    private String deviceOsVersion;

    protected DeviceData(Parcel in) {
        deviceType = in.readString();
        deviceMake = in.readString();
        ipAddress = in.readString();
        deviceTypeMsg = in.readString();
        deviceOsVersion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceType);
        dest.writeString(deviceMake);
        dest.writeString(ipAddress);
        dest.writeString(deviceTypeMsg);
        dest.writeString(deviceOsVersion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceData> CREATOR = new Creator<DeviceData>() {
        @Override
        public DeviceData createFromParcel(Parcel in) {
            return new DeviceData(in);
        }

        @Override
        public DeviceData[] newArray(int size) {
            return new DeviceData[size];
        }
    };

    public String getDeviceType ()
    {
        return deviceType;
    }

    public void setDeviceType (String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceMake ()
    {
        return deviceMake;
    }

    public void setDeviceMake (String deviceMake)
    {
        this.deviceMake = deviceMake;
    }

    public String getIpAddress ()
    {
        return ipAddress;
    }

    public void setIpAddress (String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getDeviceTypeMsg ()
    {
        return deviceTypeMsg;
    }

    public void setDeviceTypeMsg (String deviceTypeMsg)
    {
        this.deviceTypeMsg = deviceTypeMsg;
    }

    public String getDeviceOsVersion ()
    {
        return deviceOsVersion;
    }

    public void setDeviceOsVersion (String deviceOsVersion)
    {
        this.deviceOsVersion = deviceOsVersion;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deviceType = "+deviceType+", deviceMake = "+deviceMake+", ipAddress = "+ipAddress+", deviceTypeMsg = "+deviceTypeMsg+", deviceOsVersion = "+deviceOsVersion+"]";
    }
}
