package com.app.ecomstore.updateasile

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.UpdateProductService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * view model class for the update alise activity.
 */
class UpdateAisleViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: UpdateProductService,
                                               val context: Context) : ViewModel() {
    val progressVisible = ObservableField(FALSE)
    private var preferences: PreferenceHelperDataSource
    private val mErrorData = MutableLiveData<String>()
    private var mNetworkService: UpdateProductService

    init {
        preferences = preferenceHelperDataSource
        mNetworkService = networkService
    }

    /**
     * This method used to update the product
     *
     */
    public fun updateProduct(aisle: String, parentProductId: String, section: String, shelf: String) {
        progressVisible.set(TRUE)
        val updateAisleReq = UpdateAisleRequest()
        updateAisleReq.setAisle(aisle)
        updateAisleReq.setParentProductId(parentProductId)
        updateAisleReq.setSection(section)
        updateAisleReq.setShelf(shelf)
        updateAisleReq.storeId = preferenceHelperDataSource.storeId
        mNetworkService.also {
            it.updateOrder(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferences.myEmail),
                    updateAisleReq).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        EcomUtil.printLog("exe" + "it  " + it)
                        if (it != null) {
                            try {
                                val jsonObject: JSONObject
                                val code = it.code()
                                Utility.printLog("LoginResponse : Code $code")
                                if (code == SUCCESS) {
                                    val response: String = it.body()!!.string()
                                    jsonObject = JSONObject(response)
                                    mErrorData.postValue(jsonObject.getString(MESSAGE))
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
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

}