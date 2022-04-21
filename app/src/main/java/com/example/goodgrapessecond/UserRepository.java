package com.example.goodgrapessecond;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// this class is a repository of Users, which handles operations of our data
// so it is more cleanly accessible for the application
public class UserRepository {

    private UserDAO mUserDAO;
    private LiveData<List<UserDB>> mAllUsers;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDAO = db.userDao();
    }

    public LiveData<List<UserDB>> getAllUsers() {
        // we get all our users using our defined DAO object and return this list
        mAllUsers = mUserDAO.getAllUsers();
        return mAllUsers;
    }
    //method for inserting users
    public void insertUser(UserDB user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDAO.insertUser(user);
        });
    }

}
