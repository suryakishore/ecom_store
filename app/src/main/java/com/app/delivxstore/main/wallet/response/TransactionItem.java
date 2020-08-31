package com.app.delivxstore.main.wallet.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionItem implements Parcelable {

  public static final Creator<TransactionItem> CREATOR = new Creator<TransactionItem>() {
    @Override
    public TransactionItem createFromParcel(Parcel in) {
      return new TransactionItem(in);
    }

    @Override
    public TransactionItem[] newArray(int size) {
      return new TransactionItem[size];
    }
  };
  @SerializedName("walletid")
  @Expose
  private String walletid;
  @SerializedName("amount")
  @Expose
  private String amount;
  @SerializedName("notes")
  @Expose
  private String notes;
  @SerializedName("txntypetext")
  @Expose
  private String txntypetext;
  @SerializedName("txntimestamp")
  @Expose
  private String txntimestamp;
  @SerializedName("closingbal")
  @Expose
  private String closingbal;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("trigger")
  @Expose
  private String trigger;
  @SerializedName("openingbal")
  @Expose
  private String openingbal;
  @SerializedName("txntype")
  @Expose
  private int txntype;
  @SerializedName("currency")
  @Expose
  private String currency;
  @SerializedName("txnlogid")
  @Expose
  private Object txnlogid;
  @SerializedName("initiatedby")
  @Expose
  private String initiatedby;
  @SerializedName("txnid")
  @Expose
  private String txnid;

  protected TransactionItem(Parcel in) {
    walletid = in.readString();
    amount = in.readString();
    notes = in.readString();
    txntypetext = in.readString();
    txntimestamp = in.readString();
    closingbal = in.readString();
    description = in.readString();
    trigger = in.readString();
    openingbal = in.readString();
    txntype = in.readInt();
    currency = in.readString();
    initiatedby = in.readString();
    txnid = in.readString();
  }

  public String getWalletid() {
    return walletid;
  }

  public String getAmount() {
    return amount;
  }

  public String getNotes() {
    return notes;
  }

  public String getTxntypetext() {
    return txntypetext;
  }

  public String getTxntimestamp() {
    return txntimestamp;
  }

  public String getClosingbal() {
    return closingbal;
  }

  public String getDescription() {
    return description;
  }

  public String getTrigger() {
    return trigger;
  }

  public String getOpeningbal() {
    return openingbal;
  }

  public int getTxntype() {
    return txntype;
  }

  public String getCurrency() {
    return currency;
  }

  public Object getTxnlogid() {
    return txnlogid;
  }

  public String getInitiatedby() {
    return initiatedby;
  }

  public String getTxnid() {
    return txnid;
  }

  @Override
  public String toString() {
    return
        "NotificationData{" +
            "walletid = '" + walletid + '\'' +
            ",amount = '" + amount + '\'' +
            ",notes = '" + notes + '\'' +
            ",txntypetext = '" + txntypetext + '\'' +
            ",txntimestamp = '" + txntimestamp + '\'' +
            ",closingbal = '" + closingbal + '\'' +
            ",description = '" + description + '\'' +
            ",trigger = '" + trigger + '\'' +
            ",openingbal = '" + openingbal + '\'' +
            ",txntype = '" + txntype + '\'' +
            ",currency = '" + currency + '\'' +
            ",txnlogid = '" + txnlogid + '\'' +
            ",initiatedby = '" + initiatedby + '\'' +
            ",txnid = '" + txnid + '\'' +
            "}";
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(walletid);
    parcel.writeString(amount);
    parcel.writeString(notes);
    parcel.writeString(txntypetext);
    parcel.writeString(txntimestamp);
    parcel.writeString(closingbal);
    parcel.writeString(description);
    parcel.writeString(trigger);
    parcel.writeString(openingbal);
    parcel.writeInt(txntype);
    parcel.writeString(currency);
    parcel.writeString(initiatedby);
    parcel.writeString(txnid);
  }
}