package com.app.ecomstore.pack

import android.content.Context
import android.os.Build
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.main.mobileView.orderDetails.models.Attributes
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberRequest
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.ProductOrdersRequest
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LAT
import com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LONGI
import com.app.ecomstore.util.EcomConstants.*
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.function.Predicate
import javax.inject.Inject

/**
 * view model class for the select partner activity.
 */
class PackingViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                           val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mBoxesData = MutableLiveData<ArrayList<BoxData>>()
    private val mErrorData = MutableLiveData<String>()
    private val mAttributeData = MutableLiveData<String>()
    private val mSuccessData = MutableLiveData<Boolean>()

    /**
     * this method is used to set the attributes data
     */
    public fun setAttributeData(attributeData: ArrayList<Attributes>) {
        if (attributeData != null
                && attributeData.size > ZERO) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val condition: Predicate<Attributes> = Predicate<Attributes> { orderHistAttributeData: Attributes ->
                    (orderHistAttributeData.getValue() != null
                            && orderHistAttributeData.getValue().equals(""))
                }
                attributeData.removeIf(condition)
            }
            val attributeName = StringBuilder()
            for (i in ZERO until attributeData.size) {
                if (attributeData.get(i).getValue() != null
                        && !attributeData.get(
                                i).getValue().isEmpty()) {
                    attributeName.append(attributeData.get(i).getAttrname()).append(
                            ":").append(
                            attributeData.get(i).getValue()).append(" ")
                }
                if (i == ONE) {
                    break
                }
            }
            mAttributeData.postValue(attributeName.toString())
        }
    }

    /**
     * This method used to get the partners.
     */
    public fun getPackageBox() {
        progressVisible.set(TRUE)
        networkService.also {
            it.getPackageBox(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail),
                    preferenceHelperDataSource.getLanguage(), ANDROID_PLATFORM,
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
                                    val parnerResponse = gson.fromJson(jsonObject.toString(), BoxResponse::class.java)
                                    if (parnerResponse != null) {
                                        Utility.printLog("LoginResponse : Code ${parnerResponse.getData()?.size}")
                                        mBoxesData.postValue(parnerResponse.getData())
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
     * This method used to get the partners.
     */
    public fun addProduct(boxId: String?, products: Products) {
        progressVisible.set(TRUE)
        val productOrdersRequest = ProductOrdersRequest()
        productOrdersRequest.orderId = products.productOrderId
        productOrdersRequest.quantity = products.quantity.value.toInt()
        val productOrdersRequests = java.util.ArrayList<ProductOrdersRequest>()
        productOrdersRequests.add(productOrdersRequest)
        val orderNumberRequest = OrderNumberRequest()
        orderNumberRequest.productOrders = productOrdersRequests
        orderNumberRequest.newBoxId = boxId
        orderNumberRequest.existingPackageId = products.packageId
        orderNumberRequest.ipAddress = Utility.getIpAddress(context)
        orderNumberRequest.latitude = CURRENT_ZONE_LAT.toString()
        orderNumberRequest.longitude = CURRENT_ZONE_LONGI.toString()

        networkService.also {
            it.setPackOrder(
                    (context.applicationContext as ApplicationManager).getAuthToken(preferenceHelperDataSource.myEmail),
                    preferenceHelperDataSource.getLanguage(), ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE, orderNumberRequest).observeOn(AndroidSchedulers.mainThread())
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
                                    Utility.printLog("LoginResponse : Code $response")
                                    mSuccessData.postValue(TRUE)
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
    fun onBoxesData(): MutableLiveData<ArrayList<BoxData>> {
        return mBoxesData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

    /**
     * notify activity error data comes
     */
    fun getAttributeData(): MutableLiveData<String> {
        return mAttributeData
    }

    /**
     * notify activity error success comes
     */
    fun onSuccessData(): MutableLiveData<Boolean> {
        return mSuccessData
    }

}