package com.app.delivxstore.main.laundryitemPhotos;

import android.app.Activity;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.DispatcherService;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.networking.NetworkStateHolder;
import com.app.delivxstore.utility.UploadAwsImage;
import com.app.delivxstore.utility.Utility;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class LaundryItemPhotosPresenterImpl implements LaundryItemPhotosContract.Presenter {


    @Inject
    Activity context;

    @Inject
    LaundryItemPhotosContract.View view;

    @Inject
    Utility utility;

    @Inject
    NetworkService networkService;

    @Inject
    DispatcherService dispatcherService;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @Inject
    NetworkStateHolder networkStateHolder;


    @Inject
    UploadAwsImage uploadAwsImage;

    @Inject
    LaundryItemPhotosPresenterImpl() {

    }


    @Override
    public void uploadToS3(String orderId,int pos, File image) {

        uploadToAmazon(orderId,image, pos);

    }

    @Override
    public void uploadData(double orderId, String productId, JSONArray jsonArray) {

        if (!Utility.isNetworkConnected(context)) {
            view.onError(context.getResources().getString(R.string.networkError));
            return;
        }

        if (view != null)
            view.showProgress();

        networkService.saveImg(
                preferenceHelperDataSource.getLanguage(),
                preferenceHelperDataSource.getToken(), productId, orderId, jsonArray)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<ResponseBody>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ResponseBody> value) {

                        Utility.printLog("uploadData" + value.code());
                        if (view != null)
                            view.hideProgress();

                        switch (value.code()) {
                            case 200:
                                try {
                                    Utility.printLog("uploadData" + value.body().string());
                                    view.finishAct();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break;

                            case 500:
                            case 502:
                                view.onError(context.getResources().getString(R.string.serverError));
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.hideProgress();

                        }
                        Utility.printLog("uploadData : onError :" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Utility.printLog("uploadData" + "complete");
                        if (view != null)
                            view.hideProgress();
                    }
                });

    }

    @Override
    public void deleteItem(String fileName) {

        uploadAwsImage.deleteItem(preferenceHelperDataSource.getBucketName(),fileName);

    }

    @Override
    public void closeAct() {
        view.finishAct();
    }


    private void uploadToAmazon(String orderId, File file, int pos) {
        Utility.printLog("in amazon upload file " + file);
        view.showProgress();

        uploadAwsImage.uploadData("LAUNDARY",context, file, new UploadAwsImage.UploadCallBack() {

            @Override
            public void success(String url) {
                view.setLaundryImg(pos, url);

            }

            @Override
            public void success(String url, String type) {

            }

            @Override
            public void error(String errormsg) {
                view.onError(context.getString(R.string.somethingWentWrong));
            }
        });
    }

}
