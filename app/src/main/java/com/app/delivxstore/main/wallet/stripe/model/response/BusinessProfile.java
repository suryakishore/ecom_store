package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessProfile implements Parcelable {

	@SerializedName("support_email")
	@Expose
	private Object supportEmail;

	@SerializedName("support_url")
	@Expose
	private Object supportUrl;

	@SerializedName("support_address")
	@Expose
	private Object supportAddress;

	@SerializedName("support_phone")
	@Expose
	private Object supportPhone;

	@SerializedName("name")
	@Expose
	private Object name;

	@SerializedName("mcc")
	@Expose
	private Object mcc;

	@SerializedName("product_description")
	@Expose
	private Object productDescription;

	@SerializedName("url")
	@Expose
	private String url;

	protected BusinessProfile(Parcel in) {
		url = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(url);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<BusinessProfile> CREATOR = new Creator<BusinessProfile>() {
		@Override
		public BusinessProfile createFromParcel(Parcel in) {
			return new BusinessProfile(in);
		}

		@Override
		public BusinessProfile[] newArray(int size) {
			return new BusinessProfile[size];
		}
	};

	public Object getSupportEmail(){
		return supportEmail;
	}

	public Object getSupportUrl(){
		return supportUrl;
	}

	public Object getSupportAddress(){
		return supportAddress;
	}

	public Object getSupportPhone(){
		return supportPhone;
	}

	public Object getName(){
		return name;
	}

	public Object getMcc(){
		return mcc;
	}

	public Object getProductDescription(){
		return productDescription;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"BusinessProfile{" + 
			"support_email = '" + supportEmail + '\'' + 
			",support_url = '" + supportUrl + '\'' + 
			",support_address = '" + supportAddress + '\'' + 
			",support_phone = '" + supportPhone + '\'' + 
			",name = '" + name + '\'' + 
			",mcc = '" + mcc + '\'' + 
			",product_description = '" + productDescription + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}