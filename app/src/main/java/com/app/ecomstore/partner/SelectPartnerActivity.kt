package com.app.ecomstore.partner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivitySelectPartnerBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.PARTNER_NAME
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * This activity is used to select one of the partners available.
 */
class SelectPartnerActivity : DaggerAppCompatActivity(), SelectItem {
    lateinit var mBinding: ActivitySelectPartnerBinding
    lateinit var mSelectParnerViewModel: SelectParnerViewModel
    lateinit var selectPartnerAdapter: SelectPartnerAdapter
    val mParnerData = ArrayList<SelectPartnerData>()

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribePartnerData()
        subscribeOnErrorData()
    }


    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_partner)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.selectPartner))
        mBinding.layoutToolBar.imageViewAllBack.setImageDrawable(resources.getDrawable(R.drawable.ic_order_details_cross_icon))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    /**
     * <h2>initializeViewModel</h2>
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mSelectParnerViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SelectParnerViewModel::class.java)
        mBinding.viewModel = mSelectParnerViewModel
        selectPartnerAdapter = SelectPartnerAdapter(mParnerData, this)
        mBinding.rvSelectPartner.adapter = selectPartnerAdapter
        mSelectParnerViewModel.getPartners()
    }

    /**
     * subscribe to partner data
     */
    private fun subscribePartnerData() {
        mSelectParnerViewModel.onPartnerData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mParnerData.addAll(it)
                selectPartnerAdapter.notifyDataSetChanged()
            }
        })
    }


    /**
     *
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        mSelectParnerViewModel.onErrorData().observe(this, androidx.lifecycle.Observer {

            Utility.mShowMessage(resources.getString(R.string.message), it, this)

        })
    }

    override fun onSelectItem(position: Int) {
        val intent = Intent()
        intent.putExtra(PARTNER_NAME, mParnerData.get(position).getName())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
