package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StripeResponse implements Parcelable {

	@SerializedName("data")
	@Expose
	private StripeData data;

	@SerializedName("message")
	@Expose
	private String message;

	StripeResponse(){

	}
	protected StripeResponse(Parcel in) {
		message = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(message);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StripeResponse> CREATOR = new Creator<StripeResponse>() {
		@Override
		public StripeResponse createFromParcel(Parcel in) {
			return new StripeResponse(in);
		}

		@Override
		public StripeResponse[] newArray(int size) {
			return new StripeResponse[size];
		}
	};

	public StripeData getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"StripResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}