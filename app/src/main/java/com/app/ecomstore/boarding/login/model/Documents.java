package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Documents implements Parcelable {
    @SerializedName("commercialRegistrationNumber")
    @Expose
    private String commercialRegistrationNumber;
    @SerializedName("statusMsg")
    @Expose
    private String statusMsg;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("companyRegistrationDocument")
    @Expose
    private String companyRegistrationDocument;
    @SerializedName("commercialRegistrationExpiry")
    @Expose
    private String commercialRegistrationExpiry;
    @SerializedName("status")
    @Expose
    private String status;

    protected Documents(Parcel in) {
        commercialRegistrationNumber = in.readString();
        statusMsg = in.readString();
        _id = in.readString();
        companyRegistrationDocument = in.readString();
        commercialRegistrationExpiry = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commercialRegistrationNumber);
        dest.writeString(statusMsg);
        dest.writeString(_id);
        dest.writeString(companyRegistrationDocument);
        dest.writeString(commercialRegistrationExpiry);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Documents> CREATOR = new Creator<Documents>() {
        @Override
        public Documents createFromParcel(Parcel in) {
            return new Documents(in);
        }

        @Override
        public Documents[] newArray(int size) {
            return new Documents[size];
        }
    };

    public String getCommercialRegistrationNumber ()
    {
        return commercialRegistrationNumber;
    }

    public void setCommercialRegistrationNumber (String commercialRegistrationNumber)
    {
        this.commercialRegistrationNumber = commercialRegistrationNumber;
    }

    public String getStatusMsg ()
    {
        return statusMsg;
    }

    public void setStatusMsg (String statusMsg)
    {
        this.statusMsg = statusMsg;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getCompanyRegistrationDocument ()
    {
        return companyRegistrationDocument;
    }

    public void setCompanyRegistrationDocument (String companyRegistrationDocument)
    {
        this.companyRegistrationDocument = companyRegistrationDocument;
    }

    public String getCommercialRegistrationExpiry ()
    {
        return commercialRegistrationExpiry;
    }

    public void setCommercialRegistrationExpiry (String commercialRegistrationExpiry)
    {
        this.commercialRegistrationExpiry = commercialRegistrationExpiry;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [commercialRegistrationNumber = "+commercialRegistrationNumber+", statusMsg = "+statusMsg+", _id = "+_id+", companyRegistrationDocument = "+companyRegistrationDocument+", commercialRegistrationExpiry = "+commercialRegistrationExpiry+", status = "+status+"]";
    }
}
