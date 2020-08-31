package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Individual implements Parcelable {

	@SerializedName("metadata")
	@Expose
	private Metadata metadata;

	@SerializedName("requirements")
	@Expose
	private Requirements requirements;

	@SerializedName("address")
	@Expose
	private Address address;

	@SerializedName("gender")
	@Expose
	private String gender;

	@SerializedName("created")
	@Expose
	private int created;

	@SerializedName("last_name")
	@Expose
	private String lastName;

	@SerializedName("id_number_provided")
	@Expose
	private boolean idNumberProvided;

	@SerializedName("phone")
	@Expose
	private String phone;

	@SerializedName("dob")
	@Expose
	private Dob dob;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("relationship")
	@Expose
	private Relationship relationship;

	@SerializedName("first_name")
	@Expose
	private String firstName;

	@SerializedName("account")
	@Expose
	private String account;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("ssn_last_4_provided")
	@Expose
	private boolean ssnLast4Provided;

	@SerializedName("verification")
	@Expose
	private Verification verification;

	@SerializedName("object")
	@Expose
	private String object;

	protected Individual(Parcel in) {
		address = in.readParcelable(Address.class.getClassLoader());
		gender = in.readString();
		created = in.readInt();
		lastName = in.readString();
		idNumberProvided = in.readByte() != 0;
		phone = in.readString();
		dob = in.readParcelable(Dob.class.getClassLoader());
		id = in.readString();
		firstName = in.readString();
		account = in.readString();
		email = in.readString();
		ssnLast4Provided = in.readByte() != 0;
		object = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(address, flags);
		dest.writeString(gender);
		dest.writeInt(created);
		dest.writeString(lastName);
		dest.writeByte((byte)(idNumberProvided ? 1 : 0));
		dest.writeString(phone);
		dest.writeParcelable(dob, flags);
		dest.writeString(id);
		dest.writeString(firstName);
		dest.writeString(account);
		dest.writeString(email);
		dest.writeByte((byte)(ssnLast4Provided ? 1 : 0));
		dest.writeString(object);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Individual> CREATOR = new Creator<Individual>() {
		@Override
		public Individual createFromParcel(Parcel in) {
			return new Individual(in);
		}

		@Override
		public Individual[] newArray(int size) {
			return new Individual[size];
		}
	};

	public Metadata getMetadata(){
		return metadata;
	}

	public Requirements getRequirements(){
		return requirements;
	}

	public Address getAddress(){
		return address;
	}

	public String getGender(){
		return gender;
	}

	public int getCreated(){
		return created;
	}

	public String getLastName(){
		return lastName;
	}

	public boolean isIdNumberProvided(){
		return idNumberProvided;
	}

	public String getPhone(){
		return phone;
	}

	public Dob getDob(){
		return dob;
	}

	public String getId(){
		return id;
	}

	public Relationship getRelationship(){
		return relationship;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getAccount(){
		return account;
	}

	public String getEmail(){
		return email;
	}

	public boolean isSsnLast4Provided(){
		return ssnLast4Provided;
	}

	public Verification getVerification(){
		return verification;
	}

	public String getObject(){
		return object;
	}

	@Override
 	public String toString(){
		return 
			"Individual{" + 
			"metadata = '" + metadata + '\'' + 
			",requirements = '" + requirements + '\'' + 
			",address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",created = '" + created + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",id_number_provided = '" + idNumberProvided + '\'' + 
			",phone = '" + phone + '\'' + 
			",dob = '" + dob + '\'' + 
			",id = '" + id + '\'' + 
			",relationship = '" + relationship + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",account = '" + account + '\'' + 
			",email = '" + email + '\'' + 
			",ssn_last_4_provided = '" + ssnLast4Provided + '\'' + 
			",verification = '" + verification + '\'' + 
			",object = '" + object + '\'' + 
			"}";
		}
}