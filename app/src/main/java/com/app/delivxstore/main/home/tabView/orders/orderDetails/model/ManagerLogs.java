package com.app.delivxstore.main.home.tabView.orders.orderDetails.model;

import java.io.Serializable;

public class ManagerLogs implements Serializable {
    private String managerName;

    private String managerId;

    private String actionType;

    private String actionTime;

    public String getManagerName ()
    {
        return managerName;
    }

    public void setManagerName (String managerName)
    {
        this.managerName = managerName;
    }

    public String getManagerId ()
    {
        return managerId;
    }

    public void setManagerId (String managerId)
    {
        this.managerId = managerId;
    }

    public String getActionType ()
    {
        return actionType;
    }

    public void setActionType (String actionType)
    {
        this.actionType = actionType;
    }

    public String getActionTime ()
    {
        return actionTime;
    }

    public void setActionTime (String actionTime)
    {
        this.actionTime = actionTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [managerName = "+managerName+", managerId = "+managerId+", actionType = "+actionType+", actionTime = "+actionTime+"]";
    }
}
