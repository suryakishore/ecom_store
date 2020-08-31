package com.app.delivxstore.main.searchCustomer;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.addCustomer.AddCustomerActivity;
import com.app.delivxstore.utility.Utility;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchCustomerActivity extends DaggerAppCompatActivity implements SearchCustomerContract.ViewOperations {


    @BindView(R.id.ivBackBtn)
    ImageView ivBackBtn;
    @BindView(R.id.ivCross)
    ImageView ivCross;

    @BindView(R.id.etSearchCustomer)
    EditText etSearchCustomer;
    @BindView(R.id.rlCustomers)
    RelativeLayout rlCustomers;


    @BindView(R.id.rvCustomers)
    RecyclerView rvCustomers;

    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.llNumber)
    LinearLayout llNumber;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    Activity context;
    @Inject
    Utility utility;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    SearchCustomerContract.PresenterOperations presenter;

    private ArrayList<SearchCustomerData> searchCustomerData = new ArrayList<>();
    private SearchCustomerAdapter searchCustomerAdapter;
    private searchCustomerInterfaceCallBack searchCustomerInterfaceCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
        ButterKnife.bind(this);

        Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());


        initialize();

    }

    private void initialize() {
        getCallBack();
        rvCustomers.setLayoutManager(new LinearLayoutManager(this));
        rvCustomers.setHasFixedSize(true);
        searchCustomerAdapter = new SearchCustomerAdapter(searchCustomerData, searchCustomerInterfaceCallBack);
        rvCustomers.setAdapter(searchCustomerAdapter);

    }

    private void getCallBack() {

        searchCustomerInterfaceCallBack = new searchCustomerInterfaceCallBack() {
            @Override
            public void getCustomerValues(String name, String number, String mail,String customerID) {

                Intent intent = new Intent();

                intent.putExtra("name", name);
                intent.putExtra("number", number);
                intent.putExtra("mail", mail);
                intent.putExtra("customerID", customerID);

                setResult(Activity.RESULT_OK, intent);
                finish();

            }

        };

    }

    @OnTextChanged(R.id.etSearchCustomer)
    public void onTextChange() {

        if (!etSearchCustomer.getText().toString().isEmpty()) {
            ivCross.setVisibility(View.VISIBLE);
            presenter.getCustomers(etSearchCustomer.getText().toString());
        } else {
            if (llNumber.getVisibility()==View.VISIBLE){
                llNumber.setVisibility(View.GONE);
            }
            ivCross.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.ivBackBtn, R.id.llNumber, R.id.ivCross})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivBackBtn:
                finish();
                break;

            case R.id.ivCross:
                if (etSearchCustomer.getText().toString() != null && etSearchCustomer.getText().toString().length() > 0) {
                    etSearchCustomer.setText("");
                }
                break;
            case R.id.llNumber:
                presenter.searchCustomer();
                break;

        }
    }

    @Override
    public void showError(String msg) {
        utility.mShowMessage(getResources().getString(R.string.message), msg, SearchCustomerActivity.this);


    }

    @Override
    public void searchCustomer() {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        intent.putExtra("hint", tvNumber.getText().toString());
        startActivityForResult(intent, 123);
    }

    @Override
    public void setCustomers(ArrayList<SearchCustomerData> data) {
        if (data.size() > 0) {
            llNumber.setVisibility(View.GONE);
            rlCustomers.setVisibility(View.VISIBLE);
            searchCustomerData.clear();
            searchCustomerData.addAll(data);
            searchCustomerAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void showCustomerDet(String hint) {

        rlCustomers.setVisibility(View.GONE);
        llNumber.setVisibility(View.VISIBLE);
        tvNumber.setText(hint);


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

            switch (requestCode) {
                case 123:

                    setResult(Activity.RESULT_OK, data);

                    finish();

                    break;


            }


        }


    }
}
