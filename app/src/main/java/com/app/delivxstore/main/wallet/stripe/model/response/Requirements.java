package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Requirements implements Parcelable {

	@SerializedName("currently_due")
	@Expose
	private ArrayList<String> currentlyDue;

	@SerializedName("eventually_due")
	@Expose
	private ArrayList<String> eventuallyDue;

	@SerializedName("past_due")
	@Expose
	private ArrayList<String> pastDue;

	@SerializedName("errors")
	@Expose
	private ArrayList<ErrorsItem> errors;

	@SerializedName("pending_verification")
	@Expose
	private ArrayList<Object> pendingVerification;

	@SerializedName("current_deadline")
	@Expose
	private Object currentDeadline;

	@SerializedName("disabled_reason")
	@Expose
	private String disabledReason;

	protected Requirements(Parcel in) {
		currentlyDue = in.createStringArrayList();
		eventuallyDue = in.createStringArrayList();
		pastDue = in.createStringArrayList();
		errors = in.createTypedArrayList(ErrorsItem.CREATOR);
		disabledReason = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringList(currentlyDue);
		dest.writeStringList(eventuallyDue);
		dest.writeStringList(pastDue);
		dest.writeTypedList(errors);
		dest.writeString(disabledReason);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Requirements> CREATOR = new Creator<Requirements>() {
		@Override
		public Requirements createFromParcel(Parcel in) {
			return new Requirements(in);
		}

		@Override
		public Requirements[] newArray(int size) {
			return new Requirements[size];
		}
	};

	public ArrayList<String> getCurrentlyDue(){
		return currentlyDue;
	}

	public ArrayList<String> getEventuallyDue(){
		return eventuallyDue;
	}

	public ArrayList<String> getPastDue(){
		return pastDue;
	}

	public ArrayList<ErrorsItem> getErrors(){
		return errors;
	}

	public ArrayList<Object> getPendingVerification(){
		return pendingVerification;
	}

	public Object getCurrentDeadline(){
		return currentDeadline;
	}

	public String getDisabledReason(){
		return disabledReason;
	}

	@Override
 	public String toString(){
		return 
			"Requirements{" + 
			"currently_due = '" + currentlyDue + '\'' + 
			",eventually_due = '" + eventuallyDue + '\'' + 
			",past_due = '" + pastDue + '\'' + 
			",errors = '" + errors + '\'' + 
			",pending_verification = '" + pendingVerification + '\'' + 
			",current_deadline = '" + currentDeadline + '\'' + 
			",disabled_reason = '" + disabledReason + '\'' + 
			"}";
		}
}