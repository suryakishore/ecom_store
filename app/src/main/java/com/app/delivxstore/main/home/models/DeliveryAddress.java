package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryAddress implements Parcelable {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("alternatePhoneCode")
    @Expose
    private String alternatePhoneCode;
    @SerializedName("placeId")
    @Expose
    private String placeId;
    @SerializedName("createdTimeStamp")
    @Expose
    private String createdTimeStamp;

    @SerializedName("defaults")
    @Expose
    private String defaults;
    @SerializedName("tagged")
    @Expose
    private String tagged;
    @SerializedName("addLine1")
    @Expose
    private String addLine1;
    @SerializedName("addLine2")
    @Expose
    private String addLine2;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("taggedAs")
    @Expose
    private String taggedAs;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("mobileNumberCode")
    @Expose
    private String mobileNumberCode;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("flatNumber")
    @Expose
    private String flatNumber;
    @SerializedName("alternatePhone")
    @Expose
    private String alternatePhone;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("createdIsoDate")
    @Expose
    private String createdIsoDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("userType")
    @Expose
    private String userType;

    protected DeliveryAddress(Parcel in) {
        country = in.readString();
        city = in.readString();
        mobileNumber = in.readString();
        latitude = in.readString();
        alternatePhoneCode = in.readString();
        placeId = in.readString();
        createdTimeStamp = in.readString();
        defaults = in.readString();
        tagged = in.readString();
        addLine1 = in.readString();
        addLine2 = in.readString();
        state = in.readString();
        landmark = in.readString();
        taggedAs = in.readString();
        longitude = in.readString();
        mobileNumberCode = in.readString();
        pincode = in.readString();
        flatNumber = in.readString();
        alternatePhone = in.readString();
        locality = in.readString();
        userId = in.readString();
        createdIsoDate = in.readString();
        name = in.readString();
        _id = in.readString();
        userType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(mobileNumber);
        dest.writeString(latitude);
        dest.writeString(alternatePhoneCode);
        dest.writeString(placeId);
        dest.writeString(createdTimeStamp);
        dest.writeString(defaults);
        dest.writeString(tagged);
        dest.writeString(addLine1);
        dest.writeString(addLine2);
        dest.writeString(state);
        dest.writeString(landmark);
        dest.writeString(taggedAs);
        dest.writeString(longitude);
        dest.writeString(mobileNumberCode);
        dest.writeString(pincode);
        dest.writeString(flatNumber);
        dest.writeString(alternatePhone);
        dest.writeString(locality);
        dest.writeString(userId);
        dest.writeString(createdIsoDate);
        dest.writeString(name);
        dest.writeString(_id);
        dest.writeString(userType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeliveryAddress> CREATOR = new Creator<DeliveryAddress>() {
        @Override
        public DeliveryAddress createFromParcel(Parcel in) {
            return new DeliveryAddress(in);
        }

        @Override
        public DeliveryAddress[] newArray(int size) {
            return new DeliveryAddress[size];
        }
    };

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getMobileNumber ()
    {
        return mobileNumber;
    }

    public void setMobileNumber (String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getAlternatePhoneCode ()
    {
        return alternatePhoneCode;
    }

    public void setAlternatePhoneCode (String alternatePhoneCode)
    {
        this.alternatePhoneCode = alternatePhoneCode;
    }

    public String getPlaceId ()
    {
        return placeId;
    }

    public void setPlaceId (String placeId)
    {
        this.placeId = placeId;
    }

    public String getCreatedTimeStamp ()
    {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp (String createdTimeStamp)
    {
        this.createdTimeStamp = createdTimeStamp;
    }

    public String getDefault ()
    {
        return defaults;
    }

    public void setDefault (String defaults)
    {
        this.defaults = defaults;
    }

    public String getTagged ()
    {
        return tagged;
    }

    public void setTagged (String tagged)
    {
        this.tagged = tagged;
    }

    public String getAddLine1 ()
    {
        return addLine1;
    }

    public void setAddLine1 (String addLine1)
    {
        this.addLine1 = addLine1;
    }

    public String getAddLine2 ()
    {
        return addLine2;
    }

    public void setAddLine2 (String addLine2)
    {
        this.addLine2 = addLine2;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getLandmark ()
    {
        return landmark;
    }

    public void setLandmark (String landmark)
    {
        this.landmark = landmark;
    }

    public String getTaggedAs ()
    {
        return taggedAs;
    }

    public void setTaggedAs (String taggedAs)
    {
        this.taggedAs = taggedAs;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getMobileNumberCode ()
    {
        return mobileNumberCode;
    }

    public void setMobileNumberCode (String mobileNumberCode)
    {
        this.mobileNumberCode = mobileNumberCode;
    }

    public String getPincode ()
    {
        return pincode;
    }

    public void setPincode (String pincode)
    {
        this.pincode = pincode;
    }

    public String getFlatNumber ()
    {
        return flatNumber;
    }

    public void setFlatNumber (String flatNumber)
    {
        this.flatNumber = flatNumber;
    }

    public String getAlternatePhone ()
    {
        return alternatePhone;
    }

    public void setAlternatePhone (String alternatePhone)
    {
        this.alternatePhone = alternatePhone;
    }

    public String getLocality ()
    {
        return locality;
    }

    public void setLocality (String locality)
    {
        this.locality = locality;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getCreatedIsoDate ()
    {
        return createdIsoDate;
    }

    public void setCreatedIsoDate (String createdIsoDate)
    {
        this.createdIsoDate = createdIsoDate;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getUserType ()
    {
        return userType;
    }

    public void setUserType (String userType)
    {
        this.userType = userType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", city = "+city+", mobileNumber = "+mobileNumber+", latitude = "+latitude+", alternatePhoneCode = "+alternatePhoneCode+", placeId = "+placeId+", createdTimeStamp = "+createdTimeStamp+", defaults = "+defaults+", tagged = "+tagged+", addLine1 = "+addLine1+", addLine2 = "+addLine2+", state = "+state+", landmark = "+landmark+", taggedAs = "+taggedAs+", longitude = "+longitude+", mobileNumberCode = "+mobileNumberCode+", pincode = "+pincode+", flatNumber = "+flatNumber+", alternatePhone = "+alternatePhone+", locality = "+locality+", userId = "+userId+", createdIsoDate = "+createdIsoDate+", name = "+name+", _id = "+_id+", userType = "+userType+"]";
    }
}
