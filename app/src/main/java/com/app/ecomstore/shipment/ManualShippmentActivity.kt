package com.app.ecomstore.shipment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityManualShippmentBinding
import com.app.ecomstore.partner.SelectPartnerActivity
import com.app.ecomstore.util.EcomConstants.PARTNER_NAME
import com.app.ecomstore.util.EcomConstants.SELECT_PARTNER
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import javax.inject.Inject

/**
 * this class is used to open the manual shipment screen to enter partner and tracking number.
 */
class ManualShippmentActivity : DaggerAppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mBinding: ActivityManualShippmentBinding
    lateinit var manualShippmentViewModel: ManualShippmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_manual_shippment)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.manualShipping))
        mBinding.layoutButton.buttonCommon.setText(resources.getString(R.string.confirmButton))
        mBinding.layoutButton.buttonCommon.setOnClickListener(this)
        mBinding.tiManualShippmentPartner.setOnClickListener(this)
        mBinding.cvToolBar.imageViewAllBack.setOnClickListener(this)
    }

    /**
     * <h2>initializeViewModel</h2>
     *
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        manualShippmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ManualShippmentViewModel::class.java)
        mBinding.viewModel = manualShippmentViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PARTNER) {
                val partnerName = data.getStringExtra(PARTNER_NAME)
                mBinding.etManualShippmentPartner.setText(partnerName)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tiManualShippmentPartner -> {
                val intent = Intent(this, SelectPartnerActivity::class.java)
                startActivityForResult(intent, SELECT_PARTNER)
            }
            R.id.buttonCommon -> {

            }
            R.id.imageViewAllBack -> {
                finish()
            }
        }
    }
}
