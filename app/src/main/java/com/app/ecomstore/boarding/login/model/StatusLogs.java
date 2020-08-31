package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusLogs implements Parcelable {
    @SerializedName("actionByUserId")
    @Expose
    private String actionByUserId;
    @SerializedName("actionByUserType")
    @Expose
    private String actionByUserType;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    protected StatusLogs(Parcel in) {
        actionByUserId = in.readString();
        actionByUserType = in.readString();
        action = in.readString();
        timestamp = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actionByUserId);
        dest.writeString(actionByUserType);
        dest.writeString(action);
        dest.writeString(timestamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StatusLogs> CREATOR = new Creator<StatusLogs>() {
        @Override
        public StatusLogs createFromParcel(Parcel in) {
            return new StatusLogs(in);
        }

        @Override
        public StatusLogs[] newArray(int size) {
            return new StatusLogs[size];
        }
    };

    public String getActionByUserId ()
    {
        return actionByUserId;
    }

    public void setActionByUserId (String actionByUserId)
    {
        this.actionByUserId = actionByUserId;
    }

    public String getActionByUserType ()
    {
        return actionByUserType;
    }

    public void setActionByUserType (String actionByUserType)
    {
        this.actionByUserType = actionByUserType;
    }

    public String getAction ()
    {
        return action;
    }

    public void setAction (String action)
    {
        this.action = action;
    }

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [actionByUserId = "+actionByUserId+", actionByUserType = "+actionByUserType+", action = "+action+", timestamp = "+timestamp+"]";
    }
}
