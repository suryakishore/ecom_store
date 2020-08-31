package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletDataItem implements Parcelable {

	@SerializedName("walletid")
	@Expose
	private String walletid;

	@SerializedName("balance")
	@Expose
	private String balance;

	@SerializedName("createddate")
	@Expose
	private String createddate;

	@SerializedName("usertype")
	@Expose
	private String usertype;

	@SerializedName("statustext")
	@Expose
	private String statustext;

	@SerializedName("currency")
	@Expose
	private String currency;

	@SerializedName("userid")
	@Expose
	private String userid;

	@SerializedName("status")
	@Expose
	private int status;

	protected WalletDataItem(Parcel in) {
		walletid = in.readString();
		balance = in.readString();
		createddate = in.readString();
		usertype = in.readString();
		statustext = in.readString();
		currency = in.readString();
		userid = in.readString();
		status = in.readInt();
	}

	public static final Creator<WalletDataItem> CREATOR = new Creator<WalletDataItem>() {
		@Override
		public WalletDataItem createFromParcel(Parcel in) {
			return new WalletDataItem(in);
		}

		@Override
		public WalletDataItem[] newArray(int size) {
			return new WalletDataItem[size];
		}
	};

	public String getWalletid(){
		return walletid;
	}

	public String getBalance(){
		return balance;
	}

	public String getCreateddate(){
		return createddate;
	}

	public String getUsertype(){
		return usertype;
	}

	public String getStatustext(){
		return statustext;
	}

	public String getCurrency(){
		return currency;
	}

	public String getUserid(){
		return userid;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"WalletDataItem{" + 
			"walletid = '" + walletid + '\'' + 
			",balance = '" + balance + '\'' + 
			",createddate = '" + createddate + '\'' + 
			",usertype = '" + usertype + '\'' + 
			",statustext = '" + statustext + '\'' + 
			",currency = '" + currency + '\'' + 
			",userid = '" + userid + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(walletid);
		parcel.writeString(balance);
		parcel.writeString(createddate);
		parcel.writeString(usertype);
		parcel.writeString(statustext);
		parcel.writeString(currency);
		parcel.writeString(userid);
		parcel.writeInt(status);
	}
}