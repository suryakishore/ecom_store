package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branding implements Parcelable {

	@SerializedName("icon")
	@Expose
	private Object icon;

	@SerializedName("logo")
	@Expose
	private Object logo;

	@SerializedName("secondary_color")
	@Expose
	private Object secondaryColor;

	@SerializedName("primary_color")
	@Expose
	private Object primaryColor;

	protected Branding(Parcel in) {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Branding> CREATOR = new Creator<Branding>() {
		@Override
		public Branding createFromParcel(Parcel in) {
			return new Branding(in);
		}

		@Override
		public Branding[] newArray(int size) {
			return new Branding[size];
		}
	};

	public Object getIcon(){
		return icon;
	}

	public Object getLogo(){
		return logo;
	}

	public Object getSecondaryColor(){
		return secondaryColor;
	}

	public Object getPrimaryColor(){
		return primaryColor;
	}

	@Override
 	public String toString(){
		return 
			"Branding{" + 
			"icon = '" + icon + '\'' + 
			",logo = '" + logo + '\'' + 
			",secondary_color = '" + secondaryColor + '\'' + 
			",primary_color = '" + primaryColor + '\'' + 
			"}";
		}
}