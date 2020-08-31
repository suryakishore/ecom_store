package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryName implements Parcelable {
    @SerializedName("en")
    @Expose
    private String en;

    protected CategoryName(Parcel in) {
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

    public static final Creator<CategoryName> CREATOR = new Creator<CategoryName>() {
        @Override
        public CategoryName createFromParcel(Parcel in) {
            return new CategoryName(in);
        }

        @Override
        public CategoryName[] newArray(int size) {
            return new CategoryName[size];
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
