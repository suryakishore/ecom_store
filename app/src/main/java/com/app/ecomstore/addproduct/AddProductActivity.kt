package com.app.ecomstore.addproduct

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityAddProductBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.MyScrollListener
import com.app.delivxstore.utility.RxTextView
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyBtmSheet
import com.app.ecomstore.forcepick.ForcePickActivity
import com.app.ecomstore.uiutil.barcodescanning.BarCodePreviewActivity
import com.app.ecomstore.util.EcomConstants
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * This method is used to add the product activity
 */
class AddProductActivity : DaggerAppCompatActivity(), SelectItem {
    lateinit var mBinding: ActivityAddProductBinding
    lateinit var adapter: AddProductAdapter
    lateinit var viewModel: AddProductViewModel
    lateinit var layoutManager: LinearLayoutManager
    private var mAddProductList = ArrayList<ProductData>()
    private var mPenCount = ZERO
    private var mPage = ONE
    private lateinit var mProducts: Products

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var chooseQtyBtmSheet: ChooseQtyBtmSheet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeViewModel()
        subscribeAddProductData()
        subscribePagedProductData()
        subscribeNoDataFound()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_product)
        mBinding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.addProduct))
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mBinding.tvScan.setOnClickListener {
            val intent = Intent(this, BarCodePreviewActivity::class.java)
            intent.putExtra(IS_FOR_VARIANTS, TRUE)
            intent.putExtra(ADD_PRODUCT, TRUE)
            intent.putExtra(STORE_ORDER_ID, getIntent().getStringExtra(STORE_ORDER_ID))
            intent.putExtra(PARENT_PRODUCT_ID, getIntent().getStringExtra(PARENT_PRODUCT_ID))
            startActivityForResult(intent, BARCODE_REQUEST)
        }
        mBinding.tvAddManually.setOnClickListener {
            val intent = Intent(this, ForcePickActivity::class.java)
            intent.putExtra(CURRENCY_SYMBOL, getIntent().getStringExtra(CURRENCY_SYMBOL))
            intent.putExtra(CURRENCY_CODE, getIntent().getStringExtra(CURRENCY_CODE))
            intent.putExtra(OPEN_CAMERA, FALSE)
            intent.putExtra(ADD_PRODUCT, TRUE)
            intent.putExtra(STORE_ORDER_ID, getIntent().getStringExtra(STORE_ORDER_ID))
            intent.putExtra(PARENT_PRODUCT_ID, getIntent().getStringExtra(PARENT_PRODUCT_ID))
            startActivityForResult(intent, FORCE_PICK_REQUEST)
        }
        if (intent != null) {
            if (intent.getBooleanExtra(SEND_MANUALLY, FALSE)) {
                mProducts = intent.getParcelableExtra(PRODUCT) as Products
                if (this::mProducts.isInitialized) {
                    mBinding.groupProduct.visibility = View.VISIBLE
                    if (mProducts.images != null) {
                        Glide.with(this)
                                .applyDefaultRequestOptions(RequestOptions().override(70, 70).centerCrop())
                                .load(mProducts.images!!.medium).into(mBinding.ivItemAddProduct)
                    }
                    mBinding.tvItemName.text = mProducts.name
                    mBinding.tvItemBrand.text = mProducts.brandName
                    mBinding.tvItemStripCount.text = """${mProducts.getCurrencySymbol()}${Utility.roundOfDoubleValue("""${mProducts.singleUnitPrice.unitPrice}""")}"""
                }
            }
        }
    }

    /**
     * <h2>initializeViewModel</h2>
     * This method is used initialize the viewModel class for this activity.
     */
    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, modelFactory).get(AddProductViewModel::class.java)
        mBinding.viewModel = viewModel
        layoutManager = LinearLayoutManager(this)
        adapter = AddProductAdapter(mAddProductList, this)
        mBinding.rvAvailableProducts.layoutManager = layoutManager
        mBinding.rvAvailableProducts.adapter = adapter
        mBinding.ivCross.setOnClickListener {
            mBinding.etSearchSubstitute.setText("")
            mBinding.groupAddManually.visibility = View.GONE
        }
        RxTextView.textChanges(mBinding.etSearchSubstitute)
                .debounce(BUFFERING_TIME.toLong(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CharSequence> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(charSequence: CharSequence) {
                        val search = charSequence.toString()
                        mBinding.ivCross.visibility = if (search.isEmpty()) View.GONE else View.VISIBLE
                        mBinding.tvScan.visibility = if (search.isEmpty()) View.VISIBLE else View.GONE
                        if (search.isEmpty()) {
                            mAddProductList.clear()
                            adapter.notifyDataSetChanged()
                        } else {
                            viewModel.getAddProducts(search, mPage)
                        }
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                })
        mBinding.rvAvailableProducts.addOnScrollListener(
                object : MyScrollListener(layoutManager) {
                    override fun loadMoreItems() {
                        EcomUtil.printLog(
                                "exe" + "mNotifica   " + mAddProductList.size + "mPenCount" + mPenCount)
                        if (mAddProductList.size < mPenCount) {
                            EcomUtil.printLog("exe" + "sizeWhileCalling" + mAddProductList.size + "LIMIT " + (mAddProductList.size + EcomConstants.LIMIT) + "mPenCount" + mPenCount)
                            ++mPage
                            viewModel.getAddProducts(mBinding.etSearchSubstitute.text.toString(), mPage)
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
     * subscribe to add product data
     */
    private fun subscribeAddProductData() {
        viewModel.onAddProductData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mAddProductList.clear()
                mAddProductList.addAll(it.second!!)
                adapter.notifyDataSetChanged()
                mPenCount = it.first!!
                mBinding.groupNoSubstitutes.visibility = if (mAddProductList.size > ZERO) View.GONE else View.VISIBLE
                mBinding.groupAddManually.visibility = View.GONE
            }
        })
    }

    /**
     * subscribe to add product data
     */
    private fun subscribePagedProductData() {
        viewModel.onPagedData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                mAddProductList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }


    /**
     * subscribe to no product data
     */
    private fun subscribeNoDataFound() {
        viewModel.onDataNotFound().observe(this, androidx.lifecycle.Observer {
            mAddProductList.clear()
            adapter.notifyDataSetChanged()
            mBinding.tvSearchQuery.text = mBinding.etSearchSubstitute.text.toString()
            mBinding.groupAddManually.visibility = View.VISIBLE
        })
    }

    override fun onSelectItem(position: Int) {
        val bundle = Bundle()
        bundle.putString(PRODUCT_ID, mAddProductList.get(position).productId)
        bundle.putString(CHILD_PRODUCT_ID, mAddProductList.get(position).childProductId)
        bundle.putString(STORE_ORDER_ID, intent.getStringExtra(STORE_ORDER_ID))
        if (intent.getBooleanExtra(SEND_MANUALLY, FALSE)) {
            bundle.putString(PRODUCT_ID_TXT, intent.getStringExtra(PRODUCT_ID_TXT))
            bundle.putString(PARENT_PRODUCT_ID, intent.getStringExtra(PARENT_PRODUCT_ID))
            bundle.putBoolean(IS_FOR_SUBSTITUTE, TRUE)
            bundle.putBoolean(SEND_MANUALLY, TRUE)
            bundle.putInt(QUANTITY, intent.getIntExtra(QUANTITY, ZERO))
            bundle.putString(PRODUCT_ORDER_ID, intent.getStringExtra(PRODUCT_ORDER_ID))
            bundle.putParcelable(PRODUCT_ORDER, mAddProductList.get(position))
        } else
            bundle.putBoolean(IS_FOR_SUBSTITUTE, FALSE)
        bundle.putBoolean(NEEDS_WEIGHT, mAddProductList.get(position).needsWeighed!!)
        bundle.putString(CURRENCY_SYMBOL, mAddProductList.get(position).currencySymbol)
        chooseQtyBtmSheet.arguments = bundle
        chooseQtyBtmSheet.show(supportFragmentManager, chooseQtyBtmSheet.getTag())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BARCODE_REQUEST) {
            val intent = Intent()
            intent.putExtra(FINISH, TRUE)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else if (requestCode == FORCE_PICK_REQUEST) {
            val intent = Intent()
            intent.putExtra(FINISH, TRUE)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}