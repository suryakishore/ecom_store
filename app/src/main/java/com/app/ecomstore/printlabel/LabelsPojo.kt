package com.app.ecomstore.printlabel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LabelsPojo() : Parcelable {

    @SerializedName("data")
    @Expose
    private var data: LabelsData? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    constructor(parcel: Parcel) : this() {
        data = parcel.readParcelable(LabelsData::class.java.classLoader)
        message = parcel.readString()
    }


    fun getData(): LabelsData? {
        return data
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(data, flags)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LabelsPojo> {
        override fun createFromParcel(parcel: Parcel): LabelsPojo {
            return LabelsPojo(parcel)
        }

        override fun newArray(size: Int): Array<LabelsPojo?> {
            return arrayOfNulls(size)
        }
    }

}