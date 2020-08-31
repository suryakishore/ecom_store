package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeadOffice implements Parcelable {
    @SerializedName("headOfficeCountryCode")
    @Expose
    private String headOfficeCountryCode;
    @SerializedName("headOfficeAddress")
    @Expose
    private String headOfficeAddress;
    @SerializedName("headOfficeCity")
    @Expose
    private String headOfficeCity;
    @SerializedName("headOfficeLongi")
    @Expose
    private String headOfficeLongi;
    @SerializedName("headOfficeArea")
    @Expose
    private String headOfficeArea;
    @SerializedName("headOfficeCountryId")
    @Expose
    private String headOfficeCountryId;
    @SerializedName("headOfficeLat")
    @Expose
    private String headOfficeLat;
    @SerializedName("headOfficeNumber")
    @Expose
    private String headOfficeNumber;
    @SerializedName("headOfficeCountry")
    @Expose
    private String headOfficeCountry;
    @SerializedName("headOfficeZipCode")
    @Expose
    private String headOfficeZipCode;
    @SerializedName("headOfficeCityId")
    @Expose
    private String headOfficeCityId;

    protected HeadOffice(Parcel in) {
        headOfficeCountryCode = in.readString();
        headOfficeAddress = in.readString();
        headOfficeCity = in.readString();
        headOfficeLongi = in.readString();
        headOfficeArea = in.readString();
        headOfficeCountryId = in.readString();
        headOfficeLat = in.readString();
        headOfficeNumber = in.readString();
        headOfficeCountry = in.readString();
        headOfficeZipCode = in.readString();
        headOfficeCityId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headOfficeCountryCode);
        dest.writeString(headOfficeAddress);
        dest.writeString(headOfficeCity);
        dest.writeString(headOfficeLongi);
        dest.writeString(headOfficeArea);
        dest.writeString(headOfficeCountryId);
        dest.writeString(headOfficeLat);
        dest.writeString(headOfficeNumber);
        dest.writeString(headOfficeCountry);
        dest.writeString(headOfficeZipCode);
        dest.writeString(headOfficeCityId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HeadOffice> CREATOR = new Creator<HeadOffice>() {
        @Override
        public HeadOffice createFromParcel(Parcel in) {
            return new HeadOffice(in);
        }

        @Override
        public HeadOffice[] newArray(int size) {
            return new HeadOffice[size];
        }
    };

    public String getHeadOfficeCountryCode ()
    {
        return headOfficeCountryCode;
    }

    public void setHeadOfficeCountryCode (String headOfficeCountryCode)
    {
        this.headOfficeCountryCode = headOfficeCountryCode;
    }

    public String getHeadOfficeAddress ()
    {
        return headOfficeAddress;
    }

    public void setHeadOfficeAddress (String headOfficeAddress)
    {
        this.headOfficeAddress = headOfficeAddress;
    }

    public String getHeadOfficeCity ()
    {
        return headOfficeCity;
    }

    public void setHeadOfficeCity (String headOfficeCity)
    {
        this.headOfficeCity = headOfficeCity;
    }

    public String getHeadOfficeLongi ()
    {
        return headOfficeLongi;
    }

    public void setHeadOfficeLongi (String headOfficeLongi)
    {
        this.headOfficeLongi = headOfficeLongi;
    }

    public String getHeadOfficeArea ()
    {
        return headOfficeArea;
    }

    public void setHeadOfficeArea (String headOfficeArea)
    {
        this.headOfficeArea = headOfficeArea;
    }

    public String getHeadOfficeCountryId ()
    {
        return headOfficeCountryId;
    }

    public void setHeadOfficeCountryId (String headOfficeCountryId)
    {
        this.headOfficeCountryId = headOfficeCountryId;
    }

    public String getHeadOfficeLat ()
    {
        return headOfficeLat;
    }

    public void setHeadOfficeLat (String headOfficeLat)
    {
        this.headOfficeLat = headOfficeLat;
    }

    public String getHeadOfficeNumber ()
    {
        return headOfficeNumber;
    }

    public void setHeadOfficeNumber (String headOfficeNumber)
    {
        this.headOfficeNumber = headOfficeNumber;
    }

    public String getHeadOfficeCountry ()
    {
        return headOfficeCountry;
    }

    public void setHeadOfficeCountry (String headOfficeCountry)
    {
        this.headOfficeCountry = headOfficeCountry;
    }

    public String getHeadOfficeZipCode ()
    {
        return headOfficeZipCode;
    }

    public void setHeadOfficeZipCode (String headOfficeZipCode)
    {
        this.headOfficeZipCode = headOfficeZipCode;
    }

    public String getHeadOfficeCityId ()
    {
        return headOfficeCityId;
    }

    public void setHeadOfficeCityId (String headOfficeCityId)
    {
        this.headOfficeCityId = headOfficeCityId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [headOfficeCountryCode = "+headOfficeCountryCode+", headOfficeAddress = "+headOfficeAddress+", headOfficeCity = "+headOfficeCity+", headOfficeLongi = "+headOfficeLongi+", headOfficeArea = "+headOfficeArea+", headOfficeCountryId = "+headOfficeCountryId+", headOfficeLat = "+headOfficeLat+", headOfficeNumber = "+headOfficeNumber+", headOfficeCountry = "+headOfficeCountry+", headOfficeZipCode = "+headOfficeZipCode+", headOfficeCityId = "+headOfficeCityId+"]";
    }
}
