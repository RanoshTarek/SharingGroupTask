package android.com.sharinggrouptask.di.Component;


import android.com.sharinggrouptask.View.fragment.UserListFragment;
import android.com.sharinggrouptask.di.Module.UserModule;
import android.com.sharinggrouptask.di.Module.ViewModelModule;
import android.com.sharinggrouptask.di.Scope.ActivityScope;

import dagger.Component;

/******************************************************************************
 * Module: FragmentUserListComponent
 *
 * File Name: FragmentUserListComponent.java
 *
 * Description: Source file for inject FragmentUserList Component
 *
 * Author: Rana Tarek
 ******************************************************************************/
@ActivityScope
@Component(modules = {ViewModelModule.class, UserModule.class}, dependencies = ApplicationComponent.class)
public interface FragmentUserListComponent {

    void injectUserListFragment(UserListFragment listFragment);
}
