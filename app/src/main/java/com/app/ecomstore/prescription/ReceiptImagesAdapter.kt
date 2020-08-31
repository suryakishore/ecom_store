package com.app.ecomstore.prescription

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemReceiptImageBinding
import com.app.ecomstore.util.EcomConstants.ZERO
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * adapter class for the partner  items.
 */
public class ReceiptImagesAdapter(imageData: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var mContext: Context

    var mImagesData: ArrayList<String>
    var options = RequestOptions()

    init {
        mImagesData = imageData
        options = RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.ic_login_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPreviewImageBinding: ItemReceiptImageBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_receipt_image, parent, false)
        return ImageViewHolder(itemPreviewImageBinding)
    }

    override fun getItemCount(): Int {
        return if (mImagesData != null) mImagesData.size else ZERO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            Glide.with(mContext)
                    .load(mImagesData.get(position))
                    .apply(options)
                    .into((holder as ImageViewHolder).mItemBinding.ivReceipt)
        }
    }


    /**
     * view holder class for the image items
     */
    class ImageViewHolder(itemBinding: ItemReceiptImageBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemReceiptImageBinding

        init {
            mItemBinding = itemBinding
        }
    }


}