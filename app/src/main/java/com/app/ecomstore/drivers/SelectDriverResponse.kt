package com.app.ecomstore.drivers

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SelectDriverResponse() : Parcelable {
    @SerializedName("data")
    @Expose
    private var data: ArrayList<SelectDriverData>?=null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
    }


    fun getData(): ArrayList<SelectDriverData>? {
        return data
    }

    fun setData(data: ArrayList<SelectDriverData>) {
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

    companion object CREATOR : Parcelable.Creator<SelectDriverResponse> {
        override fun createFromParcel(parcel: Parcel): SelectDriverResponse {
            return SelectDriverResponse(parcel)
        }

        override fun newArray(size: Int): Array<SelectDriverResponse?> {
            return arrayOfNulls(size)
        }
    }


}