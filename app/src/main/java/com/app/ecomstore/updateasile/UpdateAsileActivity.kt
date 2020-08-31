package com.app.ecomstore.updateasile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityUpdateAsileBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.ecomstore.updateasile.updateasilebottomsheet.UpdateAisleBtmSheet
import com.app.ecomstore.util.EcomConstants
import com.app.ecomstore.util.EcomConstants.*
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This activity is used to show the aisle for pharmacy product
 */
class UpdateAsileActivity : DaggerAppCompatActivity() {
    lateinit var mBinding: ActivityUpdateAsileBinding
    lateinit var mUpdateAisleModel: UpdateAisleViewModel
    lateinit var mItem: Products
    private var mPosition = ZERO

    @Inject
    lateinit var mUpdateAisleBtmSheet: UpdateAisleBtmSheet

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeOnErrorData()
        intializeData()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_asile)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.updateAisle))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mBinding.layoutButton.buttonCommon.text = resources.getString(R.string.updateAisle)
        mBinding.cvSection.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(UPDATE_TYPE, resources.getString(R.string.section))
            bundle.putInt(SELECTED_COUNT, mPosition)
            mUpdateAisleBtmSheet.arguments = bundle
            mUpdateAisleBtmSheet.show(supportFragmentManager, mUpdateAisleBtmSheet.getTag())
        }
        mBinding.tvItemAsileCount.setText(intent.getStringExtra(AISLE))
        mBinding.tvItemShelfCount.setText(intent.getStringExtra(SHELF))
        mBinding.tvItemSectionCount.setText(intent.getStringExtra(SECTOR))
        mBinding.layoutButton.buttonCommon.setOnClickListener {
            if (!mBinding.tvItemAsileCount.text.toString().isEmpty()) {
                if (!mBinding.tvItemShelfCount.text.toString().isEmpty()) {
                    mUpdateAisleModel.updateProduct(mBinding.tvItemAsileCount.text.toString(), intent.getStringExtra(PARENT_PRODUCT_ID),
                            mBinding.tvItemSectionCount.text.toString(), mBinding.tvItemShelfCount.text.toString())
                } else {
                }
            } else {
            }
        }
    }

    /**
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mUpdateAisleModel = ViewModelProviders.of(this, mViewModelFactory).get(UpdateAisleViewModel::class.java)
        mBinding.viewModel = mUpdateAisleModel
    }

    /**
     * get the intent data
     */
    private fun intializeData() {
        mItem = intent.getParcelableExtra(EcomConstants.ITEM_DATA)
        mBinding.itemPharmacyRow.hsItemUnAvailableStatus.visibility = View.GONE
        mBinding.itemPharmacyRow.groupAsile.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemTabletCount.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemQty.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemDosage.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemStripCount.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemUnAvailableProductPrice.visibility = View.GONE
        mBinding.itemPharmacyRow.viewItemUnAvailableDashed.visibility = View.GONE
        mBinding.itemPharmacyRow.tvItemUnAvailableProductName.text = mItem.name
        if (mItem.getImages() != null) {
            Glide.with(this)
                    .load(mItem.getImages().getMedium())
                    .into(mBinding.itemPharmacyRow.ivItemUnAvailableProductImage)
        }

    }

    /**
     * update the selected count
     */
    public fun updateCount(pos: Int, updateValue: String) {
        mPosition = pos
        mBinding.tvItemSectionCount.text = updateValue
    }

    /**
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        mUpdateAisleModel.onErrorData().observe(this, androidx.lifecycle.Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra(AISLE, mBinding.tvItemAsileCount.text.toString())
            intent.putExtra(SHELF, mBinding.tvItemShelfCount.text.toString())
            intent.putExtra(SECTOR, mBinding.tvItemSectionCount.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }
}