package com.app.ecomstore.drivers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivitySelectDriversBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.Utility
import com.app.delivxstore.utility.VariableConstants.ZERO
import com.app.ecomstore.util.EcomConstants.*
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * This activity is used to select one of the drivers available.
 */
class SelectDriversActivity : DaggerAppCompatActivity(), SelectItem {

    lateinit var mBinding: ActivitySelectDriversBinding
    lateinit var mSelectDriversViewModel: SelectDriversViewModel
    lateinit var selectDriverAdapter: SelectDriverAdapter
    val mDriverData = ArrayList<SelectDriverData>()
    private var mPosition: Int = MINUS_ONE

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribePartnerData()
        subscribeOnErrorData()
        subscribeOnAssignJob()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_drivers)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.selectDriver))
        mBinding.layoutButton.buttonCommon.setText(resources.getString(R.string.assignedDriver))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mBinding.layoutButton.buttonCommon.setOnClickListener(View.OnClickListener {
            if (mPosition != MINUS_ONE) {
                if (intent.getBooleanExtra(ASSIGN_MANUALLY, FALSE)) {
                    mSelectDriversViewModel.assignManually(intent.getStringExtra(PACKAGE_ID), mDriverData.get(mPosition).get_id())
                } else
                    assignJob()
            } else {
                Utility.mShowMessage(resources.getString(R.string.message), resources.getString(R.string.pleaseSelectDriver), this)
            }
        })
    }

    /**
     * <h2>initializeViewModel</h2>
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mSelectDriversViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SelectDriversViewModel::class.java)
        mBinding.viewModel = mSelectDriversViewModel
        selectDriverAdapter = SelectDriverAdapter(mDriverData, this)
        mBinding.rvSelectPartner.adapter = selectDriverAdapter
        mSelectDriversViewModel.getDrivers(intent.getStringExtra(SLOT_ID))
    }

    /**
     * subscribe to partner data
     */
    private fun subscribePartnerData() {
        mSelectDriversViewModel.onDriverData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mDriverData.addAll(it)
                selectDriverAdapter.notifyDataSetChanged()
                mBinding.tvDriversAvailable.visibility = if (mDriverData.size > ZERO) VISIBLE else GONE
            }
        })
    }

    /**
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        mSelectDriversViewModel.onErrorData().observe(this, androidx.lifecycle.Observer {
            Utility.mShowMessage(resources.getString(R.string.message), it, this)
        })
    }

    /**
     *
     * subscribe to assign job data
     */
    private fun subscribeOnAssignJob() {
        mSelectDriversViewModel.onJobAssign().observe(this, androidx.lifecycle.Observer {
            val intent = Intent()
            intent.putExtra(FINISH, TRUE)
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }

    /**
     *
     * This method is used finish activity when we assign the job.
     */
    private fun assignJob() {
        val intent = Intent()
        intent.putExtra(FINISH, TRUE)
        intent.putExtra(DRIVER_ID, mDriverData.get(mPosition).get_id())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onSelectItem(position: Int) {
        mPosition = position;
    }
}
