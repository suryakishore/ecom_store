package com.app.ecomstore.substitute

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityProductSubStitudeBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.MyScrollListener
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.addproduct.AddProductActivity
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyBtmSheet
import com.app.ecomstore.util.EcomConstants
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This activity will used to show the substituted items for the products.
 */
class ProductSubStituteActivity : DaggerAppCompatActivity(), SelectItem {
    lateinit var mBinding: ActivityProductSubStitudeBinding
    lateinit var adapter: SubStitudesAdapter
    lateinit var viewModel: SubStitudeViewModel
    lateinit var layoutManager: LinearLayoutManager
    private var mProductSubStitudeList = ArrayList<ProductSubStituteData>()
    private lateinit var mProducts: Products
    private var mPenCount = ZERO
    private var mPage = ONE

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var chooseQtyBtmSheet: ChooseQtyBtmSheet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeSubStituteProductData()
    }

    /**
     * This method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_sub_stitude)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.subStitute))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mBinding.tvSendSubstitutes.setOnClickListener {
            val intentAddProduct = Intent(this, AddProductActivity::class.java)
            intentAddProduct.putExtra(STORE_ORDER_ID, intent.getStringExtra(STORE_ORDER_ID))
            intentAddProduct.putExtra(PRODUCT_ORDER_ID, intent.getStringExtra(PRODUCT_ORDER_ID))
            intentAddProduct.putExtra(QUANTITY, intent.getIntExtra(QUANTITY, ZERO))
            intentAddProduct.putExtra(PRODUCT, mProducts)
            intentAddProduct.putExtra(PRODUCT_ORDER, mProducts)
            intentAddProduct.putExtra(PARENT_PRODUCT_ID, intent.getStringExtra(PARENT_PRODUCT_ID))
            intentAddProduct.putExtra(PRODUCT_ID_TXT, intent.getStringExtra(PRODUCT_ID_TXT))
            intentAddProduct.putExtra(SEND_MANUALLY, TRUE)
            startActivityForResult(intentAddProduct, ADD_PRODUCT_REQUEST)
        }
    }

    /**
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, modelFactory).get(SubStitudeViewModel::class.java)
        mBinding.viewModel = viewModel
        layoutManager = LinearLayoutManager(this)
        adapter = SubStitudesAdapter(mProductSubStitudeList, this)
        mBinding.rvPreferences.layoutManager = layoutManager
        mBinding.rvPreferences.adapter = adapter
        if (intent != null) {
            mProducts = intent.getParcelableExtra(PRODUCT) as Products
            if (mProducts.images != null) {
                Glide.with(this)
                        .applyDefaultRequestOptions(RequestOptions().override(70, 70).centerCrop())
                        .load(mProducts.images!!.medium).into(mBinding.ivItemAddProduct)
            }
            mBinding.tvItemName.text = mProducts.name
            mBinding.tvItemBrand.text = mProducts.brandName
            mBinding.tvItemStripCount.setText("""${mProducts.getQuantity().getValue()}${mProducts.getQuantity().getUnit()}*${mProducts.getCurrencySymbol()}${Utility.roundOfDoubleValue("""${mProducts.singleUnitPrice.unitPrice}""")}""")
        }
        viewModel.getSubStitudeProducts(intent.getStringExtra(PRODUCT_ID_TXT), intent.getStringExtra(PARENT_PRODUCT_ID), mPage)
        mBinding.rvPreferences.addOnScrollListener(
                object : MyScrollListener(layoutManager) {
                    override fun loadMoreItems() {
                        EcomUtil.printLog(
                                "exe" + "mNotifica   " + mProductSubStitudeList.size + "mPenCount" + mPenCount)
                        if (mProductSubStitudeList.size < mPenCount) {
                            EcomUtil.printLog("exe" + "sizeWhileCalling" + mProductSubStitudeList.size + "LIMIT " + (mProductSubStitudeList.size + EcomConstants.LIMIT) + "mPenCount" + mPenCount)
                            ++mPage
                            viewModel.getSubStitudeProducts(intent.getStringExtra(PRODUCT_ID_TXT), intent.getStringExtra(PARENT_PRODUCT_ID), mPage)
                        }
                    }

                    override fun isLastPage(): Boolean {
                        return viewModel.progressVisible.get()!!
                    }

                    override fun isLoading(): Boolean {
                        return viewModel.progressVisible.get()!!
                    }
                })
    }

    /**
     * subscribe to product substitute data
     */
    private fun subscribeSubStituteProductData() {
        viewModel.subStituteData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mProductSubStitudeList.clear()
                mProductSubStitudeList.addAll(it.second!!)
                adapter.notifyDataSetChanged()
                mPenCount = it.first!!
            }
        })
    }

    override fun onSelectItem(position: Int) {
        val bundle = Bundle()
        bundle.putString(PRODUCT_ID, mProductSubStitudeList.get(position).childProductId)
        bundle.putString(PRODUCT_ID_TXT, intent.getStringExtra(PRODUCT_ID_TXT))
        bundle.putString(PARENT_PRODUCT_ID, intent.getStringExtra(PARENT_PRODUCT_ID))
        bundle.putBoolean(IS_FOR_SUBSTITUTE, TRUE)
        bundle.putInt(QUANTITY, intent.getIntExtra(QUANTITY, ZERO))
        bundle.putString(STORE_ORDER_ID, intent.getStringExtra(STORE_ORDER_ID))
        bundle.putString(PRODUCT_ORDER_ID, intent.getStringExtra(PRODUCT_ORDER_ID))
        bundle.putParcelable(PRODUCT_ORDER, mProductSubStitudeList.get(position))
        chooseQtyBtmSheet.arguments = bundle
        chooseQtyBtmSheet.show(supportFragmentManager, chooseQtyBtmSheet.getTag())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRODUCT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val intent = Intent()
                intent.putExtra(FINISH, TRUE)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
