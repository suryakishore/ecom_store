package com.app.ecomstore.uiutil.barcodescanning;

import static com.app.ecomstore.util.EcomConstants.CHILD_PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.DATA;
import static com.app.ecomstore.util.EcomConstants.FALSE;
import static com.app.ecomstore.util.EcomConstants.PARENT_PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.PRODUCT_ID;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.TRUE;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.UpdateProductService;
import com.app.delivxstore.utility.Utility;
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

/*handles the logic for bar code */
public class BarCodeViewModel extends ViewModel {
  public ObservableField<Boolean> progressVisible = new ObservableField<>(FALSE);
  public MutableLiveData<HashMap<String, String>> mLiveData =
      new MutableLiveData<>();
  @Inject
  UpdateProductService networkService;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;

  @Inject
  public BarCodeViewModel() {
  }

  /**
   * This method is using to get product that match to the given qr code
   */
  void getProductDetails(String barCodeId) {
    progressVisible.set(TRUE);
    networkService.scanProduct(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        preferenceHelperDataSource.getStoreId(),
        barCodeId).
        observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Observer<Response<ResponseBody>>() {
          @Override
          public void onSubscribe(Disposable d) {
          }

          @Override
          public void onNext(Response<ResponseBody> value) {
            progressVisible.set(FALSE);
            try {
              JSONObject jsonObject;
              if (value.code() == SUCCESS) {
                String response = value.body().string();
                Utility.printLog("exe", "response" + response);
                jsonObject = new JSONObject(response);
                if (jsonObject.has(DATA)) {
                  JSONObject jsonObjectData = jsonObject.getJSONObject(DATA);
                  if (jsonObject.has(DATA)) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(PRODUCT_ID, jsonObjectData.getString(CHILD_PRODUCT_ID));
                    map.put(PARENT_PRODUCT_ID, jsonObjectData.getString(PARENT_PRODUCT_ID));
                    mLiveData.postValue(map);
                  }
                }
              }
            } catch (JSONException | IOException e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onError(Throwable e) {
            progressVisible.set(FALSE);
          }

          @Override
          public void onComplete() {
            progressVisible.set(FALSE);
          }
        });
  }
}