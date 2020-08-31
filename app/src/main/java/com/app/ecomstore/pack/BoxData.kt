package com.app.ecomstore.pack

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BoxData() : Parcelable {


    @SerializedName("weight")
    @Expose
    private var weight: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null

    @SerializedName("volumeCapacity")
    @Expose
    private var volumeCapacity: String? = null

    @SerializedName("voulumeCapacityUnit")
    @Expose
    private var voulumeCapacityUnit: String? = null

    @SerializedName("widthCapacityUnitName")
    @Expose
    private var widthCapacityUnitName: String? = null

    @SerializedName("heightCapacityUnitName")
    @Expose
    private var heightCapacityUnitName: String? = null

    @SerializedName("voulumeCapacityUnitName")
    @Expose
    private var voulumeCapacityUnitName: String? = null

    @SerializedName("weightCapacityUnitName")
    @Expose
    private var weightCapacityUnitName: String? = null

    @SerializedName("widthCapacityUnit")
    @Expose
    private var widthCapacityUnit: String? = null

    @SerializedName("heightCapacity")
    @Expose
    private var heightCapacity: String? = null

    @SerializedName("weightCapacityUnit")
    @Expose
    private var weightCapacityUnit: String? = null

    @SerializedName("lengthCapacityUnitName")
    @Expose
    private var lengthCapacityUnitName: String? = null

    @SerializedName("statusMsg")
    @Expose
    private var statusMsg: String? = null

    @SerializedName("_id")
    @Expose
    private var _id: String? = null

    @SerializedName("heightCapacityUnit")
    @Expose
    private var heightCapacityUnit: String? = null

    @SerializedName("lengthCapacityUnit")
    @Expose
    private var lengthCapacityUnit: String? = null

    @SerializedName("widthCapacity")
    @Expose
    private var widthCapacity: String? = null

    @SerializedName("lengthCapacity")
    @Expose
    private var lengthCapacity: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    constructor(parcel: Parcel) : this() {
        weight = parcel.readString()
        image = parcel.readString()
        volumeCapacity = parcel.readString()
        voulumeCapacityUnit = parcel.readString()
        widthCapacityUnitName = parcel.readString()
        heightCapacityUnitName = parcel.readString()
        voulumeCapacityUnitName = parcel.readString()
        weightCapacityUnitName = parcel.readString()
        widthCapacityUnit = parcel.readString()
        heightCapacity = parcel.readString()
        weightCapacityUnit = parcel.readString()
        lengthCapacityUnitName = parcel.readString()
        statusMsg = parcel.readString()
        _id = parcel.readString()
        heightCapacityUnit = parcel.readString()
        lengthCapacityUnit = parcel.readString()
        widthCapacity = parcel.readString()
        lengthCapacity = parcel.readString()
        status = parcel.readString()
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getVolumeCapacity(): String? {
        return volumeCapacity
    }

    fun setVolumeCapacity(volumeCapacity: String?) {
        this.volumeCapacity = volumeCapacity
    }

    fun getVoulumeCapacityUnit(): String? {
        return voulumeCapacityUnit
    }

    fun setVoulumeCapacityUnit(voulumeCapacityUnit: String?) {
        this.voulumeCapacityUnit = voulumeCapacityUnit
    }

    fun getWidthCapacityUnitName(): String? {
        return widthCapacityUnitName
    }

    fun setWidthCapacityUnitName(widthCapacityUnitName: String?) {
        this.widthCapacityUnitName = widthCapacityUnitName
    }

    fun getWeight(): String? {
        return weight
    }

    fun setWeight(weight: String) {
        this.weight = weight
    }

    fun getHeightCapacityUnitName(): String? {
        return heightCapacityUnitName
    }

    fun setHeightCapacityUnitName(heightCapacityUnitName: String?) {
        this.heightCapacityUnitName = heightCapacityUnitName
    }

    fun getVoulumeCapacityUnitName(): String? {
        return voulumeCapacityUnitName
    }

    fun setVoulumeCapacityUnitName(voulumeCapacityUnitName: String?) {
        this.voulumeCapacityUnitName = voulumeCapacityUnitName
    }

    fun getWeightCapacityUnitName(): String? {
        return weightCapacityUnitName
    }

    fun setWeightCapacityUnitName(weightCapacityUnitName: String?) {
        this.weightCapacityUnitName = weightCapacityUnitName
    }

    fun getWidthCapacityUnit(): String? {
        return widthCapacityUnit
    }

    fun setWidthCapacityUnit(widthCapacityUnit: String?) {
        this.widthCapacityUnit = widthCapacityUnit
    }

    fun getHeightCapacity(): String? {
        return heightCapacity
    }

    fun setHeightCapacity(heightCapacity: String?) {
        this.heightCapacity = heightCapacity
    }

    fun getWeightCapacityUnit(): String? {
        return weightCapacityUnit
    }

    fun setWeightCapacityUnit(weightCapacityUnit: String?) {
        this.weightCapacityUnit = weightCapacityUnit
    }

    fun getLengthCapacityUnitName(): String? {
        return lengthCapacityUnitName
    }

    fun setLengthCapacityUnitName(lengthCapacityUnitName: String?) {
        this.lengthCapacityUnitName = lengthCapacityUnitName
    }

    fun getStatusMsg(): String? {
        return statusMsg
    }

    fun setStatusMsg(statusMsg: String?) {
        this.statusMsg = statusMsg
    }


    fun get_id(): String? {
        return _id
    }

    fun set_id(_id: String?) {
        this._id = _id
    }

    fun getHeightCapacityUnit(): String? {
        return heightCapacityUnit
    }

    fun setHeightCapacityUnit(heightCapacityUnit: String?) {
        this.heightCapacityUnit = heightCapacityUnit
    }

    fun getLengthCapacityUnit(): String? {
        return lengthCapacityUnit
    }

    fun setLengthCapacityUnit(lengthCapacityUnit: String?) {
        this.lengthCapacityUnit = lengthCapacityUnit
    }

    fun getWidthCapacity(): String? {
        return widthCapacity
    }

    fun setWidthCapacity(widthCapacity: String?) {
        this.widthCapacity = widthCapacity
    }

    fun getLengthCapacity(): String? {
        return lengthCapacity
    }

    fun setLengthCapacity(lengthCapacity: String?) {
        this.lengthCapacity = lengthCapacity
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(weight)
        parcel.writeString(image)
        parcel.writeString(volumeCapacity)
        parcel.writeString(voulumeCapacityUnit)
        parcel.writeString(widthCapacityUnitName)
        parcel.writeString(heightCapacityUnitName)
        parcel.writeString(voulumeCapacityUnitName)
        parcel.writeString(weightCapacityUnitName)
        parcel.writeString(widthCapacityUnit)
        parcel.writeString(heightCapacity)
        parcel.writeString(weightCapacityUnit)
        parcel.writeString(lengthCapacityUnitName)
        parcel.writeString(statusMsg)
        parcel.writeString(_id)
        parcel.writeString(heightCapacityUnit)
        parcel.writeString(lengthCapacityUnit)
        parcel.writeString(widthCapacity)
        parcel.writeString(lengthCapacity)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BoxData> {
        override fun createFromParcel(parcel: Parcel): BoxData {
            return BoxData(parcel)
        }

        override fun newArray(size: Int): Array<BoxData?> {
            return arrayOfNulls(size)
        }
    }


}