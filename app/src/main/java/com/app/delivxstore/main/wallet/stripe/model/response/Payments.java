package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payments implements Parcelable {

	@SerializedName("statement_descriptor")
	@Expose
	private String statementDescriptor;

	@SerializedName("statement_descriptor_kanji")
	@Expose
	private Object statementDescriptorKanji;

	@SerializedName("statement_descriptor_kana")
	@Expose
	private Object statementDescriptorKana;

	protected Payments(Parcel in) {
		statementDescriptor = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(statementDescriptor);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Payments> CREATOR = new Creator<Payments>() {
		@Override
		public Payments createFromParcel(Parcel in) {
			return new Payments(in);
		}

		@Override
		public Payments[] newArray(int size) {
			return new Payments[size];
		}
	};

	public String getStatementDescriptor(){
		return statementDescriptor;
	}

	public Object getStatementDescriptorKanji(){
		return statementDescriptorKanji;
	}

	public Object getStatementDescriptorKana(){
		return statementDescriptorKana;
	}

	@Override
 	public String toString(){
		return 
			"Payments{" + 
			"statement_descriptor = '" + statementDescriptor + '\'' + 
			",statement_descriptor_kanji = '" + statementDescriptorKanji + '\'' + 
			",statement_descriptor_kana = '" + statementDescriptorKana + '\'' + 
			"}";
		}
}