package com.app.delivxstore.dagger;


import android.content.Context;

import com.app.delivxstore.BuildConfig;
import com.app.delivxstore.data.source.PreferenceHelperDataSource;
import com.app.delivxstore.location.AddressProviderFromLocation;
import com.app.delivxstore.location.LocationProvider;
import com.app.delivxstore.location.PlaceProvider;
import com.app.delivxstore.location.RxAddressObserver;
import com.app.delivxstore.location.RxLocationObserver;
import com.app.delivxstore.location.RxPlaceSearchObserver;
import com.app.delivxstore.networking.NetworkService;
import com.app.delivxstore.observers.Connectable;
import com.app.delivxstore.observers.RxLogOutObsever;
import com.app.delivxstore.observers.RxStoreObserver;
import com.app.delivxstore.utility.FontUtils;
import com.app.delivxstore.utility.UploadAwsImage;
import com.app.delivxstore.utility.Utility;

import com.google.gson.Gson;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by DELL on 10-01-2018.
 */

@Module
public class UtilityModule {

    @Provides
    @Singleton
    Utility getUtility(){
        return new Utility();
    }

    @Provides
    @Singleton
    FontUtils getFontUtils(Context context)
    {
        return new FontUtils(context);
    }

    @Provides
    @Singleton
    RxLogOutObsever getLogOutObserver()
    {
        return new RxLogOutObsever();
    }

    @Provides
    @Singleton
    UploadAwsImage getAmazonInstance(Context context,
                                     PreferenceHelperDataSource preferenceHelperDataSource,
                                     NetworkService networkService) {
        return new UploadAwsImage(context,preferenceHelperDataSource,networkService);
    }



    @Provides
    @Singleton
    RxStoreObserver getStoreObserver()
    {
        return new RxStoreObserver();
    }

    @Provides
    @Singleton
    Connectable getConnectable()
    {
        return new Connectable();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }



    /*@Provides
    @Singleton
    Upload_file_AmazonS3 getAmazonInstance(Context context){
        return new Upload_file_AmazonS3(context,VariableConstant.COGNITO_POOL_ID);
    }

    @Provides
    AcknowledgeHelper getAcknowledgeHelper(PreferenceHelperDataSource dataSource, DispatcherService dispatcherService){
        return new AcknowledgeHelper(dataSource,dispatcherService);
    }

    @Provides
    @Singleton
    MQTTManager mqttManager(Context context, AcknowledgeHelper acknowledgeHelper, c helperDataSource)
    {
        return new MQTTManager(context,acknowledgeHelper,helperDataSource);
    }


*/

    @Provides
    @Singleton
    RxPlaceSearchObserver placeSearchObserver()
    {
        return new RxPlaceSearchObserver();
    }

    @Provides
    @Singleton
    PlaceProvider placeProvider(ReactiveLocationProvider context, RxPlaceSearchObserver observer)
    {
        return new PlaceProvider(context,observer);
    }
    @Provides
    @Singleton
    RxLocationObserver locationObserver()
    {
        return new RxLocationObserver();
    }

    @Provides
    @Singleton
    LocationProvider locationProvider(Context context, RxLocationObserver observer)
    {
        return new LocationProvider(context,observer);
    }

    @Provides
    @Singleton
    ReactiveLocationProvider reactiveLocationProvider(Context context)
    {
        return new ReactiveLocationProvider(context);
    }
    @Provides
    @Singleton
    RxAddressObserver rxAddressObserver()
    {
        return new RxAddressObserver();
    }

    @Provides
    @Singleton
    AddressProviderFromLocation addressProviderFromLocation(ReactiveLocationProvider locationProvider, RxAddressObserver addressObserver)
    {
        return new AddressProviderFromLocation(locationProvider,addressObserver);
    }

   /* @Provides
    @Singleton
    CommonApi commonApi(Context mActivity, SessionHelper manager,
                        NetworkService service)
    {
        return new CommonApi(mActivity,manager,service);
    }




    @Provides
    @Singleton
    RxCategoryNotifier provideRxConfirmPickNotifier()
    {
        return new RxCategoryNotifier();
    }

    @Provides
    @Singleton
    RxStoreChangeNotifier provideRxStoreChange()
    {
        return new RxStoreChangeNotifier();
    }

    @Provides
    @Singleton
    RxFilterDataNotifier provideFilterData()
    {
        return new RxFilterDataNotifier();
    }

    @Provides
    @Singleton
    RxStoreTypeObserver fetchStoreTypeObserver()
    {
        return new RxStoreTypeObserver();
    }


    @Provides
    @Singleton
    RxConnectable getConnectable()
    {
        return new RxConnectable();
    }*/
}
