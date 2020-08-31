package com.app.delivxstore.manual_locate;


import com.app.delivxstore.BaseView;
import com.app.delivxstore.utility.AutocompleteInfo;
import com.google.android.gms.common.api.Status;

import java.util.List;

/**
 * <H>Interface for Search location. </H>
 * @author 3Embed
 */

interface SearchView extends BaseView
{

 /**
  * This method is get predicted  address list
  * @param addressList String array list of address main name
  */
 void addressList(List<AutocompleteInfo> addressList);

 /**
  * <p>This method is used to show toast to error.</p>
  * @param msg : message to be shown
  */
 void onError(String msg);

 /**
  * <P>Start MainActivity </P>
  */
 void startHome();

 /**
  *
  */
 void showDefaultMap();

 void locationDataSet();
 void sendSuccessData(double lat, double longi, String address);
 void clearSearch();
  void setCurrentLoc(String address);
  void showEmptyResult() ;
 void showGpsAlert(Status status);
 void stopAct();

}
