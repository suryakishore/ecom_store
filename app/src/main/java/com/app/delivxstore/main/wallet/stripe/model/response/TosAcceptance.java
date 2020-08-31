package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TosAcceptance implements Parcelable {

	@SerializedName("date")
	@Expose
	private int date;

	@SerializedName("ip")
	@Expose
	private String ip;

	@SerializedName("user_agent")
	@Expose
	private Object userAgent;

	protected TosAcceptance(Parcel in) {
		date = in.readInt();
		ip = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(date);
		dest.writeString(ip);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<TosAcceptance> CREATOR = new Creator<TosAcceptance>() {
		@Override
		public TosAcceptance createFromParcel(Parcel in) {
			return new TosAcceptance(in);
		}

		@Override
		public TosAcceptance[] newArray(int size) {
			return new TosAcceptance[size];
		}
	};

	public int getDate(){
		return date;
	}

	public String getIp(){
		return ip;
	}

	public Object getUserAgent(){
		return userAgent;
	}

	@Override
 	public String toString(){
		return 
			"TosAcceptance{" + 
			"date = '" + date + '\'' + 
			",ip = '" + ip + '\'' + 
			",user_agent = '" + userAgent + '\'' + 
			"}";
		}
}