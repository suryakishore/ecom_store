package com.app.delivxstore.dagger;

import com.app.delivxstore.main.MainActivity;
import com.app.delivxstore.main.MainActivityDaggerModule;
import com.app.delivxstore.main.addCustomer.AddCustomerActivity;
import com.app.delivxstore.main.addCustomer.AddCustomerDaggerModule;
import com.app.delivxstore.main.add_address.AddAddressAct;
import com.app.delivxstore.main.add_address.AddAddressDaggerModule;
import com.app.delivxstore.main.add_address.AddAddressUtilModule;
import com.app.delivxstore.main.add_customer_items.AddLaundryItemActivity;
import com.app.delivxstore.main.add_customer_items.AddLaundryItemDaggerModule;
import com.app.delivxstore.main.cart.CartActivity;
import com.app.delivxstore.main.cart.CartDaggerModule;
import com.app.delivxstore.main.createOrder.CreateOrderActivity;
import com.app.delivxstore.main.createOrder.CreateOrderDaggerModule;
import com.app.delivxstore.main.dispatchDetails.DipatchDetailsDagerModule;
import com.app.delivxstore.main.dispatchDetails.DispatchDetailsActivity;
import com.app.delivxstore.main.editprofile.EditProfileActivity;
import com.app.delivxstore.main.editprofile.EditProfileModule;
import com.app.delivxstore.main.history.attributesdetails.AttributesDaggerModule;
import com.app.delivxstore.main.history.attributesdetails.AttributesDetailsActivity;
import com.app.delivxstore.main.history.historydetails.HistoryDetailsActivity;
import com.app.delivxstore.main.history.historydetails.HistoryDetailsDaggerModule;
import com.app.delivxstore.main.home.HomeFragModule;
import com.app.delivxstore.main.home.tabView.orders.acceptedOrders.AcceptedOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.customCalender.CustomCalenderActivity;
import com.app.delivxstore.main.home.tabView.orders.customCalender.CustomCalenderDaggerModule;
import com.app.delivxstore.main.home.tabView.orders.inAssign.InAssignModule;
import com.app.delivxstore.main.home.tabView.orders.inDispatchOrders.IndispatchOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.newOrders.NewOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.newpending.NewPendingOrderDaggerModule;
import com.app.delivxstore.main.home.tabView.orders.newpending.pending.PendingOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.DialogOrderDetailsActivity;
import com.app.delivxstore.main.home.tabView.orders.orderDetails.DialogOrderDetailsModule;
import com.app.delivxstore.main.home.tabView.orders.pending.CheckOutOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.preOrder.PreOrderDaggerModule;
import com.app.delivxstore.main.home.tabView.orders.preOrder.assign.DriverAssignOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.preOrder.assign.DriverNotAssignOrdersModule;
import com.app.delivxstore.main.home.tabView.orders.sendpackage.SendPackageDaggerModule;
import com.app.delivxstore.main.inventory.InventoryModule;
import com.app.delivxstore.main.laundryitemPhotos.LaundryItemPhotosActivity;
import com.app.delivxstore.main.laundryitemPhotos.LaundryItemPhotosDaggerModule;
import com.app.delivxstore.main.manage_address.ManageAddressAct;
import com.app.delivxstore.main.manage_address.ManageAddressActModule;
import com.app.delivxstore.main.manage_address.ManageAddressDaggerModule;
import com.app.delivxstore.main.mobileView.orderDetails.OrderDetailDaggerModule;
import com.app.delivxstore.main.mobileView.orderDetails.OrderDetailsForMobile;
import com.app.delivxstore.main.mobileView.orderDetails.productNumberBtmSheet.OrderNumberBottomSheetDaggerModule;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.ProductUnavailableSubstituteActivity;
import com.app.delivxstore.main.mobileView.orderDetails.productSubstituteUnavailable.ProductUnavailableSubstituteDaggerModule;
import com.app.delivxstore.main.orderFrom.OrderFromActivity;
import com.app.delivxstore.main.orderFrom.OrderFromDaggerModule;
import com.app.delivxstore.main.payment_choose_card.ChoosePaymentAct;
import com.app.delivxstore.main.payment_choose_card.ChoosePaymentActModule;
import com.app.delivxstore.main.payment_choose_card.ChoosePaymentDaggerModule;
import com.app.delivxstore.main.profile.ProfileModule;
import com.app.delivxstore.main.searchCustomer.SearchCustomerActivity;
import com.app.delivxstore.main.searchCustomer.SearchCustomerDaggerModule;
import com.app.delivxstore.main.wallet.payment.PaymentActivity;
import com.app.delivxstore.main.wallet.payment.PaymentDaggerModule;
import com.app.delivxstore.manual_locate.SearchDaggerModule;
import com.app.delivxstore.manual_locate.SearchLocationAct;
import com.app.delivxstore.mqtt_chat.ChattingActivity;
import com.app.delivxstore.mqtt_chat.ChattingModule;
import com.app.delivxstore.splash.SplashActivity;
import com.app.delivxstore.splash.SplashDaggerModule;
import com.app.ecomstore.addproduct.AddProductActivity;
import com.app.ecomstore.addproduct.addproductbottomsheet.ChooseQtyDaggerModule;
import com.app.ecomstore.boarding.login.EcomLoginActivity;
import com.app.ecomstore.boarding.verifyotp.VerifyOtpActivity;
import com.app.ecomstore.customizations.CustomizationsActivity;
import com.app.ecomstore.drivers.SelectDriversActivity;
import com.app.ecomstore.forceaddproduct.AddManuallyNewProductActivity;
import com.app.ecomstore.forcepick.ForcePickActivity;
import com.app.ecomstore.injection.module.AddManuallyNewProductModelModule;
import com.app.ecomstore.injection.module.AddProductModelModule;
import com.app.ecomstore.injection.module.BarCodeModelModule;
import com.app.ecomstore.injection.module.EcomLoginModelModule;
import com.app.ecomstore.injection.module.EcomManualShipmentModelModule;
import com.app.ecomstore.injection.module.EcomVerifyOtpModelModule;
import com.app.ecomstore.injection.module.ForcePickModelModule;
import com.app.ecomstore.injection.module.PrintLabelModelModule;
import com.app.ecomstore.injection.module.SelectDriversModelModule;
import com.app.ecomstore.injection.module.SelectPackModelModule;
import com.app.ecomstore.injection.module.SelectPartnerModelModule;
import com.app.ecomstore.injection.module.SubStitudeProductModelModule;
import com.app.ecomstore.injection.module.TrackOrderModelModule;
import com.app.ecomstore.injection.module.UpdateAisleModelModule;
import com.app.ecomstore.pack.PackingActivity;
import com.app.ecomstore.partner.SelectPartnerActivity;
import com.app.ecomstore.prescription.PrescriptionDetail;
import com.app.ecomstore.printlabel.PrintLabelsActivity;
import com.app.ecomstore.shipment.ManualShippmentActivity;
import com.app.ecomstore.substitute.ProductSubStituteActivity;
import com.app.ecomstore.trackOrder.TrackOrderActivity;
import com.app.ecomstore.uiutil.barcodescanning.BarCodePreviewActivity;
import com.app.ecomstore.updateasile.UpdateAsileActivity;
import com.app.ecomstore.updateasile.updateasilebottomsheet.UpdateAisleDaggerModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need
 * to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {
  @ActivityScoped
  @ContributesAndroidInjector(modules = {SplashDaggerModule.class})
  abstract SplashActivity provideSplashActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {DipatchDetailsDagerModule.class})
  abstract DispatchDetailsActivity provideDispatchDetailsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {DialogOrderDetailsModule.class})
  abstract DialogOrderDetailsActivity dialogOrderDetailsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {MainActivityDaggerModule.class,
      ProfileModule.class, HomeFragModule.class, NewOrdersModule.class, InAssignModule.class,
      CheckOutOrdersModule.class,
      AcceptedOrdersModule.class, IndispatchOrdersModule.class,
      PreOrderDaggerModule.class, DriverAssignOrdersModule.class, DriverNotAssignOrdersModule.class,
      SendPackageDaggerModule.class, InventoryModule.class, NewPendingOrderDaggerModule.class,
      PendingOrdersModule.class})
  abstract MainActivity mainActivity();

