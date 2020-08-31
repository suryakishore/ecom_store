package com.app.ecomstore.pack

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemPackingBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * adapter class for the partner  items.
 */
public class SelectBoxAdapter(boxesData: ArrayList<BoxData>, selectItem: SelectItem) : RecyclerView.Adapter<SelectBoxAdapter.PartnerViewHolder>() {
    var mSelectItem: SelectItem
    var mBoxesData: ArrayList<BoxData>
    lateinit var mContext: Context
    private var mPosition = MINUS_ONE

    init {
        mBoxesData = boxesData
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectBoxAdapter.PartnerViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemPackingBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_packing, parent, false)
        return PartnerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mBoxesData != null) mBoxesData.size else ZERO
    }

    override fun onBindViewHolder(holder: SelectBoxAdapter.PartnerViewHolder, position: Int) {
        EcomUtil.printLog("exe" + "position" + position)
        val driverData = mBoxesData.get(position)
        holder.mItemBinding.tvBoxCount.text = "${mContext.resources.getString(R.string.box)} " + (position + ONE)
        holder.mItemBinding.tvBoxDimension.text = ("${driverData.getLengthCapacity()} X ${driverData.getLengthCapacityUnit()}${driverData.getWidthCapacity()} X ${driverData.getWidthCapacityUnit()} ${driverData.getHeightCapacity()} X ${driverData.getWidthCapacityUnit()}")
        holder.mItemBinding.tvBoxCapacity.text = ("${mContext.resources.getString(R.string.capacity)} : ${driverData.getWeight()} ${driverData.getWeightCapacityUnit()}")
        holder.mItemBinding.rbSelectDriver.isChecked = if (mPosition != holder.adapterPosition) FALSE else TRUE
        val imageUrl: String? = driverData.getImage()
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_box)
            requestOptions.error(R.drawable.ic_box)
            Glide.with(mContext).load(imageUrl)
                    .apply(requestOptions).into(
                            holder.mItemBinding.ivBoxPic)
        }
        holder.mItemBinding.clItemPacking.setOnClickListener(View.OnClickListener {
            if (mPosition != holder.getAdapterPosition()) {
                mPosition = holder.getAdapterPosition()
                notifyDataSetChanged()
                mSelectItem.onSelectItem(mPosition)
            }
        })
    }

    /**
     * view holder class for the cart items
     */
    class PartnerViewHolder(itemBinding: ItemPackingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemPackingBinding

        init {
            mItemBinding = itemBinding
        }
    }
}