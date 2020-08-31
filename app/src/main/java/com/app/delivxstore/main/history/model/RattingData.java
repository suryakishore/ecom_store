package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RattingData implements Parcelable {

	@SerializedName("reviewDescription")
	@Expose
	private String reviewDescription;

	@SerializedName("isRated")
	@Expose
	private boolean isRated;

	@SerializedName("rating")
	@Expose
	private int rating;

	@SerializedName("reviewTitle")
	@Expose
	private String reviewTitle;

	protected RattingData(Parcel in) {
		reviewDescription = in.readString();
		isRated = in.readByte() != 0;
		rating = in.readInt();
		reviewTitle = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(reviewDescription);
		dest.writeByte((byte) (isRated ? 1 : 0));
		dest.writeInt(rating);
		dest.writeString(reviewTitle);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<RattingData> CREATOR = new Creator<RattingData>() {
		@Override
		public RattingData createFromParcel(Parcel in) {
			return new RattingData(in);
		}

		@Override
		public RattingData[] newArray(int size) {
			return new RattingData[size];
		}
	};

	public String getReviewDescription(){
		return reviewDescription;
	}

	public boolean isIsRated(){
		return isRated;
	}

	public int getRating(){
		return rating;
	}

	public String getReviewTitle(){
		return reviewTitle;
	}
}