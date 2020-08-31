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

package com.app.delivxstore.main.home;

import com.app.delivxstore.BaseView;

/**
 * Provide interface methods for view and presenter
 * for home
 */
public interface HomeFragmentContract {
  /**
   * Provide interface methods for view
   * for home
   */
  interface ViewOperations extends BaseView {
    /**
     * <h>logout</h>
     * <p>Navigate the user to the login screen
     * when session expired</p>
     */
    void logout();

    /**
     * <h>setView</h>
     * <p>Set the views depending on the display (mobile/tablet)</p>
     *
     * @param tablet true if is tablet
     */
    void setView(boolean tablet, int storeType, int forceAccept, int autoDispatch, int driverType
        , int packageType);

    /**
     * <h>showMessage</h>
     * <p>Show the message to the user if any background failure comes</p>
     *
     * @param statusMsg the message to be shown
     */
    void showMessage(String statusMsg);

    /**
     * <h>setCountValues</h>
     * <p>Set the count values for each tabs related to orders</p>
     *
     * @param newOrder        new order count
     * @param acceptedOrder   accepted order count
     * @param cancelledOrder  dispatched order count
     * @param packedOrder     assigned order count
     * @param readyOrder      order in progress count
     * @param inDispatchOrder order in dispatched count
     * @param completedOrder  order in delivery count
     */
    void setCountValues(
        int storeType,
        String newOrder,
        String acceptedOrder,
        String cancelledOrder,
        String packedOrder,
        String readyOrder,
        String checkedOrder,
        String inDispatchOrder,
        String completedOrder);

    void startLogin();
    void tokenExpire();

  }

  /**
   * Provide interface methods for presenter
   * for home
   */
  interface PresenterOperations {
    /**
     * <h>checkScreenSize</h>
     * <p>Check the screen size if its
     * a mobile or tablet</p>
     */
    void checkScreenSize();

    /**
     * <h>attachView</h>
     * <p>Attach the view to the presenter</p>
     *
     * @param viewOperations view interface
     */
    void attachView(HomeFragmentContract.ViewOperations viewOperations);

    /**
     * <h>getOrders</h>
     * <p>get the orders detail</p>
     */
    void getOrders();

    /**
     * <h>logout</h>
     * <p>navigate the user to login screen</p>
     */
    void logout();

    /**
     * <h>emitSearchData</h>
     * <p>emit search data for the entered text</p>
     *
     * @param searchText filtered text
     */
    void emitSearchData(String searchText);
  }
}
