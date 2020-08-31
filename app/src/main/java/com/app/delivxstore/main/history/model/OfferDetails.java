package com.app.delivxstore.main.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OfferDetails implements Serializable {

	@SerializedName("offerType")
	@Expose
	private int offerType;

	@SerializedName("offerId")
	@Expose
	private String offerId;

	@SerializedName("offerValue")
	@Expose
	private int offerValue;

	@SerializedName("offerTitle")
	@Expose
	private String offerTitle;

	public int getOfferType(){
		return offerType;
	}

	public String getOfferId(){
		return offerId;
	}

	public int getOfferValue(){
		return offerValue;
	}

	public String getOfferTitle(){
		return offerTitle;
	}
}