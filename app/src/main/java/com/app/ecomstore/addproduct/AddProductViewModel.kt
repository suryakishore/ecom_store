package com.app.ecomstore.addproduct

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
class AddProductViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: AddProductService,
                                              val context: Context) : ViewModel() {
    val progressVisible = ObservableField(FALSE)
    private val mAddProductData = MutableLiveData<Pair<Int, ArrayList<ProductData>>>()
    private val mPagedData = MutableLiveData<ArrayList<ProductData>>()
    private val mErrorData = MutableLiveData<String>()
    private val mNoDataFound = MutableLiveData<Boolean>()
    private lateinit var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }

    /**
     * This method used to get the partners.
     */
    public fun getAddProducts(searchItem: String, page: Int) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(SEARCH_ITEM, searchItem)
        map.put(PAGE, page)
        map.put(STORE_ID_TXT, preferenceHelperDataSource.storeId)
        networkService.also {
            it.getSuggetions(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(),
                    preferenceHelperDataSource.categoryId, map).observeOn(AndroidSchedulers.mainThread())
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
                                    val addProductResponse = gson.fromJson(response, AddProductResponse::class.java)
                                    if (addProductResponse.data != null) {
                                        Utility.printLog("addProductResponse : response $response")
                                        if (page > ONE) {
                                            mPagedData.postValue(addProductResponse.data?.data)
                                        } else
                                            mAddProductData.postValue(Pair.create(addProductResponse.data?.penCount?.toInt(), addProductResponse.data?.data))
                                    }
                                } else if (code == DATA_NOT_FOUND) {
                                    if (page == ONE)
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
    fun onAddProductData(): MutableLiveData<Pair<Int, ArrayList<ProductData>>> {
        return mAddProductData
    }

    /**
     * notify activity partner data comes
     */
    fun onPagedData(): MutableLiveData<ArrayList<ProductData>> {
        return mPagedData
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
    fun onDataNotFound(): MutableLiveData<Boolean> {
        return mNoDataFound
    }

    fun getStoreType(): Int {
        return preferenceHelperDataSource.storeType
    }

}