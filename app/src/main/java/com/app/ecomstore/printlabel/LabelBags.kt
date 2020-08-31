package com.app.ecomstore.printlabel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LabelBags() : Parcelable {

    @SerializedName("bagId")
    @Expose
    private var bagId: String? = null

    @SerializedName("lable")
    @Expose
    private var lable: String? = null

    constructor(parcel: Parcel) : this() {
        bagId = parcel.readString()
        lable = parcel.readString()
    }

    fun getBadId(): String? {
        return bagId
    }


    fun getLable(): String? {
        return lable
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bagId)
        parcel.writeString(lable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LabelBags> {
        override fun createFromParcel(parcel: Parcel): LabelBags {
            return LabelBags(parcel)
        }

        override fun newArray(size: Int): Array<LabelBags?> {
            return arrayOfNulls(size)
        }
    }


}