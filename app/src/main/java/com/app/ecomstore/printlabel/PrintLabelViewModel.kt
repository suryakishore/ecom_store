package com.app.ecomstore.printlabel

import android.content.Context
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.GenerateLableInput
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * view model class for the select drivers activity.
 */
class PrintLabelViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                              val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mPrintLabelData = MutableLiveData<Pair<String, ArrayList<LabelBags>>>()
    private val mErrorData = MutableLiveData<String>()
    private var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }

    /**
     * This method used to get the partners.
     */
    public fun printLables(orderId: String, lableCount: Int, currencySymbol: String, currencyCode: String) {
        progressVisible.set(TRUE)
        val generateLableInput = GenerateLableInput()
        generateLableInput.orderId = orderId
        generateLableInput.bags = lableCount
        networkService.also {
            it.generateLable(preferenceHelperDataSource.token,
                    preferenceHelperDataSource.language,
                    ANDROID_PLATFORM,
                    EcomUtil.getCurrencySymbol(currencySymbol),
                    currencyCode,
                    generateLableInput
            ).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it != null) {
                            try {
                                val jsonObject: JSONObject
                                val code = it.code()
                                Utility.printLog("ForcePickResponse : Code $code")
                                if (code == SUCCESS) {
                                    val response: String = it.body()!!.string()
                                    val gson = Gson()
                                    val labelsPojo = gson.fromJson(response, LabelsPojo::class.java)
                                    Utility.printLog("ForcePickResponse : Code $response")
                                    mPrintLabelData.postValue(Pair.create(labelsPojo.getData()!!.getShippingLabel(), labelsPojo.getData()!!.getBags()))
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(context.resources.getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    Utility.printLog("ForcePickResponse : $jsonObject")
                                }
                            } catch (e: JSONException) {
                                Utility.printLog("ForcePickResponse  JSONException: Catch :" + e.message)
                            } catch (e: IOException) {
                                Utility.printLog("ForcePickResponse IOException : Catch :" + e.localizedMessage)
                            }
                        }
                    }
        }
    }

    /**
     * notify activity partner data comes
     */
    fun onPrintLableData(): MutableLiveData<Pair<String, ArrayList<LabelBags>>> {
        return mPrintLabelData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

}