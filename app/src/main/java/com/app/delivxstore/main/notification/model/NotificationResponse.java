package com.app.delivxstore.main.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse implements Parcelable {

	@SerializedName("data")
	@Expose
	private ArrayList<NotificationData> data;

	@SerializedName("total_count")
	@Expose
	private int totalCount;

	@SerializedName("message")
	@Expose
	private String message;

	protected NotificationResponse(Parcel in) {
		totalCount = in.readInt();
		message = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(totalCount);
		dest.writeString(message);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<NotificationResponse> CREATOR = new Creator<NotificationResponse>() {
		@Override
		public NotificationResponse createFromParcel(Parcel in) {
			return new NotificationResponse(in);
		}

		@Override
		public NotificationResponse[] newArray(int size) {
			return new NotificationResponse[size];
		}
	};

	public ArrayList<NotificationData> getData(){
		return data;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public String getMessage(){
		return message;
	}
}