package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Favorites extends AppCompatActivity {

    public void browseFavorites(LinearLayout linearLayout) {
        for (int i = 0; i < MainActivity.wineList.size(); i++) {
            if (MainActivity.wineList.get(i).libSaved == true) {
      //if the wine in winelist is saved in library then its true and it will print the exact amount of textview to display the wines
  
                TextView newText = new TextView(this);
                newText.setText("Name:" + MainActivity.wineList.get(i).name + "\r\n" + "Country: " + MainActivity.wineList.get(i).country +  "\r\n" + "Type: " + MainActivity.wineList.get(i).type
                        + "\r\n" + "Grape: " + MainActivity.wineList.get(i).grape + "\r\n" + "Year: " + String.valueOf(MainActivity.wineList.get(i).year) + "\r\n");

                // set the id of the textView to be the ID of the wine
                // this way the id of the textView can be used to find the wine, even if the wines
                // are not in sequential order
                newText.setId(Integer.parseInt(MainActivity.wineList.get(i).wineID));
                int tempID = newText.getId();
                String wineIDToDisplay = MainActivity.wineList.get(tempID).wineID;

                newText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.wine_bottle_foreground,0);
                //add padding to newText with some pixels on top and bottom
                newText.setPadding(0, 35, 0, 35);
                newText.setTextColor(getResources().getColor(R.color.black));

                linearLayout.addView(newText);


                // this should open the Wine Display interface component, and send the id to that component so when we press add to library the id can be added
                newText.setOnClickListener(new View.OnClickListener() {
                                               public void onClick(View v) {
                    // we start a new intent
                   Intent i = new Intent(Favorites.this, wineDisplay.class);
                   // put the value of wineToDisplay into the global variable currentWine
                   MainActivity.currentWine = Wine.findWine(wineIDToDisplay);
                   //start the activity i, which is the wineDisplay activity, but we have now put something into currentWine
                   startActivity(i);
                   // everytime we click set currentWine to the result of finding the wine with the wineIDToDisplay

               }
                }
                );
            }
            }

    }

    //By using onCreate method we call and recreate the activity (which in this case is the navbar)
    //to be displayed on favorites
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_favorites);
        //then we ask the navbar to high light favorites
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.favorites);

        //here we tell the navbar than when one of each icons (search, favorites or profile) is clicked
        //then the application needs to get the new item and display.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchStart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutFavorites);

        browseFavorites(linearLayout);
        System.out.println(MainActivity.wineList.get(0).libSaved);
    }
}










