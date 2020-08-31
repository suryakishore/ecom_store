package com.app.delivxstore.main.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TaxItem implements Serializable {

	@SerializedName("totalValue")
	@Expose
	private double totalValue;

	@SerializedName("taxValue")
	@Expose
	private String taxValue;

	@SerializedName("taxId")
	@Expose
	private String taxId;

	@SerializedName("taxName")
	@Expose
	private String taxName;

	public double getTotalValue(){
		return totalValue;
	}

	public String getTaxValue(){
		return taxValue;
	}

	public String getTaxId(){
		return taxId;
	}

	public String getTaxName(){
		return taxName;
	}
}