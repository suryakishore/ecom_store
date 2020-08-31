package com.app.delivxstore.dagger;

import android.app.Application;
import com.app.delivxstore.ApplicationManager;
import com.app.delivxstore.networking.NetworkModule;
import com.app.delivxstore.networking.PythonNetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * <h1>AppComponent</h1>
 * Interface is used to provide the app component
 */
@Singleton
@Component(modules = {AppModule.class, UtilityModule.class, NetworkModule.class,
    PythonNetworkModule.class,
    ActivityBindingModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
  void inject(ApplicationManager application);

  @Override
  void inject(DaggerApplication instance);

  // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this)
  // .build().inject(this);
  // never having to instantiate any modules or say which module we are passing the application to.
  // Application will just be provided into our app graph now.
  @Component.Builder
  interface Builder {
    @BindsInstance
    AppComponent.Builder application(Application application);

    AppComponent build();
  }
}


