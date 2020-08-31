package com.app.delivxstore.splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.MainActivity;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.boarding.login.EcomLoginActivity;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

/*
 * <p>This class is used to provide the Splash screen.</p>
 * @author 3Embed
 *@since 28-2-18
 */
public class SplashActivity extends DaggerAppCompatActivity implements
    SplashContract.ViewOperations {
  @Inject
  SplashContract.PresenterOperations presenter;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Utility.RtlConversion(this, preferenceHelperDataSource.getLanguage());
    setContentView(R.layout.activity_splash);
    Utility.setLocale(preferenceHelperDataSource.getLanguage(), this);
    if (!Utility.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    presenter.checkScreenSize();
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (android.os.Build.VERSION.SDK_INT >= 27 && !Utility.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (android.os.Build.VERSION.SDK_INT >= 27 && !Utility.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }
    new Handler().postDelayed(() -> {
      Intent intent = new Intent(SplashActivity.this,
          preferenceHelperDataSource.isLoggedIn() ? MainActivity.class : EcomLoginActivity.class);
      startActivity(intent);
      finish();
    }, 2000);
  }

  @Override
  public void setView(boolean tablet) {
//        if(tablet){
//            ivBackground.setBackgroundResource(R.drawable.splash_screen);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }else {
//            ivBackground.setBackgroundResource(R.drawable.splash_screen);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
  }
}
