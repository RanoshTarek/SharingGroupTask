package android.com.sharinggrouptask.di.Component;

import android.com.sharinggrouptask.MyApplication;
import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.di.Module.ContextModule;
import android.com.sharinggrouptask.di.Module.DatabaseModule;
import android.com.sharinggrouptask.di.Module.RetrofitModule;
import android.com.sharinggrouptask.di.Qualifier.ApplicationContext;
import android.com.sharinggrouptask.di.Scope.ApplicationScope;
import android.com.sharinggrouptask.di.Qualifier.DatabaseInfo;
import android.com.sharinggrouptask.helper.ApiInterface;
import android.content.Context;

import dagger.Component;

/******************************************************************************
 * Module: ApplicationComponent
 *
 * File Name: ApplicationComponent.java
 *
 * Description: Source file for inject Application Component
 *
 * Author: Rana Tarek
 ******************************************************************************/
@ApplicationScope
@Component(modules = {ContextModule.class, DatabaseModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    ApiInterface getApiInterface();

    UserDao getUserDao();

    @DatabaseInfo
    String getDatabaseName();

    void injectApplication(MyApplication myApplication);
}