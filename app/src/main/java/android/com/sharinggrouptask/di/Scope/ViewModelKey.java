package android.com.sharinggrouptask.di.Scope;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/******************************************************************************
 * Module: ViewModelKey
 *
 * File Name: ViewModelKey.java
 *
 * Description: Source file for ViewModelKey MapKey
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}