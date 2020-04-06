package android.com.sharinggrouptask.di.Component;


import android.com.sharinggrouptask.View.fragment.UserDetailsFragment;
import android.com.sharinggrouptask.di.Module.UserModule;
import android.com.sharinggrouptask.di.Module.ViewModelModule;
import android.com.sharinggrouptask.di.Scope.ActivityScope;

import dagger.Component;

/******************************************************************************
 * Module: FragmentUserDetailsComponent
 *
 * File Name: FragmentUserDetailsComponent.java
 *
 * Description: Source file for inject FragmentUserDetail Component
 *
 * Author: Rana Tarek
 ******************************************************************************/

@ActivityScope
@Component(modules = {ViewModelModule.class, UserModule.class}, dependencies = ApplicationComponent.class)
public interface FragmentUserDetailsComponent {

    void injectUserDetailsFragment(UserDetailsFragment detailsFragment);
}
