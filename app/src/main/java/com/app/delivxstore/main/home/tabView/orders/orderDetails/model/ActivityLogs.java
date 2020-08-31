package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import android.location.Location;

import java.io.Serializable;

public class ActivityLogs implements Serializable {

    private String message;

    private String timeStamp;

    private Location location;

    private String stausUpdatedBy;

    private String isoDate;

    private String userId;

    private String state;

    private String ip;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getTimeStamp ()
    {
        return timeStamp;
    }

    public void setTimeStamp (String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getStausUpdatedBy ()
    {
        return stausUpdatedBy;
    }

    public void setStausUpdatedBy (String stausUpdatedBy)
    {
        this.stausUpdatedBy = stausUpdatedBy;
    }

    public String getIsoDate ()
    {
        return isoDate;
    }

    public void setIsoDate (String isoDate)
    {
        this.isoDate = isoDate;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getIp ()
    {
        return ip;
    }

    public void setIp (String ip)
    {
        this.ip = ip;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", timeStamp = "+timeStamp+", location = "+location+", stausUpdatedBy = "+stausUpdatedBy+", isoDate = "+isoDate+", userId = "+userId+", state = "+state+", ip = "+ip+"]";
    }
}
