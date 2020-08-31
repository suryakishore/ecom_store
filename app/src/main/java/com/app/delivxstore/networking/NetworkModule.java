package com.app.delivxstore.networking;
/**
 * Created by DELL on 27-12-2017.
 */

import android.app.Application;
import android.content.Context;
import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.observers.RxNetworkObserver;
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
public class NetworkModule {
  private static final String NAME_BASE_URL = "NAME_BASE_URL";
  private static final String NAME_DISPATCH_URL = "NAME_DISPATCH_URL";
  private static final String NAME_PYTHON_URL = "PYTHON_DISPATCH_URL";
  private static final String NY_API_BASE_URL = "NY_API_URL";
  private static final long CACHE_SIZE = 10 * 1024 * 1024; //10 MB

  @Provides
  @Named(NAME_BASE_URL)
  String provideBaseUrlString() {
    return BuildConfig.BASEURL;
  }

  @Provides
  @Named(NAME_DISPATCH_URL)
  String provideBaseDispatcherUrlString() {
    return BuildConfig.BASE;
  }

  @Provides
  @Named(NAME_PYTHON_URL)
  String provideBasepythonUrlString() {
    return BuildConfig.PHYTHON_BASEURL;
  }

  @Provides
  @Named(NY_API_BASE_URL)
  String provideNyApiUrlString() {
    return BuildConfig.NYAPIBASEURL;
  }



  @Provides
  @Singleton
  Converter.Factory provideGsonConverter() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  Cache provideOkhttpCache(Application application) {
    return new Cache(application.getCacheDir(), CACHE_SIZE);
  }

  @Provides
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
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl,
      Cache cache, OkHttpClient okHttpClient) {
   /* OkHttpClient.Builder builder = new OkHttpClient.Builder();
    final TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
          @Override
          public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
              String authType) throws CertificateException {
          }

          @Override
          public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
              String authType) throws CertificateException {
          }

          @Override
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
          }
        }
    };
    final SSLContext sslContext;
    try {
      sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
      builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
      builder.hostnameVerifier(new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
          return true;
        }
      });
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

  */
   /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    // Level.BODY prints Urls, Params and Response
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    okHttpClient.newBuilder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .cache(cache)
        .build();*/
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(converter)
        .build();
  }

  @Provides
  @Singleton
  DispatcherService provideDisApi(Converter.Factory converter,
      @Named(NAME_DISPATCH_URL) String baseUrl) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(DispatcherService.class);
  }

  @Provides
  @Singleton
  UpdateProductService provideUpdateProductApi(Converter.Factory converter,
      @Named(NAME_PYTHON_URL) String baseUrl, OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(UpdateProductService.class);
  }

  @Provides
  @Singleton
  NetworkService provideUsdaApi(Retrofit retrofit) {
    return retrofit.create(NetworkService.class);
  }

  @Provides
  @Singleton
  NetworkStateHolder getNetworkStateHolder() {
    return new NetworkStateHolder();
  }

  @Provides
  @Singleton
  RxNetworkObserver getNetworkObserver() {
    return new RxNetworkObserver();
  }
}
