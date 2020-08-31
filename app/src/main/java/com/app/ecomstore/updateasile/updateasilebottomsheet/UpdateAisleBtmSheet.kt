package com.app.ecomstore.updateasile.updateasilebottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.delivxstore.R
import com.app.delivxstore.databinding.FragmentBtmSheetUpdateAisleBinding
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.ecomstore.updateasile.UpdateAsileActivity
import com.app.ecomstore.util.EcomConstants.SELECTED_COUNT
import com.app.ecomstore.util.EcomConstants.UPDATE_TYPE
import com.app.ecomstore.util.EcomUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class UpdateAisleBtmSheet @Inject constructor() : BottomSheetDialogFragment(), SelectItem {
    lateinit var mBinding: FragmentBtmSheetUpdateAisleBinding
    private val aisleData = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_btm_sheet_update_aisle,
                container, false)
        initViews()
        return mBinding.root
    }

    /**
     * initialize the views.
     */
    private fun initViews() {
        mBinding.tvUpdateType.text = "${resources.getString(R.string.Update)} ${arguments!!.getString(UPDATE_TYPE)}"
        EcomUtil.printLog("exe" + "position  " + arguments!!.getInt(SELECTED_COUNT))
        aisleData.clear()
        aisleData.add(resources.getString(R.string.near))
        aisleData.add(resources.getString(R.string.mid))
        aisleData.add(resources.getString(R.string.far))
        val updateAisleAdapter = UpdateAisleAdapter(aisleData, arguments!!.getInt(SELECTED_COUNT), this)
        mBinding.rvUpdateAisle.adapter = updateAisleAdapter
    }

    override fun onSelectItem(position: Int) {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            if (activity != null) {
                (activity as UpdateAsileActivity).updateCount(position, aisleData.get(position))
            }
        }
    }
}