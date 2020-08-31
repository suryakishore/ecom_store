package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationship implements Parcelable {

	@SerializedName("owner")
	@Expose
	private boolean owner;

	@SerializedName("executive")
	@Expose
	private boolean executive;

	@SerializedName("director")
	@Expose
	private boolean director;

	@SerializedName("title")
	@Expose
	private Object title;

	@SerializedName("representative")
	@Expose
	private boolean representative;

	@SerializedName("percent_ownership")
	@Expose
	private Object percentOwnership;

	protected Relationship(Parcel in) {
		owner = in.readByte() != 0;
		executive = in.readByte() != 0;
		director = in.readByte() != 0;
		representative = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte((byte)(owner ? 1 : 0));
		dest.writeByte((byte)(executive ? 1 : 0));
		dest.writeByte((byte)(director ? 1 : 0));
		dest.writeByte((byte)(representative ? 1 : 0));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Relationship> CREATOR = new Creator<Relationship>() {
		@Override
		public Relationship createFromParcel(Parcel in) {
			return new Relationship(in);
		}

		@Override
		public Relationship[] newArray(int size) {
			return new Relationship[size];
		}
	};

	public boolean isOwner(){
		return owner;
	}

	public boolean isExecutive(){
		return executive;
	}

	public boolean isDirector(){
		return director;
	}

	public Object getTitle(){
		return title;
	}

	public boolean isRepresentative(){
		return representative;
	}

	public Object getPercentOwnership(){
		return percentOwnership;
	}

	@Override
 	public String toString(){
		return 
			"Relationship{" + 
			"owner = '" + owner + '\'' + 
			",executive = '" + executive + '\'' + 
			",director = '" + director + '\'' + 
			",title = '" + title + '\'' + 
			",representative = '" + representative + '\'' + 
			",percent_ownership = '" + percentOwnership + '\'' + 
			"}";
		}
}