package com.app.delivxstore.main.home.tabView.orders.orderDetails;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.delivxstore.R;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.utility.Utility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
//import android.icu.util.Calendar;

public class CancellationDialog implements View.OnClickListener {
  @BindView(R.id.ivCloseButton)
  ImageView ivCloseButton;
  @BindView(R.id.ivCorrectIcon)
  ImageView ivCorrectIcon;
  @BindView(R.id.rvReasons)
  RecyclerView rvReasons;
  @BindView(R.id.tvOrderDueAt)
  TextView tvOrderDueAt;
  @BindView(R.id.tvCurrentDelay)
  TextView tvCurrentDelay;
  @BindView(R.id.tvDelayBy1)
  TextView tvDelayBy1;
  @BindView(R.id.tvDelayBy2)
  TextView tvDelayBy2;
  @BindView(R.id.tvDelayBy3)
  TextView tvDelayBy3;
  @BindView(R.id.tvDelayBy4)
  TextView tvDelayBy4;
  @BindView(R.id.etReason)
  EditText etReason;
  @BindView(R.id.llDelayBy)
  LinearLayout llDelayBy;
  @BindView(R.id.tv_title)
  TextView tv_title;
  @BindString(R.string.dealy_order)
  String dealy_order;
  @BindString(R.string.cancelOrder)
  String cancelOrder;
  private Context context;
  private DialogOrderDetailsContract.PresenterOperations presenter;
  //  private OrderDetailsContract.OrderDetailsPresenter orderPresenter;
  private Dialog dialog;
  private CancelAdapter adapter;
  private String selectedDelay = null;
  private boolean delay = false;
  private String strTime;
  private ArrayList<TextView> textViewArray = new ArrayList<>();

  public CancellationDialog(Context context, Object presenter) {
    this.context = context;
    if (presenter instanceof DialogOrderDetailsContract.PresenterOperations) {
      this.presenter = (DialogOrderDetailsContract.PresenterOperations) presenter;
    } else {
      //          this.orderPresenter = (OrderDetailsContract.OrderDetailsPresenter)presenter;
    }
  }

  @TargetApi(Build.VERSION_CODES.N)
  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public void createDialog(boolean delay, String time, String timeStamp) {
    String inputPattern = "yyyy-MM-dd HH:mm:ss";
    String outputPattern = "h.mm a, d MMM ";
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);
    Date date = null;
    try {
      date = inputFormat.parse(time);
      strTime = outputFormat.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.delay = delay;
    dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_delay_or_cancel);
    dialog.getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(
        new ColorDrawable(context.getResources().getColor(R.color.transperent)));
    ButterKnife.bind(this, dialog);
    LinearLayoutManager llm = new LinearLayoutManager(context);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    rvReasons.setLayoutManager(llm);
    textViewArray.add(tvDelayBy1);
    textViewArray.add(tvDelayBy2);
    textViewArray.add(tvDelayBy3);
    textViewArray.add(tvDelayBy4);
    if (date != null) {
      calculateCurrentDelay(date);
    }
    tvOrderDueAt.setText(strTime);
    if (delay) {
      tv_title.setText(dealy_order);
      llDelayBy.setVisibility(View.VISIBLE);
    } else {
      etReason.requestFocus();
      tv_title.setText(cancelOrder);
      llDelayBy.setVisibility(View.GONE);
    }
    dialog.show();
  }

  /**
   * it will convert the date object into hours and minutes.
   *
   * @param timeStamp date object.
   */
  private void calculateCurrentDelay(Date timeStamp) {
    Date currentTime = Calendar.getInstance().getTime();
    long mills = currentTime.getTime() - timeStamp.getTime();
    int hours = (int) mills / (1000 * 60 * 60);
    int mins = (int) (mills / (1000 * 60)) % 60;
    tvCurrentDelay.setText("" + hours + " Hr : " + mins + " Min");
  }

  public void setReasons(ArrayList<Reasons> reasons) {
    adapter = new CancelAdapter(context, reasons);
    rvReasons.setAdapter(adapter);
  }

  /**
   * This method is used to set the selected reason.
   *
   * @param reason reason object.
   */
  public void setSelectedReason(Reasons reason) {
    if (delay) {
      if (selectedDelay != null) {
        if (presenter != null) {
          presenter.delayOrder(reason, selectedDelay);
        } else {
          //              orderPresenter.delayOrder(reason, selectedDelay);
        }
        closeDialog();
      } else {
        new Utility().mShowMessage(context.getResources().getString(R.string.message),
            context.getResources().getString(R.string.selectDelay), (Activity) context);
      }
    } else {
      if (presenter != null) {
        presenter.cancelOrder(reason);
      } else {
        //            orderPresenter.cancelOrder(reason);
      }
      closeDialog();
    }
    Utility.printLog("Reason: " + reason.getReasons());
  }

  @OnClick({R.id.ivCloseButton, R.id.ivCorrectIcon, R.id.tvDelayBy1, R.id.tvDelayBy2,
      R.id.tvDelayBy3, R.id.tvDelayBy4})
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.ivCloseButton:
        if (dialog != null) {
          closeDialog();
        }
        break;
      case R.id.ivCorrectIcon:
        adapter.submitOnclick(this);
        break;
      case R.id.tvDelayBy1:
        setBackground(0);
        selectedDelay = "15";
        break;
      case R.id.tvDelayBy2:
        setBackground(1);
        selectedDelay = "30";
        break;
      case R.id.tvDelayBy3:
        setBackground(2);
        selectedDelay = "45";
        break;
      case R.id.tvDelayBy4:
        setBackground(3);
        selectedDelay = "60";
        break;
    }
  }

  /**
   * This ,ethod is used to set the background colour.
   *
   * @param selected selected time.
   */
  public void setBackground(int selected) {
    for (int i = 0; i < textViewArray.size(); i++) {
      textViewArray.get(i).setTextColor(
          i == selected ? context.getResources().getColor(R.color.white)
              : context.getResources().getColor(R.color.xanadu));
      textViewArray.get(i).setBackgroundDrawable(i == selected ?
          context.getResources().getDrawable(R.drawable.rectangle_corner_green_solid)
          : context.getResources().getDrawable(R.drawable.rectangle_stroke_corner_gray));
    }
  }

  public void closeDialog() {
    dialog.dismiss();
    dialog.cancel();
  }
}
