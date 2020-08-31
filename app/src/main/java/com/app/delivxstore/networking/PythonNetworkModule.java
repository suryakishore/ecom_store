package com.app.delivxstore.networking;
/**
 * Created by DELL on 27-12-2017.
 */

import android.app.Application;
import android.content.Context;
import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.utility.SSLCertificate;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class PythonNetworkModule {
  private static final String ADD_PRODUCT_URL = "ADD_PRODUCT_URL";
  private static final long CACHE_SIZE = 10 * 1024 * 1024; //10 MB

  @Provides
  @Named(ADD_PRODUCT_URL)
  String addProductApiUrlString() {
    return BuildConfig.ADD_PRODUCT_BASEURL;
  }

/*  @Provides
  @Singleton
  Converter.Factory provideGsonConverter() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  Cache provideOkhttpCache(Application application) {
    return new Cache(application.getCacheDir(), CACHE_SIZE);
  }*/

/*  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(Context context) {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    // Level.BODY prints Urls, Params and Response
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder builder = new OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor);
    builder.sslSocketFactory(SSLCertificate.initSSL(context).getSocketFactory(),
        SSLCertificate.systemDefaultTrustManager());
    builder.hostnameVerifier((hostname, session) -> true);
    return builder.build();
  }*/

  @Provides
  @Singleton
  AddProductService addProductApi(Converter.Factory converter,
      @Named(ADD_PRODUCT_URL) String baseUrl, OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(AddProductService.class);
  }
}
