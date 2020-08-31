package com.app.delivxstore.main.language;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.app.delivxstore.R;
import com.app.delivxstore.databinding.FragmentLanguageBinding;
import com.app.delivxstore.main.MainActivity;
import com.app.delivxstore.main.language.model.LanguageList;
import com.app.delivxstore.utility.Utility;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Inject;

/*shows screen for language selection*/
public class LanguageFragment extends DaggerFragment implements LanguageContract.LanguageView {
  @Inject
  LanguageContract.Presenter presenter;
  private FragmentLanguageBinding mBinding;
  private LanguageAdapter mLanguageAdapter;
  private ArrayList<LanguageList> mLanguageLists = new ArrayList<>();

  @Inject
  public LanguageFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mBinding = FragmentLanguageBinding.inflate(inflater);
    presenter.attachView(this);
    mLanguageAdapter = new LanguageAdapter(this, mLanguageLists);
    mBinding.rvLanguage.setLayoutManager(new LinearLayoutManager(getActivity()));
    mBinding.rvLanguage.setAdapter(mLanguageAdapter);
    presenter.getLanguages();
    return mBinding.getRoot();
  }

  @Override
  public void showError(String errorMessage) {
    Utility.toastMessage(getActivity(), errorMessage);
  }

  @Override
  public void showProgress() {
    mBinding.pbLanguage.setVisibility(View.VISIBLE);
    Objects.requireNonNull(getActivity()).getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void hideProgress() {
    mBinding.pbLanguage.setVisibility(View.GONE);
    Objects.requireNonNull(getActivity()).getWindow().clearFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  @Override
  public void setLanguage(LanguageList languageList) {
    presenter.changeLanguage(languageList);
  }

  @Override
  public void goToMainActivity(String language) {
    Intent intent = new Intent(getActivity(), MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    Utility.toastMessage(getActivity(), String.format("%s %s", language,
        getString(R.string.languageSelected)));
  }

  @Override
  public void setRecyclerView(ArrayList<LanguageList> languageList) {
    mBinding.rvLanguage.setHasFixedSize(true);
    mLanguageLists.clear();
    mLanguageLists.addAll(languageList);
    for (int i = 0; i < languageList.size(); i++) {
      if (presenter.getLanguageCode().equals(languageList.get(i).getLanguageCode())) {
        languageList.get(i).setSelected(true);
        mLanguageAdapter.setPosition(i);
        break;
      }
    }
    mLanguageAdapter.notifyDataSetChanged();
    mBinding.btnSave.setOnClickListener(mLanguageAdapter);
  }
}
