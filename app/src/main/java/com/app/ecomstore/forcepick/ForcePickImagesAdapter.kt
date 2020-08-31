package com.app.ecomstore.forcepick

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ItemCameraBinding
import com.app.delivxstore.databinding.ItemPreviewImageBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.util.EcomConstants.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File


/**
 * adapter class for the partner  items.
 */
public class ForcePickImagesAdapter(imageData: ArrayList<String>, selectItem: SelectItem) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var mContext: Context
    private val TYPE_CAMERA: Int = TWO
    private val TYPE_IMAGE: Int = ONE
    private var isUrl: Boolean = false
    lateinit var mSelectItem: SelectItem
    var mImagesData: ArrayList<String>
    var options = RequestOptions()

    init {
        mImagesData = imageData
        mSelectItem = selectItem
    }

    /**
     * This method used to find whether the image is file or image
     */
    public fun isFileOrUrl(isFileOrUrl: Boolean) {
        this.isUrl = isFileOrUrl;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TYPE_IMAGE -> {
                val itemPreviewImageBinding: ItemPreviewImageBinding =
                        DataBindingUtil.inflate(layoutInflater, R.layout.item_preview_image, parent, false)
                return ImageViewHolder(itemPreviewImageBinding)
            }
            TYPE_CAMERA -> {
                val itemBinding: ItemCameraBinding =
                        DataBindingUtil.inflate(layoutInflater, R.layout.item_camera, parent, false)
                return CameraViewHolder(itemBinding)
            }
            else -> {
                val itemBinding: ItemCameraBinding =
                        DataBindingUtil.inflate(layoutInflater, R.layout.item_camera, parent, false)
                return CameraViewHolder(itemBinding)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mImagesData != null) mImagesData.size else ZERO
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            if (!isUrl) {
                Glide.with(mContext)
                        .load(File(mImagesData.get(position)))
                        .apply(options)
                        .into((holder as ImageViewHolder).mItemBinding.ivPreview)
            } else {
                Glide.with(mContext)
                        .load(mImagesData.get(position))
                        .apply(options)
                        .into((holder as ImageViewHolder).mItemBinding.ivPreview)
            }
            (holder as ImageViewHolder).mItemBinding.ivCross.setOnClickListener {
                mSelectItem.onSelectItem(position)
            }
        } else {
            (holder as CameraViewHolder).mItemBinding.ivCamera.setOnClickListener {
                mSelectItem.onSelectItem(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mImagesData.get(position) != null && mImagesData.get(position) ==
                CAMERA_ITEM) {
            TYPE_CAMERA
        } else TYPE_IMAGE
    }


    /**
     * view holder class for the image items
     */
    class ImageViewHolder(itemBinding: ItemPreviewImageBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemPreviewImageBinding

        init {
            mItemBinding = itemBinding
        }
    }

    /**
     * view holder class for the image items
     */
    class CameraViewHolder(itemBinding: ItemCameraBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var mItemBinding: ItemCameraBinding

        init {
            mItemBinding = itemBinding
        }
    }
}