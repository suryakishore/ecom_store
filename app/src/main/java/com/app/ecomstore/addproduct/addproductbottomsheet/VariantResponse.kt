package com.app.ecomstore.addproduct.addproductbottomsheet

import android.os.Parcel
import android.os.Parcelable
import com.app.ecomstore.addproduct.AddProductData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VariantResponse():Parcelable {

    @SerializedName("data")
    @Expose
    var data: ArrayList<VariantData>? = null

    @SerializedName("suppliers")
    @Expose
    var suppliers: VariantSuppliers? = null

    @SerializedName("product")
    @Expose
    var product: VariantProductData? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    constructor(parcel: Parcel) : this() {
        suppliers = parcel.readParcelable(VariantSuppliers::class.java.classLoader)
        product = parcel.readParcelable(VariantProductData::class.java.classLoader)
        message = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(suppliers, flags)
        parcel.writeParcelable(product, flags)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VariantResponse> {
        override fun createFromParcel(parcel: Parcel): VariantResponse {
            return VariantResponse(parcel)
        }

        override fun newArray(size: Int): Array<VariantResponse?> {
            return arrayOfNulls(size)
        }
    }


}