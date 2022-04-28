package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class wineDisplay extends AppCompatActivity {

    public void browseAllWines(LinearLayout linearLayout) {
        for (int i = 0; i < MainActivity.wineList.size(); i++) {
            TextView newText = new TextView(this);
            newText.setText("Name:" + MainActivity.wineList.get(i).name + "\r\n" + "Type: " + MainActivity.wineList.get(i).type
                    + "\r\n" + "Grape: " + MainActivity.wineList.get(i).grape + "\r\n" + "Year: " + String.valueOf(MainActivity.wineList.get(i).year) + "\r\n");
            newText.setId(i);
            linearLayout.addView(newText);

            // this should open the Wine Display interface component, and send the id to that component so when we press add to library the id can be added
            newText.setOnClickListener(new View.OnClickListener() {
                                           public void onClick(View v) {
                                               newText.setBackgroundColor(getResources().getColor(R.color.teal_700));
                                           }
                                       }
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_display);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


        final TextView textViewToChangeName = (TextView) findViewById(R.id.nameVAR);
        textViewToChangeName.setText(
                "" + MainActivity.wineList.get(1).name);

        // we use year as a placeholder for country :)
        final TextView textViewToChangeCountry = (TextView) findViewById(R.id.countryVAR);
        textViewToChangeCountry.setText(
                "Country: " + MainActivity.wineList.get(1).year);

        final TextView textViewToChangeGrape = (TextView) findViewById(R.id.grapeVAR);
        textViewToChangeGrape.setText(
                "Grape: " + MainActivity.wineList.get(1).grape);

        final TextView textViewToChangeType = (TextView) findViewById(R.id.typeVAR);
        textViewToChangeType.setText(
                "Grape: " + MainActivity.wineList.get(1).type);

        /*final TextView textViewToChangeRating = (TextView) findViewById(R.id.ratingVAR);
        textViewToChangeRating.setText(
                "Rating: ");
                */

    }

    Button buttonAdd = (Button) findViewById(R.id.add);
    public void clickAdd()
        if (MainActivity.wineList.get(1).libSaved == false) {
            MainActivity.wineList.get(1).libSaved = true;
    }


        //Button button = (Button)findViewById(R.id.addRemove);
        //button.setOnClickListener(new OnClickListener();
        //public void addRemove()
            //button.setText("Add to Favorites");
        //if (MainActivity.wineList.get(0).libSaved == false) {
            //button.setText("Add to Favorites");
            //MainActivity.wineList.get(0).libSaved = true; }
        //else if (MainActivity.wineList.get(0).libSaved == true) {
            //button.setText("Remove from Favorites");
            //MainActivity.wineList.get(0).libSaved = false; }


}

