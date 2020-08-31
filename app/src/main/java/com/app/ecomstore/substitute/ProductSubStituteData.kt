package com.app.ecomstore.substitute

import android.os.Parcel
import android.os.Parcelable
import com.app.delivxstore.main.mobileView.orderDetails.models.Images
import com.app.ecomstore.addproduct.FinalPriceListData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductSubStituteData() : Parcelable {


    @SerializedName("images")
    @Expose
    var images: Images? = null

    @SerializedName("parentProductId")
    @Expose
    var parentProductId: String? = null


    @SerializedName("childProductId")
    @Expose
    var childProductId: String? = null


    @SerializedName("currencySymbol")
    @Expose
    var currencySymbol: String? = null


    @SerializedName("productName")
    @Expose
    var productName: String? = null


    @SerializedName("prescriptionRequired")
    @Expose
    var prescriptionRequired: String? = null


    @SerializedName("manufactureName")
    @Expose
    var manufactureName: String? = null


    @SerializedName("outOfStock")
    @Expose
    var outOfStock: String? = null


    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("brandTitle")
    @Expose
    var brandTitle: String? = null

    @SerializedName("finalPriceList")
    @Expose
    var finalPriceList: FinalPriceListData? = null

    @SerializedName("needsWeighed")
    @Expose
    var needsWeighed: Boolean? = null

    constructor(parcel: Parcel) : this() {
        images = parcel.readParcelable(Images::class.java.classLoader)
        parentProductId = parcel.readString()
        childProductId = parcel.readString()
        currencySymbol = parcel.readString()
        productName = parcel.readString()
        prescriptionRequired = parcel.readString()
        manufactureName = parcel.readString()
        outOfStock = parcel.readString()
        currency = parcel.readString()
        brandTitle = parcel.readString()
        finalPriceList = parcel.readParcelable(FinalPriceListData::class.java.classLoader)
        needsWeighed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(images, flags)
        parcel.writeString(parentProductId)
        parcel.writeString(childProductId)
        parcel.writeString(currencySymbol)
        parcel.writeString(productName)
        parcel.writeString(prescriptionRequired)
        parcel.writeString(manufactureName)
        parcel.writeString(outOfStock)
        parcel.writeString(currency)
        parcel.writeString(brandTitle)
        parcel.writeParcelable(finalPriceList, flags)
        parcel.writeValue(needsWeighed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductSubStituteData> {
        override fun createFromParcel(parcel: Parcel): ProductSubStituteData {
            return ProductSubStituteData(parcel)
        }

        override fun newArray(size: Int): Array<ProductSubStituteData?> {
            return arrayOfNulls(size)
        }
    }


}