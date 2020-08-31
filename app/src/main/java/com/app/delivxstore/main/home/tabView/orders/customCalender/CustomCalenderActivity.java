package com.app.delivxstore.main.home.tabView.orders.customCalender;

import static com.app.ecomstore.util.EcomConstants.END;
import static com.app.ecomstore.util.EcomConstants.START;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityCustomCalenderBinding;
import com.squareup.timessquare.CalendarPickerView;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

/**
 * this activity used to open the custom calender activity.
 */
public class CustomCalenderActivity extends DaggerAppCompatActivity implements
    CustomCalenderContract.CustomCalenderView, View.OnClickListener {
  @Inject
  CustomCalenderContract.CustomCalenderPresenter presenter;
  private ActivityCustomCalenderBinding mCalenderBinding;
  private String mStart = null, mEnd = null;
  private Calendar mNextYear;
  private Date mTomorrow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCalenderBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_calender);
    initViews();
  }

  /**
   * This method used to initialize the views.
   */
  private void initViews() {
    // calendar_view = (CalendarPickerView) findViewById(R.id.calendar_view);
    mNextYear = Calendar.getInstance();
    mNextYear.add(Calendar.YEAR, -1);
    Calendar calendar = Calendar.getInstance();
    //Date today = calendar.getTime();
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    mTomorrow = calendar.getTime();
    //   Date today = new Date();
    mCalenderBinding.calendarView.init(mNextYear.getTime(), mTomorrow).inMode(
        CalendarPickerView.SelectionMode.RANGE);
    mCalenderBinding.calendarView.scrollToDate(mTomorrow);
    mCalenderBinding.btnShowDates.setOnClickListener(this::onClick);
    mCalenderBinding.ivBack.setOnClickListener(this::onClick);
    mCalenderBinding.tvClear.setOnClickListener(this::onClick);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnShowDates:
        presenter.getDates();
        break;
      case R.id.ivBack:
        finish();
        break;
      case R.id.tvClear:
        mCalenderBinding.calendarView.init(mNextYear.getTime(), mTomorrow).inMode(
            CalendarPickerView.SelectionMode.RANGE);
        mCalenderBinding.calendarView.getSelectedDates().clear();
        mCalenderBinding.calendarView.deferNotifyDataSetChanged();
        mStart = "";
        mEnd = "";
        Intent intent = new Intent();
        intent.putExtra(START, mStart);
        intent.putExtra(END, mEnd);
        setResult(Activity.RESULT_OK, intent);
        onBackPressed();
        break;
    }
  }

  @Override
  public void finishActivity() {
    Intent intent = new Intent();
    intent.putExtra(START, mStart);
    intent.putExtra(END, mEnd);
    setResult(Activity.RESULT_OK, intent);
    onBackPressed();
  }

  @Override
  public void setDates() {
    String[] words;
    int monthOfYear, day;
    String month, year, dateOfMonth;
    if (mCalenderBinding.calendarView.getSelectedDates() != null
        && mCalenderBinding.calendarView.getSelectedDates().size() >= 1) {
      for (int i = 0; i < mCalenderBinding.calendarView.getSelectedDates().size(); i++) {
        if (i == 0 || i == mCalenderBinding.calendarView.getSelectedDates().size() - 1) {
          Date startDate = mCalenderBinding.calendarView.getSelectedDates().get(i);
          words = startDate.toString().split(" ");
          monthOfYear = startDate.getMonth() + 1;
          day = startDate.getDate();
          //     Log.d("exe", "day" + day);
          month = (monthOfYear < 10) ? "0" + monthOfYear : monthOfYear + "";
          dateOfMonth = (day < 10) ? "0" + day : day + "";
          year = words[5];
          if (i == 0 || mCalenderBinding.calendarView.getSelectedDates().size() == 1) {
            mStart = String.format("%s-%s-%s", year, month, dateOfMonth);
            mEnd = mStart;
          } else {
            mEnd = String.format("%s-%s-%s", year, month, dateOfMonth);
          }
        }
      }
      if (mStart != null && mEnd != null) {
        Log.d("exe", "start" + mStart + "end" + mEnd);
        presenter.finish();
      }
    } else {
      Toast.makeText(getApplicationContext(), getResources().getString(R.string.pleaseSelectDate), Toast.LENGTH_SHORT).show();
    }
  }
}
