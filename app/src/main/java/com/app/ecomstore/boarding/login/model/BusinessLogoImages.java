package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessLogoImages implements Parcelable {
    @SerializedName("businessLogoMobilePath")
    @Expose
    private String businessLogoMobilePath;
    @SerializedName("businessLogoWebPath")
    @Expose
    private String businessLogoWebPath;
    @SerializedName("businessLogoThumbPath")
    @Expose
    private String businessLogoThumbPath;

    protected BusinessLogoImages(Parcel in) {
        businessLogoMobilePath = in.readString();
        businessLogoWebPath = in.readString();
        businessLogoThumbPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(businessLogoMobilePath);
        dest.writeString(businessLogoWebPath);
        dest.writeString(businessLogoThumbPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BusinessLogoImages> CREATOR = new Creator<BusinessLogoImages>() {
        @Override
        public BusinessLogoImages createFromParcel(Parcel in) {
            return new BusinessLogoImages(in);
        }

        @Override
        public BusinessLogoImages[] newArray(int size) {
            return new BusinessLogoImages[size];
        }
    };

    public String getBusinessLogoMobilePath ()
    {
        return businessLogoMobilePath;
    }

    public void setBusinessLogoMobilePath (String businessLogoMobilePath)
    {
        this.businessLogoMobilePath = businessLogoMobilePath;
    }

    public String getBusinessLogoWebPath ()
    {
        return businessLogoWebPath;
    }

    public void setBusinessLogoWebPath (String businessLogoWebPath)
    {
        this.businessLogoWebPath = businessLogoWebPath;
    }

    public String getBusinessLogoThumbPath ()
    {
        return businessLogoThumbPath;
    }

    public void setBusinessLogoThumbPath (String businessLogoThumbPath)
    {
        this.businessLogoThumbPath = businessLogoThumbPath;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [businessLogoMobilePath = "+businessLogoMobilePath+", businessLogoWebPath = "+businessLogoWebPath+", businessLogoThumbPath = "+businessLogoThumbPath+"]";
    }
}
