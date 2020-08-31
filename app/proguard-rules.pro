#proguard rule for transfer utility class
-keep class com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType {*;}
-keep class org.eclipse.paho.client.mqttv3.internal.TCPNetworkModuleFactory {*;}
-ignorewarnings

-keep class org.eclipse.paho.clent.mqttv3.* {*;}
-keep class org.eclipse.paho.client.mqttv3.*$ {*;}
-keep class org.eclipse.paho.client.mqttv3.logging.JSR47Logger {*;}
-keepclasseswithmembers class org.eclipse.paho.* {*;}
-keep class org.eclipse.paho.android.service.* {*;}

#Proguard configuration for crashlytics
#-keep class com.crashlytics.** { *; }
#-dontwarn com.crashlytics.**
#-keepattributes SourceFile,LineNumberTable,*Annotation*
#-keep class com.crashlytics.android.**

# Proguard configuration for Jackson 2.x (fasterxml package instead of codehaus package)
-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter {
    public ** writeValueAsString(**);
}
-keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**


-keepattributes Exceptions, InnerClasses, Signature, Deprecated, EnclosingMethod, SourceFile, LineNumberTable, *Annotation

-keep class com.google.android.gms.ads.identifier.** { *; }

# joda time
-keep class org.joda.time.** { *; }
-dontwarn org.joda.time.**

# jackson
-keepnames class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
-keep class org.codehaus.**



# gson
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.examples.android.model.** { *; }

#Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

-dontwarn okhttp3.**

# Pubnub
-dontwarn com.pubnub.**
-keep class com.pubnub.** { *; }


-dontwarn org.slf4j.**

# EventBus 3.0
-keepclassmembers class ** {
    public void onEvent*(**);
}

# EventBus 3.0 annotation
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


#For Dagger
-dontwarn com.google.errorprone.annotations.**

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes SourceFile, LineNumberTable
-keep,allowshrinking,allowoptimization class * { <methods>; }


-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.okhttp.**

-keep public class com.couchbase.lite.** { *; }
-dontwarn com.couchbase.lite.**
-keep public class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.**

-dontwarn org.apache.commons.**
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient
-keep class mypackage.MyCarGrid { *; }

# Twilio Client
-keep class com.twilio.** { *; }

-dontwarn com.android.installreferrer

#WebRtc
-keep class org.webrtc.** { *; }


#-keep class com.reryde.app.mainActivity.DriverConfigDetailModel.** { *; }
#-keep class com.reryde.pojo.** { *; }

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

#for glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#########################
-keep class org.apache.commons.logging.**               { *; }
# Class names are needed in reflection
-keepnames class com.amazonaws.**
-keepnames class com.amazon.**
# Request handlers defined in request.handlers
-keep class com.amazonaws.services.**.*Handler
# The following are referenced but aren't required to run
-dontwarn com.fasterxml.jackson.**
-dontwarn org.apache.commons.logging.**
# Android 6.0 release removes support for the Apache HTTP client
-dontwarn org.apache.http.**
# The SDK has several references of Apache HTTP client
-dontwarn com.amazonaws.http.**
-dontwarn com.amazonaws.metrics.**


##---------------Begin: proguard configuration for Gson  ----------


# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.amazonaws.services.cognitoidentityprovider.** { *; }