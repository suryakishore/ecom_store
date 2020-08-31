package com.app.delivxstore.data.source.local;

import static com.app.delivxstore.data.source.local.PreferenceKeys.KeysEntry.LANGUAGE_SETTINGS;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.language.model.LanguageList;
import com.google.gson.Gson;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by DELL on 21-12-2017.
 */
public class PreferencesHelper implements PreferenceHelperDataSource {
  private String PREF_NAME = "uflyStore";
  private SharedPreferences sharedPreferences = null;
  private Gson gson;
  private SharedPreferences.Editor editor;
  private Context mContext;

  @Inject
  public PreferencesHelper(Context context) {
    mContext = context;
    int PRIVATE_MODE = 0;
    if (Build.VERSION.SDK_INT >= 23) {
      String masterKeyAlias = null;
      try {
        masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
      try {
        sharedPreferences =
            EncryptedSharedPreferences.create(
                PREF_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    } else {
      sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }
    editor = sharedPreferences.edit();
    editor.apply();
    gson = new Gson();
    getAddress(mContext);
  }

  @Override
  public String getForceAcceptEnabled() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.FORCE_ACCEPT, null);
  }

  @Override
  public void setForceAcceptEnabled(String forceAccept) {
    editor.putString(PreferenceKeys.KeysEntry.FORCE_ACCEPT, forceAccept);
    editor.commit();
  }

  @Override
  public boolean getSynching() {
    return sharedPreferences.getBoolean(PreferenceKeys.KeysEntry.IS_FOR_SYNCHING, false);
  }

  @Override
  public void setSynching(boolean synching) {
    editor.putBoolean(PreferenceKeys.KeysEntry.IS_FOR_SYNCHING, synching);
    editor.commit();
  }

  @Override
  public double getCurrentLat() {
    return Double.parseDouble(sharedPreferences.getString(PreferenceKeys.KeysEntry.LATITUDE, "0"));
  }

  @Override
  public void setCurrentLat(double lat) {
    editor.putString(PreferenceKeys.KeysEntry.LATITUDE, String.valueOf(lat));
    editor.commit();
  }

  @Override
  public double getCurrentLong() {
    return Double.parseDouble(sharedPreferences.getString(PreferenceKeys.KeysEntry.LONGITUDE, "0"));
  }

  @Override
  public void setCurrentLong(double longi) {
    editor.putString(PreferenceKeys.KeysEntry.LONGITUDE, String.valueOf(longi));
    editor.commit();
  }

  @Override
  public String getStoreId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STORE_ID, "0");
  }

  @Override
  public void setStoreId(String id) {
    editor.putString(PreferenceKeys.KeysEntry.STORE_ID, id);
    editor.commit();
  }

  @Override
  public void setGoogleMapKey(String mapKey) {
    editor.putString(PreferenceKeys.KeysEntry.MAPKEY, mapKey);
    editor.commit();
  }

  @Override
  public String getGoogleMApKey() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.MAPKEY, null);
  }

  @Override
  public String getStoreLoginId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STORE_LOGIN_ID, "0");
  }

  @Override
  public void setStoreLoginId(String id) {
    editor.putString(PreferenceKeys.KeysEntry.STORE_LOGIN_ID, id);
    editor.commit();
  }

  @Override
  public String getPushTopic() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.PUSH_TOPIC, null);
  }

  @Override
  public void setPushTopic(String pushTopic) {
    editor.putString(PreferenceKeys.KeysEntry.PUSH_TOPIC, pushTopic);
    editor.commit();
  }

  @Override
  public String getPushCityTopic() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CITY_TOPIC, null);
  }

  @Override
  public void setPushCityTopic(String pushTopic) {
    editor.putString(PreferenceKeys.KeysEntry.CITY_TOPIC, pushTopic);
    editor.commit();
  }

  @Override
  public void setFcmTopic(String pushTopic) {
    editor.putString(PreferenceKeys.KeysEntry.FCM_TOPIC, pushTopic);
    editor.commit();
  }

  @Override
  public String getFcmyTopic() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.FCM_TOPIC, null);
  }

  @Override
  public String getProfilePic() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.PROFILE_PIC, null);
  }

  @Override
  public void setProfilePic(String url) {
    editor.putString(PreferenceKeys.KeysEntry.PROFILE_PIC, url);
    editor.commit();
  }

  @Override
  public String getStoreTopic() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STORE_TOPIC, null);
  }

  @Override
  public void setStoreTopic(String storeTopic) {
    editor.putString(PreferenceKeys.KeysEntry.STORE_TOPIC, storeTopic);
    editor.commit();
  }

  @Override
  public int getDriverType() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.DRIVER_TYPE, 0);
  }

  @Override
  public void setDriverType(int driverType) {
    editor.putInt(PreferenceKeys.KeysEntry.DRIVER_TYPE, driverType);
    editor.commit();
  }

  @Override
  public int getPackage() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.SEND_PACKAGE, 0);
  }

  @Override
  public void setPackage(int driverType) {
    editor.putInt(PreferenceKeys.KeysEntry.SEND_PACKAGE, driverType);
    editor.commit();
  }

  @Override
  public String getFranchiseName() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.FRANCHISE_NAME, "");
  }

  @Override
  public void setFranchiseName(String franchiseName) {
    editor.putString(PreferenceKeys.KeysEntry.FRANCHISE_NAME, franchiseName);
    editor.commit();
  }

  @Override
  public String getCityId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CITY_ID, "");
  }

  @Override
  public void setCityId(String cityId) {
    editor.putString(PreferenceKeys.KeysEntry.CITY_ID, cityId);
    editor.commit();
  }

  @Override
  public void setIsCityLogin(boolean cityLogin) {
    editor.putBoolean("IsCityLogin", cityLogin);
    editor.commit();
  }

  @Override
  public boolean isCityLogin() {
    return sharedPreferences.getBoolean("IsCityLogin", false);
  }

  @Override
  public String getCityName() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CITY_NAME, "");
  }

  @Override
  public void setCityName(String cityName) {
    editor.putString(PreferenceKeys.KeysEntry.CITY_NAME, cityName);
    editor.commit();
  }

  @Override
  public int getStoreType() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.STORE_TYPE, 0);
  }

  @Override
  public void setStoreType(int storeType) {
    editor.putInt(PreferenceKeys.KeysEntry.STORE_TYPE, storeType);
    editor.commit();
  }

  @Override
  public String getFranchiseId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.FRANCHISE_ID, "");
  }

  @Override
  public void setFranchiseId(String franchiseId) {
    editor.putString(PreferenceKeys.KeysEntry.FRANCHISE_ID, franchiseId);
    editor.commit();
  }

  @Override
  public String getUserType() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.USER_TYPE, "");
  }

  @Override
  public void setUserType(String userType) {
    editor.putString(PreferenceKeys.KeysEntry.USER_TYPE, userType);
    editor.commit();
  }

  @Override
  public String getStoreName() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STORE_NAME, null);
  }

  @Override
  public void setStoreName(String name) {
    editor.putString(PreferenceKeys.KeysEntry.STORE_NAME, name);
    editor.commit();
  }

  @Override
  public String getMyName() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.MY_NAME, null);
  }

  @Override
  public void setMyName(String name) {
    editor.putString(PreferenceKeys.KeysEntry.MY_NAME, name);
    editor.commit();
  }

  @Override
  public String getManagerID() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.MID, "");
  }

  @Override
  public void setManagerID(String id) {
    editor.putString(PreferenceKeys.KeysEntry.MID, id);
    editor.commit();
  }

  @Override
  public String getManagerChannel() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.MANAGER_MQTT_TOPIC, "");
  }

  @Override
  public void setManagerChannel(String chn) {
    editor.putString(PreferenceKeys.KeysEntry.MANAGER_MQTT_TOPIC, chn);
    editor.commit();
  }

  @Override
  public String getDriverChannel() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.DRIVER_MQTT_TOPIC, "");
  }

  @Override
  public void setDriverChannel(String chn) {
    editor.putString(PreferenceKeys.KeysEntry.DRIVER_MQTT_TOPIC, chn);
    editor.commit();
  }

  @Override
  public String getSessionChannel() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.SESSION_MQTT_TOPIC, "");
  }

  @Override
  public void setSessionChannel(String chn) {
    editor.putString(PreferenceKeys.KeysEntry.SESSION_MQTT_TOPIC, chn);
    editor.commit();
  }

  @Override
  public String getMyEmail() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.EMAIL, "");
  }

  @Override
  public void setMyEmail(String email) {
    editor.putString(PreferenceKeys.KeysEntry.EMAIL, email);
    editor.commit();
  }

  @Override
  public String getLanguage() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.LANGUAGE, "en");
  }

  @Override
  public void setLanguage(String language) {
    editor.putString(PreferenceKeys.KeysEntry.LANGUAGE, language);
    editor.commit();
  }

  @Override
  public String getPassword() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.PASSWORD, "");
  }

  @Override
  public void setPassword(String password) {
    editor.putString(PreferenceKeys.KeysEntry.PASSWORD, password);
    editor.commit();
  }

  @Override
  public String getCurrencySymbol() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CURRENCY_SYMBOL, "$");
  }

  @Override
  public void setCurrencySymbol(String symbol) {
    editor.putString(PreferenceKeys.KeysEntry.CURRENCY_SYMBOL, symbol);
    editor.commit();
  }

  @Override
  public String getRole() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.ROLE, "");
  }

  @Override
  public void setRole(String role) {
    editor.putString(PreferenceKeys.KeysEntry.ROLE, role);
    editor.commit();
  }

  @Override
  public String getToken() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.TOKEN, null);
  }

  @Override
  public void setToken(String token) {
    editor.putString(PreferenceKeys.KeysEntry.TOKEN, token);
    editor.commit();
  }

  @Override
  public String getDeviceId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.DEVICE_ID, null);
  }

  @Override
  public void setDeviceId(String deviceId) {
    editor.putString(PreferenceKeys.KeysEntry.DEVICE_ID, deviceId);
    editor.commit();
  }

  @Override
  public void setIsLogin(boolean isLogin) {
    editor.putBoolean(PreferenceKeys.KeysEntry.LOGGED_IN, isLogin);
    editor.commit();
  }

  @Override
  public boolean isLoggedIn() {
    return sharedPreferences.getBoolean(PreferenceKeys.KeysEntry.LOGGED_IN, false);
  }

  @Override
  public void clearSharedPredf() {
    editor.clear();
    editor.apply();
  }

  @Override
  public int getForceAccept() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.FORCED_ACCEPT, 0);
  }

  @Override
  public void setForceAccept(int forceAccept) {
    editor.putInt(PreferenceKeys.KeysEntry.FORCED_ACCEPT, forceAccept);
    editor.commit();
  }

  @Override
  public int getAutoDispatch() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.AUTO_DISPATCH, 0);
  }

  @Override
  public void setAutoDispatch(int autoDispatch) {
    editor.putInt(PreferenceKeys.KeysEntry.AUTO_DISPATCH, autoDispatch);
    editor.commit();
  }

  @Override
  public void setEnableBankAccount(int enableBankAccount) {
    editor.putInt(PreferenceKeys.KeysEntry.ENABLE_BANK_ACCOUNT, enableBankAccount);
    editor.commit();
  }

  @Override
  public int getEnableBankAccout() {
    return sharedPreferences.getInt(PreferenceKeys.KeysEntry.ENABLE_BANK_ACCOUNT, 0);
  }

  @Override
  public String getDefaultBankAccount() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.DEFAULT_BANK_ACCOUNT, null);
  }

  @Override
  public void setDefaultBankAccount(String defaultBankAccount) {
    editor.putString(PreferenceKeys.KeysEntry.DEFAULT_BANK_ACCOUNT, defaultBankAccount);
    editor.commit();
  }

  @Override
  public String getCountry() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.COUNTRY, null);
  }

  @Override
  public void setCountry(String country) {
    editor.putString(PreferenceKeys.KeysEntry.COUNTRY, country);
    editor.commit();
  }

  @Override
  public String getCurrency() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CURRENCY, null);
  }

  @Override
  public void setCurrency(String country) {
    editor.putString(PreferenceKeys.KeysEntry.CURRENCY, country);
    editor.commit();
  }

  @Override
  public void loginResponse(String response) {
    editor.putString(PreferenceKeys.KeysEntry.LOGIN_RESPONSE, response);
    editor.commit();
  }

  @Override
  public String getLoginResponse() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.LOGIN_RESPONSE, null);
  }

  @Override
  public String getLinkedWith() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.LINKED_WITH, null);
  }

  @Override
  public void setLinkedWith(String linkedWith) {
    editor.putString(PreferenceKeys.KeysEntry.LINKED_WITH, linkedWith);
    editor.commit();
  }

  @Override
  public String getBusinessType() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.BUSINESS_TYPE, "");
  }

  @Override
  public void setBusinessType(String businessType) {
    editor.putString(PreferenceKeys.KeysEntry.BUSINESS_TYPE, businessType);
    editor.commit();
  }

  @Override
  public String getStripeCountry() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STRIP_COUNTRY, "");
  }

  @Override
  public void setStripeCountry(String country) {
    editor.putString(PreferenceKeys.KeysEntry.STRIP_COUNTRY, country);
    editor.commit();
  }

  @Override
  public String getStripeCurrency() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STRIP_CURRENCY, "");
  }

  @Override
  public void setStripeCurrency(String defaultCurrency) {
    editor.putString(PreferenceKeys.KeysEntry.STRIP_CURRENCY, defaultCurrency);
    editor.commit();
  }

  @Override
  public String getMobile() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.MOBILE, "");
  }

  @Override
  public void setMobile(String mobile) {
    editor.putString(PreferenceKeys.KeysEntry.MOBILE, mobile);
    editor.commit();
  }

  @Override
  public String getBucketName() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.BUCKET, "");
  }

  @Override
  public void setBucketName(String bucket) {
    editor.putString(PreferenceKeys.KeysEntry.BUCKET, bucket);
    editor.commit();
  }

  @Override
  public String getCognitoId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.COGNITO_ID, "");
  }

  @Override
  public void setCognitoId(String identityId) {
    editor.putString(PreferenceKeys.KeysEntry.COGNITO_ID, identityId);
    editor.commit();
  }

  @Override
  public String getAwsRegion() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.REGION, "");
  }

  @Override
  public void setAwsRegion(String region) {
    editor.putString(PreferenceKeys.KeysEntry.REGION, region);
    editor.commit();
  }

  @Override
  public String getCognitoToken() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.COGNITO_TOKEN, "");
  }

  @Override
  public void setCognitoToken(String token) {
    editor.putString(PreferenceKeys.KeysEntry.COGNITO_TOKEN, token);
    editor.commit();
  }

  @Override
  public String getState() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.STATE, "");
  }

  @Override
  public String getCity() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CITY, "");
  }

  @Override
  public LanguageList getLanguageSettings() {
    String jsonString = sharedPreferences.getString(LANGUAGE_SETTINGS, "");
    return new Gson().fromJson(jsonString, LanguageList.class);
  }

  @Override
  public void setLanguageSettings(LanguageList languageSettings) {
    if (languageSettings != null) {
      String jsonString = new Gson().toJson(languageSettings);
      editor.putString(LANGUAGE_SETTINGS, jsonString);
      editor.commit();
    } else {
      editor.putString(LANGUAGE_SETTINGS, "");
      editor.commit();
    }
  }

  @Override
  public String getAddress(Context context) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      LocationManager lm = (LocationManager) context.getApplicationContext().getSystemService(
          Context.LOCATION_SERVICE);
      Geocoder geocoder = new Geocoder(context.getApplicationContext());
      for (String provider : lm.getAllProviders()) {
        @SuppressWarnings("ResourceType")
        Location location = lm.getLastKnownLocation(provider);
        if (location != null) {
          try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
              editor.putString(PreferenceKeys.KeysEntry.CITY, addresses.get(0).getLocality());
              editor.putString(PreferenceKeys.KeysEntry.COUNTRY, addresses.get(0).getCountryName());
              editor.putString(PreferenceKeys.KeysEntry.STATE, addresses.get(0).getAdminArea());
              editor.putString(PreferenceKeys.KeysEntry.LATITUDE,
                  String.valueOf(addresses.get(0).getLatitude()));
              editor.putString(PreferenceKeys.KeysEntry.LONGITUDE,
                  String.valueOf(addresses.get(0).getLongitude()));
              editor.commit();
              break;
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }

  @Override
  public String getLanguageCode() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.LAN_CODE, "");
  }

  @Override
  public void setLanguageCode(String languageCode) {
    editor.putString(PreferenceKeys.KeysEntry.LAN_CODE, languageCode);
    editor.commit();
  }

  @Override
  public String getCategoryId() {
    return sharedPreferences.getString(PreferenceKeys.KeysEntry.CATEGORY_ID, "");
  }

  @Override
  public void setCategoryId(String categoryId) {
    editor.putString(PreferenceKeys.KeysEntry.CATEGORY_ID, categoryId);
    editor.commit();
  }
}
