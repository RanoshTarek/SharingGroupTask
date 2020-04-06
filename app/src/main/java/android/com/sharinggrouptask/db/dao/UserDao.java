package android.com.sharinggrouptask.db.dao;

import android.com.sharinggrouptask.db.entity.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/******************************************************************************
 *
 * Module: UserDao
 *
 * File Name: UserDao.java
 *
 * Description: Source file for Dao for user.
 *
 * Author: Rana Tarek
 ******************************************************************************/
@Dao
public interface UserDao {
    // Select all from User table
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    // Select one user from users table by id
    @Query("SELECT * FROM User WHERE id=:id")
    LiveData<User> getTaskById(int id);

    // Select one user from users table by id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUsers(List<User> userList);
}
