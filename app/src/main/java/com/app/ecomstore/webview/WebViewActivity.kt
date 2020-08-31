package com.app.ecomstore.webview

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityWebViewBinding
import com.app.delivxstore.utility.VariableConstants.PDF_URL
import com.app.ecomstore.printlabel.LabelBags
import com.app.ecomstore.util.EcomConstants.BAG_COUNT
import com.app.ecomstore.util.EcomConstants.SHIPPING_LABEL
import com.app.ecomstore.util.EcomUtil

/**
 * This activity will open the web view activity
 */
class WebViewActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityWebViewBinding
    private var listBags = ArrayList<LabelBags>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        startWebView()
    }

    /**
     * used to open the web view
     */
    private fun startWebView() {
        mBinding.layoutButton.buttonCommon.setText(resources.getString(R.string.printAll))
        mBinding.webView.settings.javaScriptEnabled = true
        mBinding.webView.settings.loadWithOverviewMode = true
        mBinding.webView.settings.setSupportZoom(true)
        mBinding.webView.setWebViewClient(MyWebViewClient())
        if (intent != null) {
            val shippingLabel = intent.getStringExtra(SHIPPING_LABEL)
            EcomUtil.printLog("exe" + "label    " + PDF_URL + shippingLabel)
            mBinding.webView.loadUrl(PDF_URL + shippingLabel)
        }
        mBinding.layoutButton.buttonCommon.setOnClickListener {
            val intent = Intent()
            intent.putExtra(BAG_COUNT, listBags)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            EcomUtil.printLog("exe" + "shouldOverrideUrlLoading")
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            mBinding.progressBar.visibility = View.GONE
            EcomUtil.printLog("exe" + "onPageFinished")
            super.onPageFinished(view, url)
            if (view.getContentHeight() == 0) {
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.webView.loadUrl(PDF_URL + intent.getStringExtra(SHIPPING_LABEL));
            }
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            mBinding.progressBar.visibility = View.VISIBLE
            EcomUtil.printLog("exe" + "onPageStarted")
            super.onPageStarted(view, url, favicon)
        }
    }
}