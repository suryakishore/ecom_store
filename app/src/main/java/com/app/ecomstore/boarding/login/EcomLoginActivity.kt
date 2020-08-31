package com.app.ecomstore.boarding.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.BuildConfig
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityEcomLoginBinding
import com.app.delivxstore.main.MainActivity
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.boarding.verifyotp.VerifyOtpActivity
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.manager.KeyboardDetector
import com.app.ecomstore.util.manager.KeyboardStatus
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * login activity to login via email or phone number.
 */
class EcomLoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mBinding: ActivityEcomLoginBinding
    private var mCompositeDisposable = CompositeDisposable()
    lateinit var mEcomLoginViewModel: EcomLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeOnClicks()
        subscribeOnErrorData()
        subscribeToOtpData()
        checkKeyboardStatus()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ecom_login)
        setTabLayoutClickListener()
        setEmailViews()
    }

    /**
     * <h2>initializeViewModel</h2>
     *
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        mEcomLoginViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EcomLoginViewModel::class.java)
        mBinding.viewModel = mEcomLoginViewModel
        mEcomLoginViewModel.getDeviceDetails(
                Utility.getDeviceId(this),
                Utility.getIpAddress(this),
                Build.MODEL,
                Build.VERSION.RELEASE,
                BuildConfig.VERSION_NAME,
                Build.MODEL + "_3Embed"
        )
    }

    /**
     * tab layout listener to manage the email and phone number views.
     */
    private fun setTabLayoutClickListener() {
        mBinding.tbLoginOption.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    setEmailViews()
                } else {
                    setPhoneViews()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    /**
     *
     * This method is executed when a user clicks on the email tab.
     */
    private fun setEmailViews() {
        if (mBinding.pbLoginShowProgress.visibility != View.VISIBLE) {
            mBinding.btLoginContinue.text = resources.getString(R.string.login)
            mBinding.vgEmail.visibility = View.VISIBLE
            mBinding.vgPhone.visibility = View.GONE
            mBinding.etLoginEmail.requestFocus()
            mEcomLoginViewModel.setEnable(TRUE)
        }
    }

    /**
     * This method is executed when a user clicks on the phone tab.
     */
    fun setPhoneViews() {
        if (mBinding.pbLoginShowProgress.visibility != View.VISIBLE) {
            mBinding.btLoginContinue.text = resources.getString(R.string.sendOtp)
            mBinding.vgPhone.visibility = View.VISIBLE
            mBinding.vgEmail.visibility = View.GONE
            mBinding.etLoginPhoneNumber.requestFocus()
            mEcomLoginViewModel.setEnable(FALSE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose()
        }
    }

    /**
     * to check whether keyboard is open or close
     */
    private fun checkKeyboardStatus() {
        KeyboardDetector(this).observe().subscribe(object : io.reactivex.Observer<KeyboardStatus> {
            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable.add(d)
            }

            override fun onNext(status: KeyboardStatus) {
                if (status === KeyboardStatus.OPENED) {
                    if (mBinding.vgEmail.visibility == View.VISIBLE) {
                        mBinding.nsvLoginScroll.smoothScrollTo(
                                mBinding.clLoginInputs.x.toInt(),
                                mBinding.clLoginInputs.y.toInt())
                    }
                }
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }

    /**
     * This method is used to listen all the clicks of the views.
     */
    private fun subscribeOnClicks() {
        mEcomLoginViewModel.onClick().observe(this, Observer {
            when (it) {
                LoginUiAction.LOGIN -> {
                   doLogin()
                }
                LoginUiAction.HOME -> {
                    navigateToHome()
                }
                LoginUiAction.FORGOT_PASSWORD -> {
                    navigateToForGotPassword()
                }
            }
        })
    }

    /**
     *
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        mEcomLoginViewModel.onErrorData().observe(this, Observer {
            Utility.printLog("LoginResponse Error : $it")
            Utility.mShowMessage(resources.getString(R.string.message), it, this)
        })
    }

    /**
     * This method is used to set the otp data
     */
    private fun subscribeToOtpData() {
        mEcomLoginViewModel.onOtpData().observe(this, Observer {
            val hashMap: HashMap<String, Any>? = it.second
            if (hashMap != null) {
                val otpId = hashMap.get(OTP_ID) as String
                val otpExpiryTime = hashMap.get(OTP_EXPIRY_TIME) as Long
                val intent = Intent(this, VerifyOtpActivity::class.java)
                intent.putExtra(COUNTRY_CODE, DEFAULT_COUNTRY_CODE)
                intent.putExtra(PHONE_NUMBER, mBinding.etLoginPhoneNumber.text.toString())
                intent.putExtra(OTP_ID, otpId)
                intent.putExtra(OTP_EXPIRY_TIME, otpExpiryTime)
                startActivityForResult(intent, VERIFY_OTP_REQ)
            }
        })
    }

    /**
     * navigate to home activity
     */
    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * navigate to home activity
     */
    fun navigateToForGotPassword() {
        val intent = Intent(this, VerifyOtpActivity::class.java)
        intent.putExtra(FORGOT_PASSWORD, TRUE)
        startActivity(intent)
    }

    /**
     * This method is executed when a uer clicks on the login button.
     */
    private fun doLogin() {
        if (mBinding.btLoginContinue.text.toString().equals(resources.getString(R.string.login))) {
            if (mBinding.tiLoginEmail.visibility == View.VISIBLE) {
                mEcomLoginViewModel.callEmailOrPhoneNumSignInApi(EMAIL_VERIFY_TYPE,
                        mBinding.etLoginEmail.text.toString(),
                        mBinding.etLoginPassword.text.toString(),
                        "", "")
            }
        } else {
            if (mBinding.vgPhone.visibility == View.VISIBLE) {
                mEcomLoginViewModel.callEmailOrPhoneNumSignInApi(NUMBER_VERIFY_TYPE,
                        "",
                        "",
                        mBinding.ccpGetNumber.selectedCountryCode,
                        mBinding.etLoginPhoneNumber.text.toString())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == VERIFY_OTP_REQ) {
                if (resultCode == Activity.RESULT_OK) {
                    val isToFinish = data.getBooleanExtra(FINISH, FALSE)
                    if (isToFinish) {
                        navigateToHome()
                        finish()
                    }
                }
            }
        }
    }
}
