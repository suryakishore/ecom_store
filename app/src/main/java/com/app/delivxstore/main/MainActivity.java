package com.app.delivxstore.main;

import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.TWENTY_THREE;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.ActivityMainBinding;
import com.app.delivxstore.databinding.NavHeaderMainBinding;
import com.app.delivxstore.main.history.HistoryFragment;
import com.app.delivxstore.main.home.HomeFragment;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;
import com.app.delivxstore.main.inventory.InventoryFragment;
import com.app.delivxstore.main.language.LanguageFragment;
import com.app.delivxstore.main.notification.NotificationFragment;
import com.app.delivxstore.main.profile.ProfileFragment;
import com.app.delivxstore.main.store_filter.DriverAdapter;
import com.app.delivxstore.main.store_filter.StoreAdapter;
import com.app.delivxstore.main.store_filter.StoreDetails;
import com.app.delivxstore.main.wallet.WalletFragment;
import com.app.delivxstore.main.wallet.payment.PaymentActivity;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.boarding.login.EcomLoginActivity;
import com.app.ecomstore.util.EcomUtil;
import com.google.android.material.navigation.NavigationView;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * <p>This class is used to show the navigation drawer with respective fragments.</p>
 */
public class MainActivity extends DaggerAppCompatActivity implements
    MainActivityContract.ViewOperations, NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {
  @Inject
  HomeFragment homeFragment;
  @Inject
  LanguageFragment languageFragment;
  @Inject
  InventoryFragment inventaryFragment;
  @Inject
  WalletFragment walletFragment;
  @Inject
  HistoryFragment historyFragment;
  @Inject
  NotificationFragment notificationFragment;
  @Inject
  MainActivityContract.PresenterOperations presenter;
  private ActivityMainBinding mActivityMainBinding;
  private TextView tvName, tvCityName, tvRole;
  private ImageView ivProfile, ivClose;
  private StoreAdapter mStoreAdapter;
  private DriverAdapter mDriverAdapter;
  private Fragment mFragment;
  private boolean mIsCityLogin, mIsMobileScreen = false, mIsFromHome = true;
  private NavHeaderMainBinding mNavBind;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Utility.RtlConversion(this, presenter.getLanguage());
    mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    setHeader();
    mActivityMainBinding.appBarLayout.toolbar.setTitle("");
    //presenter.getDriverList();
    mStoreAdapter = new StoreAdapter();
    //presenter.getStoreList();
    //initView();
    mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.VISIBLE);
    presenter.checkScreenSize();
    setSupportActionBar(mActivityMainBinding.appBarLayout.toolbar);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
        mActivityMainBinding.drawerLayout, mActivityMainBinding.appBarLayout.toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        presenter.onDrawerOpen();
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        mActivityMainBinding.appBarLayout.ivFilter.setImageResource(R.drawable.ic_filter);
        mActivityMainBinding.appBarLayout.ivDriver.setImageResource(R.drawable.ic_driver);
      }
    };
    mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
    mActivityMainBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
        Gravity.END);
    toggle.syncState();
    mActivityMainBinding.navView.setNavigationItemSelectedListener(this);
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction()
        .replace(R.id.frameContainer, homeFragment)
        .commit();
    mActivityMainBinding.appBarLayout.tvTitle.setText(getString(R.string.orders));
    presenter.getVersion();
    setClicksToViews();
  }

  /**
   * <h>phone view</h>
   * <p>This method is used to show the phone view with respective actionbar icons</p>
   */
  private void showPhoneView() {
    mIsMobileScreen = true;
    mActivityMainBinding.appBarLayout.ivFilter.setVisibility(View.GONE);
    mActivityMainBinding.appBarLayout.ivDriver.setVisibility(View.GONE);
    mActivityMainBinding.appBarLayout.tvTitle.setMaxEms(TWENTY_THREE);
    if (!mIsCityLogin && !mIsFromHome) {
      mActivityMainBinding.appBarLayout.ivFilter.setVisibility(View.GONE);
    }
    if (presenter.getDriverType() == TWO) {
    } else if ((presenter.getForceAccept() == ZERO
        && presenter.getAutoDispatch() == ZERO) || (
        presenter.getForceAccept() == ZERO
            && presenter.getAutoDispatch() == ONE) || (
        presenter.getForceAccept() == ONE
            && presenter.getAutoDispatch() == ONE)) {
      mActivityMainBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
          Gravity.END);
    }
  }

  /**
   * <h>show tablet view</h>
   * <p>This method is used to show the tablet view with respective action bar icons</p>
   */
  private void showTabletView() {
    mActivityMainBinding.appBarLayout.ivDriver.setVisibility(
        mIsCityLogin ? View.VISIBLE : View.VISIBLE);
    mActivityMainBinding.appBarLayout.ivFilter.setVisibility(
        mIsCityLogin ? View.VISIBLE : View.GONE);

    if ((presenter.getForceAccept() == ZERO
        && presenter.getAutoDispatch() == ZERO) || (
        presenter.getForceAccept() == ZERO
            && presenter.getAutoDispatch() == ONE) || (
        presenter.getForceAccept() == ONE
            && presenter.getAutoDispatch() == ONE)) {
      mActivityMainBinding.appBarLayout.ivDriver.setVisibility(View.GONE);
      mActivityMainBinding.viewHeader.ivHeaderDriver.setVisibility(View.GONE);
      mActivityMainBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
          Gravity.END);
    }
  }

  /**
   * <h>initialization of views</h>
   * <p>This method is used to initialize the recyclerview of store and driver. </p>
   */
  public void initView() {
    mActivityMainBinding.viewFilter.rvStore.setLayoutManager(new LinearLayoutManager(this));
    mActivityMainBinding.viewFilter.rvStore.setAdapter(mStoreAdapter);
    mDriverAdapter = new DriverAdapter();
    mActivityMainBinding.viewFilter.rvDriver.setLayoutManager(new LinearLayoutManager(this));
    mActivityMainBinding.viewFilter.rvDriver.setAdapter(mDriverAdapter);
  }

  /**
   * set on click to views
   */
  private void setClicksToViews() {
    mActivityMainBinding.appBarLayout.ivFilter.setOnClickListener(this::onClick);
    mActivityMainBinding.viewHeader.ivHeaderFilter.setOnClickListener(this::onClick);
    mActivityMainBinding.viewHeader.ivClear.setOnClickListener(this::onClick);
    mActivityMainBinding.appBarLayout.ivFilterForMobile.setOnClickListener(this::onClick);
    mActivityMainBinding.viewHeader.ivHeaderDriver.setOnClickListener(this::onClick);
    mActivityMainBinding.appBarLayout.ivDriver.setOnClickListener(this::onClick);
    mActivityMainBinding.appBarLayout.ivBack.setOnClickListener(this::onClick);
    mActivityMainBinding.appBarLayout.ivStartDate.setOnClickListener(this::onClick);
  }

  @Override
  public void onBackPressed() {
    if (mActivityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
      mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
    } else if (mActivityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.END)) {
      mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.END);
    } else {
      super.onBackPressed();
    }
  }

  /**
   * <h>onPause</h>
   * <p>this is one of the activity life cycle method.this method is used to rotate the device
   * tablet device in landscape mode.</p>
   */
  @Override
  protected void onPause() {
    super.onPause();
    if (android.os.Build.VERSION.SDK_INT >= 25 && Utility.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
  }

  /**
   * <h>onResume</h>
   * <p>this is one of the activity life cycle method.this method is used to rotate the device
   * tablet device in landscape mode.</p>
   */
  @Override
  protected void onResume() {
    super.onResume();
    if (android.os.Build.VERSION.SDK_INT >= 25 && Utility.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
  }

  /**
   * <h>onlick listener</h>
   * <p>This method is used to put the onclick listener for the registered views for clicking.</p>
   *
   * @param view this parameter represents the view object by using this we can find out the id of
   *             the view.
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.ivHeaderFilter:
      case R.id.ivFilter:
        showStoreDetails();
        break;
      case R.id.ivBack:
        EcomUtil.printLog("exe" + "ivBack");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.frameContainer, homeFragment)
            .commit();
        mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.VISIBLE);
        setHeader();
        break;
      case R.id.iv_startDate:
        if (Utility.isTablet(this)) {
          //  historyFragment.onClickFilter();
        } else {
          historyFragment.onClickFilter();
        }
        break;
      case R.id.ivClear:
        mActivityMainBinding.drawerLayout.closeDrawer(Gravity.END);
        break;
      case R.id.ivMobileFilter:
        if (mIsCityLogin) {
          showStoreDetails();
        } else {
          showDriverDetails();
        }
        break;
      case R.id.ivHeaderDriver:
      case R.id.ivDriver:
        Log.d("exe", "iv_driver");
        showDriverDetails();
        break;
    }
  }

  /**
   * <h>show store details</h>
   * <p>This method is used to show the stores list from the navigation drawer.</p>
   */
  private void showStoreDetails() {
    mActivityMainBinding.drawerLayout.openDrawer(Gravity.END);
    mActivityMainBinding.viewFilter.tvListHeader.setText(R.string.selectStore);
    presenter.getStoreList();
    mActivityMainBinding.viewFilter.rvDriver.setVisibility(View.GONE);
    mActivityMainBinding.viewFilter.rvStore.setVisibility(View.VISIBLE);
    mActivityMainBinding.appBarLayout.ivFilter.setImageResource(R.drawable.ic_filter_blue);
    mActivityMainBinding.viewHeader.ivHeaderFilter.setImageResource(R.drawable.ic_filter_blue);
    mActivityMainBinding.viewHeader.ivHeaderDriver.setImageResource(R.drawable.ic_driver);
    mActivityMainBinding.appBarLayout.ivDriver.setImageResource(R.drawable.ic_driver);
  }

  /**
   * <h>show driver details</h>
   * <p>This method is used to show the driver list from the navigation drawer.</p>
   */
  private void showDriverDetails() {
    mActivityMainBinding.drawerLayout.openDrawer(Gravity.END);
    mActivityMainBinding.viewFilter.tvListHeader.setText(R.string.drivers);
    presenter.getDriverList();
    mActivityMainBinding.viewFilter.rvStore.setVisibility(View.GONE);
    mActivityMainBinding.viewFilter.rvDriver.setVisibility(View.VISIBLE);
    mActivityMainBinding.appBarLayout.ivFilter.setImageResource(R.drawable.ic_filter);
    mActivityMainBinding.appBarLayout.ivDriver.setImageResource(R.drawable.ic_driver_on);
    mActivityMainBinding.viewHeader.ivHeaderFilter.setImageResource(R.drawable.ic_filter);
    mActivityMainBinding.viewHeader.ivHeaderDriver.setImageResource(R.drawable.ic_driver_on);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  /**
   * used to show the header title indicate store name
   */
  private void setHeader() {
    mActivityMainBinding.appBarLayout.tvTitle.setText(presenter.getFranchiseName());
    mActivityMainBinding.appBarLayout.tvTitle.setVisibility(View.VISIBLE);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    FragmentManager fragmentManager = getSupportFragmentManager();
    switch (id) {
      case R.id.navHome:
        mActivityMainBinding.viewHeader.ivHeaderDriver.setVisibility(View.VISIBLE);
        mActivityMainBinding.appBarLayout.ivStartDate.setVisibility(View.GONE);
        mActivityMainBinding.viewHeader.ivHeaderFilter.setVisibility(
            mIsCityLogin ? View.VISIBLE : View.INVISIBLE);
        mIsFromHome = true;
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.VISIBLE);
        mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.GONE);
        if (Utility.isTablet(this)) {
          showTabletView();
        } else {
          showPhoneView();
        }
        fragmentManager.beginTransaction()
            .replace(R.id.frameContainer, homeFragment)
            .commit();
        setHeader();
        break;
      case R.id.navOrderHistory:
        mActivityMainBinding.appBarLayout.tvTitle1.setText(
            getResources().getString(R.string.history));
        mActivityMainBinding.appBarLayout.ivStartDate.setVisibility(View.VISIBLE);
        mActivityMainBinding.viewHeader.ivHeaderDriver.setVisibility(View.INVISIBLE);
        mActivityMainBinding.viewHeader.ivHeaderFilter.setVisibility(View.INVISIBLE);
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.VISIBLE);
        mIsFromHome = false;
        mFragment = historyFragment;
        fragmentManager.beginTransaction()
            .replace(R.id.frameContainer, mFragment)
            .commit();
        break;
      case R.id.navChangeLang:
        mActivityMainBinding.appBarLayout.tvTitle1.setText(
            getResources().getString(R.string.changeLang));
        mIsFromHome = false;
        mActivityMainBinding.appBarLayout.ivStartDate.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.VISIBLE);
        if (Utility.isTablet(this)) {
          mFragment = languageFragment;
          fragmentManager.beginTransaction()
              .replace(R.id.frameContainer, mFragment)
              .commit();
        } else {
          mFragment = languageFragment;
          fragmentManager.beginTransaction()
              .replace(R.id.frameContainer, mFragment)
              .commit();
        }
        break;
      case R.id.navNotification:
        mActivityMainBinding.appBarLayout.tvTitle1.setText(
            getResources().getString(R.string.notifications));
        mIsFromHome = false;
        mActivityMainBinding.appBarLayout.ivStartDate.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.VISIBLE);
        if (Utility.isTablet(this)) {
          mFragment = notificationFragment;
          fragmentManager.beginTransaction()
              .replace(R.id.frameContainer, mFragment)
              .commit();
        } else {
          mFragment = notificationFragment;
          fragmentManager.beginTransaction()
              .replace(R.id.frameContainer, mFragment)
              .commit();
        }
        break;
      case R.id.navPayment:
        mFragment = walletFragment;
        mIsFromHome = false;
        mActivityMainBinding.appBarLayout.ivStartDate.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.tvTitle1.setText(
            getResources().getString(R.string.wallet));
        mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.GONE);
        mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction()
            .replace(R.id.frameContainer, mFragment)
            .commit();
        break;
      case R.id.navBankDetails:
        mActivityMainBinding.appBarLayout.tvTitle.setText(
            getResources().getString(R.string.bankDetails));
        mIsFromHome = false;
        showPhoneView();
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
        break;
    }
    mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  /**
   * used to set the store list
   *
   * @param storeDetails store details
   */
  public void setArryList(ArrayList<StoreDetails> storeDetails) {
    mStoreAdapter.setArrayList(storeDetails);
    mStoreAdapter.notifyDataSetChanged();
  }

  /**
   * used to set the driver list.
   *
   * @param driverList driver details.
   */
  public void setDriverList(ArrayList<DriverData> driverList) {
    Utility.printLog("DriverDataAdded" + "List" + driverList.size());
    mDriverAdapter.setDriverList(driverList, this);
    mDriverAdapter.notifyDataSetChanged();
  }

  /**
   * publish selected store.
   *
   * @param storeDetails store details.
   */
  public void publishSelectedStore(StoreDetails storeDetails) {
    mActivityMainBinding.drawerLayout.closeDrawer(Gravity.END);
    presenter.publishStoreName(storeDetails);
  }

  @Override
  public void setView(boolean tablet, boolean isCityLogin) {
    View header = mActivityMainBinding.navView.getHeaderView(0);
    mNavBind = NavHeaderMainBinding.bind(header);
    tvName = header.findViewById(R.id.textViewDriverRowDriverName);
    tvCityName = header.findViewById(R.id.tvCityName);
    tvRole = header.findViewById(R.id.tvRoleName);
    ivProfile = header.findViewById(R.id.imageViewDriverRowDriverImage);
    ivClose = header.findViewById(R.id.ivClose);
    ivClose.setOnClickListener(
        v -> mActivityMainBinding.drawerLayout.closeDrawer(mActivityMainBinding.navView));
    header.setOnClickListener(view -> {
      mActivityMainBinding.appBarLayout.tvTitle1.setText(getResources().getString(R.string.profile));
      mActivityMainBinding.appBarLayout.appBarLayout.setVisibility(View.GONE);
      mActivityMainBinding.appBarLayout.cvToolbar.setVisibility(View.VISIBLE);
      showPhoneView();
      mActivityMainBinding.drawerLayout.closeDrawer(mActivityMainBinding.navView);
      ProfileFragment profileFragment = new ProfileFragment();
      FragmentManager fragmentManager = getSupportFragmentManager();
      fragmentManager.beginTransaction()
          .replace(R.id.frameContainer, profileFragment).commit();
    });
    this.mIsCityLogin = isCityLogin;
    if (Utility.isTablet(this)) {
      showTabletView();
    }
  }

  @Override
  public void setProfileDetails(String storeName, String name, String role) {
    String cityName = presenter.getCityName();
    if (name != null && !name.isEmpty()) {
      tvName.setText(name);
    }
    if (storeName != null
        && !storeName.isEmpty()) {
      cityName = String.format("%s, %s", storeName, cityName);
    }
    tvCityName.setText(cityName);
    tvRole.setText(String.format("%s:%s", getResources().getString(R.string.role), role));
  }

  @Override
  public void setVersionText(String version) {
    mActivityMainBinding.tvVersion.setText(
        String.format("%s: %s", getString(R.string.version), version));
  }

  @Override
  public void startLogin() {
    startActivity(new Intent(this, EcomLoginActivity.class));
    finish();
  }
}
