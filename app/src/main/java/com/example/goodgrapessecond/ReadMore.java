package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReadMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // loop through all wines and if the wines ID is equal to the ID of currentWine then set currentWine
        // to be equal to tempWine. We do this because we don't want a new object that is identical
        // but instead want direct access to the wine in wineList
        //for (int i = 0; i < MainActivity.wineList.size(); i++) {
        //   Wine tempWine = MainActivity.wineList.get(i);
        //    if (tempWine.wineID.equals(MainActivity.currentWine.wineID)) {
        //         MainActivity.currentWine = tempWine;
        //     }
        // }
        //Wine.findWine(MainActivity.currentWine.wineID);
        // set the wineToShow to be the one stored in currentWine
        Wine wineToShow = MainActivity.currentWine;
        TraceabilityInformation newTrace = TraceabilityInformation.determineTraceabilityInformation(wineToShow);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);


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
                        startActivity(new Intent(ReadMore.this,Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        TextView textViewToChangeName = (TextView) findViewById(R.id.nameVAR);
        textViewToChangeName.setText(
                "" + wineToShow.name);

        // we use year as a placeholder for country :)
        TextView textViewToChangeCountry = (TextView) findViewById(R.id.countryVAR);
        textViewToChangeCountry.setText(
                "Agriculture: " + newTrace.agricultureImpact + " kg CO2-e");

        TextView textViewToChangeGrape = (TextView) findViewById(R.id.grapeVAR);
        textViewToChangeGrape.setText(
                "Grape: " + wineToShow.grape);

        TextView textViewToChangeType = (TextView) findViewById(R.id.typeVAR);
        textViewToChangeType.setText(
                "Grape: " + wineToShow.type);

        /*final TextView textViewToChangeRating = (TextView) findViewById(R.id.ratingVAR);
        textViewToChangeRating.setText(
                "Rating: ");
                */



        //System.out.println(MainActivity.wineList.get(0).libSaved);
        //System.out.println(wineToShow.libSaved);
        // HERE IT IS INTERESTING bcos wineToShow is not saved, but the 0th wine, which should be wineToShow, is true!
        //for (int i = 0; i < MainActivity.wineList.size(); i++) {
        //  Wine tempWine = MainActivity.wineList.get(i);
        //  if (tempWine.wineID.equals(wineToShow.wineID)) {
        //     if (tempWine.wineID.equals(wineToShow.wineID)) {
        //             tempWine.libSaved = true;
        //          System.out.println(tempWine.libSaved);
        //      }
        //  }
        //   }

        // right now libSaved for wineToShow is always false????? the above will print false even if the wine is added to lib in MainActivity

        // when you print the contents of winesInLib for current Library you will always get the same thing
        // it will print (in this case) [0, 1, 0] every time you remove wine with ID 0 from the UserLibrary
        // which makes it seem like it has global access to the library, but the update does NOT happen globally



    }
}