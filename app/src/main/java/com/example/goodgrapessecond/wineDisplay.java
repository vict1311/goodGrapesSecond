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

import java.text.DecimalFormat;

public class wineDisplay extends AppCompatActivity {

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
        TraceabilityInformation traceToShow = wineToShow.traceabilityRetrieval();
        DecimalFormat df = new DecimalFormat("0.00");

        // we have calculated the average values for our wines for transport and agriculture traceability information
        double transDifference = (traceToShow.transportImpact - 0.655) / 0.655 * 100;

        double agriDifference = (traceToShow.agricultureImpact - 0.3125) / 0.3125 * 100;






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
                        startActivity(new Intent(getApplicationContext(),SearchStart.class));
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




        TextView textViewToChangeName = (TextView) findViewById(R.id.nameVAR);
        textViewToChangeName.setText(
                "" + wineToShow.name);


        TextView textViewToTransport = (TextView) findViewById(R.id.transport);
            if (transDifference > 0) {
                //textViewToTransport.setTextColor(0-102-0);
                textViewToTransport.setText(
                        "" + df.format(transDifference) + "%");
            } else {
                //textViewToTransport.setTextColor(255-102-0);
                textViewToTransport.setText(
                        "" + df.format(transDifference * -1) + "%");
        }

        TextView textViewToTransportText = (TextView) findViewById(R.id.transporttext);
        if (transDifference > 0) {
            textViewToTransportText.setText(
                    "This wine uses " + df.format(transDifference) + "% more CO2-e on transport than the average wine");
        } else {
            textViewToTransportText.setText(
                    "This wine uses " + df.format(transDifference * -1) + "% less CO2-e on transport than the average wine");
        }

        TextView textViewToArgiculture = (TextView) findViewById(R.id.agriculture);
            if (agriDifference > 0) {
                textViewToArgiculture.setText(
                        "" + df.format(agriDifference) + "%");
            } else {
                textViewToArgiculture.setText(
                        "" + df.format(agriDifference * -1) + "%");
            }

        TextView textViewToArgicultureText = (TextView) findViewById(R.id.agriculturetext);
        if (agriDifference > 0) {
            textViewToArgicultureText.setText(
                    "This wine uses " + df.format(agriDifference) + "% more CO2-e during farming than the average wine");
        } else {
            textViewToArgicultureText.setText(
                    "This wine uses " + df.format(agriDifference * -1) + "% less CO2-e during farming than the average wine");
        }







        // we use year as a placeholder for country :)
        //TextView textViewToChangeCountry = (TextView) findViewById(R.id.countryVAR);
        //textViewToChangeCountry.setText(
                //"Country: " + wineToShow.year);

        //TextView textViewToChangeGrape = (TextView) findViewById(R.id.grapeVAR);
       // textViewToChangeGrape.setText(
               // "Grape: " + wineToShow.grape);

        //TextView textViewToChangeType = (TextView) findViewById(R.id.typeVAR);
        // textViewToChangeType.setText(
             //   "Grape: " + wineToShow.type);

      



        // button to add and remove
        Button button = (Button) findViewById(R.id.addRemove);
        //button to read more
        Button buttonRead = (Button) findViewById(R.id.readMore);
        // set right of button to be the drawable of a forward arrow
        buttonRead.setCompoundDrawablesWithIntrinsicBounds( 0, 0,R.drawable.ic_ai_forward, 0);
        // apparently libSaved is always false, like below?? WHY????
        if (wineToShow.libSaved == true) {
            button.setText("Remove from favorites");
        }
        else {
            button.setText("Add to favorites");
        }


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

        //it is interesting to note that printing libSaved in MainActivity in UserLibrary will actually print true - why not here?
        button.setOnClickListener(new View.OnClickListener() {
            @Override
               public void onClick(View v) {
                   if (wineToShow.libSaved == false) {
                       //MainActivity.currentLibrary.removeWineFromLibrary(wineToShow);
                       MainActivity.currentLibrary.addWineToLibrary(wineToShow);
                       System.out.println(MainActivity.currentLibrary.winesInLib);
                       System.out.println(wineToShow.libSaved);
                       button.setText("Remove from favorites");
                       // we start a new intent
                       //Intent i = new Intent(wineDisplay.this, MainActivity.class);
                       //startActivity(i);
                   }
                   else {
                       MainActivity.currentLibrary.removeWineFromLibrary(wineToShow);
                       System.out.println(MainActivity.currentLibrary.winesInLib);
                       System.out.println(wineToShow.libSaved);
                       button.setText("Add to favorites");
                   }
               }
           }
        );

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wineDisplay.this, ReadMore.class));
            }
        } );
    }
}