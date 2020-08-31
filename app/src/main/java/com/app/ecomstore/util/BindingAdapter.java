package com.app.ecomstore.util;

import android.view.View;
import android.widget.EditText;
import com.app.delivxstore.R;
import com.google.android.material.textfield.TextInputLayout;

public class BindingAdapter {
  @androidx.databinding.BindingAdapter(value = {"clearOnFocusAndDispatch",
      "hasFocus"}, requireAll = false)
  public static void setOnLayoutChangeListener(EditText view,
      final View.OnFocusChangeListener listener, final View.OnFocusChangeListener focusListener) {
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          if (listener != null) {
            listener.onFocusChange(v, hasFocus);
          }
        } else {
          if (focusListener != null) {
            focusListener.onFocusChange(v, hasFocus);
          }
        }
      }
    });
  }

  @androidx.databinding.BindingAdapter("android:btnEnabled")
  public static void setEnable(View view, Boolean value) {
    if (view != null) {
      view.setEnabled(value);
    }
  }

  @androidx.databinding.BindingAdapter("android:ErrorMessage")
  public static void serErrorMessage(TextInputLayout view, Boolean value) {
    if (view != null) {
      if (value) {
        view.setErrorEnabled(true);
        if (view.getId() == R.id.tiLoginEmail) {
          view.setError(view.getContext().getResources().getString(R.string.enterValidEmail));
        } else if (view.getId() == R.id.tiLoginPassword) {
          view.setError(view.getContext().getResources().getString(R.string.enterValidPassword));
        } else if (view.getId() == R.id.etTotalCharge) {
          view.setError(
              view.getContext().getResources().getString(R.string.enterTotalChargeToCustomer));
        } else if (view.getId() == R.id.etName) {
          view.setError(view.getContext().getResources().getString(R.string.enterName));
        } else if (view.getId() == R.id.etQty) {
          view.setError(view.getContext().getResources().getString(R.string.enterQty));
        } else if (view.getId() == R.id.etPricePerProduct) {
          view.setError(view.getContext().getResources().getString(R.string.enterPrice));
        }
      } else {
        if (view.getError() != null
            && !view.getError().toString().isEmpty()) {
          view.setErrorEnabled(false);
          view.setError(null);
        }
      }
    }
  }

  @androidx.databinding.BindingAdapter("android:visibility")
  public static void setVisibility(View view, Boolean value) {
    if (view != null) {
      view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
  }
}
