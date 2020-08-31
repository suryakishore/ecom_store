package com.app.delivxstore.mqtt_chat;

import static com.app.ecomstore.util.EcomConstants.AMAZON_SERVER;
import static com.app.ecomstore.util.EcomConstants.ANDROID_PLATFORM;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_CODE;
import static com.app.ecomstore.util.EcomConstants.CURRENCY_SYMBOL;
import static com.app.ecomstore.util.EcomConstants.CUSTOMER_ID;
import static com.app.ecomstore.util.EcomConstants.CUSTOMER_NAME;
import static com.app.ecomstore.util.EcomConstants.DATA;
import static com.app.ecomstore.util.EcomConstants.FILE;
import static com.app.ecomstore.util.EcomConstants.IMAGE_URL;
import static com.app.ecomstore.util.EcomConstants.INTERNEL_SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.ORDER_ID;
import static com.app.ecomstore.util.EcomConstants.SERVER_ERROR;
import static com.app.ecomstore.util.EcomConstants.SUCCESS;
import static com.app.ecomstore.util.EcomConstants.TWO;
import static com.app.ecomstore.util.EcomConstants.ZERO;

import android.content.Intent;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.utility.Utility;
import com.app.ecomstore.util.EcomUtil;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Response;

/**
 * Created by DELL on 29-03-2018.
 */
public class Presenter implements ChattingContract.PresenterOperations {
  @Inject
  NetworkService messageService;
  @Inject
  ChattingContract.ViewOperations view;
  @Inject
  PreferenceHelperDataSource preferenceHelperDataSource;
  private String custName, custId, storeOrderId;
  private Observer<JSONObject> observer;
  private ArrayList<ChatData> chatDataArry = new ArrayList<>();

  @Inject
  public Presenter() {
  }

  @Override
  public void getData(Intent intent) {
    custName = intent.getStringExtra(CUSTOMER_NAME);
    storeOrderId = intent.getStringExtra(ORDER_ID);
    custId = intent.getStringExtra(CUSTOMER_ID);
    EcomUtil.printLog("id:-" + storeOrderId + "Name:-" + custName + "driId:-" + custId);
  }

  @Override
  public void setActionBar() {
    view.setActionBar(custName);
  }

  @Override
  public void initViews() {
    view.setViews(storeOrderId);
    view.setRecyclerView();
    initializeRxJava();
  }

