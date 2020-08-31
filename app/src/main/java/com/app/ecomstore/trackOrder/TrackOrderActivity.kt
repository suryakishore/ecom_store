package com.app.ecomstore.trackOrder

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityTrackOrderBinding
import com.app.ecomstore.util.EcomConstants.*
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * This activity used to show the tracking for the particular order.
 */
class TrackOrderActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mBinding: ActivityTrackOrderBinding
    lateinit var mTrackOrderViewModel: TrackOrderViewModel
    lateinit var trackOrderAdapter: TrackOrderAdapter
    val mTrackingData = ArrayList<TrackingOrderStatus>()
    private var viewStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeTrackingData()
    }


    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_track_order)
    }

    /**
     * <h2>initializeViewModel</h2>
     *
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mTrackOrderViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TrackOrderViewModel::class.java)
        mBinding.viewModel = mTrackOrderViewModel
        trackOrderAdapter = TrackOrderAdapter(mTrackingData)
        mBinding.rvDeliveryStatus.adapter = trackOrderAdapter
        mTrackOrderViewModel.getTracking(intent.getStringExtra(ORDER_ID))
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.trackingDetails))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    /**
     * subscribe to tracking data
     */
    private fun subscribeTrackingData() {
        mTrackOrderViewModel.onTrackingData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mTrackingData.addAll(it)
                setStatus()
            }
        })
    }

    /**
     * used to set the track status
     */
    private fun setStatus() {
        for (i in mTrackingData.indices) {
            val trackingItemData: TrackingOrderStatus = mTrackingData.get(i)
            if (trackingItemData.getFormatedDate()!!.isEmpty()) {
                val j: Int = i - ONE
                if (j > ZERO && !viewStatus) {
                    viewStatus = TRUE
                    val trackingPreviousItemData: TrackingOrderStatus = mTrackingData.get(j)
                    trackingPreviousItemData.setViewStatus2(TRUE)
                }
                trackingItemData.setViewStatus1(TRUE)
                trackingItemData.setViewStatus2(TRUE)
            }
        }
        trackOrderAdapter.notifyDataSetChanged()
    }
}
