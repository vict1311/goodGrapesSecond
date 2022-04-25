package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AllWines extends AppCompatActivity {


    private Button button;


    /** go through all wines like for Wine Collection interface component
     * create a new TextView for every iteration and set the text here to the name and ID to be the id of i
     * then add the View to the LinearLayout given
     * @param linearLayout a LinearLayout to which the generated TextViews should be added
     */
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
        setContentView(R.layout.activity_all_wines);
        
        //setup navBar
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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