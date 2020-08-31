package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capabilities implements Parcelable {

	@SerializedName("transfers")
	@Expose
	private String transfers;

	@SerializedName("card_payments")
	@Expose
	private String cardPayments;

	protected Capabilities(Parcel in) {
		transfers = in.readString();
		cardPayments = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(transfers);
		dest.writeString(cardPayments);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Capabilities> CREATOR = new Creator<Capabilities>() {
		@Override
		public Capabilities createFromParcel(Parcel in) {
			return new Capabilities(in);
		}

		@Override
		public Capabilities[] newArray(int size) {
			return new Capabilities[size];
		}
	};

	public String getTransfers(){
		return transfers;
	}

	public String getCardPayments(){
		return cardPayments;
	}

	@Override
 	public String toString(){
		return 
			"Capabilities{" + 
			"transfers = '" + transfers + '\'' + 
			",card_payments = '" + cardPayments + '\'' + 
			"}";
		}
}