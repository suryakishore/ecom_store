package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardPayments implements Parcelable {

	@SerializedName("statement_descriptor_prefix")
	@Expose
	private Object statementDescriptorPrefix;

	@SerializedName("decline_on")
	@Expose
	private DeclineOn declineOn;

	protected CardPayments(Parcel in) {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<CardPayments> CREATOR = new Creator<CardPayments>() {
		@Override
		public CardPayments createFromParcel(Parcel in) {
			return new CardPayments(in);
		}

		@Override
		public CardPayments[] newArray(int size) {
			return new CardPayments[size];
		}
	};

	public Object getStatementDescriptorPrefix(){
		return statementDescriptorPrefix;
	}

	public DeclineOn getDeclineOn(){
		return declineOn;
	}

	@Override
 	public String toString(){
		return 
			"CardPayments{" + 
			"statement_descriptor_prefix = '" + statementDescriptorPrefix + '\'' + 
			",decline_on = '" + declineOn + '\'' + 
			"}";
		}
}