/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.delivxstore.main.mobileView.orderDetails;

import android.os.Bundle;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.PastOrdersData;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.Reasons;
import com.app.delivxstore.main.mobileView.orderDetails.models.OrderDetailsModel;
import com.app.delivxstore.main.mobileView.orderDetails.models.ProductTaxArray;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.ProductOrdersRequest;
import java.util.ArrayList;

/**
 * Provide interface methods for view and presenter
 * for order details
 */
public interface OrderDetailsContract {
  /**
   * Provide interface methods for view
   * for order details
   */
  interface OrderDetailsView {
    /**
     * <h>finishActivity</h>
     * <p>This method is using to close the activity after
     * Successful state transition</p>
     */
    void finishActivity();

    /**
     * <h>Start Online Driver List Activity</h>
     * <p>this method is using to start Online drivers List Activity</p>
     *
     * @param orderId Order Id to assign to driver
     */
    void moveToListAct(String orderId);

    /**
     * <h>Show Pop Up for </h>
     * <p>This method is using to Show Delay or Cancel pop up</p>
     *
     * @param delay is delay for showing delay UI
     */
    void showCancelOrDelayDailog(boolean delay);

    /**
     * <h>Add Product list to View</h>
     * <p>this method is using to show list of Items with Qnty * unit price and Total for
     * each </p>
     *
     * @param products list of items in the order
     * @param currency currency symbol
     */
    void addItems(final ArrayList<Products> products, String currency, String storeType);

    /**
     * <h>setReasons</h>
     * <p>Show reasons for order cancellation</p>
     *
     * @param reasons reasons for order cancellation
     */
    void setReasons(ArrayList<Reasons> reasons);

    /**
     * <h>hideProgress</h>
     * <p>hide progress bar</p>
     */
    void hideProgress();

    /**
     * <h>showProgress</h>
     * <p>show progress bar</p>
     */
    void showProgress();

    /**
     * <h>setPastOrders</h>
     * <p>Set the past order details</p>
     *
     * @param pastOrders past order model data
     */
    void setPastOrders(PastOrdersData pastOrders);

    /**
     * <h>setViews</h>
     * <p>set views for the order</p>
     *
     * @param orderedItemDetails order details model data
     */
    void setViews(OrderDetailsModel orderedItemDetails, boolean isCityLogin);

    /**
     * <h>setFares</h>
     * <p>set fares for the order</p>
     *
     * @param subTotal       sub total of the order
     * @param discount       discount on the order
     * @param delCharge      delivery charge for the order
     * @param serviceCharge  service charges for the order
     * @param total          total price of the order
     * @param exclusiveTaxes taxes in the order
     * @param storeType      store type of the order
     * @param subTotalAmount sub total amount of the order
     */
    void setFares(String subTotal, String discount,String promoDiscount, String delCharge, String serviceCharge,
        String total, ArrayList<ProductTaxArray> exclusiveTaxes, String storeType,
        String subTotalAmount);

    /**
     * <h>openPastOrderAct</h>
     * <p>open past order activity</p>
     */
    void openPastOrderAct();

    /**
     * <h>openOrderEditDialog</h>
     * <p>open edit dialog for edit item of the order</p>
     *
     * @param products item to be edit model data
     * @param index    item position in the order item list
     */
    void openOrderEditDialog(Products products, int index);

    /**
     * <h>dissMiss</h>
     * <p>finish the order details activity</p>
     */
    void dissMiss();

    /**
     * <h>showLoader</h>
     * <p>show loading screen</p>
     */
    void showLoader();

    /**
     * <h>hideLoader</h>
     * <p>hide loader after successful api response</p>
     */
    void hideLoader();

    /**
     * Show error message if occurred in
     * API call
     *
     * @param message the message
     * @param code    the error code
     */
    void showError(String message, int code);

    void refreshAPi();

    void orderConfirm();
  }

  /**
   * Provide interface methods for presenter
   * for order details
   */
  interface OrderDetailsPresenter {
    /**
     * <h>editItems</h>
     * <p>edit items of the order</p>
     */
    void editItems();

    /**
     * <h>saveItems</h>
     * <p>save the item quantity after editing</p>
     *
     * @param productsArrayList list of items in the order
     */
    void saveItems(ArrayList<Products> productsArrayList);

    /**
     * <h>callDriver</h>
     * <p>call driver</p>
     */
    void callDriver();

    /**
     * <h>callCustomer</h>
     * <p>call customer</p>
     */
    void callCustomer();

    /**
     * <h>manualDispatch</h>
     * <p>manually dispatch the order</p>
     */
    void manualDispatch();

    /**
     * <h>cancelOrDelayOrder</h>
     */
    void cancelOrDelayOrder(boolean delay);

    /**
     * <h>updateItem</h>
     * <p>Update the unit of the item</p>
     *
     * @param quantity  new quantity of the unit
     * @param unitPrice unit price
     * @param unitId    unit id
     */
    void updateItem(int quantity, float unitPrice, String unitId);

    /**
     * <h>delayOrder</h>
     * <p>delay the order</p>
     *
     * @param reason        list of reasons related to delaying of order
     * @param selectedDelay selected reasons for order delay
     */
    void delayOrder(Reasons reason, String selectedDelay);

    /**
     * <h>cancelOrder</h>
     * <p>cancel the order</p>
     *
     * @param reason list of reasons related to delaying of order
     */
    void cancelOrder(Reasons reason);

    /**
     * <h>getCancellationReason</h>
     * <p>get the cancellation reasons for the order</p>
     */
    void getCancellationReason();

    /**
     * <h>getDueTime</h>
     * <p>get the due date and time of the order</p>
     *
     * @return date and time of the order
     */
    String getDueTime();

    /**
     * <h>getTimestamp</h>
     * <p>get the time stamp of the order placed</p>
     *
     * @return timestamp of the order placed
     */
    String getTimestamp();

    /**
     * <h>dispatch</h>
     * <p>dispatch the order</p>
     */
    void dispatch();

    /**
     * <h>updateOrder</h>
     * <p>update the order status</p>
     *
     * @param status new status of the order
     */
    void updateOrder(int status, String time);

    /**
     * ready for pick up
     */
    void readyForPickUp(String driverId);

    /**
     * <h>getUserHistory</h>
     * <p>get the user history</p>
     */
    void getUserHistory();

    /**
     * <h>getBundleData</h>
     * <p>get the bundle data of the order</p>
     *
     * @param extras bundle data related to the selected order
     */
    void getBundleData(Bundle extras);

    /**
     * <h>getFares</h>
     * <p>get the fares related to the order</p>
     *
     * @param subTotal subtotal of the order
     */
    void getFares(float subTotal);

    /**
     * <h>getStoreId</h>
     * <p>get the store id of the order</p>
     *
     * @return the store id the order is related to
     */
    String getStoreId();

    String getLanguage();

    int getStoreType();

    void cancelDispatch();

    void rejectOrder(String orderId, String reason, String type);

    boolean isCityLogin();

    void applyPack(ArrayList<ProductOrdersRequest> productOrdersRequests);

    void pickApi(String productOrderId, int quantity, String productImage);

    void confirmOrder(String productOrderId, int quantity);

    void confirmUnAvailability(Products mItems, int quantity, String reason);

    void confirmationRequest(String orderId, String reason, String type);

    int getDriverType();

    void orderPickedRequest(String orderId);

  }
}
