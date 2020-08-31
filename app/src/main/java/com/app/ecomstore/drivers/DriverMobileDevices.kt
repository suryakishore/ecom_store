package com.app.ecomstore.drivers

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DriverMobileDevices():Parcelable  {

    @SerializedName("currentlyActive")
    @Expose
    private var currentlyActive: String? = null
    @SerializedName("deviceType")
    @Expose
    private var deviceType: String? = null
    @SerializedName("lastLogin")
    @Expose
    private var lastLogin: String? = null
    @SerializedName("appVersion")
    @Expose
    private var appVersion: String? = null
    @SerializedName("lastTimestamp")
    @Expose
    private var lastTimestamp: String? = null
    @SerializedName("deviceOsVersion")
    @Expose
    private var deviceOsVersion: String? = null
    @SerializedName("lastISOdate")
    @Expose
    private var lastISOdate: String? = null
    @SerializedName("deviceId")
    @Expose
    private var deviceId: String? = null
    @SerializedName("pushToken")
    @Expose
    private var pushToken: String? = null

    constructor(parcel: Parcel) : this() {
        currentlyActive = parcel.readString()
        deviceType = parcel.readString()
        lastLogin = parcel.readString()
        appVersion = parcel.readString()
        lastTimestamp = parcel.readString()
        deviceOsVersion = parcel.readString()
        lastISOdate = parcel.readString()
        deviceId = parcel.readString()
        pushToken = parcel.readString()
    }

    fun getCurrentlyActive(): String? {
        return currentlyActive
    }

    fun setCurrentlyActive(currentlyActive: String?) {
        this.currentlyActive = currentlyActive
    }

    fun getDeviceType(): String? {
        return deviceType
    }

    fun setDeviceType(deviceType: String?) {
        this.deviceType = deviceType
    }

    fun getLastLogin(): String? {
        return lastLogin
    }

    fun setLastLogin(lastLogin: String?) {
        this.lastLogin = lastLogin
    }

    fun getAppVersion(): String? {
        return appVersion
    }

    fun setAppVersion(appVersion: String?) {
        this.appVersion = appVersion
    }

    fun getLastTimestamp(): String? {
        return lastTimestamp
    }

    fun setLastTimestamp(lastTimestamp: String?) {
        this.lastTimestamp = lastTimestamp
    }

    fun getDeviceOsVersion(): String? {
        return deviceOsVersion
    }

    fun setDeviceOsVersion(deviceOsVersion: String?) {
        this.deviceOsVersion = deviceOsVersion
    }

    fun getLastISOdate(): String? {
        return lastISOdate
    }

    fun setLastISOdate(lastISOdate: String?) {
        this.lastISOdate = lastISOdate
    }

    fun getDeviceId(): String? {
        return deviceId
    }

    fun setDeviceId(deviceId: String?) {
        this.deviceId = deviceId
    }

    fun getPushToken(): String? {
        return pushToken
    }

    fun setPushToken(pushToken: String?) {
        this.pushToken = pushToken
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currentlyActive)
        parcel.writeString(deviceType)
        parcel.writeString(lastLogin)
        parcel.writeString(appVersion)
        parcel.writeString(lastTimestamp)
        parcel.writeString(deviceOsVersion)
        parcel.writeString(lastISOdate)
        parcel.writeString(deviceId)
        parcel.writeString(pushToken)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DriverMobileDevices> {
        override fun createFromParcel(parcel: Parcel): DriverMobileDevices {
            return DriverMobileDevices(parcel)
        }

        override fun newArray(size: Int): Array<DriverMobileDevices?> {
            return arrayOfNulls(size)
        }
    }


}