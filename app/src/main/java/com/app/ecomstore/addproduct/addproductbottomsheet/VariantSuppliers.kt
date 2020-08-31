package com.app.ecomstore.addproduct.addproductbottomsheet

import android.os.Parcel
import android.os.Parcelable
import com.app.ecomstore.addproduct.AddProductData
import com.app.ecomstore.boarding.login.model.StoreName
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VariantSuppliers():Parcelable {

    @SerializedName("supplierName")
    @Expose
    var supplierName: String? = null

    @SerializedName("productId")
    @Expose
    var productId: String? = null

    @SerializedName("retailerQty")
    @Expose
    var retailerQty: String? = null

    @SerializedName("retailerPrice")
    @Expose
    var retailerPrice: String? = null

    @SerializedName("rating")
    @Expose
    var rating: String? = null


    @SerializedName("currencySymbol")
    @Expose
    var currencySymbol: String? = null


    @SerializedName("distributorPrice")
    @Expose
    var distributorPrice: String? = null

    @SerializedName("storeName")
    @Expose
    var storeName: StoreName? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("distributorQty")
    @Expose
    var distributorQty: String? = null

    constructor(parcel: Parcel) : this() {
        supplierName = parcel.readString()
        productId = parcel.readString()
        retailerQty = parcel.readString()
        retailerPrice = parcel.readString()
        rating = parcel.readString()
        currencySymbol = parcel.readString()
        distributorPrice = parcel.readString()
        storeName = parcel.readParcelable(StoreName::class.java.classLoader)
        currency = parcel.readString()
        id = parcel.readString()
        distributorQty = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(supplierName)
        parcel.writeString(productId)
        parcel.writeString(retailerQty)
        parcel.writeString(retailerPrice)
        parcel.writeString(rating)
        parcel.writeString(currencySymbol)
        parcel.writeString(distributorPrice)
        parcel.writeParcelable(storeName, flags)
        parcel.writeString(currency)
        parcel.writeString(id)
        parcel.writeString(distributorQty)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VariantSuppliers> {
        override fun createFromParcel(parcel: Parcel): VariantSuppliers {
            return VariantSuppliers(parcel)
        }

        override fun newArray(size: Int): Array<VariantSuppliers?> {
            return arrayOfNulls(size)
        }
    }

}