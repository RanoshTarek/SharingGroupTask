package android.com.sharinggrouptask.di.Component;


import android.com.sharinggrouptask.View.activity.MainActivity;
import android.com.sharinggrouptask.di.Module.MainActivityModule;
import android.com.sharinggrouptask.di.Module.ViewModelModule;
import android.com.sharinggrouptask.di.Scope.ActivityScope;

import dagger.Component;

/******************************************************************************
 * Module: MainActivityComponent
 *
 * File Name: MainActivityComponent.java
 *
 * Description: Source file for inject MainActivity Component
 *
 * Author: Rana Tarek
 ******************************************************************************/
@ActivityScope
@Component(modules = {ViewModelModule.class, MainActivityModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
}
