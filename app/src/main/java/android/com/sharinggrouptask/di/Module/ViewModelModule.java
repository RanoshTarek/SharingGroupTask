package android.com.sharinggrouptask.di.Module;

import android.com.sharinggrouptask.ViewModel.UserListViewModel;
import android.com.sharinggrouptask.ViewModel.ViewModelFactory;
import android.com.sharinggrouptask.di.Scope.ViewModelKey;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/******************************************************************************
 * Module: ViewModelModule
 *
 * File Name: ViewModelModule.java
 *
 * Description: Source file for ViewModelModule Factory
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
    //You are able to declare ViewModelProvider.Factory dependency in another module. For example in ApplicationModule.

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    abstract ViewModel userViewModel(UserListViewModel userViewModel);
}