package com.app.ecomstore.addproduct.addproductbottomsheet

import android.os.Parcel
import android.os.Parcelable
import com.app.delivxstore.main.mobileView.orderDetails.models.Images
import com.app.ecomstore.addproduct.FinalPriceListData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VariantProductData() :Parcelable{

    @SerializedName("brandName")
    @Expose
    var brandName: String? = null


    @SerializedName("finalPriceList")
    @Expose
    var finalPriceList: FinalPriceListData? = null

    @SerializedName("currencySymbol")
    @Expose
    var currencySymbol: String? = null

    @SerializedName("images")
    @Expose
    var images: ArrayList<Images>? = null


    @SerializedName("productName")
    @Expose
    var productName: String? = null

    constructor(parcel: Parcel) : this() {
        brandName = parcel.readString()
        finalPriceList = parcel.readParcelable(FinalPriceListData::class.java.classLoader)
        currencySymbol = parcel.readString()
        productName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brandName)
        parcel.writeParcelable(finalPriceList, flags)
        parcel.writeString(currencySymbol)
        parcel.writeString(productName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VariantProductData> {
        override fun createFromParcel(parcel: Parcel): VariantProductData {
            return VariantProductData(parcel)
        }

        override fun newArray(size: Int): Array<VariantProductData?> {
            return arrayOfNulls(size)
        }
    }


}