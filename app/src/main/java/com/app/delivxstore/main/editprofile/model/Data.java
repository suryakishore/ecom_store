package com.app.delivxstore.main.editprofile.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {

	@SerializedName("otpId")
	@Expose
	private String otpId;

	@SerializedName("otpExpiryTime")
	@Expose
	private int otpExpiryTime;

	protected Data(Parcel in) {
		otpId = in.readString();
		otpExpiryTime = in.readInt();
	}

	public static final Creator<Data> CREATOR = new Creator<Data>() {
		@Override
		public Data createFromParcel(Parcel in) {
			return new Data(in);
		}

		@Override
		public Data[] newArray(int size) {
			return new Data[size];
		}
	};

	public String getOtpId(){
		return otpId;
	}

	public int getOtpExpiryTime(){
		return otpExpiryTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(otpId);
		parcel.writeInt(otpExpiryTime);
	}
}