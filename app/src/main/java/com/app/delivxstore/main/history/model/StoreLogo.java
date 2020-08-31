package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StoreLogo implements Parcelable {

	@SerializedName("logoImageThumb")
	@Expose
	private String logoImageThumb;

	@SerializedName("logoImageMobile")
	@Expose
	private String logoImageMobile;

	@SerializedName("logoImageweb")
	@Expose
	private String logoImageweb;

	protected StoreLogo(Parcel in) {
		logoImageThumb = in.readString();
		logoImageMobile = in.readString();
		logoImageweb = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(logoImageThumb);
		dest.writeString(logoImageMobile);
		dest.writeString(logoImageweb);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StoreLogo> CREATOR = new Creator<StoreLogo>() {
		@Override
		public StoreLogo createFromParcel(Parcel in) {
			return new StoreLogo(in);
		}

		@Override
		public StoreLogo[] newArray(int size) {
			return new StoreLogo[size];
		}
	};

	public String getLogoImageThumb(){
		return logoImageThumb;
	}

	public String getLogoImageMobile(){
		return logoImageMobile;
	}

	public String getLogoImageweb(){
		return logoImageweb;
	}
}