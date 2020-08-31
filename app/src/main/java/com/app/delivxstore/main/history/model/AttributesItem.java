package com.app.delivxstore.main.history.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class AttributesItem implements Parcelable
{
	@SerializedName("attrname")
	@Expose
	private String attrname;

	@SerializedName("value")
	@Expose
	private String value;

	protected AttributesItem(Parcel in) {
		attrname = in.readString();
		value = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(attrname);
		dest.writeString(value);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<AttributesItem> CREATOR = new Creator<AttributesItem>() {
		@Override
		public AttributesItem createFromParcel(Parcel in) {
			return new AttributesItem(in);
		}

		@Override
		public AttributesItem[] newArray(int size) {
			return new AttributesItem[size];
		}
	};

	public String getAttrname(){
		return attrname;
	}

	public String getValue(){
		return value;
	}
}