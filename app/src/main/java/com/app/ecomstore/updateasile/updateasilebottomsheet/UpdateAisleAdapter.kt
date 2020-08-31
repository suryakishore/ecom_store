package com.app.ecomstore.updateasile.updateasilebottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemUpdateAisleBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.VariableConstants.ZERO
import com.app.ecomstore.util.EcomUtil

/**
 * adapter class for the update aisle
 */
class UpdateAisleAdapter(aisleData: ArrayList<String>, position: Int, selectItem: SelectItem) : RecyclerView.Adapter<UpdateAisleAdapter.ViewHolder>() {

    lateinit var mContext: Context
    var mAisleData: ArrayList<String>
    var mSelectItem: SelectItem
    var mPosition: Int

    init {
        mAisleData = aisleData
        mPosition = position
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateAisleAdapter.ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemUpdateAisleBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_update_aisle, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mAisleData != null) mAisleData.size else ZERO
    }

    override fun onBindViewHolder(holder: UpdateAisleAdapter.ViewHolder, position: Int) {
        EcomUtil.printLog("exe"+"mAisleData"+mAisleData.get(position))
        holder.mItemBinding.tvItemUnAvailableProductName.text = mAisleData.get(position)
        holder.mItemBinding.ivItemSelect.visibility = if (mPosition == position) View.VISIBLE else GONE
        holder.mItemBinding.tvItemUnAvailableProductName.setTextColor(if (mPosition == position) mContext.resources.getColor(R.color.historyOrderStatus) else
            mContext.resources.getColor(R.color.blackShade))
        holder.mItemBinding.clUpdateAisle.setOnClickListener {
            notifyItemChanged(position)
            mSelectItem.onSelectItem(position)
        }
    }

    class ViewHolder(itemView: ItemUpdateAisleBinding) : RecyclerView.ViewHolder(itemView.root) {
        var mItemBinding: ItemUpdateAisleBinding

        init {
            mItemBinding = itemView
        }
    }


}