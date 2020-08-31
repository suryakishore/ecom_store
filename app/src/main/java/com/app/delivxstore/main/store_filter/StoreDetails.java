package com.app.delivxstore.main.store_filter;

public class StoreDetails {

    private String id;

    private String driverType;

    private String storeName;
    private boolean isStoreType;

    public boolean isStoreType() {
        return isStoreType;
    }

    public void setStoreType(boolean storeType) {
        isStoreType = storeType;
    }

    private String[] serviceZones;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDriverType ()
    {
        return driverType;
    }

    public void setDriverType (String driverType)
    {
        this.driverType = driverType;
    }

    public String getStoreName ()
    {
        return storeName;
    }

    public void setStoreName (String storeName)
    {
        this.storeName = storeName;
    }

    public String[] getServiceZones ()
    {
        return serviceZones;
    }

    public void setServiceZones (String[] serviceZones)
    {
        this.serviceZones = serviceZones;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", driverType = "+driverType+", storeName = "+storeName+", serviceZones = "+serviceZones+"]";
    }
}
