package com.app.delivxstore.main.wallet.stripe;

import android.content.Intent;
import android.os.Bundle;
import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.wallet.stripe.model.response.StripeData;

/*defines two interface one for
 * StripAccountFragment Fragment and other one
 * for StripAccountPresenter*/
public interface StripeAccountContract {
  /*declares some methods to be implemented
   * in StripAccountFragment for updating view*/
  interface StripView extends BaseView {

    /*set date of birth to view*/
    void setDob(String format);

    /*crop image to fit into view*/
    void startCropImage();

    /*set strip pic*/
    void setStripPic(String url);

    /*takes to BankDetails Fragment*/
    void getBack();

    /*set values to views*/
    void setViews(StripeData stripeData);

    /*show error message*/
    void error(String message);
  }

  /*declares some methods to be implemented
   * in StripAccountPresenter for executing
   * user request*/
  interface StripPresenter {

    /* attach view to Presenter*/
    void attachView(StripeAccountFragment stripeAccountFragment);

    /*format date to required format and update the view*/
    void setDate(int year, int month, int dayOfMonth);

    void validateFields(String firstName, String lastName,
                        String ssn, String address,
                        String city, String state, String zipCode);

    /*handles on activity result of fragment*/
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /*get bundle data from calling fragment*/
    void getBundleData(Bundle arguments);
  }
}
