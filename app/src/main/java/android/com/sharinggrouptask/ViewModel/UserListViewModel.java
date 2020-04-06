package android.com.sharinggrouptask.ViewModel;


import android.com.sharinggrouptask.db.entity.User;
import android.com.sharinggrouptask.model.repository.UserRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;

/******************************************************************************
 * Module: UserListViewModel
 *
 * File Name: UserListViewModel.java
 *
 * Description: Source file for UserListViewModel
 *
 * Author: Rana Tarek
 ******************************************************************************/

@Module
public class UserListViewModel extends ViewModel {
    public UserRepository userRepository;
    private LiveData<List<User>> responseLiveData;
    private LiveData<User> userLiveData;

    @Inject
    public UserListViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public LiveData<List<User>> getUserList() {
        responseLiveData = userRepository.getUser();
        return responseLiveData;
    }

    public LiveData<User> getUserDetails(int id) {
        userLiveData = userRepository.getUserDetails(id);
        return userLiveData;
    }



}