package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactPerson implements Parcelable {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("designation")
    @Expose
    private String designation;

    protected ContactPerson(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        designation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(designation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactPerson> CREATOR = new Creator<ContactPerson>() {
        @Override
        public ContactPerson createFromParcel(Parcel in) {
            return new ContactPerson(in);
        }

        @Override
        public ContactPerson[] newArray(int size) {
            return new ContactPerson[size];
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

    public String getDesignation ()
    {
        return designation;
    }

    public void setDesignation (String designation)
    {
        this.designation = designation;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [firstName = "+firstName+", lastName = "+lastName+", designation = "+designation+"]";
    }
}
