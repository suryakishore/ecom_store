package com.app.delivxstore.main.profile;

import static com.app.delivxstore.utility.VariableConstants.IS_PASSWORD_CHANGED;
import static com.app.delivxstore.utility.VariableConstants.LOG_OUT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentProfileBinding;
import com.app.delivxstore.main.editprofile.EditProfileActivity;
import com.app.delivxstore.main.wallet.CommonInfoBottomSheet;
import com.app.delivxstore.main.wallet.withdraw.WithDrawFragment;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.boarding.login.EcomLoginActivity;
import com.app.ecomstore.boarding.login.model.LoginData;
import dagger.android.support.DaggerFragment;
import java.util.Objects;
import javax.inject.Inject;

/*show profile details screen */
public class ProfileFragment extends DaggerFragment
    implements ProfileContract.ViewOperations, View.OnClickListener {
  @Inject
  ProfileContract.PresenterOperations presenter;
  private CommonInfoBottomSheet mCommonInfoBottomSheet;
  private FragmentProfileBinding mBinding;

  @Inject
  public ProfileFragment() {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = FragmentProfileBinding.inflate(inflater);
    presenter.attachView(this);
    presenter.getProfileData();
    initViews();
    return mBinding.getRoot();
  }

  /*initialize view*/
  private void initViews() {
    mBinding.tvLogout.setOnClickListener(this);
    mBinding.tvPassword.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tvPassword:
        startActivity(new Intent(getActivity(), EditProfileActivity.class));
        break;
      case R.id.tvLogout:
        showLogOutDialog();
        break;
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (IS_PASSWORD_CHANGED) {
      IS_PASSWORD_CHANGED = false;
      presenter.updatePassword();
    }
  }

  /*show dialog for logout*/
  private void showLogOutDialog() {
    mCommonInfoBottomSheet = new CommonInfoBottomSheet(LOG_OUT, getString(R.string.logoutMessage));
    mCommonInfoBottomSheet.setButtonCallBack(new CommonInfoBottomSheet.ButtonsCallBack() {
      @Override
      public void onClickOfYes() {
        presenter.logoutOnClick();
        mCommonInfoBottomSheet.dismiss();
      }

      @Override
      public void onClickOfNo() {
        mCommonInfoBottomSheet.dismiss();
      }
    });
    mCommonInfoBottomSheet.show(Objects.requireNonNull(getFragmentManager()),
        WithDrawFragment.class.getSimpleName());
  }

  @Override
  public void setValues(LoginData data) {
    mBinding.tvName.setText(data.getName());
    mBinding.tvCountryCode.setText(data.getCountryCode());
    mBinding.tvPhone.setText(data.getMobile());
    mBinding.tvStoreName.setText(data.getStoreName().getEn());
    mBinding.tvRole.setText(data.getRole());
    mBinding.tvStoreLocation.setText(data.getStoreData().getBusinessLocationAddress().getCity());
    mBinding.tvEmail.setText(data.getEmail());
    mBinding.tvPassword.setText(data.getOriginalPassword());
  }

  @Override
  public void showError(String message) {
    Utility.toastMessage(getActivity(), message);
  }

  @Override
  public void logout() {
    Intent intent = new Intent(getActivity(), EcomLoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    Objects.requireNonNull(getActivity()).finish();
  }

  @Override
  public void setPassword(String password) {
    mBinding.tvPassword.setText(password);
  }

  private void activeOtherViews(boolean toActive) {
    if (toActive) {
      Objects.requireNonNull(getActivity()).getWindow().clearFlags(
          WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    } else {
      Objects.requireNonNull(getActivity()).getWindow().setFlags(
          WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
          WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
  }

  @Override
  public void showProgress() {
    mBinding.progressBar.setVisibility(View.VISIBLE);
    activeOtherViews(false);
  }

  @Override
  public void hideProgress() {
    mBinding.progressBar.setVisibility(View.GONE);
    activeOtherViews(true);
  }
}
