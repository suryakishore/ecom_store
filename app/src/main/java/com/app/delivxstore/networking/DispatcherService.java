package com.app.delivxstore.networking;

import io.reactivex.Observable;
import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.QueryMap;

public interface DispatcherService {
  @PATCH("dispatcher/order")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> updateOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("items") String items,
      @Field("extraNote") String extraNote,
      @Field("orderId") String orderId,
      @Field("deliveryCharge") String deliveryCharge,
      @Field("latitude") String latitude,
      @Field("longitude") String longitude);


}
