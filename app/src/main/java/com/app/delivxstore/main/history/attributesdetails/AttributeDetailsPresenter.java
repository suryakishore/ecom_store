package com.app.delivxstore.main.history.attributesdetails;

import static com.app.delivxstore.utility.VariableConstants.ATTRIBUTE_DATA;

import android.content.Intent;
import com.app.delivxstore.main.history.model.AttributesItem;
import java.util.ArrayList;
import javax.inject.Inject;

/*interacts with local and remote database to
 * update attributes values*/
public class AttributeDetailsPresenter implements AttributeDetailsContract.Presenter {

  @Inject
  AttributeDetailsContract.AttributesView attributesView;

  @Inject
  AttributeDetailsPresenter() {

  }

  @Override
  public void getBundleData(Intent intent) {
    ArrayList<AttributesItem> attributesItems =
        (ArrayList<AttributesItem>)intent.getSerializableExtra(ATTRIBUTE_DATA);
    attributesView.setValues(attributesItems);
  }
}
