package android.com.sharinggrouptask.di.Module;

import android.app.Activity;
import android.com.sharinggrouptask.di.Qualifier.ActivityContext;
import android.com.sharinggrouptask.di.Scope.ActivityScope;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/******************************************************************************
 * Module: MainActivityContextModule
 *
 * File Name: MainActivityContextModule.java
 *
 * Description: Source file for  Activity Context
 *
 * Author: Rana Tarek
 ******************************************************************************/

@Module
public class MainActivityContextModule {
    public Context context;
    private Activity mainActivity;

    public MainActivityContextModule(Activity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public Activity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
