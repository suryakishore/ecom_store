package com.app.delivxstore.main.addCustomer;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dagger.android.support.DaggerAppCompatActivity;

public class AddCustomerActivity extends DaggerAppCompatActivity implements AddCustomerContract.ViewOperations {

    @BindView(R.id.ivBackBtn)
    ImageView ivBackBtn;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    Activity context;

    @Inject
    Utility utility;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    AddCustomerContract.PresenterOperations presenter;

    private String hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);

        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());
        initialize();

    }

    private void initialize() {
        hint = getIntent().getStringExtra("hint");


        etPhoneNumber.setText(preferenceHelperDataSource.getCountry());
    }

    @OnTextChanged(R.id.etPhoneNumber)
    public void onTextChange() {

        if (!etPhoneNumber.getText().toString().isEmpty()) {
            if (etPhoneNumber.getText().toString().length() < (preferenceHelperDataSource.getCountry().length())) {
                etPhoneNumber.setText(preferenceHelperDataSource.getCountry());
                etPhoneNumber.setSelection(preferenceHelperDataSource.getCountry().length());
            }
        }

    }

    @OnClick(R.id.btnSave)
    public void addCustomer() {
        presenter.checkCustomer(etName.getText().toString(), etPhoneNumber.getText().toString(), etEmail.getText().toString());
    }

    @OnClick(R.id.ivBackBtn)
    public void fin() {
        finish();
    }

    @Override
    public void showError(String msg) {
        utility.mShowMessage(getResources().getString(R.string.message), msg, AddCustomerActivity.this);

    }

    @Override
    public void getCustomerValues(String name, String phoneNumber, String emailAddress,String customerID) {


        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("mail", emailAddress);
        intent.putExtra("number", phoneNumber);
        intent.putExtra("customerID", customerID);

        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }
}
