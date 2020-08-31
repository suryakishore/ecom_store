package com.app.delivxstore.networking;

import com.app.ecomstore.updateasile.UpdateAisleRequest;
import io.reactivex.Observable;
import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UpdateProductService {
  @POST("update/product/")
  Observable<Response<ResponseBody>> updateOrder(
      @Header("authorization") String authorization,
      @Body UpdateAisleRequest updateAisleRequest);

  @GET("scan/products")
  Observable<Response<ResponseBody>> scanProduct(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Query("storeId") String storeId,
      @Query("qrcode") String qrCode);

  @GET("suggestions")
  Observable<Response<ResponseBody>> getSuggetions(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("variants")
  Observable<Response<ResponseBody>> getVariants(
      @Header("Authorization") String authorization,
      @Header("language") String language,
      @QueryMap(encoded = true) HashMap<String, Object> query);
}
