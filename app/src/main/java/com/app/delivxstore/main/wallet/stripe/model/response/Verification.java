package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verification implements Parcelable {

	@SerializedName("document")
	@Expose
	private Document document;

	@SerializedName("details")
	@Expose
	private Object details;

	@SerializedName("additional_document")
	@Expose
	private AdditionalDocument additionalDocument;

	@SerializedName("details_code")
	@Expose
	private Object detailsCode;

	@SerializedName("status")
	@Expose
	private String status;

	protected Verification(Parcel in) {
		document = in.readParcelable(Document.class.getClassLoader());
		additionalDocument = in.readParcelable(AdditionalDocument.class.getClassLoader());
		status = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(document, flags);
		dest.writeParcelable(additionalDocument, flags);
		dest.writeString(status);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Verification> CREATOR = new Creator<Verification>() {
		@Override
		public Verification createFromParcel(Parcel in) {
			return new Verification(in);
		}

		@Override
		public Verification[] newArray(int size) {
			return new Verification[size];
		}
	};

	public Document getDocument(){
		return document;
	}

	public Object getDetails(){
		return details;
	}

	public AdditionalDocument getAdditionalDocument(){
		return additionalDocument;
	}

	public Object getDetailsCode(){
		return detailsCode;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Verification{" + 
			"document = '" + document + '\'' + 
			",details = '" + details + '\'' + 
			",additional_document = '" + additionalDocument + '\'' + 
			",details_code = '" + detailsCode + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}