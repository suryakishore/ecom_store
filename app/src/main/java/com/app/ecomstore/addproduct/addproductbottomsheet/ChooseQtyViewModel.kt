package com.app.ecomstore.addproduct.addproductbottomsheet

import android.content.Context
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.UnavailableItemRequest
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.UnavailableOrderRequest
import com.app.delivxstore.networking.AddProductService
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.delivxstore.utility.VariableConstants
import com.app.ecomstore.util.EcomConstants.*
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * view model class for the select drivers activity.
 */
class ChooseQtyViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: AddProductService,
                                             val context: Context, private var addProductService: NetworkService) : ViewModel() {
    val progressVisible = ObservableField(FALSE)
    private val mVariantData = MutableLiveData<Pair<VariantProductData, ArrayList<VariantData>>>()
    val errorQuantityMsg = ObservableField(FALSE)
    val errorWeightMsg = ObservableField(FALSE)
    private val mErrorData = MutableLiveData<String>()
    private val mNoDataFound = MutableLiveData<Boolean>()
    private val mAddedProduct = MutableLiveData<Boolean>()
    private var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }

    /**
     * This method used to get the partners.
     */
    public fun getVariants(productId: String) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(PRODUCT_ID_TXT, productId)
        map.put(STORE_ID, preferenceHelperDataSource.storeId)
        networkService.also {
            it.getVariants(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(), map)
                    .observeOn(AndroidSchedulers.mainThread())
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
                                    val variantResponse = gson.fromJson(response, VariantResponse::class.java)
                                    if (variantResponse.data != null) {
                                        Utility.printLog("addProductResponse : response $response")
                                        mVariantData.postValue(Pair.create(variantResponse.product, variantResponse.data))
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
     * This method used to get the partners.
     */
    public fun getChildVariants(productId: String, childProductId: String) {
        progressVisible.set(TRUE)
        val map = HashMap<String, Any>()
        map.put(PRODUCT_ID_TXT, productId)
        map.put(CHILD_PRODUCT_ID, childProductId)
        map.put(STORE_ID, preferenceHelperDataSource.storeId)
        networkService.also {
            it.getChildVariants(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail), preferenceHelperDataSource.getLanguage(), map)
                    .observeOn(AndroidSchedulers.mainThread())
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
                                    val variantResponse = gson.fromJson(response, VariantResponse::class.java)
                                    if (variantResponse.data != null) {
                                        Utility.printLog("addProductResponse : response $response")
                                        mVariantData.postValue(Pair.create(variantResponse.product, variantResponse.data))
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
     * This method used to get the partners.
     */
    public fun addToCartProduct(productId: String, storeOrderId: String, centralProductId: String, quantity: Int) {
        progressVisible.set(TRUE)
        val newItemUnavailableItemRequest = UnavailableItemRequest()
        newItemUnavailableItemRequest.quantity = quantity
        newItemUnavailableItemRequest.centralProductId = centralProductId
        newItemUnavailableItemRequest.productId = productId
        newItemUnavailableItemRequest.unitId = ""
        val unavailableOrderRequest = UnavailableOrderRequest()
        unavailableOrderRequest.orderId = storeOrderId
        unavailableOrderRequest.extraNote = ""
        unavailableOrderRequest.updateType = THREE
        unavailableOrderRequest.ipAddress = Utility.getIpAddress(context)
        unavailableOrderRequest.latitude = VariableConstants.CURRENT_ZONE_LAT.toString()
        unavailableOrderRequest.longitude = VariableConstants.CURRENT_ZONE_LONGI.toString()
        unavailableOrderRequest.newItems = newItemUnavailableItemRequest

        val userHistory: Observable<Response<ResponseBody>> = addProductService.setUnavailabilityOrder(
                preferenceHelperDataSource.token,
                preferenceHelperDataSource.language,
                ANDROID_PLATFORM,
                CURRENCY_SYMBOL,
                CURRENCY_CODE,
                unavailableOrderRequest)
        userHistory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Response<ResponseBody>> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(value: Response<ResponseBody>) {
                        progressVisible.set(FALSE)
                        try {
                            val jsonObject: JSONObject
                            if (value.code() == SUCCESS) {
                                mAddedProduct.postValue(TRUE)
                            } else {
                                jsonObject = JSONObject(value.errorBody()!!.string())
                                Utility.printLog("userHistory : $jsonObject")
                            }
                        } catch (e: JSONException) {
                            Utility.printLog("userHistory : Catch :" + e.message)
                        } catch (e: IOException) {
                            Utility.printLog("userHistory : Catch :" + e.message)
                        }
                    }

                    override fun onError(e: Throwable) {

                        Utility.printLog("userHistory : onError :" + e.message)
                    }

                    override fun onComplete() {

                    }
                })
    }


    /**
     * This method used to get the partners.
     */
    public fun replaceProduct(productId: String, storeOrderId: String, centralProductId: String, quantity: Int, oldProductId: String, oldParentProductId: String) {
        progressVisible.set(TRUE)
        val itemUnavailableItemRequest = UnavailableItemRequest()
        itemUnavailableItemRequest.centralProductId = oldParentProductId
        itemUnavailableItemRequest.productId = oldProductId
        itemUnavailableItemRequest.unitId = ""
        val newItemUnavailableItemRequest = UnavailableItemRequest()
        newItemUnavailableItemRequest.quantity = quantity
        newItemUnavailableItemRequest.centralProductId = centralProductId
        newItemUnavailableItemRequest.productId = productId
        newItemUnavailableItemRequest.unitId = ""
        val unavailableOrderRequest = UnavailableOrderRequest()
        unavailableOrderRequest.orderId = storeOrderId
        unavailableOrderRequest.extraNote = ""
        unavailableOrderRequest.updateType = TWO
        unavailableOrderRequest.ipAddress = Utility.getIpAddress(context)
        unavailableOrderRequest.latitude = VariableConstants.CURRENT_ZONE_LAT.toString()
        unavailableOrderRequest.longitude = VariableConstants.CURRENT_ZONE_LONGI.toString()
        unavailableOrderRequest.newItems = newItemUnavailableItemRequest
        unavailableOrderRequest.items = itemUnavailableItemRequest

        val userHistory: Observable<Response<ResponseBody>> = addProductService.setUnavailabilityOrder(
                preferenceHelperDataSource.token,
                preferenceHelperDataSource.language,
                ANDROID_PLATFORM,
                CURRENCY_SYMBOL,
                CURRENCY_CODE,
                unavailableOrderRequest)
        userHistory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Response<ResponseBody>> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(value: Response<ResponseBody>) {
                        progressVisible.set(FALSE)
                        try {
                            val jsonObject: JSONObject
                            if (value.code() == SUCCESS) {
                                mAddedProduct.postValue(TRUE)
                            } else {
                                jsonObject = JSONObject(value.errorBody()!!.string())
                                Utility.printLog("userHistory : $jsonObject")
                            }
                        } catch (e: JSONException) {
                            Utility.printLog("userHistory : Catch :" + e.message)
                        } catch (e: IOException) {
                            Utility.printLog("userHistory : Catch :" + e.message)
                        }
                    }

                    override fun onError(e: Throwable) {

                        Utility.printLog("userHistory : onError :" + e.message)
                    }

                    override fun onComplete() {

                    }
                })
    }


    /**
     * notify activity variant data comes
     */
    fun onVariantData(): MutableLiveData<Pair<VariantProductData, ArrayList<VariantData>>> {
        return mVariantData
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

    /**
     * notify activity when product added success
     */
    fun onProductAdded(): MutableLiveData<Boolean> {
        return mAddedProduct
    }


}