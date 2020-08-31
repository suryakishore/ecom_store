package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class AddOns implements Parcelable {
  public static final Creator<AddOns> CREATOR = new Creator<AddOns>() {
    @Override
    public AddOns createFromParcel(Parcel in) {
      return new AddOns(in);
    }

    @Override
    public AddOns[] newArray(int size) {
      return new AddOns[size];
    }
  };
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("packId")
  @Expose
  private String packId;
  @SerializedName("addOnGrou")
  @Expose
  private ArrayList<AddOnGroup> addOnGroup;

  protected AddOns(Parcel in) {
    id = in.readString();
    packId = in.readString();
    addOnGroup = in.createTypedArrayList(AddOnGroup.CREATOR);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeString(packId);
    dest.writeTypedList(addOnGroup);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPackId() {
    return packId;
  }

  public void setPackId(String packId) {
    this.packId = packId;
  }

  public ArrayList<AddOnGroup> getAddOnGroup() {
    return addOnGroup;
  }

  public void setAddOnGroup(ArrayList<AddOnGroup> addOnGroup) {
    this.addOnGroup = addOnGroup;
  }
}
