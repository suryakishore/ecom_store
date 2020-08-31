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

package com.app.delivxstore.main.dispatchDetails;

import com.app.delivxstore.BasePresenter;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.model.OrderedItemDetails;

/**
 * Provide interface methods for view and presenter
 * for dispatcher details
 */
public interface DispatchDetailsContract {

    /**
     * Provide interface methods for view
     * for dispatcher details
     */
    interface DispatchDetailsView extends BaseView {

        /**
         * <h>setViews</h>
         * <p>set the view for the order details</p>
         * @param mOrderDetails order details data
         */
        void setViews(OrderedItemDetails mOrderDetails);
    }

    /**
     * Provide interface methods for presenter
     * for dispatcher details
     */
    interface DispatchDetailsPresenter extends BasePresenter {

        /**
         * <h>getOrderDetails</h>
         * <p></p>
         * @param mOrderId
         */
        void getOrderDetails(String mOrderId);
    }
}
