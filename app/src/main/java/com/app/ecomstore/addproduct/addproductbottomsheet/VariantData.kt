package com.app.ecomstore.addproduct.addproductbottomsheet

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class VariantData() : Parcelable {

    @SerializedName("centralProductId")
    @Expose
    var centralProductId: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null

    @SerializedName("prescriptionRequired")
    @Expose
    var prescriptionRequired: String? = null

    @SerializedName("allowOrderOutOfStock")
    @Expose
    var allowOrderOutOfStock: String? = null

    @SerializedName("maxQuantity")
    @Expose
    var maxQuantity: String? = null


    @SerializedName("childProductId")
    @Expose
    var childProductId: String? = null


    @SerializedName("isPrimary")
    @Expose
    var isPrimary: String? = null

    @SerializedName("manufactureName")
    @Expose
    var manufactureName: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("keyName")
    @Expose
    var keyName: String? = null

    @SerializedName("unitId")
    @Expose
    var unitId: String? = null

    @SerializedName("sizeData")
    @Expose
    var sizeData: ArrayList<SizeData>? = null

    constructor(parcel: Parcel) : this() {
        centralProductId = parcel.readString()
        image = parcel.readString()
        prescriptionRequired = parcel.readString()
        allowOrderOutOfStock = parcel.readString()
        maxQuantity = parcel.readString()
        childProductId = parcel.readString()
        isPrimary = parcel.readString()
        manufactureName = parcel.readString()
        name = parcel.readString()
        keyName = parcel.readString()
        unitId = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(centralProductId)
        parcel.writeString(image)
        parcel.writeString(prescriptionRequired)
        parcel.writeString(allowOrderOutOfStock)
        parcel.writeString(maxQuantity)
        parcel.writeString(childProductId)
        parcel.writeString(isPrimary)
        parcel.writeString(manufactureName)
        parcel.writeString(name)
        parcel.writeString(keyName)
        parcel.writeString(unitId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VariantData> {
        override fun createFromParcel(parcel: Parcel): VariantData {
            return VariantData(parcel)
        }

        override fun newArray(size: Int): Array<VariantData?> {
            return arrayOfNulls(size)
        }
    }


}