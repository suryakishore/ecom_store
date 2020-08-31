package com.app.delivxstore.main.mobileView.orderDetails.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Count implements Parcelable {
    @SerializedName("unavailable")
    @Expose
    private String unavailable;
    @SerializedName("substitutes")
    @Expose
    private String substitutes;
    @SerializedName("pending")
    @Expose
    private String pending;
    @SerializedName("packed")
    @Expose
    private String packed;
    @SerializedName("picked")
    @Expose
    private String picked;
    @SerializedName("review")
    @Expose
    private String review;

    protected Count(Parcel in) {
        unavailable = in.readString();
        substitutes = in.readString();
        pending = in.readString();
        packed = in.readString();
        picked = in.readString();
        review = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(unavailable);
        dest.writeString(substitutes);
        dest.writeString(pending);
        dest.writeString(packed);
        dest.writeString(picked);
        dest.writeString(review);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Count> CREATOR = new Creator<Count>() {
        @Override
        public Count createFromParcel(Parcel in) {
            return new Count(in);
        }

        @Override
        public Count[] newArray(int size) {
            return new Count[size];
        }
    };

    public String getUnavailable ()
    {
        return unavailable;
    }

    public void setUnavailable (String unavailable)
    {
        this.unavailable = unavailable;
    }

    public String getSubstitutes ()
    {
        return substitutes;
    }

    public void setSubstitutes (String substitutes)
    {
        this.substitutes = substitutes;
    }

    public String getPending ()
    {
        return pending;
    }

    public void setPending (String pending)
    {
        this.pending = pending;
    }

    public String getPacked ()
    {
        return packed;
    }

    public void setPacked (String packed)
    {
        this.packed = packed;
    }

    public String getPicked() {
        return picked;
    }

    public void setPicked(String picked) {
        this.picked = picked;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [unavailable = "+unavailable+", substitutes = "+substitutes+", pending = "+pending+", packed = "+packed+"]";
    }
}
