package com.app.delivxstore.main.wallet.payment;

import static com.app.delivxstore.utility.Utility.hideSoftKeyboard;
import static com.app.delivxstore.utility.VariableConstants.IS_ACCOUNT_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.STRIP_DATA;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityPaymentBinding;
import com.app.delivxstore.main.wallet.accountdetails.AccountListFragment;
import com.app.delivxstore.main.wallet.stripe.StripeAccountFragment;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.Objects;
import javax.inject.Inject;

/*shows screen for payment option and withdraw options*/
public class PaymentActivity extends DaggerAppCompatActivity
    implements PaymentContract.PaymentView, View.OnClickListener {

  @Inject
  PaymentContract.Presenter presenter;
  @Inject
  FontUtils fontUtils;
  private Fragment mFragment;
  private boolean mIsStripAdded = false;
  private ActivityPaymentBinding mBinding;
  private StripeData mStripeData;

  @Inject
  public PaymentActivity() {
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = ActivityPaymentBinding.inflate(LayoutInflater.from(this));
    setContentView(mBinding.getRoot());
    presenter.getAccounts();
    initialization();
  }


  /*initialize the views*/
  private void initialization() {
    mBinding.includeActionBar.tvTitle.setTypeface(fontUtils.getMontserratBold());
    mBinding.includeActionBar.tvTitle.setText(getString(R.string.chooseWithdrawls));
    mBinding.tvBankPayment.setOnClickListener(this);
    mBinding.tvPaypalPayment.setOnClickListener(this);
    mBinding.includeActionBar.ivBack.setOnClickListener(this);
    mBinding.btnConfirm.setOnClickListener(this);
  }

  @Override
  public void setUi(StripeData data) {
    mStripeData = data;
    mIsStripAdded = data != null;
  }

  @Override
  public void onResume() {
    super.onResume();
    if (IS_ACCOUNT_UPDATED) {
      IS_ACCOUNT_UPDATED = false;
      presenter.getAccounts();
    }
    hideSoftKeyboard(this);
  }

  @Override
  public void hideRechargeButton() {
    mBinding.btnConfirm.setVisibility(View.GONE);
  }

  @Override
  public void onError(String message) {
    Utility.toastMessage(this, message);
  }

  @Override
  public void showProgress() {
    mBinding.pbPayment.setVisibility(View.VISIBLE);
    Objects.requireNonNull(this).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbPayment.setVisibility(View.GONE);
    Objects.requireNonNull(this).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tvBankPayment:
        if (mIsStripAdded) {
          goForAccountDetails();
        } else {
          goToAddStripAccount();
        }
        break;
      case R.id.ivBack:
        onBackPressed();
        break;
    }
  }

  /*hides payment type ui*/
  private void hidePaymentUi() {
    mBinding.btnConfirm.setVisibility(View.GONE);
    mBinding.clWithDraw.setVisibility(View.GONE);
  }

  /*takes to add strip account screen*/
  private void goToAddStripAccount() {
    hidePaymentUi();
    mBinding.includeActionBar.tvTitle.setText(R.string.addStripAccount);
    mFragment = new StripeAccountFragment();
    if (!mFragment.isAdded()) {
      this.getSupportFragmentManager().beginTransaction()
          .replace(R.id.flContainer, mFragment).commit();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (mFragment != null) {
      mFragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  /*takes to accounts fragment*/
  private void goForAccountDetails() {
    hidePaymentUi();
    mBinding.includeActionBar.tvTitle.setText(R.string.bankDetails);
    mFragment = new AccountListFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(STRIP_DATA, mStripeData);
    mFragment.setArguments(bundle);
    if (!mFragment.isAdded()) {
      this.getSupportFragmentManager().beginTransaction()
          .replace(R.id.flContainer, mFragment).commit();
    }
  }
}
