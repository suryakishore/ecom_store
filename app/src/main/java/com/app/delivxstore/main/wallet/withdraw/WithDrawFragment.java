package com.app.delivxstore.main.wallet.withdraw;

import static com.app.delivxstore.utility.VariableConstants.RECHARGE;
import static com.app.delivxstore.utility.VariableConstants.SHOW_TIME;
import static com.app.delivxstore.utility.VariableConstants.STRIP_PG;
import static com.app.delivxstore.utility.VariableConstants.ZERO;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentWithdrawBinding;
import com.app.delivxstore.main.wallet.CommonInfoBottomSheet;
import com.app.delivxstore.main.wallet.payment.PaymentActivity;
import com.app.delivxstore.main.wallet.stripe.model.response.AccountData;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.Objects;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/*show recharge ui*/
public class WithDrawFragment extends DaggerFragment implements View.OnClickListener,
    WithDrawContract.WithDrawView {

  @Inject
  WithDrawContract.Presenter withDrawPresenter;
  @Inject
  FontUtils fontUtils;
  private FragmentWithdrawBinding mBinding;
  private CommonInfoBottomSheet mCommonInfoBottomSheet;
  private String mToastMessage;
  private String mConfirmMessage;
  private AccountData mAccountData;

  @Inject
  public WithDrawFragment() {
  }

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mBinding = FragmentWithdrawBinding.inflate(inflater);
    withDrawPresenter.attachView(this);
    withDrawPresenter.getBundleData(getArguments());
    initViews();
    return mBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
  }

  /*initialize view*/
  private void initViews() {
    mBinding.tvChangeCard.setOnClickListener(this);
    mBinding.etAmount.requestFocus();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnConfirm:
        if (mBinding.etAmount.getText().toString().length() > ZERO) {
          showRechargeAlert();
        } else {
          Utility.toastMessage(getActivity(), mToastMessage);
        }
        break;
      case R.id.tvChangeCard:
        goBack();
        break;
    }
  }

  /*go back and change card or account*/
  private void goBack() {
    Objects.requireNonNull(getFragmentManager()).popBackStackImmediate();
  }

  /*show recharge alert*/
  private void showRechargeAlert() {
    mCommonInfoBottomSheet = new CommonInfoBottomSheet( RECHARGE, mConfirmMessage);
    mCommonInfoBottomSheet.setButtonCallBack(new CommonInfoBottomSheet.ButtonsCallBack() {
      @Override
      public void onClickOfYes() {
        String amount = Objects.requireNonNull(mBinding.etAmount.getText()).toString();
        withDrawPresenter.withDrawApi(mAccountData.getId(), amount, STRIP_PG);
        mCommonInfoBottomSheet.dismiss();
        mBinding.etAmount.clearFocus();
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
  public void setWithDrawUi(AccountData accountData, String currency, String cause) {
    mAccountData = accountData;
    AppCompatTextView mTitle = getActivity().findViewById(R.id.tvTitle);
    mTitle.setText(getResources().getString(R.string.withDrawWallet));
    mBinding.etAmount.requestFocus();
    mToastMessage = getString(R.string.enterSomeAmount);
    mConfirmMessage = getString(R.string.rechargeConfirmation1);
    mBinding.tvWithDrawAmount.setText(getResources().getString(R.string.withDrawAmount));
    mBinding.tvAccountNumber.setText(accountData.getAccount());
    mBinding.tvBankCode.setText(accountData.getBankName());
    mBinding.tvChangeCurrency.setText(currency);
  }

  @Override
  public void showProgress() {
    mBinding.pbRecharge.setVisibility(View.VISIBLE);
    mBinding.tvRechargeProcess.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbRecharge.setVisibility(View.GONE);
    mBinding.tvRechargeProcess.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow()
        .clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void showError(String message) {
    Utility.toastMessage(getActivity(), message);
  }

  @Override
  public void showSuccess(String amount, String message) {
    ((PaymentActivity)Objects.requireNonNull(getActivity())).hideRechargeButton();
    mBinding.tvAmount.setText(amount);
    mBinding.clRecharge.setVisibility(View.GONE);
    mBinding.clSuccess.setVisibility(View.VISIBLE);
    mBinding.tvSuccessMessage.setText(message);
    ((Animatable)mBinding.ivTickMark.getDrawable()).start();
    new Handler().postDelayed(() ->
        Objects.requireNonNull(getActivity()).onBackPressed(), SHOW_TIME);
  }
}