package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard implements Parcelable {

	@SerializedName("timezone")
	@Expose
	private String timezone;

	@SerializedName("display_name")
	@Expose
	private String displayName;

	protected Dashboard(Parcel in) {
		timezone = in.readString();
		displayName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(timezone);
		dest.writeString(displayName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Dashboard> CREATOR = new Creator<Dashboard>() {
		@Override
		public Dashboard createFromParcel(Parcel in) {
			return new Dashboard(in);
		}

		@Override
		public Dashboard[] newArray(int size) {
			return new Dashboard[size];
		}
	};

	public String getTimezone(){
		return timezone;
	}

	public String getDisplayName(){
		return displayName;
	}

	@Override
 	public String toString(){
		return 
			"Dashboard{" + 
			"timezone = '" + timezone + '\'' + 
			",display_name = '" + displayName + '\'' + 
			"}";
		}
}