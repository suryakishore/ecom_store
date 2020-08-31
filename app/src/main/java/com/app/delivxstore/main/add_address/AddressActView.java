package com.app.delivxstore.main.add_address;



import com.app.delivxstore.BaseView;
import com.app.delivxstore.utility.AutocompleteInfo;

import java.util.List;

/**
 * Created by dell on 01-Feb-18.
 */

public interface AddressActView extends BaseView
{
    void onError(String msg);
    void setAddress(String address);
    void setHomeType();
    void setOther(String text);
    void setWorkType();
    void setMarker(double lat, double longi);
    void addressList(List<AutocompleteInfo> list);
     void startLocationAct();
   void stopAct();
}