  @Override
  public void getChattingData() {
    view.showProgress();
    final Observable<Response<ResponseBody>> profile = messageService.chatHistory(
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        TWO,
        storeOrderId, String.valueOf(ZERO));
    profile.observeOn(AndroidSchedulers.mainThread())
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
                jsonObject = new JSONObject(value.body().string());
                EcomUtil.printLog("exe" + "response" + jsonObject.toString());
                ChatHistoryResponse chatHistoryResponse = new Gson().fromJson
                    (jsonObject.toString(), ChatHistoryResponse.class);
                if (chatHistoryResponse.getData() != null && chatHistoryResponse
                    .getData().size() > 0) {
                  chatDataArry.clear();
                  for (int i = 0; i < chatHistoryResponse.getData().size(); i++) {
                    if (chatHistoryResponse.getData().get(i).getFromID()
                        .equals(preferenceHelperDataSource.getStoreId())) {
                      chatHistoryResponse.getData().get(i).setCustProType(1);
                    } else if (chatHistoryResponse.getData().get(i).getFromID()
                        .equals(custId)) {
                      chatHistoryResponse.getData().get(i).setCustProType(2);
                    } else {
                      chatHistoryResponse.getData().remove(i);
                    }
                    chatDataArry.add(chatHistoryResponse.getData().get(i));
                  }
                  Collections.reverse(chatDataArry);
                  view.updateData(chatDataArry);
                }
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
//                                view.onError(jsonObject.getString("message"));
              }
            } catch (Exception e) {
              Utility.printLog("chatHistory : Catch :" + e.getMessage());
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
  public void message(String message, int contentType) {
    EcomUtil.printLog("Message" + message);
    if (!message.trim().isEmpty()) {
      sendMessage(message, contentType);
    }
  }

  @Override
  public void uploadImage(File file) {
    RequestBody requestFile =
        RequestBody.create(
            MediaType.parse("image/jpg"),
            file
        );
    MultipartBody.Part body =
        MultipartBody.Part.createFormData(FILE, file.getName(), requestFile);
    Observable<Response<ResponseBody>> uploadImage = messageService.uploadImage(AMAZON_SERVER,
        "chatModule", body, "" + System.currentTimeMillis());
    uploadImage.observeOn(AndroidSchedulers.mainThread())
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
            switch (value.code()) {
              case SUCCESS:
                try {
                  JSONObject jsonObject;
                  String response = value.body().string();
                  jsonObject = new JSONObject(response);
                  if (jsonObject.has(DATA)) {
                    JSONObject jsonObjectData = jsonObject.getJSONObject(DATA);
                    view.sendImageData(jsonObjectData.getString(IMAGE_URL));
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                }
                break;
              case SERVER_ERROR:
              case INTERNEL_SERVER_ERROR:
                assert view != null;
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
          }

          @Override
          public void onComplete() {
            if (view != null) {
              view.hideProgress();
            }
          }
        });
  }

  private void initializeRxJava() {
    observer = new Observer<JSONObject>() {
      @Override
      public void onSubscribe(Disposable d) {
      }

      @Override
      public void onNext(JSONObject jsonObject) {
        try {
          Gson gson = new Gson();
          ChatData chatData = gson.fromJson(jsonObject.getString("data"), ChatData.class);
          chatData.setCustProType(2);
          if (chatDataArry.size() > 0 && !chatDataArry.contains(chatData)) {
            chatDataArry.add(chatData);
            view.updateData(chatDataArry);
          } else if (chatDataArry.size() == 0) {
            chatDataArry.add(chatData);
            view.updateData(chatDataArry);
          }
        } catch (Exception e) {
          e.printStackTrace();
          EcomUtil.printLog("Caught : " + e.getMessage());
        }
      }

      @Override
      public void onError(Throwable e) {
      }

      @Override
      public void onComplete() {
      }
    };
    ChatDataObervable.getInstance().subscribe(observer);
  }

  /**
   * call api while sending message to the server.
   *
   * @param content message.
   */
  private void sendMessage(final String content, int contentType) {
    final long timeStamp = System.currentTimeMillis();
    if (view != null) {
      view.showProgress();
    }
    PostMessage postMessage = new PostMessage(TWO, contentType, timeStamp, content,
        preferenceHelperDataSource.getStoreId(), storeOrderId, custId, ZERO);
    Observable<Response<ResponseBody>> message = messageService.postMessage(
        preferenceHelperDataSource.getToken(),
        preferenceHelperDataSource.getLanguage(),
        ANDROID_PLATFORM,
        CURRENCY_SYMBOL,
        CURRENCY_CODE,
        postMessage);
    message.observeOn(AndroidSchedulers.mainThread())
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
                jsonObject = new JSONObject(value.body().string());
                ChatData chatData = new ChatData();
                chatData.setTimestamp(timeStamp);
                chatData.setContentType(contentType);
                chatData.setTargetId(custId);
                chatData.setFromID(custId);
                chatData.setContent(content);
                chatData.setCustProType(1);
                chatDataArry.add(chatData);
                view.updateData(chatDataArry);
              } else {
                jsonObject = new JSONObject(value.errorBody().string());
              }
              Utility.printLog("messageApi : " + jsonObject.toString());
            } catch (Exception e) {
              Utility.printLog("messageApi : Catch :" + e.getMessage());
            }
          }

          @Override
          public void onError(Throwable e) {
            if (view != null) {
              view.showProgress();
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
}
