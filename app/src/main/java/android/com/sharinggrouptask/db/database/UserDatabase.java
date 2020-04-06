package android.com.sharinggrouptask.db.database;

import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.db.entity.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/******************************************************************************
 *
 * Module: UserDatabase
 *
 * File Name: UserDatabase.java
 *
 * Description: Source file for Room database class.
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}