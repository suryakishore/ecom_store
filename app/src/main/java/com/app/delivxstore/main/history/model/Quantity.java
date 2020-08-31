package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Quantity implements Parcelable {

	@SerializedName("unit")
	@Expose
	private String unit;

	@SerializedName("value")
	@Expose
	private int value;

	protected Quantity(Parcel in) {
		unit = in.readString();
		value = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(unit);
		dest.writeInt(value);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Quantity> CREATOR = new Creator<Quantity>() {
		@Override
		public Quantity createFromParcel(Parcel in) {
			return new Quantity(in);
		}

		@Override
		public Quantity[] newArray(int size) {
			return new Quantity[size];
		}
	};

	public String getUnit(){
		return unit;
	}

	public int getValue(){
		return value;
	}
}