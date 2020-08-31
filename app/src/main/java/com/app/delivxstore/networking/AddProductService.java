package com.app.delivxstore.networking;

import io.reactivex.Observable;
import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface AddProductService {
  @GET("suggestions")
  Observable<Response<ResponseBody>> getSuggetions(
      @Header("Authorization") String authorization,
      @Header("language") String language,
      @Header("storeCategoryId") String storeCategoryId,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("variants")
  Observable<Response<ResponseBody>> getVariants(
      @Header("Authorization") String authorization,
      @Header("language") String language,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("child/variants")
  Observable<Response<ResponseBody>> getChildVariants(
      @Header("Authorization") String authorization,
      @Header("language") String language,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("substitute/products")
  Observable<Response<ResponseBody>> getSubStitudeProduct(
      @Header("Authorization") String authorization,
      @Header("language") String language,
      @QueryMap(encoded = true) HashMap<String, Object> query);

}
