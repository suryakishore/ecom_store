package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payouts implements Parcelable {

	@SerializedName("debit_negative_balances")
	@Expose
	private boolean debitNegativeBalances;

	@SerializedName("statement_descriptor")
	@Expose
	private Object statementDescriptor;

	@SerializedName("schedule")
	@Expose
	private Schedule schedule;

	protected Payouts(Parcel in) {
		debitNegativeBalances = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte((byte)(debitNegativeBalances ? 1 : 0));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Payouts> CREATOR = new Creator<Payouts>() {
		@Override
		public Payouts createFromParcel(Parcel in) {
			return new Payouts(in);
		}

		@Override
		public Payouts[] newArray(int size) {
			return new Payouts[size];
		}
	};

	public boolean isDebitNegativeBalances(){
		return debitNegativeBalances;
	}

	public Object getStatementDescriptor(){
		return statementDescriptor;
	}

	public Schedule getSchedule(){
		return schedule;
	}

	@Override
 	public String toString(){
		return 
			"Payouts{" + 
			"debit_negative_balances = '" + debitNegativeBalances + '\'' + 
			",statement_descriptor = '" + statementDescriptor + '\'' + 
			",schedule = '" + schedule + '\'' + 
			"}";
		}
}