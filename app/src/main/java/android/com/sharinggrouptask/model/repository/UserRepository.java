package android.com.sharinggrouptask.model.repository;

import android.com.sharinggrouptask.db.Executor;
import android.com.sharinggrouptask.db.dao.UserDao;
import android.com.sharinggrouptask.db.entity.User;
import android.com.sharinggrouptask.helper.ApiInterface;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
/******************************************************************************
 * Module: UserRepository
 *
 * File Name: UserRepository.java
 *
 * Description: Source file for UserRepository to get list of user and user details
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class UserRepository {
    private ApiInterface apiInterface;
    private UserDao userDao;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public UserRepository(ApiInterface apiInterface,UserDao userDao) {
        this.apiInterface = apiInterface;
        this.userDao = userDao;
    }
    /************************************************************************************
     * Function Name: getUser
     * Parameters (in): None
     * Return value: liveData-list of user
     * Description: responsible to get list of user*
     **********************************************************************************/
    public LiveData<List<User>> getUser() {
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        Single<List<User>> userObservable = apiInterface.getUserList();
        DisposableSingleObserver<List<User>> disposableSingleObserver = new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> userResponse) {
                Executor.IOThread(() -> userDao.saveUsers(userResponse));
                data.setValue(userDao.getAllUsers().getValue());
            }

            @Override
            public void onError(Throwable e) {
                data.setValue(null);

                Log.w(getClass().getName(), "onError" + e.getLocalizedMessage() + " " + e.getMessage());
            }
        };
        disposable.add(userObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableSingleObserver));
        return userDao.getAllUsers();
    }
    /************************************************************************************
     * Function Name: getUserDetails
     * Parameters (in): int - id
     * Return value: liveData of user
     * Description: responsible to get user Details
     **********************************************************************************/
    public LiveData<User> getUserDetails(int id) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        Single<User> userObservable = apiInterface.getUserDetails(id);
        DisposableSingleObserver<User> disposableSingleObserver = new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(User user) {
                data.setValue(user);
            }

            @Override
            public void onError(Throwable e) {
                data.setValue(null);
                Log.w(getClass().getName(), "onError" + e.getLocalizedMessage() + " " + e.getMessage());
            }
        };
        disposable.add(userObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableSingleObserver));
        return data;
    }
}
