package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;


@Entity(tableName = "UserDB")
public class UserDB {

    @NonNull
    @PrimaryKey(autoGenerate = true) // we can autogenerate the primary keys in this table
    private int userID;

    @ColumnInfo(name="Name")
    private String name;

    @ColumnInfo(name="Username")
    protected String username;

    @ColumnInfo(name="Password")
    protected String password;

    public UserDB(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        // do we create new UserLibrary object here?
    }

    //method for setting the ID of a User object - we need to this since it CANNOT be null
    public void setUserID(int id) {
        this.userID = id;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }
}
