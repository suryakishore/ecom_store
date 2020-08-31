package com.app.delivxstore.main.home.tabView.orders.inDispatchOrders;

import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.DISPATCH;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.INTERNEL_SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.LIMIT;
import static com.app.ecomstore.util.EcomConstants.ORDER_TYPE;
import static com.app.ecomstore.util.EcomConstants.SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.TRUE;
import static com.app.ecomstore.util.EcomConstants.TYPE;
import static com.app.ecomstore.util.EcomConstants.TYPE_ACCEPT;
import static com.app.ecomstore.util.EcomConstants.TYPE_DISPATCH;
import static com.app.ecomstore.util.EcomConstants.TYPE_NEW;
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
import org.json.JSONObject;
import retrofit2.Response;

/**
 * presenter for in dispatch fragment
 */
public class IndispatchOrdersPresenter implements IndispatchOrdersContract.PresenterOperations {
  @Inject
  Utility utility;
  @Inject
  Context context;
  String title = "";
  @Inject
  Connectable connectable;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  NetworkService networkService;
  private IndispatchOrdersContract.ViewOperations mView;
  private boolean mIsLoading;
  private OrderData mOrderData;
  private Gson mGson = new Gson();
  Observer<Data> observer = new Observer<Data>() {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Data value) {
      getReadyForPickUpApi(ZERO, LIMIT);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
  };

  @Inject
  public IndispatchOrdersPresenter() {
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
  public void attachView(IndispatchOrdersContract.ViewOperations viewOperations) {
    this.mView = viewOperations;
    RXOrderResponseObserver.getInstance().subscribe(observer);
  }

  @Override
  public void getView(Bundle arguments) {
    if (arguments != null && arguments.containsKey(TYPE)) {
      switch (arguments.getString(TYPE)) {
        case TYPE_NEW:
          title = context.getResources().getString(R.string.New);
          break;
        case TYPE_ACCEPT:
          title = context.getResources().getString(R.string.accepted);
          break;
        case TYPE_DISPATCH:
          title = context.getResources().getString(R.string.inDispatch);
          break;
      }
    }
  }

  @Override
  public boolean isLoading() {
    return mIsLoading;
  }

  /**
   * This method is used to call the orders api with status dispatch
   */
  @Override
  public void getReadyForPickUpApi(int skip, int limit) {
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
        DISPATCH,
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
                  mOrderData = mGson.fromJson(
                      response,
                      OrderData.class);
                  EcomUtil.printLog("exe" + "data  6 " + mOrderData.getData().size());
                  if (skip == ZERO && limit == LIMIT) {
                    mView.setListData(mOrderData.getData(), DISPATCH,
                        Integer.parseInt(mOrderData.getCount()),
                        preferenceHelperDataSource.isCityLogin());
                  } else {
                    mView.setListData(mOrderData.getData(), DISPATCH,
                        preferenceHelperDataSource.isCityLogin());
                  }
                } catch (IOException e) {
                  e.printStackTrace();
                }
                break;
              case SERVER_ERROR:
              case INTERNEL_SERVER_ERROR:
                assert mView != null;
                mView.showMessage(context.getResources().getString(R.string.serverError));
                break;
              default:
                try {
                  assert mView != null;
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
              mView.showMessage("Sorry" + e.getMessage());
            }
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
