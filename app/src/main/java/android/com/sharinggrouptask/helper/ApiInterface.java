package android.com.sharinggrouptask.helper;

import android.com.sharinggrouptask.db.entity.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


/******************************************************************************
 * Module: ApiInterface
 *
 * File Name: ApiInterface.java
 *
 * Description: Source file for ApiInterface
 *
 * Author: Rana Tarek
 ******************************************************************************/

public interface ApiInterface {
    @GET("users")
    Single<List<User>> getUserList();

    @GET("users/{id}")
    Single<User> getUserDetails(@Path("id") int id);
}
