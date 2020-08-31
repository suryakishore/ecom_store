package com.app.ecomstore.trackOrder

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrackingData {
    @SerializedName("orderStatus")
    @Expose
    private var orderStatus: ArrayList<TrackingOrderStatus>? = null

    fun getOrderStatus(): ArrayList<TrackingOrderStatus>? {
        return orderStatus
    }

    fun setOrderStatus(orderStatus: ArrayList<TrackingOrderStatus>) {
        this.orderStatus = orderStatus
    }
}