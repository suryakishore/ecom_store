package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorsItem implements Parcelable {

	@SerializedName("reason")
	@Expose
	private String reason;

	@SerializedName("code")
	@Expose
	private String code;

	@SerializedName("requirement")
	@Expose
	private String requirement;

	protected ErrorsItem(Parcel in) {
		reason = in.readString();
		code = in.readString();
		requirement = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(reason);
		dest.writeString(code);
		dest.writeString(requirement);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ErrorsItem> CREATOR = new Creator<ErrorsItem>() {
		@Override
		public ErrorsItem createFromParcel(Parcel in) {
			return new ErrorsItem(in);
		}

		@Override
		public ErrorsItem[] newArray(int size) {
			return new ErrorsItem[size];
		}
	};

	public String getReason(){
		return reason;
	}

	public String getCode(){
		return code;
	}

	public String getRequirement(){
		return requirement;
	}

	@Override
 	public String toString(){
		return 
			"ErrorsItem{" + 
			"reason = '" + reason + '\'' + 
			",code = '" + code + '\'' + 
			",requirement = '" + requirement + '\'' + 
			"}";
		}
}