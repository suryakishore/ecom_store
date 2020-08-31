package com.app.ecomstore.printlabel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LabelsData() : Parcelable {
    @SerializedName("shippingLabel")
    @Expose
    private var shippingLabel: String? = null

    @SerializedName("bags")
    @Expose
    private var bags: ArrayList<LabelBags>? = null

    constructor(parcel: Parcel) : this() {

    }

    fun getBags(): ArrayList<LabelBags>? {
        return bags
    }

    fun setBags(bags: ArrayList<LabelBags>) {
        this.bags = bags
    }

    fun getShippingLabel(): String? {
        return shippingLabel
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LabelsData> {
        override fun createFromParcel(parcel: Parcel): LabelsData {
            return LabelsData(parcel)
        }

        override fun newArray(size: Int): Array<LabelsData?> {
            return arrayOfNulls(size)
        }
    }


}