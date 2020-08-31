package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings implements Parcelable {

	@SerializedName("payouts")
	@Expose
	private Payouts payouts;

	@SerializedName("branding")
	@Expose
	private Branding branding;

	@SerializedName("payments")
	@Expose
	private Payments payments;

	@SerializedName("card_payments")
	@Expose
	private CardPayments cardPayments;

	@SerializedName("dashboard")
	@Expose
	private Dashboard dashboard;

	protected Settings(Parcel in) {
		payouts = in.readParcelable(Payouts.class.getClassLoader());
		branding = in.readParcelable(Branding.class.getClassLoader());
		payments = in.readParcelable(Payments.class.getClassLoader());
		cardPayments = in.readParcelable(CardPayments.class.getClassLoader());
		dashboard = in.readParcelable(Dashboard.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(payouts, flags);
		dest.writeParcelable(branding, flags);
		dest.writeParcelable(payments, flags);
		dest.writeParcelable(cardPayments, flags);
		dest.writeParcelable(dashboard, flags);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Settings> CREATOR = new Creator<Settings>() {
		@Override
		public Settings createFromParcel(Parcel in) {
			return new Settings(in);
		}

		@Override
		public Settings[] newArray(int size) {
			return new Settings[size];
		}
	};

	public Payouts getPayouts(){
		return payouts;
	}

	public Branding getBranding(){
		return branding;
	}

	public Payments getPayments(){
		return payments;
	}

	public CardPayments getCardPayments(){
		return cardPayments;
	}

	public Dashboard getDashboard(){
		return dashboard;
	}

	@Override
 	public String toString(){
		return 
			"Settings{" + 
			"payouts = '" + payouts + '\'' + 
			",branding = '" + branding + '\'' + 
			",payments = '" + payments + '\'' + 
			",card_payments = '" + cardPayments + '\'' + 
			",dashboard = '" + dashboard + '\'' + 
			"}";
		}
}