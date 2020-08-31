package com.app.ecomstore.substitute

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ProductSubStituteResponse():Parcelable  {
    @SerializedName("data")
    @Expose
    var data: ArrayList<ProductSubStituteData>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("totalCount")
    @Expose
    var totalCount: String? = null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
        totalCount = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeString(totalCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductSubStituteResponse> {
        override fun createFromParcel(parcel: Parcel): ProductSubStituteResponse {
            return ProductSubStituteResponse(parcel)
        }

        override fun newArray(size: Int): Array<ProductSubStituteResponse?> {
            return arrayOfNulls(size)
        }
    }


}