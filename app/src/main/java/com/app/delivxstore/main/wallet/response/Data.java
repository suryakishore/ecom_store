package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Data implements Parcelable {

	@SerializedName("walletEarningData")
	@Expose
	private ArrayList<WalletEarningDataItem> walletEarningData;

	@SerializedName("walletData")
	@Expose
	private ArrayList<WalletDataItem> walletData;

	protected Data(Parcel in) {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Data> CREATOR = new Creator<Data>() {
		@Override
		public Data createFromParcel(Parcel in) {
			return new Data(in);
		}

		@Override
		public Data[] newArray(int size) {
			return new Data[size];
		}
	};

	public ArrayList<WalletEarningDataItem> getWalletEarningData(){
		return walletEarningData;
	}

	public ArrayList<WalletDataItem> getWalletData(){
		return walletData;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"walletEarningData = '" + walletEarningData + '\'' + 
			",walletData = '" + walletData + '\'' + 
			"}";
		}
}