package com.app.delivxstore.main.wallet.stripe.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StripeData implements Parcelable {

	@SerializedName("tos_acceptance")
	@Expose
	private TosAcceptance tosAcceptance;

	@SerializedName("country")
	@Expose
	private String country;

	@SerializedName("settings")
	@Expose
	private Settings settings;

	@SerializedName("metadata")
	@Expose
	private Metadata metadata;

	@SerializedName("requirements")
	@Expose
	private Requirements requirements;

	@SerializedName("capabilities")
	@Expose
	private Capabilities capabilities;

	@SerializedName("individual")
	@Expose
	private Individual individual;

	@SerializedName("created")
	@Expose
	private int created;

	@SerializedName("payouts_enabled")
	@Expose
	private boolean payoutsEnabled;

	@SerializedName("type")
	@Expose
	private String type;

	@SerializedName("business_profile")
	@Expose
	private BusinessProfile businessProfile;

	@SerializedName("charges_enabled")
	@Expose
	private boolean chargesEnabled;

	@SerializedName("details_submitted")
	@Expose
	private boolean detailsSubmitted;

	@SerializedName("business_type")
	@Expose
	private String businessType;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("default_currency")
	@Expose
	private String defaultCurrency;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("object")
	@Expose
	private String object;

	@SerializedName("external_accounts")
	@Expose
	private ExternalAccounts externalAccounts;

	protected StripeData(Parcel in) {
		country = in.readString();
		capabilities = in.readParcelable(Capabilities.class.getClassLoader());
		created = in.readInt();
		payoutsEnabled = in.readByte() != 0;
		type = in.readString();
		businessProfile = in.readParcelable(BusinessProfile.class.getClassLoader());
		chargesEnabled = in.readByte() != 0;
		detailsSubmitted = in.readByte() != 0;
		businessType = in.readString();
		id = in.readString();
		defaultCurrency = in.readString();
		email = in.readString();
		object = in.readString();
		externalAccounts = in.readParcelable(ExternalAccounts.class.getClassLoader());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(country);
		dest.writeParcelable(capabilities, flags);
		dest.writeInt(created);
		dest.writeByte((byte)(payoutsEnabled ? 1 : 0));
		dest.writeString(type);
		dest.writeParcelable(businessProfile, flags);
		dest.writeByte((byte)(chargesEnabled ? 1 : 0));
		dest.writeByte((byte)(detailsSubmitted ? 1 : 0));
		dest.writeString(businessType);
		dest.writeString(id);
		dest.writeString(defaultCurrency);
		dest.writeString(email);
		dest.writeString(object);
		dest.writeParcelable(externalAccounts, flags);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StripeData> CREATOR = new Creator<StripeData>() {
		@Override
		public StripeData createFromParcel(Parcel in) {
			return new StripeData(in);
		}

		@Override
		public StripeData[] newArray(int size) {
			return new StripeData[size];
		}
	};

	public TosAcceptance getTosAcceptance(){
		return tosAcceptance;
	}

	public String getCountry(){
		return country;
	}

	public Settings getSettings(){
		return settings;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public Requirements getRequirements(){
		return requirements;
	}

	public Capabilities getCapabilities(){
		return capabilities;
	}

	public Individual getIndividual(){
		return individual;
	}

	public int getCreated(){
		return created;
	}

	public boolean isPayoutsEnabled(){
		return payoutsEnabled;
	}

	public String getType(){
		return type;
	}

	public BusinessProfile getBusinessProfile(){
		return businessProfile;
	}

	public boolean isChargesEnabled(){
		return chargesEnabled;
	}

	public boolean isDetailsSubmitted(){
		return detailsSubmitted;
	}

	public String getBusinessType(){
		return businessType;
	}

	public String getId(){
		return id;
	}

	public String getDefaultCurrency(){
		return defaultCurrency;
	}

	public String getEmail(){
		return email;
	}

	public String getObject(){
		return object;
	}

	public ExternalAccounts getExternalAccounts(){
		return externalAccounts;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"tos_acceptance = '" + tosAcceptance + '\'' + 
			",country = '" + country + '\'' + 
			",settings = '" + settings + '\'' + 
			",metadata = '" + metadata + '\'' + 
			",requirements = '" + requirements + '\'' + 
			",capabilities = '" + capabilities + '\'' + 
			",individual = '" + individual + '\'' + 
			",created = '" + created + '\'' + 
			",payouts_enabled = '" + payoutsEnabled + '\'' + 
			",type = '" + type + '\'' + 
			",business_profile = '" + businessProfile + '\'' + 
			",charges_enabled = '" + chargesEnabled + '\'' + 
			",details_submitted = '" + detailsSubmitted + '\'' + 
			",business_type = '" + businessType + '\'' + 
			",id = '" + id + '\'' + 
			",default_currency = '" + defaultCurrency + '\'' + 
			",email = '" + email + '\'' + 
			",object = '" + object + '\'' + 
			",external_accounts = '" + externalAccounts + '\'' + 
			"}";
		}
}