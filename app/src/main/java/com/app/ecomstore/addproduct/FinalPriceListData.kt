package com.app.ecomstore.addproduct

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FinalPriceListData() : Parcelable {

    @SerializedName("discountPercentage")
    @Expose
    var discountPercentage: String? = null

    @SerializedName("discountPrice")
    @Expose
    var discountPrice: String? = null

    @SerializedName("finalPrice")
    @Expose
    var finalPrice: String? = null

    @SerializedName("basePrice")
    @Expose
    var basePrice: String? = null

    constructor(parcel: Parcel) : this() {
        discountPercentage = parcel.readString()
        discountPrice = parcel.readString()
        finalPrice = parcel.readString()
        basePrice = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(discountPercentage)
        parcel.writeString(discountPrice)
        parcel.writeString(finalPrice)
        parcel.writeString(basePrice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FinalPriceListData> {
        override fun createFromParcel(parcel: Parcel): FinalPriceListData {
            return FinalPriceListData(parcel)
        }

        override fun newArray(size: Int): Array<FinalPriceListData?> {
            return arrayOfNulls(size)
        }
    }


}