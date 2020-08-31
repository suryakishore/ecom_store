package com.app.delivxstore.main.language.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class LanguageResponse implements Parcelable {

  public static final Creator<LanguageResponse> CREATOR = new Creator<LanguageResponse>() {
    @Override
    public LanguageResponse createFromParcel(Parcel in) {
      return new LanguageResponse(in);
    }

    @Override
    public LanguageResponse[] newArray(int size) {
      return new LanguageResponse[size];
    }
  };
  @SerializedName("data")
  @Expose
  private ArrayList<LanguageList> data;
  @SerializedName("message")
  @Expose
  private String message;

  protected LanguageResponse(Parcel in) {
    data = in.createTypedArrayList(LanguageList.CREATOR);
    message = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(data);
    dest.writeString(message);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public ArrayList<LanguageList> getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }
}