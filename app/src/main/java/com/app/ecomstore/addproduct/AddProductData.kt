package com.app.ecomstore.addproduct

import android.os.Parcel
import android.os.Parcelable
import com.app.delivxstore.main.mobileView.orderDetails.models.Images
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AddProductData():Parcelable{

    @SerializedName("data")
    @Expose
    var data: ArrayList<ProductData>? = null

    @SerializedName("penCount")
    @Expose
    var penCount: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    constructor(parcel: Parcel) : this() {
        penCount = parcel.readString()
        message = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(penCount)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddProductData> {
        override fun createFromParcel(parcel: Parcel): AddProductData {
            return AddProductData(parcel)
        }

        override fun newArray(size: Int): Array<AddProductData?> {
            return arrayOfNulls(size)
        }
    }


}