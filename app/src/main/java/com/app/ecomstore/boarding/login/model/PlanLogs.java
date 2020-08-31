package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanLogs implements Parcelable {
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("planName")
    @Expose
    private String planName;
    @SerializedName("planId")
    @Expose
    private String planId;

    protected PlanLogs(Parcel in) {
        timeStamp = in.readString();
        planName = in.readString();
        planId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timeStamp);
        dest.writeString(planName);
        dest.writeString(planId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlanLogs> CREATOR = new Creator<PlanLogs>() {
        @Override
        public PlanLogs createFromParcel(Parcel in) {
            return new PlanLogs(in);
        }

        @Override
        public PlanLogs[] newArray(int size) {
            return new PlanLogs[size];
        }
    };

    public String getTimeStamp ()
    {
        return timeStamp;
    }

    public void setTimeStamp (String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getPlanName ()
    {
        return planName;
    }

    public void setPlanName (String planName)
    {
        this.planName = planName;
    }

    public String getPlanId ()
    {
        return planId;
    }

    public void setPlanId (String planId)
    {
        this.planId = planId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timeStamp = "+timeStamp+", planName = "+planName+", planId = "+planId+"]";
    }
}
