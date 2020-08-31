package com.app.delivxstore.networking;

import com.app.delivxstore.main.editprofile.model.ResetPasswordBody;
import com.app.delivxstore.main.manage_address.SendAddress;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberRequest;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.AddItemOrderRequest;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.UnavailableOrderRequest;
import com.app.delivxstore.main.profile.LogoutRequestData;
import com.app.delivxstore.main.wallet.accountdetails.model.AccountDeleteBody;
import com.app.delivxstore.main.wallet.bankaccount.model.request.BankAccountBody;
import com.app.delivxstore.main.wallet.stripe.model.request.PatchStripeBody;
import com.app.delivxstore.main.wallet.stripe.model.request.StripeAccountBody;
import com.app.delivxstore.main.wallet.withdraw.model.WithDrawRequestBody;
import com.app.delivxstore.mqtt_chat.PostMessage;
import com.app.ecomstore.boarding.login.model.LoginRequestData;
import com.app.ecomstore.boarding.verifyotp.ForgotPaswwordRequest;
import com.app.ecomstore.boarding.verifyotp.SendOtpRequest;
import com.app.ecomstore.boarding.verifyotp.VerifyOTPRequest;
import com.app.ecomstore.drivers.GenerateLabelRequest;
import com.app.ecomstore.updateasile.UpdateAisleRequest;
import io.reactivex.Observable;
import java.util.HashMap;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by DELL on 27-12-2017.
 */
public interface NetworkService {
  @POST("manager/logIn")
  Observable<Response<ResponseBody>> logIn(@Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body LoginRequestData loginRequestData);

  @POST("dispatcher/forgotPassword")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> forgotPassword(@Header("language") String language,
      @Field("emailOrMobile") String email,
      @Field("verifyType") String verifyType);

  @GET("franchise/profile ")
  Observable<Response<ResponseBody>> profile(@Header("language") String language,
      @Header("authorization") String authorization);

  @POST("manager/logout")
  Observable<Response<ResponseBody>> logout(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body LogoutRequestData body);

  @GET("orders")
  Observable<Response<ResponseBody>> getOrders(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("limit") double limit,
      @Query("skip") double skip,
      @Query("status") double status,
      @Query("cityId") String cityId,
      @Query("orderType") double orderType,
      @Query("storeType") double storeType,
      @Query("bookingAssign") double bookingAssign);

  @GET("orders/count")
  Observable<Response<ResponseBody>> getOrdersCount(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("cityId") String cityId);

  @GET("/ordersHistory/{cityId}/{storeId}/{index}/{fromDate}/{toDate}/{search}")
  Observable<Response<ResponseBody>> getHistory(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("cityId") String cityId,
      @Path("storeId") String storeId,
      @Path("index") String index,
      @Path("fromDate") String fromDate,
      @Path("toDate") String toDate,
      @Path("search") String search);

  @GET("orders/details")
  Observable<Response<ResponseBody>> getOrderDetails(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("type") String type,
      @Query("orderId") String orderId);

  @PATCH("order")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> updateOrder(
      @Header("language") String language,
      @Header("authorization") String authorization,
      @Field("storeId") String storeId,
      @Field("managerId") String managerId,
      @Field("status") String status,
      @Field("timestamp") String timestamp,
      @Field("orderId") String orderId,
      @Field("reason") String reason,
      @Field("serviceType") String serviceType,
      @Field("dueDatetime") String dueDatetime);

