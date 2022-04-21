package com.example.goodgrapessecond;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

// interface to handler User with a Data Access Object
@Dao
public interface UserDAO {
    //method for inserting Users into the user database
    @Insert
    public void insertUser(User user);

    //query for selecting all users and ordering them by userID
    @Query("SELECT * from User ORDER BY userID ASC")
    // we use a List here because it is an interface that associates its objects with their index numbers
    public LiveData<List<User>> getAllUsers();
}
