package com.app.delivxstore.manual_locate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.delivxstore.R;
import com.app.delivxstore.splash.SplashActivity;
import com.app.delivxstore.utility.AutocompleteInfo;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.PlaceAutoCompleteModel;
import com.app.delivxstore.utility.Utility;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.app.delivxstore.utility.VariableConstants.COMING_FROM;
import static com.app.delivxstore.utility.VariableConstants.REQUEST_CHECK_SETTINGS;


public class SearchLocationAct extends DaggerAppCompatActivity implements SearchView,View.OnClickListener, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks
{
    @BindView(R.id.locationSearch_Et) EditText locationSearch_Et;
    @BindView(R.id.addressList_Lv) ListView addressList_Lv;
    @BindView(R.id.backIv) ImageView backIv;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.cancelTv) TextView cancelTv;
    @BindView(R.id.currentLocTxtTv) TextView currentLocTxtTv;
    @BindView(R.id.emptyRl) RelativeLayout emptyRl;
    @BindView(R.id.listLl)
    LinearLayout listLl;
    @BindView(R.id.noResultTv)
    TextView noResultTv;
    @BindView(R.id.currentLocRl)
    LinearLayout currentLocRl;
    @BindView(R.id.currentLocTv) TextView currentLocTv;
    @BindView(R.id.searchResultTxtTv) TextView searchResultTxtTv;
    @BindView(R.id.searchesRv)
    RecyclerView searchesRv;
    @Inject SearchPresenter presenter;
    private List<AutocompleteInfo> mAddressList=new ArrayList<>();
    private ArrayAdapter mAdapter;
    private Unbinder unbinder;
    private Typeface fontMedium, fontRegular;
    @Inject
    FontUtils applicationFonts;
    private static final int REQUEST_FOR_LOCATION = 111;

    private GoogleApiClient mGoogleApiClient;
    private PlaceAutoCompleteAdapter autoCompleteAdapter;
    private PlaceAdapter placeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        mInitialization();
    }

    private void mInitialization()
    {
        unbinder=  ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        Utility.RtlConversion(SearchLocationAct.this,presenter.getLanguage());

        fontMedium = applicationFonts.mediumFont();
        fontRegular = applicationFonts.regularFont();
        currentLocTv.setTypeface(fontRegular);
        currentLocTxtTv.setTypeface(fontMedium);
        searchResultTxtTv.setTypeface(fontMedium);
        noResultTv.setTypeface(fontMedium);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 , this)
                .addApi(Places.GEO_DATA_API)
                .build();

        if(getIntent().getExtras()!=null)
        {
            String comingFrom= getIntent().getExtras().getString(COMING_FROM);
            presenter.setComingFrom(comingFrom);
        }

        presenter.start();


        if (addressList_Lv != null)
        {
            mAdapter= new ArrayAdapter<>(SearchLocationAct.this, android.R.layout.simple_list_item_1, mAddressList);
            addressList_Lv.setAdapter(mAdapter);

            addressList_Lv.setOnItemClickListener((parent, view, position, id) -> {

                AutocompleteInfo info = (AutocompleteInfo) parent.getAdapter().getItem(position);

                presenter.getAddressDetail(info.getId(),null);
            });
        }

        placeAdapter = new PlaceAdapter(this);
        autoCompleteAdapter = new PlaceAutoCompleteAdapter(this);
        searchesRv.setHasFixedSize(true);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchesRv.setLayoutManager(mlayoutManager);
        searchesRv.setItemAnimator(new DefaultItemAnimator());
     //   searchesRv.setAdapter(autoCompleteAdapter);
        searchesRv.setAdapter(placeAdapter);
        searchesRv.setNestedScrollingEnabled(false);


        locationSearch_Et.setOnTouchListener((view, motionEvent) -> {
            showlist();
            return false;
        });

        locationSearch_Et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if (locationSearch_Et.getText().equals("") || s.length()<=0 || s.toString().equals("")|| s.toString().equals(" "))
                {
                    searchResultTxtTv.setVisibility(View.GONE);
                    searchesRv.setVisibility(View.GONE);

                }
                else{
                    searchResultTxtTv.setVisibility(View.VISIBLE);
                    searchesRv.setVisibility(View.VISIBLE);


                }


                if (!s.equals("") && mGoogleApiClient.isConnected())
                {
                    //autoCompleteAdapter.getFilter().filter(s.toString());
                    placeAdapter.getFilter().filter(s.toString());


                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                Utility.printLog(":text is "+locationSearch_Et.getText().toString());
                if (locationSearch_Et.getText().equals("") || editable.length()<=0 || editable.toString().equals("")|| editable.toString().equals(" "))
                {
                    searchResultTxtTv.setVisibility(View.GONE);
                    searchesRv.setVisibility(View.GONE);

                }
            }
        });
    }

    public void setAddress(PlaceAutoCompleteModel placeAutoCompleteModel, String address)
    {
        presenter.getAddressDetail(placeAutoCompleteModel.getId(),address);
      /*  GeoDataClient mGeoDataClient= Places.getGeoDataClient(this,null);
        mGeoDataClient.getPlaceById(placeAutoCompleteModel.getId()).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    Utility.printLog( "Place found: " + myPlace.getName()+" " + myPlace.getLatLng().longitude);
                    places.release();
                } else {
                    Utility.printLog( "Place not found.");
                }
            }
        });*/

    }
    private void showlist() {
        emptyRl.setVisibility(View.GONE);
        listLl.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyResult() {
        Utility.hideSoftKeyboard(this);
        emptyRl.setVisibility(View.VISIBLE);
        listLl.setVisibility(View.GONE);
    }

    @Override
    public void showGpsAlert(Status status) {
        try
        {

            status.startResolutionForResult(this, REQUEST_CHECK_SETTINGS);
        }
        catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addressList(List<AutocompleteInfo> list)
    { }

    @Override
    public void onError(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void startHome()
    {

        Intent intent=new Intent(SearchLocationAct.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(progressBar!=null)
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void stopAct() {
        onBackPressed();
    }

    @Override
    public void showDefaultMap() {

        addressList_Lv.setVisibility(View.GONE);
    }

    @Override
    public void locationDataSet()
    {
        Utility.hideSoftKeyboard(this);
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void sendSuccessData(double lat, double longi,String address)
    {
        Intent intent=new Intent();
        intent.putExtra("lat",lat);
        intent.putExtra("longi",longi);
        intent.putExtra("address",address);
        setResult(Activity.RESULT_OK, intent);
        finish();


    }

    @Override
    public void setCurrentLoc(String address)
    {
        if(address!=null && currentLocTv!=null)
        currentLocTv.setText(address);
    }

    @Override
    public void clearSearch() {
        searchResultTxtTv.setVisibility(View.GONE);
        searchesRv.setVisibility(View.GONE);
        locationSearch_Et.setText("");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    /**
     * <P>calling clear method to clear components.</P>
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    @OnClick({R.id.cancelTv,R.id.currentLocRl,R.id.backIv})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelTv:
                showlist();
                presenter.clearSearch();
              break;

              case R.id.currentLocRl:
                  mPerMissionForLocation();
              break;

            case R.id.backIv:
                if(progressBar.getVisibility()==View.VISIBLE)
                {
                    return;
                }
                presenter.stop();
                break;

            default:
                break;
        }
    }

    /**
     * <h>mperMissionForLocation</h>
     * to check for run time permission for location if post marshmellow
     * else directly call location API
     */

    private void mPerMissionForLocation()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !(checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED))
        {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_FOR_LOCATION);
        } else
        {

            presenter.getCurrentAddress();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_FOR_LOCATION)
        {
            if (grantResults!=null && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                presenter.getCurrentAddress();

            } else
            {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION))
                        {
                            if (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                            {
                                ActivityCompat.requestPermissions(SearchLocationAct.this, new
                                        String[]{ACCESS_FINE_LOCATION}, REQUEST_FOR_LOCATION);
                            }
                        } else
                        {
                            showMessageOKCancel(getString(R.string.locationPermission),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                                intent.setData(uri);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    }



            }
        }
    }

    /**
     * <h>showMessageOKCancel</h>
     * showMessage if permission not granted and to open settings of device
     * @param message input the message string to show body for dialog
     * @param okListener input for dialog
     */
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SearchLocationAct.this)
                .setMessage(message).setPositiveButton(getString(R.string.ok), okListener)
                .setNegativeButton(getString(R.string.cancel), null).create().show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
