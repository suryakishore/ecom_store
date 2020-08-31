package com.app.ecomstore.forceaddproduct

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityAddManuallyNewProductBinding
import com.app.ecomstore.util.EcomConstants.*
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AddManuallyNewProductActivity : DaggerAppCompatActivity() {
    lateinit var binding: ActivityAddManuallyNewProductBinding
    lateinit var viewModel: AddManuallyProductViewModel

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        subScribeAdded()
    }

    /**
     * this method is used to initialize the view
     */
    private fun initializeView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_manually_new_product)
        viewModel = ViewModelProviders.of(this, modelFactory).get(AddManuallyProductViewModel::class.java)
        binding.viewmodel = viewModel
        binding.layoutToolBar.textViewAllTitle.setText(resources.getString(R.string.productDetails))
        binding.layoutButton.buttonCommon.setText(resources.getString(R.string.sendProduct))
        binding.tvPerKg.setSelected(true)
        if (intent.getStringExtra(IMAGE_URL) != null) {
            Glide.with(this)
                    .load(intent.getStringExtra(IMAGE_URL))
                    .into(binding.ivPreview)
        }
        binding.layoutToolBar.imageViewAllBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.layoutButton.buttonCommon.setOnClickListener {
            if (!binding.etName.text.toString().isEmpty()) {
                if (!binding.etQty.text.toString().isEmpty()) {
                    if (!binding.etPricePerProduct.text.toString().isEmpty()) {
                        if (!binding.etTotalCharge.text.toString().isEmpty()) {
                            viewModel.addToCartProduct(binding.etName.text.toString(), intent.getStringExtra(IMAGE_URL), intent.getStringExtra(STORE_ORDER_ID), binding.tvPerKg.isSelected, binding.etQty.text.toString().toInt(), binding.etPricePerProduct.text.toString().toInt())
                        } else {
                            viewModel.errorTotalCharge.set(TRUE)
                        }
                    } else {
                        viewModel.errorPriceUnit.set(TRUE)
                    }
                } else {
                    viewModel.errorQty.set(TRUE)
                }
            } else {
                viewModel.errorItemName.set(TRUE)
            }
        }
        binding.tvPerKg.setOnClickListener {
            binding.tiWeight.visibility = View.VISIBLE
            binding.tiQty.visibility = View.GONE
            binding.tvPerKg.setSelected(true)
            binding.tvPerUnit.setSelected(false)
            binding.tvPerKg.setTextColor(resources.getColor(R.color.colorPrimary))
            binding.tvPerKg.setBackgroundDrawable(resources.getDrawable(R.drawable.rectangle_corner_bank_green))
            binding.tvPerUnit.setTextColor(resources.getColor(R.color.blackShade))
            binding.tvPerUnit.setBackgroundDrawable(resources.getDrawable(R.drawable.rectangle_corner_bank_acc_gunsmoke))
        }
        binding.tvPerUnit.setOnClickListener {
            binding.tiQty.visibility = View.VISIBLE
            binding.tiWeight.visibility = View.GONE
            binding.tvPerKg.setSelected(false)
            binding.tvPerUnit.setSelected(true)
            binding.tvPerUnit.setTextColor(resources.getColor(R.color.colorPrimary))
            binding.tvPerUnit.setBackgroundDrawable(resources.getDrawable(R.drawable.rectangle_corner_bank_green))
            binding.tvPerKg.setTextColor(resources.getColor(R.color.blackShade))
            binding.tvPerKg.setBackgroundDrawable(resources.getDrawable(R.drawable.rectangle_corner_bank_acc_gunsmoke))
        }
    }

    /**
     * subscribe to product order
     */

    private fun subScribeAdded() {
        viewModel.onProductAdded().observe(this, Observer {
            if (it != null && it) {
                val intent = Intent()
                intent.putExtra(FINISH, TRUE)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }
}