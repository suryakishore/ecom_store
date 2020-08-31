package com.app.delivxstore.main.add_address;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.manage_address.DataInGetAddress;
import com.app.delivxstore.manual_locate.SearchLocationAct;
import com.app.delivxstore.utility.AutocompleteInfo;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.WorkaroundMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.app.delivxstore.utility.VariableConstants.COMING_FROM;

public class AddAddressAct extends DaggerAppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, View.OnClickListener, AddressActView {

    private Typeface fontMedium, fontRegular;

    @BindView(R.id.saveDelLocTv)
    TextView saveDelLocTv;
    @BindView(R.id.backIv)
    ImageView backIv;
    @BindView(R.id.saveAsTv)
    TextView saveAsTv;
    @BindView(R.id.addressTl)
    TextInputLayout addressTl;
    @BindView(R.id.addressEt)
    EditText addressEt;
    @BindView(R.id.landMarkTl)
    TextInputLayout landMarkTl;
    @BindView(R.id.landMarkEt)
    EditText landMarkEt;
    @BindView(R.id.homeNoTl)
    TextInputLayout homeNoTl;
    @BindView(R.id.homeNoEt)
    EditText homeNoEt;
    @BindView(R.id.homeRl)
    RelativeLayout homeRl;
    @BindView(R.id.workRl)
    RelativeLayout workRl;
    @BindView(R.id.otherRl)
    RelativeLayout otherRl;
    @BindView(R.id.saveAndProceedBtn)
    Button saveAndProceedBtn;
    @BindView(R.id.homeIv)
    ImageView homeIv;
    @BindView(R.id.homeTv)
    TextView homeTv;
    @BindView(R.id.homeView)
    View homeView;
    @BindView(R.id.workIv)
    ImageView workIv;
    @BindView(R.id.workTv)
    TextView workTv;
    @BindView(R.id.workView)
    View workView;
    @BindView(R.id.otherIv)
    ImageView otherIv;
    @BindView(R.id.otherTv)
    TextView otherTv;
    @BindView(R.id.otherView)
    View otherView;
    @BindView(R.id.otherEt)
    EditText otherEt;
    @BindView(R.id.ohtersTl)
    TextInputLayout ohtersTl;
    @BindView(R.id.skipTv)
    TextView skipTv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    //this is flag is set true when user click to search address.
    private int app_color;
    private int colorShark;
    @Inject
    FontUtils application_fonts;
    @Inject
    AddressActPresenter presenter;
    private List<AutocompleteInfo> mAddressList = new ArrayList<>();
    private ArrayAdapter mAdapter;
    private Observable<String> queryObservable;
    private GoogleMap googleMap;
    private static final int REQUEST_FOR_LOCATION = 111;
    private final int LOCATION_RESULT = 11;

    private final int LOCATION_CHANGED = 101;

    private String action = "add";

    private double lat = 0, lng = 0;
    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;
    private String customerId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        mInitialization();

