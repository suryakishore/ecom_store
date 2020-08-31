package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ProductTaxArray implements Parcelable {
    @SerializedName("totalValue")
    @Expose
    private String totalValue;
    @SerializedName("taxValue")
    @Expose
    private String taxValue;
    @SerializedName("taxId")
    @Expose
    private String taxId;
    @SerializedName("taxName")
    @Expose
    private String taxName;

    protected ProductTaxArray(Parcel in) {
        totalValue = in.readString();
        taxValue = in.readString();
        taxId = in.readString();
        taxName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(totalValue);
        dest.writeString(taxValue);
        dest.writeString(taxId);
        dest.writeString(taxName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductTaxArray> CREATOR = new Creator<ProductTaxArray>() {
        @Override
        public ProductTaxArray createFromParcel(Parcel in) {
            return new ProductTaxArray(in);
        }

        @Override
        public ProductTaxArray[] newArray(int size) {
            return new ProductTaxArray[size];
        }
    };

    public String getTotalValue ()
    {
        return totalValue;
    }

    public void setTotalValue (String totalValue)
    {
        this.totalValue = totalValue;
    }

    public String getTaxValue ()
    {
        return taxValue;
    }

    public void setTaxValue (String taxValue)
    {
        this.taxValue = taxValue;
    }

    public String getTaxId ()
    {
        return taxId;
    }

    public void setTaxId (String taxId)
    {
        this.taxId = taxId;
    }

    public String getTaxName ()
    {
        return taxName;
    }

    public void setTaxName (String taxName)
    {
        this.taxName = taxName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalValue = "+totalValue+", taxValue = "+taxValue+", taxId = "+taxId+", taxName = "+taxName+"]";
    }
}
