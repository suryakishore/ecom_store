package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Packaging implements Parcelable {

	@SerializedName("unitType")
	@Expose
	private String unitType;

	@SerializedName("packingType")
	@Expose
	private String packingType;

	@SerializedName("unitValue")
	@Expose
	private int unitValue;

	protected Packaging(Parcel in) {
		unitType = in.readString();
		packingType = in.readString();
		unitValue = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(unitType);
		dest.writeString(packingType);
		dest.writeInt(unitValue);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Packaging> CREATOR = new Creator<Packaging>() {
		@Override
		public Packaging createFromParcel(Parcel in) {
			return new Packaging(in);
		}

		@Override
		public Packaging[] newArray(int size) {
			return new Packaging[size];
		}
	};

	public String getUnitType(){
		return unitType;
	}

	public String getPackingType(){
		return packingType;
	}

	public int getUnitValue(){
		return unitValue;
	}
}