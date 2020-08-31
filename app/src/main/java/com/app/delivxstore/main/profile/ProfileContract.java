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

package com.app.delivxstore.main.profile;

import com.app.delivxstore.BaseView;
import com.app.ecomstore.boarding.login.model.LoginData;

/**
 * Provide interface methods for view and presenter
 * for profile
 */
public interface ProfileContract {

  /**
   * Provide interface methods for view
   * for profile
   */
  interface ViewOperations extends BaseView {

    /*set values in views*/
    void setValues(LoginData data);

    /*show error*/
    void showError(String message);

    /*logout and go to login activity*/
    void logout();

    /*set value for password field*/
    void setPassword(String password);
  }

  /**
   * Provide interface methods for presenter
   * for profile
   */
  interface PresenterOperations {

    /**
     * <h>attachView</h>
     * <p>Attach the view to the presenter</p>
     *
     * @param profileFragment view of the profile
     */
    void attachView(ProfileContract.ViewOperations profileFragment);

    /**
     * <h>logout</h>
     * <p>logout the user when the session
     * is expired</p>
     */
    void logout();

    /**
     * <h>logoutOnClick</h>
     * <p>Logout the user when the
     * user manually logout</p>
     */
    void logoutOnClick();

    /*extracts profile data from shared preferences*/
    void getProfileData();

    /*update password*/
    void updatePassword();
  }
}
