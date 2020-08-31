package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class Activities implements Serializable {

    private String time;

    private String status;

    private String isoDate;

//    private String long;

    private String msg;

    private String bid;

    private String lat;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getIsoDate ()
    {
        return isoDate;
    }

    public void setIsoDate (String isoDate)
    {
        this.isoDate = isoDate;
    }

//    public String getLong ()
//    {
//        return long;
//    }
//
//    public void setLong (String long)
//    {
//        this.long = long;
//    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public String getBid ()
    {
        return bid;
    }

    public void setBid (String bid)
    {
        this.bid = bid;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+", status = "+status+", isoDate = "+isoDate+/*", long = "+long+*/", msg = "+msg+", bid = "+bid+", lat = "+lat+"]";
    }
}
