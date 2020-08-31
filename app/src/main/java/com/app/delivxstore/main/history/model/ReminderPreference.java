package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ReminderPreference implements Parcelable {
  protected ReminderPreference(Parcel in) {
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ReminderPreference> CREATOR = new Creator<ReminderPreference>() {
    @Override
    public ReminderPreference createFromParcel(Parcel in) {
      return new ReminderPreference(in);
    }

    @Override
    public ReminderPreference[] newArray(int size) {
      return new ReminderPreference[size];
    }
  };
}
