package com.app.ecomstore.pack

import android.os.Parcel
import android.os.Parcelable
import com.app.ecomstore.drivers.SelectDriverData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BoxResponse() : Parcelable {
    @SerializedName("data")
    @Expose
    private var data: ArrayList<BoxData>?=null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
    }


    fun getData(): ArrayList<BoxData>? {
        return data
    }

    fun setData(data: ArrayList<BoxData>) {
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

    companion object CREATOR : Parcelable.Creator<BoxResponse> {
        override fun createFromParcel(parcel: Parcel): BoxResponse {
            return BoxResponse(parcel)
        }

        override fun newArray(size: Int): Array<BoxResponse?> {
            return arrayOfNulls(size)
        }
    }


}