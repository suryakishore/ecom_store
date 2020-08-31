package com.app.delivxstore.main.language.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageList implements Parcelable {

  public static final Creator<LanguageList> CREATOR = new Creator<LanguageList>() {
    @Override
    public LanguageList createFromParcel(Parcel in) {
      return new LanguageList(in);
    }

    @Override
    public LanguageList[] newArray(int size) {
      return new LanguageList[size];
    }
  };
  private boolean isSelected = false;
  @SerializedName("Active")
  @Expose
  private int active;
  @SerializedName("lan_id")
  @Expose
  private int lanId;
  @SerializedName("langCode")
  @Expose
  private String langCode;
  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("lan_name")
  @Expose
  private String lanName;

  public LanguageList() {

  }

  protected LanguageList(Parcel in) {
    active = in.readInt();
    lanId = in.readInt();
    langCode = in.readString();
    id = in.readString();
    lanName = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(active);
    dest.writeInt(lanId);
    dest.writeString(langCode);
    dest.writeString(id);
    dest.writeString(lanName);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public int getLanId() {
    return lanId;
  }

  public String getLanguageCode() {
    return langCode;
  }

  public void setLanguageCode(String langCode) {
    this.langCode = langCode;
  }

  public String getId() {
    return id;
  }

  public String getLanguageName() {
    return lanName;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}