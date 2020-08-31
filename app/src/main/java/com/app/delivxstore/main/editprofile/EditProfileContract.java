package com.app.delivxstore.main.editprofile;

import com.app.delivxstore.BaseView;

/*defines two interface for view and presenter*/
public interface EditProfileContract {
  /*declares some method to be implemented in Activity fro updating views*/
  interface EditProfileView extends BaseView {

    /*show error messages*/
    void onError(String message);

    /*takes to back screen*/
    void onBackPress();

    /*disable confirm button*/
    void disableButton();

    /*show success Ui*/
    void onSuccess();
  }

  /*declares some methods to be implemented in EditProfilePresenter*/
  interface Presenter {

    /*extracts language code from shared preferences*/
    String getLanguageCode();

    /*validate password and call api*/
    void updatePassword(String newPass, String confirmPass);
  }
}
