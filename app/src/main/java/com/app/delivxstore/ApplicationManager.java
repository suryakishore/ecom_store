package com.app.delivxstore;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import com.app.delivxstore.dagger.AppComponent;
import com.app.delivxstore.dagger.DaggerAppComponent;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.language.model.LanguageList;
import com.app.delivxstore.managers.mqtt.AccountGeneral;
import com.app.delivxstore.managers.mqtt.MQTTManager;
import com.app.delivxstore.utility.Utility;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/*maintains global application state and initialize
 * classes used globally in entire application*/
public class ApplicationManager extends DaggerApplication {
  public static final String TAG = String.format("%s ", ApplicationManager.class.getSimpleName());
  static ApplicationManager applicationManager;
  private static AccountManager mAccountManager;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  MQTTManager mqttManager;

  public static ApplicationManager getInstance() {
    return applicationManager;
  }

  @Override
  public void onLowMemory() {
    System.gc();
    super.onLowMemory();
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
    appComponent.inject(this);
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    applicationManager = this;
    mAccountManager = AccountManager.get(getApplicationContext());
    connectMQTT();
    RxJavaPlugins.setErrorHandler(throwable -> {
    });
    Utility.printLog(" preferenceHelperDataSource.getLanguageSettings() " +
        preferenceHelperDataSource.getLanguageSettings());
    if (preferenceHelperDataSource.getLanguageSettings() == null) {
      preferenceHelperDataSource.setLanguageSettings(new LanguageList());
    } else {
      Utility.setLocale(preferenceHelperDataSource.getLanguageSettings().getLanguageCode(), this);
    }
  }

  /**
   * <h>connect mqtt</h>
   * <p>This method is used to connect to mqtt when the user is logged in with the user manager
   * id.as soon as the mqtt connection is successful
   * the data will be emitted from mqtt.</p>
   */
  public void connectMQTT() {
    if (preferenceHelperDataSource.isLoggedIn()) {
      mqttManager.createMQttConnection(
          String.format("%s%s", preferenceHelperDataSource.getManagerID(),
              preferenceHelperDataSource.getDeviceId()));
    }
    Utility.printLog("is mqtt connected: " + mqttManager.isMQTTConnected());
  }

  /**
   * <h>disconnect mqtt</h>
   * <p>This method is used to disconnect the mqtt.if this is successful the mqtt messages will
   * not
   * get emiited for this particular login manager id.</p>
   */
  public void disconnectMqtt() {
    //  mqttManager.disconnect(preferenceHelperDataSource.getDriverChannel());
    mqttManager.disconnect(preferenceHelperDataSource.getManagerChannel());
    //   mqttManager.disconnect(preferenceHelperDataSource.getSessionChannel());
  }

  /*set the auth token by creating the account manager with the account*/
  public void setAuthToken(String emailID, String password, String authToken) {
    Utility.printLog(TAG + "Tokens parameters: " + emailID + "\n" + password + "\n" + authToken);
    Account account = new Account(emailID, AccountGeneral.ACCOUNT_TYPE);
    mAccountManager.addAccountExplicitly(account, password, null);
    mAccountManager.setAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, authToken);
  }

  /*returns the auth token from the created account*/
  public String getAuthToken(String emailID) {
    Account[] account = mAccountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
    List<Account> accounts = Arrays.asList(account);
    Utility.printLog(TAG + "auth token from size " + accounts.size() + " " + emailID);
    if (accounts.size() > 0) {
      for (int i = 0; i < accounts.size(); i++) {
        Utility.printLog(TAG + "auth token from size " + accounts.get(i).name);
        if (accounts.get(i).name.equals(emailID)) {
          return mAccountManager.peekAuthToken(accounts.get(i),
              AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
        } else {
          removeAccount(accounts.get(i).name);
        }
      }
    }
    return null;
  }

  /*remove the account stored */
  public void removeAccount(String emailID) {
    Account[] account = mAccountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
    List<Account> accounts = Arrays.asList(account);
    Utility.printLog(TAG + "auth token from size " + accounts.size() + " " + emailID);
    if (accounts.size() > 0) {
      for (int i = 0; i < accounts.size(); i++) {
        if (accounts.get(i).name.equals(emailID)) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Utility.printLog(
                "account removed " + mAccountManager.removeAccountExplicitly(accounts.get(i)));
          }
        }
      }
    }
  }
}
