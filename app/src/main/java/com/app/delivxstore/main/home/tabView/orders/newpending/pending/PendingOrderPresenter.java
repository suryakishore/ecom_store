/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.home.tabView.orders.newpending.pending;

import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.INTERNEL_SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.NEW;
import static com.app.ecomstore.util.EcomConstants.ORDER_TYPE;
import static com.app.ecomstore.util.EcomConstants.PENDING;
import static com.app.ecomstore.util.EcomConstants.SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.models.OrderData;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RXOrderResponseObserver;
import com.app.delivxstore.observers.RxTextChangeObserver;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/**
 * New order presenter
 * implementation
 */
public class PendingOrderPresenter implements PendingOrderContract.PresenterOperations {
  @Inject
  Utility utility;
  @Inject
  Context context;
  @Inject
  Connectable connectable;
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  private boolean mIsLoading;
  private PendingOrderContract.ViewOperations mView;
  private String mTitle = "";
  private OrderData mOrderData;
  private Gson mGson = new Gson();
  Observer<Data> observer = new Observer<Data>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Data value) {
      getNewOrderApi(ZERO, LIMIT);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };

  @Inject
  public PendingOrderPresenter() {
  }

  @SuppressLint("CheckResult")
  public void subscribeFilterData() {
    connectable.getObservable().subscribe(new DisposableObserver<String>() {
      @Override
      public void onNext(String value) {
        mView.setStoreName(value);
      }

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onComplete() {
      }
    });
  }

  @Override
  public void attachView(PendingOrderContract.ViewOperations viewOperations) {
    this.mView = viewOperations;
    RXOrderResponseObserver.getInstance().subscribe(observer);
  }

  @Override
  public void getView(Bundle arguments) {
    if (arguments != null && arguments.containsKey("TYPE")) {
      switch (arguments.getString("TYPE")) {
        case "TYPE_NEW":
          mTitle = context.getResources().getString(R.string.New);
          break;
        case "TYPE_ACCEPT":
          mTitle = context.getResources().getString(R.string.accepted);
          break;
        case "TYPE_DISPATCH":
          mTitle = context.getResources().getString(R.string.inDispatch);
          break;
      }
      mView.setTitle(Utility.isTablet(context), mTitle);
      mView.setView();
    }
  }

  @Override
  public boolean isLoading() {
    return mIsLoading;
  }

  @Override
  public void emitSearchData(String searchText) {
    RxTextChangeObserver.getInstance().emit(searchText);
  }

  /**
   * used to call new order api with status new
   */
  @Override
  public void getNewOrderApi(int skip, int limit) {
    Utility.printLog("exe" + "New skip" + skip + "limit" + limit);
    if (mView != null) {
      mView.showProgress();
      mIsLoading = TRUE;
    }
    final Observable<Response<ResponseBody>> getOrders = networkService.getOrders(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        limit,
        skip,
        PENDING,
        preferenceHelperDataSource.getCityId(),
        ORDER_TYPE,
        ZERO, ZERO);
    getOrders.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            if (mView != null) {
              mView.hideProgress();
              mIsLoading = FALSE;
            }
            JSONObject jsonObject;
            switch (value.code()) {
              case SUCCESS:
                try {
                  String response = value.body().string();
                  EcomUtil.printLog("exe" + "response" + response);
                  jsonObject = new JSONObject(response);
                  mOrderData = mGson.fromJson(
                      jsonObject.toString(),
                      OrderData.class);
                  if (skip == ZERO && limit == LIMIT) {
                    mView.setListData(mOrderData.getData(), NEW,
                        Integer.parseInt(mOrderData.getCount()),
                        Integer.parseInt(mOrderData.getCount()),
                        preferenceHelperDataSource.isCityLogin());
                  } else {
                    mView.setListData(mOrderData.getData(), NEW,
                        preferenceHelperDataSource.isCityLogin());
                  }
                } catch (IOException | JSONException e) {
                  e.printStackTrace();
                }
                break;
              case SERVER_ERROR:
              case INTERNEL_SERVER_ERROR:
                mView.showMessage(context.getResources().getString(R.string.serverError));
                break;
              default:
                try {
                  mView.showMessage(value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
            }
          }

          @Override
          public void onError(Throwable e) {
            if (mView != null) {
              mView.hideProgress();
            }
            mView.showMessage("Sorry" + e.getMessage());
          }

          @Override
          public void onComplete() {
            if (mView != null) {
              mView.hideProgress();
            }
          }
        });
  }
}