        setmapHeight();
        setTypeFaces();

    }

    private void mGetIntentData() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            action = getIntent().getStringExtra("action");
            customerId=getIntent().getStringExtra("customerId");

            DataInGetAddress address = (DataInGetAddress) getIntent().getSerializableExtra("address");
             if (address!=null){
            presenter.setIntentData(action, address);

            if (action.equals("edit")) {
                addressEt.setText(address.getAddLine1());
                landMarkEt.setText(address.getLandmark());
                homeNoEt.setText(address.getFlatNumber());
                lat = address.getLatitude();
                lng = address.getLongitude();
            }
        }}
    }

    private void mInitialization() {

        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);

        fontMedium = application_fonts.mediumFont();
        fontRegular = application_fonts.regularFont();

        app_color = getResources().getColor(R.color.colorPrimary);
        colorShark = getResources().getColor(R.color.black);


        //  addressEt.setEnabled(false);
        addressEt.setClickable(true);
        addressEt.setKeyListener(null);
        ((WorkaroundMapFragment) mapFragment).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                scrollView.requestDisallowInterceptTouchEvent(true);

            }
        });
        mGetIntentData();

        presenter.start();
        mPerMissionForLocation();


    }


    private void setmapHeight() {

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels / 3;
        Utility.printLog("ht is " + height);
        height = height + 50;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getView().setLayoutParams(layoutParams);
        View mapView = mapFragment.getView();
        if (mapView != null && mapView.findViewById(Integer.valueOf(1)) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.valueOf(1)).getParent()).findViewById(Integer.valueOf(2));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams1.setMargins(0, 0, 30, 30);

        }

    }

    //this method is for setting the typefaces
    private void setTypeFaces() {

        saveDelLocTv.setTypeface(fontMedium);
        saveAsTv.setTypeface(fontMedium);
        homeTv.setTypeface(fontRegular);
        workTv.setTypeface(fontRegular);
        otherTv.setTypeface(fontRegular);
        saveAndProceedBtn.setTypeface(fontMedium);
        otherEt.setTypeface(fontMedium);
        ohtersTl.setTypeface(fontMedium);
        addressTl.setTypeface(fontMedium);
        addressEt.setTypeface(fontMedium);
        landMarkTl.setTypeface(fontMedium);
        landMarkEt.setTypeface(fontMedium);
        homeNoEt.setTypeface(fontMedium);
        homeNoTl.setTypeface(fontMedium);
        skipTv.setTypeface(fontMedium);
    }


    @Override
    public void addressList(List<AutocompleteInfo> list) {
        mAddressList.clear();
        mAddressList.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void startLocationAct() {
        Intent intent = new Intent(this, SearchLocationAct.class);
        intent.putExtra(COMING_FROM, "addAddress");
        startActivityForResult(intent, LOCATION_RESULT);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setOnCameraIdleListener(this);

        presenter.mapReady();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (lat != 0) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
                }
            }
        });
    }

    @OnClick({R.id.skipTv, R.id.homeRl, R.id.workRl, R.id.otherRl, R.id.saveAndProceedBtn, R.id.backIv, R.id.addressEt, R.id.addressTl})
    public void onClick(View v) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            return;
        }
        switch (v.getId()) {
            case R.id.addressTl:
                presenter.onAddressClick();
                break;
            case R.id.skipTv:
                onBackPressed();
                break;
            case R.id.addressEt:
                startLocationAct();
                break;
            case R.id.homeRl:
                int count = 1;
                presenter.setSavedAs(0, getString(R.string.home));
                break;
            case R.id.workRl:
                count = 1;
                presenter.setSavedAs(1, getString(R.string.work));
                break;
            case R.id.otherRl:
                presenter.setSavedAs(2, getString(R.string.other));
                count = 2;
                break;
            case R.id.saveAndProceedBtn:
                presenter.addAddress(addressEt.getText().toString(), "", homeNoEt.getText().toString(), landMarkEt.getText().toString()
                        , 0, 0, "", 0, otherEt.getText().toString(),customerId);

                break;

            case R.id.backIv:
                onBackPressed();
                break;
            default:
                break;
        }
    }


    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAddress(String address) {
        presenter.removeObserver(queryObservable);
        addressEt.setText(address);

    }

    @Override
    public void setHomeType() {
        homeIv.setColorFilter(colorShark);
        homeTv.setTextColor(colorShark);
        homeView.setBackgroundColor(colorShark);

        workIv.setColorFilter(app_color);
        workTv.setTextColor(app_color);
        workView.setBackgroundColor(app_color);

        otherIv.setColorFilter(app_color);
        otherTv.setTextColor(app_color);
        otherView.setBackgroundColor(app_color);
        ohtersTl.setVisibility(View.GONE);
    }

    @Override
    public void setOther(String text) {
        otherIv.setColorFilter(colorShark);
        otherTv.setTextColor(colorShark);
        otherView.setBackgroundColor(colorShark);

        homeIv.setColorFilter(app_color);
        homeTv.setTextColor(app_color);
        homeView.setBackgroundColor(app_color);

        workIv.setColorFilter(app_color);
        workTv.setTextColor(app_color);
        workView.setBackgroundColor(app_color);
        if (text.equals("Others")) {
            otherEt.setText("");
        } else {
            otherEt.setText(text);
        }
        ohtersTl.setVisibility(View.VISIBLE);
    }

    @Override
    public void setWorkType() {
        workIv.setColorFilter(colorShark);
        workTv.setTextColor(colorShark);
        workView.setBackgroundColor(colorShark);

        homeIv.setColorFilter(app_color);
        homeTv.setTextColor(app_color);
        homeView.setBackgroundColor(app_color);

        otherIv.setColorFilter(app_color);
        otherTv.setTextColor(app_color);
        otherView.setBackgroundColor(app_color);

        ohtersTl.setVisibility(View.GONE);
    }

    @Override
    public void setMarker(double lat, double longi) {
        Utility.printLog(" onNext location act  " + lat + " " + longi);
        preferenceHelperDataSource.setCurrentLat(lat);
        preferenceHelperDataSource.setCurrentLong(longi);
        LatLng mLatLng = new LatLng(lat, longi);
        if (googleMap != null)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

    }


    @Override
    public void stopAct() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", LOCATION_CHANGED);
        setResult(Activity.RESULT_OK, returnIntent);
        onBackPressed();
    }

    @Override
    public void onCameraIdle() {
        LatLng latLang = getCenterPointLatlong();
        Utility.printLog("latlong from idle of map " + latLang.latitude + " long" + latLang.longitude);
        presenter.getAddress(latLang.latitude, latLang.longitude);
    }


    public LatLng getCenterPointLatlong() {
        VisibleRegion visibleRegion = googleMap.getProjection()
                .getVisibleRegion();
        Point x1 = googleMap.getProjection().toScreenLocation(
                visibleRegion.farRight);
        Point y = googleMap.getProjection().toScreenLocation(
                visibleRegion.nearLeft);
        Point centerPoint = new Point(x1.x / 2, y.y / 2);

        return googleMap.getProjection().fromScreenLocation(centerPoint);
    }

    /**
     * <h>mperMissionForLocation</h>
     * to check for run time permission for location if post marshmellow
     * else directly call location API
     */
    private void mPerMissionForLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !(checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_FOR_LOCATION);
        } else {
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);

            }

            presenter.getCurrentAddress();

        }
    }


    /**
     * <H>Handling RequestPermissionsResult </H>
     * <P>If permission is granted then calling api else showing message to user.</P>
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_FOR_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (googleMap != null) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    googleMap.setMyLocationEnabled(true);

                }

                presenter.getCurrentAddress();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utility.printLog("req code " + requestCode);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case LOCATION_RESULT:
                    presenter.setLatlong(data.getDoubleExtra("lat", 0), data.getDoubleExtra("longi", 0));
                    addressEt.setText(data.getStringExtra("address"));
                    presenter.getAddress(data.getDoubleExtra("lat", 0),
                            data.getDoubleExtra("longi", 0));
                    setMarker(data.getDoubleExtra("lat", 0), data.getDoubleExtra("longi", 0));
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        // overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
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
