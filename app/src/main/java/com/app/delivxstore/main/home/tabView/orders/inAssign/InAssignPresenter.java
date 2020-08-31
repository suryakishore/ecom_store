package com.app.delivxstore.main.home.tabView.orders.inAssign;

import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.INTERNEL_SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.ORDER_TYPE;
import static com.app.ecomstore.util.EcomConstants.PACKED;
import static com.app.ecomstore.util.EcomConstants.SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.main.home.models.OrderData;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RXOrderResponseObserver;
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
import retrofit2.Response;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class InAssignPresenter implements InAssignContract.PresenterOperations {
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
  private InAssignContract.ViewOperations mView;
  private String mTitle = "";
  private int mStoretype;
  private OrderData mOrderData;
  private Gson mGson = new Gson();
  Observer<Data> observer = new Observer<Data>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Data value) {
      getPackedOrderApi(ZERO, LIMIT);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };

  @Inject
  public InAssignPresenter() {
  }

  @Override
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
  public void attachView(InAssignContract.ViewOperations viewOperations) {
    this.mView = viewOperations;
    RXOrderResponseObserver.getInstance().subscribe(observer);
  }

  @Override
  public void getView(Bundle arguments) {
  }

  @Override
  public boolean isLoading() {
    return mIsLoading;
  }

  /**
   * this method used to call the orders api with status packed.
   */
  @Override
  public void getPackedOrderApi(int skip, int limit) {
    if (mView != null) {
      mView.showProgress();
      mIsLoading = TRUE;
    }
    Utility.printLog("exe" + "InAssign skip" + skip + "limit" + limit);
    final Observable<Response<ResponseBody>> getOrders = networkService.getOrders(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        limit,
        skip,
        PACKED,
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
            switch (value.code()) {
              case SUCCESS:
                try {
                  String response = value.body().string();
                  Log.d("exe", "response" + response);
                  mOrderData = mGson.fromJson(response, OrderData.class);
                  EcomUtil.printLog("exe" + "data  4 " + mOrderData.getData().size());
                  if (skip == ZERO && limit == LIMIT) {
                    mView.setListData(mOrderData.getData(), PACKED,
                        Integer.parseInt(mOrderData.getCount()),
                        preferenceHelperDataSource.isCityLogin());
                  } else {
                    mView.setListData(mOrderData.getData(), PACKED,
                        preferenceHelperDataSource.isCityLogin());
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                  Log.d("exe", "Exception" + e.getMessage());
                }
                break;
              case SERVER_ERROR:
              case INTERNEL_SERVER_ERROR:
                mView.showMessage(context.getResources().getString(R.string.serverError));
                break;
              default:
                try {
                  mView.showMessage(value.errorBody().string());
                  Log.d("exe", "error" + value.errorBody().string());
                } catch (IOException e) {
                  e.printStackTrace();
                  Log.d("exe", "IOException" + e.getMessage());
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
