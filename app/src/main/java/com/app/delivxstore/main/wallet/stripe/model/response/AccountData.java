package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountData implements Parcelable {

  public static final Creator<AccountData> CREATOR = new Creator<AccountData>() {
    @Override
    public AccountData createFromParcel(Parcel in) {
      return new AccountData(in);
    }

    @Override
    public AccountData[] newArray(int size) {
      return new AccountData[size];
    }
  };
  private boolean isSelected = false;
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("last4")
  @Expose
  private String last4;
  @SerializedName("metadata")
  @Expose
  private Metadata metadata;
  @SerializedName("account_holder_name")
  @Expose
  private String accountHolderName;
  @SerializedName("routing_number")
  @Expose
  private String routingNumber;
  @SerializedName("account_holder_type")
  @Expose
  private String accountHolderType;
  @SerializedName("bank_name")
  @Expose
  private String bankName;
  @SerializedName("fingerprint")
  @Expose
  private String fingerprint;
  @SerializedName("currency")
  @Expose
  private String currency;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("default_for_currency")
  @Expose
  private boolean defaultForCurrency;
  @SerializedName("account")
  @Expose
  private String account;
  @SerializedName("object")
  @Expose
  private String object;
  @SerializedName("status")
  @Expose
  private String status;

  protected AccountData(Parcel in) {
    country = in.readString();
    last4 = in.readString();
    accountHolderName = in.readString();
    routingNumber = in.readString();
    accountHolderType = in.readString();
    bankName = in.readString();
    fingerprint = in.readString();
    currency = in.readString();
    id = in.readString();
    defaultForCurrency = in.readByte() != 0;
    account = in.readString();
    object = in.readString();
    status = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(country);
    dest.writeString(last4);
    dest.writeString(accountHolderName);
    dest.writeString(routingNumber);
    dest.writeString(accountHolderType);
    dest.writeString(bankName);
    dest.writeString(fingerprint);
    dest.writeString(currency);
    dest.writeString(id);
    dest.writeByte((byte)(defaultForCurrency ? 1 : 0));
    dest.writeString(account);
    dest.writeString(object);
    dest.writeString(status);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public String getCountry() {
    return country;
  }

  public String getLast4() {
    return last4;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }

  public String getAccountHolderType() {
    return accountHolderType;
  }

  public String getBankName() {
    return bankName;
  }

  public String getFingerprint() {
    return fingerprint;
  }

  public String getCurrency() {
    return currency;
  }

  public String getId() {
    return id;
  }

  public boolean isDefaultForCurrency() {
    return defaultForCurrency;
  }

  public String getAccount() {
    return account;
  }

  public String getObject() {
    return object;
  }

  public String getStatus() {
    return status;
  }

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	@Override
  public String toString() {
    return "AccountData{" + "country = '" + country + '\'' + ",last4 = '" + last4 + '\'' + "," +
				"metadata = '" + metadata + '\'' + ",account_holder_name = '" + accountHolderName + '\'' + ",routing_number = '" + routingNumber + '\'' + ",account_holder_type = '" + accountHolderType + '\'' + ",bank_name = '" + bankName + '\'' + ",fingerprint = '" + fingerprint + '\'' + ",currency = '" + currency + '\'' + ",id = '" + id + '\'' + ",default_for_currency = '" + defaultForCurrency + '\'' + ",account = '" + account + '\'' + ",object = '" + object + '\'' + ",status = '" + status + '\'' + "}";
  }
}