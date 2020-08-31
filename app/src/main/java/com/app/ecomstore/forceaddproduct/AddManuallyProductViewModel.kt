package com.app.ecomstore.forceaddproduct

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.AddItemOrderRequest
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.AddProductRequest
import com.app.delivxstore.networking.AddProductService
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.delivxstore.utility.VariableConstants
import com.app.ecomstore.util.EcomConstants.*
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
import javax.inject.Inject

/**
 * view model class for the select drivers activity.
 */
class AddManuallyProductViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: AddProductService,
                                                      val context: Context, private var addProductService: NetworkService) : ViewModel() {
    val progressVisible = ObservableField(FALSE)
    private val mErrorData = MutableLiveData<String>()
    private val mAddedProduct = MutableLiveData<Boolean>()
    val errorItemName = ObservableField(FALSE)
    val errorQty = ObservableField(FALSE)
    val errorPriceUnit = ObservableField(FALSE)
    val errorTotalCharge = ObservableField(FALSE)

    private var preferences: PreferenceHelperDataSource

    init {
        preferences = preferenceHelperDataSource
    }


    /**
     * handle text changes of name
     */
    fun onNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        errorItemName.set(FALSE)
    }


    /**
     * handle text changes of qty
     */
    fun onQtyChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        errorQty.set(FALSE)
    }


    /**
     * handle text changes of price per product
     */
    fun onPricePerProductChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        errorPriceUnit.set(FALSE)
    }


    /**
     * handle text changes of name
     */
    fun onTotalChargeToCustomerChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        errorTotalCharge.set(FALSE)
    }


    /**
     * This method used to get the partners.
     */
    public fun addToCartProduct(name: String, image: String, storeOrderId: String, isNeedsWeighed: Boolean, quantity: Int, price: Int) {
        progressVisible.set(TRUE)
        val newItemUnavailableItemRequest = AddProductRequest()
        newItemUnavailableItemRequest.quantity = quantity
        newItemUnavailableItemRequest.centralProductId = "0"
        newItemUnavailableItemRequest.productId = "0"
        newItemUnavailableItemRequest.unitId = "0"
        newItemUnavailableItemRequest.image = image
        newItemUnavailableItemRequest.name = name
        newItemUnavailableItemRequest.isNeedsWeighed = isNeedsWeighed
        newItemUnavailableItemRequest.price = price
        val unavailableOrderRequest = AddItemOrderRequest()
        unavailableOrderRequest.orderId = storeOrderId
        unavailableOrderRequest.extraNote = ""
        unavailableOrderRequest.updateType = THREE
        unavailableOrderRequest.ipAddress = Utility.getIpAddress(context)
        unavailableOrderRequest.latitude = VariableConstants.CURRENT_ZONE_LAT.toString()
        unavailableOrderRequest.longitude = VariableConstants.CURRENT_ZONE_LONGI.toString()
        unavailableOrderRequest.newItems = newItemUnavailableItemRequest

        val userHistory: Observable<Response<ResponseBody>> = addProductService.addProduct(
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
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

    /**
     * notify activity when product added success
     */
    fun onProductAdded(): MutableLiveData<Boolean> {
        return mAddedProduct
    }


}