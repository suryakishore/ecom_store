package com.app.ecomstore.prescription

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityPrescriptionDetailBinding
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dagger.android.support.DaggerAppCompatActivity

/**
 * This activity is used to open the prescription detail
 */
class PrescriptionDetail : DaggerAppCompatActivity() {
    lateinit var mBinding: ActivityPrescriptionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        getIntentData()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_prescription_detail)
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    /**
     * get the intent data
     */
    private fun getIntentData() {
        val receiptImg = intent.getBooleanExtra(RECEIPT_IMG, FALSE)
        if (!receiptImg) {
            val prescriptionImg = intent.getStringExtra(PRESCRIPTION_IMG)
            if (prescriptionImg != null) {
                mBinding.progressBar.setVisibility(View.VISIBLE)
                Glide.with(this)
                        .applyDefaultRequestOptions(RequestOptions().centerCrop())
                        .load(prescriptionImg.trim())
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(e: GlideException?, model: Any,
                                                      target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                mBinding.progressBar.setVisibility(View.GONE)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>,
                                                         dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                mBinding.progressBar.setVisibility(View.GONE)
                                return false
                            }
                        }).into(mBinding.ivPrescriptionItem)
            }
        } else {
            mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.receipt))
            mBinding.rvReceiptImages.setVisibility(View.VISIBLE)
            mBinding.ivPrescriptionItem.setVisibility(View.GONE)
            val receiptImg = intent.getSerializableExtra(PRESCRIPTION_IMG) as ArrayList<String>
            EcomUtil.printLog("exe" + "receiptImg    " + receiptImg)
            val receiptImagesAdapter = ReceiptImagesAdapter(receiptImg)
            mBinding.rvReceiptImages.adapter = receiptImagesAdapter
        }
    }
}