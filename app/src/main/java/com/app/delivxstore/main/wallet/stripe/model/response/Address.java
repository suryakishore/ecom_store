package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {

  public static final Creator<Address> CREATOR = new Creator<Address>() {
    @Override
    public Address createFromParcel(Parcel in) {
      return new Address(in);
    }

    @Override
    public Address[] newArray(int size) {
      return new Address[size];
    }
  };
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("state")
	@Expose
  private String state;
  @SerializedName("postal_code")
	@Expose
  private String postalCode;
  @SerializedName("line2")
	@Expose
  private String line2;
  @SerializedName("line1")
	@Expose
  private String line1;

  protected Address(Parcel in) {
    country = in.readString();
    city = in.readString();
    state = in.readString();
    postalCode = in.readString();
    line2 = in.readString();
    line1 = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(country);
    dest.writeString(city);
    dest.writeString(state);
    dest.writeString(postalCode);
    dest.writeString(line2);
    dest.writeString(line1);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getLine2() {
    return line2;
  }

  public String getLine1() {
    return line1;
  }

  @Override
  public String toString() {
    return "Address{" + "country = '" + country + '\'' + ",city = '" + city + '\'' + ",state = '" + state + '\'' + ",postal_code = '" + postalCode + '\'' + ",line2 = '" + line2 + '\'' + ",line1 = '" + line1 + '\'' + "}";
  }
}