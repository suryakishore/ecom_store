package com.app.delivxstore.main.home.tabView.orders.sendpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.home.models.Data;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RXOrderResponseObserver;
import com.app.delivxstore.utility.Utility;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class SendPackagePresenterImpl implements SendPackageConrtact.SendPackagePresenter {

    private SendPackageConrtact.SendPackageView view;

    @Inject
    Utility utility;

    @Inject
    Activity activity;
    private String title = "";

    @Inject
    Connectable connectable;

    @Inject
    PreferenceHelperDataSource preferenceHelperDataSource;

    @SuppressLint("CheckResult")
    public void subscribeFilterData() {
        connectable.getObservable().subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {

                view.setStoreName(value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Inject
    public SendPackagePresenterImpl() {

    }

    @Override
    public void attachView(SendPackageFragment viewOperations) {
        this.view = viewOperations;
        RXOrderResponseObserver.getInstance().subscribe(observer);
    }

    @Override
    public void getView(Bundle arguments) {
        if (arguments != null && arguments.containsKey("TYPE")) {

            switch (arguments.getString("TYPE")) {
                case "TYPE_NEW":
                    title = activity.getResources().getString(R.string.New);
                    break;

                case "TYPE_ACCEPT":
                    title = activity.getResources().getString(R.string.accepted);
                    break;

                case "TYPE_DISPATCH":
                    title = activity.getResources().getString(R.string.inDispatch);
                    break;
            }

            view.setTitle(utility.isTablet(activity), title);
//            view.setView();
        }

    }


    Observer<Data> observer = new Observer<Data>() {
        @Override
        public void onSubscribe(Disposable d) {


        }

        @Override
        public void onNext(Data value) {
            /*Utility.printLog("MyOrderResponsePre" + value.getAssignCount());

                view.setListData(value.getPackageOrder());

            view.setTitle(utility.isTablet(activity), title + "(" + value.getLaterOrder() + ")");*/

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }

    };

}
