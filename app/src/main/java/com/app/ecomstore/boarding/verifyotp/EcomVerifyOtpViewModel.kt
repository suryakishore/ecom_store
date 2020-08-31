package com.app.ecomstore.boarding.verifyotp

import android.content.Context
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.boarding.login.model.LoginResponse
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * view model class for verify otp
 */
class EcomVerifyOtpViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService, val context: Context) : ViewModel() {
    private val mMutableLiveOnTextChangeData: MutableLiveData<VerifyOtpUiAction> = MutableLiveData<VerifyOtpUiAction>()
    private val mValidateOtpLiveData = MutableLiveData<Pair<Boolean, HashMap<String, Any>>>()
    private val mLiveData = MutableLiveData<Boolean>()
    val errorMailMsg = ObservableField(FALSE)
    val progressVisible = ObservableField(FALSE)
    val btnEnabled = ObservableField(FALSE)
    private val mErrorData = MutableLiveData<String>()
    var otpId = ""
    private var mOtpFir = ""
    private var mOtpSec = ""
    private var mOtpThi = ""
    private var mOtpFour = ""
    private var mUserEmail = ""
    private var mDeviceId: String? = null
    private var mIpAddress: String? = null
    private var mDeviceMake: String? = null
    private var mVersionRelease: String? = null
    private var mVersionName: String? = null
    private var mDeviceModel: String? = null

    /**
     *  get the device details to call the apis for this  activity.
     */
    fun getDeviceDetails(deviceId: String, ipAddress: String, deviceMake: String, release: String, versionName: String, deviceModel: String) {
        this.mDeviceId = deviceId
        this.mIpAddress = ipAddress
        this.mDeviceMake = deviceMake
        this.mVersionRelease = release
        this.mVersionName = versionName
        this.mDeviceModel = deviceModel
    }

