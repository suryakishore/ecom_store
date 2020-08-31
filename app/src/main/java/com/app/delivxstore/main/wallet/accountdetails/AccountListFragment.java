package com.app.delivxstore.main.wallet.accountdetails;


import static com.app.delivxstore.utility.VariableConstants.ACCOUNT_DATA;
import static com.app.delivxstore.utility.VariableConstants.CAUSE;
import static com.app.delivxstore.utility.VariableConstants.FOR;
import static com.app.delivxstore.utility.VariableConstants.IS_ACCOUNT_UPDATED;
import static com.app.delivxstore.utility.VariableConstants.PATCH;
import static com.app.delivxstore.utility.VariableConstants.STRIP_DATA;
import static com.app.delivxstore.utility.VariableConstants.WITH_DRAW;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentAccountListBinding;
import com.app.delivxstore.main.wallet.adapter.AccountListAdapter;
import com.app.delivxstore.main.wallet.bankaccount.BankAccountFragment;
import com.app.delivxstore.main.wallet.stripe.StripeAccountFragment;
import com.app.delivxstore.main.wallet.stripe.model.response.AccountData;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;
import com.app.delivxstore.main.wallet.withdraw.WithDrawFragment;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Inject;

/*shows UI for added bank account and strip account*/
public class AccountListFragment extends DaggerFragment
    implements AccountListContract.BankDetailsView, View.OnClickListener {

  @Inject
  AccountListContract.Presenter presenter;
  @Inject
  FontUtils fontUtils;
  private FragmentAccountListBinding mBinding;
  private Dialog mDialog;
  private ArrayList<AccountData> mAccountDataList;
  private String mAccountId;
  private StripeData mStripeData;
  private StripeAccountFragment mStripeAccountFragment;
  private AppCompatButton mButtonNext;
  private AccountData mAccountData;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mBinding = FragmentAccountListBinding.inflate(inflater);
    presenter.attachView(this);
    initializeViews();
    presenter.getBundleData(getArguments());
    return mBinding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (IS_ACCOUNT_UPDATED) {
      IS_ACCOUNT_UPDATED = false;
      presenter.getAccounts();
    }
  }

  /*initialize and set type face to views*/
  private void initializeViews() {
    TextView tvTitle = Objects.requireNonNull(getActivity()).findViewById(R.id.tvTitle);
    tvTitle.setText(R.string.bankDetails);
    mButtonNext = getActivity().findViewById(R.id.btnConfirm);
    mButtonNext.setText(getString(R.string.next));
    mBinding.btnDelete.setOnClickListener(this);
    mBinding.clStrip.setOnClickListener(this);
    mButtonNext.setOnClickListener(this);
    mBinding.tvAddAccount.setOnClickListener(this);
  }

  @Override
  public void showError(String message) {
    Utility.toastMessage(getContext(), message);
  }

  @Override
  public void showProgress() {
    mBinding.pbBankDetails.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbBankDetails.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.clStrip:
        updateStripeDetails();
        break;
      case R.id.btnDelete:
        showConfirmation();
        break;
      case R.id.tvAddAccount:
        gotoAddBankAccount();
        break;
      case R.id.btnConfirm:
        if (mAccountData == null) {
          Utility.toastMessage(getActivity(), getString(R.string.selectAccount));
        } else {
          goToWithDraw();
        }
        break;
    }
  }

  /*go for withdraw amount*/
  private void goToWithDraw() {
    WithDrawFragment withDrawFragment = new WithDrawFragment();
    Bundle bundle = new Bundle();
    mButtonNext.setText(R.string.withDraw);
    mButtonNext.setOnClickListener(withDrawFragment);
    bundle.putString(FOR, WITH_DRAW);
    bundle.putParcelable(ACCOUNT_DATA, mAccountData);
    withDrawFragment.setArguments(bundle);
    if (!withDrawFragment.isAdded()) {
      Objects.requireNonNull(getFragmentManager()).beginTransaction()
          .replace(R.id.flContainer, withDrawFragment).commit();
    }
  }

  /*go to add bank account*/
  private void gotoAddBankAccount() {
    BankAccountFragment bankAccountFragment = new BankAccountFragment();
    if (!bankAccountFragment.isAdded()) {
      Objects.requireNonNull(getFragmentManager()).beginTransaction()
          .replace(R.id.flContainer, bankAccountFragment).commit();
    }
  }

  /*takes StripAccount fragment for updating strip details*/
  private void updateStripeDetails() {
    mStripeAccountFragment = new StripeAccountFragment();
    if (!mStripeAccountFragment.isAdded()) {
      Bundle bundle = new Bundle();
      bundle.putParcelable(STRIP_DATA, mStripeData);
      bundle.putString(CAUSE, PATCH);
      mStripeAccountFragment.setArguments(bundle);
      Objects.requireNonNull(getFragmentManager()).beginTransaction().replace(R.id.flContainer,
          mStripeAccountFragment).commit();
    }
  }

  /*show confirmation alerts */
  private void showConfirmation() {
    mDialog = Utility.userPromptWithTwoButtons(getActivity(),
        getString(R.string.deleteAccountConfirm), fontUtils);
    TextView tvAlertYes = mDialog.findViewById(R.id.tvAlertYes);
    TextView tvAlertNo = mDialog.findViewById(R.id.tvAlertNo);
    tvAlertYes.setOnClickListener(view -> {
      presenter.deleteAccount(mAccountId);
      mDialog.dismiss();
    });
    tvAlertNo.setOnClickListener(view -> mDialog.dismiss());
    mDialog.show();
  }

  @Override
  public void goBack() {
    onResume();
    mBinding.clAccountViews.setVisibility(View.VISIBLE);
    mBinding.clAccountDetails.setVisibility(View.GONE);
    mBinding.btnDelete.setVisibility(View.GONE);
  }

  @Override
  public void initializeRecyclerView() {
    AccountListAdapter mAdapter = new AccountListAdapter(this, mAccountDataList);
    mBinding.rvBankAccounts.setLayoutManager(new LinearLayoutManager(getActivity()));
    mBinding.rvBankAccounts.setHasFixedSize(true);
    mBinding.rvBankAccounts.setAdapter(mAdapter);
    if (mAccountDataList.size() > 0) {
      mButtonNext.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void setViews(StripeData stripeData) {
    mStripeData = stripeData;
    if (stripeData != null) {
      mAccountDataList = stripeData.getExternalAccounts().getData();
      if (mAccountDataList.size() > 0) {
        mBinding.rlNoBankAccounts.setVisibility(View.GONE);
      }
      String status = stripeData.getIndividual().getVerification().getStatus();
      mBinding.tvUnverified.setText(status);
      mBinding.tvUnverified.setTextColor(getResources().getColor(R.color.verified));
      Drawable drawable = status.equals(getString(R.string.verified))
          ? Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.verified,
          null)
          :
          Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.unverified,
              null);
      mBinding.tvUnverified.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
      mBinding.tvAddAccount.setVisibility(status.equals(getString(R.string.verified))
          ? View.VISIBLE : View.GONE);
      mBinding.ivRightArrow.setVisibility(status.equals(getString(R.string.verified))
          ? View.GONE : View.VISIBLE);
      initializeRecyclerView();
    }
  }

  @Override
  public void getSelectedAccountDetails(AccountData accountData) {
    mAccountData = accountData;
  }

  /*show details for account*/
  public void showDetails(ArrayList<AccountData> accountDataArrayList) {
    mAccountDataList = accountDataArrayList;
    AccountData accountData = null;
    for (AccountData accountData1 : accountDataArrayList) {
      if (accountData1.isSelected()) {
        accountData = accountData1;
      }
    }
    mAccountId = accountData != null ? accountData.getId() : null;
    mBinding.clAccountViews.setVisibility(View.GONE);
    mBinding.clAccountDetails.setVisibility(View.VISIBLE);
    mBinding.btnDelete.setVisibility(View.VISIBLE);
    mBinding.tvBankCode.setText(accountData != null ? accountData.getBankName() : "");
    mBinding.tvAccount.setText(accountData != null ? accountData.getAccount() : "");
    mBinding.tvConfirmAccount.setText(accountData != null ? accountData.getAccount() : "");
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    if (mStripeAccountFragment != null) {
      mStripeAccountFragment.onActivityResult(requestCode, resultCode, data);
    }
  }
}
