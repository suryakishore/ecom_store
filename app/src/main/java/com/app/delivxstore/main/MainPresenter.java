package com.app.delivxstore.main;

import static com.app.ecomstore.util.EcomConstants.AERIAL_PARNER;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.FOUR_NINE_EIGHT;
import static com.app.ecomstore.util.EcomConstants.STORE_PARNER;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.DriverData;
import com.app.delivxstore.main.profile.LogoutRequestData;
import com.app.delivxstore.main.store_filter.StoreDetails;
import com.app.delivxstore.main.store_filter.StoreModel;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RxLogOutObsever;
import com.app.delivxstore.observers.RxStoreObserver;
import com.app.delivxstore.utility.Utility;
import com.app.delivxstore.utility.VariableConstants;
import com.app.ecomstore.util.EcomConstants;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/**
 * presenter for main activity
 */
public class MainPresenter implements MainActivityContract.PresenterOperations {
  @Inject
  Context context;
  @Inject
  Utility utility;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  MainActivityContract.ViewOperations view;
  @Inject
  NetworkService networkService;
  @Inject
  RxLogOutObsever rxLogOutObsever;
  @Inject
  RxStoreObserver rxStoreObserver;
  @Inject
  Connectable rxConnectable;
  private Gson mGson = new Gson();
  private boolean mIslogout;
  private ArrayList<StoreDetails> mStoreDetails;

  @Inject
  public MainPresenter() {
  }

  /**
   * used to show the store name.
   *
   * @param storeDetails store details
   */
  public void publishStoreName(StoreDetails storeDetails) {
    preferenceHelperDataSource.setStoreId(storeDetails.getId() != null ? storeDetails.getId()
        : preferenceHelperDataSource.getStoreLoginId());
    rxConnectable.postData(storeDetails.getStoreName());
  }

  /**
   * used to get the list of driver details.
   */
  public void getDriverList() {
    rxLogOutObsever.subscribeOn(Schedulers.io());
    rxLogOutObsever.observeOn(AndroidSchedulers.mainThread());
    rxLogOutObsever.subscribeWith(new DisposableObserver<Boolean>() {
      @Override
      public void onNext(Boolean selectedValue) {
        if (selectedValue) {
          logoutOnClick();
        }
      }

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onComplete() {
      }
    });
    rxStoreObserver.subscribeOn(Schedulers.io());
    rxStoreObserver.observeOn(AndroidSchedulers.mainThread());
    rxStoreObserver.subscribeWith(new DisposableObserver<ArrayList<DriverData>>() {
      @Override
      public void onNext(ArrayList<DriverData> driverList) {
        view.setDriverList(driverList);
      }

      @Override
      public void onError(Throwable e) {
        Log.d("exe", "getDriverListonError" + e.getMessage());
      }

      @Override
      public void onComplete() {
      }
    });
  }

  @Override
  public void checkScreenSize() {
    view.setView(utility.isTablet(context), preferenceHelperDataSource.isCityLogin());
  }

  @Override
  public void onDrawerOpen() {
    view.setProfileDetails(preferenceHelperDataSource.getStoreName(),
        preferenceHelperDataSource.getMyName(), preferenceHelperDataSource.getRole());
  }

  @Override
  public void getVersion() {
    try {
      String currentVersion = context.getPackageManager().getPackageInfo(context.getPackageName(),
          0).versionName;
      view.setVersionText(currentVersion);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getCityName() {
    return preferenceHelperDataSource.getCityName();
  }

  @Override
  public String getLanguageCode() {
    return preferenceHelperDataSource.getLanguageSettings().getLanguageCode();
  }

  @Override
  public void logoutOnClick() {
    LogoutRequestData logoutRequestData = new LogoutRequestData();
    logoutRequestData.setIpAddress(Utility.getIpAddress(context));
    logoutRequestData.setLat(VariableConstants.CURRENT_ZONE_LAT);
    logoutRequestData.setLongi(VariableConstants.CURRENT_ZONE_LONGI);
    logoutRequestData.setCity(EcomConstants.CITY);
    logoutRequestData.setCountry(EcomConstants.COUNTRY);
    logoutRequestData.setDeviceMake("");
    logoutRequestData.setDeviceId(Utility.getDeviceId(context));
    logoutRequestData.setOsVersion(android.os.Build.VERSION.RELEASE);
    final Observable<Response<ResponseBody>> logout = networkService.logout(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        logoutRequestData
    );
    logout.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            try {
              Utility.printLog("logout : Code :" + value.code());
              switch (value.code()) {
                case SUCCESS:
                  mIslogout = true;
                  logout();
                  break;
                default:
                  JSONObject jsonObject = new JSONObject(value.errorBody().string());
                  break;
              }
            } catch (JSONException e) {
              Utility.printLog("logout : Catch :" + e.getMessage());
            } catch (IOException e) {
              Utility.printLog("logout : Catch :" + e.getMessage());
            }
          }

          @Override
          public void onError(Throwable e) {
            Utility.printLog("profile : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
          }
        });
  }

  /**
   * used to show the stores.
   */
  public void getStoreList() {
    if (mStoreDetails != null) {
      view.setArryList(mStoreDetails);
      return;
    }
    final Observable<Response<ResponseBody>> logout = networkService.getStoreList(
        preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getFranchiseId()
    );
    logout.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            int code = value.code();
            Log.d("exe", "getStoreList" + code);
            switch (code) {
              case SUCCESS:
                String response = "";
                try {
                  response = value.body().string();
                  Log.d("exe", "response" + response);
                  StoreModel storeModel = mGson.fromJson(response, StoreModel.class);
                  mStoreDetails = storeModel.getData();
                  if (mStoreDetails != null) {
                    view.setArryList(mStoreDetails);
                  }
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
              case FOUR_NINE_EIGHT:
                break;
              default:
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            Utility.printLog("profile : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
          }
        });
  }

  @Override
  public void logout() {
    if (mIslogout) {
      mIslogout = false;
      if (preferenceHelperDataSource.getPushTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getPushTopic());
      }
      if (preferenceHelperDataSource.getStoreTopic() != null) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(
            preferenceHelperDataSource.getStoreTopic());
      }
      preferenceHelperDataSource.clearSharedPredf();
      view.startLogin();
    }
  }

  @Override
  public String getFranchiseName() {
    if (preferenceHelperDataSource.getStoreName() != null
        && !preferenceHelperDataSource.getStoreName().isEmpty()) {
      return String.format("%s %s", preferenceHelperDataSource.getFranchiseName(),
          preferenceHelperDataSource.getStoreName());
    } else if (preferenceHelperDataSource.getMyName() != null
        && !preferenceHelperDataSource.getMyName().isEmpty()) {
      return preferenceHelperDataSource.getMyName();
    } else {
      return "";
    }
  }

  @Override
  public String getLanguage() {
    return preferenceHelperDataSource.getLanguageSettings().getLanguageCode();
  }

  @Override
  public int getAutoDispatch() {
    return preferenceHelperDataSource.getAutoDispatch();
  }

  @Override
  public int getForceAccept() {
    return preferenceHelperDataSource.getForceAccept();
  }

  @Override
  public int getDriverType() {
    return preferenceHelperDataSource.getDriverType();
  }

  @Override
  public String getStoreType() {
    switch (preferenceHelperDataSource.getUserType()) {
      case AERIAL_PARNER:
        return context.getResources().getString(R.string.aerialPartner);
      case STORE_PARNER:
        return context.getResources().getString(R.string.storeName);
      default:
        return context.getResources().getString(R.string.franchiseName);
    }
  }
}
