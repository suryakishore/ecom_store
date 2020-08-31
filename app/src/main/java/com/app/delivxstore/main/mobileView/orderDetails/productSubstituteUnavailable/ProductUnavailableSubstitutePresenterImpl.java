package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LAT;
import static com.app.delivxstore.utility.VariableConstants.CURRENT_ZONE_LONGI;
import static com.app.ecomstore.util.EcomConstants.ADMIN;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.ID;
import static com.app.ecomstore.util.EcomConstants.ONE;
import static com.app.ecomstore.util.EcomConstants.REASON_TYPE;
import static com.app.ecomstore.util.EcomConstants.STORE_CATEGORY_ID;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;

import android.app.Activity;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductSubstituteMain;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductUnavailableReasons;
import com.app.delivxstore.networking.DispatcherService;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.Utility;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.HashMap;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

public class ProductUnavailableSubstitutePresenterImpl implements
    ProductUnavailableSubstituteContract.OrderUnavailablePresenter {
  @Inject
  ProductUnavailableSubstituteContract.OrderUnavailableView view;
  @Inject
  NetworkService networkService;
  @Inject
  Utility utility;
  @Inject
  DispatcherService dispatcherService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkStateHolder networkStateHolder;
  @Inject
  Activity context;
  private Products mItems;
  private int mSelectedCount;

  @Inject
  public ProductUnavailableSubstitutePresenterImpl() {
  }

  @Override
  public void start() {
  }

  @Override
  public void stop() {
  }

  @Override
  public boolean isNetworkAvailable() {
    return false;
  }

  @Override
  public String getLanguage() {
    return preferenceHelperDataSource.getLanguage();
  }

  @Override
  public void getReasons(String storeCategoryId, int reasonId) {
    if (view != null) {
      view.showProgress();
    }
    HashMap<String, Object> map = new HashMap<>();
    map.put(REASON_TYPE, 1);
    map.put(ID, reasonId);
    map.put(ADMIN, 0);
    map.put(STORE_CATEGORY_ID, storeCategoryId);
    networkService.getProductUnavailableReasons(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM, CURRENCY_SYMBOL, CURRENCY_CODE, map).
        observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (view != null) {
              view.hideProgress();
            }
            try {
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                String response = value.body().string();
                Utility.printLog("exe", "response" + response);
                jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                ProductUnavailableReasons productUnavailableReasons =
                    gson.fromJson(jsonObject.toString(),
                        ProductUnavailableReasons.class);
                view.setReasons(productUnavailableReasons.getData().getReasonData());
              } else {
              }
            } catch (JSONException | IOException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void getSubstituteProducts() {
    if (view != null) {
      view.showProgress();
    }
    networkService.getProductSubstitute(
        ((ApplicationManager) context.getApplicationContext()).getAuthToken(
            preferenceHelperDataSource.getMyEmail()),
        preferenceHelperDataSource.getLanguage(),
        mItems.getUnitId()).
        observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (view != null) {
              view.hideProgress();
            }
            try {
              JSONObject jsonObject;
              if (value.code() == 200) {
                String response = value.body().string();
                jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                ProductSubstituteMain productSubstituteMain =
                    gson.fromJson(jsonObject.toString(),
                        ProductSubstituteMain.class);
                view.setProductSubstitutes(productSubstituteMain.getData());
              } else {
                String response = context.getString(R.string.product_substitute);
                jsonObject = new JSONObject(response);
                Gson gson = new Gson();
                ProductSubstituteMain productSubstituteMain =
                    gson.fromJson(jsonObject.toString(),
                        ProductSubstituteMain.class);
                view.setProductSubstitutes(productSubstituteMain.getData());
              }
            } catch (JSONException | IOException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public void setItemData(Products items, int selectedCount) {
    this.mItems = items;
    this.mSelectedCount = selectedCount;
  }

  @Override
  public void confirmUnAvailability(String reason) {
    if (!Utility.isNetworkConnected(context)) {
      view.onError(context.getResources().getString(R.string.networkError));
      return;
    }
    if (view != null) {
      view.showProgress();
    }
    UnavailableItemRequest itemUnavailableItemRequest = new UnavailableItemRequest();
    itemUnavailableItemRequest.setQuantity(mSelectedCount);
    itemUnavailableItemRequest.setCentralProductId(mItems.getCentralProductId());
    itemUnavailableItemRequest.setProductId(mItems.getProductId());
    itemUnavailableItemRequest.setUnitId("");
    UnavailableItemRequest newItemUnavailableItemRequest = new UnavailableItemRequest();
    newItemUnavailableItemRequest.setQuantity(
        Integer.parseInt(mItems.getQuantity().getValue()) - mSelectedCount);
    newItemUnavailableItemRequest.setCentralProductId(mItems.getCentralProductId());
    newItemUnavailableItemRequest.setProductId(mItems.getProductId());
    newItemUnavailableItemRequest.setUnitId("");
    UnavailableOrderRequest unavailableOrderRequest = new UnavailableOrderRequest();
    unavailableOrderRequest.setOrderId(mItems.getProductOrderId());
    unavailableOrderRequest.setExtraNote(reason);
    unavailableOrderRequest.setUpdateType(ONE);
    unavailableOrderRequest.setIpAddress(Utility.getIpAddress(context));
    unavailableOrderRequest.setLatitude(String.valueOf(CURRENT_ZONE_LAT));
    unavailableOrderRequest.setLongitude(String.valueOf(CURRENT_ZONE_LONGI));
    unavailableOrderRequest.setNewItems(newItemUnavailableItemRequest);
    unavailableOrderRequest.setItems(itemUnavailableItemRequest);
    final Observable<Response<ResponseBody>> userHistory = networkService.setUnavailabilityOrder(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        unavailableOrderRequest);
    userHistory.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (view != null) {
              view.hideProgress();
            }
            try {
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                view.reasonSuccess();
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
                Utility.printLog("userHistory : " + jsonObject.toString());
              }
            } catch (JSONException e) {
              Utility.printLog("userHistory : Catch :" + e.getMessage());
            } catch (IOException e) {
              Utility.printLog("userHistory : Catch :" + e.getMessage());
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.hideProgress();
            }
            Utility.printLog("userHistory : onError :" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  @Override
  public int getStoreType() {
    return preferenceHelperDataSource.getStoreType();
  }
}
