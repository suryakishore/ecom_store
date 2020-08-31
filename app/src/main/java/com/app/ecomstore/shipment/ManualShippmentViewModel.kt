package com.app.ecomstore.shipment

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.ecomstore.util.EcomConstants.FALSE
import javax.inject.Inject

/**
 * view model class for manual shipment activity.
 */
class ManualShippmentViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                                   val context: Context) : ViewModel() {

    val errorMailMsg = ObservableField(FALSE)


}