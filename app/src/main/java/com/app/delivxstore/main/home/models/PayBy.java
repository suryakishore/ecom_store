package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PayBy implements Parcelable {
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("cash")
    @Expose
    private String cash;
    @SerializedName("card")
    @Expose
    private String card;

    protected PayBy(Parcel in) {
        wallet = in.readString();
        cash = in.readString();
        card = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wallet);
        dest.writeString(cash);
        dest.writeString(card);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayBy> CREATOR = new Creator<PayBy>() {
        @Override
        public PayBy createFromParcel(Parcel in) {
            return new PayBy(in);
        }

        @Override
        public PayBy[] newArray(int size) {
            return new PayBy[size];
        }
    };

    public String getWallet ()
    {
        return wallet;
    }

    public void setWallet (String wallet)
    {
        this.wallet = wallet;
    }

    public String getCash ()
    {
        return cash;
    }

    public void setCash (String cash)
    {
        this.cash = cash;
    }

    public String getCard ()
    {
        return card;
    }

    public void setCard (String card)
    {
        this.card = card;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [wallet = "+wallet+", cash = "+cash+", card = "+card+"]";
    }
}
