package com.app.ecomstore.addproduct.addproductbottomsheet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.FragmentBtmSheetChooseQtyBinding
import com.app.delivxstore.utility.Utility
import com.app.ecomstore.addproduct.ProductData
import com.app.ecomstore.substitute.ProductSubStituteData
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

/**
 * This bottom sheet class used to show the variants for the product.
 */
class ChooseQtyBtmSheet @Inject constructor() : BottomSheetDialogFragment(), ProductAttributesClick {
    lateinit var mBinding: FragmentBtmSheetChooseQtyBinding
    lateinit var viewModel: ChooseQtyViewModel
    lateinit var productSubStituteData: ProductSubStituteData
    lateinit var productData: ProductData
    private var mVariantData = ArrayList<VariantData>()
    lateinit var adapter: VariantAdapter

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_btm_sheet_choose_qty, container, false)
        initViews()
        subscribeVariantData()
        subscribeAddedToCart()
        return mBinding.root
    }

    /**
     * initialize the views.
     */
    private fun initViews() {
        viewModel = ViewModelProviders.of(this, modelFactory).get(ChooseQtyViewModel::class.java)
        mBinding.viewmodel = viewModel
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        adapter = VariantAdapter(mVariantData, this)
        mBinding.rvVariantItems.adapter = adapter
        if (arguments != null) {
            if (arguments!!.getBoolean(IS_FOR_SUBSTITUTE)) {
                if (arguments!!.getBoolean(SEND_MANUALLY)) {
                    productData = arguments!!.getParcelable(PRODUCT_ORDER)!!
                } else
                    productSubStituteData = arguments!!.getParcelable(PRODUCT_ORDER)!!
                mBinding.layoutButton.buttonCommon.text = resources.getString(R.string.confirm)
            } else {
                mBinding.layoutButton.buttonCommon.text = resources.getString(R.string.addToCart)
            }
            if (arguments!!.getBoolean(NEEDS_WEIGHT)) {
                mBinding.vgQuantity.visibility = View.VISIBLE
                mBinding.vgAddItem.visibility = View.GONE
            }
            viewModel.getVariants(arguments!!.getString(PRODUCT_ID, ""))
        }
        mBinding.ivCross.setOnClickListener {
            if (dialog != null) {
                if (dialog!!.isShowing)
                    dialog!!.dismiss()
            }
        }

        mBinding.tvQtyPlus.setOnClickListener {
            var qty = mBinding.tvItemQty.text.toString().toInt()
            if (arguments?.getBoolean(IS_FOR_SUBSTITUTE, FALSE)!!) {
                if (qty != arguments?.getInt(QUANTITY, ZERO)) {
                    mBinding.tvItemQty.text = (++qty).toString()
                }
            } else {
                mBinding.tvItemQty.text = (++qty).toString()
            }
        }
        mBinding.tvQtyMinus.setOnClickListener {
            if (mBinding.tvItemQty.text.toString().toInt() != ONE) {
                var qty = mBinding.tvItemQty.text.toString().toInt()
                mBinding.tvItemQty.text = (--qty).toString()
            }
        }
        mBinding.layoutButton.buttonCommon.setOnClickListener {
            if (arguments!!.getBoolean(IS_FOR_SUBSTITUTE)) {
                if (arguments!!.getBoolean(SEND_MANUALLY)) {
                    viewModel.replaceProduct(productData.childProductId!!,
                            arguments!!.getString(PRODUCT_ORDER_ID, ""), productData.productId!!, mBinding.tvItemQty.text.toString().toInt(), arguments!!.getString(PRODUCT_ID_TXT, ""), arguments!!.getString(PARENT_PRODUCT_ID, ""))
                } else
                    viewModel.replaceProduct(productSubStituteData.childProductId!!,
                            arguments!!.getString(PRODUCT_ORDER_ID, ""), productSubStituteData.parentProductId!!, mBinding.tvItemQty.text.toString().toInt(), arguments!!.getString(PRODUCT_ID_TXT, ""), arguments!!.getString(PARENT_PRODUCT_ID, ""))
            } else if (arguments!!.getBoolean(NEEDS_WEIGHT)) {
                if (mBinding.etWeight.text.toString().length > ZERO)
                    viewModel.addToCartProduct(arguments!!.getString(CHILD_PRODUCT_ID, ""), arguments!!.getString(STORE_ORDER_ID, ""), arguments!!.getString(PRODUCT_ID, ""), mBinding.etWeight.text.toString().toInt())
                else
                    Utility.toastMessage(activity, resources.getString(R.string.weightError))
            } else
                viewModel.addToCartProduct(arguments!!.getString(CHILD_PRODUCT_ID, ""), arguments!!.getString(STORE_ORDER_ID, ""), arguments!!.getString(PRODUCT_ID, ""), mBinding.tvItemQty.text.toString().toInt())
        }
    }

    /**
     * subscribe to add product data
     */
    private fun subscribeVariantData() {
        viewModel.onVariantData().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                if (it.first != null) {
                    val itemData = it.first!!
                    if (itemData.images != null && itemData.images!!.size > ZERO) {
                        Glide.with(this)
                                .applyDefaultRequestOptions(RequestOptions().override(70, 70).centerCrop())
                                .load(itemData.images!!.get(ZERO).medium).into(mBinding.ivItemAddProduct)
                    }
                    mBinding.tvItemName.text = itemData.productName
                    if (itemData.brandName != null && !itemData.brandName?.isEmpty()!!) {
                        mBinding.tvItemBrand.text = itemData.brandName
                    } else {
                        mBinding.tvItemBrand.visibility = View.GONE
                    }
                    if (itemData.finalPriceList != null) {
                        if (itemData.finalPriceList?.basePrice != null && itemData.finalPriceList?.discountPrice!!.toDouble() > ZERO) {
                            mBinding.tvBasePrice.visibility = View.VISIBLE
                            mBinding.tvBasePrice.text = String.format("%s %s", itemData.currencySymbol, Utility.roundOfDoubleValue(itemData.finalPriceList?.basePrice))
                            EcomUtil.strikeThroughText(mBinding.tvBasePrice)
                        }
                        mBinding.tvItemPrice.text = String.format("%s %s", itemData.currencySymbol, Utility.roundOfDoubleValue(itemData.finalPriceList?.finalPrice))
                    }
                }
                mVariantData.clear()
                mVariantData.addAll(it.second!!)
                EcomUtil.printLog("exe" + "mVariantData" + mVariantData.size)
                adapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * subscribe to add product data
     */
    private fun subscribeAddedToCart() {
        viewModel.onProductAdded().observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                val intent = Intent()
                intent.putExtra(FINISH, TRUE)
                activity!!.setResult(Activity.RESULT_OK, intent)
                activity!!.finish()
            }
        })
    }

    override fun onClick(productId: String) {
        viewModel.getChildVariants(arguments!!.getString(PRODUCT_ID, ""), productId)
    }
}