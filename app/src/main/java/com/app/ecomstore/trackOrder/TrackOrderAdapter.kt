package com.app.ecomstore.trackOrder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemTrackingBinding
import com.app.delivxstore.utility.TimeFormatter
import com.app.ecomstore.util.EcomConstants.ONE
import com.app.ecomstore.util.EcomConstants.ZERO


/**
 * adapter class for the partner  items.
 */
public class TrackOrderAdapter(partnerData: ArrayList<TrackingOrderStatus>) : RecyclerView.Adapter<TrackOrderAdapter.PartnerViewHolder>() {
    var mTrackingData: ArrayList<TrackingOrderStatus>
    lateinit var mContext: Context

    init {
        mTrackingData = partnerData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackOrderAdapter.PartnerViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemTrackingBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_tracking, parent, false)
        return PartnerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mTrackingData != null) mTrackingData.size else ZERO
    }

    override fun onBindViewHolder(holder: TrackOrderAdapter.PartnerViewHolder, position: Int) {
        val trackingItemData = mTrackingData.get(position)
        holder.mItemBinding.tvTrackingStatus.text=(trackingItemData.getStatusText())
        if (trackingItemData.getTime() != null&&!trackingItemData.getTime()!!.isEmpty()) {
            holder.mItemBinding.tvOrderStatusDate.text=(
                    TimeFormatter.getTrackingTransactionTime(trackingItemData.getTime()))
        }
        holder.mItemBinding.viewTrackIndicatorHalf1.setBackgroundColor(if (trackingItemData.isViewStatus1()) mContext.resources.getColor(R.color.colorightWhite) else mContext.resources.getColor(R.color.lime))
        holder.mItemBinding.viewTrackIndicatorHalf2.setBackgroundColor(if (trackingItemData.isViewStatus2()) mContext.resources.getColor(R.color.colorightWhite) else mContext.resources.getColor(R.color.lime))
        holder.mItemBinding.ivCircle.setImageDrawable(
                if (trackingItemData.getFormatedDate()!!.isEmpty()) mContext.resources.getDrawable(
                        R.drawable.alto_circle) else mContext.resources.getDrawable(R.drawable.green_circle))
        if (mTrackingData.size - ONE == position) {
            holder.mItemBinding.viewTrackIndicatorHalf1.setVisibility(View.GONE)
            holder.mItemBinding.viewTrackIndicatorHalf2.setVisibility(View.GONE)
        }
    }

    /**
     * view holder class for the cart items
     */
    class PartnerViewHolder(itemBinding: ItemTrackingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemTrackingBinding

        init {
            mItemBinding = itemBinding
        }
    }
}