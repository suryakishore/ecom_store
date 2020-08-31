package com.app.delivxstore.utility;


import static com.app.delivxstore.utility.VariableConstants.CAMERA_PIC;
import static com.app.delivxstore.utility.VariableConstants.GALLERY_PIC;
import static com.app.delivxstore.utility.VariableConstants.NOUGHT;
import static com.app.delivxstore.utility.VariableConstants.PARENT_FOLDER;
import static com.app.delivxstore.utility.VariableConstants.newFile;
import static com.app.delivxstore.utility.VariableConstants.newProfileImageUri;
import static eu.janmuller.android.simplecropimage.CropImage.RETURN_DATA;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import androidx.core.content.FileProvider;
import com.app.delivxstore.R;
import java.io.File;
import java.util.Objects;

/*defines some methods to upload image*/
public class ImageEditUpload implements ActionSheet.ActionSheetListener {
  private Activity context;
  private String state;
  private String imageType;

  public ImageEditUpload(Activity context, String imageType) {
    this.context = context;
    this.imageType = imageType;
    showImageOptions();
  }

  /*show options dialog*/
  private void showImageOptions() {
    final Dialog mDialog = new Dialog(context);
    Objects.requireNonNull(mDialog.getWindow()).
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.white)));
    mDialog.setContentView(R.layout.dialog_image_options);
    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
    Button btnCamera = mDialog.findViewById(R.id.camera);
    Button btnCancel = mDialog.findViewById(R.id.cancel);
    Button btnGallery = mDialog.findViewById(R.id.gallery);
    btnCamera.setOnClickListener(view -> {
      takePicFromCamera();
      mDialog.dismiss();
    });

    btnGallery.setOnClickListener(view -> {
      openGallery();
      mDialog.dismiss();
    });
    btnCancel.setOnClickListener(view -> mDialog.dismiss());
    mDialog.show();
  }

  @Override
  public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

  }

  @Override
  public void onOtherButtonClick(ActionSheet actionSheet, int index) {
    clearOrCreateDir();
    switch (index) {
      case 0:
        openGallery();
        break;
      case 1:
        takePicFromCamera();
        break;
      default:
        break;
    }
  }

  /*opens gallery for choosing picture*/
  private void openGallery() {
    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
    photoPickerIntent.setType("image/*");
    context.startActivityForResult(photoPickerIntent, GALLERY_PIC);
  }

  /*creates directory in internal or external storage*/
  private void clearOrCreateDir() {
    state = Environment.getExternalStorageState();
    File cropImagesDir;
    File[] cropImagesDirectory;
    File profilePicsDir;
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      cropImagesDir = new File(String.format("%s/%s/Media/Images/CropImages",
          Environment.getExternalStorageDirectory(), PARENT_FOLDER));
      profilePicsDir = new File(String.format("%s/%s/Media/Images/Profile_Pictures",
          Environment.getExternalStorageDirectory(), PARENT_FOLDER));
    } else {
      cropImagesDir = new File(String.format("%s/%s/Media/Images/CropImages",
          context.getFilesDir(), PARENT_FOLDER));
      profilePicsDir = new File(String.format("%s/%s/Media/Images/Profile_Pictures",
          context.getFilesDir(), PARENT_FOLDER));
    }
    if (!cropImagesDir.isDirectory()) {
      cropImagesDir.mkdirs();
    } else {
      cropImagesDirectory = cropImagesDir.listFiles();

      if (cropImagesDirectory.length > 0) {
        for (File aCropImagesDirectory : cropImagesDirectory) {
          aCropImagesDirectory.delete();
        }
        Utility.printLog("RegistrationAct CropImages cleared successfully:");
      } else {
        Utility.printLog("RegistrationAct CropImages Dir empty  or null: " + cropImagesDirectory.length);
      }
    }

    if (!profilePicsDir.isDirectory()) {
      profilePicsDir.mkdirs();
      Utility.printLog("RegistrationAct profilePicsDir is created:" + profilePicsDir);
    } else {
      File[] profilePicsDirectory = profilePicsDir.listFiles();

      if (profilePicsDirectory.length > 0) {
        for (File aProfilePicsDirectory : profilePicsDirectory) {

          aProfilePicsDirectory.delete();
        }
        Utility.printLog("RegistrationAct profilePicsDir cleared successfully:");
      } else {
        Utility.printLog("RegistrationAct profilePicsDir empty  or null: " + profilePicsDirectory.length);
      }
    }
  }

  /**
   * <h1>takePicFromCamera</h1>
   * <p>this is the method for take image from the camera</p>
   */
  @SuppressLint("DefaultLocale")
  private void takePicFromCamera() {
    Utility.printLog("RegistrationAct Inside takePicFromCamera():");
    try {
      Log.d("camerapic", "takePicFromCamera: ");
      String takenNewImage = "";
      state = Environment.getExternalStorageState();
      takenNewImage = String.format("%s%d.png", PARENT_FOLDER, System.nanoTime());
      File newFile1;
      if (Environment.MEDIA_MOUNTED.equals(state)) {
        newFile1 = new File(Environment.getExternalStorageDirectory(), takenNewImage);
        newFile = newFile1;
      } else {
        newFile1 = new File(context.getFilesDir(), takenNewImage);
        newFile = newFile1;
      }
      Uri newProfileImageUri1;
      if (Build.VERSION.SDK_INT >= NOUGHT) {
        newProfileImageUri1 = FileProvider.getUriForFile(context,
            String.format("%s.provider", context.getApplicationContext().getPackageName()), newFile);
      } else {
        newProfileImageUri1 = Uri.fromFile(newFile);
      }
      newProfileImageUri = newProfileImageUri1;
      Utility.printLog("RegistrationAct FilePAth in takePicFromCamera()  new: "
          + newFile.getPath() + " new profileUri = " + newProfileImageUri);
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      intent.putExtra(MediaStore.EXTRA_OUTPUT, newProfileImageUri);
      intent.putExtra(RETURN_DATA, true);
      context.startActivityForResult(intent, CAMERA_PIC);
    } catch (ActivityNotFoundException e) {
      Utility.printLog("RegistrationAct cannot take picture: " + e);
    }
  }
}
