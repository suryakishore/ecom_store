package com.app.delivxstore.main.home.tabView.orders.manualDispatch.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DriversData implements Serializable {

    private ArrayList<AvailableDriver> availableDriver;

    public ArrayList<AvailableDriver> getAvailableDriver() {
        return availableDriver;
    }

    public void setAvailableDriver(ArrayList<AvailableDriver> availableDriver) {
        this.availableDriver = availableDriver;
    }
}
