package com.app.delivxstore.data.source;

import android.content.Context;
import com.app.delivxstore.main.language.model.LanguageList;

/**
 * Created by DELL on 21-12-2017.
 */
public interface PreferenceHelperDataSource {
  /**
   * <h2>getStoreId</h2>
   * This method is used to get force accept enabled or not
   */
  String getForceAcceptEnabled();

  /**
   * <h2>setForceAcceptEnabled</h2>
   * This method is used to set force accept enabled or not
   *
   * @param forceAccept forceAccept
   */
  void setForceAcceptEnabled(String forceAccept);

  /**
   * <h2>getSynching</h2>
   * This method is used to get the synching
   */
  boolean getSynching();

  /**
   * <h2>isForDataWaiting</h2>
   * This method is used to set the progressbar when the data is coming through mqtt when the
   * user is logged in for the first time
   *
   * @param synching synching
   */
  void setSynching(boolean synching);

  double getCurrentLat();

  void setCurrentLat(double lat);

  double getCurrentLong();

  void setCurrentLong(double longi);

  /**
   * <h2>getStoreId</h2>
   * This method is used to get the StoreId
   */
  String getStoreId();

  /**
   * <h2>setStoreId</h2>
   * This method is used to set the url
   *
   * @param id id
   */
  void setStoreId(String id);

  /**
   * <h2>setGoogleMapKey</h2>
   * This method is used to set the url
   *
   * @param mapKey id
   */
  void setGoogleMapKey(String mapKey);

  /**
   * <h2>getStoreId</h2>
   * This method is used to get the StoreId
   */
  String getGoogleMApKey();

  /**
   * <h2>getStoreLoginId</h2>
   * This method is used to get the StoreId
   */
  String getStoreLoginId();

  /**
   * <h2>setStoreLoginId</h2>
   * This method is used to set the url
   *
   * @param id id
   */
  void setStoreLoginId(String id);

  /**
   * <h2>getPushTopic</h2>
   * This method is used to get the pushTopic
   */
  String getPushTopic();

  /**
   * <h2>setPushTopic</h2>
   * This method is used to set the url
   *
   * @param pushTopic pushTopic
   */
  void setPushTopic(String pushTopic);

  /**
   * <h2>getPushTopic</h2>
   * This method is used to get the pushTopic
   */
  String getPushCityTopic();

  /**
   * <h2>setPushTopic</h2>
   * This method is used to set the url
   *
   * @param pushTopic pushTopic
   */
  void setPushCityTopic(String pushTopic);

  /**
   * <h2>setPushTopic</h2>
   * This method is used to set the url
   *
   * @param pushTopic pushTopic
   */
  void setFcmTopic(String pushTopic);

  /**
   * <h2>getPushTopic</h2>
   * This method is used to get the pushTopic
   */
  String getFcmyTopic();

  /**
   * <h2>getProfilePic</h2>
   * This method is used to get the url
   */
  String getProfilePic();

  /**
   * <h2>setProfilePic</h2>
   * This method is used to set the url
   *
   * @param url url
   */
  void setProfilePic(String url);

  int getDriverType();

  void setDriverType(int driverType);

  int getPackage();

  void setPackage(int driverType);

  String getStoreTopic();

  void setStoreTopic(String storeTopic);

  /**
   * <h2>getStoreName</h2>
   * This method is used to get the Store Name
   */
  String getFranchiseName();

  void setFranchiseName(String franchiseName);

  String getCityId();

  void setCityId(String cityId);

  void setIsCityLogin(boolean cityLogin);

  boolean isCityLogin();

  String getCityName();

  void setCityName(String cityName);

  int getStoreType();

  void setStoreType(int storeType);

  String getFranchiseId();

  void setFranchiseId(String franchiseId);

  String getUserType();

  void setUserType(String userType);

  String getStoreName();

  /**
   * <h2>setStoreName</h2>
   * This method is used to set the Store Name
   *
   * @param name name
   */
  void setStoreName(String name);

  /**
   * <h2>getMyName</h2>
   * This method is used to get the Driver Name
   */
  String getMyName();

  /**
   * <h2>setMyName</h2>
   * This method is used to set the Driver Name
   *
   * @param name name
   */
  void setMyName(String name);

  /**
   * <h2>getManagerID</h2>
   * This method is used to get the Manager ID
   */
  String getManagerID();

  /**
   * <h2>.setManagerID</h2>
   * This method is used to set the Manager ID
   *
   * @param id ID
   */
  void setManagerID(String id);

  /**
   * <h2>getManagerChannel</h2>
   * This method is used to get the channel
   */
  String getManagerChannel();

