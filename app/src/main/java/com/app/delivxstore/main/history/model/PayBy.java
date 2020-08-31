package com.app.delivxstore.main.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PayBy implements Serializable {

  @SerializedName("wallet")
  @Expose
  private double wallet;
  @SerializedName("cash")
  @Expose
  private double cash;
  @SerializedName("card")
  @Expose
  private double card;

  public double getWallet() {
    return wallet;
  }

  public double getCash() {
    return cash;
  }

  public double getCard() {
    return card;
  }
}