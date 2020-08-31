package com.app.ecomstore.addproduct

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddProductResponse() : Parcelable {
    @SerializedName("data")
    @Expose
    var data: AddProductData? = null

    constructor(parcel: Parcel) : this() {
        data = parcel.readParcelable(AddProductData::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddProductResponse> {
        override fun createFromParcel(parcel: Parcel): AddProductResponse {
            return AddProductResponse(parcel)
        }

        override fun newArray(size: Int): Array<AddProductResponse?> {
            return arrayOfNulls(size)
        }
    }


}