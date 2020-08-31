package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreName implements Parcelable {
    @SerializedName("en")
    @Expose
    private String en;

    protected StoreName(Parcel in) {
        en = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(en);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreName> CREATOR = new Creator<StoreName>() {
        @Override
        public StoreName createFromParcel(Parcel in) {
            return new StoreName(in);
        }

        @Override
        public StoreName[] newArray(int size) {
            return new StoreName[size];
        }
    };

    public String getEn ()
    {
        return en;
    }

    public void setEn (String en)
    {
        this.en = en;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [en = "+en+"]";
    }
}
