package com.app.delivxstore.main.wallet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.DialogTransactionBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/*opens bottom dialog for selecting transaction type*/
public class TransactionBottomSheet extends BottomSheetDialogFragment
    implements View.OnClickListener {
  private DialogTransactionBinding mBinding;
  private WalletContract.Presenter mPresenter;
  private Context mContext;
  private boolean mIsCreditSelected;
  private boolean mIsDebitSelected;

  TransactionBottomSheet(WalletContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void setupDialog(@NotNull Dialog dialog, int style) {
    super.setupDialog(dialog, style);
    mContext = dialog.getContext();
    mBinding = DialogTransactionBinding.inflate(LayoutInflater.from(dialog.getContext()));
    showCheckMark();
    dialog.setContentView(mBinding.getRoot());
    initViews();
  }

  /*show selected item tick mark*/
  private void showCheckMark() {
    if (mIsCreditSelected) {
      showDrawable(mBinding.tvCreditTrans);
    } else {
      if (mIsDebitSelected) {
        showDrawable(mBinding.tvDebitTrans);
      } else {
        showDrawable(mBinding.tvAllTrans);
      }
    }
  }

  @Override
  public void show(FragmentManager manager, String tag) {
    super.show(manager, tag);
  }

  /*set the typeface*/
  private void initViews() {
    mBinding.tvAllTrans.setOnClickListener(this);
    mBinding.tvCreditTrans.setOnClickListener(this);
    mBinding.tvDebitTrans.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    Objects.requireNonNull(this.getDialog()).dismiss();
    switch (view.getId()) {
      case R.id.tvAllTrans:
        mIsDebitSelected = false;
        mIsCreditSelected = false;
        mPresenter.setupAllTransAdapter();
        break;
      case R.id.tvDebitTrans:
        mIsDebitSelected = true;
        mPresenter.setupDebitTransAdapter();
        break;
      case R.id.tvCreditTrans:
        mIsCreditSelected = true;
        mPresenter.setupCreditTransAdapter();
        break;
    }
  }

  /*set drawable right to textView*/
  private void showDrawable(AppCompatTextView textView) {
    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_check_black, null);
    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
  }
}
