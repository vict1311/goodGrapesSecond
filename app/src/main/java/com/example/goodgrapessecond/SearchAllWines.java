package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

// we use this activity to create our search function  - it is identical to AllWines except it filters the wines printed by name!
public class SearchAllWines extends AppCompatActivity {


    private Button button;

    private LinearLayout linearLayout;

    // define a tempWine
    Wine tempWine;

    /** go through all wines like for Wine Collection interface component
     * create a new TextView for every iteration and set the text here to the name and ID to be the id of i
     * then add the View to the LinearLayout given
     * @param linearLayout a LinearLayout to which the generated TextViews should be added
     * @param search is the string to search for
     */
    public void searchAllWines(LinearLayout linearLayout, String search) throws Exception {
        for (int i = 0; i < MainActivity.wineList.size(); i++) {
            // check if the current wines name contains the search query (so not is just exactly the search query)
            // make the wine name and query lowercase
            if (MainActivity.wineList.get(i).name.toLowerCase().contains(search.toLowerCase())) {
                // if we find a wine whose name contains the query make tempWine that wine
                tempWine = MainActivity.wineList.get(i);
                TextView newText = new TextView(this);
                newText.setText("Name: " + MainActivity.wineList.get(i).name + "\r\n" + "Type: " + MainActivity.wineList.get(i).type
                        + "\r\n" + "Grape: " + MainActivity.wineList.get(i).grape + "\r\n" + "Year: " + String.valueOf(MainActivity.wineList.get(i).year) + "\r\n");
                // set the id of the textView to be the ID of the wine
                // this way the id of the textView can be used to find the wine, even if the wines
                // are not in sequential order
                newText.setId(Integer.parseInt(MainActivity.wineList.get(i).wineID));
                int tempID = newText.getId();
                String wineIDToDisplay = MainActivity.wineList.get(tempID).wineID;
                //we set a Drawable to appear to the left of the newText TextView
                newText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.wine_bottle_foreground, 0);
                //add padding to newText with some pixels on top and bottom
                newText.setPadding(0, 35, 0, 35);
                // set color
                newText.setTextColor(getResources().getColor(R.color.black));

                linearLayout.addView(newText);

                // this should open the Wine Display interface component, and send the id to that component so when we press add to library the id can be added
                newText.setOnClickListener(new View.OnClickListener() {
                           public void onClick(View v) {
                               Intent i = new Intent(SearchAllWines.this, wineDisplay.class);
                               // put the value of wineToDisplay into the variable currentWine
                               MainActivity.currentWine = Wine.findWine(wineIDToDisplay);
                               //start the activity i, which is the wineDisplay activity
                               startActivity(i);
                           }
                       }
                );
            }
        }
        // if after the for loop tempWine is STILL null we throw an exception
        if (tempWine == null) {
            throw new Exception() ;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_wines);


        //setup our actionbar to make it clickable
        //ActionBar actionBar = getActionBar();
        // we set the action bar to be clickable and to have an arrow
        //actionBar.setDisplayHomeAsUpEnabled(true);

        String searchQuery = getIntent().getStringExtra("search_key");
        System.out.println(searchQuery);

        //setup navBar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);

        // we setup our search!
        bottomNavigationView.setSelectedItemId(R.id.search);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchStart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayoutAllWine);


        // we try to use the searchAllWines method - if that returns an exception, set the textView
        // to be an error message!
        try {
            searchAllWines(linearLayout, searchQuery);
        }
        catch (Exception e) {
            TextView newText = new TextView(this);
            newText.setText("We could not find a wine with that name!");
            newText.setPadding(0, 35, 0, 35);
            newText.setTextColor(getResources().getColor(R.color.black));
            linearLayout.addView(newText);
        }

        // create our back button
        button = findViewById(R.id.backButton);
        // set a drawable for the button that is an arrow from the Android Action Bar icon pack
        button.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_ai_back, 0, 0, 0);
        // onclick listener, which creates a new intent to go back to the SearchStart activity and starts this
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(SearchAllWines.this, SearchStart.class);
                startActivity(i);
            }
        });

    }
}