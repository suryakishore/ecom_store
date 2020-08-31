package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.app.delivxstore.main.history.model.DeliveryDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class ProductLevelAccounting implements Parcelable {
    @SerializedName("offerDisscount")
    @Expose
    private String offerDisscount;
    @SerializedName("totalAppliedTaxOnProduct")
    @Expose
    private String totalAppliedTaxOnProduct;
    @SerializedName("originalPrice")
    @Expose
    private String originalPrice;
    @SerializedName("discountedPrice")
    @Expose
    private String discountedPrice;
    @SerializedName("grossTotal")
    @Expose
    private String grossTotal;
    @SerializedName("deliveryDetails")
    @Expose
    private DeliveryDetails deliveryDetails;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("finalPrice")
    @Expose
    private String finalPrice;
    @SerializedName("offerId")
    @Expose
    private String offerId;
    @SerializedName("tax")
    @Expose
    private ArrayList<Tax> tax;
    @SerializedName("productTaxArray")
    @Expose
    private ArrayList<ProductTaxArray> productTaxArray;
    @SerializedName("offerTitle")
    @Expose
    private String offerTitle;

    protected ProductLevelAccounting(Parcel in) {
        offerDisscount = in.readString();
        totalAppliedTaxOnProduct = in.readString();
        originalPrice = in.readString();
        discountedPrice = in.readString();
        grossTotal = in.readString();
        discount = in.readString();
        finalPrice = in.readString();
        offerId = in.readString();
        offerTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offerDisscount);
        dest.writeString(totalAppliedTaxOnProduct);
        dest.writeString(originalPrice);
        dest.writeString(discountedPrice);
        dest.writeString(grossTotal);
        dest.writeString(discount);
        dest.writeString(finalPrice);
        dest.writeString(offerId);
        dest.writeString(offerTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductLevelAccounting> CREATOR =
        new Creator<ProductLevelAccounting>() {
            @Override
            public ProductLevelAccounting createFromParcel(Parcel in) {
                return new ProductLevelAccounting(in);
            }

            @Override
            public ProductLevelAccounting[] newArray(int size) {
                return new ProductLevelAccounting[size];
            }
        };

    public String getOfferDisscount ()
    {
        return offerDisscount;
    }

    public void setOfferDisscount (String offerDisscount)
    {
        this.offerDisscount = offerDisscount;
    }

    public String getTotalAppliedTaxOnProduct ()
    {
        return totalAppliedTaxOnProduct;
    }

    public void setTotalAppliedTaxOnProduct (String totalAppliedTaxOnProduct)
    {
        this.totalAppliedTaxOnProduct = totalAppliedTaxOnProduct;
    }

    public String getOriginalPrice ()
    {
        return originalPrice;
    }

    public void setOriginalPrice (String originalPrice)
    {
        this.originalPrice = originalPrice;
    }

    public String getDiscountedPrice ()
    {
        return discountedPrice;
    }

    public void setDiscountedPrice (String discountedPrice)
    {
        this.discountedPrice = discountedPrice;
    }

    public String getGrossTotal ()
    {
        return grossTotal;
    }

    public void setGrossTotal (String grossTotal)
    {
        this.grossTotal = grossTotal;
    }

    public DeliveryDetails getDeliveryDetails ()
    {
        return deliveryDetails;
    }

    public void setDeliveryDetails (DeliveryDetails deliveryDetails)
    {
        this.deliveryDetails = deliveryDetails;
    }

    public String getDiscount ()
    {
        return discount;
    }

    public void setDiscount (String discount)
    {
        this.discount = discount;
    }

    public String getFinalPrice ()
    {
        return finalPrice;
    }

    public void setFinalPrice (String finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    public String getOfferId ()
    {
        return offerId;
    }

    public void setOfferId (String offerId)
    {
        this.offerId = offerId;
    }

    public ArrayList<Tax> getTax ()
    {
        return tax;
    }

    public void setTax (ArrayList<Tax> tax)
    {
        this.tax = tax;
    }

    public ArrayList<ProductTaxArray> getProductTaxArray ()
    {
        return productTaxArray;
    }

    public void setProductTaxArray (ArrayList<ProductTaxArray> productTaxArray)
    {
        this.productTaxArray = productTaxArray;
    }

    public String getOfferTitle ()
    {
        return offerTitle;
    }

    public void setOfferTitle (String offerTitle)
    {
        this.offerTitle = offerTitle;
    }


}
