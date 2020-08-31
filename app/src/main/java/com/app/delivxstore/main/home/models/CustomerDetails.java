package com.app.delivxstore.main.home.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CustomerDetails  implements Parcelable{
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("userTypeText")
    @Expose
    private String userTypeText;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("mqttTopic")
    @Expose
    private String mqttTopic;
    @SerializedName("fcmTopic")
    @Expose
    private String fcmTopic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;

    protected CustomerDetails(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        countryCode = in.readString();
        userTypeText = in.readString();
        mobile = in.readString();
        userType = in.readString();
        mqttTopic = in.readString();
        fcmTopic = in.readString();
        email = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(countryCode);
        dest.writeString(userTypeText);
        dest.writeString(mobile);
        dest.writeString(userType);
        dest.writeString(mqttTopic);
        dest.writeString(fcmTopic);
        dest.writeString(email);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomerDetails> CREATOR = new Creator<CustomerDetails>() {
        @Override
        public CustomerDetails createFromParcel(Parcel in) {
            return new CustomerDetails(in);
        }

        @Override
        public CustomerDetails[] newArray(int size) {
            return new CustomerDetails[size];
        }
    };

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getUserTypeText ()
    {
        return userTypeText;
    }

    public void setUserTypeText (String userTypeText)
    {
        this.userTypeText = userTypeText;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    public String getUserType ()
    {
        return userType;
    }

    public void setUserType (String userType)
    {
        this.userType = userType;
    }

    public String getMqttTopic ()
    {
        return mqttTopic;
    }

    public void setMqttTopic (String mqttTopic)
    {
        this.mqttTopic = mqttTopic;
    }

    public String getFcmTopic ()
    {
        return fcmTopic;
    }

    public void setFcmTopic (String fcmTopic)
    {
        this.fcmTopic = fcmTopic;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [firstName = "+firstName+", lastName = "+lastName+", countryCode = "+countryCode+", userTypeText = "+userTypeText+", mobile = "+mobile+", userType = "+userType+", mqttTopic = "+mqttTopic+", fcmTopic = "+fcmTopic+", email = "+email+"]";
    }
}
