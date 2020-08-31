package com.app.ecomstore.customizations

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityCustomizationsBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes
import com.app.ecomstore.util.EcomConstants.ATTRIBUTE_DATA
import dagger.android.support.DaggerAppCompatActivity
import java.util.*

class CustomizationsActivity : DaggerAppCompatActivity() {
    lateinit var mBinding: ActivityCustomizationsBinding
    lateinit var customizationsAdapter: CustomizationsAdapter
    var mAttributeData = ArrayList<Attributes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customizations)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.customization))
        mBinding.layoutToolBar.imageViewAllBack.setImageDrawable(resources.getDrawable(R.drawable.ic_order_details_cross_icon))
        mAttributeData.clear()
        if (intent != null) {
            mAttributeData = intent.getParcelableArrayListExtra(ATTRIBUTE_DATA)
            customizationsAdapter = CustomizationsAdapter(mAttributeData)
            mBinding.rvCustomization.adapter = customizationsAdapter
        }
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}
