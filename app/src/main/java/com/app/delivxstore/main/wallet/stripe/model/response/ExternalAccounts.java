package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ExternalAccounts implements Parcelable {

  public static final Creator<ExternalAccounts> CREATOR = new Creator<ExternalAccounts>() {
    @Override
    public ExternalAccounts createFromParcel(Parcel in) {
      return new ExternalAccounts(in);
    }

    @Override
    public ExternalAccounts[] newArray(int size) {
      return new ExternalAccounts[size];
    }
  };
  @SerializedName("data")
  @Expose
  private ArrayList<AccountData> data;
  @SerializedName("total_count")
  @Expose
  private int totalCount;
  @SerializedName("has_more")
  @Expose
  private boolean hasMore;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("object")
  @Expose
  private String object;

  protected ExternalAccounts(Parcel in) {
    data = in.createTypedArrayList(AccountData.CREATOR);
    totalCount = in.readInt();
    hasMore = in.readByte() != 0;
    url = in.readString();
    object = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(data);
    dest.writeInt(totalCount);
    dest.writeByte((byte)(hasMore ? 1 : 0));
    dest.writeString(url);
    dest.writeString(object);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public ArrayList<AccountData> getData() {
    return data;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public boolean isHasMore() {
    return hasMore;
  }

  public String getUrl() {
    return url;
  }

  public String getObject() {
    return object;
  }

  @Override
  public String toString() {
    return "ExternalAccounts{" + "data = '" + data + '\'' + ",total_count = '" + totalCount + '\'' + ",has_more = '" + hasMore + '\'' + ",url = '" + url + '\'' + ",object = '" + object + '\'' + "}";
  }
}