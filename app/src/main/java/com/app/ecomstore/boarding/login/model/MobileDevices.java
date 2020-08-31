package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileDevices implements Parcelable {
    @SerializedName("currentlyActive")
    @Expose
    private String currentlyActive;

    protected MobileDevices(Parcel in) {
        currentlyActive = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentlyActive);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MobileDevices> CREATOR = new Creator<MobileDevices>() {
        @Override
        public MobileDevices createFromParcel(Parcel in) {
            return new MobileDevices(in);
        }

        @Override
        public MobileDevices[] newArray(int size) {
            return new MobileDevices[size];
        }
    };

    public String getCurrentlyActive ()
    {
        return currentlyActive;
    }

    public void setCurrentlyActive (String currentlyActive)
    {
        this.currentlyActive = currentlyActive;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [currentlyActive = "+currentlyActive+"]";
    }
}
