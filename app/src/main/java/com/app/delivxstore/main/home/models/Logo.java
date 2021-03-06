package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Logo implements Parcelable {
    @SerializedName("logoImageThumb")
    @Expose
    private String logoImageThumb;
    @SerializedName("logoImageMobile")
    @Expose
    private String logoImageMobile;
    @SerializedName("logoImageweb")
    @Expose
    private String logoImageweb;

    protected Logo(Parcel in) {
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

    public static final Creator<Logo> CREATOR = new Creator<Logo>() {
        @Override
        public Logo createFromParcel(Parcel in) {
            return new Logo(in);
        }

        @Override
        public Logo[] newArray(int size) {
            return new Logo[size];
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
