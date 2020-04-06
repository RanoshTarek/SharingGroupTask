package android.com.sharinggrouptask.di.Module;


import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.di.Scope.ActivityScope;
import android.com.sharinggrouptask.helper.ApiInterface;
import android.com.sharinggrouptask.model.repository.UserRepository;

import dagger.Module;
import dagger.Provides;


/******************************************************************************
 * Module: UserModule
 *
 * File Name: UserModule.java
 *
 * Description: Source file for User Repository
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Module
public class UserModule {
    @Provides
    @ActivityScope
    UserRepository getUserRepository(ApiInterface apiInterface, UserDao userDao) {
        return new UserRepository(apiInterface, userDao);
    }
}
