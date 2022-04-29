package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import  android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


// we use the MainActivity class to instantiate our objects like the current user
// the wines and such
// then after doing this we immediately start the SearchStart activity
// in doing this, we ensure that the user never gets to access MainActivity, thereby the system never overwrites
// the initial attributes of our public static variables

public class MainActivity extends AppCompatActivity {

    private Button button;

    public static ArrayList<User> userList;
    public static User currentUser;
    public static ArrayList<Wine> wineList;
    public static ArrayList<UserLibrary> userLibList;
    public static UserLibrary currentLibrary;
    public static Wine currentWine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the currentWine to be null - every time we pick a wine in either allWines or the library
        // we change this, so that we have access to the wine currently being checked out
        currentWine = null;

        // we create a list of users available throughout the app's fragments
        userList = User.createUsers();
        // we create a currentUser, which will be the one that is logged in
        currentUser = userList.get(1);
        // we create a our list of Wines
        wineList = Wine.createWines();
        // we set up the libraries for our userList
        userLibList = User.createLibraries(userList);
        // we get the userLibrary for our current user
        currentLibrary = userLibList.get(currentUser.userID);

        //currentLibrary.addWineToLibrary(wineList.get(0));
        //currentLibrary.addWineToLibrary(wineList.get(1));


        //currentLibrary.addWineToLibrary(wineList.get(2));
        //currentLibrary.addWineToLibrary(wineList.get(3));
        // currentLibrary.addWineToLibrary(wineList.get(4));

        // start the SearchStart activity
        startActivity(new Intent(MainActivity.this, SearchStart.class));


    }
}

