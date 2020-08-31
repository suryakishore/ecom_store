package com.app.ecomstore.addproduct.addproductbottomsheet

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SizeData() : Parcelable {

    @SerializedName("colourId")
    @Expose
    var colourId: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("maxQuantity")
    @Expose
    var maxQuantity: String? = null

    @SerializedName("childProductId")
    @Expose
    var childProductId: String? = null


    @SerializedName("availableStock")
    @Expose
    var availableStock: String? = null

    @SerializedName("keyName")
    @Expose
    var keyName: String? = null


    @SerializedName("allowOrderOutOfStock")
    @Expose
    var allowOrderOutOfStock: String? = null


    @SerializedName("prescriptionRequired")
    @Expose
    var prescriptionRequired: String? = null

    @SerializedName("size")
    @Expose
    var size: String? = null

    @SerializedName("isPrimary")
    @Expose
    var isPrimary: String? = null


    @SerializedName("manufactureName")
    @Expose
    var manufactureName: String? = null


    @SerializedName("name")
    @Expose
    var name: String? = null


    @SerializedName("outOfStock")
    @Expose
    var outOfStock: String? = null

    @SerializedName("unitId")
    @Expose
    var unitId: String? = null

    var isSelected=false


    constructor(parcel: Parcel) : this() {
        colourId = parcel.readString()
        image = parcel.readString()
        maxQuantity = parcel.readString()
        childProductId = parcel.readString()
        availableStock = parcel.readString()
        keyName = parcel.readString()
        allowOrderOutOfStock = parcel.readString()
        prescriptionRequired = parcel.readString()
        size = parcel.readString()
        isPrimary = parcel.readString()
        manufactureName = parcel.readString()
        name = parcel.readString()
        outOfStock = parcel.readString()
        unitId = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(colourId)
        parcel.writeString(image)
        parcel.writeString(maxQuantity)
        parcel.writeString(childProductId)
        parcel.writeString(availableStock)
        parcel.writeString(keyName)
        parcel.writeString(allowOrderOutOfStock)
        parcel.writeString(prescriptionRequired)
        parcel.writeString(size)
        parcel.writeString(isPrimary)
        parcel.writeString(manufactureName)
        parcel.writeString(name)
        parcel.writeString(outOfStock)
        parcel.writeString(unitId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SizeData> {
        override fun createFromParcel(parcel: Parcel): SizeData {
            return SizeData(parcel)
        }

        override fun newArray(size: Int): Array<SizeData?> {
            return arrayOfNulls(size)
        }
    }

}