package com.app.delivxstore.main.wallet.bankaccount.model.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankAccountBody implements Parcelable {

  public static final Creator<BankAccountBody> CREATOR = new Creator<BankAccountBody>() {
    @Override
    public BankAccountBody createFromParcel(Parcel in) {
      return new BankAccountBody(in);
    }

    @Override
    public BankAccountBody[] newArray(int size) {
      return new BankAccountBody[size];
    }
  };

  @SerializedName("account_number")
  @Expose
  private String accountNumber = "";
  @SerializedName("country")
  @Expose
  private String country ;
  @SerializedName("account_holder_type")
  @Expose
  private String accountHolderType;
  @SerializedName("account_holder_name")
  @Expose
  private String accountHolderName = "";
  @SerializedName("currency")
  @Expose
  private String currency = "usd";
  @SerializedName("routing_number")
  @Expose
  private String routingNumber = "110000000";
  @SerializedName("email")
  @Expose
  private String email = "";


  public BankAccountBody() {
  }

  protected BankAccountBody(Parcel in) {
    accountNumber = in.readString();
    country = in.readString();
    accountHolderType = in.readString();
    accountHolderName = in.readString();
    currency = in.readString();
    routingNumber = in.readString();
    email = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(accountNumber);
    dest.writeString(country);
    dest.writeString(accountHolderType);
    dest.writeString(accountHolderName);
    dest.writeString(currency);
    dest.writeString(routingNumber);
    dest.writeString(email);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setAccountHolderType(String accountHolderType) {
    this.accountHolderType = accountHolderType;
  }

  public void setAccountHolderName(String accountHolderName) {
    this.accountHolderName = accountHolderName;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setRoutingNumber(String routingNumber) {
    this.routingNumber = routingNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return
        "BankAccountBody{" +
            "account_number = '" + accountNumber + '\'' +
            ",country = '" + country + '\'' +
            ",account_holder_type = '" + accountHolderType + '\'' +
            ",account_holder_name = '" + accountHolderName + '\'' +
            ",currency = '" + currency + '\'' +
            ",routing_number = '" + routingNumber + '\'' +
            ",email = '" + email + '\'' +
            "}";
  }
}