package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StorePlan implements Parcelable {

	@SerializedName("appCommissionTypeText")
	@Expose
	private String appCommissionTypeText;

	@SerializedName("appCommission")
	@Expose
	private int appCommission;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("appCommissionType")
	@Expose
	private int appCommissionType;

	protected StorePlan(Parcel in) {
		appCommissionTypeText = in.readString();
		appCommission = in.readInt();
		name = in.readString();
		appCommissionType = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(appCommissionTypeText);
		dest.writeInt(appCommission);
		dest.writeString(name);
		dest.writeInt(appCommissionType);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StorePlan> CREATOR = new Creator<StorePlan>() {
		@Override
		public StorePlan createFromParcel(Parcel in) {
			return new StorePlan(in);
		}

		@Override
		public StorePlan[] newArray(int size) {
			return new StorePlan[size];
		}
	};

	public String getAppCommissionTypeText(){
		return appCommissionTypeText;
	}

	public int getAppCommission(){
		return appCommission;
	}

	public String getName(){
		return name;
	}

	public int getAppCommissionType(){
		return appCommissionType;
	}
}