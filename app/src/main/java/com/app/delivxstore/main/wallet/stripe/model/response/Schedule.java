package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule implements Parcelable {

	@SerializedName("interval")
	@Expose
	private String interval;

	@SerializedName("delay_days")
	@Expose
	private int delayDays;

	protected Schedule(Parcel in) {
		interval = in.readString();
		delayDays = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(interval);
		dest.writeInt(delayDays);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
		@Override
		public Schedule createFromParcel(Parcel in) {
			return new Schedule(in);
		}

		@Override
		public Schedule[] newArray(int size) {
			return new Schedule[size];
		}
	};

	public String getInterval(){
		return interval;
	}

	public int getDelayDays(){
		return delayDays;
	}

	@Override
 	public String toString(){
		return 
			"Schedule{" + 
			"interval = '" + interval + '\'' + 
			",delay_days = '" + delayDays + '\'' + 
			"}";
		}
}