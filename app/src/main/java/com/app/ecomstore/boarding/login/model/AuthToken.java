package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthToken implements Parcelable {
    @SerializedName("accessExpireAt")
    @Expose
    private String accessExpireAt;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    protected AuthToken(Parcel in) {
        accessExpireAt = in.readString();
        accessToken = in.readString();
        refreshToken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessExpireAt);
        dest.writeString(accessToken);
        dest.writeString(refreshToken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AuthToken> CREATOR = new Creator<AuthToken>() {
        @Override
        public AuthToken createFromParcel(Parcel in) {
            return new AuthToken(in);
        }

        @Override
        public AuthToken[] newArray(int size) {
            return new AuthToken[size];
        }
    };

    public String getAccessExpireAt ()
    {
        return accessExpireAt;
    }

    public void setAccessExpireAt (String accessExpireAt)
    {
        this.accessExpireAt = accessExpireAt;
    }

    public String getAccessToken ()
    {
        return accessToken;
    }

    public void setAccessToken (String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getRefreshToken ()
    {
        return refreshToken;
    }

    public void setRefreshToken (String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accessExpireAt = "+accessExpireAt+", accessToken = "+accessToken+", refreshToken = "+refreshToken+"]";
    }
}
