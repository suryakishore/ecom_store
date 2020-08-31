package com.app.ecomstore.updateasile

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdateAisleRequest() : Parcelable {

    @SerializedName("aisle")
    @Expose
    private var aisle: String? = null

    @SerializedName("parentProductId")
    @Expose
    private var parentProductId: String? = null

    @SerializedName("section")
    @Expose
    private var section: String? = null

    @SerializedName("shelf")
    @Expose
    private var shelf: String? = null

    @SerializedName("storeId")
    @Expose
    var storeId: String? = null

    constructor(parcel: Parcel) : this() {
        aisle = parcel.readString()
        parentProductId = parcel.readString()
        section = parcel.readString()
        shelf = parcel.readString()
        storeId = parcel.readString()
    }

    fun setAisle(aisle: String?) {
        this.aisle = aisle
    }


    fun setParentProductId(parentProductId: String?) {
        this.parentProductId = parentProductId
    }


    fun setSection(section: String?) {
        this.section = section
    }

    fun setShelf(shelf: String?) {
        this.shelf = shelf
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(aisle)
        parcel.writeString(parentProductId)
        parcel.writeString(section)
        parcel.writeString(shelf)
        parcel.writeString(storeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpdateAisleRequest> {
        override fun createFromParcel(parcel: Parcel): UpdateAisleRequest {
            return UpdateAisleRequest(parcel)
        }

        override fun newArray(size: Int): Array<UpdateAisleRequest?> {
            return arrayOfNulls(size)
        }
    }


}