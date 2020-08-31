package com.app.ecomstore.drivers

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemDriverBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * adapter class for the partner  items.
 */
public class SelectDriverAdapter(parnerData: ArrayList<SelectDriverData>, selectItem: SelectItem) : RecyclerView.Adapter<SelectDriverAdapter.PartnerViewHolder>() {
    var mSelectItem: SelectItem
    var mDriverData: ArrayList<SelectDriverData>
    lateinit var mContext: Context
    private var mPosition = MINUS_ONE

    init {
        mDriverData = parnerData
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDriverAdapter.PartnerViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemDriverBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_driver, parent, false)
        return PartnerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mDriverData != null) mDriverData.size else ZERO
    }

    override fun onBindViewHolder(holder: SelectDriverAdapter.PartnerViewHolder, position: Int) {
        val driverData = mDriverData.get(position)
        holder.mItemBinding.tvDriverName.setText("${driverData.getFirstName()} ${driverData.getLastName()}")
        holder.mItemBinding.tvDriverNumber.setText("${driverData.getCountryCode()} ${driverData.getMobile()}")
        holder.mItemBinding.rbSelectDriver.isChecked = if (mPosition != holder.adapterPosition) FALSE else TRUE
        val imageUrl: String? = driverData.getProfilePic()
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.profile)
            requestOptions.error(R.drawable.profile)
            Glide.with(mContext).load(imageUrl)
                    .apply(RequestOptions.circleCropTransform().apply(requestOptions)).into(
                            holder.mItemBinding.ivDriverProfilePic)
        }
        holder.mItemBinding.clItemDriver.setOnClickListener(View.OnClickListener {
            if (mPosition != holder.getAdapterPosition()) {
                mPosition = holder.getAdapterPosition()
                notifyDataSetChanged()
                mSelectItem.onSelectItem(mPosition)
            }
        })
        if (position == mDriverData.size - ONE) {
            holder.mItemBinding.view.visibility = View.GONE
        }
    }

    /**
     * view holder class for the cart items
     */
    class PartnerViewHolder(itemBinding: ItemDriverBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemDriverBinding

        init {
            mItemBinding = itemBinding
        }
    }
}