package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Images implements Parcelable {

	@SerializedName("small")
	@Expose
	private String small;

	@SerializedName("altText")
	@Expose
	private String altText;

	@SerializedName("large")
	@Expose
	private String large;

	@SerializedName("extraLarge")
	@Expose
	private String extraLarge;

	@SerializedName("medium")
	@Expose
	private String medium;

	protected Images(Parcel in) {
		small = in.readString();
		altText = in.readString();
		large = in.readString();
		extraLarge = in.readString();
		medium = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(small);
		dest.writeString(altText);
		dest.writeString(large);
		dest.writeString(extraLarge);
		dest.writeString(medium);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Images> CREATOR = new Creator<Images>() {
		@Override
		public Images createFromParcel(Parcel in) {
			return new Images(in);
		}

		@Override
		public Images[] newArray(int size) {
			return new Images[size];
		}
	};

	public String getSmall(){
		return small;
	}

	public String getAltText(){
		return altText;
	}

	public String getLarge(){
		return large;
	}

	public String getExtraLarge(){
		return extraLarge;
	}

	public String getMedium(){
		return medium;
	}
}