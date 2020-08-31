package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletEarningDataItem implements Parcelable {

	@SerializedName("balance")
	@Expose
	private String balance;

	@SerializedName("createddate")
	@Expose
	private String createddate;

	@SerializedName("walletearningid")
	@Expose
	private String walletearningid;

	@SerializedName("usertype")
	@Expose
	private String usertype;

	@SerializedName("statustext")
	@Expose
	private String statustext;

	@SerializedName("userid")
	@Expose
	private String userid;

	@SerializedName("status")
	@Expose
	private int status;

	protected WalletEarningDataItem(Parcel in) {
		balance = in.readString();
		createddate = in.readString();
		walletearningid = in.readString();
		usertype = in.readString();
		statustext = in.readString();
		userid = in.readString();
		status = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(balance);
		dest.writeString(createddate);
		dest.writeString(walletearningid);
		dest.writeString(usertype);
		dest.writeString(statustext);
		dest.writeString(userid);
		dest.writeInt(status);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<WalletEarningDataItem> CREATOR = new Creator<WalletEarningDataItem>() {
		@Override
		public WalletEarningDataItem createFromParcel(Parcel in) {
			return new WalletEarningDataItem(in);
		}

		@Override
		public WalletEarningDataItem[] newArray(int size) {
			return new WalletEarningDataItem[size];
		}
	};

	public String getBalance(){
		return balance;
	}

	public String getCreateddate(){
		return createddate;
	}

	public String getWalletearningid(){
		return walletearningid;
	}

	public String getUsertype(){
		return usertype;
	}

	public String getStatustext(){
		return statustext;
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
			"WalletEarningDataItem{" + 
			"balance = '" + balance + '\'' + 
			",createddate = '" + createddate + '\'' + 
			",walletearningid = '" + walletearningid + '\'' + 
			",usertype = '" + usertype + '\'' + 
			",statustext = '" + statustext + '\'' + 
			",userid = '" + userid + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}