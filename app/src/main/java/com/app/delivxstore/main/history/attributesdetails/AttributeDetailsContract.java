package com.app.delivxstore.main.history.attributesdetails;

import android.content.Intent;
import com.app.delivxstore.main.history.model.AttributesItem;
import java.util.List;

/*defines two interface one for view and
 * other for presenter*/
public interface AttributeDetailsContract {
  /*declares some methods to be implemented
   * in AttributesDetailsActivity*/
  interface AttributesView {

    /*set values to views*/
    void setValues(List<AttributesItem> attributesItems);
  }

  /*declares some methods to be implemented
   * in AttributesDetailsPresenter*/
  interface Presenter {

    /*received data from source*/
    void getBundleData(Intent intent);
  }
}
