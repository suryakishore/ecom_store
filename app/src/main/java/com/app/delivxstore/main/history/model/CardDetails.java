package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CardDetails implements Parcelable {

	@SerializedName("last4")
	@Expose
	private String last4;

	@SerializedName("charges")
	@Expose
	private List<ChargesItem> charges;

	@SerializedName("chargeId")
	@Expose
	private String chargeId;

	@SerializedName("cardId")
	@Expose
	private String cardId;

	protected CardDetails(Parcel in) {
		last4 = in.readString();
		charges = in.createTypedArrayList(ChargesItem.CREATOR);
		chargeId = in.readString();
		cardId = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(last4);
		dest.writeTypedList(charges);
		dest.writeString(chargeId);
		dest.writeString(cardId);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<CardDetails> CREATOR = new Creator<CardDetails>() {
		@Override
		public CardDetails createFromParcel(Parcel in) {
			return new CardDetails(in);
		}

		@Override
		public CardDetails[] newArray(int size) {
			return new CardDetails[size];
		}
	};

	public String getLast4(){
		return last4;
	}

	public List<ChargesItem> getCharges(){
		return charges;
	}

	public String getChargeId(){
		return chargeId;
	}

	public String getCardId(){
		return cardId;
	}
}