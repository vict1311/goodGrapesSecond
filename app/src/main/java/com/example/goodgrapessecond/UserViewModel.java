package com.example.goodgrapessecond;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private LiveData<List<UserDB>> allUsers;
    private UserRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);

        // we create a new Repository
        mRepository = new UserRepository(application);
        allUsers = mRepository.getAllUsers();
    }

    public LiveData<List<UserDB>> getAllUsers() {
        return allUsers;
    }
    public void insertUser(UserDB user) {
        mRepository.insertUser(user);
    }
}
