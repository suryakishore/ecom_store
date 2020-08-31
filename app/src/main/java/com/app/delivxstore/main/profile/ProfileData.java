package com.app.delivxstore.main.profile;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {

    private String phone;

    private String storeName;

    private String email;

    private String name;

    private String countryCode;

    private String image;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    protected ProfileData(Parcel in) {
        phone = in.readString();
        storeName = in.readString();
        email = in.readString();
        name = in.readString();
        countryCode = in.readString();
        image = in.readString();
    }

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(phone);
        parcel.writeString(storeName);
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(countryCode);
        parcel.writeString(image);
    }
}
