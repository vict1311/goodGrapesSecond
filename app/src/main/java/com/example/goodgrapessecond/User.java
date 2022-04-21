package com.example.goodgrapessecond;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class User {

    /**
     * userID identifies a User
     */
    public int userID;
    /** * name is their name
     */
    public String name;
    /** * username is the username to be used for log-ins
     */

    private String username;
    /**
     /** * password is the password to be used for log-ins
     */
    private String password;

    /** constructor that sets object attributes as those given as parameters
     * @param userID is the ID of a User
     * @param name is the User's name
     * @param username is the username
     * @param password is the password
     */
    public User(int userID, String name, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.password = password;


    }

    /** createUsers() is a method to create users and add these to an ArrayList of users
     * @return an ArrayList of Users
     */
    public static ArrayList<User> createUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        User userEmmelie = new User(0, "Emmelie Christensen", "okemmelie", "password123");
        userList.add(userEmmelie);
        User userVictor = new User(1, "Victor Jensen", "vict1311", "NyKode5");
        userList.add(userVictor);
        User userLina = new User(2, "Lina Mancini", "lina123", "linaFraVejle");
        userList.add(userLina);
        return userList;
    }

    /**
     * createLibraries() is a method to create a library for every user
     * @param userList is a list of Users created by createUsers()
     * @return an ArrayList of UserLibraries
     * for loop to get a specific User that has been created and construct a UserLibrary using that as a parameter
     * add this library to LibraryList and return it
     * this is part of the function component Library Modifcation which the User component can acces
     */
    public static ArrayList<UserLibrary> createLibraries(ArrayList<User> userList)  {
        ArrayList<UserLibrary> libraryList = new ArrayList<UserLibrary>();
        for (int i = 0; i < userList.size(); i++) {
            User tempUser = userList.get(i);
            UserLibrary tempLib = new UserLibrary(tempUser);
            libraryList.add(tempLib);
        }

        return libraryList;
    }

}