package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dob implements Parcelable {

	@SerializedName("month")
	@Expose
	private int month;

	@SerializedName("year")
	@Expose
	private int year;

	@SerializedName("day")
	@Expose
	private int day;

	protected Dob(Parcel in) {
		month = in.readInt();
		year = in.readInt();
		day = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(month);
		dest.writeInt(year);
		dest.writeInt(day);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Dob> CREATOR = new Creator<Dob>() {
		@Override
		public Dob createFromParcel(Parcel in) {
			return new Dob(in);
		}

		@Override
		public Dob[] newArray(int size) {
			return new Dob[size];
		}
	};

	public int getMonth(){
		return month;
	}

	public int getYear(){
		return year;
	}

	public int getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Dob{" + 
			"month = '" + month + '\'' + 
			",year = '" + year + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}