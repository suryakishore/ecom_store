package com.app.ecomstore.addproduct

import android.os.Parcel
import android.os.Parcelable
import com.app.delivxstore.main.mobileView.orderDetails.models.Images
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ProductData() : Parcelable {

    @SerializedName("brandTitle")
    @Expose
    var brandTitle: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("finalPriceList")
    @Expose
    var finalPriceList: FinalPriceListData? = null

    @SerializedName("currencySymbol")
    @Expose
    var currencySymbol: String? = null

    @SerializedName("images")
    @Expose
    var images: ArrayList<Images>? = null

    @SerializedName("childProductId")
    @Expose
    var childProductId: String? = null

    @SerializedName("productId")
    @Expose
    var productId: String? = null

    @SerializedName("productName")
    @Expose
    var productName: String? = null

    @SerializedName("heightCapacityUnitName")
    @Expose
    var heightCapacityUnitName: String? = null

    @SerializedName("score")
    @Expose
    var score: String? = null

    @SerializedName("needsWeighed")
    @Expose
    var needsWeighed: Boolean? = null

    constructor(parcel: Parcel) : this() {
        brandTitle = parcel.readString()
        currency = parcel.readString()
        finalPriceList = parcel.readParcelable(FinalPriceListData::class.java.classLoader)
        currencySymbol = parcel.readString()
        childProductId = parcel.readString()
        productId = parcel.readString()
        productName = parcel.readString()
        heightCapacityUnitName = parcel.readString()
        score = parcel.readString()
        needsWeighed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brandTitle)
        parcel.writeString(currency)
        parcel.writeParcelable(finalPriceList, flags)
        parcel.writeString(currencySymbol)
        parcel.writeString(childProductId)
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(heightCapacityUnitName)
        parcel.writeString(score)
        parcel.writeValue(needsWeighed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductData> {
        override fun createFromParcel(parcel: Parcel): ProductData {
            return ProductData(parcel)
        }

        override fun newArray(size: Int): Array<ProductData?> {
            return arrayOfNulls(size)
        }
    }


}