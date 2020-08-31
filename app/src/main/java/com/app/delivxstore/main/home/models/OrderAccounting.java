package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OrderAccounting implements Parcelable {
    @SerializedName("deliveryFee")
    @Expose
    private String deliveryFee;
    @SerializedName("grandTotal")
    @Expose
    private String grandTotal;
    @SerializedName("subtotal")
    @Expose
    private String subtotal;
    @SerializedName("payBy")
    @Expose
    private PayBy payBy;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("totalDiscount")
    @Expose
    private String totalDiscount;
    @SerializedName("totalAppliedTaxOnCart")
    @Expose
    private String totalAppliedTaxOnCart;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    protected OrderAccounting(Parcel in) {
        deliveryFee = in.readString();
        grandTotal = in.readString();
        subtotal = in.readString();
        currencySymbol = in.readString();
        totalDiscount = in.readString();
        totalAppliedTaxOnCart = in.readString();
        currencyCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deliveryFee);
        dest.writeString(grandTotal);
        dest.writeString(subtotal);
        dest.writeString(currencySymbol);
        dest.writeString(totalDiscount);
        dest.writeString(totalAppliedTaxOnCart);
        dest.writeString(currencyCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderAccounting> CREATOR = new Creator<OrderAccounting>() {
        @Override
        public OrderAccounting createFromParcel(Parcel in) {
            return new OrderAccounting(in);
        }

        @Override
        public OrderAccounting[] newArray(int size) {
            return new OrderAccounting[size];
        }
    };

    public String getDeliveryFee ()
    {
        return deliveryFee;
    }

    public void setDeliveryFee (String deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }

    public String getGrandTotal ()
    {
        return grandTotal;
    }

    public void setGrandTotal (String grandTotal)
    {
        this.grandTotal = grandTotal;
    }

    public String getSubtotal ()
    {
        return subtotal;
    }

    public void setSubtotal (String subtotal)
    {
        this.subtotal = subtotal;
    }

    public PayBy getPayBy ()
    {
        return payBy;
    }

    public void setPayBy (PayBy payBy)
    {
        this.payBy = payBy;
    }

    public String getCurrencySymbol ()
    {
        return currencySymbol;
    }

    public void setCurrencySymbol (String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
    }

    public String getTotalDiscount ()
    {
        return totalDiscount;
    }

    public void setTotalDiscount (String totalDiscount)
    {
        this.totalDiscount = totalDiscount;
    }

    public String getTotalAppliedTaxOnCart ()
    {
        return totalAppliedTaxOnCart;
    }

    public void setTotalAppliedTaxOnCart (String totalAppliedTaxOnCart)
    {
        this.totalAppliedTaxOnCart = totalAppliedTaxOnCart;
    }

    public String getCurrencyCode ()
    {
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deliveryFee = "+deliveryFee+", grandTotal = "+grandTotal+", subtotal = "+subtotal+", payBy = "+payBy+", currencySymbol = "+currencySymbol+", totalDiscount = "+totalDiscount+", totalAppliedTaxOnCart = "+totalAppliedTaxOnCart+", currencyCode = "+currencyCode+"]";
    }
}