  /**
   * <h2>.setManagerChannel</h2>
   * This method is used to set the channel name
   *
   * @param chn channel
   */
  void setManagerChannel(String chn);

  /**
   * <h2>getDriverChannel</h2>
   * This method is used to get the driver channel
   */
  String getDriverChannel();

  /**
   * <h2>.setDriverChannel</h2>
   * This method is used to set the driver channel name
   *
   * @param chn channel
   */
  void setDriverChannel(String chn);

  /**
   * <h2>getSessionChannel</h2>
   * This method is used to get the session channel
   */
  String getSessionChannel();

  /**
   * <h2>.setSessionChannel</h2>
   * This method is used to set the session channel name
   *
   * @param chn channel
   */
  void setSessionChannel(String chn);

  /**
   * <h2>getMyEmail</h2>
   * This method is used to get the email
   */
  String getMyEmail();

  /**
   * <h2>setMyEmail</h2>
   * This method is used to set Email
   */
  void setMyEmail(String email);

  /**
   * <h2>getLanguage</h2>
   * This method is used to get the language
   */
  String getLanguage();

  /**
   * <h2>setLanguage</h2>
   * This method is used to set the language
   */
  void setLanguage(String language);

  /**
   * <h2>getPassword</h2>
   * This method is used to get the password
   */
  String getPassword();

  /**
   * <h2>setPassword</h2>
   * This method is used to set the password
   */
  void setPassword(String password);

  /**
   * <h2>getCurrencySymbol</h2>
   * This method is used to get the Currency Symbol
   */
  String getCurrencySymbol();

  void setCurrencySymbol(String symbol);

  /**
   * <h2>getRole</h2>
   * This method is used to get the role
   */
  String getRole();

  void setRole(String role);

  /**
   * <h2>getToken</h2>
   * This method is used to get the token
   */
  String getToken();

  void setToken(String token);

  /**
   * <h2>getDeviceId</h2>
   * This method is used to get the Device ID
   */
  String getDeviceId();

  /**
   * <h2>setDeviceId</h2>
   * This method is used to set the Device ID
   *
   * @param deviceId Device ID
   */
  void setDeviceId(String deviceId);

  /**
   * <h2>setIsLogin</h2>
   * This method is used to set if the user is logged in
   *
   * @param isLogin true if logged in else false
   */
  void setIsLogin(boolean isLogin);

  /**
   * <h2>isLoggedIn</h2>
   * This method is used to get if the user is logged in
   *
   * @return true if logged in else false
   */
  boolean isLoggedIn();

  /**
   * <h2>clearSharedPredf</h2>
   * This method is used to clear the shared prefernce
   */
  void clearSharedPredf();

  int getForceAccept();

  void setForceAccept(int forceAccept);

  int getAutoDispatch();

  void setAutoDispatch(int autoDispatch);

  void setEnableBankAccount(int enableBankAccount);

  int getEnableBankAccout();

  String getDefaultBankAccount();

  void setDefaultBankAccount(String defaultBankAccount);

  String getCountry();

  void setCountry(String country);

  String getCurrency();

  void setCurrency(String country);

  void loginResponse(String response);

  String getLoginResponse();

  /*extracts business type from shared preferences*/
  String getBusinessType();

  /*set business type*/
  void setBusinessType(String businessType);

  /*get strip country*/
  String getStripeCountry();

  /*set strip account country*/
  void setStripeCountry(String country);

  /*get strip currency*/
  String getStripeCurrency();

  /*set strip currency*/
  void setStripeCurrency(String defaultCurrency);

  /*extracts mobile number*/
  String getMobile();

  /*set mobile number*/
  void setMobile(String mobile);

  /*extracts bucket name*/
  String getBucketName();

  /*store bucket name*/
  void setBucketName(String bucket);

  /*extracts cognito id*/
  String getCognitoId();

  /*store cognito id*/
  void setCognitoId(String identityId);

  /*extracts aws region*/
  String getAwsRegion();

  /*store aws region*/
  void setAwsRegion(String region);

  /*extracts cognito token*/
  String getCognitoToken();

  /*store cognito token*/
  void setCognitoToken(String token);

  String getLinkedWith();

  void setLinkedWith(String linkedWith);

  /*extracts language setting from shared preferences */
  LanguageList getLanguageSettings();

  /*store language setting in shared preferences*/
  void setLanguageSettings(LanguageList languageSettings);

  String getState();

  String getCity();

  String getAddress(Context context);

  String getLanguageCode();

  void setLanguageCode(String languageCode);

  String getCategoryId();

  void setCategoryId(String categoryId);
}
