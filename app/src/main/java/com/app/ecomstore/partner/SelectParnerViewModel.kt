package com.app.ecomstore.partner

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants
import com.app.ecomstore.util.EcomConstants.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * view model class for the select partner activity.
 */
class SelectParnerViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                                val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mParnerData = MutableLiveData<ArrayList<SelectPartnerData>>()
    private val mErrorData = MutableLiveData<String>()

    /**
     * This method used to get the partners.
     */
    public fun getPartners() {
        progressVisible.set(TRUE)
        networkService.also {
            it.getPartners(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(), EcomConstants.ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE).observeOn(AndroidSchedulers.mainThread())
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
                                    val parnerResponse = gson.fromJson(jsonObject.toString(), SelectPartnerResponse::class.java)
                                    if (parnerResponse != null) {
                                        mParnerData.postValue(parnerResponse.getData())
                                    }
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
    fun onPartnerData(): MutableLiveData<ArrayList<SelectPartnerData>> {
        return mParnerData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

}