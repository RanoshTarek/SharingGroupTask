package android.com.sharinggrouptask.di.Module;

import android.com.sharinggrouptask.di.Qualifier.ApplicationContext;
import android.com.sharinggrouptask.di.Scope.ApplicationScope;
import android.content.Context;

import dagger.Module;
import dagger.Provides;


/******************************************************************************
 * Module: ContextModule
 *
 * File Name: ContextModule.java
 *
 * Description: Source file for Application context
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
