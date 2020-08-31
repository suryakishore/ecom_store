package com.app.ecomstore.addproduct

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
public class AddProductAdapter(parnerData: ArrayList<ProductData>, selectItem: SelectItem) : RecyclerView.Adapter<AddProductAdapter.AddProductViewHolder>() {
    var selectItem: SelectItem
    var addProdcuctData: ArrayList<ProductData>
    lateinit var mContext: Context

    init {
        addProdcuctData = parnerData
        this.selectItem = selectItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductAdapter.AddProductViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemAddProductBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_add_product, parent, false)
        return AddProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (addProdcuctData != null) addProdcuctData.size else ZERO
    }

    override fun onBindViewHolder(holder: AddProductAdapter.AddProductViewHolder, position: Int) {
        val addProductData = addProdcuctData.get(position)
        if (addProductData != null) {
            holder.mItemBinding.tvItemName.text = addProductData.productName
            if (addProductData.brandTitle != null && !addProductData.brandTitle?.isEmpty()!!) {
                holder.mItemBinding.tvItemBrand.text = addProductData.brandTitle
            } else {
                holder.mItemBinding.tvItemBrand.visibility = View.GONE
            }
            if (addProductData.finalPriceList != null) {
                if (addProductData.finalPriceList?.basePrice != null && addProductData.finalPriceList?.discountPrice!!.toDouble() > ZERO) {
                    holder.mItemBinding.tvItemScratchPrice.visibility = View.VISIBLE
                    holder.mItemBinding.tvItemScratchPrice.text = String.format("%s %s", addProductData.currencySymbol,
                            Utility.roundOfDoubleValue(addProductData.finalPriceList?.basePrice))
                    EcomUtil.strikeThroughText(holder.mItemBinding.tvItemScratchPrice)
                }
                holder.mItemBinding.tvItemPrice.text = String.format("%s %s", addProductData.currencySymbol, Utility.roundOfDoubleValue(addProductData.finalPriceList?.finalPrice))
            }
            if (addProductData.images != null && addProductData.images!!.size > ZERO) {
                Glide.with(mContext)
                        .applyDefaultRequestOptions(RequestOptions().override(70, 70).centerCrop())
                        .load(addProductData.images!!.get(ZERO).medium)
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