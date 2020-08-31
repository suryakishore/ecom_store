package com.app.delivxstore.main.wallet.bankaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentBankAccountBinding;
import com.app.delivxstore.main.wallet.accountdetails.AccountListFragment;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.Objects;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * shows screen for add bank account
 */
public class BankAccountFragment extends DaggerFragment
    implements BankAccountContract.BankAccountView, View.OnClickListener {

  @Inject
  BankAccountContract.Presenter presenter;
  @Inject
  FontUtils fontUtils;
  private FragmentBankAccountBinding mBinding;

  @Override
  public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    mBinding = FragmentBankAccountBinding.inflate(inflater);
    presenter.attachView(this);
    initializeViews();
    return mBinding.getRoot();
  }

  /*initialize views*/
  private void initializeViews() {
    TextView tvTitle = Objects.requireNonNull(getActivity()).findViewById(R.id.tvTitle);
    tvTitle.setTypeface(fontUtils.getMontserratBold());
    tvTitle.setText(R.string.addBank);
    mBinding.btnSave.setOnClickListener(this);
  }

  @Override
  public void showError(String message) {
    Utility.toastMessage(getContext(), message);
  }

  @Override
  public void showProgress() {
    mBinding.pbBankAccount.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbBankAccount.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void onClick(View view) {
    presenter.validateAllFields(mBinding.etBankCode.getText().toString(),
        mBinding.etAccountNo.getText().toString(),
        mBinding.etConfirmAccountNo.getText().toString());
  }

  @Override
  public void goBack() {
    AccountListFragment accountListFragment = new AccountListFragment();
    if (!accountListFragment.isAdded()) {
      Objects.requireNonNull(getFragmentManager()).beginTransaction().addToBackStack(null)
          .replace(R.id.flContainer, accountListFragment).commit();
    }
  }
}
