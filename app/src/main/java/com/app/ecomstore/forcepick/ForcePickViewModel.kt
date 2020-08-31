package com.app.ecomstore.forcepick

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.BuildConfig.BUCKET_NAME
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.networking.UploadReceiptInput
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import javax.inject.Inject

/**
 * view model class for the select partner activity.
 */
class ForcePickViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                             val context: Context) : ViewModel() {

    val progressVisible = ObservableField(FALSE)
    private val mImageData = MutableLiveData<String>()
    private val mUploadImages = MutableLiveData<String>()
    private val mErrorData = MutableLiveData<String>()
    private val mUploadSuccess = MutableLiveData<Boolean>()

    /**
     * This method used to get the partners.
     */
    public fun uploadImage(file: File, isArrayUpload: Boolean) {
        EcomUtil.printLog("exe" + "isArrayUpload  " + isArrayUpload)
        progressVisible.set(TRUE)
        val requestFile: RequestBody = RequestBody.create(
                MediaType.parse("image/jpg"),
                file)
        val body = MultipartBody.Part.createFormData(FILE, file.getName(), requestFile)
        networkService.also {
            it.uploadImageApi(AMAZON_SERVER, BUCKET_NAME,
                    body, "" + System.currentTimeMillis()
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
                                    val response: String = it.body()!!.string()
                                    Utility.printLog("LoginResponse : Code $response")
                                    jsonObject = JSONObject(response)
                                    if (jsonObject.has(DATA)) {
                                        val jsonObjectData = jsonObject.getJSONObject(DATA)
                                        if (isArrayUpload) {
                                            mUploadImages.postValue(jsonObjectData.getString(IMAGE_URL))
                                        } else
                                            mImageData.postValue(jsonObjectData.getString(IMAGE_URL))
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
    public fun uploadReceiptFiles(imageList: ArrayList<String>, orderID: String, currencySymbol: String, currencyCode: String) {
        progressVisible.set(TRUE)
        val uploadReceiptInput = UploadReceiptInput()
        uploadReceiptInput.imageUrl = imageList
        uploadReceiptInput.orderId = orderID
        networkService.also {
            it.uploadReceipt(preferenceHelperDataSource.token,
                    preferenceHelperDataSource.language,
                    ANDROID_PLATFORM,
                    EcomUtil.getCurrencySymbol(currencySymbol),
                    currencyCode,
                    uploadReceiptInput).observeOn(AndroidSchedulers.mainThread())
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
                                    Utility.printLog("ForcePickResponse : Code $response")
                                    mUploadSuccess.postValue(TRUE)
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
     * get the receipt of the previous  added images
     */
    public fun getReceipt(currencySymbol: String, currencyCode: String, orderID: String) {
        progressVisible.set(TRUE)
        networkService.also {
            it.orderReceipt(preferenceHelperDataSource.token,
                    preferenceHelperDataSource.language,
                    ANDROID_PLATFORM,
                    EcomUtil.getCurrencySymbol(currencySymbol),
                    currencyCode,
                    orderID).observeOn(AndroidSchedulers.mainThread())
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
                                    Utility.printLog("ForcePickResponse : Code $response")
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
    fun onImageData(): MutableLiveData<String> {
        return mImageData
    }

    /**
     * notify activity partner data comes
     */
    fun onUploadImages(): MutableLiveData<String> {
        return mUploadImages
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

    /**
     * notify activity when upload success
     */
    fun onUploadSuccess(): MutableLiveData<Boolean> {
        return mUploadSuccess
    }

}