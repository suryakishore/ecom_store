package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TransactionData implements Parcelable {

	@SerializedName("data")
	@Expose
	private ArrayList<TransactionItem> data;

	@SerializedName("pageState")
	@Expose
	private String pageState;

	@SerializedName("totalCount")
	@Expose
	private String totalCount;

	protected TransactionData(Parcel in) {
		pageState = in.readString();
		totalCount = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pageState);
		dest.writeString(totalCount);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<TransactionData> CREATOR = new Creator<TransactionData>() {
		@Override
		public TransactionData createFromParcel(Parcel in) {
			return new TransactionData(in);
		}

		@Override
		public TransactionData[] newArray(int size) {
			return new TransactionData[size];
		}
	};

	public ArrayList<TransactionItem> getData(){
		return data;
	}

	public String getPageState(){
		return pageState;
	}

	public String getTotalCount(){
		return totalCount;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"data = '" + data + '\'' + 
			",pageState = '" + pageState + '\'' + 
			",totalCount = '" + totalCount + '\'' + 
			"}";
		}
}