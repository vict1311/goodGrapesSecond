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


public class MainActivity extends AppCompatActivity {

    private Button button;

    public static ArrayList<User> userList;
    public static User currentUser;
    public static ArrayList<Wine> wineList;
    public static ArrayList<UserLibrary> userLibList;
    public static UserLibrary currentLibrary;

    public static ArrayList<ProductInformation> productInformation;
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

        currentLibrary = userLibList.get(currentUser.userID); //f9ec14
        currentLibrary.addWineToLibrary(wineList.get(0));

        //productInformation = ProductInformation.createProductInformation();
        //System.out.println(productInformation.get(2).producerName);

        //currentLibrary.addWineToLibrary(wineList.get(0));
        //currentLibrary.addWineToLibrary(wineList.get(1));

        // VERY STRANGE - here libSaved is true, but for the VERY same wine in Favorites it is false
        // do we not understand static variables properly???
        System.out.println(wineList.get(0).libSaved);

        //currentLibrary.addWineToLibrary(wineList.get(2));
        //currentLibrary.addWineToLibrary(wineList.get(3));
        // currentLibrary.addWineToLibrary(wineList.get(4));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // our search view!
        SearchView searchView = (SearchView) findViewById(R.id.search);
        // set an event listener for the text you type in the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                for (int i = 0; i < wineList.size(); i++) {
                    if (wineList.get(i).name == query) {
                        String tempWineID = wineList.get(i).wineID;
                        Intent intent = new Intent(MainActivity.this, SearchAllWines.class);
                        intent.putExtra("search_key", tempWineID);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;

                // we starter her en ny activity, som får en String, som er vores query
                // her fra skal vi køre noget lignende i vores browseAllWine
                // men i stedet som searchAllWine, som tjekker for, om den string, du har skrevet ind
                // er lig med name på vores wines
            }

            @Override
            public boolean onQueryTextChange(String query) {
                System.out.println(query);
                return true;
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        //button to viewAllWine
        button = (Button) findViewById(R.id.viewAllWine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllWines();
            }
        });

        //button to type of Wine
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openTypeOfWine();
            }
        });

        //button Grape of Wine
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGrapeofWine();
            }
        });

        //button country of Wine

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCountryOfWine();
            }
        });

        //button to wineDisplay
        button = (Button) findViewById(R.id.emmelie);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWineDisplay();
            }
        });
    }

    public void openAllWines() {
            Intent intent = new Intent(this, AllWines.class);
            startActivity(intent);
    }

    public void openGrapeofWine(){
        Intent intent = new Intent(this, GrapeOfWine.class);
        startActivity(intent);

        //
    }
    public void openTypeOfWine(){
        Intent intent = new Intent(this, TypeOfWine.class);
        startActivity(intent);
    }

    public void openCountryOfWine(){
        Intent intent = new Intent(this, CountryOfWine.class);
        startActivity(intent);

    }

    public void openWineDisplay() {
        Intent intent = new Intent(this, wineDisplay.class);
        startActivity(intent);
    }



    }

