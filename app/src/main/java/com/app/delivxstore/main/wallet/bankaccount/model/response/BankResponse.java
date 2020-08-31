package com.app.delivxstore.main.wallet.bankaccount.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankResponse implements Parcelable {

	@SerializedName("data")
	@Expose
	private BankData data;

	@SerializedName("message")
	@Expose
	private String message;

	protected BankResponse(Parcel in) {
		data = in.readParcelable(BankData.class.getClassLoader());
		message = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(data, flags);
		dest.writeString(message);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<BankResponse> CREATOR = new Creator<BankResponse>() {
		@Override
		public BankResponse createFromParcel(Parcel in) {
			return new BankResponse(in);
		}

		@Override
		public BankResponse[] newArray(int size) {
			return new BankResponse[size];
		}
	};

	public BankData getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"BankResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}