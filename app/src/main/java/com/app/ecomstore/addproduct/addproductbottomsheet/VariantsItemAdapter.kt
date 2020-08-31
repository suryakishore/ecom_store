package com.app.ecomstore.addproduct.addproductbottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemVariantBinding
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil


/**
 * adapter class for the partner  items.
 */
public class VariantsItemAdapter(sizeData: ArrayList<SizeData>, selectItem: ProductAttributesClick) : RecyclerView.Adapter<VariantsItemAdapter.AddProductViewHolder>() {
    var selectItem: ProductAttributesClick
    var sizeData: ArrayList<SizeData>
    lateinit var mContext: Context
    private var mCheckedPosition: Int = MINUS_ONE
    private var mUnCheckedPosition: Int = ZERO

    init {
        this.sizeData = sizeData
        this.selectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantsItemAdapter.AddProductViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemVariantBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_variant, parent, false)
        return AddProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (sizeData != null) sizeData.size else ZERO
    }

    override fun onBindViewHolder(holder: VariantsItemAdapter.AddProductViewHolder, position: Int) {
        val sizeDataItem = sizeData.get(position)
        if (sizeDataItem != null) {
            holder.mItemBinding.tvVariantName.text = sizeDataItem.size
            holder.mItemBinding.tvVariantName.setSelected(sizeDataItem.isPrimary?.toBoolean()!!)
            if (sizeDataItem.isPrimary?.toBoolean()!!) {
                mCheckedPosition = holder.getAdapterPosition()
                mUnCheckedPosition = mCheckedPosition
            }
            holder.mItemBinding.tvVariantName.setOnClickListener {
                if (!holder.mItemBinding.tvVariantName.isSelected()) {
                    mCheckedPosition = holder.getAdapterPosition()
                    EcomUtil.printLog("exe" + "mCheckedPosition  " + mCheckedPosition)
                    sizeData.get(mUnCheckedPosition).isPrimary = FALSE.toString()
                    selectItem.onClick(
                            sizeData.get(mCheckedPosition).childProductId)
                }
            }
        }
    }

    /**
     * view holder class for the cart items
     */
    class AddProductViewHolder(itemBinding: ItemVariantBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemVariantBinding

        init {
            mItemBinding = itemBinding
        }
    }
}