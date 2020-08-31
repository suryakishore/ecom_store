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

package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable;

import com.app.delivxstore.BasePresenter;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.mobileView.orderDetails.models.Products;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductSubstituteData;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models.ProductReason;
import java.util.ArrayList;

/**
 * Provide interface methods for view and presenter
 * for product unavailable
 */
public interface ProductUnavailableSubstituteContract {

    /**
     * Provide interface methods for view
     * for order unavailable
     */
    interface OrderUnavailableView extends BaseView {

        /**
         * <h>setReasons</h>
         * <p>Set the reasons for product unavailability</p>
         * @param data  list of reasons
         */
        void setReasons(ArrayList<ProductReason> data);

        /**
         * <h>setProductSubstitutes<h/>
         * <p>get the substitute products related to
         * the selected item which is not present at point of time</p>
         * @param data  the products list which can replace the selected product
         */
        void setProductSubstitutes(ArrayList<ProductSubstituteData> data);
        void onError(String msg);
        void finishActivity();
        void reasonSuccess();
    }

    /**
     * Provide interface methods for presenter
     * for order unavailable
     */
    interface OrderUnavailablePresenter extends BasePresenter {

        /**
         * <h>getLanguage</h>
         * <p>returns the current application language</p>
         * @return  current application language
         */
        String getLanguage();

        /**
         * <h>get reasons</h>
         * <p> get the reason for product unavailability</p>
         */
        void getReasons(String storeCategoryId,int reasonId);

        /**
         * <h>getSubstituteProducts</h>
         * <p>Get the products which can be used to substitute
         * the selected item</p>
         */
        void getSubstituteProducts();

        /**
         * <h>setItemData</h>
         * <p>Set the item data in the presenter</p>
         * @param mItems    Items data chose by the user
         */
        void setItemData(Products mItems,int selectedCount);

        void confirmUnAvailability(String reason);

        int getStoreType();

    }

}
