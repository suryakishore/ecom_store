package com.app.ecomstore.partner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemRadioButtonBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.ZERO
import com.app.ecomstore.util.EcomConstants.TRUE
import com.app.ecomstore.util.EcomConstants.FALSE

/**
 * adapter class for the partner  items.
 */
public class SelectPartnerAdapter(parnerData: ArrayList<SelectPartnerData>, selectItem: SelectItem) : RecyclerView.Adapter<SelectPartnerAdapter.PartnerViewHolder>() {
    var mSelectItem: SelectItem
    var mParnerData: ArrayList<SelectPartnerData>
    lateinit var mContext: Context
    private var mPosition = 0

    init {
        mParnerData = parnerData
        mSelectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectPartnerAdapter.PartnerViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemRadioButtonBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_radio_button, parent, false)
        return PartnerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mParnerData != null) mParnerData.size else ZERO
    }

    override fun onBindViewHolder(holder: SelectPartnerAdapter.PartnerViewHolder, position: Int) {
        holder.mItemBinding.radioButtonOptionReason.setText(mParnerData.get(position).getName())
        holder.mItemBinding.radioButtonOptionReason.isChecked = if (mPosition != holder.adapterPosition) FALSE else TRUE
        holder.mItemBinding.radioButtonOptionReason.setOnClickListener(View.OnClickListener {
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
    class PartnerViewHolder(itemBinding: ItemRadioButtonBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemRadioButtonBinding

        init {
            mItemBinding = itemBinding
        }
    }
}