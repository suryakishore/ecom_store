package com.app.delivxstore.main.notification;

import static com.app.delivxstore.utility.VariableConstants.APP_NAME;
import static com.app.delivxstore.utility.VariableConstants.DATA_SIZE;
import static com.app.delivxstore.utility.VariableConstants.FROM_PAGE;
import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.content.Context;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.notification.model.NotificationResponse;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;
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

/*interacts with local and remote database to get
 * notification data and update the views*/
public class NotificationPresenter implements NotificationContract.Presenter {

  public static final String TAG = String.format("%s ",
      NotificationPresenter.class.getSimpleName());
  @Inject
  NetworkService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Gson gson;
  @Inject
  Context context;
  private NotificationContract.NotificationView mNotificationView;

  @Inject
  public NotificationPresenter() {
  }

  @Override
  public void attachView(NotificationFragment notificationFragment) {
    mNotificationView = notificationFragment;
  }

  @Override
  public void getNotifications() {
    if (Utility.isNetworkAvailable(context)) {
      mNotificationView.showProgress();
      Observable<Response<ResponseBody>> getNotification = networkService.getNotifications(
          ((ApplicationManager)context.getApplicationContext()).getAuthToken(preferenceHelperDataSource.getMyEmail()),
          APP_NAME,
          FROM_PAGE,
          DATA_SIZE);
      getNotification.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              mNotificationView.hideProgress();
              Utility.printLog(TAG + "notifications api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    String responseString = value.body().string();
                    NotificationResponse notificationResponse = gson.fromJson(responseString,
                        NotificationResponse.class);
                    mNotificationView.setData(notificationResponse.getData());
                    Utility.printLog(TAG + "notifications api response: " + responseString);
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    Utility.printLog(TAG + "notifications api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "notifications api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              mNotificationView.hideProgress();
              Utility.printLog(TAG + "notifications api error: " + e.toString());

            }

            @Override
            public void onComplete() {

            }
          });
    } else {
      mNotificationView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }
}
