package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerImages implements Parcelable {
    @SerializedName("bannerImageweb")
    @Expose
    private String bannerImageweb;
    @SerializedName("bannerImageThumb")
    @Expose
    private String bannerImageThumb;
    @SerializedName("bannerImageMobile")
    @Expose
    private String bannerImageMobile;

    protected BannerImages(Parcel in) {
        bannerImageweb = in.readString();
        bannerImageThumb = in.readString();
        bannerImageMobile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bannerImageweb);
        dest.writeString(bannerImageThumb);
        dest.writeString(bannerImageMobile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BannerImages> CREATOR = new Creator<BannerImages>() {
        @Override
        public BannerImages createFromParcel(Parcel in) {
            return new BannerImages(in);
        }

        @Override
        public BannerImages[] newArray(int size) {
            return new BannerImages[size];
        }
    };

    public String getBannerImageweb ()
    {
        return bannerImageweb;
    }

    public void setBannerImageweb (String bannerImageweb)
    {
        this.bannerImageweb = bannerImageweb;
    }

    public String getBannerImageThumb ()
    {
        return bannerImageThumb;
    }

    public void setBannerImageThumb (String bannerImageThumb)
    {
        this.bannerImageThumb = bannerImageThumb;
    }

    public String getBannerImageMobile ()
    {
        return bannerImageMobile;
    }

    public void setBannerImageMobile (String bannerImageMobile)
    {
        this.bannerImageMobile = bannerImageMobile;
    }


}
