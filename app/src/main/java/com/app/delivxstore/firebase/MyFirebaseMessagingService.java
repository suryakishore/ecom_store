package com.app.delivxstore.firebase;

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

import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.app.delivxstore.R;
import com.app.delivxstore.splash.SplashActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private int action;
    private String msgData;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FireBaseTracker", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("FireBaseTracker", "Message data payload: " + remoteMessage.getData());
            JSONObject jsonObject = new JSONObject(remoteMessage.getData());

            try {
                action = jsonObject.getInt("action");
                msgData=jsonObject.getString("msg");
                sendNotification(msgData, action);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null && remoteMessage.getNotification().getBody()!=null) {
            String msg = remoteMessage.getNotification().getBody();
            Log.d("FireBaseTracker", "Message Notification Body: " + msg);
            Log.d("FireBaseTracker", "aIction: " + action);

            sendNotification(msg, action);

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void sendNotification(String messageBody, int action) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri orderSound;
        String channelName;

        if (action == 1) {
            channelName = getString(R.string.New);
            orderSound = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.ordersound_new);
            Log.d("FireBaseTracker", "if: " + action + "orderSound" + orderSound);

        } else {
            channelName = getString(R.string.status);
            orderSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_login_logo);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(messageBody);
        bigText.setBigContentTitle(getString(R.string.app_name));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_login_logo);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelName)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(messageBody)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_login_logo)
                .setLargeIcon(largeIcon)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSound(orderSound)
                .setAutoCancel(true)
                .setLargeIcon(bitmap)
                .setStyle(bigText);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Creating an Audio Attribute
            CharSequence name = getString(R.string.app_name);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelName, name, importance);
            mChannel.setSound(orderSound, audioAttributes);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(mChannel);

        }

        assert notificationManager != null;
        notificationManager.notify(0, notificationBuilder.build());

    }

}
