package com.app.delivxstore.main.wallet.stripe.model.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatchStripeBody implements Parcelable {

	@SerializedName("country")
	@Expose
	private String country="US";

	@SerializedName("business_type")
	@Expose
	private String businessType="individual";

	@SerializedName("ip")
	@Expose
	private String ip;

	@SerializedName("last_name")
	@Expose
	private String lastName;

	@SerializedName("first_name")
	@Expose
	private String firstName;

	public PatchStripeBody(){

	}

	protected PatchStripeBody(Parcel in) {
		country = in.readString();
		businessType = in.readString();
		ip = in.readString();
		lastName = in.readString();
		firstName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(country);
		dest.writeString(businessType);
		dest.writeString(ip);
		dest.writeString(lastName);
		dest.writeString(firstName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<PatchStripeBody> CREATOR = new Creator<PatchStripeBody>() {
		@Override
		public PatchStripeBody createFromParcel(Parcel in) {
			return new PatchStripeBody(in);
		}

		@Override
		public PatchStripeBody[] newArray(int size) {
			return new PatchStripeBody[size];
		}
	};

	public void setCountry(String country){
		this.country = country;
	}

	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	@Override
 	public String toString(){
		return 
			"PatchStripBody{" + 
			"country = '" + country + '\'' + 
			",business_type = '" + businessType + '\'' + 
			",ip = '" + ip + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}