    /**
     * <P>This method is used to listen the resend text clicked.</P>
     */
    fun onBackClicked() {
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.BACK)
    }

    /**
     * <P>This method is used to listen the resend text clicked.</P>
     */
    fun onTextResendClicked() {
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.RESEND)
    }

    /**
     * handle text changes of email
     */
    fun onUserEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        mUserEmail = s.toString()
        btnEnabled.set(EcomUtil.isEmail(mUserEmail))
    }

    /**
     * handle text changes of otp first
     */
    fun onTextChangeOtpFir(s: CharSequence, start: Int, before: Int, count: Int) {
        mOtpFir = s.toString()
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.OTP_FIRST)
        callVerifyOtp()
    }

    /**
     * handle text changes of otp second
     */
    fun onTextChangeOtpSec(s: CharSequence, start: Int, before: Int, count: Int) {
        mOtpSec = s.toString()
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.OTP_SECOND)
        callVerifyOtp()
    }

    /**
     * handle text changes of otp third
     */
    fun onTextChangeOtpThi(s: CharSequence, start: Int, before: Int, count: Int) {
        mOtpThi = s.toString()
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.OTP_THREE)
        callVerifyOtp()
    }

    /**
     * handle text changes of otp four
     */
    fun onTextChangeOtpFour(s: CharSequence, start: Int, before: Int, count: Int) {
        mOtpFour = s.toString()
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.OTP_FOUR)
        callVerifyOtp()
    }

    /**
     * calling the verify otp api.
     */
    fun callVerifyOtp() {
        btnEnabled.set(isOtpEntered())
        if (isOtpEntered()) {
            progressVisible.set(TRUE)
            val verifyOTPRequest = VerifyOTPRequest()
            verifyOTPRequest.otpId = otpId
            verifyOTPRequest.otpCode = "$mOtpFir$mOtpSec$mOtpThi$mOtpFour"
            verifyOTPRequest.verifyType = NUMBER_VERIFY_TYPE
            networkService.also {
                it.verifyOTP(preferenceHelperDataSource.language, ANDROID_PLATFORM,
                        CURRENCY_SYMBOL,
                        CURRENCY_CODE,
                        verifyOTPRequest).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe {
                            progressVisible.set(FALSE)
                            if (it != null) {
                                try {
                                    val jsonObject: JSONObject
                                    val code = it.code()
                                    Utility.printLog("VerifyResponse : Code $code")
                                    if (code == SUCCESS) {
                                        val response: String = it.body()!!.string()
                                        Utility.printLog("VerifyResponse  $response")
                                        preferenceHelperDataSource.loginResponse(response)
                                        val gson = Gson()
                                        val loginResponse = gson.fromJson(response, LoginResponse::class.java)
                                        setSignInData(loginResponse)
                                    } else if (code == INVALID_CODE) {
                                        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.INVALID_CODE)
                                    } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                        mErrorData.postValue(ApplicationManager.getInstance().getString(R.string.serverError))
                                    } else {
                                        jsonObject = JSONObject(it.errorBody()!!.string())
                                        mErrorData.postValue(jsonObject.getString(MESSAGE))
                                        Utility.printLog("VerifyResponse : $jsonObject")
                                    }
                                } catch (e: JSONException) {
                                    Utility.printLog("VerifyResponse  JSONException: Catch :" + e.message)
                                } catch (e: IOException) {
                                    Utility.printLog("VerifyResponse IOException : Catch :" + e.localizedMessage)
                                }
                            }
                        }
            }
        }
    }

    /**
     * this method is used listen when verify button clicked.
     */
    fun onDoneButtonClicked() {
        callEmailForgotPasswordApi()
    }

    /**
     * this method is used listen when verify button clicked.
     */
    fun onVerifyButtonClicked() {
        mLiveData.postValue(TRUE)
    }

    /**
     * notifies activity when Text changed
     */
    fun onTextChanged(): MutableLiveData<VerifyOtpUiAction> {
        return mMutableLiveOnTextChangeData
    }

    /**
     * set the data locally related to the user
     */
    private fun setSignInData(response: LoginResponse) {
        val data = response.data
        var mCityId: String? = ""
        var mStoreId: String? = ""
        if (data.fcmTopic != null) {
            FirebaseMessaging.getInstance().subscribeToTopic(data.fcmTopic)
        }
        if (data.authToken != null &&
                data.authToken.accessToken != null) {
            preferenceHelperDataSource.token = data.authToken.accessToken
        }
        /* if(data.mobile!=null){
             preferenceHelperDataSource.mobile = data.mobile;
         }*/
        if (data.name != null) {
            preferenceHelperDataSource.myName = data.name
        }
        if (data.storeName != null &&
                data.storeName.en != null) {
            preferenceHelperDataSource.storeName = data.storeName.en
        }
        if (data.cities != null) {
            if (data.cities[0].cityName != null) {
                preferenceHelperDataSource.cityName = data.cities[0].cityName
            }
            if (data.cities[0].cityId != null) {
                mCityId = data.cities[0].cityId
            }
        }
        if (data.role != null) {
            preferenceHelperDataSource.role = data.role
        }
        if (data.linkedWith != null) {
            preferenceHelperDataSource.linkedWith = data.linkedWith
            val linkedWith = data.linkedWith.toInt()
            if (linkedWith == 1)
                preferenceHelperDataSource.setIsCityLogin(TRUE)
        }

        preferenceHelperDataSource.managerChannel = if (preferenceHelperDataSource.isCityLogin) "$CITY_MANAGER/$mCityId/" else "$STORE_MANAGER/$mCityId/" +
                if (preferenceHelperDataSource.isCityLogin) "" else {
                    "${data.sellerId}/"
                } + "${data._id}"

        EcomUtil.printLog("exe" + "managerChannel" + preferenceHelperDataSource.managerChannel)

        if (data.storeData != null) {
            if (data.storeData.storeId != null) {
                mStoreId = data.storeData.storeId
            }
            if (data.storeData.storeTypeId != null) {
                preferenceHelperDataSource.storeType = data.storeData.storeTypeId.toInt()
            }
            if (data.storeData.businessLogoImages != null &&
                    data.storeData.businessLogoImages.businessLogoMobilePath != null) {
                preferenceHelperDataSource.profilePic = data.storeData.businessLogoImages.businessLogoMobilePath
            }
            if (data.storeData.driverTypeId != null) {
                preferenceHelperDataSource.driverType = data.storeData.driverTypeId.toInt()
            }
            if (data.storeData.currencyCode != null) {
                preferenceHelperDataSource.currency = data.storeData.currencyCode
            }
            if (data.storeData.storeId != null) {
                preferenceHelperDataSource.storeId = data.storeData.storeId
            }
            if (data.storeData.storeId != null) {
                preferenceHelperDataSource.storeLoginId = data.storeData.storeId
            }
            if (data.storeData.categoryId != null) {
                preferenceHelperDataSource.categoryId = data.storeData.categoryId
            }
        }
        preferenceHelperDataSource.managerID = mStoreId
        preferenceHelperDataSource.cityId = mCityId
        preferenceHelperDataSource.deviceId = Utility.getDeviceId(ApplicationManager.getInstance())
        preferenceHelperDataSource.setFcmTopic(data.fcmTopic)
        preferenceHelperDataSource.myEmail = data.email
        preferenceHelperDataSource.setIsLogin(TRUE)
        preferenceHelperDataSource.password = data.originalPassword
        preferenceHelperDataSource.synching = true
        (ApplicationManager.getInstance() as ApplicationManager).setAuthToken(preferenceHelperDataSource.myEmail,
                preferenceHelperDataSource.storeId, data.authToken.accessToken)
        (ApplicationManager.getInstance()).connectMQTT()
        mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.HOME)
    }

    /**
     * call send otp api
     */
    fun callSendOtpApi(countryCode: String?, phoneNumber: String?) {
        progressVisible.set(TRUE)
        val sendOtpRequest = SendOtpRequest()
        sendOtpRequest.countryCode = countryCode
        sendOtpRequest.mobile = phoneNumber
        sendOtpRequest.triggeredBy = CUSTOMER_VERIFICATION_CODE
        sendOtpRequest.verifyType = NUMBER_VERIFY_TYPE
        sendOtpRequest.userName = USER_NAME
        sendOtpRequest.type = RESEND_OTP_TYPE
        networkService.also {
            it.sendOTP(preferenceHelperDataSource.language, ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE,
                    sendOtpRequest).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it.body() != null) {
                            val response: String = it.body()!!.string()
                            val jsonObject: JSONObject
                            jsonObject = JSONObject(response)
                            val gson = Gson()
                            val loginResponse = gson.fromJson(jsonObject.toString(), LoginResponse::class.java)
                            val map = HashMap<String, Any>()
                            map.put(OTP_ID, loginResponse.data.otpId)
                            map.put(OTP_EXPIRY_TIME, loginResponse.data.otpExpiryTime)
                            mValidateOtpLiveData.setValue(Pair.create(TRUE, map))
                        } else if (it.errorBody() != null) {
                            val response: String = it.errorBody()!!.string()
                            val jsonObject: JSONObject
                            jsonObject = JSONObject(response)
                            mErrorData.postValue(jsonObject.getString(MESSAGE))
                        }
                    }
        }
    }

    /**
     * call forgot password api
     */
    private fun callEmailForgotPasswordApi() {
        progressVisible.set(TRUE)
        val forgotPaswwordRequest = ForgotPaswwordRequest()
        forgotPaswwordRequest.verifyType = EMAIL_VERIFY_TYPE
        forgotPaswwordRequest.email = mUserEmail
        forgotPaswwordRequest.deviceId = mDeviceId
        forgotPaswwordRequest.deviceMake = mDeviceMake
        forgotPaswwordRequest.browserVersion = BROWSER_VERSION
        forgotPaswwordRequest.browserName = CROME
        forgotPaswwordRequest.deviceModel = mDeviceModel
        networkService.also {
            it.forgotPassword(preferenceHelperDataSource.language, ANDROID_PLATFORM,
                    CURRENCY_SYMBOL,
                    CURRENCY_CODE,
                    forgotPaswwordRequest).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        progressVisible.set(FALSE)
                        if (it != null) {
                            when (it.code()) {
                                SUCCESS -> {
                                    mMutableLiveOnTextChangeData.postValue(VerifyOtpUiAction.FINISh)
                                }
                                else -> {
                                    if (it.errorBody() != null) {
                                        val response: String = it.errorBody()!!.string()
                                        val jsonObject: JSONObject
                                        jsonObject = JSONObject(response)
                                        mErrorData.postValue(jsonObject.getString(MESSAGE))
                                    }
                                }
                            }
                        }
                    }
        }
    }

    /**
     * returns boolean true or false when otp entered
     * @return true if entered ,false if not entered
     */
    private fun isOtpEntered(): Boolean {
        return mOtpFour.length > ZERO && mOtpFir.length > ZERO && mOtpSec.length > ZERO && mOtpThi.length > ZERO
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
    fun onButtonClick(): MutableLiveData<Boolean> {
        return mLiveData
    }

    /**
     * notify activity when resend  clicked
     */
    fun onResendClicked(): MutableLiveData<Pair<Boolean, HashMap<String, Any>>> {
        return mValidateOtpLiveData
    }
}