package com.app.ecomstore.substitute

import android.content.Context
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.AddProductService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

/**
 * view model class for the select drivers activity.
 */
class SubStitudeViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: AddProductService,
                                              val context: Context) : ViewModel() {
    val progressVisible = ObservableField(FALSE)
    private val mSubstitudeData = MutableLiveData<Pair<Int, ArrayList<ProductSubStituteData>>>()
    private val mErrorData = MutableLiveData<String>()
    private val mNoDataFound = MutableLiveData<Boolean>()
    private lateinit var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }

    /**
     * This method used to get the partners.
     */
    public fun getSubStitudeProducts(productId: String, parentProductId: String, page: Int) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(PARENT_PRODUCT_ID, parentProductId)
        map.put(PRODUCT_ID_TXT, productId)
        map.put(PAGE, page)
        networkService.also {
            it.getSubStitudeProduct(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(),
                    map).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it != null) {
                            try {
                                val jsonObject: JSONObject
                                val code = it.code()
                                Utility.printLog("addProductResponse : Code $code")
                                if (code == SUCCESS) {
                                    val response: String = it.body()!!.string()
                                    Utility.printLog("addProductResponse : response $response")
                                    jsonObject = JSONObject(response)
                                    val gson = Gson()
                                    val productSubStituteResponse = gson.fromJson(response, ProductSubStituteResponse::class.java)
                                    if (productSubStituteResponse.data != null) {
                                        Utility.printLog("addProductResponse : response $response")
                                        mSubstitudeData.postValue(Pair.create(productSubStituteResponse.totalCount?.toInt(), productSubStituteResponse.data))
                                    }
                                } else if (code == DATA_NOT_FOUND) {
                                    mNoDataFound.postValue(TRUE)
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(context.resources.getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    Utility.printLog("addProductResponse : $jsonObject")
                                }
                            } catch (e: JSONException) {
                                Utility.printLog("addProductResponse  JSONException: Catch :" + e.message)
                            } catch (e: Exception) {
                                Utility.printLog("addProductResponse Exception : Catch :" + e.message)
                            }
                        }
                    }
        }
    }


    /**
     * notify activity partner data comes
     */
    fun subStituteData(): MutableLiveData<Pair<Int, ArrayList<ProductSubStituteData>>> {
        return mSubstitudeData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }


}