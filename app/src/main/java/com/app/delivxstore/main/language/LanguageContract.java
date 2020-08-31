package com.app.delivxstore.main.language;

import com.app.delivxstore.BaseView;
import com.app.delivxstore.main.language.model.LanguageList;
import java.util.ArrayList;

/*defines two interface one for view and
 * other for presenter*/
public interface LanguageContract {
  /*declares some methods to be implemented
   * in Language Fragment to update views*/
  interface LanguageView extends BaseView {

    /*set language as selected*/
    void setLanguage(LanguageList languageCode);

    /*opens main activity to get effects of language change*/
    void goToMainActivity(String languageName);

    /*show error message*/
    void showError(String errorMessage);

    /*set data to recyclerview*/
    void setRecyclerView(ArrayList<LanguageList> data);
  }

  /*declares some methods to be implemented
   * in LanguagePresenter to execute user request*/
  interface Presenter {
    /*attach view to presenter*/
    void attachView(LanguageFragment languageFragment);

    /*change language code to shared preference*/
    void changeLanguage(LanguageList languageList);
    /*call language api*/
    void getLanguages();
    String getLanguageCode();
  }
}
