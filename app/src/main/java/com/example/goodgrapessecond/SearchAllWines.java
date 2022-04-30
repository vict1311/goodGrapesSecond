package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    /** go through all wines like for Wine Collection interface component
     * create a new TextView for every iteration and set the text here to the name and ID to be the id of i
     * then add the View to the LinearLayout given
     * @param linearLayout a LinearLayout to which the generated TextViews should be added
     */
    public void browseAllWines(LinearLayout linearLayout) {
        for (int i = 0; i < MainActivity.wineList.size(); i++) {

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
            newText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.wine_bottle_foreground,0);
            //add padding to newText with some pixels on top and bottom
            newText.setPadding(0, 35, 0, 35);
            // set color
            //newText.setTextColor(Color.parseColor("#FF000000""));

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_wines);

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

        browseAllWines(linearLayout);
    }
}