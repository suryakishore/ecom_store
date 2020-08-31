package com.app.delivxstore.main.wallet.stripe;

import static com.app.delivxstore.utility.Utility.toastMessage;
import static com.app.delivxstore.utility.VariableConstants.ASPECT_VALUE;
import static com.app.delivxstore.utility.VariableConstants.CROP_IMAGE;
import static com.app.delivxstore.utility.VariableConstants.EIGHTEEN;
import static com.app.delivxstore.utility.VariableConstants.MARSH_MALLOW;
import static com.app.delivxstore.utility.VariableConstants.REQUEST_CODE_PERMISSION_MULTIPLE;
import static com.app.delivxstore.utility.VariableConstants.STRIP_PIC;
import static com.app.delivxstore.utility.VariableConstants.STRIP_PIC_HEIGHT;
import static com.app.delivxstore.utility.VariableConstants.STRIP_PIC_WIDTH;
import static com.app.delivxstore.utility.VariableConstants.imageType;
import static com.app.delivxstore.utility.VariableConstants.newFile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentStripAccountBinding;
import com.app.delivxstore.main.wallet.accountdetails.AccountListFragment;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;
import com.app.delivxstore.utility.AppPermissionsRunTime;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.ImageEditUpload;
import com.app.delivxstore.utility.Scaler;
import com.app.delivxstore.utility.Utility;
import com.bumptech.glide.Glide;
import dagger.android.support.DaggerFragment;
import eu.janmuller.android.simplecropimage.CropImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.inject.Inject;

/*shows ui for Stripe Account*/
public class StripeAccountFragment extends DaggerFragment
    implements StripeAccountContract.StripView, View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

  @Inject
  FontUtils fontUtils;
  @Inject
  StripeAccountContract.StripPresenter presenter;
  private FragmentStripAccountBinding mBinding;
  private DatePickerDialog mDatePickerDialog;
  private long mMinDate;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mBinding = FragmentStripAccountBinding.inflate(inflater);
    presenter.attachView(this);
    presenter.getBundleData(getArguments());
    initViews();
    return mBinding.getRoot();
  }

  /*initialize views*/
  private void initViews() {
    TextView tvTitle = Objects.requireNonNull(getActivity()).findViewById(R.id.tvTitle);
    tvTitle.setTypeface(fontUtils.getMontserratBold());
    tvTitle.setText(R.string.addStripAccount);
    mBinding.etDob.setInputType(InputType.TYPE_NULL);
    mBinding.etDob.setOnClickListener(this);
    mBinding.ivAddFile.setOnClickListener(this);
    mBinding.btnSubmit.setOnClickListener(this);
    mBinding.ivClose.setOnClickListener(this);
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int date = calendar.get(Calendar.DAY_OF_MONTH);
    mDatePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year,
        month, date);
    mMinDate = mDatePickerDialog.getDatePicker().getMinDate();
  }


  @Override
  public void showProgress() {
    mBinding.pbStrip.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbStrip.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  /*check for camera and storage permission*/
  private void checkForPermission(String imageType1) {
    if (Build.VERSION.SDK_INT >= MARSH_MALLOW) {
      imageType = imageType1;
      ArrayList<AppPermissionsRunTime.MyPermissionConstants> permissionConstantList =
          new ArrayList<>();
      permissionConstantList.add(AppPermissionsRunTime.MyPermissionConstants.PERMISSION_CAMERA);
      permissionConstantList.add(AppPermissionsRunTime.MyPermissionConstants.PERMISSION_WRITE_EXTERNAL_STORAGE);
      permissionConstantList.add(AppPermissionsRunTime.MyPermissionConstants.PERMISSION_READ_EXTERNAL_STORAGE);
      if (AppPermissionsRunTime.checkPermission(getActivity(), permissionConstantList,
          REQUEST_CODE_PERMISSION_MULTIPLE)) {
        new ImageEditUpload(this.getActivity(), imageType1);
      }
    } else {
      new ImageEditUpload(this.getActivity(), imageType1);
    }
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    presenter.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CODE_PERMISSION_MULTIPLE) {
      new ImageEditUpload(getActivity(), imageType);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.etDob:
        openCalender();
        break;
      case R.id.ivAddFile:
        checkForPermission(STRIP_PIC);
        break;
      case R.id.btnSubmit:
        presenter.validateFields(mBinding.etName.getText().toString(),
            mBinding.etLName.getText().toString(), mBinding.etPersonalId.getText().toString(),
            mBinding.etAddress.getText().toString(), mBinding.etCity.getText().toString(),
            mBinding.etState.getText().toString(), mBinding.etPostalCode.getText().toString());
        break;
    }
  }

  /*opens the calendar to select date*/
  private void openCalender() {
    Calendar minAdultAge = new GregorianCalendar();
    minAdultAge.add(Calendar.YEAR, EIGHTEEN);
    mDatePickerDialog.getDatePicker().setMaxDate(minAdultAge.getTimeInMillis());
    mDatePickerDialog.getDatePicker().setMinDate(mMinDate);
    mDatePickerDialog.show();
  }

  @Override
  public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
    presenter.setDate(year, month, dayOfMonth);
  }

  @Override
  public void setDob(String date) {
    mBinding.etDob.setText(date);
  }

  @Override
  public void startCropImage() {
    Intent intent = new Intent(getActivity(), CropImage.class);
    intent.putExtra(CropImage.IMAGE_PATH, newFile.getPath());
    intent.putExtra(CropImage.SCALE, true);
    intent.putExtra(CropImage.ASPECT_X, ASPECT_VALUE);
    intent.putExtra(CropImage.ASPECT_Y, ASPECT_VALUE);
    startActivityForResult(intent, CROP_IMAGE);
  }

  @Override
  public void setStripPic(String imageUrl) {
    Uri uri = Uri.parse(imageUrl);
    double[] size = Scaler.getScalingFactor(Objects.requireNonNull(getActivity()));
    double height = (STRIP_PIC_HEIGHT) * size[1];
    double width = (STRIP_PIC_WIDTH) * size[0];
    if (imageUrl != null) {
      Glide.with(getActivity()).load(uri).override((int)width, (int)height).into(mBinding.ivAddFile);
    } else {
      toastMessage(getActivity(), getString(R.string.noImage));
    }
  }

  @Override
  public void getBack() {
    AccountListFragment accountListFragment = new AccountListFragment();
    if (!accountListFragment.isAdded()) {
      Objects.requireNonNull(getFragmentManager()).beginTransaction()
          .replace(R.id.flContainer, accountListFragment).commit();
    }
  }

  @Override
  public void setViews(StripeData stripeData) {
    mBinding.etName.setEnabled(false);
    mBinding.etLName.setEnabled(false);
    mBinding.etDob.setEnabled(false);
    mBinding.etName.setText(stripeData.getIndividual().getFirstName());
    mBinding.etLName.setText(stripeData.getIndividual().getLastName());
    mBinding.etDob.setText(Utility.sentingDateFormat(
        stripeData.getIndividual().getDob().getYear(),
        stripeData.getIndividual().getDob().getMonth(),
        stripeData.getIndividual().getDob().getDay()));
    mBinding.etAddress.setText(stripeData.getIndividual().getAddress().getLine1());
    mBinding.etCity.setText(stripeData.getIndividual().getAddress().getCity());
    mBinding.etState.setText(stripeData.getIndividual().getAddress().getState());
    mBinding.etPostalCode.setText(stripeData.getIndividual().getAddress().getPostalCode());
  }

  @Override
  public void error(String message) {
    Utility.toastMessage(getActivity(), message);
  }
}
