package com.app.ecomstore.trackOrder

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrackingOrderStatus {
    @SerializedName("statusText")
    @Expose
    private var statusText: String? = null
    @SerializedName("time")
    @Expose
    private var time: String? = null
    @SerializedName("formatedDate")
    @Expose
    private var formatedDate: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null

    private var viewStatus1 = false
    private var viewStatus2 = false

    fun getStatusText(): String? {
        return statusText
    }

    fun setStatusText(statusText: String?) {
        this.statusText = statusText
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

    fun getFormatedDate(): String? {
        return formatedDate
    }

    fun setFormatedDate(formatedDate: String?) {
        this.formatedDate = formatedDate
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun isViewStatus1(): Boolean {
        return viewStatus1
    }

    fun setViewStatus1(viewStatus1: Boolean) {
        this.viewStatus1 = viewStatus1
    }

    fun isViewStatus2(): Boolean {
        return viewStatus2
    }

    fun setViewStatus2(viewStatus2: Boolean) {
        this.viewStatus2 = viewStatus2
    }

}
