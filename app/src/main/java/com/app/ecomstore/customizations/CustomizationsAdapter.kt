package com.app.ecomstore.customizations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemCustomizationBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.ZERO


/**
 * adapter class for the partner  items.
 */
public class CustomizationsAdapter(attributeData: ArrayList<Attributes>) :
        RecyclerView.Adapter<CustomizationsAdapter.CustomizationsViewHolder>() {
    var mAttributeData: ArrayList<Attributes>
    lateinit var mContext: Context

    init {
        mAttributeData = attributeData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomizationsAdapter.CustomizationsViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCustomizationBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_customization, parent, false)
        return CustomizationsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (mAttributeData != null) mAttributeData.size else ZERO
    }

    override fun onBindViewHolder(holder: CustomizationsAdapter.CustomizationsViewHolder, position: Int) {
        val attributeData = mAttributeData.get(position)
        holder.mItemBinding.tvAttributeType.text = attributeData.attrname
        holder.mItemBinding.tvAttributeValue.text = attributeData.value
    }

    /**
     * view holder class for the cart items
     */
    class CustomizationsViewHolder(itemBinding: ItemCustomizationBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemCustomizationBinding

        init {
            mItemBinding = itemBinding
        }
    }
}