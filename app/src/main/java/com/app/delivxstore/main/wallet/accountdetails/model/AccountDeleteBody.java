package com.app.delivxstore.main.wallet.accountdetails.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDeleteBody implements Parcelable {

	@SerializedName("accountId")
	@Expose
	private String accountId;

	public AccountDeleteBody(){

	}
	protected AccountDeleteBody(Parcel in) {
		accountId = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(accountId);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<AccountDeleteBody> CREATOR = new Creator<AccountDeleteBody>() {
		@Override
		public AccountDeleteBody createFromParcel(Parcel in) {
			return new AccountDeleteBody(in);
		}

		@Override
		public AccountDeleteBody[] newArray(int size) {
			return new AccountDeleteBody[size];
		}
	};

	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	@Override
 	public String toString(){
		return 
			"AccountDeleteBody{" + 
			"accountId = '" + accountId + '\'' + 
			"}";
		}
}