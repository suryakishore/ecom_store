package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class
Sellers implements Parcelable {
    /*private String targetAmtForFreeDelivery;

    private String fullFillMentCenterId;

    private String minOrder;

    private String driverType;

    private String fullfilledBy;

    private ArrayList<String> storeTax;

    private Phone phone;

    private PickupAddress pickupAddress;

    private String autoDispatch;

    private String autoAcceptOrders;

    private String isInventoryCheck;

    private String name;

    private Logo logo;

    public String getTargetAmtForFreeDelivery ()
    {
        return targetAmtForFreeDelivery;
    }

    public void setTargetAmtForFreeDelivery (String targetAmtForFreeDelivery)
    {
        this.targetAmtForFreeDelivery = targetAmtForFreeDelivery;
    }

    public String getFullFillMentCenterId ()
    {
        return fullFillMentCenterId;
    }

    public void setFullFillMentCenterId (String fullFillMentCenterId)
    {
        this.fullFillMentCenterId = fullFillMentCenterId;
    }

    public String getMinOrder ()
    {
        return minOrder;
    }

    public void setMinOrder (String minOrder)
    {
        this.minOrder = minOrder;
    }

    public String getDriverType ()
    {
        return driverType;
    }

    public void setDriverType (String driverType)
    {
        this.driverType = driverType;
    }

    public String getFullfilledBy ()
    {
        return fullfilledBy;
    }

    public void setFullfilledBy (String fullfilledBy)
    {
        this.fullfilledBy = fullfilledBy;
    }

    public ArrayList<String> getStoreTax ()
    {
        return storeTax;
    }

    public void setStoreTax (ArrayList<String> storeTax)
    {
        this.storeTax = storeTax;
    }

    public Phone getPhone ()
    {
        return phone;
    }

    public void setPhone (Phone phone)
    {
        this.phone = phone;
    }

    public PickupAddress getPickupAddress ()
    {
        return pickupAddress;
    }

    public void setPickupAddress (PickupAddress pickupAddress)
    {
        this.pickupAddress = pickupAddress;
    }

    public String getAutoDispatch ()
    {
        return autoDispatch;
    }

    public void setAutoDispatch (String autoDispatch)
    {
        this.autoDispatch = autoDispatch;
    }

    public String getAutoAcceptOrders ()
    {
        return autoAcceptOrders;
    }

    public void setAutoAcceptOrders (String autoAcceptOrders)
    {
        this.autoAcceptOrders = autoAcceptOrders;
    }

    public String getIsInventoryCheck ()
    {
        return isInventoryCheck;
    }

    public void setIsInventoryCheck (String isInventoryCheck)
    {
        this.isInventoryCheck = isInventoryCheck;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Logo getLogo ()
    {
        return logo;
    }

    public void setLogo (Logo logo)
    {
        this.logo = logo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [targetAmtForFreeDelivery = "+targetAmtForFreeDelivery+", fullFillMentCenterId = "+fullFillMentCenterId+", minOrder = "+minOrder+", driverType = "+driverType+", fullfilledBy = "+fullfilledBy+", storeTax = "+storeTax+", phone = "+phone+", pickupAddress = "+pickupAddress+", autoDispatch = "+autoDispatch+", autoAcceptOrders = "+autoAcceptOrders+", isInventoryCheck = "+isInventoryCheck+", name = "+name+", logo = "+logo+"]";
    }*/

  protected Sellers(Parcel in) {
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Sellers> CREATOR = new Creator<Sellers>() {
    @Override
    public Sellers createFromParcel(Parcel in) {
      return new Sellers(in);
    }

    @Override
    public Sellers[] newArray(int size) {
      return new Sellers[size];
    }
  };
}
