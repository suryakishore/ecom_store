package com.app.delivxstore.main.language;

import static com.app.delivxstore.utility.VariableConstants.MESSAGE;
import static com.app.delivxstore.utility.VariableConstants.RESPONSE_CODE_SUCCESS;

import android.content.Context;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.main.language.model.LanguageList;
import com.app.delivxstore.main.language.model.LanguageResponse;
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

/*interacts with remote and local database
 *to get language data and informs view*/
public class LanguagePresenter implements LanguageContract.Presenter {
  public static final String TAG = String.format("%s ", LanguagePresenter.class.getSimpleName());
  @Inject
  NetworkService networkService;
  @Inject
  Context context;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  @Inject
  Gson gson;
  private LanguageFragment mLanguageView;

  @Inject
  LanguagePresenter() {
  }

  @Override
  public void attachView(LanguageFragment languageFragment) {
    mLanguageView = languageFragment;
  }

  @Override
  public void changeLanguage(LanguageList languageList) {
    preferenceHelperDataSource.setLanguageSettings(languageList);
    preferenceHelperDataSource.setLanguage(languageList.getLanguageName());
    preferenceHelperDataSource.setLanguageCode(languageList.getLanguageCode());
    mLanguageView.goToMainActivity(languageList.getLanguageName());
  }

  @Override
  public void getLanguages() {
    if (Utility.isNetworkAvailable(context)) {
      mLanguageView.showProgress();
      Observable<Response<ResponseBody>> getWalletDetails = networkService.getLanguageList();
      getWalletDetails.observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Response<ResponseBody> value) {
              mLanguageView.hideProgress();
              Utility.printLog(TAG + "language api response code: " + value.code());
              try {
                if (value.code() == RESPONSE_CODE_SUCCESS) {
                  if (value.body() != null) {
                    JSONObject jsonObject = new JSONObject(value.body().string());
                    LanguageResponse languageResponse = gson.fromJson(jsonObject.toString(),
                        LanguageResponse.class);
                    mLanguageView.setRecyclerView(languageResponse.getData());
                    Utility.printLog(TAG + "language api response: " + jsonObject.toString());
                  }
                } else {
                  if (value.errorBody() != null) {
                    JSONObject jsonObject = new JSONObject(value.errorBody().string());
                    String error = jsonObject.getString(MESSAGE);
                    Utility.printLog(TAG + "language api error: " + error);
                  }
                }
              } catch (IOException | JSONException e) {
                Utility.printLog(TAG + "language api catch: " + e.toString());
              }
            }

            @Override
            public void onError(Throwable e) {
              mLanguageView.hideProgress();
              Utility.printLog(TAG + "language api error: " + e.toString());
            }

            @Override
            public void onComplete() {
              mLanguageView.hideProgress();
            }
          });
    } else {
      mLanguageView.showError(context.getResources().getString(R.string.allNoNetworkError));
    }
  }

  @Override
  public String getLanguageCode() {
    return preferenceHelperDataSource.getLanguageCode();
  }
}
