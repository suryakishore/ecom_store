package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdditionalDocument implements Parcelable {

  @SerializedName("back")
	@Expose
  private Object back;

  @SerializedName("details")
	@Expose
  private Object details;

  @SerializedName("front")
	@Expose
  private Object front;

  @SerializedName("details_code")
	@Expose
  private Object detailsCode;

	protected AdditionalDocument(Parcel in) {
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<AdditionalDocument> CREATOR = new Creator<AdditionalDocument>() {
		@Override
		public AdditionalDocument createFromParcel(Parcel in) {
			return new AdditionalDocument(in);
		}

		@Override
		public AdditionalDocument[] newArray(int size) {
			return new AdditionalDocument[size];
		}
	};

	public Object getBack() {
    return back;
  }

  public Object getDetails() {
    return details;
  }

  public Object getFront() {
    return front;
  }

  public Object getDetailsCode() {
    return detailsCode;
  }

  @Override
  public String toString() {
    return "AdditionalDocument{" + "back = '" + back + '\'' + ",details = '" + details + '\'' +
				",front = '" + front + '\'' + ",details_code = '" + detailsCode + '\'' + "}";
  }
}