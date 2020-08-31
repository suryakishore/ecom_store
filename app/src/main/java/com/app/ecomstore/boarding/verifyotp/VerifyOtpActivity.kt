package com.app.ecomstore.boarding.verifyotp

import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.BuildConfig
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityVerifyOtpBinding
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * THis activity is used to verify the otp which user entered.
 */
class VerifyOtpActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mBinding: ActivityVerifyOtpBinding
    lateinit var ecomVerifyOtpViewModel: EcomVerifyOtpViewModel
    private var mCountDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeOnTextChange()
        subscribeToButtonClick()
        subscribeOnErrorData()
        subscribeResend()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp)
    }

    /**
     * This method is used to set the error.
     */
    private fun subscribeOnErrorData() {
        ecomVerifyOtpViewModel.onErrorData().observe(this, Observer {
            Utility.mShowMessage(resources.getString(R.string.message), it, this)
        })
    }

    /**
     * This method is used to set the error.
     */
    private fun subscribeToButtonClick() {
        ecomVerifyOtpViewModel.onButtonClick().observe(this, Observer {
            if (mBinding.btLoginVerify.text.toString().equals(resources.getString(R.string.retry))) {
                setInvalidCode(resources.getDrawable(R.drawable.rectangle_corner_bank_acc_gunsmoke), resources.getColor(R.color.blackShade), TRUE)
                showOtpViews(otpId = intent.getStringExtra(OTP_ID), phoneNumber = intent.getStringExtra(PHONE_NUMBER), countryCode = intent.getStringExtra(COUNTRY_CODE),
                        otpExpiryTime = intent.getLongExtra(OTP_EXPIRY_TIME, 0))
                ecomVerifyOtpViewModel.callVerifyOtp()
            } else if (mBinding.btLoginVerify.text.toString().equals(resources.getString(R.string.send))) {
                EcomUtil.hideSoftKeyboard(mBinding.clSuccess)
                mBinding.clSuccess.visibility = VISIBLE
                mBinding.btLoginVerify.visibility = GONE
                (mBinding.successTick.getDrawable() as Animatable).start()
                val verificationMsg = EcomUtil.getColoredSpanned(resources.getString(R.string.resetLinkDesc), "#7E808C")
                val number = EcomUtil.getColoredSpanned(mBinding.etForGotEmail.text.toString(), "#0C5260")
                mBinding.tvSucDesc.setText(Html.fromHtml(verificationMsg + " " + number))
            }
        })
    }

    /**
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        ecomVerifyOtpViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EcomVerifyOtpViewModel::class.java)
        mBinding.viewModel = ecomVerifyOtpViewModel
        ecomVerifyOtpViewModel.getDeviceDetails(
                Utility.getDeviceId(this),
                Utility.getIpAddress(this),
                Build.MODEL,
                Build.VERSION.RELEASE,
                BuildConfig.VERSION_NAME,
                Build.MODEL + "_3Embed"
        )
        if (intent.getBooleanExtra(FORGOT_PASSWORD, FALSE)) {
            mBinding.vgOtp.visibility = GONE
            mBinding.tiForGotEmail.visibility = VISIBLE
            mBinding.btLoginVerify.setText(resources.getString(R.string.send))
            mBinding.tvTabBarTitle.setText(resources.getString(R.string.forgotPassword))
            mBinding.tvEnterCode.setText(resources.getString(R.string.enterEmail))
            mBinding.tvOtpDescText.setText(resources.getString(R.string.enterEmailDesc))
        } else {
            val verificationMsg = EcomUtil.getColoredSpanned(resources.getString(R.string.otpDesc), "#7E808C")
            val number = EcomUtil.getColoredSpanned(intent.getStringExtra(COUNTRY_CODE) +
                    "-" + intent.getStringExtra(PHONE_NUMBER), "#0C5260")
            mBinding.tvOtpDescText.setText(Html.fromHtml(verificationMsg + " " + number))
            showOtpViews(otpId = intent.getStringExtra(OTP_ID), phoneNumber = intent.getStringExtra(PHONE_NUMBER), countryCode = intent.getStringExtra(COUNTRY_CODE),
                    otpExpiryTime = intent.getLongExtra(OTP_EXPIRY_TIME, 0))
        }
    }

    /**
     * shows the OTP views in which user enters the otp obtained from mobile
     */
    private fun showOtpViews(otpId: String, phoneNumber: String, countryCode: String, otpExpiryTime: Long) {
        ecomVerifyOtpViewModel.otpId = otpId
        mBinding.tvResetPassResend.isEnabled = FALSE
        mBinding.tvResetPassCodeTxt.visibility = GONE
        mBinding.etResetPassFir.setText("")
        mBinding.etResetPassSec.setText("")
        mBinding.etResetPassThi.setText("")
        mBinding.etResetPassFour.setText("")
        mBinding.etResetPassFir.requestFocus()
        mBinding.etResetPassFir.isFocusable = TRUE
        mBinding.tvResetPassResend.setTextColor(resources.getColor(R.color.blackShade))
        mCountDownTimer = object : CountDownTimer(otpExpiryTime * THOUSAND, THOUSAND) {
            override fun onTick(millisUntilFinished: Long) {
                mBinding.tvResetPassResend.setText(EcomUtil.getTimerValue(millisUntilFinished))
            }

            override fun onFinish() {
                mCountDownTimer!!.cancel()
                mBinding.tvResetPassCodeTxt.visibility = VISIBLE
                mBinding.tvResetPassResend.text = resources.getString(R.string.resend)
                mBinding.tvResetPassResend.setTextColor(resources.getColor(R.color.colorPrimary))
                mBinding.tvResetPassResend.isEnabled = TRUE
            }
        }
        mCountDownTimer!!.start()
    }

    /**
     * <p>This method is used to show the success dialog of the forgot mPassword.</p>
     */
    private fun subscribeResend() {
        ecomVerifyOtpViewModel.onResendClicked().observe(this, Observer {
            val hashMap: HashMap<String, Any>? = it.second
            if (hashMap != null) {
                val otpId = hashMap.get(OTP_ID) as String
                val otpExpiryTime = hashMap.get(OTP_EXPIRY_TIME) as Long
                showOtpViews(otpId = otpId, phoneNumber = intent.getStringExtra(PHONE_NUMBER), countryCode = intent.getStringExtra(COUNTRY_CODE), otpExpiryTime = otpExpiryTime)
            }
        })
    }

    /**
     * This method is used to listen the text change of the edit text
     */
    private fun subscribeOnTextChange() {
        ecomVerifyOtpViewModel.onTextChanged().observe(this, androidx.lifecycle.Observer {
            when (it) {
                VerifyOtpUiAction.OTP_FIRST -> {
                    setFocus(if (mBinding.etResetPassFir.text.toString().length == ONE) mBinding.etResetPassSec else mBinding.etResetPassFir)
                }
                VerifyOtpUiAction.OTP_SECOND -> {
                    setFocus(if (mBinding.etResetPassSec.text.toString().length == ONE) mBinding.etResetPassThi else mBinding.etResetPassFir)
                }
                VerifyOtpUiAction.OTP_THREE -> {
                    setFocus(if (mBinding.etResetPassThi.text.toString().length == ONE) mBinding.etResetPassFour else mBinding.etResetPassSec)
                }
                VerifyOtpUiAction.RESEND -> {
                    ecomVerifyOtpViewModel.callSendOtpApi(phoneNumber = intent.getStringExtra(PHONE_NUMBER), countryCode = intent.getStringExtra(COUNTRY_CODE))
                }
                VerifyOtpUiAction.BACK -> {
                    onBackPressed()
                }
                VerifyOtpUiAction.HOME -> {
                    navigateToHome()
                }
                VerifyOtpUiAction.FINISh -> {
                    finish()
                }
                VerifyOtpUiAction.INVALID_CODE -> {
                    if (mCountDownTimer != null)
                        mCountDownTimer!!.cancel()
                    setInvalidCode(resources.getDrawable(R.drawable.rectangle_corner_primary), resources.getColor(R.color.red), FALSE)
                }
            }
        })
    }

    /**
     * navigate to home activity
     */
    private fun navigateToHome() {
        val intent = Intent()
        intent.putExtra(FINISH, TRUE)
        setResult(RESULT_OK, intent)
        finish()
    }

    /**
     * sets the focus for all edit texts
     * @param editText edit text references
     */
    private fun setFocus(editText: EditText) {
        editText.requestFocus()
        editText.isFocusable = TRUE
        editText.isCursorVisible = TRUE
    }

    /**
     * used to manage the otp views if the otp entered wrong and retry again.
     */
    private fun setInvalidCode(backGround: Drawable, color: Int, isVisible: Boolean) {
        mBinding.tvResetPassCodeTxt.visibility = if (isVisible) VISIBLE else GONE
        mBinding.tvResetPassResend.isEnabled = if (isVisible) TRUE else FALSE
        mBinding.tvResetPassResend.setText(if (isVisible) resources.getString(R.string.resend) else resources.getString(R.string.invalidCode))
        mBinding.tvResetPassResend.setTextColor(if (isVisible) resources.getColor(R.color.colorPrimary) else resources.getColor(R.color.red))
        mBinding.btLoginVerify.setText(if (isVisible) resources.getString(R.string.verify) else resources.getString(R.string.retry))
        mBinding.etResetPassFir.setTextColor(color)
        mBinding.etResetPassSec.setTextColor(color)
        mBinding.etResetPassThi.setTextColor(color)
        mBinding.etResetPassFour.setTextColor(color)
        mBinding.etResetPassFir.setBackgroundDrawable(backGround)
        mBinding.etResetPassSec.setBackgroundDrawable(backGround)
        mBinding.etResetPassThi.setBackgroundDrawable(backGround)
        mBinding.etResetPassFour.setBackgroundDrawable(backGround)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCountDownTimer != null)
            mCountDownTimer!!.cancel()
    }

    override fun onBackPressed() {
        if (mBinding.clSuccess.visibility == VISIBLE) {
            mBinding.clSuccess.visibility = GONE
            mBinding.btLoginVerify.visibility = VISIBLE
        } else
            super.onBackPressed()
    }
}
