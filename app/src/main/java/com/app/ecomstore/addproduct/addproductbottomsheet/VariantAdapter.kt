package com.app.ecomstore.addproduct.addproductbottomsheet

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemVariantsBottomSheetBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.ZERO
import java.lang.String
import java.util.function.Predicate


/**
 * adapter class for the partner  items.
 */
public class VariantAdapter(variantData: ArrayList<VariantData>, selectItem: ProductAttributesClick) : RecyclerView.Adapter<VariantAdapter.AddProductViewHolder>() {
    var selectItem: ProductAttributesClick
    var variantData: ArrayList<VariantData>
    lateinit var mContext: Context

    init {
        this.variantData = variantData
        this.selectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantAdapter.AddProductViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemVariantsBottomSheetBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_variants_bottom_sheet, parent, false)
        return AddProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (variantData != null) variantData.size else ZERO
    }

    override fun onBindViewHolder(holder: VariantAdapter.AddProductViewHolder, position: Int) {
        val varaintData = variantData.get(position)
        if (varaintData != null) {
            val variantsItemAdapter = VariantsItemAdapter(varaintData.sizeData!!, selectItem)
            holder.mItemBinding.rvVariants.adapter = variantsItemAdapter
            holder.mItemBinding.tvAttributeName.text = varaintData.keyName
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val sizeData = ArrayList<SizeData>()
                sizeData.clear()
                sizeData.addAll(varaintData.sizeData!!)
                val condition = Predicate<SizeData> { !it.isPrimary!!.toBoolean() }
                sizeData.removeIf(condition)
                if (sizeData.size > ZERO) {
                    holder.mItemBinding.tvAttributeValue.setText(
                            String.format(": %s", sizeData[ZERO].size))
                } else {
                    holder.mItemBinding.tvAttributeValue.visibility = View.GONE
                }
            }
        }
    }

    /**
     * view holder class for the cart items
     */
    class AddProductViewHolder(itemBinding: ItemVariantsBottomSheetBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemVariantsBottomSheetBinding

        init {
            mItemBinding = itemBinding
        }
    }
}