package com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductReason implements Parcelable  {

  @SerializedName("reason")
  @Expose
  private String reason;

  @SerializedName("reasonMsg")
  @Expose
  private String reasonMsg;
  @SerializedName("customerUnavailable")
  @Expose
  private String customerUnavailable;
  @SerializedName("reasonId")
  @Expose
  private String reasonId;
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("reAttemptAllowed")
  @Expose
  private String reAttemptAllowed;
  @SerializedName("returnToStore")
  @Expose
  private String returnToStore;

  protected ProductReason(Parcel in) {
    reason = in.readString();
    reasonMsg = in.readString();
    customerUnavailable = in.readString();
    reasonId = in.readString();
    _id = in.readString();
    reAttemptAllowed = in.readString();
    returnToStore = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(reason);
    dest.writeString(reasonMsg);
    dest.writeString(customerUnavailable);
    dest.writeString(reasonId);
    dest.writeString(_id);
    dest.writeString(reAttemptAllowed);
    dest.writeString(returnToStore);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ProductReason> CREATOR =
      new Creator<ProductReason>() {
        @Override
        public ProductReason createFromParcel(Parcel in) {
          return new ProductReason(in);
        }

        @Override
        public ProductReason[] newArray(int size) {
          return new ProductReason[size];
        }
      };

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReasonMsg() {
    return reasonMsg;
  }

  public void setReasonMsg(String reasonMsg) {
    this.reasonMsg = reasonMsg;
  }

  public String getCustomerUnavailable() {
    return customerUnavailable;
  }

  public void setCustomerUnavailable(String customerUnavailable) {
    this.customerUnavailable = customerUnavailable;
  }

  public String getReasonId() {
    return reasonId;
  }

  public void setReasonId(String reasonId) {
    this.reasonId = reasonId;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getReAttemptAllowed() {
    return reAttemptAllowed;
  }

  public void setReAttemptAllowed(String reAttemptAllowed) {
    this.reAttemptAllowed = reAttemptAllowed;
  }

  public String getReturnToStore() {
    return returnToStore;
  }

  public void setReturnToStore(String returnToStore) {
    this.returnToStore = returnToStore;
  }
}
