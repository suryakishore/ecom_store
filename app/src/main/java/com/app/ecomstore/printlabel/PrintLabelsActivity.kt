package com.app.ecomstore.printlabel

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityPrintLabelsBinding
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.webview.WebViewActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * this activity is used to enter the number of bags
 */
class PrintLabelsActivity : DaggerAppCompatActivity() {
    lateinit var mBinding: ActivityPrintLabelsBinding
    lateinit var mViewModel: PrintLabelViewModel
    lateinit var mPhoneNumber: String

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        subscribePrintLable()
    }

    /**
     * This method is used to force pick
     */
    private fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_print_labels)
        mBinding.layoutButton.buttonCommon.setText(resources.getString(R.string.printLabel))
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PrintLabelViewModel::class.java)
        mBinding.viewModel = mViewModel
        if (intent != null) {
            mBinding.layoutToolBar.textViewAllTitle.setText(String.format("%s :%s", getResources().getString(R.string.id), intent.getStringExtra(ORDER_ID)))
            mBinding.textViewOrderDetailsCustomerName.setText(intent.getStringExtra(CUSTOMER_NAME))
            mPhoneNumber = intent.getStringExtra(CUSTOMER_NUMBER)
            mBinding.tvItemPrice.text = intent.getStringExtra(ITEM_PRICE) + " | " +
                    intent.getIntExtra(QUANTITY, ZERO) + " " + if (intent.getIntExtra(QUANTITY, ZERO) > ONE) resources.getString(R.string.items) else
                resources.getString(R.string.item)
        }
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener {
            finish()
        }
        mBinding.cvCall.setOnClickListener {
            checkCallPermission()
        }
        mBinding.layoutButton.buttonCommon.setOnClickListener {
            if (!mBinding.etNoOfBags.text.toString().isEmpty()) {
                mViewModel.printLables(getIntent().getStringExtra(ORDER_ID), mBinding.etNoOfBags.text.toString().toInt(),
                        intent.getStringExtra(CURRENCY_SYMBOL), intent.getStringExtra(CURRENCY_CODE))
            } else {
                Utility.mShowMessage(resources.getString(R.string.message),
                        resources.getString(R.string.pleaseEnterBagCount), this)
            }
        }
    }

    /**
     * subscribe to print label data
     */
    private fun subscribePrintLable() {
        mViewModel.onPrintLableData().observe(this, Observer {
            closeKeyboard()
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(BAG_COUNT, it.second as ArrayList<LabelBags>)
            intent.putExtra(SHIPPING_LABEL, it.first)
            startActivityForResult(intent, WEB_VIEW_REQUEST)
        })
    }

    /**
     * open key pad once permission accepted.
     *
     * @param number string
     */
    private fun checkCallPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(
                            this, arrayOf(Manifest.permission.CALL_PHONE),
                            MAKE_PHONE_CALL)
        } else {
            Utility.callToNumber(mPhoneNumber, this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults)
        if (requestCode == MAKE_PHONE_CALL) {
            // Checking whether user granted the permission or not.
            if (grantResults.size > ZERO
                    && grantResults[ZERO] == PackageManager.PERMISSION_GRANTED) {
                // Showing the toast message
                Utility.callToNumber(mPhoneNumber, this)
            }
        }
    }

    /**
     * This method used to close the keyboard
     */
    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == WEB_VIEW_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    val listItems: ArrayList<LabelBags> = data.getParcelableArrayListExtra(BAG_COUNT)
                    val intent = Intent()
                    intent.putExtra(BAG_COUNT, listItems)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}