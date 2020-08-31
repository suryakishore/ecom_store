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

package com.app.delivxstore.main.home.tabView.orders.preOrder.assign;

import android.os.Bundle;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.models.Data;
import java.util.ArrayList;

/**
 * Provide interface methods for view and presenter
 * for accepted order
 */
public interface DriverAssignContract {
  /**
   * Provide interface methods for view
   * for accepted order
   */
  interface ViewOperations extends BaseView {
    /**
     * <h>setStoreName</h>
     */
    void setStoreName(String storeName);

    void setTitle(boolean tablet, String type);

    void setListData(ArrayList<Data> orders, int status, int penCount,int driverCount,boolean isCityLogin);
    void setListData(ArrayList<Data> orders, int status,boolean isCityLogin);

    void setView();

    /**
     * <h>showMessage</h>
     * <p>Show the message to the user if any background failure comes</p>
     *
     * @param statusMsg the message to be shown
     */
    void showMessage(String statusMsg);

    /**
     * <h>updateOrdersList</h>
     * <p>Update the order based on
     * the search list</p>
     *
     * @param searchedText searched string
     */
    void updateOrdersList(String searchedText);
  }

  interface PresenterOperations {
    void subscribeFilterData();

    void attachView(DriverAssignContract.ViewOperations viewOperations);

    void getDriverAssignOrderApi(int skip, int limit);
//        void getOrders();

    void getView(Bundle arguments);

    boolean isLoading();
  }
}
