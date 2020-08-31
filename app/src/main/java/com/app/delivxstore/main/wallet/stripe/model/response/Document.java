package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document implements Parcelable {

	@SerializedName("back")
	@Expose
	private Object back;

	@SerializedName("details")
	@Expose
	private Object details;

	@SerializedName("front")
	@Expose
	private String front;

	@SerializedName("details_code")
	@Expose
	private Object detailsCode;

	protected Document(Parcel in) {
		front = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(front);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Document> CREATOR = new Creator<Document>() {
		@Override
		public Document createFromParcel(Parcel in) {
			return new Document(in);
		}

		@Override
		public Document[] newArray(int size) {
			return new Document[size];
		}
	};

	public Object getBack(){
		return back;
	}

	public Object getDetails(){
		return details;
	}

	public String getFront(){
		return front;
	}

	public Object getDetailsCode(){
		return detailsCode;
	}

	@Override
 	public String toString(){
		return 
			"Document{" + 
			"back = '" + back + '\'' + 
			",details = '" + details + '\'' + 
			",front = '" + front + '\'' + 
			",details_code = '" + detailsCode + '\'' + 
			"}";
		}
}