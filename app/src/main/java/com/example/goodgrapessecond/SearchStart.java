
package com.example.goodgrapessecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchStart extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_start);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // our search view!
        SearchView searchView = (SearchView) findViewById(R.id.search);
        // set an event listener for the text you type in the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // on submitting our search query, we create a new Intent to request SearchAllWines
            // does something from SearchStart. We put the given search query in this intent and start the activity
            @Override
            public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(SearchStart.this, SearchAllWines.class);
                    intent.putExtra("search_key", query);
                    startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //System.out.println(query);
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
                        startActivity(new Intent(getApplicationContext(), SearchStart.class));
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

        //button to viewAllWine
        button = (Button) findViewById(R.id.viewAllWine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllWines();
            }
        });
    }

    public void openAllWines() {
        Intent intent = new Intent(this, AllWines.class);
        startActivity(intent);
    }

}