/*  @ActivityScoped
  @ContributesAndroidInjector(modules = {DriversOnlineModule.class})
  abstract DriversListActivity driversListActivity();*/

  @ActivityScoped
  @ContributesAndroidInjector(modules = {CustomCalenderDaggerModule.class})
  abstract CustomCalenderActivity calenderActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {CreateOrderDaggerModule.class})
  abstract CreateOrderActivity getCreateOrder();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AddCustomerDaggerModule.class})
  abstract AddCustomerActivity getAddCustomer();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {OrderFromDaggerModule.class})
  abstract OrderFromActivity getOrderForm();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SearchCustomerDaggerModule.class})
  abstract SearchCustomerActivity getSearchCustomer();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {LaundryItemPhotosDaggerModule.class})
  abstract LaundryItemPhotosActivity laundryItemPhotosActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {OrderDetailDaggerModule.class})
  abstract OrderDetailsForMobile bindOrderDetails();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {ManageAddressDaggerModule.class,
      ManageAddressActModule.class})
  abstract ManageAddressAct manageAddressAct();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AddAddressDaggerModule.class, AddAddressUtilModule.class})
  abstract AddAddressAct addAddressAct();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SearchDaggerModule.class})
  abstract SearchLocationAct searchLocationAct();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AddLaundryItemDaggerModule.class})
  abstract AddLaundryItemActivity getAddLaundryItem();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {CartDaggerModule.class})
  abstract CartActivity getCart();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {ChoosePaymentDaggerModule.class,
      ChoosePaymentActModule.class})
  abstract ChoosePaymentAct paymentMethodAct();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {EcomLoginModelModule.class})
  abstract EcomLoginActivity ecomLoginActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {EcomVerifyOtpModelModule.class})
  abstract VerifyOtpActivity verifyOtpActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {PaymentDaggerModule.class})
  abstract PaymentActivity paymentAct();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {HistoryDetailsDaggerModule.class})
  abstract HistoryDetailsActivity historyDetailsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {ProductUnavailableSubstituteDaggerModule.class})
  abstract ProductUnavailableSubstituteActivity productUnavailableSubstituteActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {EcomManualShipmentModelModule.class})
  abstract ManualShippmentActivity manualShippmentActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SelectPartnerModelModule.class})
  abstract SelectPartnerActivity selectPartnerActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AttributesDaggerModule.class})
  abstract AttributesDetailsActivity attributesDetailsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {EditProfileModule.class})
  abstract EditProfileActivity editProfileActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SelectDriversModelModule.class})
  abstract SelectDriversActivity selectDriversActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SelectPackModelModule.class})
  abstract PackingActivity packingActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {})
  abstract CustomizationsActivity customizationsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {TrackOrderModelModule.class})
  abstract TrackOrderActivity trackOrderActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {})
  abstract PrescriptionDetail prescriptionDetail();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {UpdateAisleModelModule.class,
      UpdateAisleDaggerModule.class})
  abstract UpdateAsileActivity updateAsileActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {BarCodeModelModule.class, ChooseQtyDaggerModule.class})
  abstract BarCodePreviewActivity barCodePreviewActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {ForcePickModelModule.class,
      OrderNumberBottomSheetDaggerModule.class})
  abstract ForcePickActivity forcePickActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {PrintLabelModelModule.class})
  abstract PrintLabelsActivity printLabelsActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {ChattingModule.class})
  abstract ChattingActivity chattingActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AddProductModelModule.class,
      ChooseQtyDaggerModule.class})
  abstract AddProductActivity addProductActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {SubStitudeProductModelModule.class,ChooseQtyDaggerModule.class})
  abstract ProductSubStituteActivity productSubStitudeActivity();

  @ActivityScoped
  @ContributesAndroidInjector(modules = {AddManuallyNewProductModelModule.class})
  abstract AddManuallyNewProductActivity addManuallyNewProductActivity();
}
