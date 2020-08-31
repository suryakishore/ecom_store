package com.app.delivxstore.main.orderFrom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.add_customer_items.AddLaundryItemActivity;
import com.app.delivxstore.main.manage_address.ManageAddressAct;
import com.app.delivxstore.main.searchCustomer.SearchCustomerActivity;
import com.app.delivxstore.utility.Utility;

import dagger.android.support.DaggerAppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class OrderFromActivity extends DaggerAppCompatActivity
        implements OrderFromContract.ViewOperations, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.ivBackBtn)
    ImageView ivBackBtn;
    @BindView(R.id.btnCreateOrder)
    Button btnCreateOrder;
    @BindView(R.id.llFindCustomer)
    LinearLayout llFindCustomer;
    @BindView(R.id.llCustomerDetails)
    LinearLayout llCustomerDetails;
    @BindView(R.id.llPickupAdd)
    LinearLayout llPickupAdd;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvAddCustomer)
    TextView tvAddCustomer;
    @BindView(R.id.tvMail)
    TextView tvMail;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.llPickupAddDetails)
    LinearLayout llPickupAddDetails;
    @BindView(R.id.tvTaggedAs)
    TextView tvTaggedAs;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvlandMark)
    TextView tvlandMark;
    @BindView(R.id.tvChoosePicLoc)
    TextView tvChoosePicLoc;

    @BindView(R.id.llDelAdd)
    LinearLayout llDelAdd;
    @BindView(R.id.tvChooseDelLoc)
    TextView tvChooseDelLoc;
    @BindView(R.id.llDelAddDetails)
    LinearLayout llDelAddDetails;
    @BindView(R.id.tvDelTaggedAs)
    TextView tvDelTaggedAs;
    @BindView(R.id.tvDelAddress)
    TextView tvDelAddress;
    @BindView(R.id.tvDellandMark)
    TextView tvDellandMark;

    @BindView(R.id.dateAndTimeRl)
    RelativeLayout dateAndTimeRl;
    @BindView(R.id.scheduleRl)
    RelativeLayout scheduleRl;
    @BindView(R.id.arrowIv)
    ImageView arrowIv;
    @BindView(R.id.tickNowIv)
    ImageView tickNowIv;
    @BindView(R.id.tickIv)
    ImageView tickIv;
    @BindView(R.id.nowRl)
    RelativeLayout nowRl;
    @BindView(R.id.nowTv)
    TextView nowTv;
    @BindView(R.id.nowDesTv)
    TextView nowDesTv;
    @BindView(R.id.scheduleTv)
    TextView scheduleTv;
    @BindView(R.id.scheduleDesTv)
    TextView scheduleDesTv;
    @BindView(R.id.dateLl)
    LinearLayout dateLl;
    @BindView(R.id.slotLl)
    LinearLayout slotLl;
    @BindView(R.id.dateTv)
    TextView dateTv;
    @BindView(R.id.selectSlotTv)
    TextView selectSlotTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private Calendar calander;

    @Inject
    Activity context;
    @Inject
    Utility utility;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    OrderFromContract.PresenterOperations presenter;
    private Animation show_up, show_gone;
    private int bookingType = 1;
    private int serviceType;
    private double pickLat, pickLang, delLat, delLang;
    private String customerID = "", dueDatetime = "";
    private String dueDateForTime = "", dueDate = "", dueTime = "", addressId, address1, address2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_from);
        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());

        ButterKnife.bind(this);
        getIntentData();

        show_up = AnimationUtils.loadAnimation(this, R.anim.show_visible);
        show_gone = AnimationUtils.loadAnimation(this, R.anim.show_gone);
        calander = Calendar.getInstance();
        presenter.now();
    }

    private void getIntentData() {
        serviceType = getIntent().getIntExtra("serviceType", 0);
        Log.d("exe", "serviceType" + serviceType);
    }

    @OnClick({
            R.id.ivBackBtn, R.id.llFindCustomer, R.id.llPickupAdd, R.id.llDelAdd, R.id.btnCreateOrder,
            R.id.nowRl, R.id.scheduleRl, R.id.slotLl, R.id.dateLl
    })
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivBackBtn:
                finish();
                break;
            case R.id.llFindCustomer:
                presenter.searchCustomer();
                break;
            case R.id.llPickupAdd:
                if (!customerID.isEmpty()) {

                    presenter.manageAddress();

                } else {

                    presenter.showError(getResources().getString(R.string.addCustomerEror));

                }
                break;
            case R.id.llDelAdd:
                if (!customerID.isEmpty()) {

                    presenter.DeliveryAddress();

                } else {
                    presenter.showError(getResources().getString(R.string.addCustomerEror));

                }

                break;
            case R.id.nowRl:
                presenter.now();
                break;
            case R.id.scheduleRl:
                presenter.schedule();
                break;
            case R.id.slotLl:
                presenter.selectSlot();
                break;
            case R.id.dateLl:
                presenter.selectDate();

                break;
            case R.id.btnCreateOrder:

                presenter.createOrder();

                break;
        }
    }

    @Override
    public void showError(String msg) {

        utility.mShowMessage(getResources().getString(R.string.message), msg, OrderFromActivity.this);
    }

    @Override
    public void searchCustomer() {

        Intent intent = new Intent(this, SearchCustomerActivity.class);
        startActivityForResult(intent, 123);
    }

    @Override
    public void manageAddress() {

        Intent intent = new Intent(this, ManageAddressAct.class);
        intent.putExtra("customerId", customerID);

        startActivityForResult(intent, 145);

    }

    @Override
    public void selectDate() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2018, 1, 1);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void createOrder() {

        if (llCustomerDetails.getVisibility() != View.VISIBLE) {
            // Log.d("exe","once");
            if (customerID.isEmpty()) {
                presenter.showError(getResources().getString(R.string.addCustomerEror));
            }
        } else if (llPickupAddDetails.getVisibility() != View.VISIBLE) {
            // Log.d("exe","two");

            if (pickLat == 0.0 || pickLang == 0.0) {
                presenter.showError(getResources().getString(R.string.pickUpAddError));
            }
        } else if (llDelAddDetails.getVisibility() != View.VISIBLE) {
            //  Log.d("exe","three");

            if (delLat == 0.0 || delLang == 0.0) {

                presenter.showError(getResources().getString(R.string.delAddError));
            }
        } else {

            if (bookingType == 2) {
                //  Log.d("exe","three");

                if (dueDate.isEmpty()) {

                    presenter.showError(getResources().getString(R.string.pleaseSelectDate));
                    return;
                }
                if (dueTime.isEmpty()) {
                    presenter.showError(getResources().getString(R.string.pleaseSelecttime));
                    return;
                }
            }
            Intent intent = new Intent(this, AddLaundryItemActivity.class);
            intent.putExtra("customerId", customerID);
            intent.putExtra("bookingType", bookingType);
            intent.putExtra("serviceType", serviceType);
            intent.putExtra("latitude", delLat);
            intent.putExtra("longitude", delLang);
            intent.putExtra("pickUpLat", pickLat);
            intent.putExtra("pickUpLong", pickLang);
            intent.putExtra("dueDatetime", dueDate + " " + dueTime);

            intent.putExtra("addressId", addressId);
            intent.putExtra("address1", address1);
            intent.putExtra("address2", address2);
            startActivity(intent);
        }
    }

    @Override
    public void DeliveryAddress() {

        Intent intent = new Intent(this, ManageAddressAct.class);
        intent.putExtra("customerId", customerID);
        startActivityForResult(intent, 154);

    }

    @Override
    public void schedule() {

        bookingType = 2;
        if (serviceType == 2) {
            scheduleTv.setTextColor(getResources().getColor(R.color.portGore));
            tickIv.setImageResource((R.drawable.ic_check_icon_on));
            DrawableCompat.setTint(tickIv.getDrawable(), ContextCompat.getColor(this, R.color.portGore));
            scheduleDesTv.setTextColor(getResources().getColor(R.color.portGore));
        } else {
            scheduleTv.setTextColor(getResources().getColor(R.color.portGore));
            tickIv.setImageResource((R.drawable.ic_check_icon_on));
            DrawableCompat.setTint(tickIv.getDrawable(), ContextCompat.getColor(this, R.color.portGore));
            scheduleDesTv.setTextColor(getResources().getColor(R.color.portGore));
        }

        tickNowIv.setImageResource((R.drawable.ic_check_off));
        DrawableCompat.setTint(tickNowIv.getDrawable(), ContextCompat.getColor(this, R.color.manatee));
        nowTv.setTextColor(getResources().getColor(R.color.manatee));
        nowDesTv.setTextColor(getResources().getColor(R.color.manatee));
        dateAndTimeRl.startAnimation(show_gone);
        slotLl.startAnimation(show_gone);
        dateAndTimeRl.setVisibility(View.VISIBLE);
        slotLl.setVisibility(View.VISIBLE);

        scrollView.post(new Runnable() {

            public void run() {
                scrollView.scrollTo(0, scrollView.getBottom());
            }
        });
    }

    @Override
    public void now() {
        bookingType = 1;
        if (serviceType == 2) {
            nowTv.setTextColor(getResources().getColor(R.color.portGore));
            tickNowIv.setImageResource(R.drawable.ic_check_icon_on);
            DrawableCompat.setTint(tickNowIv.getDrawable(),
                    ContextCompat.getColor(this, R.color.portGore));
            nowDesTv.setTextColor(getResources().getColor(R.color.portGore));
            tickIv.setImageResource(R.drawable.ic_check_off);
        } else {
            nowTv.setTextColor(getResources().getColor(R.color.portGore));
            tickNowIv.setImageResource(R.drawable.ic_check_icon_on);
            DrawableCompat.setTint(tickNowIv.getDrawable(),
                    ContextCompat.getColor(this, R.color.portGore));
            nowDesTv.setTextColor(getResources().getColor(R.color.portGore));
            tickIv.setImageResource(R.drawable.ic_check_off);
        }
        scheduleTv.setTextColor(getResources().getColor(R.color.manatee));
        scheduleDesTv.setTextColor(getResources().getColor(R.color.manatee));
        dateAndTimeRl.setVisibility(View.GONE);
        slotLl.setVisibility(View.GONE);
        DrawableCompat.setTint(tickIv.getDrawable(), ContextCompat.getColor(this, R.color.manatee));
    }

    @Override
    public void setTimeIn12Hour(String time) {

        if (!TextUtils.isEmpty(time)) {
            dueTime = time.substring(0, time.length() - 3) + ":00";
        }
        timeTv.setText(time);
    }

    @Override
    public void setDate(String date) {
        dateTv.setText(date);
    }

    @Override
    public void selectTime() {
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, this, calander.get(Calendar.HOUR_OF_DAY),
                        calander.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

            switch (requestCode) {

                case 123:

                    if (resultCode == Activity.RESULT_OK) {
                        Bundle bundle = data.getExtras();

                        if (bundle != null) {
                            tvAddCustomer.setVisibility(View.GONE);

                            llCustomerDetails.setVisibility(View.VISIBLE);

                            tvCustomerName.setText(bundle.getString("name"));
                            tvMail.setText(bundle.getString("mail"));
                            tvNumber.setText(bundle.getString("number"));
                            customerID = bundle.getString("customerID");
                            Log.d("exe", "customerID" + customerID);
                        } else {
                            showError(getResources().getString(R.string.somethingWentWrong));
                        }
                    }

                    break;

                case 145:

                   /* intent.putExtra("flatNum", mAddressList.get(pos).getFlatNumber());
                    intent.putExtra("landMark", mAddressList.get(pos).getLandmark());
                    intent.putExtra("addr1", mAddressList.get(pos).getAddLine1());
                    intent.putExtra("addr2", mAddressList.get(pos).getAddLine2());
                    intent.putExtra("addId", mAddressList.get(pos).getId());
                    intent.putExtra("addId", mAddressList.get(pos).getId());
                    intent.putExtra("lat", mAddressList.get(pos).getLatitude());
                    intent.putExtra("longi", mAddressList.get(pos).getLongitude());
                    intent.putExtra("savedAs", mAddressList.get(pos).getTaggedAs());
*/
                    if (resultCode == Activity.RESULT_OK) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {

                            tvChoosePicLoc.setVisibility(View.GONE);

                            llPickupAddDetails.setVisibility(View.VISIBLE);
                            pickLat = bundle.getDouble("lat");
                            pickLang = bundle.getDouble("longi");
                            tvTaggedAs.setText(bundle.getString("savedAs"));
                            tvAddress.setText(bundle.getString("addr1"));
                            tvlandMark.setText(bundle.getString("landMark"));
                        } else {
                            showError(getResources().getString(R.string.somethingWentWrong));
                        }
                    }

                    break;
                case 154:

                    if (resultCode == Activity.RESULT_OK) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {

                            tvChooseDelLoc.setVisibility(View.GONE);

                            llDelAddDetails.setVisibility(View.VISIBLE);
                            delLat = bundle.getDouble("lat");
                            delLang = bundle.getDouble("longi");
                            addressId = bundle.getString("addId");
                            address1 = bundle.getString("addr1");
                            address2 = bundle.getString("addr2");
                            tvDelTaggedAs.setText(bundle.getString("savedAs"));
                            tvDelAddress.setText(bundle.getString("addr1"));
                            tvDellandMark.setText(bundle.getString("landMark"));
                            Log.d("exe", "addressId" + addressId + "ad1" + address1 + "ad2" + address2);
                        } else {
                            showError(getResources().getString(R.string.somethingWentWrong));
                        }
                    }

                    break;
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int newMonth = month + 1;
        if (String.valueOf(month).length() == 1) {
            dueDate = year + "-" + "0" + month + "-" + dayOfMonth;
            dueDateForTime = year + "-" + "0" + newMonth + "-" + dayOfMonth;
        } else {
            dueDate = year + "-" + "0" + month + "-" + dayOfMonth;
            dueDateForTime = year + "-" + "0" + newMonth + "-" + dayOfMonth;
        }
        Utility.printLog("date is " + month + "year" + year + "dayOfMonth" + dayOfMonth);
        presenter.setDate(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Utility.printLog("time is " + hourOfDay + "minute" + minute);

        Date currentDate = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2019-08-3
        String formattedDate = df.format(currentDate);

        String dueDateNewFormat = "";
        dueDateNewFormat = dueDateForTime + " " + hourOfDay + ":" + minute + ":59";

        try {
            Date dateObj = df.parse(dueDateNewFormat);
            Date currentDateObj = df.parse(formattedDate);

            if (currentDateObj.compareTo(dateObj) < 0) {

                presenter.setTimeIn12Hour(hourOfDay, minute);
            } else {
                Toast.makeText(context, "Please select valid Date and Time", Toast.LENGTH_SHORT).show();
                setTimeIn12Hour("");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
