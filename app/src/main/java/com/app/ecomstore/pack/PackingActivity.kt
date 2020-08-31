package com.app.ecomstore.pack

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityPackingBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * This activity used to open the boxes details to show the all boxes.
 */
class PackingActivity : DaggerAppCompatActivity(), SelectItem {
    lateinit var mBinding: ActivityPackingBinding
    lateinit var mPackingViewModel: PackingViewModel
    lateinit var mBoxAdapter: SelectBoxAdapter
    val mBoxesData = ArrayList<BoxData>()
    private var mPosition: Int = MINUS_ONE
    lateinit var mItem: Products

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        getIntentData()
        subscribePartnerData()
        subscribeAttributeData()
        subscribeOnErrorData()
        subscribeOnSuccessData()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_packing)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.packing))
        mBinding.layoutButton.buttonCommon.setText(resources.getString(R.string.addProduct))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mBinding.layoutButton.buttonCommon.setOnClickListener(View.OnClickListener {
            if (mPosition != MINUS_ONE) {
                mPackingViewModel.addProduct(mBoxesData.get(mPosition).get_id(), mItem)
            } else {
                Utility.mShowMessage(resources.getString(R.string.message), resources.getString(R.string.pleaseSelectBox), this)
            }
        })
    }

    /**
     * <h2>initializeViewModel</h2>
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mPackingViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PackingViewModel::class.java)
        mBinding.viewModel = mPackingViewModel
        mBoxAdapter = SelectBoxAdapter(mBoxesData, this)
        mBinding.rvBoxes.adapter = mBoxAdapter
        mPackingViewModel.getPackageBox()
    }

    /**
     * used to show product data which is intent data.
     */
    private fun getIntentData() {
        mItem = intent.getParcelableExtra(ITEM_DATA)
        if (mItem != null) {
            if (mItem.images != null) {
                Glide.with(this)
                        .load(mItem.images.medium)
                        .into(mBinding.ivProductImg)
            }
            if (mItem.name != null) {
                mBinding.tvProductName.setText(mItem.name)
            }
            if (mItem.accounting.finalTotal != null) {
                mBinding.tvProductPrice.setText("${mItem.currencySymbol} ${mItem.accounting.finalTotal}")
            }
            if (mItem.accounting.finalUnitPrice != null) {
                val finalVlaue = "${mItem.quantity.value} * ${mItem.accounting.finalUnitPrice} / ${mItem.quantity.unit}"
                mBinding.tvTotalProductPrice.setText(finalVlaue)
            }
            mPackingViewModel.setAttributeData(mItem.attributes)
        }
    }

    /**
     * subscribe to partner data
     */
    private fun subscribePartnerData() {
        mPackingViewModel.onBoxesData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mBoxesData.addAll(it)
                mBoxAdapter.notifyDataSetChanged()
            }
        })
    }

    /**
     *
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        mPackingViewModel.onErrorData().observe(this, androidx.lifecycle.Observer {
            Utility.mShowMessage(resources.getString(R.string.message), it, this)
        })
    }

    /**
     *
     * This method is used to set the attribute data.
     */
    private fun subscribeAttributeData() {
        mPackingViewModel.getAttributeData().observe(this, androidx.lifecycle.Observer {
            mBinding.tvHistorySizeAndColor.text = it
        })
    }

    /**
     *
     * This method is used to set the error.
     */
    private fun subscribeOnSuccessData() {
        mPackingViewModel.onSuccessData().observe(this, androidx.lifecycle.Observer {
            finish()
        })
    }

    override fun onSelectItem(position: Int) {
        mPosition = position
        if (mPosition >= ZERO) {
            mBinding.clAddProduct.visibility = View.VISIBLE
        }
    }
}
