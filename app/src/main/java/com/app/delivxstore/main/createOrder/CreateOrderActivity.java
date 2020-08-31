package com.app.delivxstore.main.createOrder;

import android.app.Activity;
import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.orderFrom.OrderFromActivity;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class CreateOrderActivity extends DaggerAppCompatActivity implements CreateOrderContract.ViewOperations {

    @BindView(R.id.ivBackBtn)
    ImageView ivBackBtn;
    @BindView(R.id.rlSendPackage)
    RelativeLayout rlSendPackage;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.rlInventory)
    RelativeLayout rlInventory;
    @BindView(R.id.ivSendPac)
    ImageView ivSendPac;
    @BindView(R.id.ivSendPacCheckMark)
    ImageView ivSendPacCheckMark;
    @BindView(R.id.tvSendPackage)
    TextView tvSendPackage;


    @BindView(R.id.ivInventory)
    ImageView ivInventory;
    @BindView(R.id.ivInvCheckMark)
    ImageView ivInvCheckMark;
    @BindView(R.id.tvInventory)
    TextView tvInventory;

    @BindView(R.id.rlPickUp)
    RelativeLayout rlPickUp;
    @BindView(R.id.ivPickUp)
    ImageView ivPickUp;
    @BindView(R.id.tvPickUp)
    TextView tvPickUp;
    @BindView(R.id.rlDelivery)
    RelativeLayout rlDelivery;
    @BindView(R.id.ivDelivery)
    ImageView ivDelivery;
    @BindView(R.id.tvDelivery)
    TextView tvDelivery;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Inject
    Activity context;
    @Inject
    Utility utility;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    CreateOrderContract.PresenterOperations presenter;
    private int serviceType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("exe", "onCreate");

        if (savedInstanceState != null) {
          //  String s = savedInstanceState.getString("kishore", "");
           // Log.d("exe", "onCreate   " + s);

        }
        setContentView(R.layout.activity_create_order);

        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());
        ButterKnife.bind(this);

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        Log.d("exe", "onStart");

    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d("exe", "resume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("exe", "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("exe", "onDestroy");

    }
*/
   /* @Override
    public void onSaveInstanceState(Bundle outState) {

        Log.d("exe", "onSaveInstanceState");
        outState.putString("kishore", "kishzfsdfgsd");
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("exe", "onRestoreInstanceState");
        if (savedInstanceState != null) {
            String s = savedInstanceState.getString("kishore", "");
            Log.d("exe", "onRestoreInstanceState   " + s);
        }
    }*/


    @OnClick({R.id.ivBackBtn, R.id.rlSendPackage, R.id.rlInventory, R.id.rlPickUp, R.id.rlDelivery, R.id.btnNext})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivBackBtn:
                finish();
                break;

            case R.id.rlSendPackage:

                sendPackage();
                break;


            case R.id.rlInventory:

                presenter.sendInventory();
                break;

            case R.id.rlPickUp:

                presenter.pickUp();
                break;

                case R.id.rlDelivery:

                presenter.delivery();
                break;
            case R.id.btnNext:

                if (rlSendPackage.isSelected() || rlInventory.isSelected()) {

                    if (rlPickUp.isSelected() || rlDelivery.isSelected()) {
                        presenter.orderFrom();
                    } else {
                        showError(getResources().getString(R.string.selectDelType));
                    }
                } else {
                    showError(getResources().getString(R.string.chooseorderType));
                }


                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void sendPackage() {


        if (!rlSendPackage.isSelected()) {

            serviceType = 1;
            rlSendPackage.setSelected(true);
            ivSendPacCheckMark.setVisibility(View.VISIBLE);
            ivSendPac.setColorFilter(ContextCompat.getColor(context, R.color.white));
            tvSendPackage.setTextColor(ContextCompat.getColor(context, R.color.white));

            rlDelivery.setSelected(true);
            ivDelivery.setVisibility(View.VISIBLE);
            tvDelivery.setTextColor(ContextCompat.getColor(context, R.color.white));

            rlPickUp.setSelected(false);
            rlPickUp.setEnabled(false);
            tvPickUp.setTextColor(ContextCompat.getColor(context, R.color.black));
            ivPickUp.setVisibility(View.INVISIBLE);

            rlInventory.setSelected(false);
            ivInventory.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvInventory.setTextColor(ContextCompat.getColor(context, R.color.black));
            ivInvCheckMark.setVisibility(View.INVISIBLE);


        }

    }

    @Override
    public void sendInventory() {


        if (!rlInventory.isSelected()) {


            rlSendPackage.setSelected(false);
            ivSendPacCheckMark.setVisibility(View.INVISIBLE);
            ivSendPac.setColorFilter(ContextCompat.getColor(context, R.color.black));
            tvSendPackage.setTextColor(ContextCompat.getColor(context, R.color.black));


            rlPickUp.setEnabled(true);
            rlDelivery.setEnabled(true);

            rlInventory.setSelected(true);
            ivInventory.setColorFilter(ContextCompat.getColor(context, R.color.white));
            tvInventory.setTextColor(ContextCompat.getColor(context, R.color.white));
            ivInvCheckMark.setVisibility(View.VISIBLE);


        }

    }

    @Override
    public void pickUp() {


        if (rlSendPackage.isSelected() || rlInventory.isSelected()) {

            serviceType = 2;
            if (!rlPickUp.isSelected()) {


                rlDelivery.setSelected(false);
                tvDelivery.setTextColor(ContextCompat.getColor(context, R.color.black));
                ivDelivery.setVisibility(View.INVISIBLE);

                rlPickUp.setSelected(true);
                tvPickUp.setTextColor(ContextCompat.getColor(context, R.color.white));
                ivPickUp.setVisibility(View.VISIBLE);


            }

        } else {
            showError(getResources().getString(R.string.chooseorderType));
        }


    }

    @Override
    public void delivery() {

        if (rlSendPackage.isSelected() || rlInventory.isSelected()) {

            serviceType = 1;
            if (!rlDelivery.isSelected()) {

                rlDelivery.setSelected(true);
                tvDelivery.setTextColor(ContextCompat.getColor(context, R.color.white));
                ivDelivery.setVisibility(View.VISIBLE);

                rlPickUp.setSelected(false);
                tvPickUp.setTextColor(ContextCompat.getColor(context, R.color.black));
                ivPickUp.setVisibility(View.INVISIBLE);


            }

        } else {
            showError(getResources().getString(R.string.chooseorderType));
        }


    }

    @Override
    public void showError(String msg) {
        utility.mShowMessage(getResources().getString(R.string.message), msg, CreateOrderActivity.this);

    }

    @Override
    public void orderFrom() {
        Intent intent = new Intent(this, OrderFromActivity.class);
        intent.putExtra("serviceType", serviceType);
        startActivity(intent);
    }
}
