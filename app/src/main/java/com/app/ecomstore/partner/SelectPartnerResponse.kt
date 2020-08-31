package com.app.ecomstore.partner

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SelectPartnerResponse() : Parcelable {
    @SerializedName("data")
    @Expose
    private var data: ArrayList<SelectPartnerData>?=null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
    }


    fun getData(): ArrayList<SelectPartnerData>? {
        return data
    }

    fun setData(data: ArrayList<SelectPartnerData>) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectPartnerResponse> {
        override fun createFromParcel(parcel: Parcel): SelectPartnerResponse {
            return SelectPartnerResponse(parcel)
        }

        override fun newArray(size: Int): Array<SelectPartnerResponse?> {
            return arrayOfNulls(size)
        }
    }


}