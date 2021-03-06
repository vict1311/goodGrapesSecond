package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    private LinearLayout linearLayout;
    //By using onCreate method we call and recreate the activity (which in this case is the navbar)
    //to be displayed on profile
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //then we ask the navbar to high light profile
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        //here we tell the navbar than when one of each icons (search, favorites or profile) is clicked
        //then the application needs to get the new item and display.
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchStart.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // create LinearLayout and add textview containing the current user's name
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutProfile);

        TextView newText = new TextView(this);

        newText.setText("Name: " + MainActivity.currentUser.name +
                "\r\n");
        newText.setTextColor(getResources().getColor(R.color.black));

        linearLayout.addView(newText);
    }
}