package com.app.ecomstore.uiutil.dialog;



import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.TWO;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.app.delivxstore.R;
import java.util.Objects;

/*
 * Purpose – This class holds different types of reusable Dialogs
 * @author 3Embed
 * Created on Nov 25, 2019
 * Modified on
 */
public class CustomDialogUtil {



  public interface DialogOutOfStockNotifyListener {
    void onNotifyMail(String mail);
  }

  public interface DialogCallBackListener {
    void onLogOutClickListener();
  }

  public interface SimpleAlertDialogClickHandler {
    /**
     * This method is using as ok button click callback
     */
    void onOkClickListener(int type);
  }
}
