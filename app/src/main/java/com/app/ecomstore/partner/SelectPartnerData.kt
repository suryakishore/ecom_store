package com.app.ecomstore.partner

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SelectPartnerData() :Parcelable{
    @SerializedName("trackingUrl")
    @Expose
    private var trackingUrl: String? = null
    @SerializedName("statusText")
    @Expose
    private var statusText: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("logo")
    @Expose
    private var logo: String? = null
    @SerializedName("_id")
    @Expose
    private var _id: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null

    constructor(parcel: Parcel) : this() {
        trackingUrl = parcel.readString()
        statusText = parcel.readString()
        name = parcel.readString()
        logo = parcel.readString()
        _id = parcel.readString()
        status = parcel.readString()
    }

    fun getTrackingUrl(): String? {
        return trackingUrl
    }

    fun setTrackingUrl(trackingUrl: String?) {
        this.trackingUrl = trackingUrl
    }

    fun getStatusText(): String? {
        return statusText
    }

    fun setStatusText(statusText: String?) {
        this.statusText = statusText
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getLogo(): String? {
        return logo
    }

    fun setLogo(logo: String?) {
        this.logo = logo
    }

    fun get_id(): String? {
        return _id
    }

    fun set_id(_id: String?) {
        this._id = _id
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(trackingUrl)
        parcel.writeString(statusText)
        parcel.writeString(name)
        parcel.writeString(logo)
        parcel.writeString(_id)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectPartnerData> {
        override fun createFromParcel(parcel: Parcel): SelectPartnerData {
            return SelectPartnerData(parcel)
        }

        override fun newArray(size: Int): Array<SelectPartnerData?> {
            return arrayOfNulls(size)
        }
    }

}