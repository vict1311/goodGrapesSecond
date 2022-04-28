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

public class wineDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_display);

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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(wineDisplay.this,Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        // set the wineToShow to be the one stored in currentWine
        Wine wineToShow = MainActivity.currentWine;

        TextView textViewToChangeName = (TextView) findViewById(R.id.nameVAR);
        textViewToChangeName.setText(
                "" + wineToShow.name);

        // we use year as a placeholder for country :)
        TextView textViewToChangeCountry = (TextView) findViewById(R.id.countryVAR);
        textViewToChangeCountry.setText(
                "Country: " + wineToShow.year);

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



    }
}