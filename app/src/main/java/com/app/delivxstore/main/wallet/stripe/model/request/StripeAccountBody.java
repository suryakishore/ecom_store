package com.app.delivxstore.main.wallet.stripe.model.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StripeAccountBody implements Parcelable {

  public static final Creator<StripeAccountBody> CREATOR = new Creator<StripeAccountBody>() {
    @Override
    public StripeAccountBody createFromParcel(Parcel in) {
      return new StripeAccountBody(in);
    }

    @Override
    public StripeAccountBody[] newArray(int size) {
      return new StripeAccountBody[size];
    }
  };
  @SerializedName("country")
  @Expose
  private String country="US";
  @SerializedName("id_number")
  @Expose
  private String idNumber = "110000000";
  @SerializedName("gender")
  @Expose
  private String gender;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("year")
  @Expose
  private int year;
  @SerializedName("ip")
  @Expose
  private String ip;
  @SerializedName("document")
  @Expose
  private String document;
  @SerializedName("last_name")
  @Expose
  private String lastName;
  @SerializedName("url")
  @Expose
  private String url = "www.appscrip.com";
  @SerializedName("month")
  @Expose
  private int month;
  @SerializedName("phone")
  @Expose
  private String phone;
  @SerializedName("business_type")
  @Expose
  private String businessType="individual";
  @SerializedName("ssn_last_4")
  @Expose
  private String ssnLast4;
  @SerializedName("state")
  @Expose
  private String state;
  @SerializedName("postal_code")
  @Expose
  private int postalCode;
  @SerializedName("day")
  @Expose
  private int day;
  @SerializedName("first_name")
  @Expose
  private String firstName;
  @SerializedName("line1")
  @Expose
  private String line1;
  @SerializedName("email")
  @Expose
  private String email;

  public StripeAccountBody() {

  }

  private StripeAccountBody(Parcel in) {
    country = in.readString();
    idNumber = in.readString();
    gender = in.readString();
    city = in.readString();
    year = in.readInt();
    ip = in.readString();
    document = in.readString();
    lastName = in.readString();
    url = in.readString();
    month = in.readInt();
    phone = in.readString();
    businessType = in.readString();
    ssnLast4 = in.readString();
    state = in.readString();
    postalCode = in.readInt();
    day = in.readInt();
    firstName = in.readString();
    line1 = in.readString();
    email = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(country);
    dest.writeString(idNumber);
    dest.writeString(gender);
    dest.writeString(city);
    dest.writeInt(year);
    dest.writeString(ip);
    dest.writeString(document);
    dest.writeString(lastName);
    dest.writeString(url);
    dest.writeInt(month);
    dest.writeString(phone);
    dest.writeString(businessType);
    dest.writeString(ssnLast4);
    dest.writeString(state);
    dest.writeInt(postalCode);
    dest.writeInt(day);
    dest.writeString(firstName);
    dest.writeString(line1);
    dest.writeString(email);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public void setSsnLast4(String ssnLast4) {
    this.ssnLast4 = ssnLast4;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setPostalCode(int postalCode) {
    this.postalCode = postalCode;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "StripAccountBody{" + "country = '" + country + '\'' + ",id_number = '" + idNumber + '\'' + ",gender = '" + gender + '\'' + ",city = '" + city + '\'' + ",year = '" + year + '\'' + ",ip = '" + ip + '\'' + ",document = '" + document + '\'' + ",last_name = '" + lastName + '\'' + ",url = '" + url + '\'' + ",month = '" + month + '\'' + ",phone = '" + phone + '\'' + ",business_type = '" + businessType + '\'' + ",ssn_last_4 = '" + ssnLast4 + '\'' + ",state = '" + state + '\'' + ",postal_code = '" + postalCode + '\'' + ",day = '" + day + '\'' + ",first_name = '" + firstName + '\'' + ",line1 = '" + line1 + '\'' + ",email = '" + email + '\'' + "}";
  }
}