package com.app.ecomstore.drivers

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenerateLabelRequest() : Parcelable {

    @SerializedName("orderId")
    @Expose
    private var orderId: String? = null

    @SerializedName("driverId")
    @Expose
    private var driverId: String? = null

    @SerializedName("dispatchType")
    @Expose
    private var dispatchType: Int? = null

    fun getOrderId(): String? {
        return orderId
    }

    fun setOrderId(orderId: String?) {
        this.orderId = orderId
    }


    fun getDriverId(): String? {
        return driverId
    }

    fun setDriverId(driverId: String?) {
        this.driverId = driverId
    }

    fun getDispacthType(): Int? {
        return dispatchType
    }

    fun setDispacthType(dispatchType: Int?) {
        this.dispatchType = dispatchType
    }

    constructor(parcel: Parcel) : this() {
        orderId = parcel.readString()
        driverId = parcel.readString()
        dispatchType = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(orderId)
        parcel.writeString(driverId)
        parcel.writeValue(dispatchType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GenerateLabelRequest> {
        override fun createFromParcel(parcel: Parcel): GenerateLabelRequest {
            return GenerateLabelRequest(parcel)
        }

        override fun newArray(size: Int): Array<GenerateLabelRequest?> {
            return arrayOfNulls(size)
        }
    }


}