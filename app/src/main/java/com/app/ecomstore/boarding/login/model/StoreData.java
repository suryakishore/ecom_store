package com.app.ecomstore.boarding.login.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class StoreData implements Parcelable {
  @SerializedName("orderSettings")
  @Expose
  private String orderSettings;
  @SerializedName("averagePreparationTimeInMins")
  @Expose
  private String averagePreparationTimeInMins;
  @SerializedName("socialLinks")
  @Expose
  private SocialLinks socialLinks;
  @SerializedName("parentSellerNameOrSupplierName")
  @Expose
  private String parentSellerNameOrSupplierName;
  @SerializedName("statusLogs")
  @Expose
  private ArrayList<StatusLogs> statusLogs;
  @SerializedName("documents")
  @Expose
  private ArrayList<Documents> documents;
  @SerializedName("companyName")
  @Expose
  private String companyName;
  @SerializedName("officeNumber")
  @Expose
  private OfficeNumber officeNumber;
  @SerializedName("about")
  @Expose
  private String about;
  @SerializedName("planName")
  @Expose
  private String planName;
  @SerializedName("sellerOpen")
  @Expose
  private String sellerOpen;
  @SerializedName("cityId")
  @Expose
  private String cityId;
  @SerializedName("citiesOfOperation")
  @Expose
  private CitiesOfOperation citiesOfOperation;
  @SerializedName("businessLocationAddress")
  @Expose
  private BusinessLocationAddress businessLocationAddress;
  @SerializedName("parentStore")
  @Expose
  private String parentStore;
  @SerializedName("acceptsCashOnDelivery")
  @Expose
  private String acceptsCashOnDelivery;
  @SerializedName("buyerAccountId")
  @Expose
  private String buyerAccountId;
  @SerializedName("headOffice")
  @Expose
  private HeadOffice headOffice;
  @SerializedName("parentSellerIdOrSupplierId")
  @Expose
  private String parentSellerIdOrSupplierId;
  @SerializedName("storeFronts")
  @Expose
  private ArrayList<String> storeFronts;
  @SerializedName("convenientFeeType")
  @Expose
  private String convenientFeeType;
  @SerializedName("pickUpPrePaymentCard")
  @Expose
  private String pickUpPrePaymentCard;
  @SerializedName("planId")
  @Expose
  private String planId;
  @SerializedName("workingHour")
  @Expose
  private String workingHour;
  @SerializedName("termsAndCondition")
  @Expose
  private String termsAndCondition;
  @SerializedName("sellerTypeId")
  @Expose
  private String sellerTypeId;
  @SerializedName("pricingModel")
  @Expose
  private String pricingModel;
  @SerializedName("averageCostForMealForTwo")
  @Expose
  private String averageCostForMealForTwo;
  @SerializedName("actionByUserId")
  @Expose
  private String registrationDateTimeStamp;
  @SerializedName("cardOnPickUp")
  @Expose
  private String cardOnPickUp;
  @SerializedName("freeDeliveryAbove")
  @Expose
  private String freeDeliveryAbove;
  @SerializedName("planLogs")
  @Expose
  private ArrayList<PlanLogs> planLogs;
  @SerializedName("cashOnPickUp")
  @Expose
  private String cashOnPickUp;
  @SerializedName("cashOnDelivery")
  @Expose
  private String cashOnDelivery;
  @SerializedName("zones")
  @Expose
  private ArrayList<String> zones;
  @SerializedName("convenienceFee")
  @Expose
  private String convenienceFee;
  @SerializedName("statusMsg")
  @Expose
  private String statusMsg;
  @SerializedName("autoAcceptOrders")
  @Expose
  private String autoAcceptOrders;
  @SerializedName("taxId")
  @Expose
  private String taxId;
  @SerializedName("_id")
  @Expose
  private String _id;
  @SerializedName("sellerType")
  @Expose
  private String sellerType;
  @SerializedName("status")
  @Expose
  private String status;

/*
    private ArrayList<String> galleryImages;
*/
  /*
      private ArrayList<String> serviceZones;
  */
  @SerializedName("supportedOrderTypes")
  @Expose
  private String supportedOrderTypes;
  @SerializedName("driverTypeId")
  @Expose
  private String driverTypeId;
  @SerializedName("storeTypeId")
  @Expose
  private String storeTypeId;
  @SerializedName("contactPerson")
  @Expose
  private ContactPerson contactPerson;
  @SerializedName("storeFrontType")
  @Expose
  private String storeFrontType;
  @SerializedName("categoryName")
  @Expose
  private CategoryName categoryName;
  @SerializedName("businessLogoImages")
  @Expose
  private BusinessLogoImages businessLogoImages;
  @SerializedName("cityName")
  @Expose
  private String cityName;
  @SerializedName("orderSettingsFlag")
  @Expose
  private String orderSettingsFlag;
  @SerializedName("ownerName")
  @Expose
  private String ownerName;
  @SerializedName("websiteUrl")
  @Expose
  private String websiteUrl;
  @SerializedName("autoDispatch")
  @Expose
  private String autoDispatch;
  @SerializedName("logoImages")
  @Expose
  private LogoImages logoImages;
  @SerializedName("sellerAverageRating")
  @Expose
  private SellerAverageRating sellerAverageRating;
  @SerializedName("minimumOrder")
  @Expose
  private String minimumOrder;
  @SerializedName("storeName")
  @Expose
  private StoreName storeName;
 /* @SerializedName("fixedPricing")
  @Expose
  private ArrayList<String> fixedPricing;*/
  @SerializedName("lastStatusLog")
  @Expose
  private LastStatusLog lastStatusLog;
  @SerializedName("deliveryPrePaymentCard")
  @Expose
  private String deliveryPrePaymentCard;
  @SerializedName("seqId")
  @Expose
  private String seqId;
  @SerializedName("storeType")
  @Expose
  private String storeType;
  @SerializedName("storeFrontTypeId")
  @Expose
  private String storeFrontTypeId;
  @SerializedName("acceptsWallet")
  @Expose
  private String acceptsWallet;
  @SerializedName("averageDeliveryTimeInMins")
  @Expose
  private String averageDeliveryTimeInMins;
  @SerializedName("contactEmail")
  @Expose
  private String contactEmail;
  @SerializedName("acceptsCard")
  @Expose
  private String acceptsCard;
  @SerializedName("autoAcceptProduct")
  @Expose
  private String autoAcceptProduct;
  @SerializedName("coordinates")
  @Expose
  private Coordinates coordinates;
  @SerializedName("storeId")
  @Expose
  private String storeId;
  @SerializedName("driverType")
  @Expose
  private String driverType;
  @SerializedName("ownerMobile")
  @Expose
  private String ownerMobile;
  @SerializedName("multipleStoreFronts")
  @Expose
  private String multipleStoreFronts;
  @SerializedName("billingAddress")
  @Expose
  private BillingAddress billingAddress;
  @SerializedName("contactPhone")
  @Expose
  private ContactPhone contactPhone;
  @SerializedName("currencyCode")
  @Expose
  private String currencyCode;
  @SerializedName("categoryId")
  @Expose
  private String categoryId;
  @SerializedName("cardOnDelivery")
  @Expose
  private String cardOnDelivery;
  @SerializedName("bannerImages")
  @Expose
  private BannerImages bannerImages;

    protected StoreData(Parcel in) {
        orderSettings = in.readString();
        averagePreparationTimeInMins = in.readString();
        socialLinks = in.readParcelable(SocialLinks.class.getClassLoader());
        parentSellerNameOrSupplierName = in.readString();
        statusLogs = in.createTypedArrayList(StatusLogs.CREATOR);
        documents = in.createTypedArrayList(Documents.CREATOR);
        companyName = in.readString();
        officeNumber = in.readParcelable(OfficeNumber.class.getClassLoader());
        about = in.readString();
        planName = in.readString();
        sellerOpen = in.readString();
        cityId = in.readString();
        citiesOfOperation = in.readParcelable(CitiesOfOperation.class.getClassLoader());
        businessLocationAddress = in.readParcelable(BusinessLocationAddress.class.getClassLoader());
        parentStore = in.readString();
        acceptsCashOnDelivery = in.readString();
        buyerAccountId = in.readString();
        headOffice = in.readParcelable(HeadOffice.class.getClassLoader());
        parentSellerIdOrSupplierId = in.readString();
        storeFronts = in.createStringArrayList();
        convenientFeeType = in.readString();
        pickUpPrePaymentCard = in.readString();
        planId = in.readString();
        workingHour = in.readString();
        termsAndCondition = in.readString();
        sellerTypeId = in.readString();
        pricingModel = in.readString();
        averageCostForMealForTwo = in.readString();
        registrationDateTimeStamp = in.readString();
        cardOnPickUp = in.readString();
        freeDeliveryAbove = in.readString();
        planLogs = in.createTypedArrayList(PlanLogs.CREATOR);
        cashOnPickUp = in.readString();
        cashOnDelivery = in.readString();
        zones = in.createStringArrayList();
        convenienceFee = in.readString();
        statusMsg = in.readString();
        autoAcceptOrders = in.readString();
        taxId = in.readString();
        _id = in.readString();
        sellerType = in.readString();
        status = in.readString();
        supportedOrderTypes = in.readString();
        driverTypeId = in.readString();
        storeTypeId = in.readString();
        contactPerson = in.readParcelable(ContactPerson.class.getClassLoader());
        storeFrontType = in.readString();
        categoryName = in.readParcelable(CategoryName.class.getClassLoader());
        businessLogoImages = in.readParcelable(BusinessLogoImages.class.getClassLoader());
        cityName = in.readString();
        orderSettingsFlag = in.readString();
        ownerName = in.readString();
        websiteUrl = in.readString();
        autoDispatch = in.readString();
        logoImages = in.readParcelable(LogoImages.class.getClassLoader());
        sellerAverageRating = in.readParcelable(SellerAverageRating.class.getClassLoader());
        minimumOrder = in.readString();
       // fixedPricing = in.createStringArrayList();
        deliveryPrePaymentCard = in.readString();
        seqId = in.readString();
        storeType = in.readString();
        storeFrontTypeId = in.readString();
        acceptsWallet = in.readString();
        averageDeliveryTimeInMins = in.readString();
        contactEmail = in.readString();
        acceptsCard = in.readString();
        autoAcceptProduct = in.readString();
        coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        storeId = in.readString();
        driverType = in.readString();
        ownerMobile = in.readString();
        multipleStoreFronts = in.readString();
        billingAddress = in.readParcelable(BillingAddress.class.getClassLoader());
        currencyCode = in.readString();
        categoryId = in.readString();
        cardOnDelivery = in.readString();
        bannerImages = in.readParcelable(BannerImages.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderSettings);
        dest.writeString(averagePreparationTimeInMins);
        dest.writeParcelable(socialLinks, flags);
        dest.writeString(parentSellerNameOrSupplierName);
        dest.writeTypedList(statusLogs);
        dest.writeTypedList(documents);
        dest.writeString(companyName);
        dest.writeParcelable(officeNumber, flags);
        dest.writeString(about);
        dest.writeString(planName);
        dest.writeString(sellerOpen);
        dest.writeString(cityId);
        dest.writeParcelable(citiesOfOperation, flags);
        dest.writeParcelable(businessLocationAddress, flags);
        dest.writeString(parentStore);
        dest.writeString(acceptsCashOnDelivery);
        dest.writeString(buyerAccountId);
        dest.writeParcelable(headOffice, flags);
        dest.writeString(parentSellerIdOrSupplierId);
        dest.writeStringList(storeFronts);
        dest.writeString(convenientFeeType);
        dest.writeString(pickUpPrePaymentCard);
        dest.writeString(planId);
        dest.writeString(workingHour);
        dest.writeString(termsAndCondition);
        dest.writeString(sellerTypeId);
        dest.writeString(pricingModel);
        dest.writeString(averageCostForMealForTwo);
        dest.writeString(registrationDateTimeStamp);
        dest.writeString(cardOnPickUp);
        dest.writeString(freeDeliveryAbove);
        dest.writeTypedList(planLogs);
        dest.writeString(cashOnPickUp);
        dest.writeString(cashOnDelivery);
        dest.writeStringList(zones);
        dest.writeString(convenienceFee);
        dest.writeString(statusMsg);
        dest.writeString(autoAcceptOrders);
        dest.writeString(taxId);
        dest.writeString(_id);
        dest.writeString(sellerType);
        dest.writeString(status);
        dest.writeString(supportedOrderTypes);
        dest.writeString(driverTypeId);
        dest.writeString(storeTypeId);
        dest.writeParcelable(contactPerson, flags);
        dest.writeString(storeFrontType);
        dest.writeParcelable(categoryName, flags);
        dest.writeParcelable(businessLogoImages, flags);
        dest.writeString(cityName);
        dest.writeString(orderSettingsFlag);
        dest.writeString(ownerName);
        dest.writeString(websiteUrl);
        dest.writeString(autoDispatch);
        dest.writeParcelable(logoImages, flags);
        dest.writeParcelable(sellerAverageRating, flags);
        dest.writeString(minimumOrder);
      //  dest.writeStringList(fixedPricing);
        dest.writeString(deliveryPrePaymentCard);
        dest.writeString(seqId);
        dest.writeString(storeType);
        dest.writeString(storeFrontTypeId);
        dest.writeString(acceptsWallet);
        dest.writeString(averageDeliveryTimeInMins);
        dest.writeString(contactEmail);
        dest.writeString(acceptsCard);
        dest.writeString(autoAcceptProduct);
        dest.writeParcelable(coordinates, flags);
        dest.writeString(storeId);
        dest.writeString(driverType);
        dest.writeString(ownerMobile);
        dest.writeString(multipleStoreFronts);
        dest.writeParcelable(billingAddress, flags);
        dest.writeString(currencyCode);
        dest.writeString(categoryId);
        dest.writeString(cardOnDelivery);
        dest.writeParcelable(bannerImages, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreData> CREATOR = new Creator<StoreData>() {
        @Override
        public StoreData createFromParcel(Parcel in) {
            return new StoreData(in);
        }

        @Override
        public StoreData[] newArray(int size) {
            return new StoreData[size];
        }
    };

    public String getOrderSettings() {
    return orderSettings;
  }

  public void setOrderSettings(String orderSettings) {
    this.orderSettings = orderSettings;
  }

  public String getAveragePreparationTimeInMins() {
    return averagePreparationTimeInMins;
  }

  public void setAveragePreparationTimeInMins(String averagePreparationTimeInMins) {
    this.averagePreparationTimeInMins = averagePreparationTimeInMins;
  }

  public SocialLinks getSocialLinks() {
    return socialLinks;
  }

  public void setSocialLinks(SocialLinks socialLinks) {
    this.socialLinks = socialLinks;
  }

  public String getParentSellerNameOrSupplierName() {
    return parentSellerNameOrSupplierName;
  }

  public void setParentSellerNameOrSupplierName(String parentSellerNameOrSupplierName) {
    this.parentSellerNameOrSupplierName = parentSellerNameOrSupplierName;
  }

  public ArrayList<StatusLogs> getStatusLogs() {
    return statusLogs;
  }

  public void setStatusLogs(ArrayList<StatusLogs> statusLogs) {
    this.statusLogs = statusLogs;
  }

  public ArrayList<Documents> getDocuments() {
    return documents;
  }

  public void setDocuments(ArrayList<Documents> documents) {
    this.documents = documents;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public OfficeNumber getOfficeNumber() {
    return officeNumber;
  }

  public void setOfficeNumber(OfficeNumber officeNumber) {
    this.officeNumber = officeNumber;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public String getSellerOpen() {
    return sellerOpen;
  }

  public void setSellerOpen(String sellerOpen) {
    this.sellerOpen = sellerOpen;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public CitiesOfOperation getCitiesOfOperation() {
    return citiesOfOperation;
  }

  public void setCitiesOfOperation(CitiesOfOperation citiesOfOperation) {
    this.citiesOfOperation = citiesOfOperation;
  }

  public BusinessLocationAddress getBusinessLocationAddress() {
    return businessLocationAddress;
  }

  public void setBusinessLocationAddress(BusinessLocationAddress businessLocationAddress) {
    this.businessLocationAddress = businessLocationAddress;
  }

  public String getParentStore() {
    return parentStore;
  }

  public void setParentStore(String parentStore) {
    this.parentStore = parentStore;
  }

  public String getAcceptsCashOnDelivery() {
    return acceptsCashOnDelivery;
  }

  public void setAcceptsCashOnDelivery(String acceptsCashOnDelivery) {
    this.acceptsCashOnDelivery = acceptsCashOnDelivery;
  }

  public String getBuyerAccountId() {
    return buyerAccountId;
  }

  public void setBuyerAccountId(String buyerAccountId) {
    this.buyerAccountId = buyerAccountId;
  }

  public HeadOffice getHeadOffice() {
    return headOffice;
  }

  public void setHeadOffice(HeadOffice headOffice) {
    this.headOffice = headOffice;
  }

  public String getParentSellerIdOrSupplierId() {
    return parentSellerIdOrSupplierId;
  }

  public void setParentSellerIdOrSupplierId(String parentSellerIdOrSupplierId) {
    this.parentSellerIdOrSupplierId = parentSellerIdOrSupplierId;
  }

  public ArrayList<String> getStoreFronts() {
    return storeFronts;
  }

  public void setStoreFronts(ArrayList<String> storeFronts) {
    this.storeFronts = storeFronts;
  }

  public String getConvenientFeeType() {
    return convenientFeeType;
  }

  public void setConvenientFeeType(String convenientFeeType) {
    this.convenientFeeType = convenientFeeType;
  }

  public String getPickUpPrePaymentCard() {
    return pickUpPrePaymentCard;
  }

  public void setPickUpPrePaymentCard(String pickUpPrePaymentCard) {
    this.pickUpPrePaymentCard = pickUpPrePaymentCard;
  }

  public String getPlanId() {
    return planId;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public String getWorkingHour() {
    return workingHour;
  }

  public void setWorkingHour(String workingHour) {
    this.workingHour = workingHour;
  }

  public String getTermsAndCondition() {
    return termsAndCondition;
  }

  public void setTermsAndCondition(String termsAndCondition) {
    this.termsAndCondition = termsAndCondition;
  }

  public String getSellerTypeId() {
    return sellerTypeId;
  }

  public void setSellerTypeId(String sellerTypeId) {
    this.sellerTypeId = sellerTypeId;
  }

  public String getPricingModel() {
    return pricingModel;
  }

  public void setPricingModel(String pricingModel) {
    this.pricingModel = pricingModel;
  }

  public String getAverageCostForMealForTwo() {
    return averageCostForMealForTwo;
  }

  public void setAverageCostForMealForTwo(String averageCostForMealForTwo) {
    this.averageCostForMealForTwo = averageCostForMealForTwo;
  }

  public String getRegistrationDateTimeStamp() {
    return registrationDateTimeStamp;
  }

  public void setRegistrationDateTimeStamp(String registrationDateTimeStamp) {
    this.registrationDateTimeStamp = registrationDateTimeStamp;
  }

  public String getCardOnPickUp() {
    return cardOnPickUp;
  }

  public void setCardOnPickUp(String cardOnPickUp) {
    this.cardOnPickUp = cardOnPickUp;
  }

  public String getFreeDeliveryAbove() {
    return freeDeliveryAbove;
  }

  public void setFreeDeliveryAbove(String freeDeliveryAbove) {
    this.freeDeliveryAbove = freeDeliveryAbove;
  }

  public ArrayList<PlanLogs> getPlanLogs() {
    return planLogs;
  }

  public void setPlanLogs(ArrayList<PlanLogs> planLogs) {
    this.planLogs = planLogs;
  }

  public String getCashOnPickUp() {
    return cashOnPickUp;
  }

  public void setCashOnPickUp(String cashOnPickUp) {
    this.cashOnPickUp = cashOnPickUp;
  }

  public String getCashOnDelivery() {
    return cashOnDelivery;
  }

  public void setCashOnDelivery(String cashOnDelivery) {
    this.cashOnDelivery = cashOnDelivery;
  }

  public ArrayList<String> getZones() {
    return zones;
  }

  public void setZones(ArrayList<String> zones) {
    this.zones = zones;
  }

  public String getConvenienceFee() {
    return convenienceFee;
  }

  public void setConvenienceFee(String convenienceFee) {
    this.convenienceFee = convenienceFee;
  }

  public String getStatusMsg() {
    return statusMsg;
  }

  public void setStatusMsg(String statusMsg) {
    this.statusMsg = statusMsg;
  }

  public String getAutoAcceptOrders() {
    return autoAcceptOrders;
  }

  public void setAutoAcceptOrders(String autoAcceptOrders) {
    this.autoAcceptOrders = autoAcceptOrders;
  }

  public String getTaxId() {
    return taxId;
  }

  public void setTaxId(String taxId) {
    this.taxId = taxId;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getSellerType() {
    return sellerType;
  }

  public void setSellerType(String sellerType) {
    this.sellerType = sellerType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

   /* public ArrayList<String> getGalleryImages ()
    {
        return galleryImages;
    }

    public void setGalleryImages (ArrayList<String> galleryImages)
    {
        this.galleryImages = galleryImages;
    }*/

   /* public ArrayList<String> getServiceZones ()
    {
        return serviceZones;
    }

    public void setServiceZones (ArrayList<String> serviceZones)
    {
        this.serviceZones = serviceZones;
    }*/

  public String getSupportedOrderTypes() {
    return supportedOrderTypes;
  }

  public void setSupportedOrderTypes(String supportedOrderTypes) {
    this.supportedOrderTypes = supportedOrderTypes;
  }

  public String getDriverTypeId() {
    return driverTypeId;
  }

  public void setDriverTypeId(String driverTypeId) {
    this.driverTypeId = driverTypeId;
  }

  public String getStoreTypeId() {
    return storeTypeId;
  }

  public void setStoreTypeId(String storeTypeId) {
    this.storeTypeId = storeTypeId;
  }

  public ContactPerson getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(ContactPerson contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getStoreFrontType() {
    return storeFrontType;
  }

  public void setStoreFrontType(String storeFrontType) {
    this.storeFrontType = storeFrontType;
  }

  public CategoryName getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(CategoryName categoryName) {
    this.categoryName = categoryName;
  }

  public BusinessLogoImages getBusinessLogoImages() {
    return businessLogoImages;
  }

  public void setBusinessLogoImages(BusinessLogoImages businessLogoImages) {
    this.businessLogoImages = businessLogoImages;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getOrderSettingsFlag() {
    return orderSettingsFlag;
  }

  public void setOrderSettingsFlag(String orderSettingsFlag) {
    this.orderSettingsFlag = orderSettingsFlag;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  public String getAutoDispatch() {
    return autoDispatch;
  }

  public void setAutoDispatch(String autoDispatch) {
    this.autoDispatch = autoDispatch;
  }

  public LogoImages getLogoImages() {
    return logoImages;
  }

  public void setLogoImages(LogoImages logoImages) {
    this.logoImages = logoImages;
  }

  public SellerAverageRating getSellerAverageRating() {
    return sellerAverageRating;
  }

  public void setSellerAverageRating(SellerAverageRating sellerAverageRating) {
    this.sellerAverageRating = sellerAverageRating;
  }

  public String getMinimumOrder() {
    return minimumOrder;
  }

  public void setMinimumOrder(String minimumOrder) {
    this.minimumOrder = minimumOrder;
  }

  public StoreName getStoreName() {
    return storeName;
  }

  public void setStoreName(StoreName storeName) {
    this.storeName = storeName;
  }

 /* public ArrayList<String> getFixedPricing() {
    return fixedPricing;
  }

  public void setFixedPricing(ArrayList<String> fixedPricing) {
    this.fixedPricing = fixedPricing;
  }
*/
  public LastStatusLog getLastStatusLog() {
    return lastStatusLog;
  }

  public void setLastStatusLog(LastStatusLog lastStatusLog) {
    this.lastStatusLog = lastStatusLog;
  }

  public String getDeliveryPrePaymentCard() {
    return deliveryPrePaymentCard;
  }

  public void setDeliveryPrePaymentCard(String deliveryPrePaymentCard) {
    this.deliveryPrePaymentCard = deliveryPrePaymentCard;
  }

  public String getSeqId() {
    return seqId;
  }

  public void setSeqId(String seqId) {
    this.seqId = seqId;
  }

  public String getStoreType() {
    return storeType;
  }

  public void setStoreType(String storeType) {
    this.storeType = storeType;
  }

  public String getStoreFrontTypeId() {
    return storeFrontTypeId;
  }

  public void setStoreFrontTypeId(String storeFrontTypeId) {
    this.storeFrontTypeId = storeFrontTypeId;
  }

  public String getAcceptsWallet() {
    return acceptsWallet;
  }

  public void setAcceptsWallet(String acceptsWallet) {
    this.acceptsWallet = acceptsWallet;
  }

  public String getAverageDeliveryTimeInMins() {
    return averageDeliveryTimeInMins;
  }

  public void setAverageDeliveryTimeInMins(String averageDeliveryTimeInMins) {
    this.averageDeliveryTimeInMins = averageDeliveryTimeInMins;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public String getAcceptsCard() {
    return acceptsCard;
  }

  public void setAcceptsCard(String acceptsCard) {
    this.acceptsCard = acceptsCard;
  }

  public String getAutoAcceptProduct() {
    return autoAcceptProduct;
  }

  public void setAutoAcceptProduct(String autoAcceptProduct) {
    this.autoAcceptProduct = autoAcceptProduct;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getDriverType() {
    return driverType;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public String getOwnerMobile() {
    return ownerMobile;
  }

  public void setOwnerMobile(String ownerMobile) {
    this.ownerMobile = ownerMobile;
  }

  public String getMultipleStoreFronts() {
    return multipleStoreFronts;
  }

  public void setMultipleStoreFronts(String multipleStoreFronts) {
    this.multipleStoreFronts = multipleStoreFronts;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public ContactPhone getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(ContactPhone contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCardOnDelivery() {
    return cardOnDelivery;
  }

  public void setCardOnDelivery(String cardOnDelivery) {
    this.cardOnDelivery = cardOnDelivery;
  }

  public BannerImages getBannerImages() {
    return bannerImages;
  }

  public void setBannerImages(BannerImages bannerImages) {
    this.bannerImages = bannerImages;
  }

  @Override
  public String toString() {
    return "ClassPojo [orderSettings = " + orderSettings + ", averagePreparationTimeInMins = "
        + averagePreparationTimeInMins + ", socialLinks = " + socialLinks
        + ", parentSellerNameOrSupplierName = " + parentSellerNameOrSupplierName + ", statusLogs = "
        + statusLogs + ", documents = " + documents + ", companyName = " + companyName
        + ", officeNumber = " + officeNumber + ", about = " + about + ", planName = " + planName
        + ", sellerOpen = " + sellerOpen + ", cityId = " + cityId + ", citiesOfOperation = "
        + citiesOfOperation + ", businessLocationAddress = " + businessLocationAddress
        + ", parentStore = " + parentStore + ", acceptsCashOnDelivery = " + acceptsCashOnDelivery
        + ", buyerAccountId = " + buyerAccountId + ", headOffice = " + headOffice
        + ", parentSellerIdOrSupplierId = " + parentSellerIdOrSupplierId + ", storeFronts = "
        + storeFronts + ", convenientFeeType = " + convenientFeeType + ", pickUpPrePaymentCard = "
        + pickUpPrePaymentCard + ", planId = " + planId + ", workingHour = " + workingHour
        + ", termsAndCondition = " + termsAndCondition + ", sellerTypeId = " + sellerTypeId
        + ", pricingModel = " + pricingModel + ", averageCostForMealForTwo = "
        + averageCostForMealForTwo + ", registrationDateTimeStamp = " + registrationDateTimeStamp
        + ", cardOnPickUp = " + cardOnPickUp + ", freeDeliveryAbove = " + freeDeliveryAbove
        + ", planLogs = " + planLogs + ", cashOnPickUp = " + cashOnPickUp + ", cashOnDelivery = "
        + cashOnDelivery + ", zones = " + zones + ", convenienceFee = " + convenienceFee
        + ", statusMsg = " + statusMsg + ", autoAcceptOrders = " + autoAcceptOrders + ", taxId = "
        + taxId + ", _id = " + _id + ", sellerType = " + sellerType + ", status = " + status
        + ", galleryImages = " + ", serviceZones = " + "" + ", supportedOrderTypes = "
        + supportedOrderTypes + ", driverTypeId = " + driverTypeId + ", storeTypeId = "
        + storeTypeId + ", contactPerson = " + contactPerson + ", storeFrontType = "
        + storeFrontType + ", categoryName = " + categoryName + ", businessLogoImages = "
        + businessLogoImages + ", cityName = " + cityName + ", orderSettingsFlag = "
        + orderSettingsFlag + ", ownerName = " + ownerName + ", websiteUrl = " + websiteUrl
        + ", autoDispatch = " + autoDispatch + ", logoImages = " + logoImages
        + ", sellerAverageRating = " + sellerAverageRating + ", minimumOrder = " + minimumOrder
        + ", storeName = " + storeName + ", fixedPricing = "  + ", lastStatusLog = "
        + lastStatusLog + ", deliveryPrePaymentCard = " + deliveryPrePaymentCard + ", seqId = "
        + seqId + ", storeType = " + storeType + ", storeFrontTypeId = " + storeFrontTypeId
        + ", acceptsWallet = " + acceptsWallet + ", averageDeliveryTimeInMins = "
        + averageDeliveryTimeInMins + ", contactEmail = " + contactEmail + ", acceptsCard = "
        + acceptsCard + ", autoAcceptProduct = " + autoAcceptProduct + ", coordinates = "
        + coordinates + ", storeId = " + storeId + ", driverType = " + driverType
        + ", ownerMobile = " + ownerMobile + ", multipleStoreFronts = " + multipleStoreFronts
        + ", billingAddress = " + billingAddress + ", contactPhone = " + contactPhone
        + ", currencyCode = " + currencyCode + ", categoryId = " + categoryId
        + ", cardOnDelivery = " + cardOnDelivery + ", bannerImages = " + bannerImages + "]";
  }
}