  @PATCH("dispatchOrder")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> dispatchOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("timestamp") String timestamp,
      @Field("orderId") String orderId);

  @GET("cancellationReasons")
  Observable<Response<ResponseBody>> cancellationReasons(@Header("language") String language,
      @Header("authorization") String authorization);

  @GET("dispatcher/drivers/{storeId}/{index}")
  Observable<Response<ResponseBody>> drivers(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId,
      @Path("index") String index);

  @POST("dispatcher/dispatchOrder")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> dispatchOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("orderId") String orderId,
      @Field("driverId") String driverId,
      @Field("dispatchType") int dispatchType,
      @Field("timestamp") String timestamp);

  @GET("userHistory/{storeId}/{userId}/{index}")
  Observable<Response<ResponseBody>> userHistory(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId,
      @Path("userId") String userId,
      @Path("index") String index);

  @GET("dispatcher/orders/{storeId}/{index}/{startDate}/{endDate}")
  Observable<Response<ResponseBody>> getHistoryOnDate(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId,
      /*@Path("userId") String userId,*/
      @Path("index") String index,
      @Path("startDate") String startDate,
      @Path("endDate") String endDate);

  @GET("orders/{cityId}/{index}/{franchiseId}/{storeId}/{fromDate}/{toDate}/{search}")
  Observable<Response<ResponseBody>> getNewOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId,
      @Path("cityId") String cityId,
      @Path("franchiseId") String franchiseId,
      @Path("index") String index,
      @Path("fromDate") String startDate,
      @Path("toDate") String endDate,
      @Path("search") String search);

  @GET("dispatcher/customSearch/{needle}/{storeId}")
  Observable<Response<ResponseBody>> getCustomers(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId,
      @Path("needle") String needle);

  @GET("franchise/store/{franchiseId}")
  Observable<Response<ResponseBody>> getStoreList(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("franchiseId") String franchiseId);

  @GET("store/product/{storeId}")
  Observable<Response<ResponseBody>> getInventary(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId);

  @PATCH("child/product/statusUpdate")
  @Headers("Content-Type: application/json;charset=UTF-8")
  Observable<Response<ResponseBody>> updateInventory(@Header("language") String language,
      @Header("authorization") String authorization,
      @Body String products);

  @PATCH("child/product/{productId}/{status}")
  Observable<Response<ResponseBody>> getInventaryStatus(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("productId") String productId,
      @Path("status") int status);

  @GET("franchise/orders/{customerId}/{index}")
  Observable<Response<ResponseBody>> getPastOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("customerId") String customerId,
      @Path("index") String index);

  @PATCH("order/respond")
  Observable<Response<ResponseBody>> updateOrderNew(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body OrderUpdateStatus status);

  @POST("order/dispatch")
  Observable<Response<ResponseBody>> readyForPickUp(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body ReadyForPickUpStatus status);

  @PUT("/dispatcher/order")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> cancelOrder(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("latitude") String latitude,
      @Field("longitude") String longitude,
      @Field("ipAddress") String ipAddress,
      @Field("orderId") String orderId,
      @Field("reason") String reason);

  @GET("franchise/ongoingOrders/{driverId}/{index}")
  Observable<Response<ResponseBody>> getDriverOnGoingOrders(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("driverId") String driverId,
      @Path("index") String index);

  @GET("dispatcher/search/{data}/{needle}/{storeId}")
  Observable<Response<ResponseBody>> getSearchData(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("data") String data,
      @Path("needle") String needle,
      @Path("storeId") String storeId);

  @PATCH("/laundry/saveimage")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> saveImg(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("productId") String productId,
      @Field("orderId") double orderId,
      @Field("images") JSONArray images);

  @GET("store/walletTransaction/{pageIndex}")
  Observable<Response<ResponseBody>> getWalletTransaction(@Header("authorization") String authToken,
      @Header("language") String language,
      @Path("pageIndex") String pageIndex);

  @GET("admin/connectAccount/{storeId}")
  Observable<Response<ResponseBody>> connectAccount(@Header("language") String language,
      @Header("authorization") String authorization,
      @Path("storeId") String storeId);

  @POST("admin/externalAccount")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> externalAccount(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("email") String email,
      @Field("account_number") String account_number,
      @Field("routing_number") String routing_number,
      @Field("account_holder_name") String account_holder_name,
      @Field("country") String country,
      @Field("currency") String currency,
      @Field("userId") String storeId);

  @GET("utility/languages")
  Observable<Response<ResponseBody>> getLanguageList();

  @POST("admin/connectAccount")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> createStripeAccount(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("email") String email,
      @Field("city") String city,
      @Field("country") String country,
      @Field("line1") String line1,
      @Field("postal_code") String postal_code,
      @Field("state") String state,
      @Field("day") String day,
      @Field("month") String month,
      @Field("year") String year,
      @Field("first_name") String first_name,
      @Field("last_name") String last_name,
      @Field("document") String document,
      @Field("personal_id_number") String personal_id_number,
      @Field("date") String date,
      @Field("userId") String storeId,
      @Field("ip") String ip);

  @GET("https://api.ipify.org/?format=json")
  Observable<Response<ResponseBody>> ipAddess();

  @POST("dispatcher/customer")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> addCustomer(@Header("language") String language,
      @Header("authorization") String authorization,
      @Field("name") String name,
      @Field("email") String email,
      @Field("countryCode") String countryCode,
      @Field("mobile") String mobile,
      @Field("deviceType") int deviceType);

  @GET("address/{userId}")
  Observable<Response<ResponseBody>> getAddressApi(@Header("authorization") String authorization,
      @Header("language") String language,
      @Path("userId") String userId);

  @DELETE("address/{id}")
  Observable<Response<ResponseBody>> deleteAddressApi(@Header("authorization") String authorization,
      @Header("language") String language,
      @Path("id") String id);

  @POST("address")
  Observable<Response<ResponseBody>> addAddressApi(@Header("authorization") String authorization,
      @Header("language") String language,
      @Body SendAddress order);

  @PATCH("address")
  Observable<Response<ResponseBody>> updateAddressApi(@Header("authorization") String authorization,
      @Header("language") String language,
      @Body SendAddress order);

  @GET("dispatcher/cart/{customerId}")
  Observable<Response<ResponseBody>> getCartApi(@Header("authorization") String authorization,
      @Header("language") String language, @Path(
      "customerId") String customerID);

  @POST("dispatcher/customOrder/cart")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> addToCart(@Header("authorization") String authorization,
      @Header("language") String language,
      @Field("customerId") String customerID,
      @Field("quantity") double quantity,
      @Field("itemName") String itemName,
      @Field("orderType") double orderType,
      @Field("unitName") String unitName,
      @Field("unitPrice") double unitPrice,
      @Field("storeType") double storeType,
      @Field("storeCategoryType") String storeCategoryType
  );

  @PATCH("dispatcher/customOrder/cart")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> updateCart(@Header("authorization") String authorization,
      @Header("language") String language,
      @Field("customerId") String customerID,
      @Field("cartId") String cartId,
      @Field("childProductId") String childProductId,
      @Field("unitId") String unitId,
      @Field("quantity") int quantity,
      @Field("unitPrice") int unitPrice);

  @POST("dispatcher/fare")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> getFare(@Header("authorization") String authorization,
      @Header("language") String language,
      @Field("customerId") String customerID,
      @Field("status") int status,
      @Field("type") double type,
      @Field("latitude") double latitude,
      @Field("longitude") double longitude,
      @Field("pickUpLat") double pickUpLat,
      @Field("pickUpLong") double pickUpLong);

  @DELETE("dispatcher/cart/{customerId}/{cartId}/{childProductId}/{unitId}/{addedToCartOn"
      + "}/{orderType}")
  Observable<Response<ResponseBody>> deleteItemApi(@Header("authorization") String authorization,
      @Header("language") String language,
      @Path("customerId") String customerId,
      @Path("cartId") String cartId,
      @Path("childProductId") String childProductId,
      @Path("unitId") String unitId,
      @Path("addedToCartOn") String addedToCartOn,
      @Path("orderType") double orderType);

  @GET("card/{userId}")
  Observable<Response<ResponseBody>> getCard(@Header("authorization") String authorization,
      @Header("language") String language,
      @Path("userId") String userId);

  @POST("dispatcher/orderNew")
  @FormUrlEncoded
  Observable<Response<ResponseBody>> placeOrder(@Header("authorization") String authorization,
      @Header("language") String language,
      @Field("customerId") String customerID,
      @Field("paymentType") double paymentType,
      @Field("couponCode") String couponCode,
      @Field("discount") double discount,
      @Field("cartId") String cartId,
      @Field("latitude") double latitude,
      @Field("longitude") double longitude,
      @Field("pickUpLat") double pickUpLat,
      @Field("pickUpLong") double pickUpLong,
      @Field("bookingDate") String bookingDate,
      @Field("serviceType") int serviceType,
      @Field("bookingType") int bookingType,
      @Field("extraNote") JSONObject extraNote,
      @Field("dueDatetime") String dueDatetime,
      @Field("estimatedPackageValue") double estimatedPackageValue,
      @Field("storeType") double storeType,
      @Field("storeTypeMsg") String storeTypeMsg,
      @Field("addressId") String addressId,
      @Field("address1") String address1,
      @Field("address2") String address2
  );

  @GET("reason")
  Observable<Response<ResponseBody>> getProductUnavailableReasons(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("productSubstitute/{itemId}")
  Observable<Response<ResponseBody>> getProductSubstitute(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Path("itemId") String itemId);

  @POST("customer/verifyOtp")
  Observable<Response<ResponseBody>> verifyOTP(
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body VerifyOTPRequest req);

  @POST("manager/sendOtp")
  Observable<Response<ResponseBody>> sendOTP(
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body SendOtpRequest req);

  @POST("manager/forgotPassword")
  Observable<Response<ResponseBody>> forgotPassword(
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body ForgotPaswwordRequest req);

  @POST("/v1/imageUpload")
  @Multipart
  Observable<Response<ResponseBody>> uploadImage(@Part("uploadTo") int uploadTo,
      @Part("folder") String folder,
      @Part MultipartBody.Part file,
      @Part("fileName") String fileName);

  @GET("/v1/wallet")
  Observable<Response<ResponseBody>> getWalletDetails(@Header("authorization") String authorization,
      @Header("lan") String lan,
      @Query("userId") String userId,
      @Query("userType") String userType);

  @GET("/v1/walletTransaction")
  Observable<Response<ResponseBody>> getTransactions(@Header("authorization") String authorization,
      @Header("lan") String lan,
      @Query("walletId") String walletId,
      @Query("txnType") int txnType,
      /*txnType 1-credit 2-debit 0-all*/
      @Query("pageState") String pageState,
      @Query("fetchSize") int fetchSize);

  @GET("/stripe/v1/connectAccount")
  Observable<Response<ResponseBody>> getStripDetails(@Header("authorization") String authorization,
      @Header("lan") String lan);

  @HTTP(method = "DELETE", path = "/stripe/v1/externalAccount", hasBody = true)
  Observable<Response<ResponseBody>> deleteBankAccount(
      @Header("authorization") String authorization,
      @Header("lan") String lan,
      @Body AccountDeleteBody body);

  @PATCH("/stripe/v1/connectAccount")
  Observable<Response<ResponseBody>> updateStripAccount(
      @Header("authorization") String authorization,
      @Header("lan") String lan,
      @Body PatchStripeBody body);

  @POST("/stripe/v1/connectAccount")
  Observable<Response<ResponseBody>> createStripAccount(
      @Header("authorization") String authorization,
      @Header("lan") String lan,
      @Body StripeAccountBody body);

  @POST("/stripe/v1/externalAccount")
  Observable<Response<ResponseBody>> createBankAccount(
      @Header("authorization") String authorization,
      @Header("lan") String lan,
      @Body BankAccountBody body);

  @POST("/v1/withdraw/money")
  Observable<Response<ResponseBody>> walletWithDraw(@Header("authorization") String authorization,
      @Header("lan") String lan,
      @Body WithDrawRequestBody body);

  @GET("/v1/orders")
  Observable<Response<ResponseBody>> getOrderHistory(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("limit") int limit,
      @Query("skip") int skip,
      @Query("status") int status,
      @Query("cityId") String cityId,
      @Query("orderType") int orderType,
      @Query("storeType") int storeType,
      @Query("orderBy") int orderBy,
      @Query("orderTime") String orderTime,
      @Query("search") String search);

  @PATCH("order/pack")
  Observable<Response<ResponseBody>> setPackOrder(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body OrderNumberRequest req);

  @GET("/v1/cognitoToken")
  Observable<Response<ResponseBody>> getCognitoToken(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode);

  @PATCH("order")
  Observable<Response<ResponseBody>> setUnavailabilityOrder(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body UnavailableOrderRequest req);

  @PATCH("order")
  Observable<Response<ResponseBody>> addProduct(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body AddItemOrderRequest req);

  @GET("shippingPartner")
  Observable<Response<ResponseBody>> getPartners(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode);

  @GET("driver")
  Observable<Response<ResponseBody>> getDrivers(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("order/status")
  Observable<Response<ResponseBody>> getTracking(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @QueryMap(encoded = true) HashMap<String, Object> query);

  @GET("/python/user/notification")
  Observable<Response<ResponseBody>> getNotifications(@Header("Authorization") String Authorization,
      @Header("appName") String appName,
      @Query("from") String from,
      @Query("to") String to);

  @GET("manager/resetPassword")
  Observable<Response<ResponseBody>> resetPassword(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body ResetPasswordBody resetPasswordBody);

  @GET("packagingBox")
  Observable<Response<ResponseBody>> getPackageBox(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode);

  @POST("jobs/unAssign")
  Observable<Response<ResponseBody>> unAssignJob(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body GenerateLabelRequest resetPasswordBody);

  @POST("jobs/assign")
  Observable<Response<ResponseBody>> assignJob(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body GenerateLabelRequest resetPasswordBody);

  /*
   @DELETE("order")
 */
  @HTTP(method = "DELETE", path = "order", hasBody = true)
  Observable<Response<ResponseBody>> cancelOrder(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body CancelOrderRequest request);

  @GET("order/package/details")
  Observable<Response<ResponseBody>> getPackageDetails(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("packageId") String packageId);

  @POST("update/product/")
  Observable<Response<ResponseBody>> updateOrder(
      @Header("authorization") String authorization,
      @Body UpdateAisleRequest updateAisleRequest);

  @POST("imageUpload")
  @Multipart
  Observable<Response<ResponseBody>> uploadImageApi(
      @Part("uploadTo") int uploadTo,
      @Part("folder") String folder,
      @Part MultipartBody.Part file,
      @Part("fileName") String fileName);

  @PATCH("order/pick")
  Observable<Response<ResponseBody>> orderPick(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body OrderPickInput orderPickInput);

  @PATCH("order/confirm")
  Observable<Response<ResponseBody>> orderConfirm(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body ConfirmOrderInput confirmOrderInput);

  @PATCH("order/receipt")
  Observable<Response<ResponseBody>> uploadReceipt(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body UploadReceiptInput uploadReceiptInput);

  @PATCH("order/generateLable")
  Observable<Response<ResponseBody>> generateLable(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body GenerateLableInput uploadReceiptInput);

  @GET("order/receipt")
  Observable<Response<ResponseBody>> orderReceipt(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("orderId") String orderId);

  @POST("order/confirmation/request")
  Observable<Response<ResponseBody>> confirmationRequest(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body CancelOrderRequest request);

  @POST("message")
  Observable<Response<ResponseBody>> postMessage(
      @Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencySymbol,
      @Header("currencycode") String currencyCode,
      @Body PostMessage postMessage);

  @GET("chatHistory")
  Observable<Response<ResponseBody>> chatHistory(
      @Header("language") String language,
      @Header("platform") int platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Query("type") int type,
      @Query("storeOrderId") String bookingId,
      @Query("pageNo") String pageNo);

  @PATCH("order/picked")
  Observable<Response<ResponseBody>> orderPicked(@Header("authorization") String authorization,
      @Header("language") String language,
      @Header("platform") double platform,
      @Header("currencysymbol") String currencysymbol,
      @Header("currencycode") String currencycode,
      @Body OrderPickedInput orderPickInput);
}