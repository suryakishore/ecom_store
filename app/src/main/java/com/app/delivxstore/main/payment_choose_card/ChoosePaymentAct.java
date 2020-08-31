package com.app.delivxstore.main.payment_choose_card;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.MainActivity;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class ChoosePaymentAct extends DaggerAppCompatActivity implements ChoosePaymentView {

    private Typeface fontMedium;
    private Typeface fontRegular;

    @BindView(R.id.walletTxtTv)
    TextView walletTxtTv;
    @BindView(R.id.walletTv)
    TextView walletTv;
    @BindView(R.id.walletAmountTv)
    TextView walletAmountTv;
    @BindView(R.id.creditAndDebitTv)
    TextView creditAndDebitTv;
    @BindView(R.id.addNewCardTv)
    TextView addNewCardTv;
    @BindView(R.id.payYourOrderTv)
    TextView payYourOrderTv;
    /*
        @BindView(R.id.payOnDelTv) TextView payOnDelTv;
    */
    @BindView(R.id.payOnDelTxtTv)
    TextView payOnDelTxtTv;
    @BindView(R.id.payDelTv)
    TextView payDelTv;
    @BindView(R.id.cardsListRv)
    RecyclerView cardsListRv;
    @BindView(R.id.payOnDelRl)
    RelativeLayout payOnDelRl;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.payOnDelTickIv)
    ImageView payOnDelTickIv;
    /*
        @BindView(R.id.newCardRl) RelativeLayout newCardRl;
    */
    @BindView(R.id.walletAmountRl)
    RelativeLayout walletAmountRl;
    @BindView(R.id.walletPayTv)
    TextView walletPayTv;
    @BindView(R.id.addMoneyTv)
    TextView addMoneyTv;
    //  @BindView(R.id.actionIv) ImageView actionIv;
    @BindView(R.id.walletPayLl)
    LinearLayout walletPayLl;
    @BindView(R.id.walletTickIv)
    ImageView walletTickIv;

    @BindView(R.id.idealLabelTxtTv)
    TextView idealLabelTxtTv;
    @BindView(R.id.idealRl)
    RelativeLayout idealRl;
    @BindView(R.id.idealTv)
    TextView idealTv;
    @BindView(R.id.idealTickIv)
    ImageView idealTickIv;
    @BindView(R.id.payOnIdealTv)
    TextView payOnIdealTv;
    @BindView(R.id.sucecssTv)
    TextView sucecssTv;
    @BindView(R.id.success_tick)
    ImageView success_tick;
    @BindView(R.id.successRl)
    RelativeLayout successRl;

    @Inject
    FontUtils applicationFonts;

    @Inject
    ChoosePaymentPresenter mPresenter;

    private ChoosePaymentAdapter paymentAdapter;
    private ArrayList<CardData> cardList = new ArrayList<>();
    private boolean hideWallet = false;
    private String customerId, couponCode, cartId, extraNote, dueDatetime,addressId,address1,address2;
    private int paymentType = 0;
    private int isPayByWallet = 0, discount;
    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    private int bookingType, serviceType;
    private double latitude, longitude, pickUpLat, pickUpLong, estimatedPackageValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
        mInitialization();
        setTypeFaces();
    }


    private void mInitialization() {
        ButterKnife.bind(this);
        Utility.RtlConversion(ChoosePaymentAct.this, mPresenter.getLanguage());
        //obtaining the typeface objects
        fontMedium = applicationFonts.boldFont();
        fontRegular = applicationFonts.regularFont();
        walletAmountRl.setSelected(false);

        if (getIntent() != null && getIntent().hasExtra("hideWallet")) {
            hideWallet = getIntent().getBooleanExtra("hideWallet", false);

        }
        if (hideWallet) {
            walletAmountRl.setVisibility(View.GONE);
            walletTxtTv.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager mlayoutManager;
        paymentAdapter = new ChoosePaymentAdapter(this, cardList, fontRegular);
        cardsListRv.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        cardsListRv.setLayoutManager(mlayoutManager);
        cardsListRv.setItemAnimator(new DefaultItemAnimator());
        cardsListRv.setAdapter(paymentAdapter);


       /* if (getIntent() != null && getIntent().hasExtra("paymentType")) {
            paymentType = getIntent().getIntExtra("paymentType", 0);
            isPayByWallet = getIntent().getIntExtra("isPayByWallet", 0);
        }

        if ((paymentType == 0 && isPayByWallet == 1)) { *//*wallet*//*
            showWalletPayment();
        } else if (paymentType == 1 && isPayByWallet == 1) { *//*card + wallet*//*
            showWalletPayment();
            paymentAdapter.setPaymentType(paymentType);
            paymentAdapter.notifyDataSetChanged();
        } else if (paymentType == 2 && isPayByWallet == 1) { *//*cash + wallet*//*
            showCashBtn();
            showWalletPayment();
        } else if (paymentType == 1 && isPayByWallet == 0) {*//*card*//*
            paymentAdapter.setPaymentType(paymentType);
            paymentAdapter.notifyDataSetChanged();
        } else if (paymentType == 2 && isPayByWallet == 0) {*//*cash*//*
            showCashBtn();
        }*/

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            customerId = bundle.getString("customerId");
            couponCode = bundle.getString("couponCode");
            discount = bundle.getInt("discount", 0);
            cartId = bundle.getString("cartId");
            bookingType = bundle.getInt("bookingType", 0);
            latitude = bundle.getDouble("latitude", 0);
            longitude = bundle.getDouble("longitude", 0);
            pickUpLat = bundle.getDouble("pickUpLat", 0);
            pickUpLong = bundle.getDouble("pickUpLong", 0);

            serviceType = bundle.getInt("serviceType", 0);
            extraNote = bundle.getString("extraNote");
            estimatedPackageValue = bundle.getDouble("estimatedPackageValue", 0);
            dueDatetime = bundle.getString("dueDatetime");
            addressId = bundle.getString("addressId");
            address1 = bundle.getString("address1");
            address2 = bundle.getString("address2");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // mPresenter.getCards(customerId);
    }

    private void setTypeFaces() {
        // titleTv.setTypeface(fontMedium);
        walletTxtTv.setTypeface(fontMedium);
        walletTv.setTypeface(fontMedium);
        walletAmountTv.setTypeface(fontMedium);
        creditAndDebitTv.setTypeface(fontMedium);
        payDelTv.setTypeface(fontMedium);
        payOnIdealTv.setTypeface(fontMedium);
        addNewCardTv.setTypeface(fontMedium);
        payYourOrderTv.setTypeface(fontMedium);
        walletPayTv.setTypeface(fontRegular);
        addMoneyTv.setTypeface(fontRegular);
        payOnDelTxtTv.setTypeface(fontMedium);

        idealLabelTxtTv.setTypeface(fontMedium);
        idealTv.setTypeface(fontMedium);
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
    public void stopAct() {
        onBackPressed();
    }

    @Override
    public void placeOrder() {

    }

    @Override
    public void onSucess() {
        successRl.setVisibility(View.VISIBLE);
        ((Animatable) success_tick.getDrawable()).start();

        CountDownTimer countDownTimer = new CountDownTimer(1000 * 2, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {

                Intent intent=new Intent(ChoosePaymentAct.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

        };
        countDownTimer.start();
    }

    @Override
    public void onError(String msg) {
        progressBar.setVisibility(View.GONE);
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Utility.showAlert(msg, this);
    }

    @Override
    public void setPaymentCardsList(ArrayList<CardData> cardsList) {

        if (cardsList.size() > 0) {
            cardList.clear();
            cardList.addAll(cardsList);
            paymentAdapter.notifyDataSetChanged();
        }

    }

    public void setSelectedCardId(String id, String cardNumber, String brand) {

        Intent intent = new Intent();
        intent.putExtra("paymentType", "card");
        intent.putExtra("isPayByWallet", isPayByWallet);
        intent.putExtra("cardId", id);
        intent.putExtra("cardNo", cardNumber);
        intent.putExtra("brand", brand);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void showCashBtn() {
        paymentAdapter.unSelectItem();
//        hideWalletPay();
        hideIdeal();
        payOnDelTickIv.setImageResource(R.drawable.ic_check_icon_on);
        //  payOnDelTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCashBtn() {

        payOnDelTickIv.setImageResource(R.drawable.ic_check_off);
        //payOnDelTv.setVisibility(View.GONE);

    }

    @Override
    public void startAddCardAct() {
       /* Intent intent = new Intent(this, AddCardAct.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);*/
    }

    @Override
    public void showWalletPayment() {
//        paymentAdapter.unSelectItem();
//        hideCashBtn();
        //    hideIdeal();
        // walletPayLl.setVisibility(View.VISIBLE);
        walletTickIv.setImageResource(R.drawable.ic_check_icon_on);

    }

    @Override
    public void payWallet(String amt) {
        Intent intent = new Intent();
        intent.putExtra("paymentType", "wallet");
        intent.putExtra("isPayByWallet", isPayByWallet);
        intent.putExtra("amount", amt);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void hideWalletPay() {
        walletPayLl.setVisibility(View.GONE);
        walletTickIv.setImageResource(R.drawable.ic_check_off);
    }

    @Override
    public void setWalletAmount(String amount) {
        walletAmountTv.setText(amount);
    }

    @Override
    public void addWallet() {
        //   Intent intent=new Intent(this,WalletAct.class);
        // startActivity(intent);
    }

    private void showIdeal() {
        idealTickIv.setImageResource(R.drawable.ic_check_icon_on);
        payOnIdealTv.setVisibility(View.VISIBLE);
        hideCashBtn();
        hideWalletPay();
    }

    private void hideIdeal() {
        idealTickIv.setImageResource(R.drawable.ic_check_off);
        payOnIdealTv.setVisibility(View.GONE);
    }

    private void idealSelected() {
        Intent intent = new Intent();
        intent.putExtra("paymentType", "ideal");
        setResult(RESULT_OK, intent);
        finish();
    }


    @OnClick({R.id.ivBackBtn, R.id.payOnDelRl, R.id.newCardRl,
            R.id.walletAmountRl, R.id.walletPayTv, R.id.addMoneyTv, R.id.payOnIdealTv, R.id.idealRl, R.id.confirmBtn})
    public void onClick(View view) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            return;
        }
        switch (view.getId()) {

            case R.id.ivBackBtn:
                onBackPressed();
                break;

            case R.id.walletPayTv:
                mPresenter.payWallet();
                break;

            case R.id.addMoneyTv:
                addWallet();
                break;

            case R.id.walletAmountRl:
                if (walletAmountRl.isSelected()) {
                    walletAmountRl.setSelected(false);
                    hideWalletPay();
                    isPayByWallet = 0;
                } else {
                    walletAmountRl.setSelected(true);
                    showWalletPayment();
                    isPayByWallet = 1;
                }
                break;

            case R.id.idealRl:
                showIdeal();
                break;

            case R.id.payOnIdealTv:
                idealSelected();
                break;

           /* case R.id.newCardRl:
                mPresenter.addNewCard();
                break;
*/
            case R.id.payOnDelRl:
                if (payOnDelRl.isSelected()) {
                    payOnDelRl.setSelected(false);
                    mPresenter.hideCashBtn();
                } else {
                    payOnDelRl.setSelected(true);
                    mPresenter.showCashBtn();
                }
                break;

            case R.id.confirmBtn:

                if (payOnDelRl.isSelected()) {
                    mPresenter.placeOrder(customerId, 2, couponCode, discount, cartId, latitude, longitude, pickUpLat, pickUpLong, String.valueOf(System.currentTimeMillis()), serviceType, bookingType, extraNote, dueDatetime, estimatedPackageValue,addressId,address1,address2);
                } else {
                    onError(getResources().getString(R.string.cashOndel));
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //   overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);

    }

}
