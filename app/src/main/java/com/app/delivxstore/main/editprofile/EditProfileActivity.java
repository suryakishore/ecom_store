package com.app.delivxstore.main.editprofile;


import static com.app.delivxstore.utility.VariableConstants.SHOW_TIME;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityEditProfileBinding;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

/*show ui for edit profile section and pass user request to presenter*/
public class EditProfileActivity extends DaggerAppCompatActivity implements View.OnClickListener,
    EditProfileContract.EditProfileView, TextWatcher {

  @Inject
  EditProfileContract.Presenter presenter;
  private ActivityEditProfileBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityEditProfileBinding.inflate(getLayoutInflater());
    Utility.RtlConversion(this, presenter.getLanguageCode());
    setContentView(mBinding.getRoot());
    overridePendingTransition(R.anim.bottem_slide_down, R.anim.stay_activity);
    initializeViews();
  }

  /*initialize and set type face to views*/
  private void initializeViews() {
    mBinding.includeActionBar.tvTitle.setText(getString(R.string.changePassword));
    mBinding.includeActionBar.ivBack.setOnClickListener(this);
    mBinding.btnConfirm.setOnClickListener(this);
    mBinding.etConfirmNewPassword.addTextChangedListener(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onBackPress() {
    onBackPressed();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.bottem_slide_down, R.anim.top_to_bottom);
  }

  @Override
  public void onError(String message) {
    Utility.toastMessage(this, message);
  }

  @Override
  public void showProgress() {
    mBinding.pbEditProfile.setVisibility(View.VISIBLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbEditProfile.setVisibility(View.GONE);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.ivBack:
        onBackPressed();
        break;
      case R.id.btnConfirm:
        presenter.updatePassword(mBinding.etNewPassword.getText().toString(),
            mBinding.etConfirmNewPassword.getText().toString());
    }
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
  }

  @Override
  public void afterTextChanged(Editable editable) {
    if (editable.toString().length() > 0
        && !mBinding.etCurrentPassword.getText().toString().isEmpty()
        && !mBinding.etNewPassword.getText().toString().isEmpty()) {
      enableButton();
    } else {
      disableButton();
    }
  }

  /*disable button*/
  public void disableButton() {
    mBinding.btnConfirm.setEnabled(false);
  }

  @Override
  public void onSuccess() {
    mBinding.groupViews.setVisibility(View.GONE);
    mBinding.groupSuccess.setVisibility(View.VISIBLE);
    ((Animatable)mBinding.ivTickMark.getDrawable()).start();
    new Handler().postDelayed(this::onBackPressed, SHOW_TIME);
  }

  /*enable confirm button*/
  public void enableButton() {
    mBinding.btnConfirm.setEnabled(true);
  }
}
