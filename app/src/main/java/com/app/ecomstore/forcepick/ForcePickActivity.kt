package com.app.ecomstore.forcepick

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.app.delivxstore.R
import com.app.delivxstore.databinding.ActivityForcePickBinding
import com.app.delivxstore.main.mobileView.orderDetails.models.Products
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberBtmSheet
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.SelectItem
import com.app.delivxstore.utility.Utility
import com.app.delivxstore.utility.VariableConstants
import com.app.ecomstore.addproduct.ProductData
import com.app.ecomstore.forceaddproduct.AddManuallyNewProductActivity
import com.app.ecomstore.printlabel.LabelBags
import com.app.ecomstore.printlabel.PrintLabelsActivity
import com.app.ecomstore.uiutil.barcodescanning.camera.CameraSource
import com.app.ecomstore.uiutil.customcamera.CameraPreview
import com.app.ecomstore.util.EcomConstants.*
import com.app.ecomstore.util.EcomUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerAppCompatActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


/**
 * This activity is used to force pick the bar code.
 */
class ForcePickActivity : DaggerAppCompatActivity(), SelectItem {
    lateinit var mBinding: ActivityForcePickBinding
    private var mItem: Products? = null
    private var mProductData: ProductData? = null
    private var newProfileImageUri: Uri? = null
    lateinit var newFile: File
    lateinit var options: RequestOptions
    private var state = Environment.getExternalStorageState()
    lateinit var mViewModel: ForcePickViewModel
    private var mCamera: Camera? = null
    private var mCameraSource: CameraSource? = null
    lateinit var mPictureItem: String
    lateinit var mForcePickImagesAdapter: ForcePickImagesAdapter
    private var imageList = ArrayList<String>()
    private var uploadImageList = ArrayList<String>()
    private var mUploadIndex = ZERO

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var orderNumberSheet: OrderNumberBtmSheet


    /*
     * This variable used to create the path for the picture path when you taken from custom camera.
     */
    @SuppressLint("RestrictedApi")
    private val mPicture = Camera.PictureCallback { data, camera ->
        val pictureFile: File? = getPicPath()
        if (pictureFile == null) {
            Log.d("exe", "Error creating media file, check storage permissions")
            return@PictureCallback
        }
        try {
            val fos = FileOutputStream(pictureFile)
            fos.write(data)
            fos.close()
            mBinding.ivItemForcePick.visibility = View.VISIBLE
            mBinding.clTakePicture.visibility = View.GONE
            mBinding.ivCamera.visibility = View.VISIBLE
            mBinding.tvRetake.visibility = View.VISIBLE
            Glide.with(this)
                    .load(newFile)
                    .apply(options)
                    .into(mBinding.ivItemForcePick)
            Log.d("exe", "Error: " + "OPEN_CAMERA")
            if (intent.getBooleanExtra(OPEN_CAMERA, FALSE)) {
                Log.d("exe", "Error: " + "OPEN_CAMERA INSIDE")
                mBinding.rvTakenImages.visibility = View.VISIBLE
                imageList.add(imageList.size - ONE, newFile.toString())
                mForcePickImagesAdapter.notifyDataSetChanged()
            }
        } catch (e: FileNotFoundException) {
            Log.d("exe", "File not found: " + e.message)
        } catch (e: IOException) {
            Log.d("exe", "Error accessing file: " + e.message)
        }
        EcomUtil.printLog("exemFile$newFile")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
        initializeImageData()
        initializeUploadImageData()
        uploadImageSuccess()
    }

