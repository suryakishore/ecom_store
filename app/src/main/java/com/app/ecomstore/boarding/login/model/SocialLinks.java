package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialLinks implements Parcelable {
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("google")
    @Expose
    private String google;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("linkedIn")
    @Expose
    private String linkedIn;

    protected SocialLinks(Parcel in) {
        twitter = in.readString();
        facebook = in.readString();
        google = in.readString();
        instagram = in.readString();
        linkedIn = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(twitter);
        dest.writeString(facebook);
        dest.writeString(google);
        dest.writeString(instagram);
        dest.writeString(linkedIn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SocialLinks> CREATOR = new Creator<SocialLinks>() {
        @Override
        public SocialLinks createFromParcel(Parcel in) {
            return new SocialLinks(in);
        }

        @Override
        public SocialLinks[] newArray(int size) {
            return new SocialLinks[size];
        }
    };

    public String getTwitter ()
    {
        return twitter;
    }

    public void setTwitter (String twitter)
    {
        this.twitter = twitter;
    }

    public String getFacebook ()
    {
        return facebook;
    }

    public void setFacebook (String facebook)
    {
        this.facebook = facebook;
    }

    public String getGoogle ()
    {
        return google;
    }

    public void setGoogle (String google)
    {
        this.google = google;
    }

    public String getInstagram ()
    {
        return instagram;
    }

    public void setInstagram (String instagram)
    {
        this.instagram = instagram;
    }

    public String getLinkedIn ()
    {
        return linkedIn;
    }

    public void setLinkedIn (String linkedIn)
    {
        this.linkedIn = linkedIn;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [twitter = "+twitter+", facebook = "+facebook+", google = "+google+", instagram = "+instagram+", linkedIn = "+linkedIn+"]";
    }
}
