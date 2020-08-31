package com.app.delivxstore.main.manage_address;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.delivxstore.R;
import com.app.delivxstore.main.add_address.AddAddressAct;
import com.app.delivxstore.utility.FontUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * <h1>ManageAddressAct</h1>
 * <p>This class is used for the managing the managing the address</p>
 */

public class ManageAddressAct extends DaggerAppCompatActivity implements View.OnClickListener, ManageAddressView {

    private Typeface fontMedium, fontRegular;
    @Inject
    FontUtils applicationFonts;
    @BindView(R.id.addresstxtTv)
    TextView addresstxtTv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.manageAddressRv)
    RecyclerView manageAddressRv;
    @BindView(R.id.addNewAddressRl)
    RelativeLayout addNewAddressRl;
    @BindView(R.id.emptySavedAddressRl)
    RelativeLayout emptySavedAddressRl;
    @BindView(R.id.noItemsInWishListTv)
    TextView noItemsInWishListTv;
    @BindView(R.id.addAddressBtn)
    Button addAddressBtn;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;
    @BindView(R.id.view_top_shadow)
    View view_top_shadow;
    @BindView(R.id.rlToolbarDummy)
    RelativeLayout rlToolbarDummy;


    private RecyclerView.Adapter madapter;
    private ArrayList<DataInGetAddress> mAddressList = new ArrayList<>();
    String comingFrom = "";
    @Inject
    ManageAddressPresenter presenter;

    private final int ADD_ADDRESS_CODE = 400;
    private final int CHANGE_ADDRESS_CODE = 101;
    private String customerId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        mInitialization();
        setTypeFaces();
        nestedScrollViewListener();
    }


    private void mInitialization() {

        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().getExtras() != null) {
            comingFrom = getIntent().getStringExtra("comingFrom");
            customerId=getIntent().getStringExtra("customerId");
        }

        //obtaining the typeface objects
        fontMedium = applicationFonts.mediumFont();
        fontRegular = applicationFonts.regularFont();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }

        madapter = new ManageAddressAdapter(this, mAddressList, fontMedium, fontRegular);
        manageAddressRv.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manageAddressRv.setLayoutManager(mlayoutManager);
        manageAddressRv.setItemAnimator(new DefaultItemAnimator());
        manageAddressRv.setAdapter(madapter);

        presenter.getAddressList(customerId);
    }

    private void nestedScrollViewListener() {
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > (rlToolbarDummy.getHeight() / 2) + 20) {
                tvToolbarTitle.setVisibility(View.VISIBLE);
                view_top_shadow.setVisibility(View.VISIBLE);
            } else {
                tvToolbarTitle.setVisibility(View.INVISIBLE);
                view_top_shadow.setVisibility(View.INVISIBLE);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        presenter.getAddressList();
    }

    /**
     * <h2>setTypeFaces</h2>
     * <p>This mehtod is used to set the typefaces</p>
     */

    private void setTypeFaces() {
        addresstxtTv.setTypeface(fontMedium);
        noItemsInWishListTv.setTypeface(fontMedium);
        addAddressBtn.setTypeface(fontMedium);
    }

    /**
     * <h2>onClick</h2>
     * <p>This method is executed whenever the onclick of any  view</p>
     *
     * @param view is a type of view
     */


    @OnClick({R.id.ivToolbarBack, R.id.addAddressBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivToolbarBack:
                onBackPressed();
                break;
            case R.id.addAddressBtn:
                Intent intent1 = new Intent(this, AddAddressAct.class);
//                startActivity(intent1);
                intent1.putExtra("customerId", customerId);

                startActivityForResult(intent1, ADD_ADDRESS_CODE);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ADDRESS_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                int code = data.getIntExtra("result", 0);
                if (code == CHANGE_ADDRESS_CODE) {
                    presenter.getAddressList(customerId);
                }
            }
        }
    }

    public void mDeleteAddress(String id, int pos, double lat, double longi) {
//        if (comingFrom.equals("profile"))
            presenter.deleteAddress(id, pos, lat, longi);
    }

    public void mSelectAddress(String id, int pos) {


            Intent intent = new Intent();
            intent.putExtra("flatNum", mAddressList.get(pos).getFlatNumber());
            intent.putExtra("landMark", mAddressList.get(pos).getLandmark());
            intent.putExtra("addr1", mAddressList.get(pos).getAddLine1());
            intent.putExtra("addr2", mAddressList.get(pos).getAddLine2());
            intent.putExtra("addId", mAddressList.get(pos).getId());
            intent.putExtra("lat", mAddressList.get(pos).getLatitude());
            intent.putExtra("longi", mAddressList.get(pos).getLongitude());
            intent.putExtra("savedAs", mAddressList.get(pos).getTaggedAs());
            setResult(RESULT_OK, intent);
            onBackPressed();

    }

    public void mEditAddress(DataInGetAddress address) {
        Intent intent = new Intent(this, AddAddressAct.class);
        intent.putExtra("address", address);
        intent.putExtra("action", "edit");
//        startActivity(intent);

        startActivityForResult(intent, ADD_ADDRESS_CODE);
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

    }


    @Override
    public void removeAddressRow(int pos) {
        mAddressList.remove(pos);
        madapter.notifyDataSetChanged();

    }

    @Override
    public void setAddressList(ArrayList<DataInGetAddress> manageAddressList) {


        if (manageAddressList.size() > 0) {
            addNewAddressRl.setVisibility(View.VISIBLE);
            emptySavedAddressRl.setVisibility(View.GONE);
        }

        mAddressList.clear();
        mAddressList.addAll(manageAddressList);
        madapter.notifyDataSetChanged();

    }

    @Override
    public void noAddress() {
        addNewAddressRl.setVisibility(View.GONE);
        emptySavedAddressRl.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String msg) {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
        Toast.makeText(ManageAddressAct.this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
    }




    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}

