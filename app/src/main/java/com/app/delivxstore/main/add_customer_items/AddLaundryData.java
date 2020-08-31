package com.app.delivxstore.main.add_customer_items;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${3embed} on ${27-10-2017}.
 * Banglore
 */
public class AddLaundryData implements Serializable {

    private String totalPrice;

    private String cartId;

    private String incGrossTotalPrice;

    private ArrayList<AddLaundryCart> cart;

    private String[] inclusiveTaxes;

    private String exclTax;

    private String[] exclusiveTaxes;

    private String cartTotal;

    private String inclTax;

    private String cartDiscount;

    private String laundryCartExists;

    private String incNetPrice;

    private String finalTotalIncludingTaxes;

    public String getTotalPrice ()
    {
        return totalPrice;
    }

    public void setTotalPrice (String totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getCartId ()
    {
        return cartId;
    }

    public void setCartId (String cartId)
    {
        this.cartId = cartId;
    }

    public String getIncGrossTotalPrice ()
    {
        return incGrossTotalPrice;
    }

    public void setIncGrossTotalPrice (String incGrossTotalPrice)
    {
        this.incGrossTotalPrice = incGrossTotalPrice;
    }

    public ArrayList<AddLaundryCart> getCart ()
    {
        return cart;
    }

    public void setCart (ArrayList<AddLaundryCart> cart)
    {
        this.cart = cart;
    }

    public String[] getInclusiveTaxes ()
    {
        return inclusiveTaxes;
    }

    public void setInclusiveTaxes (String[] inclusiveTaxes)
    {
        this.inclusiveTaxes = inclusiveTaxes;
    }

    public String getExclTax ()
    {
        return exclTax;
    }

    public void setExclTax (String exclTax)
    {
        this.exclTax = exclTax;
    }

    public String[] getExclusiveTaxes ()
    {
        return exclusiveTaxes;
    }

    public void setExclusiveTaxes (String[] exclusiveTaxes)
    {
        this.exclusiveTaxes = exclusiveTaxes;
    }

    public String getCartTotal ()
    {
        return cartTotal;
    }

    public void setCartTotal (String cartTotal)
    {
        this.cartTotal = cartTotal;
    }

    public String getInclTax ()
    {
        return inclTax;
    }

    public void setInclTax (String inclTax)
    {
        this.inclTax = inclTax;
    }

    public String getCartDiscount ()
    {
        return cartDiscount;
    }

    public void setCartDiscount (String cartDiscount)
    {
        this.cartDiscount = cartDiscount;
    }

    public String getLaundryCartExists ()
    {
        return laundryCartExists;
    }

    public void setLaundryCartExists (String laundryCartExists)
    {
        this.laundryCartExists = laundryCartExists;
    }

    public String getIncNetPrice ()
    {
        return incNetPrice;
    }

    public void setIncNetPrice (String incNetPrice)
    {
        this.incNetPrice = incNetPrice;
    }

    public String getFinalTotalIncludingTaxes ()
    {
        return finalTotalIncludingTaxes;
    }

    public void setFinalTotalIncludingTaxes (String finalTotalIncludingTaxes)
    {
        this.finalTotalIncludingTaxes = finalTotalIncludingTaxes;
    }



}

