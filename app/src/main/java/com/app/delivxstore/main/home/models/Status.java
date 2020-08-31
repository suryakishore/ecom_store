package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Status implements Parcelable{
    @SerializedName("statusText")
    @Expose
    private String statusText;
    @SerializedName("updatedOnTimeStamp")
    @Expose
    private String updatedOnTimeStamp;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("expiryTimeForConfirmation")
    @Expose
    private String expiryTimeForConfirmation;

  /*  @SerializedName("customerConfirm")
    @Expose
    private Boolean customerConfirm;
*/
    protected Status(Parcel in) {
        statusText = in.readString();
        updatedOnTimeStamp = in.readString();
        updatedOn = in.readString();
        status = in.readString();
        expiryTimeForConfirmation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(statusText);
        dest.writeString(updatedOnTimeStamp);
        dest.writeString(updatedOn);
        dest.writeString(status);
        dest.writeString(expiryTimeForConfirmation);
/*
        dest.writeByte((byte) (customerConfirm ? 1 : 0));
*/
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    public String getStatusText ()
    {
        return statusText;
    }

    public void setStatusText (String statusText)
    {
        this.statusText = statusText;
    }

    public String getUpdatedOnTimeStamp ()
    {
        return updatedOnTimeStamp;
    }

    public void setUpdatedOnTimeStamp (String updatedOnTimeStamp)
    {
        this.updatedOnTimeStamp = updatedOnTimeStamp;
    }

    public String getUpdatedOn ()
    {
        return updatedOn;
    }

    public void setUpdatedOn (String updatedOn)
    {
        this.updatedOn = updatedOn;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getExpiryTimeForConfirmation() {
        return expiryTimeForConfirmation;
    }

    public void setExpiryTimeForConfirmation(String expiryTimeForConfirmation) {
        this.expiryTimeForConfirmation = expiryTimeForConfirmation;
    }

 /*   public boolean isCustomerConfirm() {
        return customerConfirm;
    }

    public void setCustomerConfirm(boolean customerConfirm) {
        this.customerConfirm = customerConfirm;
    }*/

    @Override
    public String toString()
    {
        return "Status [statusText = "+statusText+", updatedOnTimeStamp = "+updatedOnTimeStamp+", updatedOn = "+updatedOn+", status = "+status+"]";
    }
}
