package com.app.delivxstore.main.laundryitemPhotos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.VariableConstants;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.app.delivxstore.utility.VariableConstants.REQUEST_CODE_PERMISSION_MULTIPLE;

public class LaundryItemPhotosActivity extends DaggerAppCompatActivity implements LaundryItemPhotosContract.View {

    private ArrayList<LaundryItemPhotos> arrayList = new ArrayList<>();
    private LaundryItemPhotosAdapter laundryItemPhotosAdapter;
    private GridLayoutManager layoutManager;
    private String state;
    private File newFile;
    private Uri newProfileImageUri;
    private boolean notUploaded;
    @BindView(R.id.rvlaundryItems)
    RecyclerView rvlaundryItems;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvSave)
    TextView tvSave;

    ArrayList<String> images = new ArrayList<>();

    @Inject
    LaundryItemPhotosContract.Presenter mPresenter;
    private int uploadIndex, finalIndex;
    private String orderId, productId;
    private boolean isFromDetails;
    private JSONArray jsonArray;
   // private boolean closeAct=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_item_photos);

        init();
        getIntentData();

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            orderId = bundle.getString("orderId");
            productId = bundle.getString("productId");
            finalIndex = bundle.getInt("finalIndex", 0);
            if ((ArrayList<String>) bundle.getStringArrayList("images") != null)

                images.addAll((ArrayList<String>) bundle.getStringArrayList("images"));

            Utility.printLog("uploadData" + "images" + images);

            if (images.size() > 0) {

                for (int i = 0; i < images.size(); i++) {

                    LaundryItemPhotos laundryItemPhotos = new LaundryItemPhotos();
                    laundryItemPhotos.setItemPhoto(images.get(i));
                    laundryItemPhotos.setUploaded(true);
                    if (isFromDetails) {
                        arrayList.add(arrayList.size() - 1, laundryItemPhotos);
                    } else {
                        laundryItemPhotos.setDel(true);
                        arrayList.add(arrayList.size(), laundryItemPhotos);
                    }
                }

                laundryItemPhotosAdapter.notifyDataSetChanged();

            }

        }

        if (!isFromDetails) {
            tvSave.setVisibility(View.GONE);
        }

    }

    public void init() {

        ButterKnife.bind(this);
        isFromDetails = getIntent().getBooleanExtra("isFromDetails", false);
        if (Utility.isTablet(this)) {
            layoutManager = new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }

        else {
            layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }

        laundryItemPhotosAdapter = new LaundryItemPhotosAdapter(this, this, arrayList);
        rvlaundryItems.setLayoutManager(layoutManager);

        rvlaundryItems.setAdapter(laundryItemPhotosAdapter);

        if (isFromDetails) {
            LaundryItemPhotos laundryItemPhotos = new LaundryItemPhotos();
            laundryItemPhotos.setCamera(true);
            arrayList.add(laundryItemPhotos);
        }

        laundryItemPhotosAdapter.notifyDataSetChanged();
        clearOrCreateDir();

    }


    @OnClick({R.id.tvSave, R.id.ivBack})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tvSave:

              /*  if (arrayList.size() == 1) {
                    onError(getResources().getString(R.string.addImages));
                    return;
                }*/

                images.clear();
                jsonArray = new JSONArray();
                notUploaded = false;
           //     closeAct=true;
                for (int i = 0; i < arrayList.size() - 1; i++) {

                    LaundryItemPhotos laundryItemPhotos = arrayList.get(i);

                    if (!laundryItemPhotos.isUploaded()) {
                        uploadIndex = i;
                        notUploaded = true;
                        Utility.printLog("in amazon file" + laundryItemPhotos.getFile());
                        mPresenter.uploadToS3(orderId, uploadIndex, laundryItemPhotos.getFile());
                        break;
                    } else {
                        jsonArray.put(laundryItemPhotos.getItemPhoto());
                        images.add(laundryItemPhotos.getItemPhoto());
                    }
                }

                if (!notUploaded) {
                    mPresenter.uploadData(Double.parseDouble(orderId), productId, jsonArray);
                }
                break;

            case R.id.ivBack:
                mPresenter.closeAct();
                break;

        }

    }


    private void mSetPicPath() {
        String takenNewImage;
        state = Environment.getExternalStorageState();
        takenNewImage = "image" + String.valueOf(System.nanoTime()) + ".png";

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            newFile = new File(Environment.getExternalStorageDirectory() + "/" + VariableConstants.PARENT_FOLDER + "/Images/", takenNewImage);
           /* try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            newProfileImageUri = FileProvider.getUriForFile(
                    Objects.requireNonNull(this),
                    this
                            .getPackageName() + ".provider", newFile);
        } else {

            newFile = new File(Objects.requireNonNull(this).getFilesDir() + "/" + VariableConstants.PARENT_FOLDER + "/Images/", takenNewImage);

            newProfileImageUri = FileProvider.getUriForFile(
                    this,
                    this
                            .getPackageName() + ".provider", newFile);

        }

    }

    private void mSelectImagePopUp() {

        //clearOrCreateDir();
        final CharSequence[] options = {getString(R.string.TakePhoto), getString(R.string.ChoosefromGallery), getString(R.string.cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.AddPhoto));
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals(getString(R.string.TakePhoto))) {
                takePicFromCamera();

            } else if (options[item].equals(getString(R.string.ChoosefromGallery))) {
                try {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, VariableConstants.GALLERY_PIC);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (options[item].equals(getString(R.string.cancel))) {
                dialog.dismiss();
            }

        });
        builder.show();

    }


    private void clearOrCreateDir() {
        try {
            state = Environment.getExternalStorageState();
            File cropImagesDir;
            File[] cropImagesDirectory;
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                cropImagesDir = new File(Environment.getExternalStorageDirectory() + "/" + VariableConstants.PARENT_FOLDER + "/Images/");
            } else {
                cropImagesDir = new File(Objects.requireNonNull(this).getFilesDir() + "/" + VariableConstants.PARENT_FOLDER + "/Images/");
            }
            if (!cropImagesDir.isDirectory()) {
                cropImagesDir.mkdirs();
            } else {
                cropImagesDirectory = cropImagesDir.listFiles();
                if (cropImagesDirectory.length > 0) {
                    for (File aCropImagesDirectory : cropImagesDirectory) {
                        aCropImagesDirectory.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void takePicFromCamera() {
        mSetPicPath();
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, newProfileImageUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, VariableConstants.CAMERA_PIC);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void openImageSelectDialog() {
        if (Utility.isNetworkConnected(this))
            mCheckPerMission();
        else
            onError(getResources().getString(R.string.networkError));
    }

    @Override
    public void finishAct() {

            Intent intent = new Intent();
            intent.putExtra("finalIndex", finalIndex);
            intent.putStringArrayListExtra("images", images);
            setResult(Activity.RESULT_OK, intent);
            finish();

    }

    @Override
    public void setLaundryImg(int pos, String laundryImg) {

        LaundryItemPhotos laundryItemPhotos = arrayList.get(pos);

        laundryItemPhotos.setUploaded(true);

        laundryItemPhotos.setItemPhoto(laundryImg);
        jsonArray.put(laundryItemPhotos.getItemPhoto());
        images.add(laundryItemPhotos.getItemPhoto());


        uploadIndex = pos + 1;

        if (uploadIndex != arrayList.size() - 1) {
            LaundryItemPhotos laundryItemAfter = arrayList.get(uploadIndex);

            mPresenter.uploadToS3(orderId, uploadIndex, laundryItemAfter.getFile());

        } else {

            mPresenter.uploadData(Double.parseDouble(orderId), productId, jsonArray);

            // hideProgress();
        }

    }

    @Override
    public void onError(String msg) {

     //   Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
           Utility.showAlert(msg,this);
    }

    @Override
    public void showAlertDialog(int position) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertDialog.setMessage(getString(R.string.sureForDelete));
        alertDialog.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }

                });

        alertDialog.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (Utility.isNetworkConnected(LaundryItemPhotosActivity.this)) {

                            LaundryItemPhotos laundryItemPhotos = arrayList.get(position);

                            if (laundryItemPhotos.getItemPhoto() != null) {

                                String itemPhoto = laundryItemPhotos.getItemPhoto();
                                String[] separated = itemPhoto.split("https://s3.eu-central-1.amazonaws.com/delivx/");
                                //  Log.d("exe", "separated0" + separated[0] +"separated1" + separated[1]+ "le" + separated.length);
                                mPresenter.deleteItem(separated[1]);

                                arrayList.remove(position);
                                laundryItemPhotosAdapter.notifyDataSetChanged();
                                images.clear();
                                jsonArray = new JSONArray();
                                notUploaded = false;
                             //   closeAct=false;
                                for (int i = 0; i < arrayList.size() - 1; i++) {

                                    LaundryItemPhotos laundryItemPhotosUpd = arrayList.get(i);

                                    if (!laundryItemPhotosUpd.isUploaded()) {
                                        uploadIndex = i;
                                        notUploaded = true;
                                        Utility.printLog("in amazon file" + laundryItemPhotosUpd.getFile());
                                        mPresenter.uploadToS3(orderId, uploadIndex, laundryItemPhotosUpd.getFile());
                                        break;
                                    } else {
                                        images.add(laundryItemPhotosUpd.getItemPhoto());
                                        jsonArray.put(laundryItemPhotosUpd.getItemPhoto());
                                    }
                                }

                                if (!notUploaded) {
                                    mPresenter.uploadData(Double.parseDouble(orderId), productId, jsonArray);
                                }
                            }


                        } else {
                            onError(getResources().getString(R.string.networkError));
                        }

                    }

                });
        alertDialog.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utility.printLog("got result");
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case VariableConstants.CAMERA_PIC:

                    // startCropImage();

                  /*  newProfileImageUri = FileProvider.getUriForFile(
                            this,
                            this
                                    .getPackageName() + ".provider", newFile);*/
                    Log.d("exe", "CAMERA_PIC" + newFile);
                    LaundryItemPhotos laundryItemPhotos = new LaundryItemPhotos();
                    laundryItemPhotos.setFile(newFile);
                    // laundryItemPhotos.setFile(fileArrayList.get(fileArrayList.size()-1));

                    arrayList.add(arrayList.size() - 1, laundryItemPhotos);

                    laundryItemPhotosAdapter.notifyItemChanged(arrayList.size() - 1);
                    layoutManager.scrollToPosition(arrayList.size());

                    break;

                case VariableConstants.GALLERY_PIC:
                    mSetPicPath();
                    InputStream inputStream;
                    try {
                        inputStream = Objects.requireNonNull(this).getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                        assert inputStream != null;
                        try {
                            Utility.copyStream(inputStream, fileOutputStream);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileOutputStream.close();
                        inputStream.close();
                        /*newProfileImageUri = FileProvider.getUriForFile(
                                this,
                                this
                                        .getPackageName() + ".provider", newFile);*/
                        Log.d("exe", "  " + newProfileImageUri);
                        LaundryItemPhotos laundryItemPhotosGal = new LaundryItemPhotos();
                        //   laundryItemPhotosGal.setFileOrUrl(true);
                        laundryItemPhotosGal.setFile(newFile);

                        //   laundryItemPhotosGal.setFile(fileArrayList.get(fileArrayList.size()-1));

                        arrayList.add(arrayList.size() - 1, laundryItemPhotosGal);

                        laundryItemPhotosAdapter.notifyItemChanged(arrayList.size() - 1);
                        layoutManager.scrollToPosition(arrayList.size());
                        //startCropImage();
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                        Log.d("exe", "ex" + e.getMessage());
                    }
                    break;


                default:
                    break;
            }

        }
    }


    private void mCheckPerMission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ((checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) || (checkSelfPermission(READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (checkSelfPermission(WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))) {
            requestPermissions(new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_MULTIPLE);
        } else {
            mSelectImagePopUp();
        }

    }

    /**
     * predefined method to check run time permissions list
     *
     * @param requestCode
     * @param permissions:  contains the list of requested permissions
     * @param grantResults: contains granted and un granted permissions result list
     */

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_MULTIPLE:
                boolean isDeninedRTPs = false, showRationaleRTPs = false;
                if (grantResults.length > 0) {

                    for (int i = 0; i < permissions.length; i++) {
                        isDeninedRTPs = grantResults[i] == PackageManager.PERMISSION_DENIED;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            showRationaleRTPs = shouldShowRequestPermissionRationale(permissions[i]);
                        }

                    }
                    if (isDeninedRTPs) {
                        if (!showRationaleRTPs) {
                            //goToSettings();
                          /*  showMessageOKCancel("You need to allow access permissions,Otherwise you can't" + " continue.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                                intent.setData(uri);
                                                startActivity(intent);
                                            }
                                        }
                                    });
*/
                        } else {

                            isDeninedRTPs = false;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                ActivityCompat.requestPermissions(this, new
                                        String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_MULTIPLE);
                            }
                        }


                    } else {
                        mSelectImagePopUp();
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearOrCreateDir();
    }
}
