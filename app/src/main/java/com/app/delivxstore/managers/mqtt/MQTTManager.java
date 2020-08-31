package com.app.delivxstore.managers.mqtt;

import static com.app.ecomstore.util.EcomConstants.CUSTOMER_ID;
import static com.app.ecomstore.util.EcomConstants.ORDER_ID;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.R;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.mqtt_chat.ChatDataObervable;
import com.app.delivxstore.mqtt_chat.ChattingActivity;
import com.app.delivxstore.observers.RXMqttMessageObserver;
import com.app.delivxstore.utility.Utility;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.inject.Inject;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

/*handles mqtt connection and
data getting on various status*/
public class MQTTManager {
  private static final String TAG = "MQTTManager";
  private PreferenceHelperDataSource mHelperDataSource;
  private IMqttActionListener mMQTTListener;
  private MqttAndroidClient mMqttAndroidClient;
  private MqttConnectOptions mMqttConnectOptions;
  private Context mContext;

  @Inject
  MQTTManager(Context context, PreferenceHelperDataSource preferenceHelperDataSource) {
    mContext = context;
    this.mHelperDataSource = preferenceHelperDataSource;
    mMQTTListener = new IMqttActionListener() {
      @Override
      public void onSuccess(IMqttToken asyncActionToken) {
        unSubscribeToTopic(mHelperDataSource.getManagerChannel());
        unSubscribeToTopic(
            String.format("%s/%s", mContext.getResources().getString(R.string.message),
                mHelperDataSource.getManagerID()));
        subscribeToTopic(mHelperDataSource.getManagerChannel());
        subscribeToTopic(String.format("%s/%s", mContext.getResources().getString(R.string.message),
            mHelperDataSource.getManagerID()));
        Utility.printLog(TAG + " topic: " + mHelperDataSource.getManagerChannel());
        Utility.printLog(TAG + " onSuccessPhone: myqtt client " + asyncActionToken.isComplete());
      }

      @Override
      public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        exception.printStackTrace();
        Utility.printLog(String.format("%s onFailure exception: %s", TAG, exception.toString()));
        Utility.printLog(String.format("%s onFailure: mqtt client: %s %s", TAG,
            asyncActionToken.isComplete(), exception.getMessage()));
      }
    };
  }

  /*subscribe topic to mqtt*/
  private void subscribeToTopic(String mqttTopic) {
    try {
      if (mMqttAndroidClient != null) {
        mMqttAndroidClient.subscribe(mqttTopic, 2);
      }
    } catch (MqttException | NullPointerException e) {
      Utility.printLog(TAG + " MqttException " + e);
      e.printStackTrace();
    }
  }

  /*unsubscribe topic from mqtt*/
  private void unSubscribeToTopic(String topic) {
    Log.d("exe", "topic" + topic + "mMqttAndroidClient" + mMqttAndroidClient);
    try {
      if (mMqttAndroidClient != null) {
        mMqttAndroidClient.unsubscribe(topic);
        Log.d("exe", "topicInside" + topic);
      }
    } catch (MqttException | NullPointerException e) {
      e.printStackTrace();
    }
  }

  /*checks whether mqtt connected or not*/
  public boolean isMQTTConnected() {
    return mMqttAndroidClient != null && mMqttAndroidClient.isConnected();
  }

  /*creates mqtt connection */
  public void createMQttConnection(String clientId) {
    Utility.printLog(TAG + " createMQttConnection: " + clientId);
    String serverUri =
        String.format("ssl://%s:%s", BuildConfig.MQTT_HOST, BuildConfig.MQTT_PORT);
    mMqttAndroidClient = new MqttAndroidClient(mContext, serverUri, clientId);
    mMqttAndroidClient.setCallback(new MqttCallback() {
      @Override
      public void connectionLost(Throwable cause) {
        try {
          Utility.printLog(TAG + " connectionLost: " + cause.getMessage());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void messageArrived(String topic, final MqttMessage message) {
        try {
          final JSONObject jsonObject = new JSONObject(new String(message.getPayload()));
          Utility.printLog(TAG + " messageArrived: " + new String(message.getPayload()));
          if (jsonObject.has("data")) {
            if (ChattingActivity.isOpen) {
              ChatDataObervable.getInstance().emitData(jsonObject);
            } else {
              String bid = jsonObject.getJSONObject("data").getString("storeOrderId");
              String content = jsonObject.getJSONObject("data").getString("content");
              String custID = jsonObject.getJSONObject("data").getString("fromID");
              Utility.printLog(
                  "data" + "orderID:-" + bid + "driverId:-" + custID + "driverName:-");
              sendNotification(bid, content, custID);
            }
          } else {
            RXMqttMessageObserver.getInstance().emit(jsonObject);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void deliveryComplete(IMqttDeliveryToken token) {
        Utility.printLog(TAG + " deliveryComplete: " + token);
      }
    });
    mMqttConnectOptions = new MqttConnectOptions();
    SocketFactory.SocketFactoryOptions socketFactoryOptions =
        new SocketFactory.SocketFactoryOptions();
    try {
      socketFactoryOptions.withCaInputStream(mContext.getResources().openRawResource(R.raw.ecomm));
      mMqttConnectOptions.setSocketFactory(new SocketFactory(socketFactoryOptions));
    } catch (IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException | KeyManagementException | UnrecoverableKeyException e) {
      e.printStackTrace();
      Utility.printLog(TAG + " socketFactoryOptions: " + e.toString());
    }
    mMqttConnectOptions.setCleanSession(true);
    mMqttConnectOptions.setAutomaticReconnect(true);
    mMqttConnectOptions.setUserName(BuildConfig.MQTT_USERNAME);
    mMqttConnectOptions.setPassword(BuildConfig.MQTT_PASSWORD.toCharArray());
    connectMQTTClient(mContext);
  }

  /*connects mqtt client*/
  private void connectMQTTClient(Context mContext) {
    try {
      Utility.printLog(TAG + " connectMQTTClient: ");
      mMqttAndroidClient.connect(mMqttConnectOptions, mContext, mMQTTListener);
    } catch (MqttException e) {
      Utility.printLog(TAG + " MqttException: " + e);
      e.printStackTrace();
    }
  }

  /*disconnect the MQtt client*/
  public void disconnect(String mqttTopic) {
    Log.d("exe", "mqttTopic" + mqttTopic + "mMqttAndroidClient" + mMqttAndroidClient);
    try {
      if (mMqttAndroidClient != null) {
        unSubscribeToTopic(mqttTopic);
        mMqttAndroidClient.disconnect();
      }
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  private void sendNotification(String bid, String message, String custID) {
    if (mHelperDataSource.isLoggedIn()) {
      Intent intent = new Intent(mContext, ChattingActivity.class);
      intent.putExtra(ORDER_ID, bid);
      intent.putExtra(CUSTOMER_ID, custID);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
          intent, PendingIntent.FLAG_ONE_SHOT);
      Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
      bigText.bigText(message);
      bigText.setBigContentTitle(mContext.getString(R.string.app_name));
      Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
          R.drawable.ic_login_logo);
      NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(
          Context.NOTIFICATION_SERVICE);
      Bitmap largeIcon = BitmapFactory.decodeResource(mContext.getResources(),
          R.drawable.ic_login_logo);
      NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext,
          mContext.getString(R.string.app_name))
          .setContentTitle(mContext.getString(R.string.app_name))
          .setContentText(message)
          .setContentIntent(pendingIntent)
          .setSmallIcon(R.drawable.ic_login_logo)
          .setLargeIcon(largeIcon)
          .setPriority(NotificationCompat.PRIORITY_MAX)
          .setAutoCancel(true)
          .setLargeIcon(bitmap)
          .setStyle(bigText);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Creating an Audio Attribute
        CharSequence name = mContext.getString(R.string.app_name);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build();
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(
            mContext.getString(R.string.app_name), name, importance);
        mChannel.setSound(defaultSoundUri, audioAttributes);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(mChannel);
      }
      assert notificationManager != null;
      notificationManager.notify(0, notificationBuilder.build());
    }
  }
}
