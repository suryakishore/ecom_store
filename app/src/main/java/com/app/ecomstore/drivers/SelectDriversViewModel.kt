package com.app.ecomstore.drivers

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
class SelectDriversViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                                 val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mDriverData = MutableLiveData<ArrayList<SelectDriverData>>()
    private val mErrorData = MutableLiveData<String>()
    private val mJobAssign = MutableLiveData<Boolean>()
    private lateinit var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }

    /**
     * This method used to get the partners.
     */
    public fun getDrivers(slotId: String) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(FROM, ZERO)
        map.put(TO, LIMIT)
        map.put(STATUS, THREE)
        map.put(CITY_ID, preferenceHelperDataSource.cityId)
        map.put(SLOT_ID, slotId)
        map.put(STORE_ID, "")
        if (slotId.isEmpty())
            map.put(SUPPORTED_DELIVERY_TYPE, ONE)
        networkService.also {
            it.getDrivers(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(), ANDROID_PLATFORM,
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
                                    jsonObject = JSONObject(response)
                                    val gson = Gson()
                                    val driverResponse = gson.fromJson(jsonObject.toString(), SelectDriverResponse::class.java)
                                    if (driverResponse != null) {
                                        mDriverData.postValue(driverResponse.getData())
                                    }
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(context.resources.getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    Utility.printLog("DriverResponse : $jsonObject")
                                }
                            } catch (e: JSONException) {
                                Utility.printLog("DriverResponse  JSONException: Catch :" + e.message)
                            } catch (e: IOException) {
                                Utility.printLog("DriverResponse IOException : Catch :" + e.localizedMessage)
                            }
                        }
                    }
        }
    }


    /**
     * This method used to show manually.
     */
    public fun assignManually(packageId: String, driverID: String?) {
        progressVisible.set(TRUE)
        val generateLabelRequest = GenerateLabelRequest()
        generateLabelRequest.setOrderId(packageId);
        generateLabelRequest.setDriverId(driverID);
        generateLabelRequest.setDispacthType(TWO)
        networkService.also {
            it.assignJob(
                    preferenceHelperDataSource.token,
                    preferenceHelperDataSource.getLanguage(), ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE,
                    generateLabelRequest
            ).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it != null) {
                            try {
                                val jsonObject: JSONObject
                                val code = it.code()
                                Utility.printLog("LoginResponse : Code $code")
                                if (code == SUCCESS) {
                                    mJobAssign.postValue(TRUE)
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(context.resources.getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    Utility.printLog("LoginResponse : $jsonObject")
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
    fun onDriverData(): MutableLiveData<ArrayList<SelectDriverData>> {
        return mDriverData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

    /**
     * notify activity when job
     */
    fun onJobAssign(): MutableLiveData<Boolean> {
        return mJobAssign
    }

    fun getStoreType(): Int {
        return preferenceHelperDataSource.storeType
    }

}