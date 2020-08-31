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

package com.app.delivxstore.main.home.tabView.orders.newpending.pending;

import android.os.Bundle;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.models.Data;
import java.util.ArrayList;

/**
 * Provide interface methods for view and presenter
 * for new order
 */
public interface PendingOrderContract {
  /**
   * Provide interface methods for view
   * for new order
   */
  interface ViewOperations extends BaseView {
    /**
     * <h>setStoreName</h>
     * <p>set the store name</p>
     *
     * @param storeName store name
     */
    void setStoreName(String storeName);

    /**
     * <h>setTitle</h>
     * <p>set title for the now order</p>
     *
     * @param tablet is tablet or mobile
     * @param type   type of order
     */
    void setTitle(boolean tablet, String type);

    /**
     * <h>setListData</h>
     * <p>set the new order list</p>
     *
     * @param newOrderList new order list
     */
    void setListData(ArrayList<Data> newOrderList, int status, int penCount, int pendingCount,boolean isCityLogin);

    void setListData(ArrayList<Data> newOrderList, int status, boolean isCityLogin);

    /**
     * <h>setView</h>
     * <p>set the views for the
     * new order</p>
     */
    void setView();

    /**
     * <h>showMessage</h>
     * <p>Show the message to the user if any background failure comes</p>
     *
     * @param statusMsg the message to be shown
     */
    void showMessage(String statusMsg);
  }

  /**
   * Provide interface methods for presenter
   * for new order
   */
  interface PresenterOperations {
    /**
     * <h>subscribeFilterData</h>
     * <p>subscribe to filter data</p>
     */
    void subscribeFilterData();

    /**
     * <h>attachView</h>
     * <p>attach view for the new order</p>
     *
     * @param viewOperations view
     */
    void attachView(
        PendingOrderContract.ViewOperations viewOperations);

    void getNewOrderApi(int skip, int limit);

    /**
     * <h>getView</h>
     * <p>bundle data</p>
     *
     * @param arguments data from the activity
     */
    void getView(Bundle arguments);

    boolean isLoading();

    /**
     * <h>emitSearchData</h>
     * <p>emit search data for the entered text</p>
     *
     * @param searchText filtered text
     */
    void emitSearchData(String searchText);
  }
}
