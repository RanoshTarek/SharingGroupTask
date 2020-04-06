package android.com.sharinggrouptask.di.Module;

import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.db.database.UserDatabase;
import android.com.sharinggrouptask.di.Qualifier.ApplicationContext;
import android.com.sharinggrouptask.di.Scope.ApplicationScope;
import android.com.sharinggrouptask.di.Qualifier.DatabaseInfo;
import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;

/******************************************************************************
 * Module: DatabaseModule
 *
 * File Name: DatabaseModule.java
 *
 * Description: Source file for Database Configuration
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Module
public class DatabaseModule {
    @ApplicationContext
    private final Context mContext;

    @DatabaseInfo
    private final String mDBName = "user.db";

    public DatabaseModule(@ApplicationContext Context context) {
        mContext = context;
    }

    @Provides
    @ApplicationScope
    UserDatabase provideDatabase() {
        return Room.databaseBuilder(
                mContext,
                UserDatabase.class,
                mDBName
        ).fallbackToDestructiveMigration().build();
    }

    @Provides
    @DatabaseInfo
    @ApplicationScope
    String provideDatabaseName() {
        return mDBName;
    }


    @Provides
    @ApplicationScope
    UserDao providePersonDao(UserDatabase db) {
        return db.userDao();
    }


}