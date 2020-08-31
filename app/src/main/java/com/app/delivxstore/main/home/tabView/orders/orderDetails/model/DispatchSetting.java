package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class DispatchSetting implements Serializable {

    private String laterBookingCentralDispatchRatio;

    private String scheduledBookingsOnOFF;

    private String dispatchTime;

    private String staffAcceptTime;

    private String centralDispatchExpriryTime;

    private String dispatchMode;

    private String DispatchRadius;

    private String centralDispatchTime;

    private String longHaulDispatchRadius;

    private String LaterBookingDispatchBeforeMinutes;

    private String dispatchStartMinutes;

    private String dispatchStartHour;

    private String driverAcceptTime;

    private String nowBookingAutoDispatchRatio;

    private String shortHaulDispatchRadius;

    private String nowBookingExpriryTime;

    private String nowBookingCentralDispatchRatio;

    private String timeForDriverAck;

    private String bookLaterExpQueueMin;

    private String laterBookingAutoDispatchRatio;

    private String dispatchDuration;

    private String LaterBookingDispatchBeforeHours;

    public String getLaterBookingCentralDispatchRatio ()
    {
        return laterBookingCentralDispatchRatio;
    }

    public void setLaterBookingCentralDispatchRatio (String laterBookingCentralDispatchRatio)
    {
        this.laterBookingCentralDispatchRatio = laterBookingCentralDispatchRatio;
    }

    public String getScheduledBookingsOnOFF ()
    {
        return scheduledBookingsOnOFF;
    }

    public void setScheduledBookingsOnOFF (String scheduledBookingsOnOFF)
    {
        this.scheduledBookingsOnOFF = scheduledBookingsOnOFF;
    }

    public String getDispatchTime ()
    {
        return dispatchTime;
    }

    public void setDispatchTime (String dispatchTime)
    {
        this.dispatchTime = dispatchTime;
    }

    public String getStaffAcceptTime ()
    {
        return staffAcceptTime;
    }

    public void setStaffAcceptTime (String staffAcceptTime)
    {
        this.staffAcceptTime = staffAcceptTime;
    }

    public String getCentralDispatchExpriryTime ()
    {
        return centralDispatchExpriryTime;
    }

    public void setCentralDispatchExpriryTime (String centralDispatchExpriryTime)
    {
        this.centralDispatchExpriryTime = centralDispatchExpriryTime;
    }

    public String getDispatchMode ()
    {
        return dispatchMode;
    }

    public void setDispatchMode (String dispatchMode)
    {
        this.dispatchMode = dispatchMode;
    }

    public String getDispatchRadius ()
    {
        return DispatchRadius;
    }

    public void setDispatchRadius (String DispatchRadius)
    {
        this.DispatchRadius = DispatchRadius;
    }

    public String getCentralDispatchTime ()
    {
        return centralDispatchTime;
    }

    public void setCentralDispatchTime (String centralDispatchTime)
    {
        this.centralDispatchTime = centralDispatchTime;
    }

    public String getLongHaulDispatchRadius ()
    {
        return longHaulDispatchRadius;
    }

    public void setLongHaulDispatchRadius (String longHaulDispatchRadius)
    {
        this.longHaulDispatchRadius = longHaulDispatchRadius;
    }

    public String getLaterBookingDispatchBeforeMinutes ()
    {
        return LaterBookingDispatchBeforeMinutes;
    }

    public void setLaterBookingDispatchBeforeMinutes (String LaterBookingDispatchBeforeMinutes)
    {
        this.LaterBookingDispatchBeforeMinutes = LaterBookingDispatchBeforeMinutes;
    }

    public String getDispatchStartMinutes ()
    {
        return dispatchStartMinutes;
    }

    public void setDispatchStartMinutes (String dispatchStartMinutes)
    {
        this.dispatchStartMinutes = dispatchStartMinutes;
    }

    public String getDispatchStartHour ()
    {
        return dispatchStartHour;
    }

    public void setDispatchStartHour (String dispatchStartHour)
    {
        this.dispatchStartHour = dispatchStartHour;
    }

    public String getDriverAcceptTime ()
    {
        return driverAcceptTime;
    }

    public void setDriverAcceptTime (String driverAcceptTime)
    {
        this.driverAcceptTime = driverAcceptTime;
    }

    public String getNowBookingAutoDispatchRatio ()
    {
        return nowBookingAutoDispatchRatio;
    }

    public void setNowBookingAutoDispatchRatio (String nowBookingAutoDispatchRatio)
    {
        this.nowBookingAutoDispatchRatio = nowBookingAutoDispatchRatio;
    }

    public String getShortHaulDispatchRadius ()
    {
        return shortHaulDispatchRadius;
    }

    public void setShortHaulDispatchRadius (String shortHaulDispatchRadius)
    {
        this.shortHaulDispatchRadius = shortHaulDispatchRadius;
    }

    public String getNowBookingExpriryTime ()
    {
        return nowBookingExpriryTime;
    }

    public void setNowBookingExpriryTime (String nowBookingExpriryTime)
    {
        this.nowBookingExpriryTime = nowBookingExpriryTime;
    }

    public String getNowBookingCentralDispatchRatio ()
    {
        return nowBookingCentralDispatchRatio;
    }

    public void setNowBookingCentralDispatchRatio (String nowBookingCentralDispatchRatio)
    {
        this.nowBookingCentralDispatchRatio = nowBookingCentralDispatchRatio;
    }

    public String getTimeForDriverAck ()
    {
        return timeForDriverAck;
    }

    public void setTimeForDriverAck (String timeForDriverAck)
    {
        this.timeForDriverAck = timeForDriverAck;
    }

    public String getBookLaterExpQueueMin ()
    {
        return bookLaterExpQueueMin;
    }

    public void setBookLaterExpQueueMin (String bookLaterExpQueueMin)
    {
        this.bookLaterExpQueueMin = bookLaterExpQueueMin;
    }

    public String getLaterBookingAutoDispatchRatio ()
    {
        return laterBookingAutoDispatchRatio;
    }

    public void setLaterBookingAutoDispatchRatio (String laterBookingAutoDispatchRatio)
    {
        this.laterBookingAutoDispatchRatio = laterBookingAutoDispatchRatio;
    }

    public String getDispatchDuration ()
    {
        return dispatchDuration;
    }

    public void setDispatchDuration (String dispatchDuration)
    {
        this.dispatchDuration = dispatchDuration;
    }

    public String getLaterBookingDispatchBeforeHours ()
    {
        return LaterBookingDispatchBeforeHours;
    }

    public void setLaterBookingDispatchBeforeHours (String LaterBookingDispatchBeforeHours)
    {
        this.LaterBookingDispatchBeforeHours = LaterBookingDispatchBeforeHours;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [laterBookingCentralDispatchRatio = "+laterBookingCentralDispatchRatio+", scheduledBookingsOnOFF = "+scheduledBookingsOnOFF+", dispatchTime = "+dispatchTime+", staffAcceptTime = "+staffAcceptTime+", centralDispatchExpriryTime = "+centralDispatchExpriryTime+", dispatchMode = "+dispatchMode+", DispatchRadius = "+DispatchRadius+", centralDispatchTime = "+centralDispatchTime+", longHaulDispatchRadius = "+longHaulDispatchRadius+", LaterBookingDispatchBeforeMinutes = "+LaterBookingDispatchBeforeMinutes+", dispatchStartMinutes = "+dispatchStartMinutes+", dispatchStartHour = "+dispatchStartHour+", driverAcceptTime = "+driverAcceptTime+", nowBookingAutoDispatchRatio = "+nowBookingAutoDispatchRatio+", shortHaulDispatchRadius = "+shortHaulDispatchRadius+", nowBookingExpriryTime = "+nowBookingExpriryTime+", nowBookingCentralDispatchRatio = "+nowBookingCentralDispatchRatio+", timeForDriverAck = "+timeForDriverAck+", bookLaterExpQueueMin = "+bookLaterExpQueueMin+", laterBookingAutoDispatchRatio = "+laterBookingAutoDispatchRatio+", dispatchDuration = "+dispatchDuration+", LaterBookingDispatchBeforeHours = "+LaterBookingDispatchBeforeHours+"]";
    }
}
