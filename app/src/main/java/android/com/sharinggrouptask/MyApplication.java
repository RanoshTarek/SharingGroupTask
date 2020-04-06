package android.com.sharinggrouptask;

import android.app.Activity;
import android.app.Application;
import android.com.sharinggrouptask.di.Component.ApplicationComponent;
import android.com.sharinggrouptask.di.Component.DaggerApplicationComponent;
import android.com.sharinggrouptask.di.Module.ContextModule;
import android.com.sharinggrouptask.di.Module.DatabaseModule;


/**
 * Created by Rana on 2/12/2019.
 */
public class MyApplication extends Application  {

    private static MyApplication instance;

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //DaggerApplicationComponent
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .databaseModule(new DatabaseModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

}
