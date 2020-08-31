package com.app.delivxstore.main.createOrder.productCategory;

import android.util.Log;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

public class CategoryPresenterImpl implements CategoryActivityContract.CategoryPresenter
{
  @Inject
  NetworkService service;
  @Inject
  PreferenceHelperDataSource manager;


  @Inject
  public CategoryPresenterImpl() {
  }


  private void storeCategoriesApi()
  {
    Observable<Response<ResponseBody>> responseObservable /*= service.getCategoryApi(
        manager.getToken(), manager.getLanguage() + "",
        manager.getZoneID(),1,
        manager.geMainStoreId(), manager.getStoreId(),
        manager.getCurrentLat(), manager.getCurrentLong())*/ = null;



    responseObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Response<ResponseBody> responseBodyResponse)
          {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });

  }

}
