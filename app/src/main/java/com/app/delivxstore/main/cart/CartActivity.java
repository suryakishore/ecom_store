package com.app.delivxstore.main.cart;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.payment_choose_card.ChoosePaymentAct;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class CartActivity extends DaggerAppCompatActivity implements CartContract.ViewOperations {

    @BindView(R.id.etPromocode)
    EditText etPromocode;

    @BindView(R.id.tvTaxTotal)
    TextView tvTaxTotal;
    @BindView(R.id.tvDeliveryCharge)
    TextView tvDeliveryCharge;

    @BindView(R.id.tvToPay)
    TextView tvToPay;
    @BindView(R.id.btnPay)
    Button btnPay;
    @BindView(R.id.ivCross)
    ImageView ivCross;
    @BindView(R.id.ivBackBtn)
    ImageView ivBackBtn;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    @Inject
    CartPresenter mPresenter;
    private int storeDeliveryFee, tax, itemTotal,discount,bookingType,serviceType;
    private String currencySymbol,customerId,cartId,extraNote,dueDatetime,addressId,address1,address2;
    private double latitude, longitude, pickUpLat, pickUpLong,estimatedPackageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());

        ButterKnife.bind(this);
        getIntentData();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currencySymbol = bundle.getString("currencySymbol");
            customerId=bundle.getString("customerId");
            storeDeliveryFee = bundle.getInt("storeDeliveryFee", 0);
            tax = bundle.getInt("tax", 0);
            discount=bundle.getInt("discount",0);
            cartId=bundle.getString("cartId");
            bookingType = bundle.getInt("bookingType", 0);
            latitude = bundle.getDouble("latitude", 0);
            longitude = bundle.getDouble("longitude", 0);
            pickUpLat = bundle.getDouble("pickUpLat", 0);
            pickUpLong = bundle.getDouble("pickUpLong", 0);
            serviceType=bundle.getInt("serviceType",0);
             extraNote=bundle.getString("extraNote");;
            String value = bundle.getString("estimatedPackageValue", "");
            estimatedPackageValue= TextUtils.isEmpty(value)?0.0:Double.parseDouble(value);
            dueDatetime=bundle.getString("dueDatetime");

            addressId = bundle.getString("addressId");
            address1 = bundle.getString("address1");
            address2 = bundle.getString("address2");


            tvDeliveryCharge.setText(currencySymbol + " " + storeDeliveryFee);
            tvTaxTotal.setText(currencySymbol + " " + tax);
            tvToPay.setText(currencySymbol + " " + (storeDeliveryFee+tax));
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.ivCross, R.id.btnPay, R.id.ivBackBtn})
    public void click(View v) {

        switch (v.getId()) {

            case R.id.ivCross:

                etPromocode.setText("");

                break;
            case R.id.ivBackBtn:

                onBackPressed();

                break;
            case R.id.btnPay:

                mPresenter.startPayment();

                break;


        }

    }


    @Override
    public void startPayment() {

        Intent intent = new Intent(this, ChoosePaymentAct.class);
        intent.putExtra("customerId",customerId);
        intent.putExtra("couponCode",etPromocode.getText().toString());
        intent.putExtra("discount",discount);
        intent.putExtra("cartId",cartId);
        intent.putExtra("bookingType", bookingType);
        intent.putExtra("serviceType",serviceType);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("pickUpLat", pickUpLat);
        intent.putExtra("pickUpLong", pickUpLong);
        intent.putExtra("extraNote",extraNote);
        intent.putExtra("estimatedPackageValue",estimatedPackageValue);
        intent.putExtra("dueDatetime",dueDatetime);

        intent.putExtra("addressId",addressId);
        intent.putExtra("address1",address1);
        intent.putExtra("address2",address2);
        startActivity(intent);
    }
}
