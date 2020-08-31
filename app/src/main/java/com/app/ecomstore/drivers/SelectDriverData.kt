package com.app.ecomstore.drivers

import android.os.Parcel
import android.os.Parcelable
import com.app.ecomstore.boarding.login.model.CategoryName
import com.app.ecomstore.boarding.login.model.Location
import com.app.ecomstore.boarding.login.model.MobileDevices
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SelectDriverData() :Parcelable{


    @SerializedName("lastName")
    @Expose
    private var lastName: String? = null

    @SerializedName("zipCode")
    @Expose
    private var zipCode: String? = null

    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("driverLicenseBack")
    @Expose
    private var driverLicenseBack: String? = null

    @SerializedName("createdISOdate")
    @Expose
    private var createdISOdate: String? = null

    @SerializedName("appVersion")
    @Expose
    private var appVersion: String? = null

    @SerializedName("driverLicenseFront")
    @Expose
    private var driverLicenseFront: String? = null

    @SerializedName("planName")
    @Expose
    private var planName: String? = null

    @SerializedName("storeTypeId")
    @Expose
    private var storeTypeId: String? = null

    @SerializedName("storeFrontType")
    @Expose
    private var storeFrontType: String? = null

    @SerializedName("cityId")
    @Expose
    private var cityId: String? = null

    @SerializedName("mqttTopic")
    @Expose
    private var mqttTopic: String? = null

    @SerializedName("categoryName")
    @Expose
    private var categoryName: CategoryName? = null

    @SerializedName("password")
    @Expose
    private var password: String? = null

    @SerializedName("cityName")
    @Expose
    private var cityName: String? = null

    @SerializedName("countryCode")
    @Expose
    private var countryCode: String? = null

    @SerializedName("referralCode")
    @Expose
    private var referralCode: String? = null

    @SerializedName("storeName")
    @Expose
    private var storeName: String? = null

    @SerializedName("currency")
    @Expose
    private var currency: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("storeType")
    @Expose
    private var storeType: String? = null

    @SerializedName("storeFrontTypeId")
    @Expose
    private var storeFrontTypeId: String? = null

    @SerializedName("timedOut")
    @Expose
    private var timedOut: String? = null

    @SerializedName("profilePic")
    @Expose
    private var profilePic: String? = null

    @SerializedName("createdTimestamp")
    @Expose
    private var createdTimestamp: String? = null

    @SerializedName("mobile")
    @Expose
    private var mobile: String? = null

    @SerializedName("abbrevation")
    @Expose
    private var abbrevation: String? = null

    @SerializedName("currencySymbol")
    @Expose
    private var currencySymbol: String? = null

    @SerializedName("fcmTopic")
    @Expose
    private var fcmTopic: String? = null

    @SerializedName("storeId")
    @Expose
    private var storeId: String? = null

    @SerializedName("driverLicenseExpiry")
    @Expose
    private var driverLicenseExpiry: String? = null

    @SerializedName("driverType")
    @Expose
    private var driverType: String? = null

    @SerializedName("supportedDeliveryType")
    @Expose
    private var supportedDeliveryType: String? = null

    @SerializedName("previousState")
    @Expose
    private var previousState: String? = null

    @SerializedName("driverLicenseExpiryISO")
    @Expose
    private var driverLicenseExpiryISO: String? = null

    @SerializedName("firstName")
    @Expose
    private var firstName: String? = null

    @SerializedName("driverLicenseNumber")
    @Expose
    private var driverLicenseNumber: String? = null

    @SerializedName("statusMsg")
    @Expose
    private var statusMsg: String? = null

    @SerializedName("dob")
    @Expose
    private var dob: String? = null

    @SerializedName("mobileDevices")
    @Expose
    private var mobileDevices: DriverMobileDevices? = null

    @SerializedName("isLoggedIn")
    @Expose
    private var isLoggedIn: String? = null

    @SerializedName("location")
    @Expose
    private var location: Location? = null

    @SerializedName("planID")
    @Expose
    private var planID: String? = null

    @SerializedName("_id")
    @Expose
    private var _id: String? = null

    @SerializedName("categoryId")
    @Expose
    private var categoryId: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("serviceZones")
    @Expose
    private var serviceZones: ArrayList<String>?=null

    constructor(parcel: Parcel) : this() {
        lastName = parcel.readString()
        zipCode = parcel.readString()
        country = parcel.readString()
        driverLicenseBack = parcel.readString()
        createdISOdate = parcel.readString()
        appVersion = parcel.readString()
        driverLicenseFront = parcel.readString()
        planName = parcel.readString()
        storeTypeId = parcel.readString()
        storeFrontType = parcel.readString()
        cityId = parcel.readString()
        mqttTopic = parcel.readString()
        categoryName = parcel.readParcelable(CategoryName::class.java.classLoader)
        password = parcel.readString()
        cityName = parcel.readString()
        countryCode = parcel.readString()
        referralCode = parcel.readString()
        storeName = parcel.readString()
        currency = parcel.readString()
        email = parcel.readString()
        storeType = parcel.readString()
        storeFrontTypeId = parcel.readString()
        timedOut = parcel.readString()
        profilePic = parcel.readString()
        createdTimestamp = parcel.readString()
        mobile = parcel.readString()
        abbrevation = parcel.readString()
        currencySymbol = parcel.readString()
        fcmTopic = parcel.readString()
        storeId = parcel.readString()
        driverLicenseExpiry = parcel.readString()
        driverType = parcel.readString()
        supportedDeliveryType = parcel.readString()
        previousState = parcel.readString()
        driverLicenseExpiryISO = parcel.readString()
        firstName = parcel.readString()
        driverLicenseNumber = parcel.readString()
        statusMsg = parcel.readString()
        dob = parcel.readString()
        mobileDevices = parcel.readParcelable(DriverMobileDevices::class.java.classLoader)
        isLoggedIn = parcel.readString()
        planID = parcel.readString()
        _id = parcel.readString()
        categoryId = parcel.readString()
        status = parcel.readString()
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun getZipCode(): String? {
        return zipCode
    }

    fun setZipCode(zipCode: String?) {
        this.zipCode = zipCode
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getDriverLicenseBack(): String? {
        return driverLicenseBack
    }

    fun setDriverLicenseBack(driverLicenseBack: String?) {
        this.driverLicenseBack = driverLicenseBack
    }

    fun getCreatedISOdate(): String? {
        return createdISOdate
    }

    fun setCreatedISOdate(createdISOdate: String?) {
        this.createdISOdate = createdISOdate
    }

    fun getAppVersion(): String? {
        return appVersion
    }

    fun setAppVersion(appVersion: String?) {
        this.appVersion = appVersion
    }

    fun getDriverLicenseFront(): String? {
        return driverLicenseFront
    }

    fun setDriverLicenseFront(driverLicenseFront: String?) {
        this.driverLicenseFront = driverLicenseFront
    }

    fun getPlanName(): String? {
        return planName
    }

    fun setPlanName(planName: String?) {
        this.planName = planName
    }

    fun getStoreTypeId(): String? {
        return storeTypeId
    }

    fun setStoreTypeId(storeTypeId: String?) {
        this.storeTypeId = storeTypeId
    }

    fun getStoreFrontType(): String? {
        return storeFrontType
    }

    fun setStoreFrontType(storeFrontType: String?) {
        this.storeFrontType = storeFrontType
    }

    fun getCityId(): String? {
        return cityId
    }

    fun setCityId(cityId: String?) {
        this.cityId = cityId
    }

    fun getMqttTopic(): String? {
        return mqttTopic
    }

    fun setMqttTopic(mqttTopic: String?) {
        this.mqttTopic = mqttTopic
    }

    fun getCategoryName(): CategoryName? {
        return categoryName
    }

    fun setCategoryName(categoryName: CategoryName?) {
        this.categoryName = categoryName
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getCityName(): String? {
        return cityName
    }

    fun setCityName(cityName: String?) {
        this.cityName = cityName
    }

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String?) {
        this.countryCode = countryCode
    }

    fun getReferralCode(): String? {
        return referralCode
    }

    fun setReferralCode(referralCode: String?) {
        this.referralCode = referralCode
    }

    fun getStoreName(): String? {
        return storeName
    }

    fun setStoreName(storeName: String?) {
        this.storeName = storeName
    }

    fun getCurrency(): String? {
        return currency
    }

    fun setCurrency(currency: String?) {
        this.currency = currency
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getStoreType(): String? {
        return storeType
    }

    fun setStoreType(storeType: String?) {
        this.storeType = storeType
    }

    fun getStoreFrontTypeId(): String? {
        return storeFrontTypeId
    }

    fun setStoreFrontTypeId(storeFrontTypeId: String?) {
        this.storeFrontTypeId = storeFrontTypeId
    }

    fun getTimedOut(): String? {
        return timedOut
    }

    fun setTimedOut(timedOut: String?) {
        this.timedOut = timedOut
    }

    fun getProfilePic(): String? {
        return profilePic
    }

    fun setProfilePic(profilePic: String?) {
        this.profilePic = profilePic
    }

    fun getCreatedTimestamp(): String? {
        return createdTimestamp
    }

    fun setCreatedTimestamp(createdTimestamp: String?) {
        this.createdTimestamp = createdTimestamp
    }

    fun getMobile(): String? {
        return mobile
    }

    fun setMobile(mobile: String?) {
        this.mobile = mobile
    }

    fun getAbbrevation(): String? {
        return abbrevation
    }

    fun setAbbrevation(abbrevation: String?) {
        this.abbrevation = abbrevation
    }

    fun getCurrencySymbol(): String? {
        return currencySymbol
    }

    fun setCurrencySymbol(currencySymbol: String?) {
        this.currencySymbol = currencySymbol
    }

    fun getFcmTopic(): String? {
        return fcmTopic
    }

    fun setFcmTopic(fcmTopic: String?) {
        this.fcmTopic = fcmTopic
    }

    fun getStoreId(): String? {
        return storeId
    }

    fun setStoreId(storeId: String?) {
        this.storeId = storeId
    }

    fun getDriverLicenseExpiry(): String? {
        return driverLicenseExpiry
    }

    fun setDriverLicenseExpiry(driverLicenseExpiry: String?) {
        this.driverLicenseExpiry = driverLicenseExpiry
    }

    fun getDriverType(): String? {
        return driverType
    }

    fun setDriverType(driverType: String?) {
        this.driverType = driverType
    }

    fun getSupportedDeliveryType(): String? {
        return supportedDeliveryType
    }

    fun setSupportedDeliveryType(supportedDeliveryType: String?) {
        this.supportedDeliveryType = supportedDeliveryType
    }

    fun getPreviousState(): String? {
        return previousState
    }

    fun setPreviousState(previousState: String?) {
        this.previousState = previousState
    }

    fun getDriverLicenseExpiryISO(): String? {
        return driverLicenseExpiryISO
    }

    fun setDriverLicenseExpiryISO(driverLicenseExpiryISO: String?) {
        this.driverLicenseExpiryISO = driverLicenseExpiryISO
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun getDriverLicenseNumber(): String? {
        return driverLicenseNumber
    }

    fun setDriverLicenseNumber(driverLicenseNumber: String?) {
        this.driverLicenseNumber = driverLicenseNumber
    }

    fun getStatusMsg(): String? {
        return statusMsg
    }

    fun setStatusMsg(statusMsg: String?) {
        this.statusMsg = statusMsg
    }

    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String?) {
        this.dob = dob
    }

    fun getMobileDevices(): DriverMobileDevices? {
        return mobileDevices
    }

    fun setMobileDevices(mobileDevices: DriverMobileDevices?) {
        this.mobileDevices = mobileDevices
    }

    fun getIsLoggedIn(): String? {
        return isLoggedIn
    }

    fun setIsLoggedIn(isLoggedIn: String?) {
        this.isLoggedIn = isLoggedIn
    }

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location?) {
        this.location = location
    }

    fun getPlanID(): String? {
        return planID
    }

    fun setPlanID(planID: String?) {
        this.planID = planID
    }

    fun get_id(): String? {
        return _id
    }

    fun set_id(_id: String?) {
        this._id = _id
    }

    fun getCategoryId(): String? {
        return categoryId
    }

    fun setCategoryId(categoryId: String?) {
        this.categoryId = categoryId
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getServiceZones(): ArrayList<String>? {
        return serviceZones
    }

    fun setServiceZones(serviceZones: ArrayList<String>) {
        this.serviceZones = serviceZones
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastName)
        parcel.writeString(zipCode)
        parcel.writeString(country)
        parcel.writeString(driverLicenseBack)
        parcel.writeString(createdISOdate)
        parcel.writeString(appVersion)
        parcel.writeString(driverLicenseFront)
        parcel.writeString(planName)
        parcel.writeString(storeTypeId)
        parcel.writeString(storeFrontType)
        parcel.writeString(cityId)
        parcel.writeString(mqttTopic)
        parcel.writeParcelable(categoryName, flags)
        parcel.writeString(password)
        parcel.writeString(cityName)
        parcel.writeString(countryCode)
        parcel.writeString(referralCode)
        parcel.writeString(storeName)
        parcel.writeString(currency)
        parcel.writeString(email)
        parcel.writeString(storeType)
        parcel.writeString(storeFrontTypeId)
        parcel.writeString(timedOut)
        parcel.writeString(profilePic)
        parcel.writeString(createdTimestamp)
        parcel.writeString(mobile)
        parcel.writeString(abbrevation)
        parcel.writeString(currencySymbol)
        parcel.writeString(fcmTopic)
        parcel.writeString(storeId)
        parcel.writeString(driverLicenseExpiry)
        parcel.writeString(driverType)
        parcel.writeString(supportedDeliveryType)
        parcel.writeString(previousState)
        parcel.writeString(driverLicenseExpiryISO)
        parcel.writeString(firstName)
        parcel.writeString(driverLicenseNumber)
        parcel.writeString(statusMsg)
        parcel.writeString(dob)
        parcel.writeParcelable(mobileDevices, flags)
        parcel.writeString(isLoggedIn)
        parcel.writeString(planID)
        parcel.writeString(_id)
        parcel.writeString(categoryId)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectDriverData> {
        override fun createFromParcel(parcel: Parcel): SelectDriverData {
            return SelectDriverData(parcel)
        }

        override fun newArray(size: Int): Array<SelectDriverData?> {
            return arrayOfNulls(size)
        }
    }


}