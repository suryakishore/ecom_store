package com.app.delivxstore.main.mobileView.orderDetails.models;

import com.app.delivxstore.main.history.model.DeliveryDetails;
import java.io.Serializable;
import java.util.ArrayList;

public class StoreLevelAccounting implements Serializable {
    private String offerDisscount;

    private String totalAppliedTaxOnProduct;

    private String originalPrice;

    private String discountedPrice;

    private DeliveryDetails deliveryDetails;

    private String currencySymbol;

    private String discount;

    private String finalPrice;

    private ArrayList<Tax> tax;

    private String totalGrossTotal;

    private String currencyCode;

    private ProductTaxArray[] productTaxArray;

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

    public DeliveryDetails getDeliveryDetails ()
    {
        return deliveryDetails;
    }

    public void setDeliveryDetails (DeliveryDetails deliveryDetails)
    {
        this.deliveryDetails = deliveryDetails;
    }

    public String getCurrencySymbol ()
    {
        return currencySymbol;
    }

    public void setCurrencySymbol (String currencySymbol)
    {
        this.currencySymbol = currencySymbol;
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

    public ArrayList<Tax> getTax ()
    {
        return tax;
    }

    public void setTax (ArrayList<Tax> tax)
    {
        this.tax = tax;
    }

    public String getTotalGrossTotal ()
    {
        return totalGrossTotal;
    }

    public void setTotalGrossTotal (String totalGrossTotal)
    {
        this.totalGrossTotal = totalGrossTotal;
    }

    public String getCurrencyCode ()
    {
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public ProductTaxArray[] getProductTaxArray ()
    {
        return productTaxArray;
    }

    public void setProductTaxArray (ProductTaxArray[] productTaxArray)
    {
        this.productTaxArray = productTaxArray;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [offerDisscount = "+offerDisscount+", totalAppliedTaxOnProduct = "+totalAppliedTaxOnProduct+", originalPrice = "+originalPrice+", discountedPrice = "+discountedPrice+", deliveryDetails = "+deliveryDetails+", currencySymbol = "+currencySymbol+", discount = "+discount+", finalPrice = "+finalPrice+", tax = "+tax+", totalGrossTotal = "+totalGrossTotal+", currencyCode = "+currencyCode+", productTaxArray = "+productTaxArray+"]";
    }
}
