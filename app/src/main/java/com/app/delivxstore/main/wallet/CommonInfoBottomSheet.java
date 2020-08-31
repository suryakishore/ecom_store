package com.app.delivxstore.main.wallet;

import static com.app.delivxstore.utility.VariableConstants.LOG_OUT;
import static com.app.delivxstore.utility.VariableConstants.RECHARGE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.Nullable;
import com.app.delivxstore.databinding.DialogTwoButtonAlertBinding;
import com.app.delivxstore.databinding.DialogWithDrawConfirmationBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import org.jetbrains.annotations.NotNull;

/*shows a bottom sheet dialog*/
public class CommonInfoBottomSheet extends BottomSheetDialogFragment {

  private DialogWithDrawConfirmationBinding mConfirmationBinding;
  private DialogTwoButtonAlertBinding mDialogBinding;
  private String mFrom;
  private String mMessage;
  private ButtonsCallBack mButtonCallBack;

  public CommonInfoBottomSheet(String from, String message) {
    mFrom = from;
    mMessage = message;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void setupDialog(@NotNull Dialog dialog, int style) {
    super.setupDialog(dialog, style);
    setView(dialog);
  }

  /*set required view to dialog*/
  private void setView(Dialog dialog) {
    View view = null;
    switch (mFrom) {
      case RECHARGE:
        mConfirmationBinding =
            DialogWithDrawConfirmationBinding.inflate(LayoutInflater.from(dialog.getContext()));
        view = mConfirmationBinding.getRoot();
        setConfirmationViews();
        break;
      case LOG_OUT:
        mDialogBinding =
            DialogTwoButtonAlertBinding.inflate(LayoutInflater.from(dialog.getContext()));
        view = mDialogBinding.getRoot();
        setLogoutView();
    }
    dialog.setContentView(view);
  }

  /*show logout dialg views*/
  private void setLogoutView() {
    mDialogBinding.tvAlertBody.setText(mMessage);
    mDialogBinding.tvAlertYes.setOnClickListener(view ->
        mButtonCallBack.onClickOfYes());
    mDialogBinding.tvAlertNo.setOnClickListener(view ->
        mButtonCallBack.onClickOfNo());
  }

  /*set views for recharge confirmation*/
  private void setConfirmationViews() {
    mConfirmationBinding.tvConfirmMessage.setText(mMessage);
    mConfirmationBinding.btnYes.setOnClickListener(view -> mButtonCallBack.onClickOfYes());
    mConfirmationBinding.btnNo.setOnClickListener(view -> mButtonCallBack.onClickOfNo());
  }

  @Nullable
  @Override
  public Dialog getDialog() {
    return super.getDialog();
  }


  public void setButtonCallBack(ButtonsCallBack rechargeCallBack) {
    mButtonCallBack = rechargeCallBack;
  }


  public interface ButtonsCallBack {
    void onClickOfYes();

    void onClickOfNo();
  }
}
