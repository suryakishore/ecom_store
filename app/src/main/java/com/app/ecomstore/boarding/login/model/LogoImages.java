package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoImages implements Parcelable {
    @SerializedName("logoImageThumb")
    @Expose
    private String logoImageThumb;
    @SerializedName("logoImageMobile")
    @Expose
    private String logoImageMobile;
    @SerializedName("logoImageweb")
    @Expose
    private String logoImageweb;

    protected LogoImages(Parcel in) {
        logoImageThumb = in.readString();
        logoImageMobile = in.readString();
        logoImageweb = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logoImageThumb);
        dest.writeString(logoImageMobile);
        dest.writeString(logoImageweb);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LogoImages> CREATOR = new Creator<LogoImages>() {
        @Override
        public LogoImages createFromParcel(Parcel in) {
            return new LogoImages(in);
        }

        @Override
        public LogoImages[] newArray(int size) {
            return new LogoImages[size];
        }
    };

    public String getLogoImageThumb ()
    {
        return logoImageThumb;
    }

    public void setLogoImageThumb (String logoImageThumb)
    {
        this.logoImageThumb = logoImageThumb;
    }

    public String getLogoImageMobile ()
    {
        return logoImageMobile;
    }

    public void setLogoImageMobile (String logoImageMobile)
    {
        this.logoImageMobile = logoImageMobile;
    }

    public String getLogoImageweb ()
    {
        return logoImageweb;
    }

    public void setLogoImageweb (String logoImageweb)
    {
        this.logoImageweb = logoImageweb;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [logoImageThumb = "+logoImageThumb+", logoImageMobile = "+logoImageMobile+", logoImageweb = "+logoImageweb+"]";
    }
}
