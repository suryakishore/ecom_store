package com.app.ecomstore.uiutil.barcodescanning;

import static com.app.delivxstore.utility.VariableConstants.CAMERA_PIC;
import static com.app.delivxstore.utility.VariableConstants.CROP_IMAGE;
import static com.app.delivxstore.utility.VariableConstants.NOUGHT;
import static com.app.ecomstore.util.EcomConstants.ADD_PRODUCT;
import static com.app.ecomstore.util.EcomConstants.BARCODE_DETECTION;
import static com.app.ecomstore.util.EcomConstants.CAMERA_PERMISSION_REQ_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.FILE_PATH;
import static com.app.ecomstore.util.EcomConstants.FORCE_PICK_REQUEST;
import static com.app.ecomstore.util.EcomConstants.FOUR;
import static com.app.ecomstore.util.EcomConstants.IMAGE_URL;
import static com.app.ecomstore.util.EcomConstants.IS_FOR_VARIANTS;
import static com.app.ecomstore.util.EcomConstants.ITEM_DATA;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.OPEN_CAMERA;
import static com.app.ecomstore.util.EcomConstants.PARENT_FOLDER;
import static com.app.ecomstore.util.EcomConstants.PARENT_PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ORDER;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.QUANTITY;
import static com.app.ecomstore.util.EcomConstants.STORE_ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.ZERO;
import static eu.janmuller.android.simplecropimage.CropImage.RETURN_DATA;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityLivePreviewBinding;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyBtmSheet;
import com.app.ecomstore.forcepick.ForcePickActivity;
import com.app.ecomstore.uiutil.barcodescanning.barcode.BarCodeCommunicator;
import com.app.ecomstore.uiutil.barcodescanning.barcode.BarcodeScanningProcessor;
import com.app.ecomstore.uiutil.barcodescanning.camera.CameraSource;
import com.app.ecomstore.uiutil.barcodescanning.camera.CameraSourcePreview;
import com.app.ecomstore.uiutil.barcodescanning.graphic.GraphicOverlay;
import com.app.ecomstore.uiutil.dialog.CustomDialogUtil;
import com.app.ecomstore.util.EcomUtil;
import dagger.android.support.DaggerAppCompatActivity;
import eu.janmuller.android.simplecropimage.CropImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/*
 * Purpose – This class is using for scanning the bar code using ML kit
 * @author 3Embed
 * Created on Jan 25, 2019
 * Modified on
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BarCodePreviewActivity extends DaggerAppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback,
    AdapterView.OnItemSelectedListener, CustomDialogUtil.SimpleAlertDialogClickHandler,
    CompoundButton.OnCheckedChangeListener, BarCodeCommunicator {
  @Inject
  ViewModelProvider.Factory mViewModelFactory;
  @Inject
  ChooseQtyBtmSheet chooseQtyBtmSheet;
  private CameraSource mCameraSource = null;
  private CameraSourcePreview mSourcePreview;
  private GraphicOverlay mGraphicOverlay;
  private boolean mIsApiFired = false;
  private ActivityLivePreviewBinding mBinding;
  private BarCodeViewModel mViewModel;
  private String mProductId, mParentProductId;
  private Uri newProfileImageUri;
  private File newFile;
  private String state = Environment.getExternalStorageState();
  private Products mProduct;
  private String mProductOrderId;
  private int mQuantity;
  private boolean mIsToOpenCamera;
  private boolean mIsToOpenVariants;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(
        this,
        R.layout.activity_live_preview);
    initialize();
  }

  /**
   * This method is using to initialize all the basic resources
   */
  private void initialize() {
    mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(
        BarCodeViewModel.class);
    mBinding.setViewModel(mViewModel);
    mProductId = getIntent().getStringExtra(PRODUCT_ID);
    mParentProductId = getIntent().getStringExtra(PARENT_PRODUCT_ID);
    mProductOrderId = getIntent().getStringExtra(PRODUCT_ORDER_ID);
    mQuantity = getIntent().getIntExtra(QUANTITY, ONE);
    mProduct = getIntent().getParcelableExtra(ITEM_DATA);
    mIsToOpenCamera = getIntent().getBooleanExtra(OPEN_CAMERA, FALSE);
    mIsToOpenVariants = getIntent().getBooleanExtra(IS_FOR_VARIANTS, FALSE);
    mSourcePreview = mBinding.firePreview;
    mGraphicOverlay = mBinding.fireFaceOverlay;
    mBinding.ivBackBtn.setOnClickListener(view -> finish());
    allPermissionsGranted();
    mViewModel.mLiveData.observe(this, isMatches -> {
      String productId = (String) isMatches.get(PRODUCT_ID);
      String parentProductId = (String) isMatches.get(PARENT_PRODUCT_ID);
      EcomUtil.printLog("exe" + "productId Second" + productId);
      EcomUtil.printLog("exe" + "parentProductId" + parentProductId);
      if (!mIsToOpenVariants) {
        if (mProductId.equals(productId)) {
          Intent intent = new Intent();
          intent.putExtra(PRODUCT_ID, productId);
          intent.putExtra(PARENT_PRODUCT_ID, parentProductId);
          intent.putExtra(PRODUCT_ORDER_ID, mProductOrderId);
          intent.putExtra(QUANTITY, mQuantity);
          setResult(RESULT_OK, intent);
          finish();
        } else {
          mBinding.clProductNotFound.setVisibility(View.VISIBLE);
          Handler handler = new Handler();
          handler.postDelayed(() -> {
                mBinding.clProductNotFound.setVisibility(View.GONE);
              },
              2000);
        }
      } else {
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_ID, productId);
         bundle.putParcelable(PRODUCT_ORDER, null);
        chooseQtyBtmSheet.setArguments(bundle);
        chooseQtyBtmSheet.show(getSupportFragmentManager(), chooseQtyBtmSheet.getTag());
      }
    });
    mBinding.tvForcePick.setOnClickListener(v -> setCameraPreview());
  }

  /**
   * set the camera preview.
   */
  private void setCameraPreview() {
    Intent intent = new Intent(this, ForcePickActivity.class);
    intent.putExtra(CURRENCY_SYMBOL, getIntent().getStringExtra(CURRENCY_SYMBOL));
    intent.putExtra(CURRENCY_CODE, getIntent().getStringExtra(CURRENCY_CODE));
    intent.putExtra(PRODUCT_ORDER_ID, mProductOrderId);
    intent.putExtra(OPEN_CAMERA, FALSE);
    intent.putExtra(ADD_PRODUCT, getIntent().getBooleanExtra(ADD_PRODUCT, FALSE));
    intent.putExtra(STORE_ORDER_ID, getIntent().getStringExtra(STORE_ORDER_ID));
    intent.putExtra(PARENT_PRODUCT_ID, getIntent().getStringExtra(PARENT_PRODUCT_ID));
    intent.putExtra(ITEM_DATA, mProduct);
    startActivityForResult(intent, FORCE_PICK_REQUEST);
  }

  @Override
  public synchronized void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    mSourcePreview.stop();
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_DENIED) {
      createCameraSource(BARCODE_DETECTION);
      startCameraSource();
    } else {
      allPermissionsGranted();
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
    // Do nothing.
  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (mCameraSource != null) {
      mCameraSource.setFacing(
          isChecked ? CameraSource.CAMERA_FACING_FRONT : CameraSource.CAMERA_FACING_BACK);
    }
    mSourcePreview.stop();
    startCameraSource();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return true;
  }

  private void createCameraSource(String model) {
    if (mCameraSource == null) {
      mCameraSource = new CameraSource(this, mGraphicOverlay);
    }
    try {
      if (BARCODE_DETECTION.equals(model)) {
        mCameraSource.setMachineLearningFrameProcessor(new BarcodeScanningProcessor(this));
      }
    } catch (Exception e) {
      Toast.makeText(
          getApplicationContext(),
          "Can not create image processor: " + e.getMessage(),
          Toast.LENGTH_LONG)
          .show();
    }
  }

  /**
   * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
   * (e.g., because onResume was called before the camera source was created), this will be called
   * again when the camera source is created.
   */
  private void startCameraSource() {
    if (mCameraSource != null) {
      try {
        mSourcePreview.start(mCameraSource, mGraphicOverlay);
      } catch (IOException e) {
        mCameraSource.release();
        mCameraSource = null;
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    startCameraSource();
  }

  /**
   * Stops the camera.
   */
  @Override
  protected void onPause() {
    super.onPause();
    mSourcePreview.stop();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (mCameraSource != null) {
      mCameraSource.release();
    }
  }

  private void allPermissionsGranted() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat
          .requestPermissions(
              this, new String[]{Manifest.permission.CAMERA},
              CAMERA_PERMISSION_REQ_CODE);
    } else {
      createCameraSource(BARCODE_DETECTION);
    }
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NotNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == CAMERA_PERMISSION_REQ_CODE
        && grantResults[ZERO] == PackageManager.PERMISSION_GRANTED) {
      createCameraSource(BARCODE_DETECTION);
    } else {
      finish();
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public void onSuccessFullBarCodeRead(String barCode) {
    EcomUtil.printLog("exe" + "barCode" + barCode);
    if (!mIsApiFired && !TextUtils.isEmpty(barCode)) {
      mViewModel.getProductDetails(barCode);
      mIsApiFired = true;
    }
  }

  @Override
  public void onOkClickListener(int type) {
    finish();
  }

  private void openCamera() {
    setPicPath();
    try {
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      intent.putExtra(MediaStore.EXTRA_OUTPUT, newProfileImageUri);
      intent.putExtra(RETURN_DATA, true);
      startActivityForResult(intent, CAMERA_PIC);
    } catch (ActivityNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Storing the Image in local file
   */
  private void setPicPath() {
    EcomUtil.printLog("RegistrationAct Inside takePicFromCamera():");
    try {
      Log.d("camerapic", "takePicFromCamera: ");
      String takenNewImage = "";
      state = Environment.getExternalStorageState();
      takenNewImage = String.format("%s%d.png", PARENT_FOLDER, System.nanoTime());
      File newFile1;
      if (Environment.MEDIA_MOUNTED.equals(state)) {
        newFile1 = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            takenNewImage);
        newFile = newFile1;
      } else {
        newFile1 = new File(this.getFilesDir(), takenNewImage);
        newFile = newFile1;
      }
      Uri newProfileImageUri1;
      if (Build.VERSION.SDK_INT >= NOUGHT) {
        newProfileImageUri1 = FileProvider.getUriForFile(this,
            String.format("%s.provider", this.getApplicationContext().getPackageName()),
            newFile);
      } else {
        newProfileImageUri1 = Uri.fromFile(newFile);
      }
      newProfileImageUri = newProfileImageUri1;
      EcomUtil.printLog("RegistrationAct FilePAth in takePicFromCamera()  new: "
          + newFile.getPath() + " new profileUri = " + newProfileImageUri);
    } catch (ActivityNotFoundException e) {
      EcomUtil.printLog("RegistrationAct cannot take picture: " + e);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case CAMERA_PIC:
        EcomUtil.printLog("got result" + newFile);
        Intent intent = new Intent(this, ForcePickActivity.class);
        ArrayList<File> documents = new ArrayList<File>();
        documents.add(newFile);
        intent.putExtra(FILE_PATH, documents);
        intent.putExtra(ITEM_DATA, mProduct);
        startActivityForResult(intent, FORCE_PICK_REQUEST);
        break;
      case CROP_IMAGE:
        break;
      case FORCE_PICK_REQUEST:
        if (resultCode == RESULT_OK && data != null) {
          String imgUrl = data.getStringExtra(IMAGE_URL);
          EcomUtil.printLog("got result" + "imgUrl" + imgUrl);
          Intent intentImg = new Intent();
          intentImg.putExtra(IMAGE_URL, imgUrl);
          intentImg.putExtra(PRODUCT_ORDER_ID, mProductOrderId);
          intentImg.putExtra(QUANTITY, data.getIntExtra(QUANTITY, ONE));
          setResult(RESULT_OK, intentImg);
          finish();
        }
        break;
      default:
        break;
    }
  }

  /**
   * after getting Image from Camera starting crop activity
   */
  private void startCropImage() {
    Intent intent = new Intent(this, CropImage.class);
    intent.putExtra(CropImage.IMAGE_PATH, newFile.getPath());
    intent.putExtra(CropImage.SCALE, true);
    intent.putExtra(CropImage.ASPECT_X, FOUR);
    intent.putExtra(CropImage.ASPECT_Y, FOUR);
    startActivityForResult(intent, CROP_IMAGE);
  }
}