    /**
     * This method is used to force pick
     */
    @SuppressLint("RestrictedApi")
    fun initializeData() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_force_pick)
        mBinding.layoutToolBar.textViewAllTitle.setText(
                if (intent.getBooleanExtra(ADD_PRODUCT, FALSE)) resources.getString(R.string.takeProductPhoto)
                else
                    if (intent.getBooleanExtra(OPEN_CAMERA, FALSE)) resources.getString(R.string.uploadReceipt)
                    else resources.getString(R.string.forcePick))
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ForcePickViewModel::class.java)
        mBinding.viewModel = mViewModel
        options = RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.ic_place_holder)
        mItem = intent.getParcelableExtra(ITEM_DATA)
        mProductData = intent.getParcelableExtra(ADD_PRODUCT_DATA)
        if (intent.getBooleanExtra(OPEN_CAMERA, FALSE)) {
            imageList.add(CAMERA_ITEM)
            mForcePickImagesAdapter = ForcePickImagesAdapter(imageList, this)
            mBinding.rvTakenImages.adapter = mForcePickImagesAdapter
        }
        if (intent.getBooleanExtra(ADD_PRODUCT, FALSE)) {
            mBinding.itemGrocery.clGroceryItem.visibility = View.GONE
            mBinding.itemAddProduct.clItemAddProduct.visibility = View.GONE
            mBinding.itemAddProduct.tvItemBrand.text = mProductData?.brandTitle
            mBinding.itemAddProduct.tvItemName.text = mProductData?.productName
            mBinding.itemAddProduct.tvAdd.visibility = View.GONE
            mBinding.itemAddProduct.progressBar.visibility = View.GONE
        } else if (mItem != null) {
            mBinding.itemGrocery.tvItemUnAvailableProductName.text = (mItem!!.getName())
            if (mItem!!.getImages() != null) {
                Glide.with(this)
                        .load(mItem!!.getImages().getMedium())
                        .into(mBinding.itemGrocery.ivItemUnAvailableProductImage)
            }
            mBinding.itemGrocery.tvItemSize.text = """${resources.getString(R.string.size)}:"""
            mBinding.itemGrocery.groupAsile.visibility = View.GONE
            mBinding.itemGrocery.hsItemUnAvailableStatus.setVisibility(View.GONE)
            mBinding.itemGrocery.tvItemUnAvailableProductPrice.setText(String.format("%s %s", mItem!!.getCurrencySymbol(),
                    Utility.roundOfDoubleValue(String.format("%s", mItem!!.getAccounting().getUnitPrice()))))
            mBinding.itemGrocery.tvItemQtyAndPrice.setText(String.format("%s %s*%s %s", mItem!!.getQuantity().getValue(), mItem!!.getQuantity().getUnit(), mItem!!.getCurrencySymbol(),
                    Utility.roundOfDoubleValue(String.format("%s", mItem!!.singleUnitPrice.unitPrice))))
        } else {
            mBinding.itemGrocery.clGroceryItem.visibility = View.GONE
        }
        openCamera()
        mBinding.tvRetake.setOnClickListener {
            openCamera()
        }
        mBinding.layoutToolBar.imageViewAllBack.setOnClickListener {
            finish()
        }
        mBinding.ivCamera.setOnClickListener {
            if (intent.getBooleanExtra(OPEN_CAMERA, FALSE)) {
                if (imageList.size > ONE)
                    imageList.removeAt(imageList.size - ONE)
                mViewModel.uploadImage(File(imageList.get(mUploadIndex)), TRUE)
            } else
                mViewModel.uploadImage(newFile, FALSE)
        }
        mBinding.ivClickCamera.setOnClickListener { v -> mCamera!!.takePicture(null, null, mPicture) }
    }

    /**
     * This is used to open the camera
     */
    @SuppressLint("RestrictedApi")
    private fun openCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(
                            this, arrayOf(Manifest.permission.CAMERA),
                            CAMERA_PERMISSION_REQ_CODE)
        }else {
            if (mCameraSource == null) {
                mCameraSource = CameraSource(this, null)
            }
            mBinding.clTakePicture.visibility = View.VISIBLE
            mBinding.ivItemForcePick.visibility = View.GONE
            if (intent.getBooleanExtra(OPEN_CAMERA, FALSE)) {
                mBinding.ivCamera.visibility = if (imageList.size > ONE) View.VISIBLE else View.GONE
            } else
                mBinding.ivCamera.visibility = View.GONE
            mBinding.tvRetake.visibility = View.GONE
            if (mCamera == null)
                mCamera = mCameraSource!!.getCamera()
            val cameraPreview = CameraPreview(this, mCamera)
            mBinding.flCameraPreview.addView(cameraPreview)
        }
    }

    private fun initializeImageData() {
        mViewModel.onImageData().observe(this, Observer {
            EcomUtil.printLog("exe" + "image" + it)
            mPictureItem = it
            if (intent.getBooleanExtra(ADD_PRODUCT, FALSE)) {
                EcomUtil.printLog("exe" + "done")
                val intent = Intent(this, AddManuallyNewProductActivity::class.java)
                intent.putExtra(IMAGE_URL, mPictureItem)
                intent.putExtra(STORE_ORDER_ID, getIntent().getStringExtra(STORE_ORDER_ID))
                intent.putExtra(PARENT_PRODUCT_ID, getIntent().getStringExtra(PARENT_PRODUCT_ID))
                startActivityForResult(intent, ADD_MANUALLY)
            } else if (mItem != null) {
                openQuantityBottomSheet()
            }
        })
    }

    private fun initializeUploadImageData() {
        mViewModel.onUploadImages().observe(this, Observer {
            uploadImageList.add(it)
            ++mUploadIndex
            if (mUploadIndex <= imageList.size - ONE) {
                mViewModel.uploadImage(File(imageList.get(mUploadIndex)), TRUE)
            } else {
                mViewModel.uploadReceiptFiles(uploadImageList, intent.getStringExtra(ORDER_ID),
                        intent.getStringExtra(CURRENCY_SYMBOL), intent.getStringExtra(CURRENCY_CODE))
            }
        })
    }

    /**
     * after uploaded images success open the print labels activity
     */
    private fun uploadImageSuccess() {
        mViewModel.onUploadSuccess().observe(this, Observer {
            val intent = Intent(this, PrintLabelsActivity::class.java)
            intent.putExtra(ORDER_ID, getIntent().getStringExtra(ORDER_ID))
            intent.putExtra(CUSTOMER_NAME, getIntent().getStringExtra(CUSTOMER_NAME))
            intent.putExtra(ITEM_PRICE, getIntent().getStringExtra(ITEM_PRICE))
            intent.putExtra(CURRENCY_SYMBOL, getIntent().getStringExtra(CURRENCY_SYMBOL))
            intent.putExtra(CURRENCY_CODE, getIntent().getStringExtra(CURRENCY_CODE))
            intent.putExtra(QUANTITY, getIntent().getIntExtra(QUANTITY, ZERO))
            intent.putExtra(CUSTOMER_NUMBER, getIntent().getStringExtra(CUSTOMER_NUMBER))
            startActivityForResult(intent, PRINT_LABEL_REQUEST)
        })
    }

    /**
     * after getting Image from Camera starting crop activity
     */
    private fun openQuantityBottomSheet() {
        if (mItem?.quantity != null && mItem?.quantity?.value?.toInt() == ONE) {
            val intent = Intent()
            intent.putExtra(PRODUCT_ORDER_ID, getIntent().getStringExtra(PRODUCT_ORDER_ID))
            intent.putExtra(IMAGE_URL, mPictureItem)
            intent.putExtra(QUANTITY, mItem?.quantity?.value)
            setResult(RESULT_OK, intent)
            finish()
        } else {
            orderNumberSheet.setItemDetails(mItem, FALSE, TRUE)
            orderNumberSheet.show(supportFragmentManager, orderNumberSheet.getTag())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PRINT_LABEL_REQUEST -> {
                if (data != null) {
                    val intent = Intent()
                    val listItems: ArrayList<LabelBags> = data.getParcelableArrayListExtra(BAG_COUNT)
                    EcomUtil.printLog("exe" + "listItems" + listItems.size)
                    intent.putExtra(BAG_COUNT, listItems)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            ADD_MANUALLY -> {
                if (data != null) {
                    val intent = Intent()
                    intent.putExtra(FINISH, TRUE)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
            else -> {
            }
        }
    }

    @SuppressLint("RestrictedApi")
    public fun setQuantity(quantity: Int) {
        EcomUtil.printLog("exe" + "quantity" + quantity)
        mBinding.ivCamera.visibility = View.GONE
        mBinding.ivClickCamera.visibility = View.GONE
        mBinding.cvProductUnavailableToolbar.visibility = View.GONE
        mBinding.clProductNotFound.visibility = View.VISIBLE
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent()
            intent.putExtra(IMAGE_URL, mPictureItem)
            intent.putExtra(QUANTITY, quantity)
            setResult(RESULT_OK, intent)
            finish()
        }, 2000)
    }

    /**
     * Storing the Image in local file
     */
    private fun getPicPath(): File? {
        EcomUtil.printLog("RegistrationAct Inside takePicFromCamera():")
        try {
            Log.d("camerapic", "takePicFromCamera: ")
            var takenNewImage = ""
            state = Environment.getExternalStorageState()
            takenNewImage = String.format("%s%d.png", PARENT_FOLDER, System.nanoTime())
            val newFile1: File
            newFile1 = if (Environment.MEDIA_MOUNTED == state) File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    takenNewImage) else File(this.filesDir, takenNewImage)
            newFile = newFile1
            val newProfileImageUri1: Uri
            newProfileImageUri1 = if (Build.VERSION.SDK_INT >= VariableConstants.NOUGHT) {
                FileProvider.getUriForFile(this, String.format("%s.provider", this.applicationContext.packageName),
                        newFile)
            } else {
                Uri.fromFile(newFile)
            }
            newProfileImageUri = newProfileImageUri1
            EcomUtil.printLog("RegistrationAct FilePAth in takePicFromCamera()  new: "
                    + newFile.path + " new profileUri = " + newProfileImageUri)
        } catch (e: ActivityNotFoundException) {
            EcomUtil.printLog("RegistrationAct cannot take picture: $e")
        }
        return newFile
    }

    override fun onSelectItem(position: Int) {
        if (position == imageList.size - ONE)
            openCamera()
        else {
            imageList.removeAt(position)
            mForcePickImagesAdapter.notifyDataSetChanged()
        }
        EcomUtil.printLog("exe" + "imageList" + imageList.size)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults)
        if (requestCode == CAMERA_PERMISSION_REQ_CODE) {
            // Checking whether user granted the permission or not.
            if (grantResults.size > ZERO
                    && grantResults[ZERO] == PackageManager.PERMISSION_GRANTED) {
                initializeData()
            }
            else{
                initializeData()
            }
        }
    }
}