package android.com.sharinggrouptask.di.Module;

import android.com.sharinggrouptask.View.fragment.UserDetailsFragment;
import android.com.sharinggrouptask.View.fragment.UserListFragment;
import android.com.sharinggrouptask.di.Scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/******************************************************************************
 * Module: MainActivityModule
 *
 * File Name: MainActivityModule.java
 *
 * Description: Source file for  MainActivity
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Module
public class MainActivityModule {
    @Provides
    @ActivityScope
    UserListFragment getUserListFragment() {
        return new UserListFragment();
    }

    @Provides
    @ActivityScope
    UserDetailsFragment getUserDetailsFragment() {
        return new UserDetailsFragment();
    }

}
