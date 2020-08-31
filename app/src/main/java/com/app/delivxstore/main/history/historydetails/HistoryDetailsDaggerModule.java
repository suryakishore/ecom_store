package com.app.delivxstore.main.history.historydetails;

import android.app.Activity;
import com.app.delivxstore.dagger.ActivityScoped;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class HistoryDetailsDaggerModule {
  @ActivityScoped
  @Binds
  abstract Activity getActivity(HistoryDetailsActivity historyDetailsActivity);

  @ActivityScoped
  @Binds
  abstract HistoryDetailsContract.HistoryDetailsView getView(HistoryDetailsActivity detailsActivity);

  @ActivityScoped
  @Binds
  abstract HistoryDetailsContract.Presenter getPresenter(HistoryDetailsPresenter historyDetailsPresenter);
}
