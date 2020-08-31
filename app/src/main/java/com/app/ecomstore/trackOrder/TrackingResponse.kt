package com.app.ecomstore.trackOrder

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrackingResponse {
    @SerializedName("data")
    @Expose
    private var data: TrackingData? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getData(): TrackingData? {
        return data
    }

    fun setData(data: TrackingData?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

}