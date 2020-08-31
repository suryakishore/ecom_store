package com.app.ecomstore.trackOrder

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * view model class for the select drivers activity.
 */
class TrackOrderViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                              val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mTrackingData = MutableLiveData<ArrayList<TrackingOrderStatus>>()
    private val mErrorData = MutableLiveData<String>()
    private val mJobAssign = MutableLiveData<Boolean>()

    /**
     * This method used to get the tracking.
     */
    public fun getTracking(productOrderID: String) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(ORDER_ID, productOrderID)
        networkService.also {
            it.getTracking(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail),
                    preferenceHelperDataSource.getLanguage(),
                    ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE,
                    map).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it != null) {
                            try {
                                val jsonObject: JSONObject
                                val code = it.code()
                                Utility.printLog("LoginResponse : Code $code")
                                if (code == SUCCESS) {
                                    val response: String = it.body()!!.string()
                                    val gson = Gson()
                                    val driverResponse = gson.fromJson(response, TrackingResponse::class.java)
                                    if (driverResponse != null) {
                                        if (driverResponse.getData() != null)
                                            mTrackingData.postValue(driverResponse.getData()?.getOrderStatus())
                                    }
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(context.resources.getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    mErrorData.postValue(jsonObject.toString())
                                }
                            } catch (e: JSONException) {
                                Utility.printLog("LoginResponse  JSONException: Catch :" + e.message)
                            } catch (e: IOException) {
                                Utility.printLog("LoginResponse IOException : Catch :" + e.localizedMessage)
                            }
                        }
                    }
        }
    }


    /**
     * notify activity partner data comes
     */
    fun onTrackingData(): MutableLiveData<ArrayList<TrackingOrderStatus>> {
        return mTrackingData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }


}