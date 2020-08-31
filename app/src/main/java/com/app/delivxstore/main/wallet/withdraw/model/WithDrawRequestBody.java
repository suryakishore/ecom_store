package com.app.delivxstore.main.wallet.withdraw.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawRequestBody implements Parcelable {

	@SerializedName("bankId")
	@Expose
	private String bankId;

	@SerializedName("amount")
	@Expose
	private String amount;

	@SerializedName("notes")
	@Expose
	private String notes;

	@SerializedName("pgId")
	@Expose
	private String pgId;

	@SerializedName("currency")
	@Expose
	private String currency;

	@SerializedName("userType")
	@Expose
	private String userType;

	@SerializedName("autoPayout")
	@Expose
	private boolean autoPayout;

	@SerializedName("userId")
	@Expose
	private String userId;

	@SerializedName("pgName")
	@Expose
	private String pgName;

	public WithDrawRequestBody(){

	}
	public WithDrawRequestBody(Parcel in) {
		bankId = in.readString();
		amount = in.readString();
		notes = in.readString();
		pgId = in.readString();
		currency = in.readString();
		userType = in.readString();
		autoPayout = in.readByte() != 0;
		userId = in.readString();
		pgName = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(bankId);
		dest.writeString(amount);
		dest.writeString(notes);
		dest.writeString(pgId);
		dest.writeString(currency);
		dest.writeString(userType);
		dest.writeByte((byte)(autoPayout ? 1 : 0));
		dest.writeString(userId);
		dest.writeString(pgName);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<WithDrawRequestBody> CREATOR = new Creator<WithDrawRequestBody>() {
		@Override
		public WithDrawRequestBody createFromParcel(Parcel in) {
			return new WithDrawRequestBody(in);
		}

		@Override
		public WithDrawRequestBody[] newArray(int size) {
			return new WithDrawRequestBody[size];
		}
	};

	public String getBankId(){
		return bankId;
	}

	public String getAmount(){
		return amount;
	}

	public String getNotes(){
		return notes;
	}

	public String getPgId(){
		return pgId;
	}

	public String getCurrency(){
		return currency;
	}

	@Override
	public String toString() {
		return "WithDrawRequestBody{" +
				"bankId='" + bankId + '\'' +
				", amount='" + amount + '\'' +
				", notes='" + notes + '\'' +
				", pgId='" + pgId + '\'' +
				", currency='" + currency + '\'' +
				", userType='" + userType + '\'' +
				", autoPayout=" + autoPayout +
				", userId='" + userId + '\'' +
				", pgName='" + pgName + '\'' +
				'}';
	}

	public String getUserType(){
		return userType;
	}

	public boolean isAutoPayout(){
		return autoPayout;
	}

	public String getUserId(){
		return userId;
	}

	public String getPgName(){
		return pgName;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setAutoPayout(boolean autoPayout) {
		this.autoPayout = autoPayout;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
}