package com.app.ecomstore.boarding.login

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.delivxstore.ApplicationManager
import com.app.delivxstore.BuildConfig
import com.app.delivxstore.R
import com.app.delivxstore.data.source.PreferenceHelperDataSource
import com.app.delivxstore.networking.NetworkService
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.boarding.login.model.LoginData
import com.app.ecomstore.boarding.login.model.LoginRequestData
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
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

/**
 * view model class for the login activity
 */
class EcomLoginViewModel @Inject constructor(private var preferenceHelperDataSource: PreferenceHelperDataSource, private var networkService: NetworkService,
                                             val context: Context) : ViewModel() {
    private val mLiveData = MutableLiveData<LoginUiAction>()
    private val mErrorData = MutableLiveData<String>()
    private val mOtpLiveData = MutableLiveData<Pair<Boolean, HashMap<String, Any>>>()
    val progressVisible = ObservableField(FALSE)
    val errorMailMsg = ObservableField(FALSE)
    val errorPasswordMsg = ObservableField(FALSE)
    val btnEnabled = ObservableField(FALSE)
    val passwordShowIcon = ObservableField(FALSE)
    private var mDeviceId: String? = null
    private var mIpAddress: String? = null
    private var mDeviceMake: String? = null
    private var mVersionRelease: String? = null
    private var mVersionName: String? = null
    private var mDeviceModel: String? = null
    private var mUserEmail = ""
    private var mUserPassword = ""
    private var mUserPhone = ""

    fun getDeviceDetails(deviceId: String, ipAddress: String, deviceMake: String, release: String, versionName: String, deviceModel: String) {
        this.mDeviceId = deviceId
        this.mIpAddress = ipAddress
        this.mDeviceMake = deviceMake
        this.mVersionRelease = release
        this.mVersionName = versionName
        this.mDeviceModel = deviceModel

    }

    /**
     * this method used to set the user location details..
     */
    public fun setUserLocationDetails() {
        preferenceHelperDataSource.getAddress(ApplicationManager.getInstance())
    }

    /**
     * <h2>onClick</h2>
     * notify activity when a view is  clicked
     */
    fun onClick(): MutableLiveData<LoginUiAction> {
        return mLiveData
    }

    /**
     * notify activity error data comes
     */
    fun onErrorData(): MutableLiveData<String> {
        return mErrorData
    }

    /**
     *
     * this method is used listen when login button clicked.
     */
    fun onForgotPasswordClicked() {
        mLiveData.postValue(LoginUiAction.FORGOT_PASSWORD)
    }

    /**
     * <h2>onUserEmailChanged</h2>
     * handle text changes of email
     */
    fun onUserEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        mUserEmail = s.toString()
        errorMailMsg.set(FALSE)
        btnEnabled.set(
                EcomUtil.isEmail(mUserEmail) && mUserPassword.length >= PASSWORD_VALID_LENGTH)
    }

    /**
     * <h2>onTextChangePassword</h2>
     * handle text changes of password
     */
    fun onTextChangePassword(s: CharSequence, start: Int, before: Int, count: Int) {
        mUserPassword = s.toString()
        errorPasswordMsg.set(FALSE)
        btnEnabled.set(mUserPassword.length >= PASSWORD_VALID_LENGTH && EcomUtil.isEmail(
                Objects.requireNonNull(mUserEmail)))
    }

    /**
     * <h2>onEmailFocus</h2>
     * handle focus loose of email
     */
    fun onEmailFocus() {
        if (!EcomUtil.isEmail(
                        Objects.requireNonNull(mUserEmail))) {
            errorMailMsg.set(TRUE)
        }
    }

    /**
     * set enable the button
     */
    fun setEnable(email: Boolean) {
        if (email) {
            btnEnabled.set(
                    EcomUtil.isEmail(mUserEmail) && mUserPassword.length >= PASSWORD_VALID_LENGTH)
        } else {
            btnEnabled.set(EcomUtil.isPhone(
                    Objects.requireNonNull(mUserPhone)))
        }
    }

    /**
     * <h2>onPasswordFocus</h2>
     * handle focus loose of password
     */
    fun onPasswordFocus() {
        passwordShowIcon.set(mUserPassword != null && mUserPassword.length > ZERO)
        if (!EcomUtil.isPassword(
                        Objects.requireNonNull(mUserPassword))) {
            errorPasswordMsg.set(TRUE)
        }
    }

    /**
     * handle has focus of password
     */
    fun onPasswordHasFocus() {
        passwordShowIcon.set(TRUE)
    }

    /**
     * handle text changes of phone number
     */
    fun onTextChangePhoneNumber(s: CharSequence, start: Int, before: Int, count: Int) {
        mUserPhone = s.toString()
        btnEnabled.set(EcomUtil.isPhone(
                Objects.requireNonNull(mUserPhone)))
    }

    /**
     * this method is used listen when login button clicked.
     */
    fun onLoginButtonClicked() {
        mLiveData.postValue(LoginUiAction.LOGIN)
    }

    /**
     * call email or phone number api
     */
    fun callEmailOrPhoneNumSignInApi(type: Int, email: String?, password: String?, countryCode: String?, phoneNumber: String?) {
        progressVisible.set(TRUE)
        val loginRequestData = LoginRequestData()
        loginRequestData.id = ""
        loginRequestData.email = email
        loginRequestData.password = password
        loginRequestData.countryCode = countryCode
        loginRequestData.mobile = phoneNumber
        loginRequestData.ipAddress = Utility.getIpAddress(ApplicationManager.getInstance())
        loginRequestData.type = 1.0
        loginRequestData.verifyType = type
        Log.d("exe", "lat" + preferenceHelperDataSource.currentLat)
        Log.d("exe", "lan" + preferenceHelperDataSource.currentLong)
        loginRequestData.latitude = preferenceHelperDataSource.currentLat
        loginRequestData.longitude = preferenceHelperDataSource.currentLong
        loginRequestData.city = preferenceHelperDataSource.city
        loginRequestData.country = preferenceHelperDataSource.country
        loginRequestData.deviceMake = Build.MODEL
        loginRequestData.deviceOsVersion = Build.VERSION.RELEASE
        loginRequestData.deviceId = Utility.getDeviceId(ApplicationManager.getInstance())
        loginRequestData.version = BuildConfig.VERSION_NAME
        loginRequestData.deviceModel = Build.MODEL
        networkService.also {
            it.logIn(preferenceHelperDataSource.language, 2.0,
                    "$",
                    "INR",
                    loginRequestData).observeOn(AndroidSchedulers.mainThread())
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
                                    Log.d("exe", "response" + response)
                                    preferenceHelperDataSource.loginResponse(response)
                                    jsonObject = JSONObject(response)
                                    val gson = Gson()
                                    val loginResponse = gson.fromJson(jsonObject.toString(), LoginResponse::class.java)
                                    if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
                                        setSignInData(loginResponse.data, email, password)
                                    } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
                                        val map = HashMap<String, Any>()
                                        map.put(OTP_ID, loginResponse.data.otpId)
                                        map.put(OTP_EXPIRY_TIME, loginResponse.data.otpExpiryTime)
                                        mOtpLiveData.setValue(Pair.create(TRUE, map))
                                    }
                                    Utility.printLog("LoginResponse : $jsonObject")
                                } else if (code == SERVER_ERROR || code == INTERNEL_SERVER_ERROR) {
                                    mErrorData.postValue(ApplicationManager.getInstance().getString(R.string.serverError))
                                } else {
                                    jsonObject = JSONObject(it.errorBody()!!.string())
                                    mErrorData.postValue(jsonObject.getString(MESSAGE))
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
     * set the data locally related to the user
     */
    private fun setSignInData(data: LoginData, userName: String, password: String) {
        var mCityId: String? = ""
        var mStoreId: String? = ""
        if (data.fcmTopic != null) {
            FirebaseMessaging.getInstance().subscribeToTopic(data.fcmTopic)
        }
        if (data.authToken != null &&
                data.authToken.accessToken != null) {
            preferenceHelperDataSource.token = data.authToken.accessToken
        }

        preferenceHelperDataSource.storeId = data._id;

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
        if (preferenceHelperDataSource.isCityLogin)
            preferenceHelperDataSource.managerChannel = CITY_MANAGER + "/" + mCityId + "/" + data._id
        else {
            preferenceHelperDataSource.managerChannel = STORE_MANAGER + "/" + mCityId + "/" + data.sellerId + "/" + data._id
        }
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
        preferenceHelperDataSource.myEmail = userName
        preferenceHelperDataSource.setIsLogin(TRUE)
        preferenceHelperDataSource.password = password
        preferenceHelperDataSource.synching = true
        (context.getApplicationContext() as ApplicationManager).setAuthToken(preferenceHelperDataSource.myEmail,
                preferenceHelperDataSource.storeId, data.authToken.accessToken)
        (ApplicationManager.getInstance()).connectMQTT()
        mLiveData.postValue(LoginUiAction.HOME)
    }

    /**
     * notify activity when resend  clicked
     */
    fun onOtpData(): MutableLiveData<Pair<Boolean, HashMap<String, Any>>> {
        return mOtpLiveData
    }
}