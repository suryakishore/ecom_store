package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Timestamps implements Parcelable {

  @SerializedName("inDispatch")
  @Expose
  private String inDispatch;
  @SerializedName("created")
  @Expose
  private String created;
  @SerializedName("readyForPickup")
  @Expose
  private String readyForPickup;
  @SerializedName("accepted")
  @Expose
  private String accepted;
  @SerializedName("cancelled")
  @Expose
  private String cancelled;
  @SerializedName("completed")
  @Expose
  private String completed;
  @SerializedName("packed")
  @Expose
  private String packed;

  protected Timestamps(Parcel in) {
    inDispatch = in.readString();
    created = in.readString();
    readyForPickup = in.readString();
    accepted = in.readString();
    cancelled = in.readString();
    completed = in.readString();
    packed = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(inDispatch);
    dest.writeString(created);
    dest.writeString(readyForPickup);
    dest.writeString(accepted);
    dest.writeString(cancelled);
    dest.writeString(completed);
    dest.writeString(packed);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Timestamps> CREATOR = new Creator<Timestamps>() {
    @Override
    public Timestamps createFromParcel(Parcel in) {
      return new Timestamps(in);
    }

    @Override
    public Timestamps[] newArray(int size) {
      return new Timestamps[size];
    }
  };

  public String getInDispatch() {
    return inDispatch;
  }

  public String getCreated() {
    return created;
  }

  public String getReadyForPickup() {
    return readyForPickup;
  }

  public String getAccepted() {
    return accepted;
  }

  public String getCancelled() {
    return cancelled;
  }

  public String getCompleted() {
    return completed;
  }

  public String getPacked() {
    return packed;
  }
}