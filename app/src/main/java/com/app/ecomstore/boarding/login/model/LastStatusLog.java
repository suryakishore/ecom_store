package com.app.ecomstore.boarding.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LastStatusLog implements Serializable {
    @SerializedName("actionByUserId")
    @Expose
    private String actionByUserId;
    @SerializedName("actionByUserType")
    @Expose
    private String actionByUserType;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

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

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
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
        return "ClassPojo [actionByUserId = "+actionByUserId+", actionByUserType = "+actionByUserType+", action = "+action+", status = "+status+", timestamp = "+timestamp+"]";
    }
}
