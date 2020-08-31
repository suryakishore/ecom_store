package com.app.ecomstore.substitute

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemAddProductBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.ZERO
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/**
 * adapter class for the partner  items.
 */
public class SubStitudesAdapter(substitudeData: ArrayList<ProductSubStituteData>, selectItem: SelectItem) : RecyclerView.Adapter<SubStitudesAdapter.AddProductViewHolder>() {
    var selectItem: SelectItem
    var productSubstitudeData: ArrayList<ProductSubStituteData>
    lateinit var mContext: Context

    init {
        productSubstitudeData = substitudeData
        this.selectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubStitudesAdapter.AddProductViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemAddProductBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_add_product, parent, false)
        return AddProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (productSubstitudeData != null) productSubstitudeData.size else ZERO
    }

    override fun onBindViewHolder(holder: SubStitudesAdapter.AddProductViewHolder, position: Int) {
        val productData = productSubstitudeData.get(position)
        holder.mItemBinding.tvAdd.text = mContext.resources.getString(R.string.select)
        if (productData != null) {
            holder.mItemBinding.tvItemName.text = productData.productName
            holder.mItemBinding.tvItemBrand.text = productData.brandTitle
            if (productData.finalPriceList != null) {
                if (productData.finalPriceList?.basePrice != null && productData.finalPriceList?.discountPrice!!.toDouble() > ZERO) {
                    holder.mItemBinding.tvItemScratchPrice.visibility = View.VISIBLE
                    holder.mItemBinding.tvItemScratchPrice.text = String.format("%s %s", productData.currency, Utility.roundOfDoubleValue(productData.finalPriceList?.basePrice))
                    EcomUtil.strikeThroughText(holder.mItemBinding.tvItemScratchPrice)
                }
                holder.mItemBinding.tvItemPrice.text = String.format("%s %s", productData.currency, Utility.roundOfDoubleValue(productData.finalPriceList?.finalPrice))
            }
            if (productData.images != null) {
                Glide.with(mContext)
                        .applyDefaultRequestOptions(RequestOptions().override(70, 70).centerCrop())
                        .load(productData.images?.medium)
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any,
                                                      target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                holder.mItemBinding.progressBar.setVisibility(View.GONE)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>,
                                                         dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                holder.mItemBinding.progressBar.setVisibility(View.GONE)
                                return false
                            }
                        }).into(holder.mItemBinding.ivItemAddProduct)
            }
            holder.mItemBinding.tvAdd.setOnClickListener {
                selectItem.onSelectItem(position)
            }
        }
    }

    /**
     * view holder class for the cart items
     */
    class AddProductViewHolder(itemBinding: ItemAddProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemAddProductBinding

        init {
            mItemBinding = itemBinding
        }
    }
}